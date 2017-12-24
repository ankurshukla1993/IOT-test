package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import javax.annotation.Nullable;

final class LocalCache$StrongAccessEntry<K, V> extends LocalCache$StrongEntry<K, V> {
    volatile long accessTime = Long.MAX_VALUE;
    ReferenceEntry<K, V> nextAccess = LocalCache.nullEntry();
    ReferenceEntry<K, V> previousAccess = LocalCache.nullEntry();

    LocalCache$StrongAccessEntry(K key, int hash, @Nullable ReferenceEntry<K, V> next) {
        super(key, hash, next);
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
}
