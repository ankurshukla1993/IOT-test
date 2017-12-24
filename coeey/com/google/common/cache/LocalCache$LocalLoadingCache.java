package com.google.common.cache;

import com.google.common.base.Preconditions;
import com.google.common.cache.LocalCache.LocalManualCache;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.UncheckedExecutionException;
import java.util.concurrent.ExecutionException;

class LocalCache$LocalLoadingCache<K, V> extends LocalManualCache<K, V> implements LoadingCache<K, V> {
    private static final long serialVersionUID = 1;

    LocalCache$LocalLoadingCache(CacheBuilder<? super K, ? super V> builder, CacheLoader<? super K, V> loader) {
        super(new LocalCache(builder, (CacheLoader) Preconditions.checkNotNull(loader)), null);
    }

    public V get(K key) throws ExecutionException {
        return this.localCache.getOrLoad(key);
    }

    public V getUnchecked(K key) {
        try {
            return get(key);
        } catch (ExecutionException e) {
            throw new UncheckedExecutionException(e.getCause());
        }
    }

    public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
        return this.localCache.getAll(keys);
    }

    public void refresh(K key) {
        this.localCache.refresh(key);
    }

    public final V apply(K key) {
        return getUnchecked(key);
    }

    Object writeReplace() {
        return new LocalCache$LoadingSerializationProxy(this.localCache);
    }
}
