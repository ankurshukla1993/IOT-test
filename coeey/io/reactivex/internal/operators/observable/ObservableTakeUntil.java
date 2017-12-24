package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.ArrayCompositeDisposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.concurrent.atomic.AtomicBoolean;

public final class ObservableTakeUntil<T, U> extends AbstractObservableWithUpstream<T, T> {
    final ObservableSource<? extends U> other;

    final class TakeUntil implements Observer<U> {
        private final ArrayCompositeDisposable frc;
        private final SerializedObserver<T> serial;

        TakeUntil(ArrayCompositeDisposable frc, SerializedObserver<T> serial) {
            this.frc = frc;
            this.serial = serial;
        }

        public void onSubscribe(Disposable s) {
            this.frc.setResource(1, s);
        }

        public void onNext(U u) {
            this.frc.dispose();
            this.serial.onComplete();
        }

        public void onError(Throwable t) {
            this.frc.dispose();
            this.serial.onError(t);
        }

        public void onComplete() {
            this.frc.dispose();
            this.serial.onComplete();
        }
    }

    static final class TakeUntilObserver<T> extends AtomicBoolean implements Observer<T> {
        private static final long serialVersionUID = 3451719290311127173L;
        final Observer<? super T> actual;
        final ArrayCompositeDisposable frc;
        Disposable f2734s;

        TakeUntilObserver(Observer<? super T> actual, ArrayCompositeDisposable frc) {
            this.actual = actual;
            this.frc = frc;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2734s, s)) {
                this.f2734s = s;
                this.frc.setResource(0, s);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
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

    public ObservableTakeUntil(ObservableSource<T> source, ObservableSource<? extends U> other) {
        super(source);
        this.other = other;
    }

    public void subscribeActual(Observer<? super T> child) {
        SerializedObserver<T> serial = new SerializedObserver(child);
        ArrayCompositeDisposable frc = new ArrayCompositeDisposable(2);
        TakeUntilObserver<T> tus = new TakeUntilObserver(serial, frc);
        child.onSubscribe(frc);
        this.other.subscribe(new TakeUntil(frc, serial));
        this.source.subscribe(tus);
    }
}
