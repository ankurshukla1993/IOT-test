package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

public final class FlowableSingleMaybe<T> extends Maybe<T> implements FuseToFlowable<T> {
    final Flowable<T> source;

    static final class SingleElementSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final MaybeObserver<? super T> actual;
        boolean done;
        Subscription f2580s;
        T value;

        SingleElementSubscriber(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2580s, s)) {
                this.f2580s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.value != null) {
                    this.done = true;
                    this.f2580s.cancel();
                    this.f2580s = SubscriptionHelper.CANCELLED;
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
            this.f2580s = SubscriptionHelper.CANCELLED;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.f2580s = SubscriptionHelper.CANCELLED;
                T v = this.value;
                this.value = null;
                if (v == null) {
                    this.actual.onComplete();
                } else {
                    this.actual.onSuccess(v);
                }
            }
        }

        public void dispose() {
            this.f2580s.cancel();
            this.f2580s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2580s == SubscriptionHelper.CANCELLED;
        }
    }

    public FlowableSingleMaybe(Flowable<T> source) {
        this.source = source;
    }

    protected void subscribeActual(MaybeObserver<? super T> s) {
        this.source.subscribe(new SingleElementSubscriber(s));
    }

    public Flowable<T> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableSingle(this.source, null));
    }
}
