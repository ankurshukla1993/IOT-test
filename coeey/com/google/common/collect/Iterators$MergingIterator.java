package com.google.common.collect;

import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;
import java.util.Queue;

class Iterators$MergingIterator<T> extends UnmodifiableIterator<T> {
    final Queue<PeekingIterator<T>> queue;

    public Iterators$MergingIterator(Iterable<? extends Iterator<? extends T>> iterators, final Comparator<? super T> itemComparator) {
        this.queue = new PriorityQueue(2, new Comparator<PeekingIterator<T>>() {
            public int compare(PeekingIterator<T> o1, PeekingIterator<T> o2) {
                return itemComparator.compare(o1.peek(), o2.peek());
            }
        });
        for (Iterator<? extends T> iterator : iterators) {
            if (iterator.hasNext()) {
                this.queue.add(Iterators.peekingIterator(iterator));
            }
        }
    }

    public boolean hasNext() {
        return !this.queue.isEmpty();
    }

    public T next() {
        PeekingIterator<T> nextIter = (PeekingIterator) this.queue.remove();
        T next = nextIter.next();
        if (nextIter.hasNext()) {
            this.queue.add(nextIter);
        }
        return next;
    }
}
