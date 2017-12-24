package okhttp3.internal.ws;

import android.support.v4.view.PointerIconCompat;
import java.net.ProtocolException;

public final class WebSocketProtocol {
    public static final String ACCEPT_MAGIC = "258EAFA5-E914-47DA-95CA-C5AB0DC85B11";
    static final int B0_FLAG_FIN = 128;
    static final int B0_FLAG_RSV1 = 64;
    static final int B0_FLAG_RSV2 = 32;
    static final int B0_FLAG_RSV3 = 16;
    static final int B0_MASK_OPCODE = 15;
    static final int B1_FLAG_MASK = 128;
    static final int B1_MASK_LENGTH = 127;
    static final int OPCODE_BINARY = 2;
    static final int OPCODE_CONTINUATION = 0;
    static final int OPCODE_CONTROL_CLOSE = 8;
    static final int OPCODE_CONTROL_PING = 9;
    static final int OPCODE_CONTROL_PONG = 10;
    static final int OPCODE_FLAG_CONTROL = 8;
    static final int OPCODE_TEXT = 1;
    static final long PAYLOAD_BYTE_MAX = 125;
    static final int PAYLOAD_LONG = 127;
    static final int PAYLOAD_SHORT = 126;
    static final long PAYLOAD_SHORT_MAX = 65535;

    static void toggleMask(byte[] buffer, long byteCount, byte[] key, long frameBytesRead) {
        int keyLength = key.length;
        int i = 0;
        while (((long) i) < byteCount) {
            buffer[i] = (byte) (buffer[i] ^ key[(int) (frameBytesRead % ((long) keyLength))]);
            i++;
            frameBytesRead++;
        }
    }

    static void validateCloseCode(int code, boolean argument) throws ProtocolException {
        String message = null;
        if (code < 1000 || code >= 5000) {
            message = "Code must be in range [1000,5000): " + code;
        } else if ((code >= PointerIconCompat.TYPE_WAIT && code <= 1006) || (code >= PointerIconCompat.TYPE_NO_DROP && code <= 2999)) {
            message = "Code " + code + " is reserved and may not be used.";
        }
        if (message == null) {
            return;
        }
        if (argument) {
            throw new IllegalArgumentException(message);
        }
        throw new ProtocolException(message);
    }

    private WebSocketProtocol() {
        throw new AssertionError("No instances.");
    }
}
