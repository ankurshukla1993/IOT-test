package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.ObserverFullArbiter;
import io.reactivex.internal.observers.FullArbiterObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableTimeoutTimed<T> extends AbstractObservableWithUpstream<T, T> {
    static final Disposable NEW_TIMER = new EmptyDisposable();
    final ObservableSource<? extends T> other;
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    static final class EmptyDisposable implements Disposable {
        EmptyDisposable() {
        }

        public void dispose() {
        }

        public boolean isDisposed() {
            return true;
        }
    }

    static final class TimeoutTimedObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -8387234228317808253L;
        final Observer<? super T> actual;
        volatile boolean done;
        volatile long index;
        Disposable f2741s;
        final long timeout;
        final TimeUnit unit;
        final Worker worker;

        final class TimeoutTask implements Runnable {
            private final long idx;

            TimeoutTask(long idx) {
                this.idx = idx;
            }

            public void run() {
                if (this.idx == TimeoutTimedObserver.this.index) {
                    TimeoutTimedObserver.this.done = true;
                    TimeoutTimedObserver.this.f2741s.dispose();
                    DisposableHelper.dispose(TimeoutTimedObserver.this);
                    TimeoutTimedObserver.this.actual.onError(new TimeoutException());
                    TimeoutTimedObserver.this.worker.dispose();
                }
            }
        }

        TimeoutTimedObserver(Observer<? super T> actual, long timeout, TimeUnit unit, Worker worker) {
            this.actual = actual;
            this.timeout = timeout;
            this.unit = unit;
            this.worker = worker;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2741s, s)) {
                this.f2741s = s;
                this.actual.onSubscribe(this);
                scheduleTimeout(0);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long idx = this.index + 1;
                this.index = idx;
                this.actual.onNext(t);
                scheduleTimeout(idx);
            }
        }

        void scheduleTimeout(long idx) {
            Disposable d = (Disposable) get();
            if (d != null) {
                d.dispose();
            }
            if (compareAndSet(d, ObservableTimeoutTimed.NEW_TIMER)) {
                DisposableHelper.replace(this, this.worker.schedule(new TimeoutTask(idx), this.timeout, this.unit));
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
            dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
                dispose();
            }
        }

        public void dispose() {
            this.f2741s.dispose();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }

    static final class TimeoutTimedOtherObserver<T> extends AtomicReference<Disposable> implements Observer<T>, Disposable {
        private static final long serialVersionUID = -4619702551964128179L;
        final Observer<? super T> actual;
        final ObserverFullArbiter<T> arbiter;
        volatile boolean done;
        volatile long index;
        final ObservableSource<? extends T> other;
        Disposable f2742s;
        final long timeout;
        final TimeUnit unit;
        final Worker worker;

        final class SubscribeNext implements Runnable {
            private final long idx;

            SubscribeNext(long idx) {
                this.idx = idx;
            }

            public void run() {
                if (this.idx == TimeoutTimedOtherObserver.this.index) {
                    TimeoutTimedOtherObserver.this.done = true;
                    TimeoutTimedOtherObserver.this.f2742s.dispose();
                    DisposableHelper.dispose(TimeoutTimedOtherObserver.this);
                    TimeoutTimedOtherObserver.this.subscribeNext();
                    TimeoutTimedOtherObserver.this.worker.dispose();
                }
            }
        }

        TimeoutTimedOtherObserver(Observer<? super T> actual, long timeout, TimeUnit unit, Worker worker, ObservableSource<? extends T> other) {
            this.actual = actual;
            this.timeout = timeout;
            this.unit = unit;
            this.worker = worker;
            this.other = other;
            this.arbiter = new ObserverFullArbiter(actual, this, 8);
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2742s, s)) {
                this.f2742s = s;
                if (this.arbiter.setDisposable(s)) {
                    this.actual.onSubscribe(this.arbiter);
                    scheduleTimeout(0);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long idx = this.index + 1;
                this.index = idx;
                if (this.arbiter.onNext(t, this.f2742s)) {
                    scheduleTimeout(idx);
                }
            }
        }

        void scheduleTimeout(long idx) {
            Disposable d = (Disposable) get();
            if (d != null) {
                d.dispose();
            }
            if (compareAndSet(d, ObservableTimeoutTimed.NEW_TIMER)) {
                DisposableHelper.replace(this, this.worker.schedule(new SubscribeNext(idx), this.timeout, this.unit));
            }
        }

        void subscribeNext() {
            this.other.subscribe(new FullArbiterObserver(this.arbiter));
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.arbiter.onError(t, this.f2742s);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.arbiter.onComplete(this.f2742s);
                this.worker.dispose();
            }
        }

        public void dispose() {
            this.f2742s.dispose();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }

    public ObservableTimeoutTimed(ObservableSource<T> source, long timeout, TimeUnit unit, Scheduler scheduler, ObservableSource<? extends T> other) {
        super(source);
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }

    public void subscribeActual(Observer<? super T> t) {
        if (this.other == null) {
            this.source.subscribe(new TimeoutTimedObserver(new SerializedObserver(t), this.timeout, this.unit, this.scheduler.createWorker()));
            return;
        }
        this.source.subscribe(new TimeoutTimedOtherObserver(t, this.timeout, this.unit, this.scheduler.createWorker(), this.other));
    }
}
