package com.google.common.cache;

import java.util.Iterator;
import java.util.concurrent.ConcurrentMap;

final class LocalCache$KeySet extends LocalCache$AbstractCacheSet<K> {
    final /* synthetic */ LocalCache this$0;

    LocalCache$KeySet(LocalCache localCache, ConcurrentMap<?, ?> map) {
        this.this$0 = localCache;
        super(localCache, map);
    }

    public Iterator<K> iterator() {
        return new LocalCache$KeyIterator(this.this$0);
    }

    public boolean contains(Object o) {
        return this.map.containsKey(o);
    }

    public boolean remove(Object o) {
        return this.map.remove(o) != null;
    }
}
