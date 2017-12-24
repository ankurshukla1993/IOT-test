package io.reactivex.internal.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public abstract class DeferredScalarSubscriber<T, R> extends DeferredScalarSubscription<R> implements FlowableSubscriber<T> {
    private static final long serialVersionUID = 2984505488220891551L;
    protected boolean hasValue;
    protected Subscription f2763s;

    public DeferredScalarSubscriber(Subscriber<? super R> actual) {
        super(actual);
    }

    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.f2763s, s)) {
            this.f2763s = s;
            this.actual.onSubscribe(this);
            s.request(Long.MAX_VALUE);
        }
    }

    public void onError(Throwable t) {
        this.value = null;
        this.actual.onError(t);
    }

    public void onComplete() {
        if (this.hasValue) {
            complete(this.value);
        } else {
            this.actual.onComplete();
        }
    }

    public void cancel() {
        super.cancel();
        this.f2763s.cancel();
    }
}
