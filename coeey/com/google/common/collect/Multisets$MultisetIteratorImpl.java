package com.google.common.collect;

import com.google.common.collect.Multiset.Entry;
import java.util.Iterator;
import java.util.NoSuchElementException;

final class Multisets$MultisetIteratorImpl<E> implements Iterator<E> {
    private boolean canRemove;
    private Entry<E> currentEntry;
    private final Iterator<Entry<E>> entryIterator;
    private int laterCount;
    private final Multiset<E> multiset;
    private int totalCount;

    Multisets$MultisetIteratorImpl(Multiset<E> multiset, Iterator<Entry<E>> entryIterator) {
        this.multiset = multiset;
        this.entryIterator = entryIterator;
    }

    public boolean hasNext() {
        return this.laterCount > 0 || this.entryIterator.hasNext();
    }

    public E next() {
        if (hasNext()) {
            if (this.laterCount == 0) {
                this.currentEntry = (Entry) this.entryIterator.next();
                int count = this.currentEntry.getCount();
                this.laterCount = count;
                this.totalCount = count;
            }
            this.laterCount--;
            this.canRemove = true;
            return this.currentEntry.getElement();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        CollectPreconditions.checkRemove(this.canRemove);
        if (this.totalCount == 1) {
            this.entryIterator.remove();
        } else {
            this.multiset.remove(this.currentEntry.getElement());
        }
        this.totalCount--;
        this.canRemove = false;
    }
}
