package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;

public final class SingleDoOnError<T> extends Single<T> {
    final Consumer<? super Throwable> onError;
    final SingleSource<T> source;

    final class DoOnError implements SingleObserver<T> {
        private final SingleObserver<? super T> f2786s;

        DoOnError(SingleObserver<? super T> s) {
            this.f2786s = s;
        }

        public void onSubscribe(Disposable d) {
            this.f2786s.onSubscribe(d);
        }

        public void onSuccess(T value) {
            this.f2786s.onSuccess(value);
        }

        public void onError(Throwable e) {
            try {
                SingleDoOnError.this.onError.accept(e);
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                e = new CompositeException(e, ex);
            }
            this.f2786s.onError(e);
        }
    }

    public SingleDoOnError(SingleSource<T> source, Consumer<? super Throwable> onError) {
        this.source = source;
        this.onError = onError;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new DoOnError(s));
    }
}
