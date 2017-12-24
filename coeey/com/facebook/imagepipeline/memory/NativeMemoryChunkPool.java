package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.imagepipeline.memory.BasePool.InvalidSizeException;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class NativeMemoryChunkPool extends BasePool<NativeMemoryChunk> {
    private final int[] mBucketSizes;

    public NativeMemoryChunkPool(MemoryTrimmableRegistry memoryTrimmableRegistry, PoolParams poolParams, PoolStatsTracker nativeMemoryChunkPoolStatsTracker) {
        super(memoryTrimmableRegistry, poolParams, nativeMemoryChunkPoolStatsTracker);
        SparseIntArray bucketSizes = poolParams.bucketSizes;
        this.mBucketSizes = new int[bucketSizes.size()];
        for (int i = 0; i < this.mBucketSizes.length; i++) {
            this.mBucketSizes[i] = bucketSizes.keyAt(i);
        }
        initialize();
    }

    public int getMinBufferSize() {
        return this.mBucketSizes[0];
    }

    protected NativeMemoryChunk alloc(int bucketedSize) {
        return new NativeMemoryChunk(bucketedSize);
    }

    protected void free(NativeMemoryChunk value) {
        Preconditions.checkNotNull(value);
        value.close();
    }

    protected int getSizeInBytes(int bucketedSize) {
        return bucketedSize;
    }

    protected int getBucketedSize(int requestSize) {
        int intRequestSize = requestSize;
        if (intRequestSize <= 0) {
            throw new InvalidSizeException(Integer.valueOf(requestSize));
        }
        for (int bucketedSize : this.mBucketSizes) {
            if (bucketedSize >= intRequestSize) {
                return bucketedSize;
            }
        }
        return requestSize;
    }

    protected int getBucketedSizeForValue(NativeMemoryChunk value) {
        Preconditions.checkNotNull(value);
        return value.getSize();
    }

    protected boolean isReusable(NativeMemoryChunk value) {
        Preconditions.checkNotNull(value);
        return !value.isClosed();
    }
}
