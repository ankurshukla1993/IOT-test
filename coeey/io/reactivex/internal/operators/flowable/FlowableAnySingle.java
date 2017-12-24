package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

public final class FlowableAnySingle<T> extends Single<Boolean> implements FuseToFlowable<Boolean> {
    final Predicate<? super T> predicate;
    final Flowable<T> source;

    static final class AnySubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final SingleObserver<? super Boolean> actual;
        boolean done;
        final Predicate<? super T> predicate;
        Subscription f2510s;

        AnySubscriber(SingleObserver<? super Boolean> actual, Predicate<? super T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2510s, s)) {
                this.f2510s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    if (this.predicate.test(t)) {
                        this.done = true;
                        this.f2510s.cancel();
                        this.f2510s = SubscriptionHelper.CANCELLED;
                        this.actual.onSuccess(Boolean.valueOf(true));
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2510s.cancel();
                    this.f2510s = SubscriptionHelper.CANCELLED;
                    onError(e);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.f2510s = SubscriptionHelper.CANCELLED;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.f2510s = SubscriptionHelper.CANCELLED;
                this.actual.onSuccess(Boolean.valueOf(false));
            }
        }

        public void dispose() {
            this.f2510s.cancel();
            this.f2510s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2510s == SubscriptionHelper.CANCELLED;
        }
    }

    public FlowableAnySingle(Flowable<T> source, Predicate<? super T> predicate) {
        this.source = source;
        this.predicate = predicate;
    }

    protected void subscribeActual(SingleObserver<? super Boolean> s) {
        this.source.subscribe(new AnySubscriber(s, this.predicate));
    }

    public Flowable<Boolean> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableAny(this.source, this.predicate));
    }
}
