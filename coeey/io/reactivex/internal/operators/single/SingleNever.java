package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.internal.disposables.EmptyDisposable;

public final class SingleNever extends Single<Object> {
    public static final Single<Object> INSTANCE = new SingleNever();

    private SingleNever() {
    }

    protected void subscribeActual(SingleObserver<? super Object> s) {
        s.onSubscribe(EmptyDisposable.NEVER);
    }
}
