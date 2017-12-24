package com.facebook.imagepipeline.memory;

import android.util.SparseIntArray;

public class DefaultBitmapPoolParams {
    private static final SparseIntArray DEFAULT_BUCKETS = new SparseIntArray(0);
    private static final int MAX_SIZE_SOFT_CAP = 0;

    private DefaultBitmapPoolParams() {
    }

    private static int getMaxSizeHardCap() {
        int maxMemory = (int) Math.min(Runtime.getRuntime().maxMemory(), 2147483647L);
        if (maxMemory > 16777216) {
            return (maxMemory / 4) * 3;
        }
        return maxMemory / 2;
    }

    public static PoolParams get() {
        return new PoolParams(0, getMaxSizeHardCap(), DEFAULT_BUCKETS);
    }
}
