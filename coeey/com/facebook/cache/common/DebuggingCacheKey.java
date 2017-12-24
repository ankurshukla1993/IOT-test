package com.facebook.cache.common;

import javax.annotation.Nullable;

public class DebuggingCacheKey extends SimpleCacheKey {
    private final Object mCallerContext;

    public DebuggingCacheKey(String key, @Nullable Object callerContext) {
        super(key);
        this.mCallerContext = callerContext;
    }

    @Nullable
    public Object getCallerContext() {
        return this.mCallerContext;
    }
}
