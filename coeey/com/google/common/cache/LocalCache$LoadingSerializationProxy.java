package com.google.common.cache;

import com.google.common.collect.ImmutableMap;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.concurrent.ExecutionException;

final class LocalCache$LoadingSerializationProxy<K, V> extends LocalCache$ManualSerializationProxy<K, V> implements LoadingCache<K, V>, Serializable {
    private static final long serialVersionUID = 1;
    transient LoadingCache<K, V> autoDelegate;

    LocalCache$LoadingSerializationProxy(LocalCache<K, V> cache) {
        super(cache);
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.autoDelegate = recreateCacheBuilder().build(this.loader);
    }

    public V get(K key) throws ExecutionException {
        return this.autoDelegate.get(key);
    }

    public V getUnchecked(K key) {
        return this.autoDelegate.getUnchecked(key);
    }

    public ImmutableMap<K, V> getAll(Iterable<? extends K> keys) throws ExecutionException {
        return this.autoDelegate.getAll(keys);
    }

    public final V apply(K key) {
        return this.autoDelegate.apply(key);
    }

    public void refresh(K key) {
        this.autoDelegate.refresh(key);
    }

    private Object readResolve() {
        return this.autoDelegate;
    }
}
