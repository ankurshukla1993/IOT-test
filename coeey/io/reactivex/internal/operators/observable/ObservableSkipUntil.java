package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;

public final class ObservableSkipUntil<T, U> extends AbstractObservableWithUpstream<T, T> {
    final ObservableSource<U> other;

    final class SkipUntil implements Observer<U> {
        private final ArrayCompositeDisposable frc;
        Disposable f2726s;
        private final SerializedObserver<T> serial;
        private final SkipUntilObserver<T> sus;

        SkipUntil(ArrayCompositeDisposable frc, SkipUntilObserver<T> sus, SerializedObserver<T> serial) {
            this.frc = frc;
            this.sus = sus;
            this.serial = serial;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2726s, s)) {
                this.f2726s = s;
                this.frc.setResource(1, s);
            }
        }

        public void onNext(U u) {
            this.f2726s.dispose();
            this.sus.notSkipping = true;
        }

        public void onError(Throwable t) {
            this.frc.dispose();
            this.serial.onError(t);
        }

        public void onComplete() {
            this.sus.notSkipping = true;
        }
    }

    static final class SkipUntilObserver<T> implements Observer<T> {
        final Observer<? super T> actual;
        final ArrayCompositeDisposable frc;
        volatile boolean notSkipping;
        boolean notSkippingLocal;
        Disposable f2727s;

        SkipUntilObserver(Observer<? super T> actual, ArrayCompositeDisposable frc) {
            this.actual = actual;
            this.frc = frc;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2727s, s)) {
                this.f2727s = s;
                this.frc.setResource(0, s);
            }
        }

        public void onNext(T t) {
            if (this.notSkippingLocal) {
                this.actual.onNext(t);
            } else if (this.notSkipping) {
                this.notSkippingLocal = true;
                this.actual.onNext(t);
            }
        }

        public void onError(Throwable t) {
            this.frc.dispose();
            this.actual.onError(t);
        }

        public void onComplete() {
            this.frc.dispose();
            this.actual.onComplete();
        }
    }

    public ObservableSkipUntil(ObservableSource<T> source, ObservableSource<U> other) {
        super(source);
        this.other = other;
    }

    public void subscribeActual(Observer<? super T> child) {
        SerializedObserver<T> serial = new SerializedObserver(child);
        ArrayCompositeDisposable frc = new ArrayCompositeDisposable(2);
        serial.onSubscribe(frc);
        SkipUntilObserver<T> sus = new SkipUntilObserver(serial, frc);
        this.other.subscribe(new SkipUntil(frc, sus, serial));
        this.source.subscribe(sus);
    }
}
