package com.facebook.stetho.server;

import android.net.LocalSocket;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class SocketLike {
    private final LeakyBufferedInputStream mLeakyInput;
    private final LocalSocket mSocket;

    public SocketLike(SocketLike socketLike, LeakyBufferedInputStream leakyInput) {
        this(socketLike.mSocket, leakyInput);
    }

    public SocketLike(LocalSocket socket, LeakyBufferedInputStream leakyInput) {
        this.mSocket = socket;
        this.mLeakyInput = leakyInput;
    }

    public InputStream getInput() throws IOException {
        return this.mLeakyInput.leakBufferAndStream();
    }

    public OutputStream getOutput() throws IOException {
        return this.mSocket.getOutputStream();
    }
}
