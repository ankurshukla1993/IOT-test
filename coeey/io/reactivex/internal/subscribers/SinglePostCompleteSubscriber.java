package io.reactivex.internal.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public abstract class SinglePostCompleteSubscriber<T, R> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
    static final long COMPLETE_MASK = Long.MIN_VALUE;
    static final long REQUEST_MASK = Long.MAX_VALUE;
    private static final long serialVersionUID = 7917814472626990048L;
    protected final Subscriber<? super R> actual;
    protected long produced;
    protected Subscription f2562s;
    protected R value;

    public SinglePostCompleteSubscriber(Subscriber<? super R> actual) {
        this.actual = actual;
    }

    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.f2562s, s)) {
            this.f2562s = s;
            this.actual.onSubscribe(this);
        }
    }

    protected final void complete(R n) {
        long p = this.produced;
        if (p != 0) {
            BackpressureHelper.produced(this, p);
        }
        while (true) {
            long r = get();
            if ((r & Long.MIN_VALUE) != 0) {
                onDrop(n);
                return;
            } else if ((Long.MAX_VALUE & r) != 0) {
                lazySet(-9223372036854775807L);
                this.actual.onNext(n);
                this.actual.onComplete();
                return;
            } else {
                this.value = n;
                if (!compareAndSet(0, Long.MIN_VALUE)) {
                    this.value = null;
                } else {
                    return;
                }
            }
        }
    }

    protected void onDrop(R r) {
    }

    public final void request(long n) {
        if (SubscriptionHelper.validate(n)) {
            long r;
            do {
                r = get();
                if ((r & Long.MIN_VALUE) != 0) {
                    if (compareAndSet(Long.MIN_VALUE, -9223372036854775807L)) {
                        this.actual.onNext(this.value);
                        this.actual.onComplete();
                        return;
                    }
                    return;
                }
            } while (!compareAndSet(r, BackpressureHelper.addCap(r, n)));
            this.f2562s.request(n);
        }
    }

    public void cancel() {
        this.f2562s.cancel();
    }
}
