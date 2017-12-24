package com.bumptech.glide;

import com.bumptech.glide.load.engine.cache.DiskCache;
import com.bumptech.glide.load.engine.cache.DiskCache.Factory;

class GlideBuilder$1 implements Factory {
    final /* synthetic */ GlideBuilder this$0;
    final /* synthetic */ DiskCache val$diskCache;

    GlideBuilder$1(GlideBuilder glideBuilder, DiskCache diskCache) {
        this.this$0 = glideBuilder;
        this.val$diskCache = diskCache;
    }

    public DiskCache build() {
        return this.val$diskCache;
    }
}
