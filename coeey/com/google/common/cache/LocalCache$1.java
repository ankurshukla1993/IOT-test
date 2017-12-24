package com.google.common.cache;

import com.google.common.cache.LocalCache.ReferenceEntry;
import com.google.common.cache.LocalCache.ValueReference;
import java.lang.ref.ReferenceQueue;
import javax.annotation.Nullable;

class LocalCache$1 implements ValueReference<Object, Object> {
    LocalCache$1() {
    }

    public Object get() {
        return null;
    }

    public int getWeight() {
        return 0;
    }

    public ReferenceEntry<Object, Object> getEntry() {
        return null;
    }

    public ValueReference<Object, Object> copyFor(ReferenceQueue<Object> referenceQueue, @Nullable Object value, ReferenceEntry<Object, Object> referenceEntry) {
        return this;
    }

    public boolean isLoading() {
        return false;
    }

    public boolean isActive() {
        return false;
    }

    public Object waitForValue() {
        return null;
    }

    public void notifyNewValue(Object newValue) {
    }
}
