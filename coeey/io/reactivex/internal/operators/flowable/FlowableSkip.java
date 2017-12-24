package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableSkip<T> extends AbstractFlowableWithUpstream<T, T> {
    final long f2583n;

    static final class SkipSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        long remaining;
        Subscription f2582s;

        SkipSubscriber(Subscriber<? super T> actual, long n) {
            this.actual = actual;
            this.remaining = n;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2582s, s)) {
                long n = this.remaining;
                this.f2582s = s;
                this.actual.onSubscribe(this);
                s.request(n);
            }
        }

        public void onNext(T t) {
            if (this.remaining != 0) {
                this.remaining--;
            } else {
                this.actual.onNext(t);
            }
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void request(long n) {
            this.f2582s.request(n);
        }

        public void cancel() {
            this.f2582s.cancel();
        }
    }

    public FlowableSkip(Flowable<T> source, long n) {
        super(source);
        this.f2583n = n;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new SkipSubscriber(s, this.f2583n));
    }
}
