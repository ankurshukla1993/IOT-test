package com.facebook.cache.disk;

import com.facebook.cache.common.CacheEvent;
import com.facebook.cache.common.CacheEventListener.EvictionReason;
import com.facebook.cache.common.CacheKey;
import java.io.IOException;
import javax.annotation.Nullable;

public class SettableCacheEvent implements CacheEvent {
    private CacheKey mCacheKey;
    private long mCacheLimit;
    private long mCacheSize;
    private EvictionReason mEvictionReason;
    private IOException mException;
    private long mItemSize;
    private String mResourceId;

    @Nullable
    public CacheKey getCacheKey() {
        return this.mCacheKey;
    }

    public SettableCacheEvent setCacheKey(CacheKey cacheKey) {
        this.mCacheKey = cacheKey;
        return this;
    }

    @Nullable
    public String getResourceId() {
        return this.mResourceId;
    }

    public SettableCacheEvent setResourceId(String resourceId) {
        this.mResourceId = resourceId;
        return this;
    }

    public long getItemSize() {
        return this.mItemSize;
    }

    public SettableCacheEvent setItemSize(long itemSize) {
        this.mItemSize = itemSize;
        return this;
    }

    public long getCacheSize() {
        return this.mCacheSize;
    }

    public SettableCacheEvent setCacheSize(long cacheSize) {
        this.mCacheSize = cacheSize;
        return this;
    }

    public long getCacheLimit() {
        return this.mCacheLimit;
    }

    public SettableCacheEvent setCacheLimit(long cacheLimit) {
        this.mCacheLimit = cacheLimit;
        return this;
    }

    @Nullable
    public IOException getException() {
        return this.mException;
    }

    public SettableCacheEvent setException(IOException exception) {
        this.mException = exception;
        return this;
    }

    @Nullable
    public EvictionReason getEvictionReason() {
        return this.mEvictionReason;
    }

    public SettableCacheEvent setEvictionReason(EvictionReason evictionReason) {
        this.mEvictionReason = evictionReason;
        return this;
    }
}
