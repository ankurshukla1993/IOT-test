package com.bumptech.glide.load.engine.cache;

import android.content.Context;
import com.bumptech.glide.load.engine.cache.DiskCache.Factory;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory.CacheDirectoryGetter;
import java.io.File;

public final class ExternalCacheDiskCacheFactory extends DiskLruCacheFactory {

    class C07341 implements CacheDirectoryGetter {
        final /* synthetic */ Context val$context;
        final /* synthetic */ String val$diskCacheName;

        C07341(Context context, String str) {
            this.val$context = context;
            this.val$diskCacheName = str;
        }

        public File getCacheDirectory() {
            File cacheDirectory = this.val$context.getExternalCacheDir();
            if (cacheDirectory == null) {
                return null;
            }
            if (this.val$diskCacheName != null) {
                return new File(cacheDirectory, this.val$diskCacheName);
            }
            return cacheDirectory;
        }
    }

    public ExternalCacheDiskCacheFactory(Context context) {
        this(context, Factory.DEFAULT_DISK_CACHE_DIR, Factory.DEFAULT_DISK_CACHE_SIZE);
    }

    public ExternalCacheDiskCacheFactory(Context context, int diskCacheSize) {
        this(context, Factory.DEFAULT_DISK_CACHE_DIR, diskCacheSize);
    }

    public ExternalCacheDiskCacheFactory(Context context, String diskCacheName, int diskCacheSize) {
        super(new C07341(context, diskCacheName), diskCacheSize);
    }
}
