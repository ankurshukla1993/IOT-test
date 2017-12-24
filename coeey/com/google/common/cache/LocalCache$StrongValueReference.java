package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.ValueReference;
import java.lang.ref.ReferenceQueue;

class LocalCache$StrongValueReference<K, V> implements ValueReference<K, V> {
    final V referent;

    LocalCache$StrongValueReference(V referent) {
        this.referent = referent;
    }

    public V get() {
        return this.referent;
    }

    public int getWeight() {
        return 1;
    }

    public ReferenceEntry<K, V> getEntry() {
        return null;
    }

    public ValueReference<K, V> copyFor(ReferenceQueue<V> referenceQueue, V v, ReferenceEntry<K, V> referenceEntry) {
        return this;
    }

    public boolean isLoading() {
        return false;
    }

    public boolean isActive() {
        return true;
    }

    public V waitForValue() {
        return get();
    }

    public void notifyNewValue(V v) {
    }
}
