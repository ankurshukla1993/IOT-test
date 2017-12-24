package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.SequentialDisposable;
import java.util.concurrent.TimeUnit;

public final class SingleDelay<T> extends Single<T> {
    final Scheduler scheduler;
    final SingleSource<? extends T> source;
    final long time;
    final TimeUnit unit;

    final class Delay implements SingleObserver<T> {
        final SingleObserver<? super T> f2780s;
        private final SequentialDisposable sd;

        final class OnError implements Runnable {
            private final Throwable f2779e;

            OnError(Throwable e) {
                this.f2779e = e;
            }

            public void run() {
                Delay.this.f2780s.onError(this.f2779e);
            }
        }

        final class OnSuccess implements Runnable {
            private final T value;

            OnSuccess(T value) {
                this.value = value;
            }

            public void run() {
                Delay.this.f2780s.onSuccess(this.value);
            }
        }

        Delay(SequentialDisposable sd, SingleObserver<? super T> s) {
            this.sd = sd;
            this.f2780s = s;
        }

        public void onSubscribe(Disposable d) {
            this.sd.replace(d);
        }

        public void onSuccess(T value) {
            this.sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnSuccess(value), SingleDelay.this.time, SingleDelay.this.unit));
        }

        public void onError(Throwable e) {
            this.sd.replace(SingleDelay.this.scheduler.scheduleDirect(new OnError(e), 0, SingleDelay.this.unit));
        }
    }

    public SingleDelay(SingleSource<? extends T> source, long time, TimeUnit unit, Scheduler scheduler) {
        this.source = source;
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        SequentialDisposable sd = new SequentialDisposable();
        s.onSubscribe(sd);
        this.source.subscribe(new Delay(sd, s));
    }
}
