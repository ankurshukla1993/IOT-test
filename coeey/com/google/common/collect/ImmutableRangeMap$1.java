package com.google.common.collect;

import com.google.common.base.Preconditions;

class ImmutableRangeMap$1 extends ImmutableList<Range<K>> {
    final /* synthetic */ ImmutableRangeMap this$0;
    final /* synthetic */ int val$len;
    final /* synthetic */ int val$off;
    final /* synthetic */ Range val$range;

    ImmutableRangeMap$1(ImmutableRangeMap immutableRangeMap, int i, int i2, Range range) {
        this.this$0 = immutableRangeMap;
        this.val$len = i;
        this.val$off = i2;
        this.val$range = range;
    }

    public int size() {
        return this.val$len;
    }

    public Range<K> get(int index) {
        Preconditions.checkElementIndex(index, this.val$len);
        if (index == 0 || index == this.val$len - 1) {
            return ((Range) ImmutableRangeMap.access$000(this.this$0).get(this.val$off + index)).intersection(this.val$range);
        }
        return (Range) ImmutableRangeMap.access$000(this.this$0).get(this.val$off + index);
    }

    boolean isPartialView() {
        return true;
    }
}
