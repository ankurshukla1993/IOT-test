package com.google.common.collect;

import com.google.common.collect.TreeRangeSet.SubRangeSetRangesByLowerBound;
import java.util.Iterator;
import java.util.Map.Entry;

class TreeRangeSet$SubRangeSetRangesByLowerBound$1 extends AbstractIterator<Entry<Cut<C>, Range<C>>> {
    final /* synthetic */ SubRangeSetRangesByLowerBound this$0;
    final /* synthetic */ Iterator val$completeRangeItr;
    final /* synthetic */ Cut val$upperBoundOnLowerBounds;

    TreeRangeSet$SubRangeSetRangesByLowerBound$1(SubRangeSetRangesByLowerBound subRangeSetRangesByLowerBound, Iterator it, Cut cut) {
        this.this$0 = subRangeSetRangesByLowerBound;
        this.val$completeRangeItr = it;
        this.val$upperBoundOnLowerBounds = cut;
    }

    protected Entry<Cut<C>, Range<C>> computeNext() {
        if (!this.val$completeRangeItr.hasNext()) {
            return (Entry) endOfData();
        }
        Range<C> nextRange = (Range) this.val$completeRangeItr.next();
        if (this.val$upperBoundOnLowerBounds.isLessThan(nextRange.lowerBound)) {
            return (Entry) endOfData();
        }
        nextRange = nextRange.intersection(SubRangeSetRangesByLowerBound.access$300(this.this$0));
        return Maps.immutableEntry(nextRange.lowerBound, nextRange);
    }
}
