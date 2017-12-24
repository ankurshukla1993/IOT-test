package com.google.common.collect;

import java.util.Iterator;

final class Iterables$UnmodifiableIterable<T> extends FluentIterable<T> {
    private final Iterable<T> iterable;

    private Iterables$UnmodifiableIterable(Iterable<T> iterable) {
        this.iterable = iterable;
    }

    public Iterator<T> iterator() {
        return Iterators.unmodifiableIterator(this.iterable.iterator());
    }

    public String toString() {
        return this.iterable.toString();
    }
}
