package io.reactivex.internal.operators.observable;

import io.reactivex.Notification;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class ObservableMaterialize<T> extends AbstractObservableWithUpstream<T, Notification<T>> {

    static final class MaterializeObserver<T> implements Observer<T>, Disposable {
        final Observer<? super Notification<T>> actual;
        Disposable f2707s;

        MaterializeObserver(Observer<? super Notification<T>> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2707s, s)) {
                this.f2707s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2707s.dispose();
        }

        public boolean isDisposed() {
            return this.f2707s.isDisposed();
        }

        public void onNext(T t) {
            this.actual.onNext(Notification.createOnNext(t));
        }

        public void onError(Throwable t) {
            this.actual.onNext(Notification.createOnError(t));
            this.actual.onComplete();
        }

        public void onComplete() {
            this.actual.onNext(Notification.createOnComplete());
            this.actual.onComplete();
        }
    }

    public ObservableMaterialize(ObservableSource<T> source) {
        super(source);
    }

    public void subscribeActual(Observer<? super Notification<T>> t) {
        this.source.subscribe(new MaterializeObserver(t));
    }
}
