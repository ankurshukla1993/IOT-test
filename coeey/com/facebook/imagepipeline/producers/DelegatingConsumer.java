package com.facebook.imagepipeline.producers;

public abstract class DelegatingConsumer<I, O> extends BaseConsumer<I> {
    private final Consumer<O> mConsumer;

    public DelegatingConsumer(Consumer<O> consumer) {
        this.mConsumer = consumer;
    }

    public Consumer<O> getConsumer() {
        return this.mConsumer;
    }

    protected void onFailureImpl(Throwable t) {
        this.mConsumer.onFailure(t);
    }

    protected void onCancellationImpl() {
        this.mConsumer.onCancellation();
    }

    protected void onProgressUpdateImpl(float progress) {
        this.mConsumer.onProgressUpdate(progress);
    }
}
