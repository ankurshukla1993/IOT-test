package com.google.common.collect;

import java.util.Enumeration;

class Iterators$13 extends UnmodifiableIterator<T> {
    final /* synthetic */ Enumeration val$enumeration;

    Iterators$13(Enumeration enumeration) {
        this.val$enumeration = enumeration;
    }

    public boolean hasNext() {
        return this.val$enumeration.hasMoreElements();
    }

    public T next() {
        return this.val$enumeration.nextElement();
    }
}
