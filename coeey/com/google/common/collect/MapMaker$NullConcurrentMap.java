package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.io.Serializable;
import java.util.AbstractMap;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.ConcurrentMap;
import javax.annotation.Nullable;

class MapMaker$NullConcurrentMap<K, V> extends AbstractMap<K, V> implements ConcurrentMap<K, V>, Serializable {
    private static final long serialVersionUID = 0;
    private final MapMaker$RemovalCause removalCause;
    private final MapMaker$RemovalListener<K, V> removalListener;

    MapMaker$NullConcurrentMap(MapMaker mapMaker) {
        this.removalListener = mapMaker.getRemovalListener();
        this.removalCause = mapMaker.nullRemovalCause;
    }

    public boolean containsKey(@Nullable Object key) {
        return false;
    }

    public boolean containsValue(@Nullable Object value) {
        return false;
    }

    public V get(@Nullable Object key) {
        return null;
    }

    void notifyRemoval(K key, V value) {
        this.removalListener.onRemoval(new MapMaker$RemovalNotification(key, value, this.removalCause));
    }

    public V put(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        notifyRemoval(key, value);
        return null;
    }

    public V putIfAbsent(K key, V value) {
        return put(key, value);
    }

    public V remove(@Nullable Object key) {
        return null;
    }

    public boolean remove(@Nullable Object key, @Nullable Object value) {
        return false;
    }

    public V replace(K key, V value) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(value);
        return null;
    }

    public boolean replace(K key, @Nullable V v, V newValue) {
        Preconditions.checkNotNull(key);
        Preconditions.checkNotNull(newValue);
        return false;
    }

    public Set<Entry<K, V>> entrySet() {
        return Collections.emptySet();
    }
}
