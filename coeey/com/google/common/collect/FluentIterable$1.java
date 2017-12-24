package com.google.common.collect;

import java.util.Iterator;

class FluentIterable$1 extends FluentIterable<E> {
    final /* synthetic */ Iterable val$iterable;

    FluentIterable$1(Iterable x0, Iterable iterable) {
        this.val$iterable = iterable;
        super(x0);
    }

    public Iterator<E> iterator() {
        return this.val$iterable.iterator();
    }
}
