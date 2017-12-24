package com.google.common.collect;

import java.util.NoSuchElementException;
import java.util.Queue;

class Iterables$ConsumingQueueIterator<T> extends AbstractIterator<T> {
    private final Queue<T> queue;

    private Iterables$ConsumingQueueIterator(Queue<T> queue) {
        this.queue = queue;
    }

    public T computeNext() {
        try {
            return this.queue.remove();
        } catch (NoSuchElementException e) {
            return endOfData();
        }
    }
}
