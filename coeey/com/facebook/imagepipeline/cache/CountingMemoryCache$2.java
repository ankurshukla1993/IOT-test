package com.facebook.imagepipeline.cache;

import com.facebook.common.references.ResourceReleaser;
import com.facebook.imagepipeline.cache.CountingMemoryCache.Entry;

class CountingMemoryCache$2 implements ResourceReleaser<V> {
    final /* synthetic */ CountingMemoryCache this$0;
    final /* synthetic */ Entry val$entry;

    CountingMemoryCache$2(CountingMemoryCache this$0, Entry entry) {
        this.this$0 = this$0;
        this.val$entry = entry;
    }

    public void release(V v) {
        CountingMemoryCache.access$000(this.this$0, this.val$entry);
    }
}
