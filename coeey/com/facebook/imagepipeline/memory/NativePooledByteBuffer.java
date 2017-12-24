package com.facebook.imagepipeline.memory;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.memory.PooledByteBuffer.ClosedException;
import javax.annotation.concurrent.GuardedBy;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativePooledByteBuffer implements PooledByteBuffer {
    @GuardedBy("this")
    @VisibleForTesting
    CloseableReference<NativeMemoryChunk> mBufRef;
    private final int mSize;

    public NativePooledByteBuffer(CloseableReference<NativeMemoryChunk> bufRef, int size) {
        Preconditions.checkNotNull(bufRef);
        boolean z = size >= 0 && size <= ((NativeMemoryChunk) bufRef.get()).getSize();
        Preconditions.checkArgument(z);
        this.mBufRef = bufRef.clone();
        this.mSize = size;
    }

    public synchronized int size() {
        ensureValid();
        return this.mSize;
    }

    public synchronized byte read(int offset) {
        byte read;
        boolean z = true;
        synchronized (this) {
            boolean z2;
            ensureValid();
            if (offset >= 0) {
                z2 = true;
            } else {
                z2 = false;
            }
            Preconditions.checkArgument(z2);
            if (offset >= this.mSize) {
                z = false;
            }
            Preconditions.checkArgument(z);
            read = ((NativeMemoryChunk) this.mBufRef.get()).read(offset);
        }
        return read;
    }

    public synchronized void read(int offset, byte[] buffer, int bufferOffset, int length) {
        ensureValid();
        Preconditions.checkArgument(offset + length <= this.mSize);
        ((NativeMemoryChunk) this.mBufRef.get()).read(offset, buffer, bufferOffset, length);
    }

    public synchronized long getNativePtr() {
        ensureValid();
        return ((NativeMemoryChunk) this.mBufRef.get()).getNativePtr();
    }

    public synchronized boolean isClosed() {
        return !CloseableReference.isValid(this.mBufRef);
    }

    public synchronized void close() {
        CloseableReference.closeSafely(this.mBufRef);
        this.mBufRef = null;
    }

    synchronized void ensureValid() {
        if (isClosed()) {
            throw new ClosedException();
        }
    }
}
