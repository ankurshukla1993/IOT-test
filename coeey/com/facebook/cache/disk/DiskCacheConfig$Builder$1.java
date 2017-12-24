package com.facebook.cache.disk;

import com.facebook.cache.disk.DiskCacheConfig.Builder;
import com.facebook.common.internal.Supplier;
import java.io.File;

class DiskCacheConfig$Builder$1 implements Supplier<File> {
    final /* synthetic */ Builder this$0;

    DiskCacheConfig$Builder$1(Builder this$0) {
        this.this$0 = this$0;
    }

    public File get() {
        return Builder.access$1100(this.this$0).getApplicationContext().getCacheDir();
    }
}
