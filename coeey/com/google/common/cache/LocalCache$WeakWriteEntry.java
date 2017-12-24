package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import java.lang.ref.ReferenceQueue;
import javax.annotation.Nullable;

final class LocalCache$WeakWriteEntry<K, V> extends LocalCache$WeakEntry<K, V> {
    ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
    ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();
    volatile long writeTime = Long.MAX_VALUE;

    LocalCache$WeakWriteEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
        super(queue, key, hash, next);
    }

    public long getWriteTime() {
        return this.writeTime;
    }

    public void setWriteTime(long time) {
        this.writeTime = time;
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
