package com.facebook.imagepipeline.producers;

import android.os.SystemClock;
import com.facebook.common.internal.VisibleForTesting;
import com.facebook.common.references.CloseableReference;
import com.facebook.imagepipeline.image.EncodedImage;
import com.facebook.imagepipeline.memory.ByteArrayPool;
import com.facebook.imagepipeline.memory.PooledByteBufferFactory;
import com.facebook.imagepipeline.memory.PooledByteBufferOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import javax.annotation.Nullable;

public class NetworkFetchProducer implements Producer<EncodedImage> {
    public static final String INTERMEDIATE_RESULT_PRODUCER_EVENT = "intermediate_result";
    @VisibleForTesting
    static final String PRODUCER_NAME = "NetworkFetchProducer";
    private static final int READ_SIZE = 16384;
    @VisibleForTesting
    static final long TIME_BETWEEN_PARTIAL_RESULTS_MS = 100;
    private final ByteArrayPool mByteArrayPool;
    private final NetworkFetcher mNetworkFetcher;
    private final PooledByteBufferFactory mPooledByteBufferFactory;

    public NetworkFetchProducer(PooledByteBufferFactory pooledByteBufferFactory, ByteArrayPool byteArrayPool, NetworkFetcher networkFetcher) {
        this.mPooledByteBufferFactory = pooledByteBufferFactory;
        this.mByteArrayPool = byteArrayPool;
        this.mNetworkFetcher = networkFetcher;
    }

    public void produceResults(Consumer<EncodedImage> consumer, ProducerContext context) {
        context.getListener().onProducerStart(context.getId(), PRODUCER_NAME);
        FetchState fetchState = this.mNetworkFetcher.createFetchState(consumer, context);
        this.mNetworkFetcher.fetch(fetchState, new 1(this, fetchState));
    }

    private void onResponse(FetchState fetchState, InputStream responseData, int responseContentLength) throws IOException {
        PooledByteBufferOutputStream pooledOutputStream;
        if (responseContentLength > 0) {
            pooledOutputStream = this.mPooledByteBufferFactory.newOutputStream(responseContentLength);
        } else {
            pooledOutputStream = this.mPooledByteBufferFactory.newOutputStream();
        }
        byte[] ioArray = (byte[]) this.mByteArrayPool.get(16384);
        while (true) {
            try {
                int length = responseData.read(ioArray);
                if (length < 0) {
                    break;
                } else if (length > 0) {
                    pooledOutputStream.write(ioArray, 0, length);
                    maybeHandleIntermediateResult(pooledOutputStream, fetchState);
                    fetchState.getConsumer().onProgressUpdate(calculateProgress(pooledOutputStream.size(), responseContentLength));
                }
            } finally {
                this.mByteArrayPool.release(ioArray);
                pooledOutputStream.close();
            }
        }
        this.mNetworkFetcher.onFetchCompletion(fetchState, pooledOutputStream.size());
        handleFinalResult(pooledOutputStream, fetchState);
    }

    private static float calculateProgress(int downloaded, int total) {
        return total > 0 ? ((float) downloaded) / ((float) total) : FlexItem.FLEX_SHRINK_DEFAULT - ((float) Math.exp(((double) (-downloaded)) / 50000.0d));
    }

    private void maybeHandleIntermediateResult(PooledByteBufferOutputStream pooledOutputStream, FetchState fetchState) {
        long nowMs = SystemClock.uptimeMillis();
        if (shouldPropagateIntermediateResults(fetchState) && nowMs - fetchState.getLastIntermediateResultTimeMs() >= TIME_BETWEEN_PARTIAL_RESULTS_MS) {
            fetchState.setLastIntermediateResultTimeMs(nowMs);
            fetchState.getListener().onProducerEvent(fetchState.getId(), PRODUCER_NAME, INTERMEDIATE_RESULT_PRODUCER_EVENT);
            notifyConsumer(pooledOutputStream, false, fetchState.getConsumer());
        }
    }

    private void handleFinalResult(PooledByteBufferOutputStream pooledOutputStream, FetchState fetchState) {
        fetchState.getListener().onProducerFinishWithSuccess(fetchState.getId(), PRODUCER_NAME, getExtraMap(fetchState, pooledOutputStream.size()));
        notifyConsumer(pooledOutputStream, true, fetchState.getConsumer());
    }

    private void notifyConsumer(PooledByteBufferOutputStream pooledOutputStream, boolean isFinal, Consumer<EncodedImage> consumer) {
        Throwable th;
        CloseableReference result = CloseableReference.of(pooledOutputStream.toByteBuffer());
        EncodedImage encodedImage = null;
        try {
            EncodedImage encodedImage2 = new EncodedImage(result);
            try {
                encodedImage2.parseMetaData();
                consumer.onNewResult(encodedImage2, isFinal);
                EncodedImage.closeSafely(encodedImage2);
                CloseableReference.closeSafely(result);
            } catch (Throwable th2) {
                th = th2;
                encodedImage = encodedImage2;
                EncodedImage.closeSafely(encodedImage);
                CloseableReference.closeSafely(result);
                throw th;
            }
        } catch (Throwable th3) {
            th = th3;
            EncodedImage.closeSafely(encodedImage);
            CloseableReference.closeSafely(result);
            throw th;
        }
    }

    private void onFailure(FetchState fetchState, Throwable e) {
        fetchState.getListener().onProducerFinishWithFailure(fetchState.getId(), PRODUCER_NAME, e, null);
        fetchState.getConsumer().onFailure(e);
    }

    private void onCancellation(FetchState fetchState) {
        fetchState.getListener().onProducerFinishWithCancellation(fetchState.getId(), PRODUCER_NAME, null);
        fetchState.getConsumer().onCancellation();
    }

    private boolean shouldPropagateIntermediateResults(FetchState fetchState) {
        if (fetchState.getContext().getImageRequest().getProgressiveRenderingEnabled()) {
            return this.mNetworkFetcher.shouldPropagate(fetchState);
        }
        return false;
    }

    @Nullable
    private Map<String, String> getExtraMap(FetchState fetchState, int byteSize) {
        if (fetchState.getListener().requiresExtraMap(fetchState.getId())) {
            return this.mNetworkFetcher.getExtraMap(fetchState, byteSize);
        }
        return null;
    }
}
