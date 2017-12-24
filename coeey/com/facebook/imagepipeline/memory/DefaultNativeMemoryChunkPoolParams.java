package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public class DefaultNativeMemoryChunkPoolParams {
    private static final int LARGE_BUCKET_LENGTH = 2;
    private static final int SMALL_BUCKET_LENGTH = 5;

    public static PoolParams get() {
        SparseIntArray DEFAULT_BUCKETS = new SparseIntArray();
        DEFAULT_BUCKETS.put(1024, 5);
        DEFAULT_BUCKETS.put(2048, 5);
        DEFAULT_BUCKETS.put(4096, 5);
        DEFAULT_BUCKETS.put(8192, 5);
        DEFAULT_BUCKETS.put(16384, 5);
        DEFAULT_BUCKETS.put(32768, 5);
        DEFAULT_BUCKETS.put(65536, 5);
        DEFAULT_BUCKETS.put(131072, 5);
        DEFAULT_BUCKETS.put(262144, 2);
        DEFAULT_BUCKETS.put(524288, 2);
        DEFAULT_BUCKETS.put(1048576, 2);
        return new PoolParams(getMaxSizeSoftCap(), getMaxSizeHardCap(), DEFAULT_BUCKETS);
    }

    private static int getMaxSizeSoftCap() {
        int maxMemory = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (maxMemory < 16777216) {
            return 3145728;
        }
        if (maxMemory < 33554432) {
            return 6291456;
        }
        return 12582912;
    }

    private static int getMaxSizeHardCap() {
        int maxMemory = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (maxMemory < 16777216) {
            return maxMemory / 2;
        }
        return (maxMemory / 4) * 3;
    }
}
