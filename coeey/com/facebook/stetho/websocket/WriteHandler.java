package com.facebook.stetho.websocket;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
class WriteHandler {
    private final BufferedOutputStream mBufferedOutput;

    public WriteHandler(OutputStream rawSocketOutput) {
        this.mBufferedOutput = new BufferedOutputStream(rawSocketOutput, 1024);
    }

    public synchronized void write(Frame frame, WriteCallback callback) {
        try {
            frame.writeTo(this.mBufferedOutput);
            this.mBufferedOutput.flush();
            callback.onSuccess();
        } catch (IOException e) {
            callback.onFailure(e);
        }
    }
}
