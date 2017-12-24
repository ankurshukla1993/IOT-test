package com.facebook.cache.common;

import com.facebook.cache.common.CacheErrorLogger.CacheErrorCategory;
import javax.annotation.Nullable;

public class NoOpCacheErrorLogger implements CacheErrorLogger {
    private static NoOpCacheErrorLogger sInstance = null;

    private NoOpCacheErrorLogger() {
    }

    public static synchronized NoOpCacheErrorLogger getInstance() {
        NoOpCacheErrorLogger noOpCacheErrorLogger;
        synchronized (NoOpCacheErrorLogger.class) {
            if (sInstance == null) {
                sInstance = new NoOpCacheErrorLogger();
            }
            noOpCacheErrorLogger = sInstance;
        }
        return noOpCacheErrorLogger;
    }

    public void logError(CacheErrorCategory category, Class<?> cls, String message, @Nullable Throwable throwable) {
    }
}
