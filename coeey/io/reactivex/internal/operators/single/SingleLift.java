package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleOperator;
import io.reactivex.SingleSource;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;

public final class SingleLift<T, R> extends Single<R> {
    final SingleOperator<? extends R, ? super T> onLift;
    final SingleSource<T> source;

    public SingleLift(SingleSource<T> source, SingleOperator<? extends R, ? super T> onLift) {
        this.source = source;
        this.onLift = onLift;
    }

    protected void subscribeActual(SingleObserver<? super R> s) {
        try {
            this.source.subscribe((SingleObserver) ObjectHelper.requireNonNull(this.onLift.apply(s), "The onLift returned a null SingleObserver"));
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptyDisposable.error(ex, (SingleObserver) s);
        }
    }
}
