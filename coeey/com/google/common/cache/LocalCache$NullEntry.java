package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.ValueReference;

enum LocalCache$NullEntry implements ReferenceEntry<Object, Object> {
    INSTANCE;

    public ValueReference<Object, Object> getValueReference() {
        return null;
    }

    public void setValueReference(ValueReference<Object, Object> valueReference) {
    }

    public ReferenceEntry<Object, Object> getNext() {
        return null;
    }

    public int getHash() {
        return 0;
    }

    public Object getKey() {
        return null;
    }

    public long getAccessTime() {
        return 0;
    }

    public void setAccessTime(long time) {
    }

    public ReferenceEntry<Object, Object> getNextInAccessQueue() {
        return this;
    }

    public void setNextInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
    }

    public ReferenceEntry<Object, Object> getPreviousInAccessQueue() {
        return this;
    }

    public void setPreviousInAccessQueue(ReferenceEntry<Object, Object> referenceEntry) {
    }

    public long getWriteTime() {
        return 0;
    }

    public void setWriteTime(long time) {
    }

    public ReferenceEntry<Object, Object> getNextInWriteQueue() {
        return this;
    }

    public void setNextInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
    }

    public ReferenceEntry<Object, Object> getPreviousInWriteQueue() {
        return this;
    }

    public void setPreviousInWriteQueue(ReferenceEntry<Object, Object> referenceEntry) {
    }
}
