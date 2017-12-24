package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableObserveOn<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean delayError;
    final int prefetch;
    final Scheduler scheduler;

    static abstract class BaseObserveOnSubscriber<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T>, Runnable {
        private static final long serialVersionUID = -8241002408341274697L;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        boolean outputFused;
        final int prefetch;
        long produced;
        SimpleQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription f2563s;
        int sourceMode;
        final Worker worker;

        abstract void runAsync();

        abstract void runBackfused();

        abstract void runSync();

        BaseObserveOnSubscriber(Worker worker, boolean delayError, int prefetch) {
            this.worker = worker;
            this.delayError = delayError;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
        }

        public final void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode == 2) {
                    trySchedule();
                    return;
                }
                if (!this.queue.offer(t)) {
                    this.f2563s.cancel();
                    this.error = new MissingBackpressureException("Queue is full?!");
                    this.done = true;
                }
                trySchedule();
            }
        }

        public final void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.error = t;
            this.done = true;
            trySchedule();
        }

        public final void onComplete() {
            if (!this.done) {
                this.done = true;
                trySchedule();
            }
        }

        public final void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                trySchedule();
            }
        }

        public final void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2563s.cancel();
                this.worker.dispose();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        final void trySchedule() {
            if (getAndIncrement() == 0) {
                this.worker.schedule(this);
            }
        }

        public final void run() {
            if (this.outputFused) {
                runBackfused();
            } else if (this.sourceMode == 1) {
                runSync();
            } else {
                runAsync();
            }
        }

        final boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a) {
            if (this.cancelled) {
                clear();
                return true;
            }
            if (d) {
                Throwable e;
                if (!this.delayError) {
                    e = this.error;
                    if (e != null) {
                        clear();
                        a.onError(e);
                        this.worker.dispose();
                        return true;
                    } else if (empty) {
                        a.onComplete();
                        this.worker.dispose();
                        return true;
                    }
                } else if (empty) {
                    e = this.error;
                    if (e != null) {
                        a.onError(e);
                    } else {
                        a.onComplete();
                    }
                    this.worker.dispose();
                    return true;
                }
            }
            return false;
        }

        public final int requestFusion(int requestedMode) {
            if ((requestedMode & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        public final void clear() {
            this.queue.clear();
        }

        public final boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    static final class ObserveOnConditionalSubscriber<T> extends BaseObserveOnSubscriber<T> {
        private static final long serialVersionUID = 644624475404284533L;
        final ConditionalSubscriber<? super T> actual;
        long consumed;

        ObserveOnConditionalSubscriber(ConditionalSubscriber<? super T> actual, Worker worker, boolean delayError, int prefetch) {
            super(worker, delayError, prefetch);
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.s, s)) {
                this.s = s;
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> f = (QueueSubscription) s;
                    int m = f.requestFusion(7);
                    if (m == 1) {
                        this.sourceMode = 1;
                        this.queue = f;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        return;
                    } else if (m == 2) {
                        this.sourceMode = 2;
                        this.queue = f;
                        this.actual.onSubscribe(this);
                        s.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.actual.onSubscribe(this);
                s.request((long) this.prefetch);
            }
        }

        void runSync() {
            int missed = 1;
            ConditionalSubscriber<? super T> a = this.actual;
            SimpleQueue<T> q = this.queue;
            long e = this.produced;
            while (true) {
                long r = this.requested.get();
                while (e != r) {
                    try {
                        T v = q.poll();
                        if (!this.cancelled) {
                            if (v == null) {
                                a.onComplete();
                                this.worker.dispose();
                                return;
                            } else if (a.tryOnNext(v)) {
                                e++;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.s.cancel();
                        a.onError(ex);
                        this.worker.dispose();
                        return;
                    }
                }
                if (!this.cancelled) {
                    if (q.isEmpty()) {
                        a.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    int w = get();
                    if (missed == w) {
                        this.produced = e;
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else {
                        missed = w;
                    }
                } else {
                    return;
                }
            }
        }

        void runAsync() {
            int missed = 1;
            ConditionalSubscriber<? super T> a = this.actual;
            SimpleQueue<T> q = this.queue;
            long emitted = this.produced;
            long polled = this.consumed;
            while (true) {
                long r = this.requested.get();
                while (emitted != r) {
                    boolean d = this.done;
                    try {
                        T v = q.poll();
                        boolean empty = v == null;
                        if (!checkTerminated(d, empty, a)) {
                            if (empty) {
                                break;
                            }
                            if (a.tryOnNext(v)) {
                                emitted++;
                            }
                            polled++;
                            if (polled == ((long) this.limit)) {
                                this.s.request(polled);
                                polled = 0;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.s.cancel();
                        q.clear();
                        a.onError(ex);
                        this.worker.dispose();
                        return;
                    }
                }
                if (emitted == r) {
                    if (checkTerminated(this.done, q.isEmpty(), a)) {
                        return;
                    }
                }
                int w = get();
                if (missed == w) {
                    this.produced = emitted;
                    this.consumed = polled;
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else {
                    missed = w;
                }
            }
        }

        void runBackfused() {
            int missed = 1;
            while (!this.cancelled) {
                boolean d = this.done;
                this.actual.onNext(null);
                if (d) {
                    Throwable e = this.error;
                    if (e != null) {
                        this.actual.onError(e);
                    } else {
                        this.actual.onComplete();
                    }
                    this.worker.dispose();
                    return;
                }
                missed = addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
            }
        }

        @Nullable
        public T poll() throws Exception {
            T v = this.queue.poll();
            if (!(v == null || this.sourceMode == 1)) {
                long p = this.consumed + 1;
                if (p == ((long) this.limit)) {
                    this.consumed = 0;
                    this.s.request(p);
                } else {
                    this.consumed = p;
                }
            }
            return v;
        }
    }

    static final class ObserveOnSubscriber<T> extends BaseObserveOnSubscriber<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -4547113800637756442L;
        final Subscriber<? super T> actual;

        ObserveOnSubscriber(Subscriber<? super T> actual, Worker worker, boolean delayError, int prefetch) {
            super(worker, delayError, prefetch);
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.s, s)) {
                this.s = s;
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> f = (QueueSubscription) s;
                    int m = f.requestFusion(7);
                    if (m == 1) {
                        this.sourceMode = 1;
                        this.queue = f;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        return;
                    } else if (m == 2) {
                        this.sourceMode = 2;
                        this.queue = f;
                        this.actual.onSubscribe(this);
                        s.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.actual.onSubscribe(this);
                s.request((long) this.prefetch);
            }
        }

        void runSync() {
            int missed = 1;
            Subscriber<? super T> a = this.actual;
            SimpleQueue<T> q = this.queue;
            long e = this.produced;
            while (true) {
                long r = this.requested.get();
                while (e != r) {
                    try {
                        T v = q.poll();
                        if (!this.cancelled) {
                            if (v == null) {
                                a.onComplete();
                                this.worker.dispose();
                                return;
                            }
                            a.onNext(v);
                            e++;
                        } else {
                            return;
                        }
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.s.cancel();
                        a.onError(ex);
                        this.worker.dispose();
                        return;
                    }
                }
                if (!this.cancelled) {
                    if (q.isEmpty()) {
                        a.onComplete();
                        this.worker.dispose();
                        return;
                    }
                    int w = get();
                    if (missed == w) {
                        this.produced = e;
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else {
                        missed = w;
                    }
                } else {
                    return;
                }
            }
        }

        void runAsync() {
            int missed = 1;
            Subscriber<? super T> a = this.actual;
            SimpleQueue<T> q = this.queue;
            long e = this.produced;
            while (true) {
                long r = this.requested.get();
                while (e != r) {
                    boolean d = this.done;
                    try {
                        T v = q.poll();
                        boolean empty = v == null;
                        if (!checkTerminated(d, empty, a)) {
                            if (empty) {
                                break;
                            }
                            a.onNext(v);
                            e++;
                            if (e == ((long) this.limit)) {
                                if (r != Long.MAX_VALUE) {
                                    r = this.requested.addAndGet(-e);
                                }
                                this.s.request(e);
                                e = 0;
                            }
                        } else {
                            return;
                        }
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.s.cancel();
                        q.clear();
                        a.onError(ex);
                        this.worker.dispose();
                        return;
                    }
                }
                if (e != r || !checkTerminated(this.done, q.isEmpty(), a)) {
                    int w = get();
                    if (missed == w) {
                        this.produced = e;
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else {
                        missed = w;
                    }
                } else {
                    return;
                }
            }
        }

        void runBackfused() {
            int missed = 1;
            while (!this.cancelled) {
                boolean d = this.done;
                this.actual.onNext(null);
                if (d) {
                    Throwable e = this.error;
                    if (e != null) {
                        this.actual.onError(e);
                    } else {
                        this.actual.onComplete();
                    }
                    this.worker.dispose();
                    return;
                }
                missed = addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
            }
        }

        @Nullable
        public T poll() throws Exception {
            T v = this.queue.poll();
            if (!(v == null || this.sourceMode == 1)) {
                long p = this.produced + 1;
                if (p == ((long) this.limit)) {
                    this.produced = 0;
                    this.s.request(p);
                } else {
                    this.produced = p;
                }
            }
            return v;
        }
    }

    public FlowableObserveOn(Flowable<T> source, Scheduler scheduler, boolean delayError, int prefetch) {
        super(source);
        this.scheduler = scheduler;
        this.delayError = delayError;
        this.prefetch = prefetch;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        Worker worker = this.scheduler.createWorker();
        if (s instanceof ConditionalSubscriber) {
            this.source.subscribe(new ObserveOnConditionalSubscriber((ConditionalSubscriber) s, worker, this.delayError, this.prefetch));
        } else {
            this.source.subscribe(new ObserveOnSubscriber(s, worker, this.delayError, this.prefetch));
        }
    }
}
