package com.google.common.cache;

import com.google.common.base.Supplier;
import com.google.common.cache.AbstractCache.SimpleStatsCounter;
import com.google.common.cache.AbstractCache.StatsCounter;

class CacheBuilder$2 implements Supplier<StatsCounter> {
    CacheBuilder$2() {
    }

    public StatsCounter get() {
        return new SimpleStatsCounter();
    }
}
