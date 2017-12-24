package com.facebook.imagepipeline.memory;

import android.util.Log;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.nativecode.ImagePipelineNativeLoader;
import java.io.Closeable;

@DoNotStrip
public class NativeMemoryChunk implements Closeable {
    private static final String TAG = "NativeMemoryChunk";
    private boolean mClosed;
    private final long mNativePtr;
    private final int mSize;

    @DoNotStrip
    private static native long nativeAllocate(int i);

    @DoNotStrip
    private static native void nativeCopyFromByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    private static native void nativeCopyToByteArray(long j, byte[] bArr, int i, int i2);

    @DoNotStrip
    private static native void nativeFree(long j);

    @DoNotStrip
    private static native void nativeMemcpy(long j, long j2, int i);

    @DoNotStrip
    private static native byte nativeReadByte(long j);

    static {
        ImagePipelineNativeLoader.load();
    }

    public NativeMemoryChunk(int size) {
        boolean z;
        if (size > 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        this.mSize = size;
        this.mNativePtr = nativeAllocate(this.mSize);
        this.mClosed = false;
    }

    @VisibleForTesting
    public NativeMemoryChunk() {
        this.mSize = 0;
        this.mNativePtr = 0;
        this.mClosed = true;
    }

    public synchronized void close() {
        if (!this.mClosed) {
            this.mClosed = true;
            nativeFree(this.mNativePtr);
        }
    }

    public synchronized boolean isClosed() {
        return this.mClosed;
    }

    public int getSize() {
        return this.mSize;
    }

    public synchronized int write(int nativeMemoryOffset, byte[] byteArray, int byteArrayOffset, int count) {
        int actualCount;
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkState(!isClosed());
        actualCount = adjustByteCount(nativeMemoryOffset, count);
        checkBounds(nativeMemoryOffset, byteArray.length, byteArrayOffset, actualCount);
        nativeCopyFromByteArray(this.mNativePtr + ((long) nativeMemoryOffset), byteArray, byteArrayOffset, actualCount);
        return actualCount;
    }

    public synchronized int read(int nativeMemoryOffset, byte[] byteArray, int byteArrayOffset, int count) {
        int actualCount;
        Preconditions.checkNotNull(byteArray);
        Preconditions.checkState(!isClosed());
        actualCount = adjustByteCount(nativeMemoryOffset, count);
        checkBounds(nativeMemoryOffset, byteArray.length, byteArrayOffset, actualCount);
        nativeCopyToByteArray(this.mNativePtr + ((long) nativeMemoryOffset), byteArray, byteArrayOffset, actualCount);
        return actualCount;
    }

    public synchronized byte read(int offset) {
        byte nativeReadByte;
        boolean z = true;
        synchronized (this) {
            boolean z2;
            if (isClosed()) {
                z2 = false;
            } else {
                z2 = true;
            }
            Preconditions.checkState(z2);
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
            nativeReadByte = nativeReadByte(this.mNativePtr + ((long) offset));
        }
        return nativeReadByte;
    }

    public void copy(int offset, NativeMemoryChunk other, int otherOffset, int count) {
        Preconditions.checkNotNull(other);
        if (other.mNativePtr == this.mNativePtr) {
            Log.w(TAG, "Copying from NativeMemoryChunk " + Integer.toHexString(System.identityHashCode(this)) + " to NativeMemoryChunk " + Integer.toHexString(System.identityHashCode(other)) + " which share the same address " + Long.toHexString(this.mNativePtr));
            Preconditions.checkArgument(false);
        }
        if (other.mNativePtr < this.mNativePtr) {
            synchronized (other) {
                synchronized (this) {
                    doCopy(offset, other, otherOffset, count);
                }
            }
            return;
        }
        synchronized (this) {
            synchronized (other) {
                doCopy(offset, other, otherOffset, count);
            }
        }
    }

    public long getNativePtr() {
        return this.mNativePtr;
    }

    private void doCopy(int offset, NativeMemoryChunk other, int otherOffset, int count) {
        boolean z;
        boolean z2 = true;
        if (isClosed()) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkState(z);
        if (other.isClosed()) {
            z2 = false;
        }
        Preconditions.checkState(z2);
        checkBounds(offset, other.mSize, otherOffset, count);
        nativeMemcpy(other.mNativePtr + ((long) otherOffset), this.mNativePtr + ((long) offset), count);
    }

    protected void finalize() throws Throwable {
        if (!isClosed()) {
            Log.w(TAG, "finalize: Chunk " + Integer.toHexString(System.identityHashCode(this)) + " still active. Underlying address = " + Long.toHexString(this.mNativePtr));
            try {
                close();
            } finally {
                super.finalize();
            }
        }
    }

    private int adjustByteCount(int offset, int count) {
        return Math.min(Math.max(0, this.mSize - offset), count);
    }

    private void checkBounds(int myOffset, int otherLength, int otherOffset, int count) {
        boolean z;
        boolean z2 = true;
        Preconditions.checkArgument(count >= 0);
        if (myOffset >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (otherOffset >= 0) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (myOffset + count <= this.mSize) {
            z = true;
        } else {
            z = false;
        }
        Preconditions.checkArgument(z);
        if (otherOffset + count > otherLength) {
            z2 = false;
        }
        Preconditions.checkArgument(z2);
    }
}
