package com.google.common.collect;

import java.util.Iterator;

class Iterables$2 extends FluentIterable<T> {
    final /* synthetic */ Iterable val$inputs;

    Iterables$2(Iterable iterable) {
        this.val$inputs = iterable;
    }

    public Iterator<T> iterator() {
        return Iterators.concat(Iterables.access$100(this.val$inputs));
    }
}
