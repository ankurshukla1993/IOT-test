package io.reactivex.internal.operators.observable;

import io.reactivex.Notification;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableDematerialize<T> extends AbstractObservableWithUpstream<Notification<T>, T> {

    static final class DematerializeObserver<T> implements Observer<Notification<T>>, Disposable {
        final Observer<? super T> actual;
        boolean done;
        Disposable f2685s;

        DematerializeObserver(Observer<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2685s, s)) {
                this.f2685s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void dispose() {
            this.f2685s.dispose();
        }

        public boolean isDisposed() {
            return this.f2685s.isDisposed();
        }

        public void onNext(Notification<T> t) {
            if (this.done) {
                if (t.isOnError()) {
                    RxJavaPlugins.onError(t.getError());
                }
            } else if (t.isOnError()) {
                this.f2685s.dispose();
                onError(t.getError());
            } else if (t.isOnComplete()) {
                this.f2685s.dispose();
                onComplete();
            } else {
                this.actual.onNext(t.getValue());
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }
    }

    public ObservableDematerialize(ObservableSource<Notification<T>> source) {
        super(source);
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new DematerializeObserver(t));
    }
}
