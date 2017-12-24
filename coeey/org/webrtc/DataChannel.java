package org.webrtc;

import java.nio.ByteBuffer;

public class DataChannel {
    private final long nativeDataChannel;
    private long nativeObserver;

    public interface Observer {
        void onBufferedAmountChange(long j);

        void onMessage(Buffer buffer);

        void onStateChange();
    }

    public static class Buffer {
        public final boolean binary;
        public final ByteBuffer data;

        public Buffer(ByteBuffer data, boolean binary) {
            this.data = data;
            this.binary = binary;
        }
    }

    public static class Init {
        public int id = -1;
        public int maxRetransmitTimeMs = -1;
        public int maxRetransmits = -1;
        public boolean negotiated = false;
        public boolean ordered = true;
        public String protocol = "";

        private Init(boolean ordered, int maxRetransmitTimeMs, int maxRetransmits, String protocol, boolean negotiated, int id) {
            this.ordered = ordered;
            this.maxRetransmitTimeMs = maxRetransmitTimeMs;
            this.maxRetransmits = maxRetransmits;
            this.protocol = protocol;
            this.negotiated = negotiated;
            this.id = id;
        }
    }

    public enum State {
        CONNECTING,
        OPEN,
        CLOSING,
        CLOSED
    }

    private native long registerObserverNative(Observer observer);

    private native boolean sendNative(byte[] bArr, boolean z);

    private native void unregisterObserverNative(long j);

    public native long bufferedAmount();

    public native void close();

    public native void dispose();

    public native int id();

    public native String label();

    public native State state();

    public DataChannel(long nativeDataChannel) {
        this.nativeDataChannel = nativeDataChannel;
    }

    public void registerObserver(Observer observer) {
        if (this.nativeObserver != 0) {
            unregisterObserverNative(this.nativeObserver);
        }
        this.nativeObserver = registerObserverNative(observer);
    }

    public void unregisterObserver() {
        unregisterObserverNative(this.nativeObserver);
    }

    public boolean send(Buffer buffer) {
        byte[] data = new byte[buffer.data.remaining()];
        buffer.data.get(data);
        return sendNative(data, buffer.binary);
    }
}
