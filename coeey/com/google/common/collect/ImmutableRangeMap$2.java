package com.google.common.collect;

import java.util.Map;

class ImmutableRangeMap$2 extends ImmutableRangeMap<K, V> {
    final /* synthetic */ ImmutableRangeMap this$0;
    final /* synthetic */ ImmutableRangeMap val$outer;
    final /* synthetic */ Range val$range;

    ImmutableRangeMap$2(ImmutableRangeMap immutableRangeMap, ImmutableList x0, ImmutableList x1, Range range, ImmutableRangeMap immutableRangeMap2) {
        this.this$0 = immutableRangeMap;
        this.val$range = range;
        this.val$outer = immutableRangeMap2;
        super(x0, x1);
    }

    public /* bridge */ /* synthetic */ Map asMapOfRanges() {
        return super.asMapOfRanges();
    }

    public ImmutableRangeMap<K, V> subRangeMap(Range<K> subRange) {
        if (this.val$range.isConnected(subRange)) {
            return this.val$outer.subRangeMap(subRange.intersection(this.val$range));
        }
        return ImmutableRangeMap.of();
    }
}
