package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.ArrayDeque;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableSkipLast<T> extends AbstractFlowableWithUpstream<T, T> {
    final int skip;

    static final class SkipLastSubscriber<T> extends ArrayDeque<T> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -3807491841935125653L;
        final Subscriber<? super T> actual;
        Subscription f2584s;
        final int skip;

        SkipLastSubscriber(Subscriber<? super T> actual, int skip) {
            super(skip);
            this.actual = actual;
            this.skip = skip;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2584s, s)) {
                this.f2584s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.skip == size()) {
                this.actual.onNext(poll());
            } else {
                this.f2584s.request(1);
            }
            offer(t);
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void request(long n) {
            this.f2584s.request(n);
        }

        public void cancel() {
            this.f2584s.cancel();
        }
    }

    public FlowableSkipLast(Flowable<T> source, int skip) {
        super(source);
        this.skip = skip;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new SkipLastSubscriber(s, this.skip));
    }
}
