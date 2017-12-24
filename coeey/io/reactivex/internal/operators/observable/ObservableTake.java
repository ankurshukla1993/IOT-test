package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.plugins.RxJavaPlugins;

public final class ObservableTake<T> extends AbstractObservableWithUpstream<T, T> {
    final long limit;

    static final class TakeObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        boolean done;
        long remaining;
        Disposable subscription;

        TakeObserver(Observer<? super T> actual, long limit) {
            this.actual = actual;
            this.remaining = limit;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.subscription, s)) {
                this.subscription = s;
                if (this.remaining == 0) {
                    this.done = true;
                    s.dispose();
                    EmptyDisposable.complete(this.actual);
                    return;
                }
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long j = this.remaining;
                this.remaining = j - 1;
                if (j > 0) {
                    boolean stop = this.remaining == 0;
                    this.actual.onNext(t);
                    if (stop) {
                        onComplete();
                    }
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.subscription.dispose();
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.subscription.dispose();
                this.actual.onComplete();
            }
        }

        public void dispose() {
            this.subscription.dispose();
        }

        public boolean isDisposed() {
            return this.subscription.isDisposed();
        }
    }

    public ObservableTake(ObservableSource<T> source, long limit) {
        super(source);
        this.limit = limit;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        this.source.subscribe(new TakeObserver(observer, this.limit));
    }
}
