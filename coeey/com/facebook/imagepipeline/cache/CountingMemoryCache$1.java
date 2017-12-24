package com.facebook.imagepipeline.cache;

import com.facebook.imagepipeline.cache.CountingMemoryCache.Entry;

class CountingMemoryCache$1 implements ValueDescriptor<Entry<K, V>> {
    final /* synthetic */ CountingMemoryCache this$0;
    final /* synthetic */ ValueDescriptor val$evictableValueDescriptor;

    CountingMemoryCache$1(CountingMemoryCache this$0, ValueDescriptor valueDescriptor) {
        this.this$0 = this$0;
        this.val$evictableValueDescriptor = valueDescriptor;
    }

    public int getSizeInBytes(Entry<K, V> entry) {
        return this.val$evictableValueDescriptor.getSizeInBytes(entry.valueRef.get());
    }
}
