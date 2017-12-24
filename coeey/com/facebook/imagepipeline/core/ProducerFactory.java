package com.facebook.imagepipeline.core;

import android.content.ContentResolver;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.bitmaps.PlatformBitmapFactory;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.decoder.ImageDecoder;
import com.facebook.imagepipeline.decoder.ProgressiveJpegConfig;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.producers.AddImageTransformMetaDataProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheGetProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.BitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.BranchOnSeparateImagesProducer;
import com.facebook.imagepipeline.producers.DataFetchProducer;
import com.facebook.imagepipeline.producers.DecodeProducer;
import com.facebook.imagepipeline.producers.DiskCacheProducer;
import com.facebook.imagepipeline.producers.EncodedCacheKeyMultiplexProducer;
import com.facebook.imagepipeline.producers.EncodedMemoryCacheProducer;
import com.facebook.imagepipeline.producers.LocalAssetFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriFetchProducer;
import com.facebook.imagepipeline.producers.LocalContentUriThumbnailFetchProducer;
import com.facebook.imagepipeline.producers.LocalExifThumbnailProducer;
import com.facebook.imagepipeline.producers.LocalFileFetchProducer;
import com.facebook.imagepipeline.producers.LocalResourceFetchProducer;
import com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer;
import com.facebook.imagepipeline.producers.NetworkFetchProducer;
import com.facebook.imagepipeline.producers.NetworkFetcher;
import com.facebook.imagepipeline.producers.NullProducer;
import com.facebook.imagepipeline.producers.PostprocessedBitmapMemoryCacheProducer;
import com.facebook.imagepipeline.producers.PostprocessorProducer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.ResizeAndRotateProducer;
import com.facebook.imagepipeline.producers.SwallowResultProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducer;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.producers.ThrottlingProducer;
import com.facebook.imagepipeline.producers.ThumbnailBranchProducer;
import com.facebook.imagepipeline.producers.ThumbnailProducer;
import com.facebook.imagepipeline.producers.WebpTranscodeProducer;

public class ProducerFactory {
    private AssetManager mAssetManager;
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final ByteArrayPool mByteArrayPool;
    private final CacheKeyFactory mCacheKeyFactory;
    private ContentResolver mContentResolver;
    private final boolean mDecodeFileDescriptorEnabled;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final boolean mDownsampleEnabled;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private final ExecutorSupplier mExecutorSupplier;
    private final int mForceSmallCacheThresholdBytes;
    private final ImageDecoder mImageDecoder;
    private final PlatformBitmapFactory mPlatformBitmapFactory;
    private final PooledByteBufferFactory mPooledByteBufferFactory;
    private final ProgressiveJpegConfig mProgressiveJpegConfig;
    private final boolean mResizeAndRotateEnabledForNetwork;
    private Resources mResources;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    public ProducerFactory(Context context, ByteArrayPool byteArrayPool, ImageDecoder imageDecoder, ProgressiveJpegConfig progressiveJpegConfig, boolean downsampleEnabled, boolean resizeAndRotateEnabledForNetwork, ExecutorSupplier executorSupplier, PooledByteBufferFactory pooledByteBufferFactory, MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, PlatformBitmapFactory platformBitmapFactory, boolean decodeFileDescriptorEnabled, int forceSmallCacheThresholdBytes) {
        this.mForceSmallCacheThresholdBytes = forceSmallCacheThresholdBytes;
        this.mContentResolver = context.getApplicationContext().getContentResolver();
        this.mResources = context.getApplicationContext().getResources();
        this.mAssetManager = context.getApplicationContext().getAssets();
        this.mByteArrayPool = byteArrayPool;
        this.mImageDecoder = imageDecoder;
        this.mProgressiveJpegConfig = progressiveJpegConfig;
        this.mDownsampleEnabled = downsampleEnabled;
        this.mResizeAndRotateEnabledForNetwork = resizeAndRotateEnabledForNetwork;
        this.mExecutorSupplier = executorSupplier;
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mBitmapMemoryCache = bitmapMemoryCache;
        this.mEncodedMemoryCache = encodedMemoryCache;
        this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mPlatformBitmapFactory = platformBitmapFactory;
        this.mDecodeFileDescriptorEnabled = decodeFileDescriptorEnabled;
    }

    public static AddImageTransformMetaDataProducer newAddImageTransformMetaDataProducer(Producer<EncodedImage> inputProducer) {
        return new AddImageTransformMetaDataProducer(inputProducer);
    }

    public BitmapMemoryCacheGetProducer newBitmapMemoryCacheGetProducer(Producer<CloseableReference<CloseableImage>> inputProducer) {
        return new BitmapMemoryCacheGetProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, inputProducer);
    }

    public BitmapMemoryCacheKeyMultiplexProducer newBitmapMemoryCacheKeyMultiplexProducer(Producer<CloseableReference<CloseableImage>> inputProducer) {
        return new BitmapMemoryCacheKeyMultiplexProducer(this.mCacheKeyFactory, inputProducer);
    }

    public BitmapMemoryCacheProducer newBitmapMemoryCacheProducer(Producer<CloseableReference<CloseableImage>> inputProducer) {
        return new BitmapMemoryCacheProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, inputProducer);
    }

    public static BranchOnSeparateImagesProducer newBranchOnSeparateImagesProducer(Producer<EncodedImage> inputProducer1, Producer<EncodedImage> inputProducer2) {
        return new BranchOnSeparateImagesProducer(inputProducer1, inputProducer2);
    }

    public DataFetchProducer newDataFetchProducer() {
        return new DataFetchProducer(this.mPooledByteBufferFactory, this.mDecodeFileDescriptorEnabled);
    }

    public DecodeProducer newDecodeProducer(Producer<EncodedImage> inputProducer) {
        return new DecodeProducer(this.mByteArrayPool, this.mExecutorSupplier.forDecode(), this.mImageDecoder, this.mProgressiveJpegConfig, this.mDownsampleEnabled, this.mResizeAndRotateEnabledForNetwork, inputProducer);
    }

    public DiskCacheProducer newDiskCacheProducer(Producer<EncodedImage> inputProducer) {
        return new DiskCacheProducer(this.mDefaultBufferedDiskCache, this.mSmallImageBufferedDiskCache, this.mCacheKeyFactory, inputProducer, this.mForceSmallCacheThresholdBytes);
    }

    public EncodedCacheKeyMultiplexProducer newEncodedCacheKeyMultiplexProducer(Producer<EncodedImage> inputProducer) {
        return new EncodedCacheKeyMultiplexProducer(this.mCacheKeyFactory, inputProducer);
    }

    public EncodedMemoryCacheProducer newEncodedMemoryCacheProducer(Producer<EncodedImage> inputProducer) {
        return new EncodedMemoryCacheProducer(this.mEncodedMemoryCache, this.mCacheKeyFactory, inputProducer);
    }

    public LocalAssetFetchProducer newLocalAssetFetchProducer() {
        return new LocalAssetFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mAssetManager, this.mDecodeFileDescriptorEnabled);
    }

    public LocalContentUriFetchProducer newLocalContentUriFetchProducer() {
        return new LocalContentUriFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver, this.mDecodeFileDescriptorEnabled);
    }

    public LocalContentUriThumbnailFetchProducer newLocalContentUriThumbnailFetchProducer() {
        return new LocalContentUriThumbnailFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver, this.mDecodeFileDescriptorEnabled);
    }

    public LocalExifThumbnailProducer newLocalExifThumbnailProducer() {
        return new LocalExifThumbnailProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mContentResolver);
    }

    public ThumbnailBranchProducer newThumbnailBranchProducer(ThumbnailProducer<EncodedImage>[] thumbnailProducers) {
        return new ThumbnailBranchProducer(thumbnailProducers);
    }

    public LocalFileFetchProducer newLocalFileFetchProducer() {
        return new LocalFileFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mDecodeFileDescriptorEnabled);
    }

    public LocalResourceFetchProducer newLocalResourceFetchProducer() {
        return new LocalResourceFetchProducer(this.mExecutorSupplier.forLocalStorageRead(), this.mPooledByteBufferFactory, this.mResources, this.mDecodeFileDescriptorEnabled);
    }

    public LocalVideoThumbnailProducer newLocalVideoThumbnailProducer() {
        return new LocalVideoThumbnailProducer(this.mExecutorSupplier.forLocalStorageRead());
    }

    public NetworkFetchProducer newNetworkFetchProducer(NetworkFetcher networkFetcher) {
        return new NetworkFetchProducer(this.mPooledByteBufferFactory, this.mByteArrayPool, networkFetcher);
    }

    public static <T> NullProducer<T> newNullProducer() {
        return new NullProducer();
    }

    public PostprocessedBitmapMemoryCacheProducer newPostprocessorBitmapMemoryCacheProducer(Producer<CloseableReference<CloseableImage>> inputProducer) {
        return new PostprocessedBitmapMemoryCacheProducer(this.mBitmapMemoryCache, this.mCacheKeyFactory, inputProducer);
    }

    public PostprocessorProducer newPostprocessorProducer(Producer<CloseableReference<CloseableImage>> inputProducer) {
        return new PostprocessorProducer(inputProducer, this.mPlatformBitmapFactory, this.mExecutorSupplier.forBackgroundTasks());
    }

    public ResizeAndRotateProducer newResizeAndRotateProducer(Producer<EncodedImage> inputProducer) {
        return new ResizeAndRotateProducer(this.mExecutorSupplier.forBackgroundTasks(), this.mPooledByteBufferFactory, inputProducer);
    }

    public static <T> SwallowResultProducer<T> newSwallowResultProducer(Producer<T> inputProducer) {
        return new SwallowResultProducer(inputProducer);
    }

    public <T> ThreadHandoffProducer<T> newBackgroundThreadHandoffProducer(Producer<T> inputProducer, ThreadHandoffProducerQueue inputThreadHandoffProducerQueue) {
        return new ThreadHandoffProducer(inputProducer, inputThreadHandoffProducerQueue);
    }

    public <T> ThrottlingProducer<T> newThrottlingProducer(int maxSimultaneousRequests, Producer<T> inputProducer) {
        return new ThrottlingProducer(maxSimultaneousRequests, this.mExecutorSupplier.forLightweightBackgroundTasks(), inputProducer);
    }

    public WebpTranscodeProducer newWebpTranscodeProducer(Producer<EncodedImage> inputProducer) {
        return new WebpTranscodeProducer(this.mExecutorSupplier.forBackgroundTasks(), this.mPooledByteBufferFactory, inputProducer);
    }
}
