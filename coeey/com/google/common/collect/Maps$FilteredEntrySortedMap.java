package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Comparator;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.SortedSet;

class Maps$FilteredEntrySortedMap<K, V> extends Maps$FilteredEntryMap<K, V> implements SortedMap<K, V> {

    class SortedKeySet extends KeySet implements SortedSet<K> {
        SortedKeySet() {
            super();
        }

        public Comparator<? super K> comparator() {
            return Maps$FilteredEntrySortedMap.this.sortedMap().comparator();
        }

        public SortedSet<K> subSet(K fromElement, K toElement) {
            return (SortedSet) Maps$FilteredEntrySortedMap.this.subMap(fromElement, toElement).keySet();
        }

        public SortedSet<K> headSet(K toElement) {
            return (SortedSet) Maps$FilteredEntrySortedMap.this.headMap(toElement).keySet();
        }

        public SortedSet<K> tailSet(K fromElement) {
            return (SortedSet) Maps$FilteredEntrySortedMap.this.tailMap(fromElement).keySet();
        }

        public K first() {
            return Maps$FilteredEntrySortedMap.this.firstKey();
        }

        public K last() {
            return Maps$FilteredEntrySortedMap.this.lastKey();
        }
    }

    Maps$FilteredEntrySortedMap(SortedMap<K, V> unfiltered, Predicate<? super Entry<K, V>> entryPredicate) {
        super(unfiltered, entryPredicate);
    }

    SortedMap<K, V> sortedMap() {
        return (SortedMap) this.unfiltered;
    }

    public SortedSet<K> keySet() {
        return (SortedSet) super.keySet();
    }

    SortedSet<K> createKeySet() {
        return new SortedKeySet();
    }

    public Comparator<? super K> comparator() {
        return sortedMap().comparator();
    }

    public K firstKey() {
        return keySet().iterator().next();
    }

    public K lastKey() {
        SortedMap<K, V> headMap = sortedMap();
        while (true) {
            K key = headMap.lastKey();
            if (apply(key, this.unfiltered.get(key))) {
                return key;
            }
            headMap = sortedMap().headMap(key);
        }
    }

    public SortedMap<K, V> headMap(K toKey) {
        return new Maps$FilteredEntrySortedMap(sortedMap().headMap(toKey), this.predicate);
    }

    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return new Maps$FilteredEntrySortedMap(sortedMap().subMap(fromKey, toKey), this.predicate);
    }

    public SortedMap<K, V> tailMap(K fromKey) {
        return new Maps$FilteredEntrySortedMap(sortedMap().tailMap(fromKey), this.predicate);
    }
}
