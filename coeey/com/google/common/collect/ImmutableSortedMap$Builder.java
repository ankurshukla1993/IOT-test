package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Comparator;
import java.util.Map;
import java.util.Map.Entry;

public class ImmutableSortedMap$Builder<K, V> extends ImmutableMap$Builder<K, V> {
    private final Comparator<? super K> comparator;

    public ImmutableSortedMap$Builder(Comparator<? super K> comparator) {
        this.comparator = (Comparator) Preconditions.checkNotNull(comparator);
    }

    public ImmutableSortedMap$Builder<K, V> put(K key, V value) {
        super.put(key, value);
        return this;
    }

    public ImmutableSortedMap$Builder<K, V> put(Entry<? extends K, ? extends V> entry) {
        super.put(entry);
        return this;
    }

    public ImmutableSortedMap$Builder<K, V> putAll(Map<? extends K, ? extends V> map) {
        super.putAll(map);
        return this;
    }

    public ImmutableSortedMap<K, V> build() {
        return ImmutableSortedMap.fromEntries(this.comparator, false, this.size, this.entries);
    }
}
