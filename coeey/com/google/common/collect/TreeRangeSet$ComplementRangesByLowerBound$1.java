package com.google.common.collect;

import com.google.common.collect.TreeRangeSet.ComplementRangesByLowerBound;
import java.util.Map.Entry;

class TreeRangeSet$ComplementRangesByLowerBound$1 extends AbstractIterator<Entry<Cut<C>, Range<C>>> {
    Cut<C> nextComplementRangeLowerBound = this.val$firstComplementRangeLowerBound;
    final /* synthetic */ ComplementRangesByLowerBound this$0;
    final /* synthetic */ Cut val$firstComplementRangeLowerBound;
    final /* synthetic */ PeekingIterator val$positiveItr;

    TreeRangeSet$ComplementRangesByLowerBound$1(ComplementRangesByLowerBound complementRangesByLowerBound, Cut cut, PeekingIterator peekingIterator) {
        this.this$0 = complementRangesByLowerBound;
        this.val$firstComplementRangeLowerBound = cut;
        this.val$positiveItr = peekingIterator;
    }

    protected Entry<Cut<C>, Range<C>> computeNext() {
        if (ComplementRangesByLowerBound.access$100(this.this$0).upperBound.isLessThan(this.nextComplementRangeLowerBound) || this.nextComplementRangeLowerBound == Cut.aboveAll()) {
            return (Entry) endOfData();
        }
        Range<C> negativeRange;
        if (this.val$positiveItr.hasNext()) {
            Range<C> positiveRange = (Range) this.val$positiveItr.next();
            negativeRange = Range.create(this.nextComplementRangeLowerBound, positiveRange.lowerBound);
            this.nextComplementRangeLowerBound = positiveRange.upperBound;
        } else {
            negativeRange = Range.create(this.nextComplementRangeLowerBound, Cut.aboveAll());
            this.nextComplementRangeLowerBound = Cut.aboveAll();
        }
        return Maps.immutableEntry(negativeRange.lowerBound, negativeRange);
    }
}
