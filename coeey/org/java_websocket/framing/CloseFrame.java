package org.java_websocket.framing;

import android.support.v4.view.PointerIconCompat;
import java.nio.ByteBuffer;
import org.java_websocket.exceptions.InvalidDataException;
import org.java_websocket.exceptions.InvalidFrameException;
import org.java_websocket.framing.Framedata.Opcode;
import org.java_websocket.util.ByteBufferUtils;
import org.java_websocket.util.Charsetfunctions;

public class CloseFrame extends ControlFrame {
    public static final int ABNORMAL_CLOSE = 1006;
    public static final int BUGGYCLOSE = -2;
    public static final int EXTENSION = 1010;
    public static final int FLASHPOLICY = -3;
    public static final int GOING_AWAY = 1001;
    public static final int NEVER_CONNECTED = -1;
    public static final int NOCODE = 1005;
    public static final int NORMAL = 1000;
    public static final int NO_UTF8 = 1007;
    public static final int POLICY_VALIDATION = 1008;
    public static final int PROTOCOL_ERROR = 1002;
    public static final int REFUSE = 1003;
    public static final int TLS_ERROR = 1015;
    public static final int TOOBIG = 1009;
    public static final int UNEXPECTED_CONDITION = 1011;
    private int code;
    private String reason;

    public CloseFrame() {
        super(Opcode.CLOSING);
        setReason("");
        setCode(1000);
    }

    public void setCode(int code) {
        this.code = code;
        if (code == 1015) {
            this.code = NOCODE;
            this.reason = "";
        }
        updatePayload();
    }

    public void setReason(String reason) {
        if (reason == null) {
            reason = "";
        }
        this.reason = reason;
        updatePayload();
    }

    public int getCloseCode() {
        return this.code;
    }

    public String getMessage() {
        return this.reason;
    }

    public String toString() {
        return super.toString() + "code: " + this.code;
    }

    public void isValid() throws InvalidDataException {
        super.isValid();
        if (this.code == 1007 && this.reason == null) {
            throw new InvalidDataException(1007);
        } else if (this.code == NOCODE && this.reason.length() > 0) {
            throw new InvalidDataException(1002, "A close frame must have a closecode if it has a reason");
        } else if (this.code > 1011 && this.code < 3000 && this.code != 1015) {
            throw new InvalidDataException(1002, "Trying to send an illegal close code!");
        } else if (this.code == 1006 || this.code == 1015 || this.code == NOCODE || this.code > 4999 || this.code < 1000 || this.code == PointerIconCompat.TYPE_WAIT) {
            throw new InvalidFrameException("closecode must not be sent over the wire: " + this.code);
        }
    }

    public void setPayload(ByteBuffer payload) {
        this.code = NOCODE;
        this.reason = "";
        payload.mark();
        if (payload.remaining() == 0) {
            this.code = 1000;
        } else if (payload.remaining() == 1) {
            this.code = 1002;
        } else {
            if (payload.remaining() >= 2) {
                ByteBuffer bb = ByteBuffer.allocate(4);
                bb.position(2);
                bb.putShort(payload.getShort());
                bb.position(0);
                this.code = bb.getInt();
            }
            payload.reset();
            try {
                int mark = payload.position();
                try {
                    payload.position(payload.position() + 2);
                    this.reason = Charsetfunctions.stringUtf8(payload);
                    payload.position(mark);
                } catch (IllegalArgumentException e) {
                    throw new InvalidDataException(1007);
                } catch (Throwable th) {
                    payload.position(mark);
                }
            } catch (InvalidDataException e2) {
                this.code = 1007;
                this.reason = null;
            }
        }
    }

    private void updatePayload() {
        byte[] by = Charsetfunctions.utf8Bytes(this.reason);
        ByteBuffer buf = ByteBuffer.allocate(4);
        buf.putInt(this.code);
        buf.position(2);
        ByteBuffer pay = ByteBuffer.allocate(by.length + 2);
        pay.put(buf);
        pay.put(by);
        pay.rewind();
        super.setPayload(pay);
    }

    public ByteBuffer getPayloadData() {
        if (this.code == NOCODE) {
            return ByteBufferUtils.getEmptyByteBuffer();
        }
        return super.getPayloadData();
    }
}
