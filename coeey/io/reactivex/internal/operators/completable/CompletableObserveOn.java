package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableObserveOn extends Completable {
    final Scheduler scheduler;
    final CompletableSource source;

    static final class ObserveOnCompletableObserver extends AtomicReference<Disposable> implements CompletableObserver, Disposable, Runnable {
        private static final long serialVersionUID = 8571289934935992137L;
        final CompletableObserver actual;
        Throwable error;
        final Scheduler scheduler;

        ObserveOnCompletableObserver(CompletableObserver actual, Scheduler scheduler) {
            this.actual = actual;
            this.scheduler = scheduler;
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return DisposableHelper.isDisposed((Disposable) get());
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.setOnce(this, d)) {
                this.actual.onSubscribe(this);
            }
        }

        public void onError(Throwable e) {
            this.error = e;
            DisposableHelper.replace(this, this.scheduler.scheduleDirect(this));
        }

        public void onComplete() {
            DisposableHelper.replace(this, this.scheduler.scheduleDirect(this));
        }

        public void run() {
            Throwable ex = this.error;
            if (ex != null) {
                this.error = null;
                this.actual.onError(ex);
                return;
            }
            this.actual.onComplete();
        }
    }

    public CompletableObserveOn(CompletableSource source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    protected void subscribeActual(CompletableObserver s) {
        this.source.subscribe(new ObserveOnCompletableObserver(s, this.scheduler));
    }
}
