package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.concurrent.TimeUnit;

public final class ObservableDelay<T> extends AbstractObservableWithUpstream<T, T> {
    final long delay;
    final boolean delayError;
    final Scheduler scheduler;
    final TimeUnit unit;

    static final class DelayObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        final long delay;
        final boolean delayError;
        Disposable f2683s;
        final TimeUnit unit;
        final Worker f2684w;

        final class OnComplete implements Runnable {
            OnComplete() {
            }

            public void run() {
                try {
                    DelayObserver.this.actual.onComplete();
                } finally {
                    DelayObserver.this.f2684w.dispose();
                }
            }
        }

        final class OnError implements Runnable {
            private final Throwable throwable;

            OnError(Throwable throwable) {
                this.throwable = throwable;
            }

            public void run() {
                try {
                    DelayObserver.this.actual.onError(this.throwable);
                } finally {
                    DelayObserver.this.f2684w.dispose();
                }
            }
        }

        final class OnNext implements Runnable {
            private final T f2682t;

            OnNext(T t) {
                this.f2682t = t;
            }

            public void run() {
                DelayObserver.this.actual.onNext(this.f2682t);
            }
        }

        DelayObserver(Observer<? super T> actual, long delay, TimeUnit unit, Worker w, boolean delayError) {
            this.actual = actual;
            this.delay = delay;
            this.unit = unit;
            this.f2684w = w;
            this.delayError = delayError;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2683s, s)) {
                this.f2683s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.f2684w.schedule(new OnNext(t), this.delay, this.unit);
        }

        public void onError(Throwable t) {
            this.f2684w.schedule(new OnError(t), this.delayError ? this.delay : 0, this.unit);
        }

        public void onComplete() {
            this.f2684w.schedule(new OnComplete(), this.delay, this.unit);
        }

        public void dispose() {
            this.f2683s.dispose();
            this.f2684w.dispose();
        }

        public boolean isDisposed() {
            return this.f2684w.isDisposed();
        }
    }

    public ObservableDelay(ObservableSource<T> source, long delay, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        super(source);
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
        this.delayError = delayError;
    }

    public void subscribeActual(Observer<? super T> t) {
        Observer<T> s;
        if (this.delayError) {
            s = t;
        } else {
            s = new SerializedObserver(t);
        }
        this.source.subscribe(new DelayObserver(s, this.delay, this.unit, this.scheduler.createWorker(), this.delayError));
    }
}
