package com.google.common.collect;

import java.util.Iterator;
import java.util.List;

class Iterables$4 extends FluentIterable<List<T>> {
    final /* synthetic */ Iterable val$iterable;
    final /* synthetic */ int val$size;

    Iterables$4(Iterable iterable, int i) {
        this.val$iterable = iterable;
        this.val$size = i;
    }

    public Iterator<List<T>> iterator() {
        return Iterators.partition(this.val$iterable.iterator(), this.val$size);
    }
}
