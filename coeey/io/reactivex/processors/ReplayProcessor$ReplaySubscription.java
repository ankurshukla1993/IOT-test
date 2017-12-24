package io.reactivex.processors;

import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

final class ReplayProcessor$ReplaySubscription<T> extends AtomicInteger implements Subscription {
    private static final long serialVersionUID = 466549804534799122L;
    final Subscriber<? super T> actual;
    volatile boolean cancelled;
    long emitted;
    Object index;
    final AtomicLong requested = new AtomicLong();
    final ReplayProcessor<T> state;

    ReplayProcessor$ReplaySubscription(Subscriber<? super T> actual, ReplayProcessor<T> state) {
        this.actual = actual;
        this.state = state;
    }

    public void request(long n) {
        if (SubscriptionHelper.validate(n)) {
            BackpressureHelper.add(this.requested, n);
            this.state.buffer.replay(this);
        }
    }

    public void cancel() {
        if (!this.cancelled) {
            this.cancelled = true;
            this.state.remove(this);
        }
    }
}
