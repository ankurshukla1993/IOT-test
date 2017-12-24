package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableBufferTimed<T, U extends Collection<? super T>> extends AbstractFlowableWithUpstream<T, U> {
    final Callable<U> bufferSupplier;
    final int maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    static final class BufferExactBoundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Subscription, Runnable, Disposable {
        U buffer;
        final Callable<U> bufferSupplier;
        long consumerIndex;
        final int maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        Subscription f2517s;
        Disposable timer;
        final long timespan;
        final TimeUnit unit;
        final Worker f2518w;

        BufferExactBoundedSubscriber(Subscriber<? super U> actual, Callable<U> bufferSupplier, long timespan, TimeUnit unit, int maxSize, boolean restartOnMaxSize, Worker w) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier;
            this.timespan = timespan;
            this.unit = unit;
            this.maxSize = maxSize;
            this.restartTimerOnMaxSize = restartOnMaxSize;
            this.f2518w = w;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2517s, s)) {
                this.f2517s = s;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.actual.onSubscribe(this);
                    this.timer = this.f2518w.schedulePeriodically(this, this.timespan, this.timespan, this.unit);
                    s.request(Long.MAX_VALUE);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2518w.dispose();
                    s.cancel();
                    EmptySubscription.error(e, this.actual);
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
            r11.fastPathOrderedEmitMax(r9, r2, r11);
            r2 = r11.bufferSupplier;	 Catch:{ Throwable -> 0x005c }
            r2 = r2.call();	 Catch:{ Throwable -> 0x005c }
            r3 = "The supplied buffer is null";
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
            r2 = r11.f2518w;
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
            r11.cancel();
            r2 = r11.actual;
            r2.onError(r10);
            goto L_0x0008;
        L_0x0069:
            r2 = move-exception;
            monitor-exit(r11);	 Catch:{ all -> 0x0069 }
            throw r2;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferTimed.BufferExactBoundedSubscriber.onNext(java.lang.Object):void");
        }

        public void onError(Throwable t) {
            synchronized (this) {
                this.buffer = null;
            }
            this.actual.onError(t);
            this.f2518w.dispose();
        }

        public void onComplete() {
            U b;
            synchronized (this) {
                b = this.buffer;
                this.buffer = null;
            }
            this.queue.offer(b);
            this.done = true;
            if (enter()) {
                QueueDrainHelper.drainMaxLoop(this.queue, this.actual, false, this, this);
            }
            this.f2518w.dispose();
        }

        public boolean accept(Subscriber<? super U> a, U v) {
            a.onNext(v);
            return true;
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                dispose();
            }
        }

        public void dispose() {
            synchronized (this) {
                this.buffer = null;
            }
            this.f2517s.cancel();
            this.f2518w.dispose();
        }

        public boolean isDisposed() {
            return this.f2518w.isDisposed();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r8 = this;
            r3 = r8.bufferSupplier;	 Catch:{ Throwable -> 0x001d }
            r3 = r3.call();	 Catch:{ Throwable -> 0x001d }
            r4 = "The supplied buffer is null";
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
            r8.cancel();
            r3 = r8.actual;
            r3.onError(r1);
            goto L_0x001c;
        L_0x002a:
            r8.buffer = r2;	 Catch:{ all -> 0x0032 }
            monitor-exit(r8);	 Catch:{ all -> 0x0032 }
            r3 = 0;
            r8.fastPathOrderedEmitMax(r0, r3, r8);
            goto L_0x001c;
        L_0x0032:
            r3 = move-exception;
            monitor-exit(r8);	 Catch:{ all -> 0x0032 }
            throw r3;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferTimed.BufferExactBoundedSubscriber.run():void");
        }
    }

    static final class BufferExactUnboundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Subscription, Runnable, Disposable {
        U buffer;
        final Callable<U> bufferSupplier;
        Subscription f2519s;
        final Scheduler scheduler;
        final AtomicReference<Disposable> timer = new AtomicReference();
        final long timespan;
        final TimeUnit unit;

        BufferExactUnboundedSubscriber(Subscriber<? super U> actual, Callable<U> bufferSupplier, long timespan, TimeUnit unit, Scheduler scheduler) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier;
            this.timespan = timespan;
            this.unit = unit;
            this.scheduler = scheduler;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2519s, s)) {
                this.f2519s = s;
                try {
                    this.buffer = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.actual.onSubscribe(this);
                    if (!this.cancelled) {
                        s.request(Long.MAX_VALUE);
                        Disposable d = this.scheduler.schedulePeriodicallyDirect(this, this.timespan, this.timespan, this.unit);
                        if (!this.timer.compareAndSet(null, d)) {
                            d.dispose();
                        }
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    cancel();
                    EmptySubscription.error(e, this.actual);
                }
            }
        }

        public void onNext(T t) {
            synchronized (this) {
                U b = this.buffer;
                if (b != null) {
                    b.add(t);
                }
            }
        }

        public void onError(Throwable t) {
            DisposableHelper.dispose(this.timer);
            synchronized (this) {
                this.buffer = null;
            }
            this.actual.onError(t);
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void onComplete() {
            /*
            r4 = this;
            r1 = r4.timer;
            io.reactivex.internal.disposables.DisposableHelper.dispose(r1);
            monitor-enter(r4);
            r0 = r4.buffer;	 Catch:{ all -> 0x0027 }
            if (r0 != 0) goto L_0x000c;
        L_0x000a:
            monitor-exit(r4);	 Catch:{ all -> 0x0027 }
        L_0x000b:
            return;
        L_0x000c:
            r1 = 0;
            r4.buffer = r1;	 Catch:{ all -> 0x0027 }
            monitor-exit(r4);	 Catch:{ all -> 0x0027 }
            r1 = r4.queue;
            r1.offer(r0);
            r1 = 1;
            r4.done = r1;
            r1 = r4.enter();
            if (r1 == 0) goto L_0x000b;
        L_0x001e:
            r1 = r4.queue;
            r2 = r4.actual;
            r3 = 0;
            io.reactivex.internal.util.QueueDrainHelper.drainMaxLoop(r1, r2, r3, r4, r4);
            goto L_0x000b;
        L_0x0027:
            r1 = move-exception;
            monitor-exit(r4);	 Catch:{ all -> 0x0027 }
            throw r1;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableBufferTimed.BufferExactUnboundedSubscriber.onComplete():void");
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            this.f2519s.cancel();
            DisposableHelper.dispose(this.timer);
        }

        public void run() {
            try {
                U current;
                Collection next = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                synchronized (this) {
                    current = this.buffer;
                    if (current != null) {
                        this.buffer = next;
                    }
                }
                if (current == null) {
                    DisposableHelper.dispose(this.timer);
                } else {
                    fastPathEmitMax(current, false, this);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                cancel();
                this.actual.onError(e);
            }
        }

        public boolean accept(Subscriber<? super U> subscriber, U v) {
            this.actual.onNext(v);
            return true;
        }

        public void dispose() {
            cancel();
        }

        public boolean isDisposed() {
            return this.timer.get() == DisposableHelper.DISPOSED;
        }
    }

    static final class BufferSkipBoundedSubscriber<T, U extends Collection<? super T>> extends QueueDrainSubscriber<T, U, U> implements Subscription, Runnable {
        final Callable<U> bufferSupplier;
        final List<U> buffers = new LinkedList();
        Subscription f2520s;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        final Worker f2521w;

        final class RemoveFromBuffer implements Runnable {
            private final U buffer;

            RemoveFromBuffer(U buffer) {
                this.buffer = buffer;
            }

            public void run() {
                synchronized (BufferSkipBoundedSubscriber.this) {
                    BufferSkipBoundedSubscriber.this.buffers.remove(this.buffer);
                }
                BufferSkipBoundedSubscriber.this.fastPathOrderedEmitMax(this.buffer, false, BufferSkipBoundedSubscriber.this.f2521w);
            }
        }

        BufferSkipBoundedSubscriber(Subscriber<? super U> actual, Callable<U> bufferSupplier, long timespan, long timeskip, TimeUnit unit, Worker w) {
            super(actual, new MpscLinkedQueue());
            this.bufferSupplier = bufferSupplier;
            this.timespan = timespan;
            this.timeskip = timeskip;
            this.unit = unit;
            this.f2521w = w;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2520s, s)) {
                this.f2520s = s;
                try {
                    Collection b = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    this.buffers.add(b);
                    this.actual.onSubscribe(this);
                    s.request(Long.MAX_VALUE);
                    this.f2521w.schedulePeriodically(this, this.timeskip, this.timeskip, this.unit);
                    this.f2521w.schedule(new RemoveFromBuffer(b), this.timespan, this.unit);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2521w.dispose();
                    s.cancel();
                    EmptySubscription.error(e, this.actual);
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
            this.f2521w.dispose();
            clear();
            this.actual.onError(t);
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
                QueueDrainHelper.drainMaxLoop(this.queue, this.actual, false, this.f2521w, this);
            }
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            clear();
            this.f2520s.cancel();
            this.f2521w.dispose();
        }

        void clear() {
            synchronized (this) {
                this.buffers.clear();
            }
        }

        public void run() {
            if (!this.cancelled) {
                try {
                    Collection b = (Collection) ObjectHelper.requireNonNull(this.bufferSupplier.call(), "The supplied buffer is null");
                    synchronized (this) {
                        if (this.cancelled) {
                            return;
                        }
                        this.buffers.add(b);
                        this.f2521w.schedule(new RemoveFromBuffer(b), this.timespan, this.unit);
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    cancel();
                    this.actual.onError(e);
                }
            }
        }

        public boolean accept(Subscriber<? super U> a, U v) {
            a.onNext(v);
            return true;
        }
    }

    public FlowableBufferTimed(Flowable<T> source, long timespan, long timeskip, TimeUnit unit, Scheduler scheduler, Callable<U> bufferSupplier, int maxSize, boolean restartTimerOnMaxSize) {
        super(source);
        this.timespan = timespan;
        this.timeskip = timeskip;
        this.unit = unit;
        this.scheduler = scheduler;
        this.bufferSupplier = bufferSupplier;
        this.maxSize = maxSize;
        this.restartTimerOnMaxSize = restartTimerOnMaxSize;
    }

    protected void subscribeActual(Subscriber<? super U> s) {
        if (this.timespan == this.timeskip && this.maxSize == Integer.MAX_VALUE) {
            this.source.subscribe(new BufferExactUnboundedSubscriber(new SerializedSubscriber(s), this.bufferSupplier, this.timespan, this.unit, this.scheduler));
            return;
        }
        Worker w = this.scheduler.createWorker();
        if (this.timespan == this.timeskip) {
            this.source.subscribe(new BufferExactBoundedSubscriber(new SerializedSubscriber(s), this.bufferSupplier, this.timespan, this.unit, this.maxSize, this.restartTimerOnMaxSize, w));
        } else {
            this.source.subscribe(new BufferSkipBoundedSubscriber(new SerializedSubscriber(s), this.bufferSupplier, this.timespan, this.timeskip, this.unit, w));
        }
    }
}
