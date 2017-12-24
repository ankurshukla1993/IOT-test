package com.google.common.collect;

import java.util.Enumeration;
import java.util.Iterator;

class Iterators$14 implements Enumeration<T> {
    final /* synthetic */ Iterator val$iterator;

    Iterators$14(Iterator it) {
        this.val$iterator = it;
    }

    public boolean hasMoreElements() {
        return this.val$iterator.hasNext();
    }

    public T nextElement() {
        return this.val$iterator.next();
    }
}
