package com.google.common.cache;

import java.util.AbstractCollection;
import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

final class LocalCache$Values extends AbstractCollection<V> {
    private final ConcurrentMap<?, ?> map;
    final /* synthetic */ LocalCache this$0;

    LocalCache$Values(LocalCache localCache, ConcurrentMap<?, ?> map) {
        this.this$0 = localCache;
        this.map = map;
    }

    public int size() {
        return this.map.size();
    }

    public boolean isEmpty() {
        return this.map.isEmpty();
    }

    public void clear() {
        this.map.clear();
    }

    public Iterator<V> iterator() {
        return new LocalCache$ValueIterator(this.this$0);
    }

    public boolean contains(Object o) {
        return this.map.containsValue(o);
    }
}
