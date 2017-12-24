package com.facebook.common.streams;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

public class LimitedInputStream extends FilterInputStream {
    private int mBytesToRead;
    private int mBytesToReadWhenMarked;

    public LimitedInputStream(InputStream inputStream, int limit) {
        super(inputStream);
        if (inputStream == null) {
            throw new NullPointerException();
        } else if (limit < 0) {
            throw new IllegalArgumentException("limit must be >= 0");
        } else {
            this.mBytesToRead = limit;
            this.mBytesToReadWhenMarked = -1;
        }
    }

    public int read() throws IOException {
        if (this.mBytesToRead == 0) {
            return -1;
        }
        int readByte = this.in.read();
        if (readByte == -1) {
            return readByte;
        }
        this.mBytesToRead--;
        return readByte;
    }

    public int read(byte[] buffer, int byteOffset, int byteCount) throws IOException {
        if (this.mBytesToRead == 0) {
            return -1;
        }
        int bytesRead = this.in.read(buffer, byteOffset, Math.min(byteCount, this.mBytesToRead));
        if (bytesRead <= 0) {
            return bytesRead;
        }
        this.mBytesToRead -= bytesRead;
        return bytesRead;
    }

    public long skip(long byteCount) throws IOException {
        long bytesSkipped = this.in.skip(Math.min(byteCount, (long) this.mBytesToRead));
        this.mBytesToRead = (int) (((long) this.mBytesToRead) - bytesSkipped);
        return bytesSkipped;
    }

    public int available() throws IOException {
        return Math.min(this.in.available(), this.mBytesToRead);
    }

    public void mark(int readLimit) {
        if (this.in.markSupported()) {
            this.in.mark(readLimit);
            this.mBytesToReadWhenMarked = this.mBytesToRead;
        }
    }

    public void reset() throws IOException {
        if (!this.in.markSupported()) {
            throw new IOException("mark is not supported");
        } else if (this.mBytesToReadWhenMarked == -1) {
            throw new IOException("mark not set");
        } else {
            this.in.reset();
            this.mBytesToRead = this.mBytesToReadWhenMarked;
        }
    }
}
