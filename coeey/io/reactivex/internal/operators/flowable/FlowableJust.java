package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.internal.fuseable.ScalarCallable;
import io.reactivex.internal.subscriptions.ScalarSubscription;
import org.reactivestreams.Subscriber;

public final class FlowableJust<T> extends Flowable<T> implements ScalarCallable<T> {
    private final T value;

    public FlowableJust(T value) {
        this.value = value;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        s.onSubscribe(new ScalarSubscription(s, this.value));
    }

    public T call() {
        return this.value;
    }
}
