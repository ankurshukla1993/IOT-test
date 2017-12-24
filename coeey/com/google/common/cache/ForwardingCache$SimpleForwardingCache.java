package com.google.common.cache;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;

@Beta
public abstract class ForwardingCache$SimpleForwardingCache<K, V> extends ForwardingCache<K, V> {
    private final Cache<K, V> delegate;

    protected ForwardingCache$SimpleForwardingCache(Cache<K, V> delegate) {
        this.delegate = (Cache) Preconditions.checkNotNull(delegate);
    }

    protected final Cache<K, V> delegate() {
        return this.delegate;
    }
}
