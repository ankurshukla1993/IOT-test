package io.reactivex.internal.operators.observable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableIgnoreElementsCompletable<T> extends Completable implements FuseToObservable<T> {
    final ObservableSource<T> source;

    static final class IgnoreObservable<T> implements Observer<T>, Disposable {
        final CompletableObserver actual;
        Disposable f2702d;

        IgnoreObservable(CompletableObserver t) {
            this.actual = t;
        }

        public void onSubscribe(Disposable s) {
            this.f2702d = s;
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
            this.f2702d.dispose();
        }

        public boolean isDisposed() {
            return this.f2702d.isDisposed();
        }
    }

    public ObservableIgnoreElementsCompletable(ObservableSource<T> source) {
        this.source = source;
    }

    public void subscribeActual(CompletableObserver t) {
        this.source.subscribe(new IgnoreObservable(t));
    }

    public Observable<T> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableIgnoreElements(this.source));
    }
}
