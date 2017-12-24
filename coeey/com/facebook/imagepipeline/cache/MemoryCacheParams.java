package com.facebook.imagepipeline.cache;

public class MemoryCacheParams {
    public final int maxCacheEntries;
    public final int maxCacheEntrySize;
    public final int maxCacheSize;
    public final int maxEvictionQueueEntries;
    public final int maxEvictionQueueSize;

    public MemoryCacheParams(int maxCacheSize, int maxCacheEntries, int maxEvictionQueueSize, int maxEvictionQueueEntries, int maxCacheEntrySize) {
        this.maxCacheSize = maxCacheSize;
        this.maxCacheEntries = maxCacheEntries;
        this.maxEvictionQueueSize = maxEvictionQueueSize;
        this.maxEvictionQueueEntries = maxEvictionQueueEntries;
        this.maxCacheEntrySize = maxCacheEntrySize;
    }
}
