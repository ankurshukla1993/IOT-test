package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;

public final class ObservableFromUnsafeSource<T> extends Observable<T> {
    final ObservableSource<T> source;

    public ObservableFromUnsafeSource(ObservableSource<T> source) {
        this.source = source;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(observer);
    }
}
