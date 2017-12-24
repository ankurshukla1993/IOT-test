package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EmptyComponent;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableDetach<T> extends AbstractFlowableWithUpstream<T, T> {

    static final class DetachSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        Subscriber<? super T> actual;
        Subscription f2538s;

        DetachSubscriber(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void request(long n) {
            this.f2538s.request(n);
        }

        public void cancel() {
            Subscription s = this.f2538s;
            this.f2538s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asSubscriber();
            s.cancel();
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2538s, s)) {
                this.f2538s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            Subscriber<? super T> a = this.actual;
            this.f2538s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asSubscriber();
            a.onError(t);
        }

        public void onComplete() {
            Subscriber<? super T> a = this.actual;
            this.f2538s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asSubscriber();
            a.onComplete();
        }
    }

    public FlowableDetach(Flowable<T> source) {
        super(source);
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new DetachSubscriber(s));
    }
}
