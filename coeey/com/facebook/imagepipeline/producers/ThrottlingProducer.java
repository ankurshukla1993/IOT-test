package com.facebook.imagepipeline.producers;

import android.util.Pair;
import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Executor;
import javax.annotation.concurrent.GuardedBy;

public class ThrottlingProducer<T> implements Producer<T> {
    @VisibleForTesting
    static final String PRODUCER_NAME = "ThrottlingProducer";
    private final Executor mExecutor;
    private final Producer<T> mInputProducer;
    private final int mMaxSimultaneousRequests;
    @GuardedBy("this")
    private int mNumCurrentRequests = 0;
    @GuardedBy("this")
    private final ConcurrentLinkedQueue<Pair<Consumer<T>, ProducerContext>> mPendingRequests = new ConcurrentLinkedQueue();

    private class ThrottlerConsumer extends DelegatingConsumer<T, T> {
        private ThrottlerConsumer(Consumer<T> consumer) {
            super(consumer);
        }

        protected void onNewResultImpl(T newResult, boolean isLast) {
            getConsumer().onNewResult(newResult, isLast);
            if (isLast) {
                onRequestFinished();
            }
        }

        protected void onFailureImpl(Throwable t) {
            getConsumer().onFailure(t);
            onRequestFinished();
        }

        protected void onCancellationImpl() {
            getConsumer().onCancellation();
            onRequestFinished();
        }

        private void onRequestFinished() {
            synchronized (ThrottlingProducer.this) {
                final Pair<Consumer<T>, ProducerContext> nextRequestPair = (Pair) ThrottlingProducer.this.mPendingRequests.poll();
                if (nextRequestPair == null) {
                    ThrottlingProducer.this.mNumCurrentRequests = ThrottlingProducer.this.mNumCurrentRequests - 1;
                }
            }
            if (nextRequestPair != null) {
                ThrottlingProducer.this.mExecutor.execute(new Runnable() {
                    public void run() {
                        ThrottlingProducer.this.produceResultsInternal((Consumer) nextRequestPair.first, (ProducerContext) nextRequestPair.second);
                    }
                });
            }
        }
    }

    public ThrottlingProducer(int maxSimultaneousRequests, Executor executor, Producer<T> inputProducer) {
        this.mMaxSimultaneousRequests = maxSimultaneousRequests;
        this.mExecutor = (Executor) Preconditions.checkNotNull(executor);
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
    }

    public void produceResults(Consumer<T> consumer, ProducerContext producerContext) {
        boolean delayRequest;
        producerContext.getListener().onProducerStart(producerContext.getId(), PRODUCER_NAME);
        synchronized (this) {
            if (this.mNumCurrentRequests >= this.mMaxSimultaneousRequests) {
                this.mPendingRequests.add(Pair.create(consumer, producerContext));
                delayRequest = true;
            } else {
                this.mNumCurrentRequests++;
                delayRequest = false;
            }
        }
        if (!delayRequest) {
            produceResultsInternal(consumer, producerContext);
        }
    }

    void produceResultsInternal(Consumer<T> consumer, ProducerContext producerContext) {
        producerContext.getListener().onProducerFinishWithSuccess(producerContext.getId(), PRODUCER_NAME, null);
        this.mInputProducer.produceResults(new ThrottlerConsumer(consumer), producerContext);
    }
}
