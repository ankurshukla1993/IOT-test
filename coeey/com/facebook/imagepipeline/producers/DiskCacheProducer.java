package com.facebook.imagepipeline.producers;

import bolts.Continuation;
import bolts.Task;
import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.imagepipeline.cache.BufferedDiskCache;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.request.ImageRequest;
import com.facebook.imagepipeline.request.ImageRequest$ImageType;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.google.android.flexbox.FlexItem;
import java.util.Map;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;

public class DiskCacheProducer implements Producer<EncodedImage> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "DiskCacheProducer";
    @VisibleForTesting
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final boolean mChooseCacheByImageSize;
    private final BufferedDiskCache mDefaultBufferedDiskCache;
    private final int mForceSmallCacheThresholdBytes;
    private final Producer<EncodedImage> mInputProducer;
    private final BufferedDiskCache mSmallImageBufferedDiskCache;

    private class DiskCacheConsumer extends DelegatingConsumer<EncodedImage, EncodedImage> {
        private final BufferedDiskCache mCache;
        private final CacheKey mCacheKey;

        private DiskCacheConsumer(Consumer<EncodedImage> consumer, BufferedDiskCache cache, CacheKey cacheKey) {
            super(consumer);
            this.mCache = cache;
            this.mCacheKey = cacheKey;
        }

        public void onNewResultImpl(EncodedImage newResult, boolean isLast) {
            if (newResult != null && isLast) {
                if (DiskCacheProducer.this.mChooseCacheByImageSize) {
                    int size = newResult.getSize();
                    if (size <= 0 || size >= DiskCacheProducer.this.mForceSmallCacheThresholdBytes) {
                        DiskCacheProducer.this.mDefaultBufferedDiskCache.put(this.mCacheKey, newResult);
                    } else {
                        DiskCacheProducer.this.mSmallImageBufferedDiskCache.put(this.mCacheKey, newResult);
                    }
                } else {
                    this.mCache.put(this.mCacheKey, newResult);
                }
            }
            getConsumer().onNewResult(newResult, isLast);
        }
    }

    public DiskCacheProducer(BufferedDiskCache defaultBufferedDiskCache, BufferedDiskCache smallImageBufferedDiskCache, CacheKeyFactory cacheKeyFactory, Producer<EncodedImage> inputProducer, int forceSmallCacheThresholdBytes) {
        this.mDefaultBufferedDiskCache = defaultBufferedDiskCache;
        this.mSmallImageBufferedDiskCache = smallImageBufferedDiskCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
        this.mForceSmallCacheThresholdBytes = forceSmallCacheThresholdBytes;
        this.mChooseCacheByImageSize = forceSmallCacheThresholdBytes > 0;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext producerContext) {
        ImageRequest imageRequest = producerContext.getImageRequest();
        if (imageRequest.isDiskCacheEnabled()) {
            BufferedDiskCache preferredCache;
            Task<EncodedImage> diskLookupTask;
            producerContext.getListener().onProducerStart(producerContext.getId(), PRODUCER_NAME);
            final CacheKey cacheKey = this.mCacheKeyFactory.getEncodedCacheKey(imageRequest, producerContext.getCallerContext());
            if (imageRequest.getImageType() == ImageRequest$ImageType.SMALL) {
                preferredCache = this.mSmallImageBufferedDiskCache;
            } else {
                preferredCache = this.mDefaultBufferedDiskCache;
            }
            final AtomicBoolean isCancelled = new AtomicBoolean(false);
            if (this.mChooseCacheByImageSize) {
                BufferedDiskCache firstCache;
                BufferedDiskCache secondCache;
                boolean alreadyInSmall = this.mSmallImageBufferedDiskCache.containsSync(cacheKey);
                boolean alreadyInMain = this.mDefaultBufferedDiskCache.containsSync(cacheKey);
                if (alreadyInSmall || !alreadyInMain) {
                    firstCache = this.mSmallImageBufferedDiskCache;
                    secondCache = this.mDefaultBufferedDiskCache;
                } else {
                    firstCache = this.mDefaultBufferedDiskCache;
                    secondCache = this.mSmallImageBufferedDiskCache;
                }
                diskLookupTask = firstCache.get(cacheKey, isCancelled).continueWithTask(new Continuation<EncodedImage, Task<EncodedImage>>() {
                    public Task<EncodedImage> then(Task<EncodedImage> task) throws Exception {
                        if (DiskCacheProducer.isTaskCancelled(task)) {
                            return task;
                        }
                        return (task.isFaulted() || task.getResult() == null) ? secondCache.get(cacheKey, isCancelled) : task;
                    }
                });
            } else {
                diskLookupTask = preferredCache.get(cacheKey, isCancelled);
            }
            diskLookupTask.continueWith(onFinishDiskReads(consumer, preferredCache, cacheKey, producerContext));
            subscribeTaskForRequestCancellation(isCancelled, producerContext);
            return;
        }
        maybeStartInputProducer(consumer, consumer, producerContext);
    }

    private Continuation<EncodedImage, Void> onFinishDiskReads(Consumer<EncodedImage> consumer, BufferedDiskCache preferredCache, CacheKey preferredCacheKey, ProducerContext producerContext) {
        final String requestId = producerContext.getId();
        final ProducerListener listener = producerContext.getListener();
        final Consumer<EncodedImage> consumer2 = consumer;
        final BufferedDiskCache bufferedDiskCache = preferredCache;
        final CacheKey cacheKey = preferredCacheKey;
        final ProducerContext producerContext2 = producerContext;
        return new Continuation<EncodedImage, Void>() {
            public Void then(Task<EncodedImage> task) throws Exception {
                if (DiskCacheProducer.isTaskCancelled(task)) {
                    listener.onProducerFinishWithCancellation(requestId, DiskCacheProducer.PRODUCER_NAME, null);
                    consumer2.onCancellation();
                } else if (task.isFaulted()) {
                    listener.onProducerFinishWithFailure(requestId, DiskCacheProducer.PRODUCER_NAME, task.getError(), null);
                    DiskCacheProducer.this.maybeStartInputProducer(consumer2, new DiskCacheConsumer(consumer2, bufferedDiskCache, cacheKey), producerContext2);
                } else {
                    EncodedImage cachedReference = (EncodedImage) task.getResult();
                    if (cachedReference != null) {
                        listener.onProducerFinishWithSuccess(requestId, DiskCacheProducer.PRODUCER_NAME, DiskCacheProducer.getExtraMap(listener, requestId, true));
                        consumer2.onProgressUpdate(FlexItem.FLEX_SHRINK_DEFAULT);
                        consumer2.onNewResult(cachedReference, true);
                        cachedReference.close();
                    } else {
                        listener.onProducerFinishWithSuccess(requestId, DiskCacheProducer.PRODUCER_NAME, DiskCacheProducer.getExtraMap(listener, requestId, false));
                        DiskCacheProducer.this.maybeStartInputProducer(consumer2, new DiskCacheConsumer(consumer2, bufferedDiskCache, cacheKey), producerContext2);
                    }
                }
                return null;
            }
        };
    }

    private static boolean isTaskCancelled(Task<?> task) {
        return task.isCancelled() || (task.isFaulted() && (task.getError() instanceof CancellationException));
    }

    private void maybeStartInputProducer(Consumer<EncodedImage> consumerOfDiskCacheProducer, Consumer<EncodedImage> consumerOfInputProducer, ProducerContext producerContext) {
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest$RequestLevel.DISK_CACHE.getValue()) {
            consumerOfDiskCacheProducer.onNewResult(null, true);
        } else {
            this.mInputProducer.produceResults(consumerOfInputProducer, producerContext);
        }
    }

    @VisibleForTesting
    static Map<String, String> getExtraMap(ProducerListener listener, String requestId, boolean valueFound) {
        if (listener.requiresExtraMap(requestId)) {
            return ImmutableMap.of(VALUE_FOUND, String.valueOf(valueFound));
        }
        return null;
    }

    private void subscribeTaskForRequestCancellation(final AtomicBoolean isCancelled, ProducerContext producerContext) {
        producerContext.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                isCancelled.set(true);
            }
        });
    }
}
