package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Comparator;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;

class Maps$SortedAsMapView<K, V> extends Maps$AsMapView<K, V> implements SortedMap<K, V> {
    Maps$SortedAsMapView(SortedSet<K> set, Function<? super K, V> function) {
        super(set, function);
    }

    SortedSet<K> backingSet() {
        return (SortedSet) super.backingSet();
    }

    public Comparator<? super K> comparator() {
        return backingSet().comparator();
    }

    public Set<K> keySet() {
        return Maps.access$300(backingSet());
    }

    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return Maps.asMap(backingSet().subSet(fromKey, toKey), this.function);
    }

    public SortedMap<K, V> headMap(K toKey) {
        return Maps.asMap(backingSet().headSet(toKey), this.function);
    }

    public SortedMap<K, V> tailMap(K fromKey) {
        return Maps.asMap(backingSet().tailSet(fromKey), this.function);
    }

    public K firstKey() {
        return backingSet().first();
    }

    public K lastKey() {
        return backingSet().last();
    }
}
