package io.reactivex.internal.operators.observable;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class ObservableLastMaybe<T> extends Maybe<T> {
    final ObservableSource<T> source;

    static final class LastObserver<T> implements Observer<T>, Disposable {
        final MaybeObserver<? super T> actual;
        T item;
        Disposable f2704s;

        LastObserver(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2704s.dispose();
            this.f2704s = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2704s == DisposableHelper.DISPOSED;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2704s, s)) {
                this.f2704s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.item = t;
        }

        public void onError(Throwable t) {
            this.f2704s = DisposableHelper.DISPOSED;
            this.item = null;
            this.actual.onError(t);
        }

        public void onComplete() {
            this.f2704s = DisposableHelper.DISPOSED;
            T v = this.item;
            if (v != null) {
                this.item = null;
                this.actual.onSuccess(v);
                return;
            }
            this.actual.onComplete();
        }
    }

    public ObservableLastMaybe(ObservableSource<T> source) {
        this.source = source;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new LastObserver(observer));
    }
}
