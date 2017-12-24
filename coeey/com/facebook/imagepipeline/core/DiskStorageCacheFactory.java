package com.facebook.imagepipeline.core;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.cache.disk.DiskStorage;
import com.facebook.cache.disk.DiskStorageCache;
import com.facebook.cache.disk.DiskStorageCache.Params;
import com.facebook.cache.disk.FileCache;

public class DiskStorageCacheFactory implements FileCacheFactory {
    private DiskStorageFactory mDiskStorageFactory;

    public DiskStorageCacheFactory(DiskStorageFactory diskStorageFactory) {
        this.mDiskStorageFactory = diskStorageFactory;
    }

    public static DiskStorageCache buildDiskStorageCache(DiskCacheConfig diskCacheConfig, DiskStorage diskStorage) {
        return new DiskStorageCache(diskStorage, diskCacheConfig.getEntryEvictionComparatorSupplier(), new Params(diskCacheConfig.getMinimumSizeLimit(), diskCacheConfig.getLowDiskSpaceSizeLimit(), diskCacheConfig.getDefaultSizeLimit()), diskCacheConfig.getCacheEventListener(), diskCacheConfig.getCacheErrorLogger(), diskCacheConfig.getDiskTrimmableRegistry());
    }

    public FileCache get(DiskCacheConfig diskCacheConfig) {
        return buildDiskStorageCache(diskCacheConfig, this.mDiskStorageFactory.get(diskCacheConfig));
    }
}
