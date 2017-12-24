package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;

class AbstractSortedMultiset$1 extends DescendingMultiset<E> {
    final /* synthetic */ AbstractSortedMultiset this$0;

    AbstractSortedMultiset$1(AbstractSortedMultiset abstractSortedMultiset) {
        this.this$0 = abstractSortedMultiset;
    }

    SortedMultiset<E> forwardMultiset() {
        return this.this$0;
    }

    Iterator<Entry<E>> entryIterator() {
        return this.this$0.descendingEntryIterator();
    }

    public Iterator<E> iterator() {
        return this.this$0.descendingIterator();
    }
}
