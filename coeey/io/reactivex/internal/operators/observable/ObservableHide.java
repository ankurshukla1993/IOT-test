package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class ObservableHide<T> extends AbstractObservableWithUpstream<T, T> {

    static final class HideDisposable<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        Disposable f2700d;

        HideDisposable(Observer<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2700d.dispose();
        }

        public boolean isDisposed() {
            return this.f2700d.isDisposed();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2700d, d)) {
                this.f2700d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }
    }

    public ObservableHide(ObservableSource<T> source) {
        super(source);
    }

    protected void subscribeActual(Observer<? super T> o) {
        this.source.subscribe(new HideDisposable(o));
    }
}
