package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;
import com.facebook.common.internal.Preconditions;
import javax.annotation.Nullable;

public class PoolParams {
    public static final int IGNORE_THREADS = -1;
    public final SparseIntArray bucketSizes;
    public final int maxBucketSize;
    public final int maxNumThreads;
    public final int maxSizeHardCap;
    public final int maxSizeSoftCap;
    public final int minBucketSize;

    public PoolParams(int maxSize, @Nullable SparseIntArray bucketSizes) {
        this(maxSize, maxSize, bucketSizes, 0, Integer.MAX_VALUE, -1);
    }

    public PoolParams(int maxSizeSoftCap, int maxSizeHardCap, @Nullable SparseIntArray bucketSizes) {
        this(maxSizeSoftCap, maxSizeHardCap, bucketSizes, 0, Integer.MAX_VALUE, -1);
    }

    public PoolParams(int maxSizeSoftCap, int maxSizeHardCap, @Nullable SparseIntArray bucketSizes, int minBucketSize, int maxBucketSize, int maxNumThreads) {
        boolean z = maxSizeSoftCap >= 0 && maxSizeHardCap >= maxSizeSoftCap;
        Preconditions.checkState(z);
        this.maxSizeSoftCap = maxSizeSoftCap;
        this.maxSizeHardCap = maxSizeHardCap;
        this.bucketSizes = bucketSizes;
        this.minBucketSize = minBucketSize;
        this.maxBucketSize = maxBucketSize;
        this.maxNumThreads = maxNumThreads;
    }
}
