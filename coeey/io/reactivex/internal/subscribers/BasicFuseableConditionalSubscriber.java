package io.reactivex.internal.subscribers;

import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

public abstract class BasicFuseableConditionalSubscriber<T, R> implements ConditionalSubscriber<T>, QueueSubscription<R> {
    protected final ConditionalSubscriber<? super R> actual;
    protected boolean done;
    protected QueueSubscription<T> qs;
    protected Subscription f2540s;
    protected int sourceMode;

    public BasicFuseableConditionalSubscriber(ConditionalSubscriber<? super R> actual) {
        this.actual = actual;
    }

    public final void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.f2540s, s)) {
            this.f2540s = s;
            if (s instanceof QueueSubscription) {
                this.qs = (QueueSubscription) s;
            }
            if (beforeDownstream()) {
                this.actual.onSubscribe(this);
                afterDownstream();
            }
        }
    }

    protected boolean beforeDownstream() {
        return true;
    }

    protected void afterDownstream() {
    }

    public void onError(Throwable t) {
        if (this.done) {
            RxJavaPlugins.onError(t);
            return;
        }
        this.done = true;
        this.actual.onError(t);
    }

    protected final void fail(Throwable t) {
        Exceptions.throwIfFatal(t);
        this.f2540s.cancel();
        onError(t);
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            this.actual.onComplete();
        }
    }

    protected final int transitiveBoundaryFusion(int mode) {
        QueueSubscription<T> qs = this.qs;
        if (qs == null || (mode & 4) != 0) {
            return 0;
        }
        int m = qs.requestFusion(mode);
        if (m == 0) {
            return m;
        }
        this.sourceMode = m;
        return m;
    }

    public void request(long n) {
        this.f2540s.request(n);
    }

    public void cancel() {
        this.f2540s.cancel();
    }

    public boolean isEmpty() {
        return this.qs.isEmpty();
    }

    public void clear() {
        this.qs.clear();
    }

    public final boolean offer(R r) {
        throw new UnsupportedOperationException("Should not be called!");
    }

    public final boolean offer(R r, R r2) {
        throw new UnsupportedOperationException("Should not be called!");
    }
}
