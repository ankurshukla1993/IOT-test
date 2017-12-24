package io.reactivex.internal.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.FullArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscription;

public final class FullArbiterSubscriber<T> implements FlowableSubscriber<T> {
    final FullArbiter<T> arbiter;
    Subscription f2803s;

    public FullArbiterSubscriber(FullArbiter<T> arbiter) {
        this.arbiter = arbiter;
    }

    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.f2803s, s)) {
            this.f2803s = s;
            this.arbiter.setSubscription(s);
        }
    }

    public void onNext(T t) {
        this.arbiter.onNext(t, this.f2803s);
    }

    public void onError(Throwable t) {
        this.arbiter.onError(t, this.f2803s);
    }

    public void onComplete() {
        this.arbiter.onComplete(this.f2803s);
    }
}
