package com.google.common.collect;

final class TreeRangeMap$RangeMapEntry<K extends Comparable, V> extends AbstractMapEntry<Range<K>, V> {
    private final Range<K> range;
    private final V value;

    TreeRangeMap$RangeMapEntry(Cut<K> lowerBound, Cut<K> upperBound, V value) {
        this(Range.create(lowerBound, upperBound), value);
    }

    TreeRangeMap$RangeMapEntry(Range<K> range, V value) {
        this.range = range;
        this.value = value;
    }

    public Range<K> getKey() {
        return this.range;
    }

    public V getValue() {
        return this.value;
    }

    public boolean contains(K value) {
        return this.range.contains(value);
    }

    Cut<K> getLowerBound() {
        return this.range.lowerBound;
    }

    Cut<K> getUpperBound() {
        return this.range.upperBound;
    }
}
