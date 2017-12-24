package com.google.common.collect;

import java.util.Iterator;

class Iterables$3 extends TransformedIterator<Iterable<? extends T>, Iterator<? extends T>> {
    Iterables$3(Iterator x0) {
        super(x0);
    }

    Iterator<? extends T> transform(Iterable<? extends T> from) {
        return from.iterator();
    }
}
