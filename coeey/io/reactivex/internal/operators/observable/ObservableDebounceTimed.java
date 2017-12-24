package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableDebounceTimed<T> extends AbstractObservableWithUpstream<T, T> {
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    static final class DebounceEmitter<T> extends AtomicReference<Disposable> implements Runnable, Disposable {
        private static final long serialVersionUID = 6812032969491025141L;
        final long idx;
        final AtomicBoolean once = new AtomicBoolean();
        final DebounceTimedObserver<T> parent;
        final T value;

        DebounceEmitter(T value, long idx, DebounceTimedObserver<T> parent) {
            this.value = value;
            this.idx = idx;
            this.parent = parent;
        }

        public void run() {
            if (this.once.compareAndSet(false, true)) {
                this.parent.emit(this.idx, this.value, this);
            }
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }

        public boolean isDisposed() {
            return get() == DisposableHelper.DISPOSED;
        }

        public void setResource(Disposable d) {
            DisposableHelper.replace(this, d);
        }
    }

    static final class DebounceTimedObserver<T> implements Observer<T>, Disposable {
        final Observer<? super T> actual;
        boolean done;
        volatile long index;
        Disposable f2681s;
        final long timeout;
        final AtomicReference<Disposable> timer = new AtomicReference();
        final TimeUnit unit;
        final Worker worker;

        DebounceTimedObserver(Observer<? super T> actual, long timeout, TimeUnit unit, Worker worker) {
            this.actual = actual;
            this.timeout = timeout;
            this.unit = unit;
            this.worker = worker;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2681s, s)) {
                this.f2681s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long idx = this.index + 1;
                this.index = idx;
                Disposable d = (Disposable) this.timer.get();
                if (d != null) {
                    d.dispose();
                }
                DebounceEmitter<T> de = new DebounceEmitter(t, idx, this);
                if (this.timer.compareAndSet(d, de)) {
                    de.setResource(this.worker.schedule(de, this.timeout, this.unit));
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                Disposable d = (Disposable) this.timer.get();
                if (d != DisposableHelper.DISPOSED) {
                    DebounceEmitter<T> de = (DebounceEmitter) d;
                    if (de != null) {
                        de.run();
                    }
                    this.actual.onComplete();
                    this.worker.dispose();
                }
            }
        }

        public void dispose() {
            this.f2681s.dispose();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }

        void emit(long idx, T t, DebounceEmitter<T> emitter) {
            if (idx == this.index) {
                this.actual.onNext(t);
                emitter.dispose();
            }
        }
    }

    public ObservableDebounceTimed(ObservableSource<T> source, long timeout, TimeUnit unit, Scheduler scheduler) {
        super(source);
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
    }

    public void subscribeActual(Observer<? super T> t) {
        this.source.subscribe(new DebounceTimedObserver(new SerializedObserver(t), this.timeout, this.unit, this.scheduler.createWorker()));
    }
}
