package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.plugins.RxJavaPlugins;

public final class CompletableDisposeOn extends Completable {
    final Scheduler scheduler;
    final CompletableSource source;

    static final class CompletableObserverImplementation implements CompletableObserver, Disposable, Runnable {
        Disposable f2493d;
        volatile boolean disposed;
        final CompletableObserver f2494s;
        final Scheduler scheduler;

        CompletableObserverImplementation(CompletableObserver s, Scheduler scheduler) {
            this.f2494s = s;
            this.scheduler = scheduler;
        }

        public void onComplete() {
            if (!this.disposed) {
                this.f2494s.onComplete();
            }
        }

        public void onError(Throwable e) {
            if (this.disposed) {
                RxJavaPlugins.onError(e);
            } else {
                this.f2494s.onError(e);
            }
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2493d, d)) {
                this.f2493d = d;
                this.f2494s.onSubscribe(this);
            }
        }

        public void dispose() {
            this.disposed = true;
            this.scheduler.scheduleDirect(this);
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        public void run() {
            this.f2493d.dispose();
            this.f2493d = DisposableHelper.DISPOSED;
        }
    }

    public CompletableDisposeOn(CompletableSource source, Scheduler scheduler) {
        this.source = source;
        this.scheduler = scheduler;
    }

    protected void subscribeActual(CompletableObserver s) {
        this.source.subscribe(new CompletableObserverImplementation(s, this.scheduler));
    }
}
