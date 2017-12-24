package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.EmptyDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.observers.SerializedObserver;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableBufferTimed<T, U extends Collection<? super T>> extends AbstractObservableWithUpstream<T, U> {
    final Callable<U> bufferSupplier;
    final int maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    static final class BufferExactBoundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        U buffer;
        final Callable<U> bufferSupplier;
        long consumerIndex;
        final int maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        Disposable f2664s;
        Disposable timer;
        final long timespan;
        final TimeUnit unit;
        final Worker f2665w;

        BufferExactBoundedObserver(Observer<? super U> actual, Callable<U> bufferSupplier, long timespan, TimeUnit unit, int maxSize, boolean restartOnMaxSize, Worker w) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier;
            this.timespan = timespan;
            this.unit = unit;
            this.maxSize = maxSize;
            this.restartTimerOnMaxSize = restartOnMaxSize;
            this.f2665w = w;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2664s, s)) {
                this.f2664s = s;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    this.actual.onSubscribe(this);
                    this.timer = this.f2665w.schedulePeriodically(this, this.timespan, this.timespan, this.unit);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    s.dispose();
                    EmptyDisposable.error(e, this.actual);
                    this.f2665w.dispose();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onNext(T r12) {
            /*
            r11 = this;
            r4 = 1;
            monitor-enter(r11);
            r9 = r11.buffer;	 Catch:{ all -> 0x0016 }
            if (r9 != 0) goto L_0x0009;
        L_0x0007:
            monitor-exit(r11);	 Catch:{ all -> 0x0016 }
        L_0x0008:
            return;
        L_0x0009:
            r9.add(r12);	 Catch:{ all -> 0x0016 }
            r2 = r9.size();	 Catch:{ all -> 0x0016 }
            r3 = r11.maxSize;	 Catch:{ all -> 0x0016 }
            if (r2 >= r3) goto L_0x0019;
        L_0x0014:
            monitor-exit(r11);	 Catch:{ all -> 0x0016 }
            goto L_0x0008;
        L_0x0016:
            r2 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x0016 }
            throw r2;
        L_0x0019:
            r2 = 0;
            r11.buffer = r2;	 Catch:{ all -> 0x0016 }
            r2 = r11.producerIndex;	 Catch:{ all -> 0x0016 }
            r2 = r2 + r4;
            r11.producerIndex = r2;	 Catch:{ all -> 0x0016 }
            monitor-exit(r11);	 Catch:{ all -> 0x0016 }
            r2 = r11.restartTimerOnMaxSize;
            if (r2 == 0) goto L_0x002b;
        L_0x0026:
            r2 = r11.timer;
            r2.dispose();
        L_0x002b:
            r2 = 0;
            r11.fastPathOrderedEmit(r9, r2, r11);
            r2 = r11.bufferSupplier;	 Catch:{ Throwable -> 0x005c }
            r2 = r2.call();	 Catch:{ Throwable -> 0x005c }
            r3 = "The buffer supplied is null";
            r2 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r2, r3);	 Catch:{ Throwable -> 0x005c }
            r0 = r2;
            r0 = (java.util.Collection) r0;	 Catch:{ Throwable -> 0x005c }
            r9 = r0;
            monitor-enter(r11);
            r11.buffer = r9;	 Catch:{ all -> 0x0069 }
            r2 = r11.consumerIndex;	 Catch:{ all -> 0x0069 }
            r2 = r2 + r4;
            r11.consumerIndex = r2;	 Catch:{ all -> 0x0069 }
            monitor-exit(r11);	 Catch:{ all -> 0x0069 }
            r2 = r11.restartTimerOnMaxSize;
            if (r2 == 0) goto L_0x0008;
        L_0x004c:
            r2 = r11.f2665w;
            r4 = r11.timespan;
            r6 = r11.timespan;
            r8 = r11.unit;
            r3 = r11;
            r2 = r2.schedulePeriodically(r3, r4, r6, r8);
            r11.timer = r2;
            goto L_0x0008;
        L_0x005c:
            r10 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r10);
            r2 = r11.actual;
            r2.onError(r10);
            r11.dispose();
            goto L_0x0008;
        L_0x0069:
            r2 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x0069 }
            throw r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableBufferTimed.BufferExactBoundedObserver.onNext(java.lang.Object):void");
        }

        public void onError(Throwable t) {
            synchronized (this) {
                this.buffer = null;
            }
            this.actual.onError(t);
            this.f2665w.dispose();
        }

        public void onComplete() {
            U b;
            this.f2665w.dispose();
            synchronized (this) {
                b = this.buffer;
                this.buffer = null;
            }
            this.queue.offer(b);
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainLoop(this.queue, this.actual, false, this, this);
            }
        }

        public void accept(Observer<? super U> a, U v) {
            a.onNext(v);
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2664s.dispose();
                this.f2665w.dispose();
                synchronized (this) {
                    this.buffer = null;
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r8 = this;
            r3 = r8.bufferSupplier;	 Catch:{ Throwable -> 0x001d }
            r3 = r3.call();	 Catch:{ Throwable -> 0x001d }
            r4 = "The bufferSupplier returned a null buffer";
            r2 = io.reactivex.internal.functions.ObjectHelper.requireNonNull(r3, r4);	 Catch:{ Throwable -> 0x001d }
            r2 = (java.util.Collection) r2;	 Catch:{ Throwable -> 0x001d }
            monitor-enter(r8);
            r0 = r8.buffer;	 Catch:{ all -> 0x0032 }
            if (r0 == 0) goto L_0x001b;
        L_0x0013:
            r4 = r8.producerIndex;	 Catch:{ all -> 0x0032 }
            r6 = r8.consumerIndex;	 Catch:{ all -> 0x0032 }
            r3 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1));
            if (r3 == 0) goto L_0x002a;
        L_0x001b:
            monitor-exit(r8);	 Catch:{ all -> 0x0032 }
        L_0x001c:
            return;
        L_0x001d:
            r1 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r1);
            r8.dispose();
            r3 = r8.actual;
            r3.onError(r1);
            goto L_0x001c;
        L_0x002a:
            r8.buffer = r2;	 Catch:{ all -> 0x0032 }
            monitor-exit(r8);	 Catch:{ all -> 0x0032 }
            r3 = 0;
            r8.fastPathOrderedEmit(r0, r3, r8);
            goto L_0x001c;
        L_0x0032:
            r3 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0032 }
            throw r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.observable.ObservableBufferTimed.BufferExactBoundedObserver.run():void");
        }
    }

    static final class BufferExactUnboundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        U buffer;
        final Callable<U> bufferSupplier;
        Disposable f2666s;
        final Scheduler scheduler;
        final AtomicReference<Disposable> timer = new AtomicReference();
        final long timespan;
        final TimeUnit unit;

        BufferExactUnboundedObserver(Observer<? super U> actual, Callable<U> bufferSupplier, long timespan, TimeUnit unit, Scheduler scheduler) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier;
            this.timespan = timespan;
            this.unit = unit;
            this.scheduler = scheduler;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2666s, s)) {
                this.f2666s = s;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    this.actual.onSubscribe(this);
                    if (!this.cancelled) {
                        Disposable d = this.scheduler.schedulePeriodicallyDirect(this, this.timespan, this.timespan, this.unit);
                        if (!this.timer.compareAndSet(null, d)) {
                            d.dispose();
                        }
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    dispose();
                    EmptyDisposable.error(e, this.actual);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                U b = this.buffer;
                if (b == null) {
                    return;
                }
                b.add(t);
            }
        }

        public void onError(Throwable t) {
            synchronized (this) {
                this.buffer = null;
            }
            this.actual.onError(t);
            DisposableHelper.dispose(this.timer);
        }

        public void onComplete() {
            synchronized (this) {
                U b = this.buffer;
                this.buffer = null;
            }
            if (b != null) {
                this.queue.offer(b);
                this.done = true;
                if (enter()) {
                    QueueDrainHelper.drainLoop(this.queue, this.actual, false, this, this);
                }
            }
            DisposableHelper.dispose(this.timer);
        }

        public void dispose() {
            DisposableHelper.dispose(this.timer);
            this.f2666s.dispose();
        }

        public boolean isDisposed() {
            return this.timer.get() == DisposableHelper.DISPOSED;
        }

        public void run() {
            try {
                U current;
                Collection next = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                synchronized (this) {
                    current = this.buffer;
                    if (current != null) {
                        this.buffer = next;
                    }
                }
                if (current == null) {
                    DisposableHelper.dispose(this.timer);
                } else {
                    fastPathEmit(current, false, this);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.actual.onError(e);
                dispose();
            }
        }

        public void accept(Observer<? super U> observer, U v) {
            this.actual.onNext(v);
        }
    }

    static final class BufferSkipBoundedObserver<T, U extends Collection<? super T>> extends QueueDrainObserver<T, U, U> implements Runnable, Disposable {
        final Callable<U> bufferSupplier;
        final List<U> buffers = new LinkedList();
        Disposable f2668s;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        final Worker f2669w;

        final class RemoveFromBuffer implements Runnable {
            private final U f2667b;

            RemoveFromBuffer(U b) {
                this.f2667b = b;
            }

            public void run() {
                synchronized (BufferSkipBoundedObserver.this) {
                    BufferSkipBoundedObserver.this.buffers.remove(this.f2667b);
                }
                BufferSkipBoundedObserver.this.fastPathOrderedEmit(this.f2667b, false, BufferSkipBoundedObserver.this.f2669w);
            }
        }

        final class RemoveFromBufferEmit implements Runnable {
            private final U buffer;

            RemoveFromBufferEmit(U buffer) {
                this.buffer = buffer;
            }

            public void run() {
                synchronized (BufferSkipBoundedObserver.this) {
                    BufferSkipBoundedObserver.this.buffers.remove(this.buffer);
                }
                BufferSkipBoundedObserver.this.fastPathOrderedEmit(this.buffer, false, BufferSkipBoundedObserver.this.f2669w);
            }
        }

        BufferSkipBoundedObserver(Observer<? super U> actual, Callable<U> bufferSupplier, long timespan, long timeskip, TimeUnit unit, Worker w) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier;
            this.timespan = timespan;
            this.timeskip = timeskip;
            this.unit = unit;
            this.f2669w = w;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2668s, s)) {
                this.f2668s = s;
                try {
                    Collection b = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The buffer supplied is null");
                    this.buffers.add(b);
                    this.actual.onSubscribe(this);
                    this.f2669w.schedulePeriodically(this, this.timeskip, this.timeskip, this.unit);
                    this.f2669w.schedule(new RemoveFromBufferEmit(b), this.timespan, this.unit);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    s.dispose();
                    EmptyDisposable.error(e, this.actual);
                    this.f2669w.dispose();
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                for (Collection b : this.buffers) {
                    b.add(t);
                }
            }
        }

        public void onError(Throwable t) {
            this.done = true;
            clear();
            this.actual.onError(t);
            this.f2669w.dispose();
        }

        public void onComplete() {
            synchronized (this) {
                List<U> bs = new ArrayList(this.buffers);
                this.buffers.clear();
            }
            for (U b : bs) {
                this.queue.offer(b);
            }
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainLoop(this.queue, this.actual, false, this.f2669w, this);
            }
        }

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                clear();
                this.f2668s.dispose();
                this.f2669w.dispose();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void clear() {
            synchronized (this) {
                this.buffers.clear();
            }
        }

        public void run() {
            if (!this.cancelled) {
                try {
                    Collection b = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The bufferSupplier returned a null buffer");
                    synchronized (this) {
                        if (this.cancelled) {
                            return;
                        }
                        this.buffers.add(b);
                        this.f2669w.schedule(new RemoveFromBuffer(b), this.timespan, this.unit);
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.actual.onError(e);
                    dispose();
                }
            }
        }

        public void accept(Observer<? super U> a, U v) {
            a.onNext(v);
        }
    }

    public ObservableBufferTimed(ObservableSource<T> source, long timespan, long timeskip, TimeUnit unit, Scheduler scheduler, Callable<U> bufferSupplier, int maxSize, boolean restartTimerOnMaxSize) {
        super(source);
        this.timespan = timespan;
        this.timeskip = timeskip;
        this.unit = unit;
        this.scheduler = scheduler;
        this.bufferSupplier = bufferSupplier;
        this.maxSize = maxSize;
        this.restartTimerOnMaxSize = restartTimerOnMaxSize;
    }

    protected void subscribeActual(Observer<? super U> t) {
        if (this.timespan == this.timeskip && this.maxSize == Integer.MAX_VALUE) {
            this.source.subscribe(new BufferExactUnboundedObserver(new SerializedObserver(t), this.bufferSupplier, this.timespan, this.unit, this.scheduler));
            return;
        }
        Worker w = this.scheduler.createWorker();
        if (this.timespan == this.timeskip) {
            this.source.subscribe(new BufferExactBoundedObserver(new SerializedObserver(t), this.bufferSupplier, this.timespan, this.unit, this.maxSize, this.restartTimerOnMaxSize, w));
        } else {
            this.source.subscribe(new BufferSkipBoundedObserver(new SerializedObserver(t), this.bufferSupplier, this.timespan, this.timeskip, this.unit, w));
        }
    }
}
