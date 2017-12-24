package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.ValueReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

class LocalCache$WeakValueReference<K, V> extends WeakReference<V> implements ValueReference<K, V> {
    final ReferenceEntry<K, V> entry;

    LocalCache$WeakValueReference(ReferenceQueue<V> queue, V referent, ReferenceEntry<K, V> entry) {
        super(referent, queue);
        this.entry = entry;
    }

    public int getWeight() {
        return 1;
    }

    public ReferenceEntry<K, V> getEntry() {
        return this.entry;
    }

    public void notifyNewValue(V v) {
    }

    public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry) {
        return new LocalCache$WeakValueReference(queue, value, entry);
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
}
