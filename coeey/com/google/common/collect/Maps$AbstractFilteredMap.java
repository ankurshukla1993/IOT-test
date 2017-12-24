package com.google.common.collect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import javax.annotation.Nullable;

abstract class Maps$AbstractFilteredMap<K, V> extends Maps$ImprovedAbstractMap<K, V> {
    final Predicate<? super Entry<K, V>> predicate;
    final Map<K, V> unfiltered;

    Maps$AbstractFilteredMap(Map<K, V> unfiltered, Predicate<? super Entry<K, V>> predicate) {
        this.unfiltered = unfiltered;
        this.predicate = predicate;
    }

    boolean apply(@Nullable Object key, @Nullable V value) {
        return this.predicate.apply(Maps.immutableEntry(key, value));
    }

    public V put(K key, V value) {
        Preconditions.checkArgument(apply(key, value));
        return this.unfiltered.put(key, value);
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        for (Entry<? extends K, ? extends V> entry : map.entrySet()) {
            Preconditions.checkArgument(apply(entry.getKey(), entry.getValue()));
        }
        this.unfiltered.putAll(map);
    }

    public boolean containsKey(Object key) {
        return this.unfiltered.containsKey(key) && apply(key, this.unfiltered.get(key));
    }

    public V get(Object key) {
        V value = this.unfiltered.get(key);
        return (value == null || !apply(key, value)) ? null : value;
    }

    public boolean isEmpty() {
        return entrySet().isEmpty();
    }

    public V remove(Object key) {
        return containsKey(key) ? this.unfiltered.remove(key) : null;
    }

    Collection<V> createValues() {
        return new Maps$FilteredMapValues(this, this.unfiltered, this.predicate);
    }
}
