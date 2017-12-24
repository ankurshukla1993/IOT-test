package com.google.common.cache;

import java.util.Iterator;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

final class LocalCache$EntrySet extends LocalCache$AbstractCacheSet<Entry<K, V>> {
    final /* synthetic */ LocalCache this$0;

    LocalCache$EntrySet(LocalCache localCache, ConcurrentMap<?, ?> map) {
        this.this$0 = localCache;
        super(localCache, map);
    }

    public Iterator<Entry<K, V>> iterator() {
        return new LocalCache$EntryIterator(this.this$0);
    }

    public boolean contains(Object o) {
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry<?, ?> e = (Entry) o;
        Object key = e.getKey();
        if (key == null) {
            return false;
        }
        V v = this.this$0.get(key);
        if (v == null || !this.this$0.valueEquivalence.equivalent(e.getValue(), v)) {
            return false;
        }
        return true;
    }

    public boolean remove(Object o) {
        if (!(o instanceof Entry)) {
            return false;
        }
        Entry<?, ?> e = (Entry) o;
        Object key = e.getKey();
        if (key == null || !this.this$0.remove(key, e.getValue())) {
            return false;
        }
        return true;
    }
}
