package com.google.common.collect;

import java.util.Iterator;

class Iterators$10 extends UnmodifiableIterator<T> {
    final /* synthetic */ Iterator val$iterator;

    Iterators$10(Iterator it) {
        this.val$iterator = it;
    }

    public boolean hasNext() {
        return this.val$iterator.hasNext();
    }

    public T next() {
        T next = this.val$iterator.next();
        this.val$iterator.remove();
        return next;
    }

    public String toString() {
        return "Iterators.consumingIterator(...)";
    }
}
