package com.google.common.cache;

final class LocalCache$ValueIterator extends LocalCache$HashIterator<V> {
    final /* synthetic */ LocalCache this$0;

    LocalCache$ValueIterator(LocalCache localCache) {
        this.this$0 = localCache;
        super(localCache);
    }

    public V next() {
        return nextEntry().getValue();
    }
}
