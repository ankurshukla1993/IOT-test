package com.google.common.collect;

import com.google.common.collect.TreeRangeSet.SubRangeSetRangesByLowerBound;
import java.util.Iterator;
import java.util.Map.Entry;

class TreeRangeSet$SubRangeSetRangesByLowerBound$2 extends AbstractIterator<Entry<Cut<C>, Range<C>>> {
    final /* synthetic */ SubRangeSetRangesByLowerBound this$0;
    final /* synthetic */ Iterator val$completeRangeItr;

    TreeRangeSet$SubRangeSetRangesByLowerBound$2(SubRangeSetRangesByLowerBound subRangeSetRangesByLowerBound, Iterator it) {
        this.this$0 = subRangeSetRangesByLowerBound;
        this.val$completeRangeItr = it;
    }

    protected Entry<Cut<C>, Range<C>> computeNext() {
        if (!this.val$completeRangeItr.hasNext()) {
            return (Entry) endOfData();
        }
        Range<C> nextRange = (Range) this.val$completeRangeItr.next();
        if (SubRangeSetRangesByLowerBound.access$300(this.this$0).lowerBound.compareTo(nextRange.upperBound) >= 0) {
            return (Entry) endOfData();
        }
        nextRange = nextRange.intersection(SubRangeSetRangesByLowerBound.access$300(this.this$0));
        if (SubRangeSetRangesByLowerBound.access$400(this.this$0).contains(nextRange.lowerBound)) {
            return Maps.immutableEntry(nextRange.lowerBound, nextRange);
        }
        return (Entry) endOfData();
    }
}
