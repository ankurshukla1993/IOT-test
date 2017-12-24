package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposables;

public final class SingleJust<T> extends Single<T> {
    final T value;

    public SingleJust(T value) {
        this.value = value;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        s.onSubscribe(Disposables.disposed());
        s.onSuccess(this.value);
    }
}
