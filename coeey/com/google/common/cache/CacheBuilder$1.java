package com.google.common.cache;

import com.google.common.cache.AbstractCache.StatsCounter;

class CacheBuilder$1 implements StatsCounter {
    CacheBuilder$1() {
    }

    public void recordHits(int count) {
    }

    public void recordMisses(int count) {
    }

    public void recordLoadSuccess(long loadTime) {
    }

    public void recordLoadException(long loadTime) {
    }

    public void recordEviction() {
    }

    public CacheStats snapshot() {
        return CacheBuilder.EMPTY_STATS;
    }
}
