package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public final class CompletableFromObservable<T> extends Completable {
    final ObservableSource<T> observable;

    static final class CompletableFromObservableObserver<T> implements Observer<T> {
        final CompletableObserver co;

        CompletableFromObservableObserver(CompletableObserver co) {
            this.co = co;
        }

        public void onSubscribe(Disposable d) {
            this.co.onSubscribe(d);
        }

        public void onNext(T t) {
        }

        public void onError(Throwable e) {
            this.co.onError(e);
        }

        public void onComplete() {
            this.co.onComplete();
        }
    }

    public CompletableFromObservable(ObservableSource<T> observable) {
        this.observable = observable;
    }

    protected void subscribeActual(CompletableObserver s) {
        this.observable.subscribe(new CompletableFromObservableObserver(s));
    }
}
