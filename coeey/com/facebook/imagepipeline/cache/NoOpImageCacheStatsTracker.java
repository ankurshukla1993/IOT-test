package com.facebook.imagepipeline.cache;

public class NoOpImageCacheStatsTracker implements ImageCacheStatsTracker {
    private static NoOpImageCacheStatsTracker sInstance = null;

    private NoOpImageCacheStatsTracker() {
    }

    public static synchronized NoOpImageCacheStatsTracker getInstance() {
        NoOpImageCacheStatsTracker noOpImageCacheStatsTracker;
        synchronized (NoOpImageCacheStatsTracker.class) {
            if (sInstance == null) {
                sInstance = new NoOpImageCacheStatsTracker();
            }
            noOpImageCacheStatsTracker = sInstance;
        }
        return noOpImageCacheStatsTracker;
    }

    public void onBitmapCachePut() {
    }

    public void onBitmapCacheHit() {
    }

    public void onBitmapCacheMiss() {
    }

    public void onMemoryCachePut() {
    }

    public void onMemoryCacheHit() {
    }

    public void onMemoryCacheMiss() {
    }

    public void onStagingAreaHit() {
    }

    public void onStagingAreaMiss() {
    }

    public void onDiskCacheHit() {
    }

    public void onDiskCacheMiss() {
    }

    public void onDiskCacheGetFail() {
    }

    public void registerBitmapMemoryCache(CountingMemoryCache<?, ?> countingMemoryCache) {
    }

    public void registerEncodedMemoryCache(CountingMemoryCache<?, ?> countingMemoryCache) {
    }
}
