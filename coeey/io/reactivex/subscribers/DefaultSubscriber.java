package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import org.reactivestreams.Subscription;

public abstract class DefaultSubscriber<T> implements FlowableSubscriber<T> {
    private Subscription f2506s;

    public final void onSubscribe(Subscription s) {
        if (EndConsumerHelper.validate(this.f2506s, s, getClass())) {
            this.f2506s = s;
            onStart();
        }
    }

    protected final void request(long n) {
        Subscription s = this.f2506s;
        if (s != null) {
            s.request(n);
        }
    }

    protected final void cancel() {
        Subscription s = this.f2506s;
        this.f2506s = SubscriptionHelper.CANCELLED;
        s.cancel();
    }

    protected void onStart() {
        request(Long.MAX_VALUE);
    }
}
