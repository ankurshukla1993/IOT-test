package io.reactivex.internal.operators.single;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class SingleToObservable<T> extends Observable<T> {
    final SingleSource<? extends T> source;

    static final class SingleToObservableObserver<T> implements SingleObserver<T>, Disposable {
        final Observer<? super T> actual;
        Disposable f2798d;

        SingleToObservableObserver(Observer<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2798d, d)) {
                this.f2798d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.actual.onNext(value);
            this.actual.onComplete();
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void dispose() {
            this.f2798d.dispose();
        }

        public boolean isDisposed() {
            return this.f2798d.isDisposed();
        }
    }

    public SingleToObservable(SingleSource<? extends T> source) {
        this.source = source;
    }

    public void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new SingleToObservableObserver(s));
    }
}
