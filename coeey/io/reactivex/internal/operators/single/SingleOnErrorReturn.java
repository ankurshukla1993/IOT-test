package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;

public final class SingleOnErrorReturn<T> extends Single<T> {
    final SingleSource<? extends T> source;
    final T value;
    final Function<? super Throwable, ? extends T> valueSupplier;

    final class OnErrorReturn implements SingleObserver<T> {
        private final SingleObserver<? super T> observer;

        OnErrorReturn(SingleObserver<? super T> observer) {
            this.observer = observer;
        }

        public void onError(Throwable e) {
            if (SingleOnErrorReturn.this.valueSupplier != null) {
                try {
                    T v = SingleOnErrorReturn.this.valueSupplier.apply(e);
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    this.observer.onError(new CompositeException(e, ex));
                    return;
                }
            }
            v = SingleOnErrorReturn.this.value;
            if (v == null) {
                NullPointerException npe = new NullPointerException("Value supplied was null");
                npe.initCause(e);
                this.observer.onError(npe);
                return;
            }
            this.observer.onSuccess(v);
        }

        public void onSubscribe(Disposable d) {
            this.observer.onSubscribe(d);
        }

        public void onSuccess(T value) {
            this.observer.onSuccess(value);
        }
    }

    public SingleOnErrorReturn(SingleSource<? extends T> source, Function<? super Throwable, ? extends T> valueSupplier, T value) {
        this.source = source;
        this.valueSupplier = valueSupplier;
        this.value = value;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new OnErrorReturn(s));
    }
}
