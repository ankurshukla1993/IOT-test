package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.internal.disposables.EmptyDisposable;

public final class CompletableError extends Completable {
    final Throwable error;

    public CompletableError(Throwable error) {
        this.error = error;
    }

    protected void subscribeActual(CompletableObserver s) {
        EmptyDisposable.error(this.error, s);
    }
}
