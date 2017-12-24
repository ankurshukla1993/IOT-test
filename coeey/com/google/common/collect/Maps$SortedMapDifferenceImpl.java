package com.google.common.collect;

import com.google.common.collect.MapDifference.ValueDifference;
import java.util.SortedMap;

class Maps$SortedMapDifferenceImpl<K, V> extends Maps$MapDifferenceImpl<K, V> implements SortedMapDifference<K, V> {
    Maps$SortedMapDifferenceImpl(SortedMap<K, V> onlyOnLeft, SortedMap<K, V> onlyOnRight, SortedMap<K, V> onBoth, SortedMap<K, ValueDifference<V>> differences) {
        super(onlyOnLeft, onlyOnRight, onBoth, differences);
    }

    public SortedMap<K, ValueDifference<V>> entriesDiffering() {
        return (SortedMap) super.entriesDiffering();
    }

    public SortedMap<K, V> entriesInCommon() {
        return (SortedMap) super.entriesInCommon();
    }

    public SortedMap<K, V> entriesOnlyOnLeft() {
        return (SortedMap) super.entriesOnlyOnLeft();
    }

    public SortedMap<K, V> entriesOnlyOnRight() {
        return (SortedMap) super.entriesOnlyOnRight();
    }
}
