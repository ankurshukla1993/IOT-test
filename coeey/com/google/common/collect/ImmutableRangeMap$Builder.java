package com.google.common.collect;

import com.google.common.base.Preconditions;
import java.util.Map;
import java.util.Map.Entry;

public final class ImmutableRangeMap$Builder<K extends Comparable<?>, V> {
    private final RangeSet<K> keyRanges = TreeRangeSet.create();
    private final RangeMap<K, V> rangeMap = TreeRangeMap.create();

    public ImmutableRangeMap$Builder<K, V> put(Range<K> range, V value) {
        boolean z;
        Preconditions.checkNotNull(range);
        Preconditions.checkNotNull(value);
        if (range.isEmpty()) {
            z = false;
        } else {
            z = true;
        }
        Preconditions.checkArgument(z, "Range must not be empty, but was %s", range);
        if (!this.keyRanges.complement().encloses(range)) {
            for (Entry<Range<K>, V> entry : this.rangeMap.asMapOfRanges().entrySet()) {
                Range<K> key = (Range) entry.getKey();
                if (key.isConnected(range) && !key.intersection(range).isEmpty()) {
                    String valueOf = String.valueOf(String.valueOf(range));
                    String valueOf2 = String.valueOf(String.valueOf(entry));
                    throw new IllegalArgumentException(new StringBuilder((valueOf.length() + 47) + valueOf2.length()).append("Overlapping ranges: range ").append(valueOf).append(" overlaps with entry ").append(valueOf2).toString());
                }
            }
        }
        this.keyRanges.add(range);
        this.rangeMap.put(range, value);
        return this;
    }

    public ImmutableRangeMap$Builder<K, V> putAll(RangeMap<K, ? extends V> rangeMap) {
        for (Entry<Range<K>, ? extends V> entry : rangeMap.asMapOfRanges().entrySet()) {
            put((Range) entry.getKey(), entry.getValue());
        }
        return this;
    }

    public ImmutableRangeMap<K, V> build() {
        Map<Range<K>, V> map = this.rangeMap.asMapOfRanges();
        ImmutableList$Builder<Range<K>> rangesBuilder = new ImmutableList$Builder(map.size());
        ImmutableList$Builder<V> valuesBuilder = new ImmutableList$Builder(map.size());
        for (Entry<Range<K>, V> entry : map.entrySet()) {
            rangesBuilder.add(entry.getKey());
            valuesBuilder.add(entry.getValue());
        }
        return new ImmutableRangeMap(rangesBuilder.build(), valuesBuilder.build());
    }
}
