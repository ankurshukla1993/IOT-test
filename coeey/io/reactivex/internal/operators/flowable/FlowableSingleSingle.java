package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import org.reactivestreams.Subscription;

public final class FlowableSingleSingle<T> extends Single<T> implements FuseToFlowable<T> {
    final T defaultValue;
    final Flowable<T> source;

    static final class SingleElementSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final SingleObserver<? super T> actual;
        final T defaultValue;
        boolean done;
        Subscription f2581s;
        T value;

        SingleElementSubscriber(SingleObserver<? super T> actual, T defaultValue) {
            this.actual = actual;
            this.defaultValue = defaultValue;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2581s, s)) {
                this.f2581s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.value != null) {
                    this.done = true;
                    this.f2581s.cancel();
                    this.f2581s = SubscriptionHelper.CANCELLED;
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
            this.f2581s = SubscriptionHelper.CANCELLED;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.f2581s = SubscriptionHelper.CANCELLED;
                T v = this.value;
                this.value = null;
                if (v == null) {
                    v = this.defaultValue;
                }
                if (v != null) {
                    this.actual.onSuccess(v);
                } else {
                    this.actual.onError(new NoSuchElementException());
                }
            }
        }

        public void dispose() {
            this.f2581s.cancel();
            this.f2581s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2581s == SubscriptionHelper.CANCELLED;
        }
    }

    public FlowableSingleSingle(Flowable<T> source, T defaultValue) {
        this.source = source;
        this.defaultValue = defaultValue;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new SingleElementSubscriber(s, this.defaultValue));
    }

    public Flowable<T> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableSingle(this.source, this.defaultValue));
    }
}
