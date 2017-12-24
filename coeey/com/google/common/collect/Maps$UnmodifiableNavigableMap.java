package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.io.Serializable;
import java.util.Collections;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

@GwtIncompatible("NavigableMap")
class Maps$UnmodifiableNavigableMap<K, V> extends ForwardingSortedMap<K, V> implements NavigableMap<K, V>, Serializable {
    private final NavigableMap<K, V> delegate;
    private transient Maps$UnmodifiableNavigableMap<K, V> descendingMap;

    Maps$UnmodifiableNavigableMap(NavigableMap<K, V> delegate) {
        this.delegate = delegate;
    }

    Maps$UnmodifiableNavigableMap(NavigableMap<K, V> delegate, Maps$UnmodifiableNavigableMap<K, V> descendingMap) {
        this.delegate = delegate;
        this.descendingMap = descendingMap;
    }

    protected SortedMap<K, V> delegate() {
        return Collections.unmodifiableSortedMap(this.delegate);
    }

    public Entry<K, V> lowerEntry(K key) {
        return Maps.access$800(this.delegate.lowerEntry(key));
    }

    public K lowerKey(K key) {
        return this.delegate.lowerKey(key);
    }

    public Entry<K, V> floorEntry(K key) {
        return Maps.access$800(this.delegate.floorEntry(key));
    }

    public K floorKey(K key) {
        return this.delegate.floorKey(key);
    }

    public Entry<K, V> ceilingEntry(K key) {
        return Maps.access$800(this.delegate.ceilingEntry(key));
    }

    public K ceilingKey(K key) {
        return this.delegate.ceilingKey(key);
    }

    public Entry<K, V> higherEntry(K key) {
        return Maps.access$800(this.delegate.higherEntry(key));
    }

    public K higherKey(K key) {
        return this.delegate.higherKey(key);
    }

    public Entry<K, V> firstEntry() {
        return Maps.access$800(this.delegate.firstEntry());
    }

    public Entry<K, V> lastEntry() {
        return Maps.access$800(this.delegate.lastEntry());
    }

    public final Entry<K, V> pollFirstEntry() {
        throw new UnsupportedOperationException();
    }

    public final Entry<K, V> pollLastEntry() {
        throw new UnsupportedOperationException();
    }

    public NavigableMap<K, V> descendingMap() {
        NavigableMap<K, V> navigableMap = this.descendingMap;
        if (navigableMap != null) {
            return navigableMap;
        }
        NavigableMap maps$UnmodifiableNavigableMap = new Maps$UnmodifiableNavigableMap(this.delegate.descendingMap(), this);
        this.descendingMap = maps$UnmodifiableNavigableMap;
        return maps$UnmodifiableNavigableMap;
    }

    public Set<K> keySet() {
        return navigableKeySet();
    }

    public NavigableSet<K> navigableKeySet() {
        return Sets.unmodifiableNavigableSet(this.delegate.navigableKeySet());
    }

    public NavigableSet<K> descendingKeySet() {
        return Sets.unmodifiableNavigableSet(this.delegate.descendingKeySet());
    }

    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return subMap(fromKey, true, toKey, false);
    }

    public SortedMap<K, V> headMap(K toKey) {
        return headMap(toKey, false);
    }

    public SortedMap<K, V> tailMap(K fromKey) {
        return tailMap(fromKey, true);
    }

    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return Maps.unmodifiableNavigableMap(this.delegate.subMap(fromKey, fromInclusive, toKey, toInclusive));
    }

    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return Maps.unmodifiableNavigableMap(this.delegate.headMap(toKey, inclusive));
    }

    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return Maps.unmodifiableNavigableMap(this.delegate.tailMap(fromKey, inclusive));
    }
}
