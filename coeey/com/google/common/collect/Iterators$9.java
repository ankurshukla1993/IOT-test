package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators$9 implements Iterator<T> {
    private int count;
    final /* synthetic */ Iterator val$iterator;
    final /* synthetic */ int val$limitSize;

    Iterators$9(int i, Iterator it) {
        this.val$limitSize = i;
        this.val$iterator = it;
    }

    public boolean hasNext() {
        return this.count < this.val$limitSize && this.val$iterator.hasNext();
    }

    public T next() {
        if (hasNext()) {
            this.count++;
            return this.val$iterator.next();
        }
        throw new NoSuchElementException();
    }

    public void remove() {
        this.val$iterator.remove();
    }
}
