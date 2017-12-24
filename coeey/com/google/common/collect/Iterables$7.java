package com.google.common.collect;

import java.util.Iterator;

class Iterables$7 extends FluentIterable<T> {
    final /* synthetic */ Class val$type;
    final /* synthetic */ Iterable val$unfiltered;

    Iterables$7(Iterable iterable, Class cls) {
        this.val$unfiltered = iterable;
        this.val$type = cls;
    }

    public Iterator<T> iterator() {
        return Iterators.filter(this.val$unfiltered.iterator(), this.val$type);
    }
}
