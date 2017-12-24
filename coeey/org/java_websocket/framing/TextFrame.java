package org.java_websocket.framing;

import org.java_websocket.framing.Framedata.Opcode;

public class TextFrame extends DataFrame {
    public TextFrame() {
        super(Opcode.TEXT);
    }
}
