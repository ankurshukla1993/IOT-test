package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class PooledByteStreams {
    private static final int DEFAULT_TEMP_BUF_SIZE = 16384;
    private final ByteArrayPool mByteArrayPool;
    private final int mTempBufSize;

    public PooledByteStreams(ByteArrayPool byteArrayPool) {
        this(byteArrayPool, 16384);
    }

    @VisibleForTesting
    PooledByteStreams(ByteArrayPool byteArrayPool, int tempBufSize) {
        Preconditions.checkArgument(tempBufSize > 0);
        this.mTempBufSize = tempBufSize;
        this.mByteArrayPool = byteArrayPool;
    }

    public long copy(InputStream from, OutputStream to) throws IOException {
        long count = 0;
        byte[] tmp = (byte[]) this.mByteArrayPool.get(this.mTempBufSize);
        while (true) {
            int read = from.read(tmp, 0, this.mTempBufSize);
            if (read == -1) {
                break;
            }
            try {
                to.write(tmp, 0, read);
                count += (long) read;
            } finally {
                this.mByteArrayPool.release(tmp);
            }
        }
        return count;
    }

    public long copy(InputStream from, OutputStream to, long bytesToCopy) throws IOException {
        boolean z = false;
        if (bytesToCopy > 0) {
            z = true;
        }
        Preconditions.checkState(z);
        long copied = 0;
        byte[] tmp = (byte[]) this.mByteArrayPool.get(this.mTempBufSize);
        while (copied < bytesToCopy) {
            int read = from.read(tmp, 0, (int) Math.min((long) this.mTempBufSize, bytesToCopy - copied));
            if (read == -1) {
                break;
            }
            try {
                to.write(tmp, 0, read);
                copied += (long) read;
            } finally {
                this.mByteArrayPool.release(tmp);
            }
        }
        this.mByteArrayPool.release(tmp);
        return copied;
    }
}
