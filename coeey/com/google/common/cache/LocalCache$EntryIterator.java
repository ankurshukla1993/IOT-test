package com.google.common.cache;

import java.util.Map.Entry;

final class LocalCache$EntryIterator extends LocalCache$HashIterator<Entry<K, V>> {
    final /* synthetic */ LocalCache this$0;

    LocalCache$EntryIterator(LocalCache localCache) {
        this.this$0 = localCache;
        super(localCache);
    }

    public Entry<K, V> next() {
        return nextEntry();
    }
}
