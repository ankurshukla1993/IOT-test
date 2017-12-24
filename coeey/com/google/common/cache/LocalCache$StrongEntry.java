package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.ValueReference;
import javax.annotation.Nullable;

class LocalCache$StrongEntry<K, V> extends LocalCache$AbstractReferenceEntry<K, V> {
    final int hash;
    final K key;
    final ReferenceEntry<K, V> next;
    volatile ValueReference<K, V> valueReference = LocalCache.unset();

    LocalCache$StrongEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
        this.key = key;
        this.hash = hash;
        this.next = next;
    }

    public K getKey() {
        return this.key;
    }

    public ValueReference<K, V> getValueReference() {
        return this.valueReference;
    }

    public void setValueReference(ValueReference<K, V> valueReference) {
        this.valueReference = valueReference;
    }

    public int getHash() {
        return this.hash;
    }

    public ReferenceEntry<K, V> getNext() {
        return this.next;
    }
}
