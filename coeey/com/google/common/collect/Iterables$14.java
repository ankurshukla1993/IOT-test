package com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;

class Iterables$14 extends FluentIterable<T> {
    final /* synthetic */ Comparator val$comparator;
    final /* synthetic */ Iterable val$iterables;

    Iterables$14(Iterable iterable, Comparator comparator) {
        this.val$iterables = iterable;
        this.val$comparator = comparator;
    }

    public Iterator<T> iterator() {
        return Iterators.mergeSorted(Iterables.transform(this.val$iterables, Iterables.access$300()), this.val$comparator);
    }
}
