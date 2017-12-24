package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.CompletableSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class CompletableTimeout extends Completable {
    final CompletableSource other;
    final Scheduler scheduler;
    final CompletableSource source;
    final long timeout;
    final TimeUnit unit;

    final class DisposeTask implements Runnable {
        private final AtomicBoolean once;
        final CompletableObserver f2502s;
        final CompositeDisposable set;

        final class DisposeObserver implements CompletableObserver {
            DisposeObserver() {
            }

            public void onSubscribe(Disposable d) {
                DisposeTask.this.set.add(d);
            }

            public void onError(Throwable e) {
                DisposeTask.this.set.dispose();
                DisposeTask.this.f2502s.onError(e);
            }

            public void onComplete() {
                DisposeTask.this.set.dispose();
                DisposeTask.this.f2502s.onComplete();
            }
        }

        DisposeTask(AtomicBoolean once, CompositeDisposable set, CompletableObserver s) {
            this.once = once;
            this.set = set;
            this.f2502s = s;
        }

        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.set.clear();
                if (CompletableTimeout.this.other == null) {
                    this.f2502s.onError(new TimeoutException());
                } else {
                    CompletableTimeout.this.other.subscribe(new DisposeObserver());
                }
            }
        }
    }

    static final class TimeOutObserver implements CompletableObserver {
        private final AtomicBoolean once;
        private final CompletableObserver f2503s;
        private final CompositeDisposable set;

        TimeOutObserver(CompositeDisposable set, AtomicBoolean once, CompletableObserver s) {
            this.set = set;
            this.once = once;
            this.f2503s = s;
        }

        public void onSubscribe(Disposable d) {
            this.set.add(d);
        }

        public void onError(Throwable e) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.f2503s.onError(e);
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.f2503s.onComplete();
            }
        }
    }

    public CompletableTimeout(CompletableSource source, long timeout, TimeUnit unit, Scheduler scheduler, CompletableSource other) {
        this.source = source;
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }

    public void subscribeActual(CompletableObserver s) {
        CompositeDisposable set = new CompositeDisposable();
        s.onSubscribe(set);
        AtomicBoolean once = new AtomicBoolean();
        set.add(this.scheduler.scheduleDirect(new DisposeTask(once, set, s), this.timeout, this.unit));
        this.source.subscribe(new TimeOutObserver(set, once, s));
    }
}
