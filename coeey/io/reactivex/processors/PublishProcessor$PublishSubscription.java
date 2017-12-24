package io.reactivex.processors;

import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

final class PublishProcessor$PublishSubscription<T> extends AtomicLong implements Subscription {
    private static final long serialVersionUID = 3562861878281475070L;
    final Subscriber<? super T> actual;
    final PublishProcessor<T> parent;

    PublishProcessor$PublishSubscription(Subscriber<? super T> actual, PublishProcessor<T> parent) {
        this.actual = actual;
        this.parent = parent;
    }

    public void onNext(T t) {
        long r = get();
        if (r != Long.MIN_VALUE) {
            if (r != 0) {
                this.actual.onNext(t);
                if (r != Long.MAX_VALUE) {
                    decrementAndGet();
                    return;
                }
                return;
            }
            cancel();
            this.actual.onError(new MissingBackpressureException("Could not emit value due to lack of requests"));
        }
    }

    public void onError(Throwable t) {
        if (get() != Long.MIN_VALUE) {
            this.actual.onError(t);
        } else {
            RxJavaPlugins.onError(t);
        }
    }

    public void onComplete() {
        if (get() != Long.MIN_VALUE) {
            this.actual.onComplete();
        }
    }

    public void request(long n) {
        if (SubscriptionHelper.validate(n)) {
            BackpressureHelper.addCancel(this, n);
        }
    }

    public void cancel() {
        if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
            this.parent.remove(this);
        }
    }

    public boolean isCancelled() {
        return get() == Long.MIN_VALUE;
    }

    boolean isFull() {
        return get() == 0;
    }
}
