package org.java_websocket.drafts;

import com.google.common.net.HttpHeaders;
import com.ihealth.communication.control.AmProfile;
import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import org.java_websocket.drafts.Draft.CloseHandshakeType;
import org.java_websocket.drafts.Draft.HandshakeState;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.exceptions.InvalidHandshakeException;
import org.java_websocket.exceptions.LimitExedeedException;
import org.java_websocket.exceptions.NotSendableException;
import org.java_websocket.framing.Framedata;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.framing.TextFrame;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.handshake.ClientHandshakeBuilder;
import org.java_websocket.handshake.HandshakeBuilder;
import org.java_websocket.handshake.ServerHandshake;
import org.java_websocket.handshake.ServerHandshakeBuilder;
import org.java_websocket.util.Charsetfunctions;

@Deprecated
public class Draft_75 extends Draft {
    public static final byte CR = (byte) 13;
    public static final byte END_OF_FRAME = (byte) -1;
    public static final byte LF = (byte) 10;
    public static final byte START_OF_FRAME = (byte) 0;
    protected ByteBuffer currentFrame;
    protected boolean readingState = false;
    protected List<Framedata> readyframes = new LinkedList();
    private final Random reuseableRandom = new Random();

    public HandshakeState acceptHandshakeAsClient(ClientHandshake request, ServerHandshake response) {
        return (request.getFieldValue("WebSocket-Origin").equals(response.getFieldValue(HttpHeaders.ORIGIN)) && basicAccept(response)) ? HandshakeState.MATCHED : HandshakeState.NOT_MATCHED;
    }

    public HandshakeState acceptHandshakeAsServer(ClientHandshake handshakedata) {
        if (handshakedata.hasFieldValue(HttpHeaders.ORIGIN) && basicAccept(handshakedata)) {
            return HandshakeState.MATCHED;
        }
        return HandshakeState.NOT_MATCHED;
    }

    public ByteBuffer createBinaryFrame(Framedata framedata) {
        if (framedata.getOpcode() != Opcode.TEXT) {
            throw new RuntimeException("only text frames supported");
        }
        ByteBuffer pay = framedata.getPayloadData();
        ByteBuffer b = ByteBuffer.allocate(pay.remaining() + 2);
        b.put((byte) 0);
        pay.mark();
        b.put(pay);
        pay.reset();
        b.put((byte) -1);
        b.flip();
        return b;
    }

    public List<Framedata> createFrames(ByteBuffer binary, boolean mask) {
        throw new RuntimeException("not yet implemented");
    }

    public List<Framedata> createFrames(String text, boolean mask) {
        TextFrame frame = new TextFrame();
        frame.setPayload(ByteBuffer.wrap(Charsetfunctions.utf8Bytes(text)));
        frame.setTransferemasked(mask);
        try {
            frame.isValid();
            return Collections.singletonList(frame);
        } catch (InvalidDataException e) {
            throw new NotSendableException(e);
        }
    }

    public ClientHandshakeBuilder postProcessHandshakeRequestAsClient(ClientHandshakeBuilder request) throws InvalidHandshakeException {
        request.put(HttpHeaders.UPGRADE, "WebSocket");
        request.put(HttpHeaders.CONNECTION, HttpHeaders.UPGRADE);
        if (!request.hasFieldValue(HttpHeaders.ORIGIN)) {
            request.put(HttpHeaders.ORIGIN, AmProfile.GET_RANDOM_AM + this.reuseableRandom.nextInt());
        }
        return request;
    }

    public HandshakeBuilder postProcessHandshakeResponseAsServer(ClientHandshake request, ServerHandshakeBuilder response) throws InvalidHandshakeException {
        response.setHttpStatusMessage("Web Socket Protocol Handshake");
        response.put(HttpHeaders.UPGRADE, "WebSocket");
        response.put(HttpHeaders.CONNECTION, request.getFieldValue(HttpHeaders.CONNECTION));
        response.put("WebSocket-Origin", request.getFieldValue(HttpHeaders.ORIGIN));
        response.put("WebSocket-Location", "ws://" + request.getFieldValue(HttpHeaders.HOST) + request.getResourceDescriptor());
        return response;
    }

    protected List<Framedata> translateRegularFrame(ByteBuffer buffer) throws InvalidDataException {
        while (buffer.hasRemaining()) {
            byte newestByte = buffer.get();
            if (newestByte == (byte) 0) {
                if (this.readingState) {
                    throw new InvalidFrameException("unexpected START_OF_FRAME");
                }
                this.readingState = true;
            } else if (newestByte == (byte) -1) {
                if (this.readingState) {
                    if (this.currentFrame != null) {
                        this.currentFrame.flip();
                        TextFrame curframe = new TextFrame();
                        curframe.setPayload(this.currentFrame);
                        this.readyframes.add(curframe);
                        this.currentFrame = null;
                        buffer.mark();
                    }
                    this.readingState = false;
                } else {
                    throw new InvalidFrameException("unexpected END_OF_FRAME");
                }
            } else if (!this.readingState) {
                return null;
            } else {
                if (this.currentFrame == null) {
                    this.currentFrame = createBuffer();
                } else if (!this.currentFrame.hasRemaining()) {
                    this.currentFrame = increaseBuffer(this.currentFrame);
                }
                this.currentFrame.put(newestByte);
            }
        }
        List<Framedata> frames = this.readyframes;
        this.readyframes = new LinkedList();
        return frames;
    }

    public List<Framedata> translateFrame(ByteBuffer buffer) throws InvalidDataException {
        List<Framedata> frames = translateRegularFrame(buffer);
        if (frames != null) {
            return frames;
        }
        throw new InvalidDataException(1002);
    }

    public void reset() {
        this.readingState = false;
        this.currentFrame = null;
    }

    public CloseHandshakeType getCloseHandshakeType() {
        return CloseHandshakeType.NONE;
    }

    public ByteBuffer createBuffer() {
        return ByteBuffer.allocate(INITIAL_FAMESIZE);
    }

    public ByteBuffer increaseBuffer(ByteBuffer full) throws LimitExedeedException, InvalidDataException {
        full.flip();
        ByteBuffer newbuffer = ByteBuffer.allocate(checkAlloc(full.capacity() * 2));
        newbuffer.put(full);
        return newbuffer;
    }

    public Draft copyInstance() {
        return new Draft_75();
    }
}
