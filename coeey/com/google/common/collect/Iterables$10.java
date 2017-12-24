package com.google.common.collect;

import java.util.Iterator;

class Iterables$10 extends FluentIterable<T> {
    final /* synthetic */ Iterable val$iterable;
    final /* synthetic */ int val$numberToSkip;

    Iterables$10(Iterable iterable, int i) {
        this.val$iterable = iterable;
        this.val$numberToSkip = i;
    }

    public Iterator<T> iterator() {
        final Iterator<T> iterator = this.val$iterable.iterator();
        Iterators.advance(iterator, this.val$numberToSkip);
        return new Iterator<T>() {
            boolean atStart = true;

            public boolean hasNext() {
                return iterator.hasNext();
            }

            public T next() {
                T result = iterator.next();
                this.atStart = false;
                return result;
            }

            public void remove() {
                CollectPreconditions.checkRemove(!this.atStart);
                iterator.remove();
            }
        };
    }
}
