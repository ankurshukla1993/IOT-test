package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.collect.AbstractSequentialIterator;
import java.util.AbstractQueue;
import java.util.Iterator;

final class LocalCache$WriteQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
    final ReferenceEntry<K, V> head = new C15611();

    class C15611 extends LocalCache$AbstractReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextWrite = this;
        ReferenceEntry<K, V> previousWrite = this;

        C15611() {
        }

        public long getWriteTime() {
            return Long.MAX_VALUE;
        }

        public void setWriteTime(long time) {
        }

        public ReferenceEntry<K, V> getNextInWriteQueue() {
            return this.nextWrite;
        }

        public void setNextInWriteQueue(ReferenceEntry<K, V> next) {
            this.nextWrite = next;
        }

        public ReferenceEntry<K, V> getPreviousInWriteQueue() {
            return this.previousWrite;
        }

        public void setPreviousInWriteQueue(ReferenceEntry<K, V> previous) {
            this.previousWrite = previous;
        }
    }

    LocalCache$WriteQueue() {
    }

    public boolean offer(ReferenceEntry<K, V> entry) {
        LocalCache.connectWriteOrder(entry.getPreviousInWriteQueue(), entry.getNextInWriteQueue());
        LocalCache.connectWriteOrder(this.head.getPreviousInWriteQueue(), entry);
        LocalCache.connectWriteOrder(entry, this.head);
        return true;
    }

    public ReferenceEntry<K, V> peek() {
        ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
        return next == this.head ? null : next;
    }

    public ReferenceEntry<K, V> poll() {
        ReferenceEntry<K, V> next = this.head.getNextInWriteQueue();
        if (next == this.head) {
            return null;
        }
        remove(next);
        return next;
    }

    public boolean remove(Object o) {
        ReferenceEntry<K, V> e = (ReferenceEntry) o;
        ReferenceEntry<K, V> previous = e.getPreviousInWriteQueue();
        ReferenceEntry<K, V> next = e.getNextInWriteQueue();
        LocalCache.connectWriteOrder(previous, next);
        LocalCache.nullifyWriteOrder(e);
        return next != LocalCache$NullEntry.INSTANCE;
    }

    public boolean contains(Object o) {
        return ((ReferenceEntry) o).getNextInWriteQueue() != LocalCache$NullEntry.INSTANCE;
    }

    public boolean isEmpty() {
        return this.head.getNextInWriteQueue() == this.head;
    }

    public int size() {
        int size = 0;
        for (ReferenceEntry<K, V> e = this.head.getNextInWriteQueue(); e != this.head; e = e.getNextInWriteQueue()) {
            size++;
        }
        return size;
    }

    public void clear() {
        ReferenceEntry<K, V> e = this.head.getNextInWriteQueue();
        while (e != this.head) {
            ReferenceEntry<K, V> next = e.getNextInWriteQueue();
            LocalCache.nullifyWriteOrder(e);
            e = next;
        }
        this.head.setNextInWriteQueue(this.head);
        this.head.setPreviousInWriteQueue(this.head);
    }

    public Iterator<ReferenceEntry<K, V>> iterator() {
        return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) {
            protected ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> previous) {
                ReferenceEntry<K, V> next = previous.getNextInWriteQueue();
                return next == LocalCache$WriteQueue.this.head ? null : next;
            }
        };
    }
}
