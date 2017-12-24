package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;

public final class SingleDoOnSuccess<T> extends Single<T> {
    final Consumer<? super T> onSuccess;
    final SingleSource<T> source;

    final class DoOnSuccess implements SingleObserver<T> {
        private final SingleObserver<? super T> f2788s;

        DoOnSuccess(SingleObserver<? super T> s) {
            this.f2788s = s;
        }

        public void onSubscribe(Disposable d) {
            this.f2788s.onSubscribe(d);
        }

        public void onSuccess(T value) {
            try {
                SingleDoOnSuccess.this.onSuccess.accept(value);
                this.f2788s.onSuccess(value);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.f2788s.onError(ex);
            }
        }

        public void onError(Throwable e) {
            this.f2788s.onError(e);
        }
    }

    public SingleDoOnSuccess(SingleSource<T> source, Consumer<? super T> onSuccess) {
        this.source = source;
        this.onSuccess = onSuccess;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new DoOnSuccess(s));
    }
}
