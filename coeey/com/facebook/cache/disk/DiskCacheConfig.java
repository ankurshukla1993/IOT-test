package com.facebook.cache.disk;

import android.content.Context;
import com.facebook.cache.common.CacheErrorLogger;
import com.facebook.cache.common.CacheEventListener;
import com.facebook.cache.common.NoOpCacheErrorLogger;
import com.facebook.cache.common.NoOpCacheEventListener;
import com.facebook.common.disk.DiskTrimmableRegistry;
import com.facebook.common.disk.NoOpDiskTrimmableRegistry;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import java.io.File;
import javax.annotation.Nullable;

public class DiskCacheConfig {
    private final String mBaseDirectoryName;
    private final Supplier<File> mBaseDirectoryPathSupplier;
    private final CacheErrorLogger mCacheErrorLogger;
    private final CacheEventListener mCacheEventListener;
    private final long mDefaultSizeLimit;
    private final DiskTrimmableRegistry mDiskTrimmableRegistry;
    private final EntryEvictionComparatorSupplier mEntryEvictionComparatorSupplier;
    private final long mLowDiskSpaceSizeLimit;
    private final long mMinimumSizeLimit;
    private final int mVersion;

    private DiskCacheConfig(Builder builder) {
        CacheErrorLogger instance;
        CacheEventListener instance2;
        DiskTrimmableRegistry instance3;
        this.mVersion = Builder.access$000(builder);
        this.mBaseDirectoryName = (String) Preconditions.checkNotNull(Builder.access$100(builder));
        this.mBaseDirectoryPathSupplier = (Supplier) Preconditions.checkNotNull(Builder.access$200(builder));
        this.mDefaultSizeLimit = Builder.access$300(builder);
        this.mLowDiskSpaceSizeLimit = Builder.access$400(builder);
        this.mMinimumSizeLimit = Builder.access$500(builder);
        this.mEntryEvictionComparatorSupplier = (EntryEvictionComparatorSupplier) Preconditions.checkNotNull(Builder.access$600(builder));
        if (Builder.access$700(builder) == null) {
            instance = NoOpCacheErrorLogger.getInstance();
        } else {
            instance = Builder.access$700(builder);
        }
        this.mCacheErrorLogger = instance;
        if (Builder.access$800(builder) == null) {
            instance2 = NoOpCacheEventListener.getInstance();
        } else {
            instance2 = Builder.access$800(builder);
        }
        this.mCacheEventListener = instance2;
        if (Builder.access$900(builder) == null) {
            instance3 = NoOpDiskTrimmableRegistry.getInstance();
        } else {
            instance3 = Builder.access$900(builder);
        }
        this.mDiskTrimmableRegistry = instance3;
    }

    public int getVersion() {
        return this.mVersion;
    }

    public String getBaseDirectoryName() {
        return this.mBaseDirectoryName;
    }

    public Supplier<File> getBaseDirectoryPathSupplier() {
        return this.mBaseDirectoryPathSupplier;
    }

    public long getDefaultSizeLimit() {
        return this.mDefaultSizeLimit;
    }

    public long getLowDiskSpaceSizeLimit() {
        return this.mLowDiskSpaceSizeLimit;
    }

    public long getMinimumSizeLimit() {
        return this.mMinimumSizeLimit;
    }

    public EntryEvictionComparatorSupplier getEntryEvictionComparatorSupplier() {
        return this.mEntryEvictionComparatorSupplier;
    }

    public CacheErrorLogger getCacheErrorLogger() {
        return this.mCacheErrorLogger;
    }

    public CacheEventListener getCacheEventListener() {
        return this.mCacheEventListener;
    }

    public DiskTrimmableRegistry getDiskTrimmableRegistry() {
        return this.mDiskTrimmableRegistry;
    }

    public static Builder newBuilder(@Nullable Context context) {
        return new Builder(context, null);
    }
}
