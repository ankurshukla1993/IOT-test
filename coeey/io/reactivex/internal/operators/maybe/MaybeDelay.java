package io.reactivex.internal.operators.maybe;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeDelay<T> extends AbstractMaybeWithUpstream<T, T> {
    final long delay;
    final Scheduler scheduler;
    final TimeUnit unit;

    static final class DelayMaybeObserver<T> extends AtomicReference<Disposable> implements MaybeObserver<T>, Disposable, Runnable {
        private static final long serialVersionUID = 5566860102500855068L;
        final MaybeObserver<? super T> actual;
        final long delay;
        Throwable error;
        final Scheduler scheduler;
        final TimeUnit unit;
        T value;

        DelayMaybeObserver(MaybeObserver<? super T> actual, long delay, TimeUnit unit, Scheduler scheduler) {
            this.actual = actual;
            this.delay = delay;
            this.unit = unit;
            this.scheduler = scheduler;
        }

        public void run() {
            Throwable ex = this.error;
            if (ex != null) {
                this.actual.onError(ex);
                return;
            }
            T v = this.value;
            if (v != null) {
                this.actual.onSuccess(v);
            } else {
                this.actual.onComplete();
            }
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

        public void onSuccess(T value) {
            this.value = value;
            schedule();
        }

        public void onError(Throwable e) {
            this.error = e;
            schedule();
        }

        public void onComplete() {
            schedule();
        }

        void schedule() {
            DisposableHelper.replace(this, this.scheduler.scheduleDirect(this, this.delay, this.unit));
        }
    }

    public MaybeDelay(MaybeSource<T> source, long delay, TimeUnit unit, Scheduler scheduler) {
        super(source);
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new DelayMaybeObserver(observer, this.delay, this.unit, this.scheduler));
    }
}
