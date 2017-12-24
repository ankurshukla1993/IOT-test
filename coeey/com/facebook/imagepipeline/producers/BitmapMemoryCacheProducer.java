package com.facebook.imagepipeline.producers;

import com.facebook.cache.common.CacheKey;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.cache.CacheKeyFactory;
import com.facebook.imagepipeline.cache.MemoryCache;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.QualityInfo;
import com.facebook.imagepipeline.request.ImageRequest$RequestLevel;
import com.facebook.internal.ServerProtocol;
import com.google.android.flexbox.FlexItem;
import java.util.Map;

public class BitmapMemoryCacheProducer implements Producer<CloseableReference<CloseableImage>> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "BitmapMemoryCacheProducer";
    @VisibleForTesting
    static final String VALUE_FOUND = "cached_value_found";
    private final CacheKeyFactory mCacheKeyFactory;
    private final Producer<CloseableReference<CloseableImage>> mInputProducer;
    private final MemoryCache<CacheKey, CloseableImage> mMemoryCache;

    public BitmapMemoryCacheProducer(MemoryCache<CacheKey, CloseableImage> memoryCache, CacheKeyFactory cacheKeyFactory, Producer<CloseableReference<CloseableImage>> inputProducer) {
        this.mMemoryCache = memoryCache;
        this.mCacheKeyFactory = cacheKeyFactory;
        this.mInputProducer = inputProducer;
    }

    public void produceResults(Consumer<CloseableReference<CloseableImage>> consumer, ProducerContext producerContext) {
        Map of;
        Map map = null;
        ProducerListener listener = producerContext.getListener();
        String requestId = producerContext.getId();
        listener.onProducerStart(requestId, getProducerName());
        CacheKey cacheKey = this.mCacheKeyFactory.getBitmapCacheKey(producerContext.getImageRequest(), producerContext.getCallerContext());
        CloseableReference<CloseableImage> cachedReference = this.mMemoryCache.get(cacheKey);
        if (cachedReference != null) {
            boolean isFinal = ((CloseableImage) cachedReference.get()).getQualityInfo().isOfFullQuality();
            if (isFinal) {
                String producerName = getProducerName();
                if (listener.requiresExtraMap(requestId)) {
                    of = ImmutableMap.of(VALUE_FOUND, ServerProtocol.DIALOG_RETURN_SCOPES_TRUE);
                } else {
                    of = null;
                }
                listener.onProducerFinishWithSuccess(requestId, producerName, of);
                consumer.onProgressUpdate(FlexItem.FLEX_SHRINK_DEFAULT);
            }
            consumer.onNewResult(cachedReference, isFinal);
            cachedReference.close();
            if (isFinal) {
                return;
            }
        }
        if (producerContext.getLowestPermittedRequestLevel().getValue() >= ImageRequest$RequestLevel.BITMAP_MEMORY_CACHE.getValue()) {
            producerName = getProducerName();
            if (listener.requiresExtraMap(requestId)) {
                of = ImmutableMap.of(VALUE_FOUND, "false");
            } else {
                of = null;
            }
            listener.onProducerFinishWithSuccess(requestId, producerName, of);
            consumer.onNewResult(null, true);
            return;
        }
        Consumer<CloseableReference<CloseableImage>> wrappedConsumer = wrapConsumer(consumer, cacheKey);
        String producerName2 = getProducerName();
        if (listener.requiresExtraMap(requestId)) {
            map = ImmutableMap.of(VALUE_FOUND, "false");
        }
        listener.onProducerFinishWithSuccess(requestId, producerName2, map);
        this.mInputProducer.produceResults(wrappedConsumer, producerContext);
    }

    protected Consumer<CloseableReference<CloseableImage>> wrapConsumer(Consumer<CloseableReference<CloseableImage>> consumer, final CacheKey cacheKey) {
        return new DelegatingConsumer<CloseableReference<CloseableImage>, CloseableReference<CloseableImage>>(consumer) {
            public void onNewResultImpl(CloseableReference<CloseableImage> newResult, boolean isLast) {
                if (newResult == null) {
                    if (isLast) {
                        getConsumer().onNewResult(null, true);
                    }
                } else if (((CloseableImage) newResult.get()).isStateful()) {
                    getConsumer().onNewResult(newResult, isLast);
                } else {
                    if (!isLast) {
                        CloseableReference<CloseableImage> currentCachedResult = BitmapMemoryCacheProducer.this.mMemoryCache.get(cacheKey);
                        if (currentCachedResult != null) {
                            try {
                                QualityInfo newInfo = ((CloseableImage) newResult.get()).getQualityInfo();
                                QualityInfo cachedInfo = ((CloseableImage) currentCachedResult.get()).getQualityInfo();
                                if (cachedInfo.isOfFullQuality() || cachedInfo.getQuality() >= newInfo.getQuality()) {
                                    getConsumer().onNewResult(currentCachedResult, false);
                                    return;
                                }
                                CloseableReference.closeSafely(currentCachedResult);
                            } finally {
                                CloseableReference.closeSafely(currentCachedResult);
                            }
                        }
                    }
                    CloseableReference<CloseableImage> newCachedResult = BitmapMemoryCacheProducer.this.mMemoryCache.cache(cacheKey, newResult);
                    if (isLast) {
                        try {
                            getConsumer().onProgressUpdate(FlexItem.FLEX_SHRINK_DEFAULT);
                        } catch (Throwable th) {
                            CloseableReference.closeSafely(newCachedResult);
                        }
                    }
                    Consumer consumer = getConsumer();
                    if (newCachedResult != null) {
                        newResult = newCachedResult;
                    }
                    consumer.onNewResult(newResult, isLast);
                    CloseableReference.closeSafely(newCachedResult);
                }
            }
        };
    }

    protected String getProducerName() {
        return PRODUCER_NAME;
    }
}
