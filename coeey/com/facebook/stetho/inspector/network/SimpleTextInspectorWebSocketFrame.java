package com.facebook.stetho.inspector.network;

public class SimpleTextInspectorWebSocketFrame implements NetworkEventReporter$InspectorWebSocketFrame {
    private final String mPayload;
    private final String mRequestId;

    public SimpleTextInspectorWebSocketFrame(String requestId, String payload) {
        this.mRequestId = requestId;
        this.mPayload = payload;
    }

    public String requestId() {
        return this.mRequestId;
    }

    public int opcode() {
        return 1;
    }

    public boolean mask() {
        return false;
    }

    public String payloadData() {
        return this.mPayload;
    }
}
