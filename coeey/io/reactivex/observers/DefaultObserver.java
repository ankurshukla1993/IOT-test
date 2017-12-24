package io.reactivex.observers;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;

public abstract class DefaultObserver<T> implements Observer<T> {
    private Disposable f2654s;

    public final void onSubscribe(@NonNull Disposable s) {
        if (EndConsumerHelper.validate(this.f2654s, s, getClass())) {
            this.f2654s = s;
            onStart();
        }
    }

    protected final void cancel() {
        Disposable s = this.f2654s;
        this.f2654s = DisposableHelper.DISPOSED;
        s.dispose();
    }

    protected void onStart() {
    }
}
