package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposables;

public final class MaybeError<T> extends Maybe<T> {
    final Throwable error;

    public MaybeError(Throwable error) {
        this.error = error;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        observer.onSubscribe(Disposables.disposed());
        observer.onError(this.error);
    }
}
