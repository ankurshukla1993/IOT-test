package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;

public final class ObservableTakeLastOne<T> extends AbstractObservableWithUpstream<T, T> {

    static final class TakeLastOneObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        Disposable f2732s;
        T value;

        TakeLastOneObserver(Observer<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2732s, s)) {
                this.f2732s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.value = t;
        }

        public void onError(Throwable t) {
            this.value = null;
            this.actual.onError(t);
        }

        public void onComplete() {
            emit();
        }

        void emit() {
            T v = this.value;
            if (v != null) {
                this.value = null;
                this.actual.onNext(v);
            }
            this.actual.onComplete();
        }

        public void dispose() {
            this.value = null;
            this.f2732s.dispose();
        }

        public boolean isDisposed() {
            return this.f2732s.isDisposed();
        }
    }

    public ObservableTakeLastOne(ObservableSource<T> source) {
        super(source);
    }

    public void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new TakeLastOneObserver(s));
    }
}
