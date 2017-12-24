package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Notification;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableDematerialize<T> extends AbstractFlowableWithUpstream<Notification<T>, T> {

    static final class DematerializeSubscriber<T> implements FlowableSubscriber<Notification<T>>, Subscription {
        final Subscriber<? super T> actual;
        boolean done;
        Subscription f2537s;

        DematerializeSubscriber(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2537s, s)) {
                this.f2537s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(Notification<T> t) {
            if (this.done) {
                if (t.isOnError()) {
                    RxJavaPlugins.onError(t.getError());
                }
            } else if (t.isOnError()) {
                this.f2537s.cancel();
                onError(t.getError());
            } else if (t.isOnComplete()) {
                this.f2537s.cancel();
                onComplete();
            } else {
                this.actual.onNext(t.getValue());
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }

        public void request(long n) {
            this.f2537s.request(n);
        }

        public void cancel() {
            this.f2537s.cancel();
        }
    }

    public FlowableDematerialize(Flowable<Notification<T>> source) {
        super(source);
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new DematerializeSubscriber(s));
    }
}
