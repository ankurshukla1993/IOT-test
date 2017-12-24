package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public final class ObservableIgnoreElements<T> extends AbstractObservableWithUpstream<T, T> {

    static final class IgnoreObservable<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        Disposable f2701d;

        IgnoreObservable(Observer<? super T> t) {
            this.actual = t;
        }

        public void onSubscribe(Disposable s) {
            this.f2701d = s;
            this.actual.onSubscribe(this);
        }

        public void onNext(T t) {
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void dispose() {
            this.f2701d.dispose();
        }

        public boolean isDisposed() {
            return this.f2701d.isDisposed();
        }
    }

    public ObservableIgnoreElements(ObservableSource<T> source) {
        super(source);
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new IgnoreObservable(t));
    }
}
