package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class FlowableReduceSeedSingle<T, R> extends Single<R> {
    final BiFunction<R, ? super T, R> reducer;
    final R seed;
    final Publisher<T> source;

    static final class ReduceSeedObserver<T, R> implements FlowableSubscriber<T>, Disposable {
        final SingleObserver<? super R> actual;
        final BiFunction<R, ? super T, R> reducer;
        Subscription f2574s;
        R value;

        ReduceSeedObserver(SingleObserver<? super R> actual, BiFunction<R, ? super T, R> reducer, R value) {
            this.actual = actual;
            this.value = value;
            this.reducer = reducer;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2574s, s)) {
                this.f2574s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T value) {
            try {
                this.value = ObjectHelper.requireNonNull(this.reducer.apply(this.value, value), "The reducer returned a null value");
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.f2574s.cancel();
                onError(ex);
            }
        }

        public void onError(Throwable e) {
            this.value = null;
            this.f2574s = SubscriptionHelper.CANCELLED;
            this.actual.onError(e);
        }

        public void onComplete() {
            R v = this.value;
            this.value = null;
            this.f2574s = SubscriptionHelper.CANCELLED;
            this.actual.onSuccess(v);
        }

        public void dispose() {
            this.f2574s.cancel();
            this.f2574s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2574s == SubscriptionHelper.CANCELLED;
        }
    }

    public FlowableReduceSeedSingle(Publisher<T> source, R seed, BiFunction<R, ? super T, R> reducer) {
        this.source = source;
        this.seed = seed;
        this.reducer = reducer;
    }

    protected void subscribeActual(SingleObserver<? super R> observer) {
        this.source.subscribe(new ReduceSeedObserver(observer, this.reducer, this.seed));
    }
}
