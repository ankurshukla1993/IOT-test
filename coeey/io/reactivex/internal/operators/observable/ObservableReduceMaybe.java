package io.reactivex.internal.operators.observable;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableReduceMaybe<T> extends Maybe<T> {
    final BiFunction<T, T, T> reducer;
    final ObservableSource<T> source;

    static final class ReduceObserver<T> implements Observer<T>, Disposable {
        final MaybeObserver<? super T> actual;
        Disposable f2712d;
        boolean done;
        final BiFunction<T, T, T> reducer;
        T value;

        ReduceObserver(MaybeObserver<? super T> observer, BiFunction<T, T, T> reducer) {
            this.actual = observer;
            this.reducer = reducer;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2712d, d)) {
                this.f2712d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T value) {
            if (!this.done) {
                T v = this.value;
                if (v == null) {
                    this.value = value;
                    return;
                }
                try {
                    this.value = ObjectHelper.requireNonNull(this.reducer.apply(v, value), "The reducer returned a null value");
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    this.f2712d.dispose();
                    onError(ex);
                }
            }
        }

        public void onError(Throwable e) {
            if (this.done) {
                RxJavaPlugins.onError(e);
                return;
            }
            this.done = true;
            this.value = null;
            this.actual.onError(e);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                T v = this.value;
                this.value = null;
                if (v != null) {
                    this.actual.onSuccess(v);
                } else {
                    this.actual.onComplete();
                }
            }
        }

        public void dispose() {
            this.f2712d.dispose();
        }

        public boolean isDisposed() {
            return this.f2712d.isDisposed();
        }
    }

    public ObservableReduceMaybe(ObservableSource<T> source, BiFunction<T, T, T> reducer) {
        this.source = source;
        this.reducer = reducer;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new ReduceObserver(observer, this.reducer));
    }
}
