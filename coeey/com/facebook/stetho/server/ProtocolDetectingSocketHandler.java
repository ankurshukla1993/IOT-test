package com.facebook.stetho.server;

import android.content.Context;
import android.net.LocalSocket;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class ProtocolDetectingSocketHandler extends SecureSocketHandler {
    private static final int SENSING_BUFFER_SIZE = 256;
    private final ArrayList<HandlerInfo> mHandlers = new ArrayList(2);

    public interface MagicMatcher {
        boolean matches(InputStream inputStream) throws IOException;
    }

    public static class AlwaysMatchMatcher implements MagicMatcher {
        public boolean matches(InputStream in) throws IOException {
            return true;
        }
    }

    public static class ExactMagicMatcher implements MagicMatcher {
        private final byte[] mMagic;

        public ExactMagicMatcher(byte[] magic) {
            this.mMagic = magic;
        }

        public boolean matches(InputStream in) throws IOException {
            byte[] buf = new byte[this.mMagic.length];
            return in.read(buf) == buf.length && Arrays.equals(buf, this.mMagic);
        }
    }

    private static class HandlerInfo {
        public final SocketLikeHandler handler;
        public final MagicMatcher magicMatcher;

        private HandlerInfo(MagicMatcher magicMatcher, SocketLikeHandler handler) {
            this.magicMatcher = magicMatcher;
            this.handler = handler;
        }
    }

    public ProtocolDetectingSocketHandler(Context context) {
        super(context);
    }

    public void addHandler(MagicMatcher magicMatcher, SocketLikeHandler handler) {
        this.mHandlers.add(new HandlerInfo(magicMatcher, handler));
    }

    protected void onSecured(LocalSocket socket) throws IOException {
        LeakyBufferedInputStream leakyIn = new LeakyBufferedInputStream(socket.getInputStream(), 256);
        if (this.mHandlers.isEmpty()) {
            throw new IllegalStateException("No handlers added");
        }
        int N = this.mHandlers.size();
        for (int i = 0; i < N; i++) {
            HandlerInfo handlerInfo = (HandlerInfo) this.mHandlers.get(i);
            leakyIn.mark(256);
            boolean matches = handlerInfo.magicMatcher.matches(leakyIn);
            leakyIn.reset();
            if (matches) {
                handlerInfo.handler.onAccepted(new SocketLike(socket, leakyIn));
                return;
            }
        }
        throw new IOException("No matching handler, firstByte=" + leakyIn.read());
    }
}
