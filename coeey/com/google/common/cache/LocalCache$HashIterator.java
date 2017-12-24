package com.google.common.cache;

import com.google.common.base.Preconditions;
import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.Segment;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicReferenceArray;

abstract class LocalCache$HashIterator<T> implements Iterator<T> {
    Segment<K, V> currentSegment;
    AtomicReferenceArray<ReferenceEntry<K, V>> currentTable;
    LocalCache$WriteThroughEntry lastReturned;
    ReferenceEntry<K, V> nextEntry;
    LocalCache$WriteThroughEntry nextExternal;
    int nextSegmentIndex;
    int nextTableIndex = -1;
    final /* synthetic */ LocalCache this$0;

    public abstract T next();

    LocalCache$HashIterator(LocalCache localCache) {
        this.this$0 = localCache;
        this.nextSegmentIndex = localCache.segments.length - 1;
        advance();
    }

    final void advance() {
        this.nextExternal = null;
        if (!nextInChain() && !nextInTable()) {
            while (this.nextSegmentIndex >= 0) {
                Segment[] segmentArr = this.this$0.segments;
                int i = this.nextSegmentIndex;
                this.nextSegmentIndex = i - 1;
                this.currentSegment = segmentArr[i];
                if (this.currentSegment.count != 0) {
                    this.currentTable = this.currentSegment.table;
                    this.nextTableIndex = this.currentTable.length() - 1;
                    if (nextInTable()) {
                        return;
                    }
                }
            }
        }
    }

    boolean nextInChain() {
        if (this.nextEntry != null) {
            this.nextEntry = this.nextEntry.getNext();
            while (this.nextEntry != null) {
                if (advanceTo(this.nextEntry)) {
                    return true;
                }
                this.nextEntry = this.nextEntry.getNext();
            }
        }
        return false;
    }

    boolean nextInTable() {
        while (this.nextTableIndex >= 0) {
            AtomicReferenceArray atomicReferenceArray = this.currentTable;
            int i = this.nextTableIndex;
            this.nextTableIndex = i - 1;
            ReferenceEntry referenceEntry = (ReferenceEntry) atomicReferenceArray.get(i);
            this.nextEntry = referenceEntry;
            if (referenceEntry != null && (advanceTo(this.nextEntry) || nextInChain())) {
                return true;
            }
        }
        return false;
    }

    boolean advanceTo(ReferenceEntry<K, V> entry) {
        try {
            long now = this.this$0.ticker.read();
            K key = entry.getKey();
            V value = this.this$0.getLiveValue(entry, now);
            if (value != null) {
                this.nextExternal = new LocalCache$WriteThroughEntry(this.this$0, key, value);
                return true;
            }
            this.currentSegment.postReadCleanup();
            return false;
        } finally {
            this.currentSegment.postReadCleanup();
        }
    }

    public boolean hasNext() {
        return this.nextExternal != null;
    }

    LocalCache$WriteThroughEntry nextEntry() {
        if (this.nextExternal == null) {
            throw new NoSuchElementException();
        }
        this.lastReturned = this.nextExternal;
        advance();
        return this.lastReturned;
    }

    public void remove() {
        Preconditions.checkState(this.lastReturned != null);
        this.this$0.remove(this.lastReturned.getKey());
        this.lastReturned = null;
    }
}
