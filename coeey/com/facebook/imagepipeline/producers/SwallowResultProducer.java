package com.facebook.imagepipeline.producers;

public class SwallowResultProducer<T> implements Producer<Void> {
    private final Producer<T> mInputProducer;

    public SwallowResultProducer(Producer<T> inputProducer) {
        this.mInputProducer = inputProducer;
    }

    public void produceResults(Consumer<Void> consumer, ProducerContext producerContext) {
        this.mInputProducer.produceResults(new DelegatingConsumer<T, Void>(consumer) {
            protected void onNewResultImpl(T t, boolean isLast) {
                if (isLast) {
                    getConsumer().onNewResult(null, isLast);
                }
            }
        }, producerContext);
    }
}
