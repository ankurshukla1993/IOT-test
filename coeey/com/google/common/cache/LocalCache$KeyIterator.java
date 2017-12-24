package com.google.common.cache;

final class LocalCache$KeyIterator extends LocalCache$HashIterator<K> {
    final /* synthetic */ LocalCache this$0;

    LocalCache$KeyIterator(LocalCache localCache) {
        this.this$0 = localCache;
        super(localCache);
    }

    public K next() {
        return nextEntry().getKey();
    }
}
