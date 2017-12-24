package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.fuseable.FuseToObservable;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableCountSingle<T> extends Single<Long> implements FuseToObservable<Long> {
    final ObservableSource<T> source;

    static final class CountObserver implements Observer<Object>, Disposable {
        final SingleObserver<? super Long> actual;
        long count;
        Disposable f2679d;

        CountObserver(SingleObserver<? super Long> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2679d, d)) {
                this.f2679d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2679d.dispose();
            this.f2679d = DisposableHelper.DISPOSED;
        }

        public boolean isDisposed() {
            return this.f2679d.isDisposed();
        }

        public void onNext(Object t) {
            this.count++;
        }

        public void onError(Throwable t) {
            this.f2679d = DisposableHelper.DISPOSED;
            this.actual.onError(t);
        }

        public void onComplete() {
            this.f2679d = DisposableHelper.DISPOSED;
            this.actual.onSuccess(Long.valueOf(this.count));
        }
    }

    public ObservableCountSingle(ObservableSource<T> source) {
        this.source = source;
    }

    public void subscribeActual(SingleObserver<? super Long> t) {
        this.source.subscribe(new CountObserver(t));
    }

    public Observable<Long> fuseToObservable() {
        return RxJavaPlugins.onAssembly(new ObservableCount(this.source));
    }
}
