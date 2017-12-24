package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.logging.FLog;
import com.facebook.common.references.ResourceReleaser;
import java.io.IOException;
import java.io.InputStream;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class PooledByteArrayBufferedInputStream extends InputStream {
    private static final String TAG = "PooledByteInputStream";
    private int mBufferOffset = 0;
    private int mBufferedSize = 0;
    private final byte[] mByteArray;
    private boolean mClosed = false;
    private final InputStream mInputStream;
    private final ResourceReleaser<byte[]> mResourceReleaser;

    public PooledByteArrayBufferedInputStream(InputStream inputStream, byte[] byteArray, ResourceReleaser<byte[]> resourceReleaser) {
        this.mInputStream = (InputStream) Preconditions.checkNotNull(inputStream);
        this.mByteArray = (byte[]) Preconditions.checkNotNull(byteArray);
        this.mResourceReleaser = (ResourceReleaser) Preconditions.checkNotNull(resourceReleaser);
    }

    public int read() throws IOException {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        ensureNotClosed();
        if (!ensureDataInBuffer()) {
            return -1;
        }
        byte[] bArr = this.mByteArray;
        int i = this.mBufferOffset;
        this.mBufferOffset = i + 1;
        return bArr[i] & 255;
    }

    public int read(byte[] buffer, int offset, int length) throws IOException {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        ensureNotClosed();
        if (!ensureDataInBuffer()) {
            return -1;
        }
        int bytesToRead = Math.min(this.mBufferedSize - this.mBufferOffset, length);
        System.arraycopy(this.mByteArray, this.mBufferOffset, buffer, offset, bytesToRead);
        this.mBufferOffset += bytesToRead;
        return bytesToRead;
    }

    public int available() throws IOException {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        ensureNotClosed();
        return (this.mBufferedSize - this.mBufferOffset) + this.mInputStream.available();
    }

    public void close() throws IOException {
        if (!this.mClosed) {
            this.mClosed = true;
            this.mResourceReleaser.release(this.mByteArray);
            super.close();
        }
    }

    public long skip(long byteCount) throws IOException {
        Preconditions.checkState(this.mBufferOffset <= this.mBufferedSize);
        ensureNotClosed();
        int bytesLeftInBuffer = this.mBufferedSize - this.mBufferOffset;
        if (((long) bytesLeftInBuffer) >= byteCount) {
            this.mBufferOffset = (int) (((long) this.mBufferOffset) + byteCount);
            return byteCount;
        }
        this.mBufferOffset = this.mBufferedSize;
        return ((long) bytesLeftInBuffer) + this.mInputStream.skip(byteCount - ((long) bytesLeftInBuffer));
    }

    private boolean ensureDataInBuffer() throws IOException {
        if (this.mBufferOffset < this.mBufferedSize) {
            return true;
        }
        int readData = this.mInputStream.read(this.mByteArray);
        if (readData <= 0) {
            return false;
        }
        this.mBufferedSize = readData;
        this.mBufferOffset = 0;
        return true;
    }

    private void ensureNotClosed() throws IOException {
        if (this.mClosed) {
            throw new IOException("stream already closed");
        }
    }

    protected void finalize() throws Throwable {
        if (!this.mClosed) {
            FLog.m111e(TAG, "Finalized without closing");
            close();
        }
        super.finalize();
    }
}
