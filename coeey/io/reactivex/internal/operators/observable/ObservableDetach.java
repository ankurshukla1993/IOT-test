package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.EmptyComponent;

public final class ObservableDetach<T> extends AbstractObservableWithUpstream<T, T> {

    static final class DetachObserver<T> implements Observer<T>, Disposable {
        Observer<? super T> actual;
        Disposable f2686s;

        DetachObserver(Observer<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            Disposable s = this.f2686s;
            this.f2686s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asObserver();
            s.dispose();
        }

        public boolean isDisposed() {
            return this.f2686s.isDisposed();
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2686s, s)) {
                this.f2686s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            Observer<? super T> a = this.actual;
            this.f2686s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asObserver();
            a.onError(t);
        }

        public void onComplete() {
            Observer<? super T> a = this.actual;
            this.f2686s = EmptyComponent.INSTANCE;
            this.actual = EmptyComponent.asObserver();
            a.onComplete();
        }
    }

    public ObservableDetach(ObservableSource<T> source) {
        super(source);
    }

    protected void subscribeActual(Observer<? super T> s) {
        this.source.subscribe(new DetachObserver(s));
    }
}
