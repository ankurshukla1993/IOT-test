package com.facebook.imagepipeline.producers;

public class NullProducer<T> implements Producer<T> {
    public void produceResults(Consumer<T> consumer, ProducerContext context) {
        consumer.onNewResult(null, true);
    }
}
