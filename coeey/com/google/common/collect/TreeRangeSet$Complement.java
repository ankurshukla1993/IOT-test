package com.google.common.collect;

import com.google.common.collect.TreeRangeSet.ComplementRangesByLowerBound;

final class TreeRangeSet$Complement extends TreeRangeSet<C> {
    final /* synthetic */ TreeRangeSet this$0;

    TreeRangeSet$Complement(TreeRangeSet treeRangeSet) {
        this.this$0 = treeRangeSet;
        super(new ComplementRangesByLowerBound(treeRangeSet.rangesByLowerBound), null);
    }

    public void add(Range<C> rangeToAdd) {
        this.this$0.remove(rangeToAdd);
    }

    public void remove(Range<C> rangeToRemove) {
        this.this$0.add(rangeToRemove);
    }

    public boolean contains(C value) {
        return !this.this$0.contains(value);
    }

    public RangeSet<C> complement() {
        return this.this$0;
    }
}
