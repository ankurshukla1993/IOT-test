package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public final class CompletableToObservable<T> extends Observable<T> {
    final CompletableSource source;

    static final class ObserverCompletableObserver implements CompletableObserver {
        private final Observer<?> observer;

        ObserverCompletableObserver(Observer<?> observer) {
            this.observer = observer;
        }

        public void onComplete() {
            this.observer.onComplete();
        }

        public void onError(Throwable e) {
            this.observer.onError(e);
        }

        public void onSubscribe(Disposable d) {
            this.observer.onSubscribe(d);
        }
    }

    public CompletableToObservable(CompletableSource source) {
        this.source = source;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new ObserverCompletableObserver(observer));
    }
}
