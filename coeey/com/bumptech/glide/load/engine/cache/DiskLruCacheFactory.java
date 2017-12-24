package com.bumptech.glide.load.engine.cache;

import com.bumptech.glide.load.engine.cache.DiskCache.Factory;
import java.io.File;

public class DiskLruCacheFactory implements Factory {
    private final CacheDirectoryGetter cacheDirectoryGetter;
    private final int diskCacheSize;

    public interface CacheDirectoryGetter {
        File getCacheDirectory();
    }

    class C07321 implements CacheDirectoryGetter {
        final /* synthetic */ String val$diskCacheFolder;

        C07321(String str) {
            this.val$diskCacheFolder = str;
        }

        public File getCacheDirectory() {
            return new File(this.val$diskCacheFolder);
        }
    }

    class C07332 implements CacheDirectoryGetter {
        final /* synthetic */ String val$diskCacheFolder;
        final /* synthetic */ String val$diskCacheName;

        C07332(String str, String str2) {
            this.val$diskCacheFolder = str;
            this.val$diskCacheName = str2;
        }

        public File getCacheDirectory() {
            return new File(this.val$diskCacheFolder, this.val$diskCacheName);
        }
    }

    public DiskLruCacheFactory(String diskCacheFolder, int diskCacheSize) {
        this(new C07321(diskCacheFolder), diskCacheSize);
    }

    public DiskLruCacheFactory(String diskCacheFolder, String diskCacheName, int diskCacheSize) {
        this(new C07332(diskCacheFolder, diskCacheName), diskCacheSize);
    }

    public DiskLruCacheFactory(CacheDirectoryGetter cacheDirectoryGetter, int diskCacheSize) {
        this.diskCacheSize = diskCacheSize;
        this.cacheDirectoryGetter = cacheDirectoryGetter;
    }

    public DiskCache build() {
        File cacheDir = this.cacheDirectoryGetter.getCacheDirectory();
        if (cacheDir == null) {
            return null;
        }
        if (cacheDir.mkdirs() || (cacheDir.exists() && cacheDir.isDirectory())) {
            return DiskLruCacheWrapper.get(cacheDir, this.diskCacheSize);
        }
        return null;
    }
}
