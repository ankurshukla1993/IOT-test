package okhttp3.internal.ws;

import java.io.IOException;
import java.net.ProtocolException;
import java.util.Random;
import java.util.concurrent.Executor;
import java.util.concurrent.atomic.AtomicBoolean;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.internal.NamedRunnable;
import okhttp3.internal.ws.WebSocketReader.FrameCallback;
import okhttp3.ws.WebSocket;
import okhttp3.ws.WebSocketListener;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public abstract class RealWebSocket implements WebSocket {
    private static final int CLOSE_PROTOCOL_EXCEPTION = 1002;
    private final AtomicBoolean connectionClosed = new AtomicBoolean();
    private final WebSocketListener listener;
    private final WebSocketReader reader;
    private boolean readerSentClose;
    private final WebSocketWriter writer;
    private volatile boolean writerSentClose;
    private boolean writerWantsClose;

    protected abstract void close() throws IOException;

    public RealWebSocket(boolean isClient, BufferedSource source, BufferedSink sink, Random random, final Executor replyExecutor, final WebSocketListener listener, final String url) {
        this.listener = listener;
        this.writer = new WebSocketWriter(isClient, sink, random);
        this.reader = new WebSocketReader(isClient, source, new FrameCallback() {
            public void onMessage(ResponseBody message) throws IOException {
                listener.onMessage(message);
            }

            public void onPing(final Buffer buffer) {
                replyExecutor.execute(new NamedRunnable("OkHttp %s WebSocket Pong Reply", new Object[]{url}) {
                    protected void execute() {
                        try {
                            RealWebSocket.this.writer.writePong(buffer);
                        } catch (IOException e) {
                        }
                    }
                });
            }

            public void onPong(Buffer buffer) {
                listener.onPong(buffer);
            }

            public void onClose(int code, String reason) {
                RealWebSocket.this.readerSentClose = true;
                final int i = code;
                final String str = reason;
                replyExecutor.execute(new NamedRunnable("OkHttp %s WebSocket Close Reply", new Object[]{url}) {
                    protected void execute() {
                        RealWebSocket.this.peerClose(i, str);
                    }
                });
            }
        });
    }

    public boolean readMessage() {
        try {
            this.reader.processNextFrame();
            if (this.readerSentClose) {
                return false;
            }
            return true;
        } catch (IOException e) {
            readerErrorClose(e);
            return false;
        }
    }

    public void sendMessage(RequestBody message) throws IOException {
        if (message == null) {
            throw new NullPointerException("message == null");
        } else if (this.writerSentClose) {
            throw new IllegalStateException("closed");
        } else if (this.writerWantsClose) {
            throw new IllegalStateException("must call close()");
        } else {
            MediaType contentType = message.contentType();
            if (contentType == null) {
                throw new IllegalArgumentException("Message content type was null. Must use WebSocket.TEXT or WebSocket.BINARY.");
            }
            int formatOpcode;
            String contentSubtype = contentType.subtype();
            if (WebSocket.TEXT.subtype().equals(contentSubtype)) {
                formatOpcode = 1;
            } else if (WebSocket.BINARY.subtype().equals(contentSubtype)) {
                formatOpcode = 2;
            } else {
                throw new IllegalArgumentException("Unknown message content type: " + contentType.type() + "/" + contentType.subtype() + ". Must use WebSocket.TEXT or WebSocket.BINARY.");
            }
            BufferedSink sink = Okio.buffer(this.writer.newMessageSink(formatOpcode, message.contentLength()));
            try {
                message.writeTo(sink);
                sink.close();
            } catch (IOException e) {
                this.writerWantsClose = true;
                throw e;
            }
        }
    }

    public void sendPing(Buffer payload) throws IOException {
        if (this.writerSentClose) {
            throw new IllegalStateException("closed");
        } else if (this.writerWantsClose) {
            throw new IllegalStateException("must call close()");
        } else {
            try {
                this.writer.writePing(payload);
            } catch (IOException e) {
                this.writerWantsClose = true;
                throw e;
            }
        }
    }

    public void sendPong(Buffer payload) throws IOException {
        if (this.writerSentClose) {
            throw new IllegalStateException("closed");
        } else if (this.writerWantsClose) {
            throw new IllegalStateException("must call close()");
        } else {
            try {
                this.writer.writePong(payload);
            } catch (IOException e) {
                this.writerWantsClose = true;
                throw e;
            }
        }
    }

    public void close(int code, String reason) throws IOException {
        if (this.writerSentClose) {
            throw new IllegalStateException("closed");
        }
        this.writerSentClose = true;
        try {
            this.writer.writeClose(code, reason);
        } catch (IOException e) {
            if (this.connectionClosed.compareAndSet(false, true)) {
                try {
                    close();
                } catch (IOException e2) {
                }
            }
            throw e;
        }
    }

    private void peerClose(int code, String reason) {
        if (!this.writerSentClose) {
            try {
                this.writer.writeClose(code, reason);
            } catch (IOException e) {
            }
        }
        if (this.connectionClosed.compareAndSet(false, true)) {
            try {
                close();
            } catch (IOException e2) {
            }
        }
        this.listener.onClose(code, reason);
    }

    private void readerErrorClose(IOException e) {
        if (!this.writerSentClose && (e instanceof ProtocolException)) {
            try {
                this.writer.writeClose(1002, null);
            } catch (IOException e2) {
            }
        }
        if (this.connectionClosed.compareAndSet(false, true)) {
            try {
                close();
            } catch (IOException e3) {
            }
        }
        this.listener.onFailure(e, null);
    }
}
