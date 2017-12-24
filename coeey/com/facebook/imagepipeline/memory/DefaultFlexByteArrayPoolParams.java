package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public class DefaultFlexByteArrayPoolParams {
    public static final int DEFAULT_MAX_BYTE_ARRAY_SIZE = 4194304;
    public static final int DEFAULT_MAX_NUM_THREADS = Runtime.getRuntime().availableProcessors();
    private static final int DEFAULT_MIN_BYTE_ARRAY_SIZE = 131072;

    private DefaultFlexByteArrayPoolParams() {
    }

    public static SparseIntArray generateBuckets(int min, int max, int numThreads) {
        SparseIntArray buckets = new SparseIntArray();
        for (int i = min; i <= max; i *= 2) {
            buckets.put(i, numThreads);
        }
        return buckets;
    }

    public static PoolParams get() {
        return new PoolParams(4194304, DEFAULT_MAX_NUM_THREADS * 4194304, generateBuckets(131072, 4194304, DEFAULT_MAX_NUM_THREADS), 131072, 4194304, DEFAULT_MAX_NUM_THREADS);
    }
}
