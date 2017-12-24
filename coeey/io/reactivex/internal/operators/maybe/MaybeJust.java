package io.reactivex.internal.operators.maybe;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposables;
import io.reactivex.internal.fuseable.ScalarCallable;

public final class MaybeJust<T> extends Maybe<T> implements ScalarCallable<T> {
    final T value;

    public MaybeJust(T value) {
        this.value = value;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        observer.onSubscribe(Disposables.disposed());
        observer.onSuccess(this.value);
    }

    public T call() {
        return this.value;
    }
}
