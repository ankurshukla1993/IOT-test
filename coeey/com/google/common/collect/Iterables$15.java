package com.google.common.collect;

import com.google.common.base.Function;
import java.util.Iterator;

class Iterables$15 implements Function<Iterable<? extends T>, Iterator<? extends T>> {
    Iterables$15() {
    }

    public Iterator<? extends T> apply(Iterable<? extends T> iterable) {
        return iterable.iterator();
    }
}
