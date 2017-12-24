package com.google.common.collect;

import com.google.common.annotations.GwtIncompatible;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

@GwtIncompatible("NavigableMap")
abstract class Maps$DescendingMap<K, V> extends ForwardingMap<K, V> implements NavigableMap<K, V> {
    private transient Comparator<? super K> comparator;
    private transient Set<Entry<K, V>> entrySet;
    private transient NavigableSet<K> navigableKeySet;

    class C16741 extends Maps$EntrySet<K, V> {
        C16741() {
        }

        Map<K, V> map() {
            return Maps$DescendingMap.this;
        }

        public Iterator<Entry<K, V>> iterator() {
            return Maps$DescendingMap.this.entryIterator();
        }
    }

    abstract Iterator<Entry<K, V>> entryIterator();

    abstract NavigableMap<K, V> forward();

    Maps$DescendingMap() {
    }

    protected final Map<K, V> delegate() {
        return forward();
    }

    public Comparator<? super K> comparator() {
        Comparator<? super K> result = this.comparator;
        if (result != null) {
            return result;
        }
        Comparator<? super K> forwardCmp = forward().comparator();
        if (forwardCmp == null) {
            forwardCmp = Ordering.natural();
        }
        result = reverse(forwardCmp);
        this.comparator = result;
        return result;
    }

    private static <T> Ordering<T> reverse(Comparator<T> forward) {
        return Ordering.from(forward).reverse();
    }

    public K firstKey() {
        return forward().lastKey();
    }

    public K lastKey() {
        return forward().firstKey();
    }

    public Entry<K, V> lowerEntry(K key) {
        return forward().higherEntry(key);
    }

    public K lowerKey(K key) {
        return forward().higherKey(key);
    }

    public Entry<K, V> floorEntry(K key) {
        return forward().ceilingEntry(key);
    }

    public K floorKey(K key) {
        return forward().ceilingKey(key);
    }

    public Entry<K, V> ceilingEntry(K key) {
        return forward().floorEntry(key);
    }

    public K ceilingKey(K key) {
        return forward().floorKey(key);
    }

    public Entry<K, V> higherEntry(K key) {
        return forward().lowerEntry(key);
    }

    public K higherKey(K key) {
        return forward().lowerKey(key);
    }

    public Entry<K, V> firstEntry() {
        return forward().lastEntry();
    }

    public Entry<K, V> lastEntry() {
        return forward().firstEntry();
    }

    public Entry<K, V> pollFirstEntry() {
        return forward().pollLastEntry();
    }

    public Entry<K, V> pollLastEntry() {
        return forward().pollFirstEntry();
    }

    public NavigableMap<K, V> descendingMap() {
        return forward();
    }

    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> set = this.entrySet;
        if (set != null) {
            return set;
        }
        set = createEntrySet();
        this.entrySet = set;
        return set;
    }

    Set<Entry<K, V>> createEntrySet() {
        return new C16741();
    }

    public Set<K> keySet() {
        return navigableKeySet();
    }

    public NavigableSet<K> navigableKeySet() {
        NavigableSet<K> navigableSet = this.navigableKeySet;
        if (navigableSet != null) {
            return navigableSet;
        }
        navigableSet = new Maps$NavigableKeySet(this);
        this.navigableKeySet = navigableSet;
        return navigableSet;
    }

    public NavigableSet<K> descendingKeySet() {
        return forward().navigableKeySet();
    }

    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return forward().subMap(toKey, toInclusive, fromKey, fromInclusive).descendingMap();
    }

    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return forward().tailMap(toKey, inclusive).descendingMap();
    }

    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return forward().headMap(fromKey, inclusive).descendingMap();
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

    public Collection<V> values() {
        return new Maps$Values(this);
    }

    public String toString() {
        return standardToString();
    }
}
