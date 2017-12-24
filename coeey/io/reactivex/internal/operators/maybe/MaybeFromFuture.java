package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class MaybeFromFuture<T> extends Maybe<T> {
    final Future<? extends T> future;
    final long timeout;
    final TimeUnit unit;

    public MaybeFromFuture(Future<? extends T> future, long timeout, TimeUnit unit) {
        this.future = future;
        this.timeout = timeout;
        this.unit = unit;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        Disposable d = Disposables.empty();
        observer.onSubscribe(d);
        if (!d.isDisposed()) {
            try {
                T v;
                if (this.timeout <= 0) {
                    v = this.future.get();
                } else {
                    v = this.future.get(this.timeout, this.unit);
                }
                if (!d.isDisposed()) {
                    if (v == null) {
                        observer.onComplete();
                    } else {
                        observer.onSuccess(v);
                    }
                }
            } catch (InterruptedException ex) {
                if (!d.isDisposed()) {
                    observer.onError(ex);
                }
            } catch (ExecutionException ex2) {
                if (!d.isDisposed()) {
                    observer.onError(ex2.getCause());
                }
            } catch (TimeoutException ex3) {
                if (!d.isDisposed()) {
                    observer.onError(ex3);
                }
            }
        }
    }
}
