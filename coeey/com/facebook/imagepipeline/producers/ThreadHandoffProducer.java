package com.facebook.imagepipeline.producers;

import com.facebook.common.internal.Preconditions;
import com.facebook.common.internal.VisibleForTesting;

public class ThreadHandoffProducer<T> implements Producer<T> {
    @VisibleForTesting
    protected static final String PRODUCER_NAME = "BackgroundThreadHandoffProducer";
    private final Producer<T> mInputProducer;
    private final ThreadHandoffProducerQueue mThreadHandoffProducerQueue;

    public ThreadHandoffProducer(Producer<T> inputProducer, ThreadHandoffProducerQueue inputThreadHandoffProducerQueue) {
        this.mInputProducer = (Producer) Preconditions.checkNotNull(inputProducer);
        this.mThreadHandoffProducerQueue = inputThreadHandoffProducerQueue;
    }

    public void produceResults(Consumer<T> consumer, ProducerContext context) {
        ProducerListener producerListener = context.getListener();
        String requestId = context.getId();
        final ProducerListener producerListener2 = producerListener;
        final String str = requestId;
        final Consumer<T> consumer2 = consumer;
        final ProducerContext producerContext = context;
        final StatefulProducerRunnable<T> statefulRunnable = new StatefulProducerRunnable<T>(consumer, producerListener, PRODUCER_NAME, requestId) {
            protected void onSuccess(T t) {
                producerListener2.onProducerFinishWithSuccess(str, ThreadHandoffProducer.PRODUCER_NAME, null);
                ThreadHandoffProducer.this.mInputProducer.produceResults(consumer2, producerContext);
            }

            protected void disposeResult(T t) {
            }

            protected T getResult() throws Exception {
                return null;
            }
        };
        context.addCallbacks(new BaseProducerContextCallbacks() {
            public void onCancellationRequested() {
                statefulRunnable.cancel();
                ThreadHandoffProducer.this.mThreadHandoffProducerQueue.remove(statefulRunnable);
            }
        });
        this.mThreadHandoffProducerQueue.addToQueueOrExecute(statefulRunnable);
    }
}
