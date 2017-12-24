package okhttp3.internal.ws;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.ws.WebSocket;
import okio.Buffer;
import okio.BufferedSource;
import okio.Okio;
import okio.Source;
import okio.Timeout;

public final class WebSocketReader {
    private boolean closed;
    private long frameBytesRead;
    private final FrameCallback frameCallback;
    private long frameLength;
    private final Source framedMessageSource = new FramedMessageSource();
    private final boolean isClient;
    private boolean isControlFrame;
    private boolean isFinalFrame;
    private boolean isMasked;
    private final byte[] maskBuffer = new byte[8192];
    private final byte[] maskKey = new byte[4];
    private boolean messageClosed;
    private int opcode;
    private final BufferedSource source;

    public interface FrameCallback {
        void onClose(int i, String str);

        void onMessage(ResponseBody responseBody) throws IOException;

        void onPing(Buffer buffer);

        void onPong(Buffer buffer);
    }

    private final class FramedMessageSource implements Source {
        private FramedMessageSource() {
        }

        public long read(Buffer sink, long byteCount) throws IOException {
            if (WebSocketReader.this.closed) {
                throw new IOException("closed");
            } else if (WebSocketReader.this.messageClosed) {
                throw new IllegalStateException("closed");
            } else {
                long read;
                if (WebSocketReader.this.frameBytesRead == WebSocketReader.this.frameLength) {
                    if (WebSocketReader.this.isFinalFrame) {
                        return -1;
                    }
                    WebSocketReader.this.readUntilNonControlFrame();
                    if (WebSocketReader.this.opcode != 0) {
                        throw new ProtocolException("Expected continuation opcode. Got: " + Integer.toHexString(WebSocketReader.this.opcode));
                    } else if (WebSocketReader.this.isFinalFrame && WebSocketReader.this.frameLength == 0) {
                        return -1;
                    }
                }
                long toRead = Math.min(byteCount, WebSocketReader.this.frameLength - WebSocketReader.this.frameBytesRead);
                if (WebSocketReader.this.isMasked) {
                    read = (long) WebSocketReader.this.source.read(WebSocketReader.this.maskBuffer, 0, (int) Math.min(toRead, (long) WebSocketReader.this.maskBuffer.length));
                    if (read == -1) {
                        throw new EOFException();
                    }
                    WebSocketProtocol.toggleMask(WebSocketReader.this.maskBuffer, read, WebSocketReader.this.maskKey, WebSocketReader.this.frameBytesRead);
                    sink.write(WebSocketReader.this.maskBuffer, 0, (int) read);
                } else {
                    read = WebSocketReader.this.source.read(sink, toRead);
                    if (read == -1) {
                        throw new EOFException();
                    }
                }
                WebSocketReader.this.frameBytesRead = WebSocketReader.this.frameBytesRead + read;
                return read;
            }
        }

        public Timeout timeout() {
            return WebSocketReader.this.source.timeout();
        }

        public void close() throws IOException {
            if (!WebSocketReader.this.messageClosed) {
                WebSocketReader.this.messageClosed = true;
                if (!WebSocketReader.this.closed) {
                    WebSocketReader.this.source.skip(WebSocketReader.this.frameLength - WebSocketReader.this.frameBytesRead);
                    while (!WebSocketReader.this.isFinalFrame) {
                        WebSocketReader.this.readUntilNonControlFrame();
                        WebSocketReader.this.source.skip(WebSocketReader.this.frameLength);
                    }
                }
            }
        }
    }

    public WebSocketReader(boolean isClient, BufferedSource source, FrameCallback frameCallback) {
        if (source == null) {
            throw new NullPointerException("source == null");
        } else if (frameCallback == null) {
            throw new NullPointerException("frameCallback == null");
        } else {
            this.isClient = isClient;
            this.source = source;
            this.frameCallback = frameCallback;
        }
    }

    public void processNextFrame() throws IOException {
        readHeader();
        if (this.isControlFrame) {
            readControlFrame();
        } else {
            readMessageFrame();
        }
    }

    private void readHeader() throws IOException {
        boolean z = true;
        if (this.closed) {
            throw new IOException("closed");
        }
        boolean z2;
        int b0 = this.source.readByte() & 255;
        this.opcode = b0 & 15;
        this.isFinalFrame = (b0 & 128) != 0;
        if ((b0 & 8) != 0) {
            z2 = true;
        } else {
            z2 = false;
        }
        this.isControlFrame = z2;
        if (!this.isControlFrame || this.isFinalFrame) {
            boolean reservedFlag1;
            if ((b0 & 64) != 0) {
                reservedFlag1 = true;
            } else {
                reservedFlag1 = false;
            }
            boolean reservedFlag2;
            if ((b0 & 32) != 0) {
                reservedFlag2 = true;
            } else {
                reservedFlag2 = false;
            }
            boolean reservedFlag3;
            if ((b0 & 16) != 0) {
                reservedFlag3 = true;
            } else {
                reservedFlag3 = false;
            }
            if (reservedFlag1 || reservedFlag2 || reservedFlag3) {
                throw new ProtocolException("Reserved flags are unsupported.");
            }
            int b1 = this.source.readByte() & 255;
            if ((b1 & 128) == 0) {
                z = false;
            }
            this.isMasked = z;
            if (this.isMasked == this.isClient) {
                throw new ProtocolException("Client-sent frames must be masked. Server sent must not.");
            }
            this.frameLength = (long) (b1 & 127);
            if (this.frameLength == 126) {
                this.frameLength = ((long) this.source.readShort()) & 65535;
            } else if (this.frameLength == 127) {
                this.frameLength = this.source.readLong();
                if (this.frameLength < 0) {
                    throw new ProtocolException("Frame length 0x" + Long.toHexString(this.frameLength) + " > 0x7FFFFFFFFFFFFFFF");
                }
            }
            this.frameBytesRead = 0;
            if (this.isControlFrame && this.frameLength > 125) {
                throw new ProtocolException("Control frame must be less than 125B.");
            } else if (this.isMasked) {
                this.source.readFully(this.maskKey);
                return;
            } else {
                return;
            }
        }
        throw new ProtocolException("Control frames must be final.");
    }

    private void readControlFrame() throws IOException {
        Buffer buffer = null;
        if (this.frameBytesRead < this.frameLength) {
            buffer = new Buffer();
            if (this.isClient) {
                this.source.readFully(buffer, this.frameLength);
            } else {
                while (this.frameBytesRead < this.frameLength) {
                    int read = this.source.read(this.maskBuffer, 0, (int) Math.min(this.frameLength - this.frameBytesRead, (long) this.maskBuffer.length));
                    if (read == -1) {
                        throw new EOFException();
                    }
                    WebSocketProtocol.toggleMask(this.maskBuffer, (long) read, this.maskKey, this.frameBytesRead);
                    buffer.write(this.maskBuffer, 0, read);
                    this.frameBytesRead += (long) read;
                }
            }
        }
        switch (this.opcode) {
            case 8:
                int code = 1000;
                String reason = "";
                if (buffer != null) {
                    long bufferSize = buffer.size();
                    if (bufferSize == 1) {
                        throw new ProtocolException("Malformed close payload length of 1.");
                    } else if (bufferSize != 0) {
                        code = buffer.readShort();
                        WebSocketProtocol.validateCloseCode(code, false);
                        reason = buffer.readUtf8();
                    }
                }
                this.frameCallback.onClose(code, reason);
                this.closed = true;
                return;
            case 9:
                this.frameCallback.onPing(buffer);
                return;
            case 10:
                this.frameCallback.onPong(buffer);
                return;
            default:
                throw new ProtocolException("Unknown control opcode: " + Integer.toHexString(this.opcode));
        }
    }

    private void readMessageFrame() throws IOException {
        MediaType type;
        switch (this.opcode) {
            case 1:
                type = WebSocket.TEXT;
                break;
            case 2:
                type = WebSocket.BINARY;
                break;
            default:
                throw new ProtocolException("Unknown opcode: " + Integer.toHexString(this.opcode));
        }
        final BufferedSource source = Okio.buffer(this.framedMessageSource);
        ResponseBody body = new ResponseBody() {
            public MediaType contentType() {
                return type;
            }

            public long contentLength() {
                return -1;
            }

            public BufferedSource source() {
                return source;
            }
        };
        this.messageClosed = false;
        this.frameCallback.onMessage(body);
        if (!this.messageClosed) {
            throw new IllegalStateException("Listener failed to call close on message payload.");
        }
    }

    private void readUntilNonControlFrame() throws IOException {
        while (!this.closed) {
            readHeader();
            if (this.isControlFrame) {
                readControlFrame();
            } else {
                return;
            }
        }
    }
}
