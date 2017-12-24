package com.google.common.collect;

import java.util.Iterator;
import java.util.Queue;

class Iterables$12 extends FluentIterable<T> {
    final /* synthetic */ Iterable val$iterable;

    Iterables$12(Iterable iterable) {
        this.val$iterable = iterable;
    }

    public Iterator<T> iterator() {
        return new Iterables$ConsumingQueueIterator((Queue) this.val$iterable, null);
    }

    public String toString() {
        return "Iterables.consumingIterable(...)";
    }
}
