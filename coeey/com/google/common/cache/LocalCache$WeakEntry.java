package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.ValueReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import javax.annotation.Nullable;

class LocalCache$WeakEntry<K, V> extends WeakReference<K> implements ReferenceEntry<K, V> {
    final int hash;
    final ReferenceEntry<K, V> next;
    volatile ValueReference<K, V> valueReference = LocalCache.unset();

    LocalCache$WeakEntry(ReferenceQueue<K> queue, K key, int hash, @Nullable ReferenceEntry<K, V> next) {
        super(key, queue);
        this.hash = hash;
        this.next = next;
    }

    public K getKey() {
        return get();
    }

    public long getAccessTime() {
        throw new UnsupportedOperationException();
    }

    public void setAccessTime(long time) {
        throw new UnsupportedOperationException();
    }

    public ReferenceEntry<K, V> getNextInAccessQueue() {
        throw new UnsupportedOperationException();
    }

    public void setNextInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
        throw new UnsupportedOperationException();
    }

    public ReferenceEntry<K, V> getPreviousInAccessQueue() {
        throw new UnsupportedOperationException();
    }

    public void setPreviousInAccessQueue(ReferenceEntry<K, V> referenceEntry) {
        throw new UnsupportedOperationException();
    }

    public long getWriteTime() {
        throw new UnsupportedOperationException();
    }

    public void setWriteTime(long time) {
        throw new UnsupportedOperationException();
    }

    public ReferenceEntry<K, V> getNextInWriteQueue() {
        throw new UnsupportedOperationException();
    }

    public void setNextInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
        throw new UnsupportedOperationException();
    }

    public ReferenceEntry<K, V> getPreviousInWriteQueue() {
        throw new UnsupportedOperationException();
    }

    public void setPreviousInWriteQueue(ReferenceEntry<K, V> referenceEntry) {
        throw new UnsupportedOperationException();
    }

    public ValueReference<K, V> getValueReference() {
        return this.valueReference;
    }

    public void setValueReference(ValueReference<K, V> valueReference) {
        this.valueReference = valueReference;
    }

    public int getHash() {
        return this.hash;
    }

    public ReferenceEntry<K, V> getNext() {
        return this.next;
    }
}
