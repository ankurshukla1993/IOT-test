package com.facebook.imagepipeline.producers;

import com.facebook.imagepipeline.producers.MultiplexProducer.Multiplexer;

class MultiplexProducer$Multiplexer$ForwardingConsumer extends BaseConsumer<T> {
    final /* synthetic */ Multiplexer this$1;

    private MultiplexProducer$Multiplexer$ForwardingConsumer(Multiplexer multiplexer) {
        this.this$1 = multiplexer;
    }

    protected void onNewResultImpl(T newResult, boolean isLast) {
        this.this$1.onNextResult(this, newResult, isLast);
    }

    protected void onFailureImpl(Throwable t) {
        this.this$1.onFailure(this, t);
    }

    protected void onCancellationImpl() {
        this.this$1.onCancelled(this);
    }

    protected void onProgressUpdateImpl(float progress) {
        this.this$1.onProgressUpdate(this, progress);
    }
}
