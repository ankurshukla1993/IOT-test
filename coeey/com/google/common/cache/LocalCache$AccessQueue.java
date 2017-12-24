package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.collect.AbstractSequentialIterator;
import java.util.AbstractQueue;
import java.util.Iterator;

final class LocalCache$AccessQueue<K, V> extends AbstractQueue<ReferenceEntry<K, V>> {
    final ReferenceEntry<K, V> head = new C15471();

    class C15471 extends LocalCache$AbstractReferenceEntry<K, V> {
        ReferenceEntry<K, V> nextAccess = this;
        ReferenceEntry<K, V> previousAccess = this;

        C15471() {
        }

        public long getAccessTime() {
            return Long.MAX_VALUE;
        }

        public void setAccessTime(long time) {
        }

        public ReferenceEntry<K, V> getNextInAccessQueue() {
            return this.nextAccess;
        }

        public void setNextInAccessQueue(ReferenceEntry<K, V> next) {
            this.nextAccess = next;
        }

        public ReferenceEntry<K, V> getPreviousInAccessQueue() {
            return this.previousAccess;
        }

        public void setPreviousInAccessQueue(ReferenceEntry<K, V> previous) {
            this.previousAccess = previous;
        }
    }

    LocalCache$AccessQueue() {
    }

    public boolean offer(ReferenceEntry<K, V> entry) {
        LocalCache.connectAccessOrder(entry.getPreviousInAccessQueue(), entry.getNextInAccessQueue());
        LocalCache.connectAccessOrder(this.head.getPreviousInAccessQueue(), entry);
        LocalCache.connectAccessOrder(entry, this.head);
        return true;
    }

    public ReferenceEntry<K, V> peek() {
        ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
        return next == this.head ? null : next;
    }

    public ReferenceEntry<K, V> poll() {
        ReferenceEntry<K, V> next = this.head.getNextInAccessQueue();
        if (next == this.head) {
            return null;
        }
        remove(next);
        return next;
    }

    public boolean remove(Object o) {
        ReferenceEntry<K, V> e = (ReferenceEntry) o;
        ReferenceEntry<K, V> previous = e.getPreviousInAccessQueue();
        ReferenceEntry<K, V> next = e.getNextInAccessQueue();
        LocalCache.connectAccessOrder(previous, next);
        LocalCache.nullifyAccessOrder(e);
        return next != LocalCache$NullEntry.INSTANCE;
    }

    public boolean contains(Object o) {
        return ((ReferenceEntry) o).getNextInAccessQueue() != LocalCache$NullEntry.INSTANCE;
    }

    public boolean isEmpty() {
        return this.head.getNextInAccessQueue() == this.head;
    }

    public int size() {
        int size = 0;
        for (ReferenceEntry<K, V> e = this.head.getNextInAccessQueue(); e != this.head; e = e.getNextInAccessQueue()) {
            size++;
        }
        return size;
    }

    public void clear() {
        ReferenceEntry<K, V> e = this.head.getNextInAccessQueue();
        while (e != this.head) {
            ReferenceEntry<K, V> next = e.getNextInAccessQueue();
            LocalCache.nullifyAccessOrder(e);
            e = next;
        }
        this.head.setNextInAccessQueue(this.head);
        this.head.setPreviousInAccessQueue(this.head);
    }

    public Iterator<ReferenceEntry<K, V>> iterator() {
        return new AbstractSequentialIterator<ReferenceEntry<K, V>>(peek()) {
            protected ReferenceEntry<K, V> computeNext(ReferenceEntry<K, V> previous) {
                ReferenceEntry<K, V> next = previous.getNextInAccessQueue();
                return next == LocalCache$AccessQueue.this.head ? null : next;
            }
        };
    }
}
