package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class SingleDoAfterTerminate<T> extends Single<T> {
    final Action onAfterTerminate;
    final SingleSource<T> source;

    static final class DoAfterTerminateObserver<T> implements SingleObserver<T>, Disposable {
        final SingleObserver<? super T> actual;
        Disposable f2783d;
        final Action onAfterTerminate;

        DoAfterTerminateObserver(SingleObserver<? super T> actual, Action onAfterTerminate) {
            this.actual = actual;
            this.onAfterTerminate = onAfterTerminate;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2783d, d)) {
                this.f2783d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
            onAfterTerminate();
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
            onAfterTerminate();
        }

        public void dispose() {
            this.f2783d.dispose();
        }

        public boolean isDisposed() {
            return this.f2783d.isDisposed();
        }

        private void onAfterTerminate() {
            try {
                this.onAfterTerminate.run();
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaPlugins.onError(ex);
            }
        }
    }

    public SingleDoAfterTerminate(SingleSource<T> source, Action onAfterTerminate) {
        this.source = source;
        this.onAfterTerminate = onAfterTerminate;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new DoAfterTerminateObserver(s, this.onAfterTerminate));
    }
}
