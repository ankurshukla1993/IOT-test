package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableCount<T> extends AbstractFlowableWithUpstream<T, Long> {

    static final class CountSubscriber extends DeferredScalarSubscription<Long> implements FlowableSubscriber<Object> {
        private static final long serialVersionUID = 4973004223787171406L;
        long count;
        Subscription f2528s;

        CountSubscriber(Subscriber<? super Long> actual) {
            super(actual);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2528s, s)) {
                this.f2528s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(Object t) {
            this.count++;
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            complete(Long.valueOf(this.count));
        }

        public void cancel() {
            super.cancel();
            this.f2528s.cancel();
        }
    }

    public FlowableCount(Flowable<T> source) {
        super(source);
    }

    protected void subscribeActual(Subscriber<? super Long> s) {
        this.source.subscribe(new CountSubscriber(s));
    }
}
