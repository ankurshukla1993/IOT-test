package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableSingle<T> extends AbstractFlowableWithUpstream<T, T> {
    final T defaultValue;

    static final class SingleElementSubscriber<T> extends DeferredScalarSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -5526049321428043809L;
        final T defaultValue;
        boolean done;
        Subscription f2579s;

        SingleElementSubscriber(Subscriber<? super T> actual, T defaultValue) {
            super(actual);
            this.defaultValue = defaultValue;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2579s, s)) {
                this.f2579s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.value != null) {
                    this.done = true;
                    this.f2579s.cancel();
                    this.actual.onError(new IllegalArgumentException("Sequence contains more than one element!"));
                    return;
                }
                this.value = t;
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
                T v = this.value;
                this.value = null;
                if (v == null) {
                    v = this.defaultValue;
                }
                if (v == null) {
                    this.actual.onComplete();
                } else {
                    complete(v);
                }
            }
        }

        public void cancel() {
            super.cancel();
            this.f2579s.cancel();
        }
    }

    public FlowableSingle(Flowable<T> source, T defaultValue) {
        super(source);
        this.defaultValue = defaultValue;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new SingleElementSubscriber(s, this.defaultValue));
    }
}
