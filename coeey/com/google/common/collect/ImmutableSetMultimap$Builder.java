package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map.Entry;

public final class ImmutableSetMultimap$Builder<K, V> extends ImmutableMultimap$Builder<K, V> {
    public ImmutableSetMultimap$Builder() {
        this.builderMultimap = new ImmutableSetMultimap$BuilderMultimap();
    }

    public ImmutableSetMultimap$Builder<K, V> put(K key, V value) {
        this.builderMultimap.put(Preconditions.checkNotNull(key), Preconditions.checkNotNull(value));
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
        this.builderMultimap.put(Preconditions.checkNotNull(entry.getKey()), Preconditions.checkNotNull(entry.getValue()));
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> putAll(K key, Iterable<? extends V> values) {
        Collection<V> collection = this.builderMultimap.get(Preconditions.checkNotNull(key));
        for (V value : values) {
            collection.add(Preconditions.checkNotNull(value));
        }
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> putAll(K key, V... values) {
        return putAll((Object) key, Arrays.asList(values));
    }

    public ImmutableSetMultimap$Builder<K, V> putAll(Multimap<? extends K, ? extends V> multimap) {
        for (Entry<? extends K, ? extends Collection<? extends V>> entry : multimap.asMap().entrySet()) {
            putAll(entry.getKey(), (Iterable) entry.getValue());
        }
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> orderKeysBy(Comparator<? super K> keyComparator) {
        this.keyComparator = (Comparator) Preconditions.checkNotNull(keyComparator);
        return this;
    }

    public ImmutableSetMultimap$Builder<K, V> orderValuesBy(Comparator<? super V> valueComparator) {
        super.orderValuesBy(valueComparator);
        return this;
    }

    public ImmutableSetMultimap<K, V> build() {
        if (this.keyComparator != null) {
            Multimap<K, V> sortedCopy = new ImmutableSetMultimap$BuilderMultimap();
            List<Entry<K, Collection<V>>> entries = Lists.newArrayList(this.builderMultimap.asMap().entrySet());
            Collections.sort(entries, Ordering.from(this.keyComparator).onKeys());
            for (Entry<K, Collection<V>> entry : entries) {
                sortedCopy.putAll(entry.getKey(), (Iterable) entry.getValue());
            }
            this.builderMultimap = sortedCopy;
        }
        return ImmutableSetMultimap.access$000(this.builderMultimap, this.valueComparator);
    }
}
