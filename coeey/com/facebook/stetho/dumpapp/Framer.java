package com.facebook.stetho.dumpapp;

import com.facebook.stetho.common.LogUtil;
import java.io.BufferedOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.nio.charset.Charset;
import javax.annotation.Nullable;

class Framer {
    public static final byte ENTER_FRAME_PREFIX = (byte) 33;
    public static final byte EXIT_FRAME_PREFIX = (byte) 120;
    public static final byte STDERR_FRAME_PREFIX = (byte) 50;
    public static final byte STDIN_FRAME_PREFIX = (byte) 45;
    public static final byte STDIN_REQUEST_FRAME_PREFIX = (byte) 95;
    public static final byte STDOUT_FRAME_PREFIX = (byte) 49;
    private static final String TAG = "FramingSocket";
    private final DataInputStream mInput;
    private final DataOutputStream mMultiplexedOutputStream;
    private final PrintStream mStderr = new PrintStream(new FramingOutputStream(STDERR_FRAME_PREFIX));
    private final InputStream mStdin = new FramingInputStream();
    private final PrintStream mStdout = new PrintStream(new BufferedOutputStream(new FramingOutputStream(STDOUT_FRAME_PREFIX)));

    private static class ClosedHelper {
        private volatile boolean mClosed;

        private ClosedHelper() {
        }

        public void throwIfClosed() throws IOException {
            if (this.mClosed) {
                throw new IOException("Stream is closed");
            }
        }

        public void close() {
            this.mClosed = true;
        }
    }

    private class FramingInputStream extends InputStream {
        private final ClosedHelper mClosedHelper;

        private FramingInputStream() {
            this.mClosedHelper = new ClosedHelper();
        }

        public int read() throws IOException {
            byte[] buf = new byte[1];
            if (read(buf) == 0) {
                return -1;
            }
            return buf[0];
        }

        public int read(byte[] buffer) throws IOException {
            return read(buffer, 0, buffer.length);
        }

        public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
            int length;
            this.mClosedHelper.throwIfClosed();
            synchronized (Framer.this) {
                Framer.this.writeIntFrame(Framer.STDIN_REQUEST_FRAME_PREFIX, byteCount);
                byte b = Framer.this.readFrameType();
                if (b != Framer.STDIN_FRAME_PREFIX) {
                    throw new UnexpectedFrameException(Framer.STDIN_FRAME_PREFIX, b);
                }
                length = Framer.this.readInt();
                if (length > 0) {
                    if (length > byteCount) {
                        throw new DumpappFramingException("Expected at most " + byteCount + " bytes, got: " + length);
                    }
                    Framer.this.mInput.readFully(buffer, byteOffset, length);
                }
            }
            return length;
        }

        public long skip(long byteCount) throws IOException {
            long skipped = 0;
            byte[] buf = new byte[((int) Math.min(byteCount, 2048))];
            synchronized (Framer.this) {
                while (skipped < byteCount) {
                    int n = read(buf);
                    if (n < 0) {
                        break;
                    }
                    skipped += (long) n;
                }
            }
            return skipped;
        }

        public void close() throws IOException {
            this.mClosedHelper.close();
        }
    }

    private class FramingOutputStream extends OutputStream {
        private final ClosedHelper mClosedHelper = new ClosedHelper();
        private final byte mPrefix;

        public FramingOutputStream(byte prefix) {
            this.mPrefix = prefix;
        }

        public void write(byte[] buffer, int offset, int length) throws IOException {
            this.mClosedHelper.throwIfClosed();
            if (length > 0) {
                try {
                    synchronized (Framer.this) {
                        Framer.this.writeIntFrame(this.mPrefix, length);
                        Framer.this.writeBlob(buffer, offset, length);
                        Framer.this.mMultiplexedOutputStream.flush();
                    }
                } catch (Throwable e) {
                    throw new DumpappOutputBrokenException(e);
                }
            }
        }

        public void write(int oneByte) throws IOException {
            byte[] buffer = new byte[]{(byte) oneByte};
            write(buffer, 0, buffer.length);
        }

        public void write(byte[] buffer) throws IOException {
            write(buffer, 0, buffer.length);
        }

        public void close() throws IOException {
            this.mClosedHelper.close();
        }
    }

    public Framer(InputStream input, OutputStream output) throws IOException {
        this.mInput = new DataInputStream(input);
        this.mMultiplexedOutputStream = new DataOutputStream(output);
    }

    public InputStream getStdin() {
        return this.mStdin;
    }

    public PrintStream getStdout() {
        return this.mStdout;
    }

    public PrintStream getStderr() {
        return this.mStderr;
    }

    public byte readFrameType() throws IOException {
        return this.mInput.readByte();
    }

    public int readInt() throws IOException {
        return this.mInput.readInt();
    }

    public String readString() throws IOException {
        byte[] buf = new byte[this.mInput.readUnsignedShort()];
        this.mInput.readFully(buf);
        return new String(buf, Charset.forName("UTF-8"));
    }

    public void writeExitCode(int exitCode) throws IOException {
        this.mStdout.flush();
        this.mStderr.flush();
        writeIntFrame(EXIT_FRAME_PREFIX, exitCode);
    }

    public void writeIntFrame(byte type, int intParameter) throws IOException {
        this.mMultiplexedOutputStream.write(type);
        this.mMultiplexedOutputStream.writeInt(intParameter);
    }

    public void writeBlob(byte[] data, int offset, int count) throws IOException {
        this.mMultiplexedOutputStream.write(data, offset, count);
    }

    private static <T extends Throwable> T handleSuppression(@Nullable T previous, T current) {
        if (previous == null) {
            return current;
        }
        LogUtil.m194i(TAG, current, "Suppressed while handling " + previous);
        return previous;
    }
}
