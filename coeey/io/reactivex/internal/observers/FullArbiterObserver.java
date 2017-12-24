package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ObserverFullArbiter;

public final class FullArbiterObserver<T> implements Observer<T> {
    final ObserverFullArbiter<T> arbiter;
    Disposable f2485s;

    public FullArbiterObserver(ObserverFullArbiter<T> arbiter) {
        this.arbiter = arbiter;
    }

    public void onSubscribe(Disposable s) {
        if (DisposableHelper.validate(this.f2485s, s)) {
            this.f2485s = s;
            this.arbiter.setDisposable(s);
        }
    }

    public void onNext(T t) {
        this.arbiter.onNext(t, this.f2485s);
    }

    public void onError(Throwable t) {
        this.arbiter.onError(t, this.f2485s);
    }

    public void onComplete() {
        this.arbiter.onComplete(this.f2485s);
    }
}
