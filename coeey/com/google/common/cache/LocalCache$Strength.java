package com.google.common.cache;

import com.google.common.base.Equivalence;
import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.Segment;
import com.google.common.cache.LocalCache.ValueReference;

enum LocalCache$Strength {
    STRONG {
        <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V value, int weight) {
            return weight == 1 ? new LocalCache$StrongValueReference(value) : new LocalCache$WeightedStrongValueReference(value, weight);
        }

        Equivalence<Object> defaultEquivalence() {
            return Equivalence.equals();
        }
    },
    SOFT {
        <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> entry, V value, int weight) {
            return weight == 1 ? new LocalCache$SoftValueReference(segment.valueReferenceQueue, value, entry) : new LocalCache$WeightedSoftValueReference(segment.valueReferenceQueue, value, entry, weight);
        }

        Equivalence<Object> defaultEquivalence() {
            return Equivalence.identity();
        }
    },
    WEAK {
        <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> entry, V value, int weight) {
            return weight == 1 ? new LocalCache$WeakValueReference(segment.valueReferenceQueue, value, entry) : new LocalCache$WeightedWeakValueReference(segment.valueReferenceQueue, value, entry, weight);
        }

        Equivalence<Object> defaultEquivalence() {
            return Equivalence.identity();
        }
    };

    abstract Equivalence<Object> defaultEquivalence();

    abstract <K, V> ValueReference<K, V> referenceValue(Segment<K, V> segment, ReferenceEntry<K, V> referenceEntry, V v, int i);
}
