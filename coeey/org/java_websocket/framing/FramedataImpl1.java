package org.java_websocket.framing;

import java.nio.ByteBuffer;
import java.util.Arrays;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.util.ByteBufferUtils;
import org.java_websocket.util.Charsetfunctions;

public abstract class FramedataImpl1 implements Framedata {
    private boolean fin = true;
    private Opcode optcode;
    private boolean rsv1 = false;
    private boolean rsv2 = false;
    private boolean rsv3 = false;
    private boolean transferemasked = false;
    private ByteBuffer unmaskedpayload = ByteBufferUtils.getEmptyByteBuffer();

    public abstract void isValid() throws InvalidDataException;

    public FramedataImpl1(Opcode op) {
        this.optcode = op;
    }

    public boolean isRSV1() {
        return this.rsv1;
    }

    public boolean isRSV2() {
        return this.rsv2;
    }

    public boolean isRSV3() {
        return this.rsv3;
    }

    public boolean isFin() {
        return this.fin;
    }

    public Opcode getOpcode() {
        return this.optcode;
    }

    public boolean getTransfereMasked() {
        return this.transferemasked;
    }

    public ByteBuffer getPayloadData() {
        return this.unmaskedpayload;
    }

    public void append(Framedata nextframe) {
        ByteBuffer b = nextframe.getPayloadData();
        if (this.unmaskedpayload == null) {
            this.unmaskedpayload = ByteBuffer.allocate(b.remaining());
            b.mark();
            this.unmaskedpayload.put(b);
            b.reset();
        } else {
            b.mark();
            this.unmaskedpayload.position(this.unmaskedpayload.limit());
            this.unmaskedpayload.limit(this.unmaskedpayload.capacity());
            if (b.remaining() > this.unmaskedpayload.remaining()) {
                ByteBuffer tmp = ByteBuffer.allocate(b.remaining() + this.unmaskedpayload.capacity());
                this.unmaskedpayload.flip();
                tmp.put(this.unmaskedpayload);
                tmp.put(b);
                this.unmaskedpayload = tmp;
            } else {
                this.unmaskedpayload.put(b);
            }
            this.unmaskedpayload.rewind();
            b.reset();
        }
        this.fin = nextframe.isFin();
    }

    public String toString() {
        return "Framedata{ optcode:" + getOpcode() + ", fin:" + isFin() + ", rsv1:" + isRSV1() + ", rsv2:" + isRSV2() + ", rsv3:" + isRSV3() + ", payloadlength:[pos:" + this.unmaskedpayload.position() + ", len:" + this.unmaskedpayload.remaining() + "], payload:" + Arrays.toString(Charsetfunctions.utf8Bytes(new String(this.unmaskedpayload.array()))) + "}";
    }

    public void setPayload(ByteBuffer payload) {
        this.unmaskedpayload = payload;
    }

    public void setFin(boolean fin) {
        this.fin = fin;
    }

    public void setRSV1(boolean rsv1) {
        this.rsv1 = rsv1;
    }

    public void setRSV2(boolean rsv2) {
        this.rsv2 = rsv2;
    }

    public void setRSV3(boolean rsv3) {
        this.rsv3 = rsv3;
    }

    public void setTransferemasked(boolean transferemasked) {
        this.transferemasked = transferemasked;
    }

    public static FramedataImpl1 get(Opcode opcode) {
        if (opcode == null) {
            throw new IllegalArgumentException("Supplied opcode cannot be null");
        }
        switch (opcode) {
            case PING:
                return new PingFrame();
            case PONG:
                return new PongFrame();
            case TEXT:
                return new TextFrame();
            case BINARY:
                return new BinaryFrame();
            case CLOSING:
                return new CloseFrame();
            case CONTINUOUS:
                return new ContinuousFrame();
            default:
                throw new IllegalArgumentException("Supplied opcode is invalid");
        }
    }
}
