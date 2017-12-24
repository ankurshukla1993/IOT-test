package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class ObservableCount<T> extends AbstractObservableWithUpstream<T, Long> {

    static final class CountObserver implements Observer<Object>, Disposable {
        final Observer<? super Long> actual;
        long count;
        Disposable f2678s;

        CountObserver(Observer<? super Long> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2678s, s)) {
                this.f2678s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2678s.dispose();
        }

        public boolean isDisposed() {
            return this.f2678s.isDisposed();
        }

        public void onNext(Object t) {
            this.count++;
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onNext(Long.valueOf(this.count));
            this.actual.onComplete();
        }
    }

    public ObservableCount(ObservableSource<T> source) {
        super(source);
    }

    public void subscribeActual(Observer<? super Long> t) {
        this.source.subscribe(new CountObserver(t));
    }
}
