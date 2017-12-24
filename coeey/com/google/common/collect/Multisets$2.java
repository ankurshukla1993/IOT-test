package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;
import java.util.Set;

class Multisets$2 extends AbstractMultiset<E> {
    final /* synthetic */ Multiset val$multiset1;
    final /* synthetic */ Multiset val$multiset2;

    Multisets$2(Multiset multiset, Multiset multiset2) {
        this.val$multiset1 = multiset;
        this.val$multiset2 = multiset2;
    }

    public int count(Object element) {
        int count1 = this.val$multiset1.count(element);
        return count1 == 0 ? 0 : Math.min(count1, this.val$multiset2.count(element));
    }

    Set<E> createElementSet() {
        return Sets.intersection(this.val$multiset1.elementSet(), this.val$multiset2.elementSet());
    }

    Iterator<Entry<E>> entryIterator() {
        final Iterator<Entry<E>> iterator1 = this.val$multiset1.entrySet().iterator();
        return new AbstractIterator<Entry<E>>() {
            protected Entry<E> computeNext() {
                while (iterator1.hasNext()) {
                    Entry<E> entry1 = (Entry) iterator1.next();
                    E element = entry1.getElement();
                    int count = Math.min(entry1.getCount(), Multisets$2.this.val$multiset2.count(element));
                    if (count > 0) {
                        return Multisets.immutableEntry(element, count);
                    }
                }
                return (Entry) endOfData();
            }
        };
    }

    int distinctElements() {
        return elementSet().size();
    }
}
