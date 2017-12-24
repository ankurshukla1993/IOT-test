package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class SingleHide<T> extends Single<T> {
    final SingleSource<? extends T> source;

    static final class HideSingleObserver<T> implements SingleObserver<T>, Disposable {
        final SingleObserver<? super T> actual;
        Disposable f2793d;

        HideSingleObserver(SingleObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2793d.dispose();
        }

        public boolean isDisposed() {
            return this.f2793d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2793d, d)) {
                this.f2793d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T value) {
            this.actual.onSuccess(value);
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }
    }

    public SingleHide(SingleSource<? extends T> source) {
        this.source = source;
    }

    protected void subscribeActual(SingleObserver<? super T> subscriber) {
        this.source.subscribe(new HideSingleObserver(subscriber));
    }
}
