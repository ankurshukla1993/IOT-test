package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.ValueReference;
import java.lang.ref.ReferenceQueue;

final class LocalCache$WeightedSoftValueReference<K, V> extends LocalCache$SoftValueReference<K, V> {
    final int weight;

    LocalCache$WeightedSoftValueReference(ReferenceQueue<V> queue, V referent, ReferenceEntry<K, V> entry, int weight) {
        super(queue, referent, entry);
        this.weight = weight;
    }

    public int getWeight() {
        return this.weight;
    }

    public ValueReference<K, V> copyFor(ReferenceQueue<V> queue, V value, ReferenceEntry<K, V> entry) {
        return new LocalCache$WeightedSoftValueReference(queue, value, entry, this.weight);
    }
}
