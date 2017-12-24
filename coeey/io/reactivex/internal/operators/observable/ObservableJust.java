package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.internal.fuseable.ScalarCallable;
import io.reactivex.internal.operators.observable.ObservableScalarXMap.ScalarDisposable;

public final class ObservableJust<T> extends Observable<T> implements ScalarCallable<T> {
    private final T value;

    public ObservableJust(T value) {
        this.value = value;
    }

    protected void subscribeActual(Observer<? super T> s) {
        ScalarDisposable<T> sd = new ScalarDisposable(s, this.value);
        s.onSubscribe(sd);
        sd.run();
    }

    public T call() {
        return this.value;
    }
}
