package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableTimer extends Completable {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    static final class TimerDisposable extends AtomicReference<Disposable> implements Disposable, Runnable {
        private static final long serialVersionUID = 3167244060586201109L;
        final CompletableObserver actual;

        TimerDisposable(CompletableObserver actual) {
            this.actual = actual;
        }

        public void run() {
            this.actual.onComplete();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        void setFuture(Disposable d) {
            DisposableHelper.replace(this, d);
        }
    }

    public CompletableTimer(long delay, TimeUnit unit, Scheduler scheduler) {
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    protected void subscribeActual(CompletableObserver s) {
        TimerDisposable parent = new TimerDisposable(s);
        s.onSubscribe(parent);
        parent.setFuture(this.scheduler.scheduleDirect(parent, this.delay, this.unit));
    }
}
