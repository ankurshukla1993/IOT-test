package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.annotations.Experimental;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

@Experimental
public final class SingleDoAfterSuccess<T> extends Single<T> {
    final Consumer<? super T> onAfterSuccess;
    final SingleSource<T> source;

    static final class DoAfterObserver<T> implements SingleObserver<T>, Disposable {
        final SingleObserver<? super T> actual;
        Disposable f2782d;
        final Consumer<? super T> onAfterSuccess;

        DoAfterObserver(SingleObserver<? super T> actual, Consumer<? super T> onAfterSuccess) {
            this.actual = actual;
            this.onAfterSuccess = onAfterSuccess;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2782d, d)) {
                this.f2782d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onSuccess(T t) {
            this.actual.onSuccess(t);
            try {
                this.onAfterSuccess.accept(t);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                RxJavaPlugins.onError(ex);
            }
        }

        public void onError(Throwable e) {
            this.actual.onError(e);
        }

        public void dispose() {
            this.f2782d.dispose();
        }

        public boolean isDisposed() {
            return this.f2782d.isDisposed();
        }
    }

    public SingleDoAfterSuccess(SingleSource<T> source, Consumer<? super T> onAfterSuccess) {
        this.source = source;
        this.onAfterSuccess = onAfterSuccess;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new DoAfterObserver(s, this.onAfterSuccess));
    }
}
