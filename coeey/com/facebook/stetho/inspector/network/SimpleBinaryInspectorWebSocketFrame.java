package com.facebook.stetho.inspector.network;

import java.io.UnsupportedEncodingException;

public class SimpleBinaryInspectorWebSocketFrame implements NetworkEventReporter$InspectorWebSocketFrame {
    private final byte[] mPayload;
    private final String mRequestId;

    public SimpleBinaryInspectorWebSocketFrame(String requestId, byte[] payload) {
        this.mRequestId = requestId;
        this.mPayload = payload;
    }

    public String requestId() {
        return this.mRequestId;
    }

    public int opcode() {
        return 2;
    }

    public boolean mask() {
        return false;
    }

    public String payloadData() {
        try {
            return new String(this.mPayload, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
