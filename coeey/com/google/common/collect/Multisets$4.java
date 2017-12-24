package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;
import javax.annotation.Nullable;

class Multisets$4 extends AbstractMultiset<E> {
    final /* synthetic */ Multiset val$multiset1;
    final /* synthetic */ Multiset val$multiset2;

    Multisets$4(Multiset multiset, Multiset multiset2) {
        this.val$multiset1 = multiset;
        this.val$multiset2 = multiset2;
    }

    public int count(@Nullable Object element) {
        int count1 = this.val$multiset1.count(element);
        if (count1 == 0) {
            return 0;
        }
        return Math.max(0, count1 - this.val$multiset2.count(element));
    }

    Iterator<Entry<E>> entryIterator() {
        final Iterator<Entry<E>> iterator1 = this.val$multiset1.entrySet().iterator();
        return new AbstractIterator<Entry<E>>() {
            protected Entry<E> computeNext() {
                while (iterator1.hasNext()) {
                    Entry<E> entry1 = (Entry) iterator1.next();
                    E element = entry1.getElement();
                    int count = entry1.getCount() - Multisets$4.this.val$multiset2.count(element);
                    if (count > 0) {
                        return Multisets.immutableEntry(element, count);
                    }
                }
                return (Entry) endOfData();
            }
        };
    }

    int distinctElements() {
        return Iterators.size(entryIterator());
    }
}
