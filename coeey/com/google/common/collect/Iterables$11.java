package com.google.common.collect;

import java.util.Iterator;

class Iterables$11 extends FluentIterable<T> {
    final /* synthetic */ Iterable val$iterable;
    final /* synthetic */ int val$limitSize;

    Iterables$11(Iterable iterable, int i) {
        this.val$iterable = iterable;
        this.val$limitSize = i;
    }

    public Iterator<T> iterator() {
        return Iterators.limit(this.val$iterable.iterator(), this.val$limitSize);
    }
}
