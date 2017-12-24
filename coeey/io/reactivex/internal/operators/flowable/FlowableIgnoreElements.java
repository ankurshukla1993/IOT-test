package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.Nullable;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableIgnoreElements<T> extends AbstractFlowableWithUpstream<T, T> {

    static final class IgnoreElementsSubscriber<T> implements FlowableSubscriber<T>, QueueSubscription<T> {
        final Subscriber<? super T> actual;
        Subscription f2557s;

        IgnoreElementsSubscriber(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2557s, s)) {
                this.f2557s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public boolean offer(T t) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        public boolean offer(T t, T t2) {
            throw new UnsupportedOperationException("Should not be called!");
        }

        @Nullable
        public T poll() {
            return null;
        }

        public boolean isEmpty() {
            return true;
        }

        public void clear() {
        }

        public void request(long n) {
        }

        public void cancel() {
            this.f2557s.cancel();
        }

        public int requestFusion(int mode) {
            return mode & 2;
        }
    }

    public FlowableIgnoreElements(Flowable<T> source) {
        super(source);
    }

    protected void subscribeActual(Subscriber<? super T> t) {
        this.source.subscribe(new IgnoreElementsSubscriber(t));
    }
}
