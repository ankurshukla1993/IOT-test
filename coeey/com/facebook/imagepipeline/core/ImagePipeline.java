package com.facebook.imagepipeline.core;

import android.net.Uri;
import bolts.Continuation;
import bolts.Task;
import com.RNFetchBlob.RNFetchBlobConst;
import com.android.internal.util.Predicate;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.Objects;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.Supplier;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.datasource.DataSource;
import com.facebook.datasource.DataSources;
import com.facebook.datasource.SimpleDataSource;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.common.Priority;
import com.facebook.imagepipeline.datasource.CloseableProducerToDataSourceAdapter;
import com.facebook.imagepipeline.datasource.ProducerToDataSourceAdapter;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.listener.ForwardingRequestListener;
import com.facebook.imagepipeline.listener.RequestListener;
import com.facebook.imagepipeline.memory.PooledByteBuffer;
import com.facebook.imagepipeline.producers.Producer;
import com.facebook.imagepipeline.producers.ProducerListener;
import com.facebook.imagepipeline.producers.SettableProducerContext;
import com.facebook.imagepipeline.producers.ThreadHandoffProducerQueue;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.imagepipeline.request.ImageRequestBuilder;
import java.util.Set;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.concurrent.ThreadSafe;

@ThreadSafe
public class ImagePipeline {
    private static final CancellationException PREFETCH_EXCEPTION = new CancellationException("Prefetching is not enabled");
    private final MemoryCache<CacheKey, CloseableImage> mBitmapMemoryCache;
    private final CacheKeyFactory mCacheKeyFactory;
    private final MemoryCache<CacheKey, PooledByteBuffer> mEncodedMemoryCache;
    private AtomicLong mIdCounter = new AtomicLong();
    private final Supplier<Boolean> mIsPrefetchEnabledSupplier;
    private final BufferedDiskCache mMainBufferedDiskCache;
    private final ProducerSequenceFactory mProducerSequenceFactory;
    private final RequestListener mRequestListener;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    class C11603 implements Predicate<CacheKey> {
        C11603() {
        }

        public boolean apply(CacheKey key) {
            return true;
        }
    }

    public ImagePipeline(ProducerSequenceFactory producerSequenceFactory, Set<RequestListener> requestListeners, Supplier<Boolean> isPrefetchEnabledSupplier, MemoryCache<CacheKey, CloseableImage> bitmapMemoryCache, MemoryCache<CacheKey, PooledByteBuffer> encodedMemoryCache, BufferedDiskCache mainBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, ThreadHandoffProducerQueue threadHandoffProducerQueue) {
        this.mProducerSequenceFactory = producerSequenceFactory;
        this.mRequestListener = new ForwardingRequestListener(requestListeners);
        this.mIsPrefetchEnabledSupplier = isPrefetchEnabledSupplier;
        this.mBitmapMemoryCache = bitmapMemoryCache;
        this.mEncodedMemoryCache = encodedMemoryCache;
        this.mMainBufferedDiskCache = mainBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mThreadHandoffProducerQueue = threadHandoffProducerQueue;
    }

    private String generateUniqueFutureId() {
        return String.valueOf(this.mIdCounter.getAndIncrement());
    }

    public Supplier<DataSource<CloseableReference<CloseableImage>>> getDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext, final boolean bitmapCacheOnly) {
        return new Supplier<DataSource<CloseableReference<CloseableImage>>>() {
            public DataSource<CloseableReference<CloseableImage>> get() {
                if (bitmapCacheOnly) {
                    return ImagePipeline.this.fetchImageFromBitmapCache(imageRequest, callerContext);
                }
                return ImagePipeline.this.fetchDecodedImage(imageRequest, callerContext);
            }

            public String toString() {
                return Objects.toStringHelper(this).add(RNFetchBlobConst.DATA_ENCODE_URI, imageRequest.getSourceUri()).toString();
            }
        };
    }

    public Supplier<DataSource<CloseableReference<PooledByteBuffer>>> getEncodedImageDataSourceSupplier(final ImageRequest imageRequest, final Object callerContext) {
        return new Supplier<DataSource<CloseableReference<PooledByteBuffer>>>() {
            public DataSource<CloseableReference<PooledByteBuffer>> get() {
                return ImagePipeline.this.fetchEncodedImage(imageRequest, callerContext);
            }

            public String toString() {
                return Objects.toStringHelper(this).add(RNFetchBlobConst.DATA_ENCODE_URI, imageRequest.getSourceUri()).toString();
            }
        };
    }

    public DataSource<CloseableReference<CloseableImage>> fetchImageFromBitmapCache(ImageRequest imageRequest, Object callerContext) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, ImageRequest$RequestLevel.BITMAP_MEMORY_CACHE, callerContext);
        } catch (Exception exception) {
            return DataSources.immediateFailedDataSource(exception);
        }
    }

    public DataSource<CloseableReference<CloseableImage>> fetchDecodedImage(ImageRequest imageRequest, Object callerContext) {
        try {
            return submitFetchRequest(this.mProducerSequenceFactory.getDecodedImageProducerSequence(imageRequest), imageRequest, ImageRequest$RequestLevel.FULL_FETCH, callerContext);
        } catch (Exception exception) {
            return DataSources.immediateFailedDataSource(exception);
        }
    }

    public DataSource<CloseableReference<PooledByteBuffer>> fetchEncodedImage(ImageRequest imageRequest, Object callerContext) {
        Preconditions.checkNotNull(imageRequest.getSourceUri());
        try {
            Producer<CloseableReference<PooledByteBuffer>> producerSequence = this.mProducerSequenceFactory.getEncodedImageProducerSequence(imageRequest);
            if (imageRequest.getResizeOptions() != null) {
                imageRequest = ImageRequestBuilder.fromRequest(imageRequest).setResizeOptions(null).build();
            }
            return submitFetchRequest(producerSequence, imageRequest, ImageRequest$RequestLevel.FULL_FETCH, callerContext);
        } catch (Exception exception) {
            return DataSources.immediateFailedDataSource(exception);
        }
    }

    public DataSource<Void> prefetchToBitmapCache(ImageRequest imageRequest, Object callerContext) {
        if (!((Boolean) this.mIsPrefetchEnabledSupplier.get()).booleanValue()) {
            return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
        }
        try {
            return submitPrefetchRequest(this.mProducerSequenceFactory.getDecodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest$RequestLevel.FULL_FETCH, callerContext, Priority.LOW);
        } catch (Exception exception) {
            return DataSources.immediateFailedDataSource(exception);
        }
    }

    public DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext) {
        return prefetchToDiskCache(imageRequest, callerContext, Priority.LOW);
    }

    public DataSource<Void> prefetchToDiskCache(ImageRequest imageRequest, Object callerContext, Priority priority) {
        if (!((Boolean) this.mIsPrefetchEnabledSupplier.get()).booleanValue()) {
            return DataSources.immediateFailedDataSource(PREFETCH_EXCEPTION);
        }
        try {
            return submitPrefetchRequest(this.mProducerSequenceFactory.getEncodedImagePrefetchProducerSequence(imageRequest), imageRequest, ImageRequest$RequestLevel.FULL_FETCH, callerContext, priority);
        } catch (Exception exception) {
            return DataSources.immediateFailedDataSource(exception);
        }
    }

    public void evictFromMemoryCache(Uri uri) {
        Predicate<CacheKey> predicate = predicateForUri(uri);
        this.mBitmapMemoryCache.removeAll(predicate);
        this.mEncodedMemoryCache.removeAll(predicate);
    }

    public void evictFromDiskCache(Uri uri) {
        evictFromDiskCache(ImageRequest.fromUri(uri));
    }

    public void evictFromDiskCache(ImageRequest imageRequest) {
        CacheKey cacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        this.mMainBufferedDiskCache.remove(cacheKey);
        this.mSmallImageBufferedDiskCache.remove(cacheKey);
    }

    public void evictFromCache(Uri uri) {
        evictFromMemoryCache(uri);
        evictFromDiskCache(uri);
    }

    public void clearMemoryCaches() {
        Predicate<CacheKey> allPredicate = new C11603();
        this.mBitmapMemoryCache.removeAll(allPredicate);
        this.mEncodedMemoryCache.removeAll(allPredicate);
    }

    public void clearDiskCaches() {
        this.mMainBufferedDiskCache.clearAll();
        this.mSmallImageBufferedDiskCache.clearAll();
    }

    public void clearCaches() {
        clearMemoryCaches();
        clearDiskCaches();
    }

    public boolean isInBitmapMemoryCache(Uri uri) {
        if (uri == null) {
            return false;
        }
        return this.mBitmapMemoryCache.contains(predicateForUri(uri));
    }

    public boolean isInBitmapMemoryCache(ImageRequest imageRequest) {
        if (imageRequest == null) {
            return false;
        }
        CloseableReference<CloseableImage> ref = this.mBitmapMemoryCache.get(this.mCacheKeyFactory.getBitmapCacheKey(imageRequest, null));
        try {
            boolean isValid = CloseableReference.isValid(ref);
            return isValid;
        } finally {
            CloseableReference.closeSafely(ref);
        }
    }

    public boolean isInDiskCacheSync(Uri uri) {
        return isInDiskCacheSync(ImageRequest.fromUri(uri));
    }

    public boolean isInDiskCacheSync(ImageRequest imageRequest) {
        return this.mMainBufferedDiskCache.diskCheckSync(this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null));
    }

    public DataSource<Boolean> isInDiskCache(Uri uri) {
        return isInDiskCache(ImageRequest.fromUri(uri));
    }

    public DataSource<Boolean> isInDiskCache(ImageRequest imageRequest) {
        final CacheKey cacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, null);
        final SimpleDataSource<Boolean> dataSource = SimpleDataSource.create();
        this.mMainBufferedDiskCache.contains(cacheKey).continueWithTask(new Continuation<Boolean, Task<Boolean>>() {
            public Task<Boolean> then(Task<Boolean> task) throws Exception {
                if (task.isCancelled() || task.isFaulted() || !((Boolean) task.getResult()).booleanValue()) {
                    return ImagePipeline.this.mSmallImageBufferedDiskCache.contains(cacheKey);
                }
                return Task.forResult(Boolean.valueOf(true));
            }
        }).continueWith(new Continuation<Boolean, Void>() {
            public Void then(Task<Boolean> task) throws Exception {
                SimpleDataSource simpleDataSource = dataSource;
                boolean z = (task.isCancelled() || task.isFaulted() || !((Boolean) task.getResult()).booleanValue()) ? false : true;
                simpleDataSource.setResult(Boolean.valueOf(z));
                return null;
            }
        });
        return dataSource;
    }

    private <T> DataSource<CloseableReference<T>> submitFetchRequest(Producer<CloseableReference<T>> producerSequence, ImageRequest imageRequest, ImageRequest$RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext) {
        boolean z = false;
        try {
            ImageRequest$RequestLevel lowestPermittedRequestLevel = ImageRequest$RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit);
            String generateUniqueFutureId = generateUniqueFutureId();
            ProducerListener producerListener = this.mRequestListener;
            if (imageRequest.getProgressiveRenderingEnabled() || !UriUtil.isNetworkUri(imageRequest.getSourceUri())) {
                z = true;
            }
            return CloseableProducerToDataSourceAdapter.create(producerSequence, new SettableProducerContext(imageRequest, generateUniqueFutureId, producerListener, callerContext, lowestPermittedRequestLevel, false, z, imageRequest.getPriority()), this.mRequestListener);
        } catch (Exception exception) {
            return DataSources.immediateFailedDataSource(exception);
        }
    }

    private DataSource<Void> submitPrefetchRequest(Producer<Void> producerSequence, ImageRequest imageRequest, ImageRequest$RequestLevel lowestPermittedRequestLevelOnSubmit, Object callerContext, Priority priority) {
        try {
            return ProducerToDataSourceAdapter.create(producerSequence, new SettableProducerContext(imageRequest, generateUniqueFutureId(), this.mRequestListener, callerContext, ImageRequest$RequestLevel.getMax(imageRequest.getLowestPermittedRequestLevel(), lowestPermittedRequestLevelOnSubmit), true, false, priority), this.mRequestListener);
        } catch (Exception exception) {
            return DataSources.immediateFailedDataSource(exception);
        }
    }

    private Predicate<CacheKey> predicateForUri(final Uri uri) {
        return new Predicate<CacheKey>() {
            public boolean apply(CacheKey key) {
                return key.containsUri(uri);
            }
        };
    }

    public void pause() {
        this.mThreadHandoffProducerQueue.startQueueing();
    }

    public void resume() {
        this.mThreadHandoffProducerQueue.stopQueuing();
    }

    public boolean isPaused() {
        return this.mThreadHandoffProducerQueue.isQueueing();
    }
}
