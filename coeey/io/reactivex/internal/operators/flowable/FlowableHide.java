package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableHide<T> extends AbstractFlowableWithUpstream<T, T> {

    static final class HideSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        Subscription f2556s;

        HideSubscriber(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void request(long n) {
            this.f2556s.request(n);
        }

        public void cancel() {
            this.f2556s.cancel();
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2556s, s)) {
                this.f2556s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public FlowableHide(Flowable<T> source) {
        super(source);
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new HideSubscriber(s));
    }
}
