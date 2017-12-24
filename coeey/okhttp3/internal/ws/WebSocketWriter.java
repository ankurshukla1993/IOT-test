package okhttp3.internal.ws;

import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Sink;
import okio.Timeout;

public final class WebSocketWriter {
    static final /* synthetic */ boolean $assertionsDisabled = (!WebSocketWriter.class.desiredAssertionStatus());
    private boolean activeWriter;
    private final Buffer buffer = new Buffer();
    private final FrameSink frameSink = new FrameSink();
    private final boolean isClient;
    private final byte[] maskBuffer;
    private final byte[] maskKey;
    private final Random random;
    private final BufferedSink sink;
    private boolean writerClosed;

    private final class FrameSink implements Sink {
        private boolean closed;
        private long contentLength;
        private int formatOpcode;
        private boolean isFirstFrame;

        private FrameSink() {
        }

        public void write(Buffer source, long byteCount) throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            WebSocketWriter.this.buffer.write(source, byteCount);
            boolean deferWrite;
            if (!this.isFirstFrame || this.contentLength == -1 || WebSocketWriter.this.buffer.size() <= this.contentLength - 8192) {
                deferWrite = false;
            } else {
                deferWrite = true;
            }
            long emitCount = WebSocketWriter.this.buffer.completeSegmentByteCount();
            if (emitCount > 0 && !deferWrite) {
                synchronized (WebSocketWriter.this) {
                    WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, emitCount, this.isFirstFrame, false);
                }
                this.isFirstFrame = false;
            }
        }

        public void flush() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, false);
            }
            this.isFirstFrame = false;
        }

        public Timeout timeout() {
            return WebSocketWriter.this.sink.timeout();
        }

        public void close() throws IOException {
            if (this.closed) {
                throw new IOException("closed");
            }
            synchronized (WebSocketWriter.this) {
                WebSocketWriter.this.writeMessageFrameSynchronized(this.formatOpcode, WebSocketWriter.this.buffer.size(), this.isFirstFrame, true);
            }
            this.closed = true;
            WebSocketWriter.this.activeWriter = false;
        }
    }

    public WebSocketWriter(boolean isClient, BufferedSink sink, Random random) {
        byte[] bArr = null;
        if (sink == null) {
            throw new NullPointerException("sink == null");
        } else if (random == null) {
            throw new NullPointerException("random == null");
        } else {
            byte[] bArr2;
            this.isClient = isClient;
            this.sink = sink;
            this.random = random;
            if (isClient) {
                bArr2 = new byte[4];
            } else {
                bArr2 = null;
            }
            this.maskKey = bArr2;
            if (isClient) {
                bArr = new byte[8192];
            }
            this.maskBuffer = bArr;
        }
    }

    public void writePing(Buffer payload) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(9, payload);
        }
    }

    public void writePong(Buffer payload) throws IOException {
        synchronized (this) {
            writeControlFrameSynchronized(10, payload);
        }
    }

    public void writeClose(int code, String reason) throws IOException {
        Buffer payload = null;
        if (!(code == 0 && reason == null)) {
            if (code != 0) {
                WebSocketProtocol.validateCloseCode(code, true);
            }
            payload = new Buffer();
            payload.writeShort(code);
            if (reason != null) {
                payload.writeUtf8(reason);
            }
        }
        synchronized (this) {
            writeControlFrameSynchronized(8, payload);
            this.writerClosed = true;
        }
    }

    private void writeControlFrameSynchronized(int opcode, Buffer payload) throws IOException {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.writerClosed) {
            throw new IOException("closed");
        } else {
            int length = 0;
            if (payload != null) {
                length = (int) payload.size();
                if (((long) length) > 125) {
                    throw new IllegalArgumentException("Payload size must be less than or equal to 125");
                }
            }
            this.sink.writeByte(opcode | 128);
            int b1 = length;
            if (this.isClient) {
                this.sink.writeByte(b1 | 128);
                this.random.nextBytes(this.maskKey);
                this.sink.write(this.maskKey);
                if (payload != null) {
                    writeMaskedSynchronized(payload, (long) length);
                }
            } else {
                this.sink.writeByte(b1);
                if (payload != null) {
                    this.sink.writeAll(payload);
                }
            }
            this.sink.emit();
        }
    }

    public Sink newMessageSink(int formatOpcode, long contentLength) {
        if (this.activeWriter) {
            throw new IllegalStateException("Another message writer is active. Did you call close()?");
        }
        this.activeWriter = true;
        this.frameSink.formatOpcode = formatOpcode;
        this.frameSink.contentLength = contentLength;
        this.frameSink.isFirstFrame = true;
        this.frameSink.closed = false;
        return this.frameSink;
    }

    private void writeMessageFrameSynchronized(int formatOpcode, long byteCount, boolean isFirstFrame, boolean isFinal) throws IOException {
        if (!$assertionsDisabled && !Thread.holdsLock(this)) {
            throw new AssertionError();
        } else if (this.writerClosed) {
            throw new IOException("closed");
        } else {
            int b0 = isFirstFrame ? formatOpcode : 0;
            if (isFinal) {
                b0 |= 128;
            }
            this.sink.writeByte(b0);
            int b1 = 0;
            if (this.isClient) {
                b1 = 0 | 128;
                this.random.nextBytes(this.maskKey);
            }
            if (byteCount <= 125) {
                this.sink.writeByte(b1 | ((int) byteCount));
            } else if (byteCount <= 65535) {
                this.sink.writeByte(b1 | 126);
                this.sink.writeShort((int) byteCount);
            } else {
                this.sink.writeByte(b1 | 127);
                this.sink.writeLong(byteCount);
            }
            if (this.isClient) {
                this.sink.write(this.maskKey);
                writeMaskedSynchronized(this.buffer, byteCount);
            } else {
                this.sink.write(this.buffer, byteCount);
            }
            this.sink.emit();
        }
    }

    private void writeMaskedSynchronized(BufferedSource source, long byteCount) throws IOException {
        if ($assertionsDisabled || Thread.holdsLock(this)) {
            long written = 0;
            while (written < byteCount) {
                int read = source.read(this.maskBuffer, 0, (int) Math.min(byteCount, (long) this.maskBuffer.length));
                if (read == -1) {
                    throw new AssertionError();
                }
                WebSocketProtocol.toggleMask(this.maskBuffer, (long) read, this.maskKey, written);
                this.sink.write(this.maskBuffer, 0, read);
                written += (long) read;
            }
            return;
        }
        throw new AssertionError();
    }
}
