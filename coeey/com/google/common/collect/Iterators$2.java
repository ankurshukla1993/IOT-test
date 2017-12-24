package com.google.common.collect;

import java.util.Iterator;
import java.util.NoSuchElementException;

class Iterators$2 implements Iterator<Object> {
    Iterators$2() {
    }

    public boolean hasNext() {
        return false;
    }

    public Object next() {
        throw new NoSuchElementException();
    }

    public void remove() {
        CollectPreconditions.checkRemove(false);
    }
}
