package com.facebook.imagepipeline.core;

import android.app.ActivityManager;
import android.content.Context;
import android.graphics.Bitmap.Config;
import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.memory.MemoryTrimmableRegistry;
import com.facebook.common.memory.NoOpMemoryTrimmableRegistry;
import com.facebook.imagepipeline.animated.factory.AnimatedImageFactory;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultBitmapMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.DefaultCacheKeyFactory;
import com.facebook.imagepipeline.cache.DefaultEncodedMemoryCacheParamsSupplier;
import com.facebook.imagepipeline.cache.ImageCacheStatsTracker;
import com.facebook.imagepipeline.cache.MemoryCacheParams;
import com.facebook.imagepipeline.cache.NoOpImageCacheStatsTracker;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.decoder.SimpleProgressiveJpegConfig;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PoolConfig;
import com.facebook.imagepipeline.memory.PoolFactory;
import com.facebook.imagepipeline.producers.HttpUrlConnectionNetworkFetcher;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.ihealth.communication.control.AmProfile;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import javax.annotation.Nullable;

public class ImagePipelineConfig {
    @Nullable
    private final AnimatedImageFactory mAnimatedImageFactory;
    private final Config mBitmapConfig;
    private final Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
    private final CacheKeyFactory mCacheKeyFactory;
    private final Context mContext;
    private final boolean mDecodeMemoryFileEnabled;
    private final boolean mDownsampleEnabled;
    private final Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
    private final ExecutorSupplier mExecutorSupplier;
    private final FileCacheFactory mFileCacheFactory;
    private final ImageCacheStatsTracker mImageCacheStatsTracker;
    @Nullable
    private final ImageDecoder mImageDecoder;
    private final ImagePipelineExperiments mImagePipelineExperiments;
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final DiskCacheConfig mMainDiskCacheConfig;
    private final MemoryTrimmableRegistry mMemoryTrimmableRegistry;
    private final NetworkFetcher mNetworkFetcher;
    @Nullable
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final PoolFactory mPoolFactory;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final Set<RequestListener> mRequestListeners;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private final DiskCacheConfig mSmallImageDiskCacheConfig;

    public static class Builder {
        private AnimatedImageFactory mAnimatedImageFactory;
        private Config mBitmapConfig;
        private Supplier<MemoryCacheParams> mBitmapMemoryCacheParamsSupplier;
        private CacheKeyFactory mCacheKeyFactory;
        private final Context mContext;
        private boolean mDecodeMemoryFileEnabled;
        private boolean mDownsampleEnabled;
        private Supplier<MemoryCacheParams> mEncodedMemoryCacheParamsSupplier;
        private ExecutorSupplier mExecutorSupplier;
        private final com.facebook.imagepipeline.core.ImagePipelineExperiments.Builder mExperimentsBuilder;
        private FileCacheFactory mFileCacheFactory;
        private ImageCacheStatsTracker mImageCacheStatsTracker;
        private ImageDecoder mImageDecoder;
        private Supplier<Boolean> mIsPrefetchEnabledSupplier;
        private DiskCacheConfig mMainDiskCacheConfig;
        private MemoryTrimmableRegistry mMemoryTrimmableRegistry;
        private NetworkFetcher mNetworkFetcher;
        private PlatformBitmapFactory mPlatformBitmapFactory;
        private PoolFactory mPoolFactory;
        private ProgressiveJpegConfig mProgressiveJpegConfig;
        private Set<RequestListener> mRequestListeners;
        private boolean mResizeAndRotateEnabledForNetwork;
        private DiskCacheConfig mSmallImageDiskCacheConfig;

        private Builder(Context context) {
            this.mDownsampleEnabled = false;
            this.mResizeAndRotateEnabledForNetwork = true;
            this.mExperimentsBuilder = new com.facebook.imagepipeline.core.ImagePipelineExperiments.Builder(this);
            this.mContext = (Context) Preconditions.checkNotNull(context);
        }

        public Builder setAnimatedImageFactory(AnimatedImageFactory animatedImageFactory) {
            this.mAnimatedImageFactory = animatedImageFactory;
            return this;
        }

        public Builder setBitmapsConfig(Config config) {
            this.mBitmapConfig = config;
            return this;
        }

        public Builder setBitmapMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> bitmapMemoryCacheParamsSupplier) {
            this.mBitmapMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(bitmapMemoryCacheParamsSupplier);
            return this;
        }

        public Builder setCacheKeyFactory(CacheKeyFactory cacheKeyFactory) {
            this.mCacheKeyFactory = cacheKeyFactory;
            return this;
        }

        public Builder setDecodeMemoryFileEnabled(boolean decodeMemoryFileEnabled) {
            this.mDecodeMemoryFileEnabled = decodeMemoryFileEnabled;
            return this;
        }

        public Builder setFileCacheFactory(FileCacheFactory fileCacheFactory) {
            this.mFileCacheFactory = fileCacheFactory;
            return this;
        }

        @Deprecated
        public Builder setDiskStorageFactory(DiskStorageFactory diskStorageFactory) {
            setFileCacheFactory(new DiskStorageCacheFactory(diskStorageFactory));
            return this;
        }

        public boolean isDownsampleEnabled() {
            return this.mDownsampleEnabled;
        }

        public Builder setDownsampleEnabled(boolean downsampleEnabled) {
            this.mDownsampleEnabled = downsampleEnabled;
            return this;
        }

        public Builder setEncodedMemoryCacheParamsSupplier(Supplier<MemoryCacheParams> encodedMemoryCacheParamsSupplier) {
            this.mEncodedMemoryCacheParamsSupplier = (Supplier) Preconditions.checkNotNull(encodedMemoryCacheParamsSupplier);
            return this;
        }

        public Builder setExecutorSupplier(ExecutorSupplier executorSupplier) {
            this.mExecutorSupplier = executorSupplier;
            return this;
        }

        public Builder setImageCacheStatsTracker(ImageCacheStatsTracker imageCacheStatsTracker) {
            this.mImageCacheStatsTracker = imageCacheStatsTracker;
            return this;
        }

        public Builder setImageDecoder(ImageDecoder imageDecoder) {
            this.mImageDecoder = imageDecoder;
            return this;
        }

        public Builder setIsPrefetchEnabledSupplier(Supplier<Boolean> isPrefetchEnabledSupplier) {
            this.mIsPrefetchEnabledSupplier = isPrefetchEnabledSupplier;
            return this;
        }

        public Builder setMainDiskCacheConfig(DiskCacheConfig mainDiskCacheConfig) {
            this.mMainDiskCacheConfig = mainDiskCacheConfig;
            return this;
        }

        public Builder setMemoryTrimmableRegistry(MemoryTrimmableRegistry memoryTrimmableRegistry) {
            this.mMemoryTrimmableRegistry = memoryTrimmableRegistry;
            return this;
        }

        public Builder setNetworkFetcher(NetworkFetcher networkFetcher) {
            this.mNetworkFetcher = networkFetcher;
            return this;
        }

        public Builder setPlatformBitmapFactory(PlatformBitmapFactory platformBitmapFactory) {
            this.mPlatformBitmapFactory = platformBitmapFactory;
            return this;
        }

        public Builder setPoolFactory(PoolFactory poolFactory) {
            this.mPoolFactory = poolFactory;
            return this;
        }

        public Builder setProgressiveJpegConfig(ProgressiveJpegConfig progressiveJpegConfig) {
            this.mProgressiveJpegConfig = progressiveJpegConfig;
            return this;
        }

        public Builder setRequestListeners(Set<RequestListener> requestListeners) {
            this.mRequestListeners = requestListeners;
            return this;
        }

        public Builder setResizeAndRotateEnabledForNetwork(boolean resizeAndRotateEnabledForNetwork) {
            this.mResizeAndRotateEnabledForNetwork = resizeAndRotateEnabledForNetwork;
            return this;
        }

        public Builder setSmallImageDiskCacheConfig(DiskCacheConfig smallImageDiskCacheConfig) {
            this.mSmallImageDiskCacheConfig = smallImageDiskCacheConfig;
            return this;
        }

        public com.facebook.imagepipeline.core.ImagePipelineExperiments.Builder experiment() {
            return this.mExperimentsBuilder;
        }

        public ImagePipelineConfig build() {
            return new ImagePipelineConfig();
        }
    }

    private ImagePipelineConfig(Builder builder) {
        Supplier defaultBitmapMemoryCacheParamsSupplier;
        Config config;
        CacheKeyFactory instance;
        FileCacheFactory diskStorageCacheFactory;
        ImageCacheStatsTracker instance2;
        DiskCacheConfig defaultMainDiskCacheConfig;
        MemoryTrimmableRegistry instance3;
        NetworkFetcher httpUrlConnectionNetworkFetcher;
        PoolFactory poolFactory;
        ProgressiveJpegConfig simpleProgressiveJpegConfig;
        Set hashSet;
        ExecutorSupplier defaultExecutorSupplier;
        this.mAnimatedImageFactory = builder.mAnimatedImageFactory;
        if (builder.mBitmapMemoryCacheParamsSupplier == null) {
            defaultBitmapMemoryCacheParamsSupplier = new DefaultBitmapMemoryCacheParamsSupplier((ActivityManager) builder.mContext.getSystemService(AmProfile.SYNC_ACTIVITY_DATA_AM));
        } else {
            defaultBitmapMemoryCacheParamsSupplier = builder.mBitmapMemoryCacheParamsSupplier;
        }
        this.mBitmapMemoryCacheParamsSupplier = defaultBitmapMemoryCacheParamsSupplier;
        if (builder.mBitmapConfig == null) {
            config = Config.ARGB_8888;
        } else {
            config = builder.mBitmapConfig;
        }
        this.mBitmapConfig = config;
        if (builder.mCacheKeyFactory == null) {
            instance = DefaultCacheKeyFactory.getInstance();
        } else {
            instance = builder.mCacheKeyFactory;
        }
        this.mCacheKeyFactory = instance;
        this.mContext = (Context) Preconditions.checkNotNull(builder.mContext);
        this.mDecodeMemoryFileEnabled = builder.mDecodeMemoryFileEnabled;
        if (builder.mFileCacheFactory == null) {
            diskStorageCacheFactory = new DiskStorageCacheFactory(new DynamicDefaultDiskStorageFactory());
        } else {
            diskStorageCacheFactory = builder.mFileCacheFactory;
        }
        this.mFileCacheFactory = diskStorageCacheFactory;
        this.mDownsampleEnabled = builder.mDownsampleEnabled;
        if (builder.mEncodedMemoryCacheParamsSupplier == null) {
            defaultBitmapMemoryCacheParamsSupplier = new DefaultEncodedMemoryCacheParamsSupplier();
        } else {
            defaultBitmapMemoryCacheParamsSupplier = builder.mEncodedMemoryCacheParamsSupplier;
        }
        this.mEncodedMemoryCacheParamsSupplier = defaultBitmapMemoryCacheParamsSupplier;
        if (builder.mImageCacheStatsTracker == null) {
            instance2 = NoOpImageCacheStatsTracker.getInstance();
        } else {
            instance2 = builder.mImageCacheStatsTracker;
        }
        this.mImageCacheStatsTracker = instance2;
        this.mImageDecoder = builder.mImageDecoder;
        if (builder.mIsPrefetchEnabledSupplier == null) {
            defaultBitmapMemoryCacheParamsSupplier = new 1(this);
        } else {
            defaultBitmapMemoryCacheParamsSupplier = builder.mIsPrefetchEnabledSupplier;
        }
        this.mIsPrefetchEnabledSupplier = defaultBitmapMemoryCacheParamsSupplier;
        if (builder.mMainDiskCacheConfig == null) {
            defaultMainDiskCacheConfig = getDefaultMainDiskCacheConfig(builder.mContext);
        } else {
            defaultMainDiskCacheConfig = builder.mMainDiskCacheConfig;
        }
        this.mMainDiskCacheConfig = defaultMainDiskCacheConfig;
        if (builder.mMemoryTrimmableRegistry == null) {
            instance3 = NoOpMemoryTrimmableRegistry.getInstance();
        } else {
            instance3 = builder.mMemoryTrimmableRegistry;
        }
        this.mMemoryTrimmableRegistry = instance3;
        if (builder.mNetworkFetcher == null) {
            httpUrlConnectionNetworkFetcher = new HttpUrlConnectionNetworkFetcher();
        } else {
            httpUrlConnectionNetworkFetcher = builder.mNetworkFetcher;
        }
        this.mNetworkFetcher = httpUrlConnectionNetworkFetcher;
        this.mPlatformBitmapFactory = builder.mPlatformBitmapFactory;
        if (builder.mPoolFactory == null) {
            poolFactory = new PoolFactory(PoolConfig.newBuilder().build());
        } else {
            poolFactory = builder.mPoolFactory;
        }
        this.mPoolFactory = poolFactory;
        if (builder.mProgressiveJpegConfig == null) {
            simpleProgressiveJpegConfig = new SimpleProgressiveJpegConfig();
        } else {
            simpleProgressiveJpegConfig = builder.mProgressiveJpegConfig;
        }
        this.mProgressiveJpegConfig = simpleProgressiveJpegConfig;
        if (builder.mRequestListeners == null) {
            hashSet = new HashSet();
        } else {
            hashSet = builder.mRequestListeners;
        }
        this.mRequestListeners = hashSet;
        this.mResizeAndRotateEnabledForNetwork = builder.mResizeAndRotateEnabledForNetwork;
        if (builder.mSmallImageDiskCacheConfig == null) {
            defaultMainDiskCacheConfig = this.mMainDiskCacheConfig;
        } else {
            defaultMainDiskCacheConfig = builder.mSmallImageDiskCacheConfig;
        }
        this.mSmallImageDiskCacheConfig = defaultMainDiskCacheConfig;
        int numCpuBoundThreads = this.mPoolFactory.getFlexByteArrayPoolMaxNumThreads();
        if (builder.mExecutorSupplier == null) {
            defaultExecutorSupplier = new DefaultExecutorSupplier(numCpuBoundThreads);
        } else {
            defaultExecutorSupplier = builder.mExecutorSupplier;
        }
        this.mExecutorSupplier = defaultExecutorSupplier;
        this.mImagePipelineExperiments = builder.mExperimentsBuilder.build();
    }

    private static DiskCacheConfig getDefaultMainDiskCacheConfig(Context context) {
        return DiskCacheConfig.newBuilder(context).build();
    }

    @Nullable
    public AnimatedImageFactory getAnimatedImageFactory() {
        return this.mAnimatedImageFactory;
    }

    public Config getBitmapConfig() {
        return this.mBitmapConfig;
    }

    public Supplier<MemoryCacheParams> getBitmapMemoryCacheParamsSupplier() {
        return this.mBitmapMemoryCacheParamsSupplier;
    }

    public CacheKeyFactory getCacheKeyFactory() {
        return this.mCacheKeyFactory;
    }

    public Context getContext() {
        return this.mContext;
    }

    public boolean isDecodeFileDescriptorEnabled() {
        return this.mImagePipelineExperiments.isDecodeFileDescriptorEnabled();
    }

    public boolean isDecodeMemoryFileEnabled() {
        return this.mDecodeMemoryFileEnabled;
    }

    public FileCacheFactory getFileCacheFactory() {
        return this.mFileCacheFactory;
    }

    public boolean isDownsampleEnabled() {
        return this.mDownsampleEnabled;
    }

    public boolean isWebpSupportEnabled() {
        return this.mImagePipelineExperiments.isWebpSupportEnabled();
    }

    public Supplier<MemoryCacheParams> getEncodedMemoryCacheParamsSupplier() {
        return this.mEncodedMemoryCacheParamsSupplier;
    }

    public ExecutorSupplier getExecutorSupplier() {
        return this.mExecutorSupplier;
    }

    @Deprecated
    public int getForceSmallCacheThresholdBytes() {
        return this.mImagePipelineExperiments.getForceSmallCacheThresholdBytes();
    }

    public ImageCacheStatsTracker getImageCacheStatsTracker() {
        return this.mImageCacheStatsTracker;
    }

    @Nullable
    public ImageDecoder getImageDecoder() {
        return this.mImageDecoder;
    }

    public Supplier<Boolean> getIsPrefetchEnabledSupplier() {
        return this.mIsPrefetchEnabledSupplier;
    }

    public DiskCacheConfig getMainDiskCacheConfig() {
        return this.mMainDiskCacheConfig;
    }

    public MemoryTrimmableRegistry getMemoryTrimmableRegistry() {
        return this.mMemoryTrimmableRegistry;
    }

    public NetworkFetcher getNetworkFetcher() {
        return this.mNetworkFetcher;
    }

    @Nullable
    public PlatformBitmapFactory getPlatformBitmapFactory() {
        return this.mPlatformBitmapFactory;
    }

    public PoolFactory getPoolFactory() {
        return this.mPoolFactory;
    }

    public ProgressiveJpegConfig getProgressiveJpegConfig() {
        return this.mProgressiveJpegConfig;
    }

    public Set<RequestListener> getRequestListeners() {
        return Collections.unmodifiableSet(this.mRequestListeners);
    }

    public boolean isResizeAndRotateEnabledForNetwork() {
        return this.mResizeAndRotateEnabledForNetwork;
    }

    public DiskCacheConfig getSmallImageDiskCacheConfig() {
        return this.mSmallImageDiskCacheConfig;
    }

    public ImagePipelineExperiments getExperiments() {
        return this.mImagePipelineExperiments;
    }

    public static Builder newBuilder(Context context) {
        return new Builder(context);
    }
}
