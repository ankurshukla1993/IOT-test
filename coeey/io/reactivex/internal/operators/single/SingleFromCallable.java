package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import java.util.concurrent.Callable;

public final class SingleFromCallable<T> extends Single<T> {
    final Callable<? extends T> callable;

    public SingleFromCallable(Callable<? extends T> callable) {
        this.callable = callable;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        s.onSubscribe(EmptyDisposable.INSTANCE);
        try {
            T v = this.callable.call();
            if (v != null) {
                s.onSuccess(v);
            } else {
                s.onError(new NullPointerException("The callable returned a null value"));
            }
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            s.onError(e);
        }
    }
}
