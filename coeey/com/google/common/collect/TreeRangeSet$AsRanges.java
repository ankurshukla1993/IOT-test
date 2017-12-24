package com.google.common.collect;

import java.util.Collection;
import java.util.Set;
import javax.annotation.Nullable;

final class TreeRangeSet$AsRanges extends ForwardingCollection<Range<C>> implements Set<Range<C>> {
    final /* synthetic */ TreeRangeSet this$0;

    TreeRangeSet$AsRanges(TreeRangeSet treeRangeSet) {
        this.this$0 = treeRangeSet;
    }

    protected Collection<Range<C>> delegate() {
        return this.this$0.rangesByLowerBound.values();
    }

    public int hashCode() {
        return Sets.hashCodeImpl(this);
    }

    public boolean equals(@Nullable Object o) {
        return Sets.equalsImpl(this, o);
    }
}
