package com.google.common.collect;

import com.google.common.base.Predicate;
import java.util.Iterator;

class Iterables$6 extends FluentIterable<T> {
    final /* synthetic */ Predicate val$predicate;
    final /* synthetic */ Iterable val$unfiltered;

    Iterables$6(Iterable iterable, Predicate predicate) {
        this.val$unfiltered = iterable;
        this.val$predicate = predicate;
    }

    public Iterator<T> iterator() {
        return Iterators.filter(this.val$unfiltered.iterator(), this.val$predicate);
    }
}
