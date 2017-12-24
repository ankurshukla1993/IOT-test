package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableReduceSeedSingle<T, R> extends Single<R> {
    final BiFunction<R, ? super T, R> reducer;
    final R seed;
    final ObservableSource<T> source;

    static final class ReduceSeedObserver<T, R> implements Observer<T>, Disposable {
        final SingleObserver<? super R> actual;
        Disposable f2713d;
        final BiFunction<R, ? super T, R> reducer;
        R value;

        ReduceSeedObserver(SingleObserver<? super R> actual, BiFunction<R, ? super T, R> reducer, R value) {
            this.actual = actual;
            this.value = value;
            this.reducer = reducer;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2713d, d)) {
                this.f2713d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T value) {
            R v = this.value;
            if (v != null) {
                try {
                    this.value = ObjectHelper.requireNonNull(this.reducer.apply(v, value), "The reducer returned a null value");
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    this.f2713d.dispose();
                    onError(ex);
                }
            }
        }

        public void onError(Throwable e) {
            R v = this.value;
            this.value = null;
            if (v != null) {
                this.actual.onError(e);
            } else {
                RxJavaPlugins.onError(e);
            }
        }

        public void onComplete() {
            R v = this.value;
            this.value = null;
            if (v != null) {
                this.actual.onSuccess(v);
            }
        }

        public void dispose() {
            this.f2713d.dispose();
        }

        public boolean isDisposed() {
            return this.f2713d.isDisposed();
        }
    }

    public ObservableReduceSeedSingle(ObservableSource<T> source, R seed, BiFunction<R, ? super T, R> reducer) {
        this.source = source;
        this.seed = seed;
        this.reducer = reducer;
    }

    protected void subscribeActual(SingleObserver<? super R> observer) {
        this.source.subscribe(new ReduceSeedObserver(observer, this.reducer, this.seed));
    }
}
