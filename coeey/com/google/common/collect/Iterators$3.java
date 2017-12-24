package com.google.common.collect;

import java.util.Iterator;

class Iterators$3 extends UnmodifiableIterator<T> {
    final /* synthetic */ Iterator val$iterator;

    Iterators$3(Iterator it) {
        this.val$iterator = it;
    }

    public boolean hasNext() {
        return this.val$iterator.hasNext();
    }

    public T next() {
        return this.val$iterator.next();
    }
}
