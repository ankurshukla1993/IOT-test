package com.google.common.collect;

import java.util.Map.Entry;
import javax.annotation.Nullable;

abstract class DenseImmutableTable$ImmutableArrayMap<K, V> extends ImmutableMap<K, V> {
    private final int size;

    @Nullable
    abstract V getValue(int i);

    abstract ImmutableMap<K, Integer> keyToIndex();

    DenseImmutableTable$ImmutableArrayMap(int size) {
        this.size = size;
    }

    private boolean isFull() {
        return this.size == keyToIndex().size();
    }

    K getKey(int index) {
        return keyToIndex().keySet().asList().get(index);
    }

    ImmutableSet<K> createKeySet() {
        return isFull() ? keyToIndex().keySet() : super.createKeySet();
    }

    public int size() {
        return this.size;
    }

    public V get(@Nullable Object key) {
        Integer keyIndex = (Integer) keyToIndex().get(key);
        return keyIndex == null ? null : getValue(keyIndex.intValue());
    }

    ImmutableSet<Entry<K, V>> createEntrySet() {
        return new 1(this);
    }
}
