package com.google.common.collect;

import java.util.Iterator;
import java.util.List;

class Iterables$5 extends FluentIterable<List<T>> {
    final /* synthetic */ Iterable val$iterable;
    final /* synthetic */ int val$size;

    Iterables$5(Iterable iterable, int i) {
        this.val$iterable = iterable;
        this.val$size = i;
    }

    public Iterator<List<T>> iterator() {
        return Iterators.paddedPartition(this.val$iterable.iterator(), this.val$size);
    }
}
