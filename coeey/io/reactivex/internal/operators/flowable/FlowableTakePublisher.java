package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public final class FlowableTakePublisher<T> extends Flowable<T> {
    final long limit;
    final Publisher<T> source;

    public FlowableTakePublisher(Publisher<T> source, long limit) {
        this.source = source;
        this.limit = limit;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new TakeSubscriber(s, this.limit));
    }
}
