package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import java.io.IOException;
import javax.annotation.concurrent.NotThreadSafe;

@NotThreadSafe
public class NativePooledByteBufferOutputStream extends PooledByteBufferOutputStream {
    private CloseableReference<NativeMemoryChunk> mBufRef;
    private int mCount;
    private final NativeMemoryChunkPool mPool;

    public static class InvalidStreamException extends RuntimeException {
        public InvalidStreamException() {
            super("OutputStream no longer valid");
        }
    }

    public NativePooledByteBufferOutputStream(NativeMemoryChunkPool pool) {
        this(pool, pool.getMinBufferSize());
    }

    public NativePooledByteBufferOutputStream(NativeMemoryChunkPool pool, int initialCapacity) {
        boolean z;
        if (initialCapacity > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.mPool = (NativeMemoryChunkPool) Preconditions.checkNotNull(pool);
        this.mCount = 0;
        this.mBufRef = CloseableReference.of(this.mPool.get(initialCapacity), this.mPool);
    }

    public NativePooledByteBuffer toByteBuffer() {
        ensureValid();
        return new NativePooledByteBuffer(this.mBufRef, this.mCount);
    }

    public int size() {
        return this.mCount;
    }

    public void write(int oneByte) throws IOException {
        write(new byte[]{(byte) oneByte});
    }

    public void write(byte[] buffer, int offset, int count) throws IOException {
        if (offset < 0 || count < 0 || offset + count > buffer.length) {
            throw new ArrayIndexOutOfBoundsException("length=" + buffer.length + "; regionStart=" + offset + "; regionLength=" + count);
        }
        ensureValid();
        realloc(this.mCount + count);
        ((NativeMemoryChunk) this.mBufRef.get()).write(this.mCount, buffer, offset, count);
        this.mCount += count;
    }

    public void close() {
        CloseableReference.closeSafely(this.mBufRef);
        this.mBufRef = null;
        this.mCount = -1;
        super.close();
    }

    @VisibleForTesting
    void realloc(int newLength) {
        ensureValid();
        if (newLength > ((NativeMemoryChunk) this.mBufRef.get()).getSize()) {
            NativeMemoryChunk newbuf = (NativeMemoryChunk) this.mPool.get(newLength);
            ((NativeMemoryChunk) this.mBufRef.get()).copy(0, newbuf, 0, this.mCount);
            this.mBufRef.close();
            this.mBufRef = CloseableReference.of(newbuf, this.mPool);
        }
    }

    private void ensureValid() {
        if (!CloseableReference.isValid(this.mBufRef)) {
            throw new InvalidStreamException();
        }
    }
}
