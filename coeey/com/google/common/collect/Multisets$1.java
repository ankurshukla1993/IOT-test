package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;
import java.util.Set;
import javax.annotation.Nullable;

class Multisets$1 extends AbstractMultiset<E> {
    final /* synthetic */ Multiset val$multiset1;
    final /* synthetic */ Multiset val$multiset2;

    Multisets$1(Multiset multiset, Multiset multiset2) {
        this.val$multiset1 = multiset;
        this.val$multiset2 = multiset2;
    }

    public boolean contains(@Nullable Object element) {
        return this.val$multiset1.contains(element) || this.val$multiset2.contains(element);
    }

    public boolean isEmpty() {
        return this.val$multiset1.isEmpty() && this.val$multiset2.isEmpty();
    }

    public int count(Object element) {
        return Math.max(this.val$multiset1.count(element), this.val$multiset2.count(element));
    }

    Set<E> createElementSet() {
        return Sets.union(this.val$multiset1.elementSet(), this.val$multiset2.elementSet());
    }

    Iterator<Entry<E>> entryIterator() {
        final Iterator<? extends Entry<? extends E>> iterator1 = this.val$multiset1.entrySet().iterator();
        final Iterator<? extends Entry<? extends E>> iterator2 = this.val$multiset2.entrySet().iterator();
        return new AbstractIterator<Entry<E>>() {
            protected Entry<E> computeNext() {
                if (iterator1.hasNext()) {
                    Entry<? extends E> entry1 = (Entry) iterator1.next();
                    E element = entry1.getElement();
                    return Multisets.immutableEntry(element, Math.max(entry1.getCount(), Multisets$1.this.val$multiset2.count(element)));
                }
                while (iterator2.hasNext()) {
                    Entry<? extends E> entry2 = (Entry) iterator2.next();
                    element = entry2.getElement();
                    if (!Multisets$1.this.val$multiset1.contains(element)) {
                        return Multisets.immutableEntry(element, entry2.getCount());
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
