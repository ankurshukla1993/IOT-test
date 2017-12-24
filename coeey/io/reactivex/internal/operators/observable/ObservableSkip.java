package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public final class ObservableSkip<T> extends AbstractObservableWithUpstream<T, T> {
    final long f2723n;

    static final class SkipObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        Disposable f2722d;
        long remaining;

        SkipObserver(Observer<? super T> actual, long n) {
            this.actual = actual;
            this.remaining = n;
        }

        public void onSubscribe(Disposable s) {
            this.f2722d = s;
            this.actual.onSubscribe(this);
        }

        public void onNext(T t) {
            if (this.remaining != 0) {
                this.remaining--;
            } else {
                this.actual.onNext(t);
            }
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void dispose() {
            this.f2722d.dispose();
        }

        public boolean isDisposed() {
            return this.f2722d.isDisposed();
        }
    }

    public ObservableSkip(ObservableSource<T> source, long n) {
        super(source);
        this.f2723n = n;
    }

    public void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new SkipObserver(s, this.f2723n));
    }
}
