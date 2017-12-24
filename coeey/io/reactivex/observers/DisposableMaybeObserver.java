package io.reactivex.observers;

import io.reactivex.MaybeObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;

public abstract class DisposableMaybeObserver<T> implements MaybeObserver<T>, Disposable {
    final AtomicReference<Disposable> f2811s = new AtomicReference();

    public final void onSubscribe(@NonNull Disposable s) {
        if (EndConsumerHelper.setOnce(this.f2811s, s, getClass())) {
            onStart();
        }
    }

    protected void onStart() {
    }

    public final boolean isDisposed() {
        return this.f2811s.get() == DisposableHelper.DISPOSED;
    }

    public final void dispose() {
        DisposableHelper.dispose(this.f2811s);
    }
}
