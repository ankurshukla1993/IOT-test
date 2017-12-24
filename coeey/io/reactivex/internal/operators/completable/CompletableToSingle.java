package io.reactivex.internal.operators.completable;

import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import java.util.concurrent.Callable;

public final class CompletableToSingle<T> extends Single<T> {
    final T completionValue;
    final Callable<? extends T> completionValueSupplier;
    final CompletableSource source;

    final class ToSingle implements CompletableObserver {
        private final SingleObserver<? super T> observer;

        ToSingle(SingleObserver<? super T> observer) {
            this.observer = observer;
        }

        public void onComplete() {
            if (CompletableToSingle.this.completionValueSupplier != null) {
                try {
                    T v = CompletableToSingle.this.completionValueSupplier.call();
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.observer.onError(e);
                    return;
                }
            }
            v = CompletableToSingle.this.completionValue;
            if (v == null) {
                this.observer.onError(new NullPointerException("The value supplied is null"));
            } else {
                this.observer.onSuccess(v);
            }
        }

        public void onError(Throwable e) {
            this.observer.onError(e);
        }

        public void onSubscribe(Disposable d) {
            this.observer.onSubscribe(d);
        }
    }

    public CompletableToSingle(CompletableSource source, Callable<? extends T> completionValueSupplier, T completionValue) {
        this.source = source;
        this.completionValue = completionValue;
        this.completionValueSupplier = completionValueSupplier;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        this.source.subscribe(new ToSingle(s));
    }
}
