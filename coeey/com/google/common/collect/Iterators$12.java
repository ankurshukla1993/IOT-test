package com.google.common.collect;

import java.util.NoSuchElementException;

class Iterators$12 extends UnmodifiableIterator<T> {
    boolean done;
    final /* synthetic */ Object val$value;

    Iterators$12(Object obj) {
        this.val$value = obj;
    }

    public boolean hasNext() {
        return !this.done;
    }

    public T next() {
        if (this.done) {
            throw new NoSuchElementException();
        }
        this.done = true;
        return this.val$value;
    }
}
