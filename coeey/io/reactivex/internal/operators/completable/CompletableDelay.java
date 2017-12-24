package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;

public final class CompletableDelay extends Completable {
    final long delay;
    final boolean delayError;
    final Scheduler scheduler;
    final CompletableSource source;
    final TimeUnit unit;

    final class Delay implements CompletableObserver {
        final CompletableObserver f2492s;
        private final CompositeDisposable set;

        final class OnComplete implements Runnable {
            OnComplete() {
            }

            public void run() {
                Delay.this.f2492s.onComplete();
            }
        }

        final class OnError implements Runnable {
            private final Throwable f2491e;

            OnError(Throwable e) {
                this.f2491e = e;
            }

            public void run() {
                Delay.this.f2492s.onError(this.f2491e);
            }
        }

        Delay(CompositeDisposable set, CompletableObserver s) {
            this.set = set;
            this.f2492s = s;
        }

        public void onComplete() {
            this.set.add(CompletableDelay.this.scheduler.scheduleDirect(new OnComplete(), CompletableDelay.this.delay, CompletableDelay.this.unit));
        }

        public void onError(Throwable e) {
            this.set.add(CompletableDelay.this.scheduler.scheduleDirect(new OnError(e), CompletableDelay.this.delayError ? CompletableDelay.this.delay : 0, CompletableDelay.this.unit));
        }

        public void onSubscribe(Disposable d) {
            this.set.add(d);
            this.f2492s.onSubscribe(this.set);
        }
    }

    public CompletableDelay(CompletableSource source, long delay, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        this.source = source;
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
        this.delayError = delayError;
    }

    protected void subscribeActual(CompletableObserver s) {
        this.source.subscribe(new Delay(new CompositeDisposable(), s));
    }
}
