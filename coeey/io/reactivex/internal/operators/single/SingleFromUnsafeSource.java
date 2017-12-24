package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;

public final class SingleFromUnsafeSource<T> extends Single<T> {
    final SingleSource<T> source;

    public SingleFromUnsafeSource(SingleSource<T> source) {
        this.source = source;
    }

    protected void subscribeActual(SingleObserver<? super T> observer) {
        this.source.subscribe(observer);
    }
}
