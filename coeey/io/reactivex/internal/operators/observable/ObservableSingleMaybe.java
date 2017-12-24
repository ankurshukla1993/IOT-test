package io.reactivex.internal.operators.observable;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableSingleMaybe<T> extends Maybe<T> {
    final ObservableSource<T> source;

    static final class SingleElementObserver<T> implements Observer<T>, Disposable {
        final MaybeObserver<? super T> actual;
        boolean done;
        Disposable f2720s;
        T value;

        SingleElementObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2720s, s)) {
                this.f2720s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2720s.dispose();
        }

        public boolean isDisposed() {
            return this.f2720s.isDisposed();
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.value != null) {
                    this.done = true;
                    this.f2720s.dispose();
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
                    this.actual.onComplete();
                } else {
                    this.actual.onSuccess(v);
                }
            }
        }
    }

    public ObservableSingleMaybe(ObservableSource<T> source) {
        this.source = source;
    }

    public void subscribeActual(MaybeObserver<? super T> t) {
        this.source.subscribe(new SingleElementObserver(t));
    }
}
