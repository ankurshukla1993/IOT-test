package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.Map.Entry;
import java.util.Set;
import javax.annotation.Nullable;

final class Maps$FilteredEntryBiMap<K, V> extends Maps$FilteredEntryMap<K, V> implements BiMap<K, V> {
    private final BiMap<V, K> inverse;

    private static <K, V> Predicate<Entry<V, K>> inversePredicate(final Predicate<? super Entry<K, V>> forwardPredicate) {
        return new Predicate<Entry<V, K>>() {
            public boolean apply(Entry<V, K> input) {
                return forwardPredicate.apply(Maps.immutableEntry(input.getValue(), input.getKey()));
            }
        };
    }

    Maps$FilteredEntryBiMap(BiMap<K, V> delegate, Predicate<? super Entry<K, V>> predicate) {
        super(delegate, predicate);
        this.inverse = new Maps$FilteredEntryBiMap(delegate.inverse(), inversePredicate(predicate), this);
    }

    private Maps$FilteredEntryBiMap(BiMap<K, V> delegate, Predicate<? super Entry<K, V>> predicate, BiMap<V, K> inverse) {
        super(delegate, predicate);
        this.inverse = inverse;
    }

    BiMap<K, V> unfiltered() {
        return (BiMap) this.unfiltered;
    }

    public V forcePut(@Nullable K key, @Nullable V value) {
        Preconditions.checkArgument(apply(key, value));
        return unfiltered().forcePut(key, value);
    }

    public BiMap<V, K> inverse() {
        return this.inverse;
    }

    public Set<V> values() {
        return this.inverse.keySet();
    }
}
