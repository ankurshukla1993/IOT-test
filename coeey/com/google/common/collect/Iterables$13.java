package com.google.common.collect;

import java.util.Iterator;

class Iterables$13 extends FluentIterable<T> {
    final /* synthetic */ Iterable val$iterable;

    Iterables$13(Iterable iterable) {
        this.val$iterable = iterable;
    }

    public Iterator<T> iterator() {
        return Iterators.consumingIterator(this.val$iterable.iterator());
    }

    public String toString() {
        return "Iterables.consumingIterable(...)";
    }
}
