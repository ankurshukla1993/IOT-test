package org.java_websocket.framing;

import org.java_websocket.framing.Framedata.Opcode;

public class BinaryFrame extends DataFrame {
    public BinaryFrame() {
        super(Opcode.BINARY);
    }
}
