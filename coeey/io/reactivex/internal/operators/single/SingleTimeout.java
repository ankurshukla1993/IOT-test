package io.reactivex.internal.operators.single;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicBoolean;

public final class SingleTimeout<T> extends Single<T> {
    final SingleSource<? extends T> other;
    final Scheduler scheduler;
    final SingleSource<T> source;
    final long timeout;
    final TimeUnit unit;

    final class TimeoutDispose implements Runnable {
        private final AtomicBoolean once;
        final SingleObserver<? super T> f2795s;
        final CompositeDisposable set;

        final class TimeoutObserver implements SingleObserver<T> {
            TimeoutObserver() {
            }

            public void onError(Throwable e) {
                TimeoutDispose.this.set.dispose();
                TimeoutDispose.this.f2795s.onError(e);
            }

            public void onSubscribe(Disposable d) {
                TimeoutDispose.this.set.add(d);
            }

            public void onSuccess(T value) {
                TimeoutDispose.this.set.dispose();
                TimeoutDispose.this.f2795s.onSuccess(value);
            }
        }

        TimeoutDispose(AtomicBoolean once, CompositeDisposable set, SingleObserver<? super T> s) {
            this.once = once;
            this.set = set;
            this.f2795s = s;
        }

        public void run() {
            if (!this.once.compareAndSet(false, true)) {
                return;
            }
            if (SingleTimeout.this.other != null) {
                this.set.clear();
                SingleTimeout.this.other.subscribe(new TimeoutObserver());
                return;
            }
            this.set.dispose();
            this.f2795s.onError(new TimeoutException());
        }
    }

    final class TimeoutObserver implements SingleObserver<T> {
        private final AtomicBoolean once;
        private final SingleObserver<? super T> f2796s;
        private final CompositeDisposable set;

        TimeoutObserver(AtomicBoolean once, CompositeDisposable set, SingleObserver<? super T> s) {
            this.once = once;
            this.set = set;
            this.f2796s = s;
        }

        public void onError(Throwable e) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.f2796s.onError(e);
            }
        }

        public void onSubscribe(Disposable d) {
            this.set.add(d);
        }

        public void onSuccess(T value) {
            if (this.once.compareAndSet(false, true)) {
                this.set.dispose();
                this.f2796s.onSuccess(value);
            }
        }
    }

    public SingleTimeout(SingleSource<T> source, long timeout, TimeUnit unit, Scheduler scheduler, SingleSource<? extends T> other) {
        this.source = source;
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        CompositeDisposable set = new CompositeDisposable();
        s.onSubscribe(set);
        AtomicBoolean once = new AtomicBoolean();
        set.add(this.scheduler.scheduleDirect(new TimeoutDispose(once, set, s), this.timeout, this.unit));
        this.source.subscribe(new TimeoutObserver(once, set, s));
    }
}
