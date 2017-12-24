package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import java.lang.ref.ReferenceQueue;
import javax.annotation.Nullable;

final class LocalCache$WeakAccessWriteEntry<K, V> extends LocalCache$WeakEntry<K, V> {
    volatile long accessTime = Long.MAX_VALUE;
    ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
    ReferenceEntry<K, V> nextWrite = LocalCache.nullEntry();
    ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();
    ReferenceEntry<K, V> previousWrite = LocalCache.nullEntry();
    volatile long writeTime = Long.MAX_VALUE;

    LocalCache$WeakAccessWriteEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
        super(queue, key, hash, next);
    }

    public long getAccessTime() {
        return this.accessTime;
    }

    public void setAccessTime(long time) {
        this.accessTime = time;
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
