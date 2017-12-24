package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators$4 implements Iterator<T> {
    Iterator<T> iterator = Iterators.emptyIterator();
    Iterator<T> removeFrom;
    final /* synthetic */ Iterable val$iterable;

    Iterators$4(Iterable iterable) {
        this.val$iterable = iterable;
    }

    public boolean hasNext() {
        if (!this.iterator.hasNext()) {
            this.iterator = this.val$iterable.iterator();
        }
        return this.iterator.hasNext();
    }

    public T next() {
        if (hasNext()) {
            this.removeFrom = this.iterator;
            return this.iterator.next();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        CollectPreconditions.checkRemove(this.removeFrom != null);
        this.removeFrom.remove();
        this.removeFrom = null;
    }
}
