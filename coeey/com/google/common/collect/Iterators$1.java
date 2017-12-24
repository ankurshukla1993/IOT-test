package com.google.common.collect;

import java.util.NoSuchElementException;

class Iterators$1 extends UnmodifiableListIterator<Object> {
    Iterators$1() {
    }

    public boolean hasNext() {
        return false;
    }

    public Object next() {
        throw new NoSuchElementException();
    }

    public boolean hasPrevious() {
        return false;
    }

    public Object previous() {
        throw new NoSuchElementException();
    }

    public int nextIndex() {
        return 0;
    }

    public int previousIndex() {
        return -1;
    }
}
