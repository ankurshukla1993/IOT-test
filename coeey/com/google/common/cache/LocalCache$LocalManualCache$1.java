package com.google.common.cache;

import com.google.common.cache.LocalCache.LocalManualCache;
import java.util.concurrent.Callable;

class LocalCache$LocalManualCache$1 extends CacheLoader<Object, V> {
    final /* synthetic */ LocalManualCache this$0;
    final /* synthetic */ Callable val$valueLoader;

    LocalCache$LocalManualCache$1(LocalManualCache localManualCache, Callable callable) {
        this.this$0 = localManualCache;
        this.val$valueLoader = callable;
    }

    public V load(Object key) throws Exception {
        return this.val$valueLoader.call();
    }
}
