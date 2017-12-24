package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableFlatMap<T, U> extends AbstractFlowableWithUpstream<T, U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends Publisher<? extends U>> mapper;
    final int maxConcurrency;

    static final class InnerSubscriber<T, U> extends AtomicReference<Subscription> implements FlowableSubscriber<U>, Disposable {
        private static final long serialVersionUID = -4606175640614850599L;
        final int bufferSize;
        volatile boolean done;
        int fusionMode;
        final long id;
        final int limit = (this.bufferSize >> 2);
        final MergeSubscriber<T, U> parent;
        long produced;
        volatile SimpleQueue<U> queue;

        InnerSubscriber(MergeSubscriber<T, U> parent, long id) {
            this.id = id;
            this.parent = parent;
            this.bufferSize = parent.bufferSize;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<U> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(7);
                    if (m == 1) {
                        this.fusionMode = m;
                        this.queue = qs;
                        this.done = true;
                        this.parent.drain();
                        return;
                    } else if (m == 2) {
                        this.fusionMode = m;
                        this.queue = qs;
                    }
                }
                s.request((long) this.bufferSize);
            }
        }

        public void onNext(U t) {
            if (this.fusionMode != 2) {
                this.parent.tryEmit(t, this);
            } else {
                this.parent.drain();
            }
        }

        public void onError(Throwable t) {
            lazySet(SubscriptionHelper.CANCELLED);
            this.parent.innerError(this, t);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        void requestMore(long n) {
            if (this.fusionMode != 1) {
                long p = this.produced + n;
                if (p >= ((long) this.limit)) {
                    this.produced = 0;
                    ((Subscription) get()).request(p);
                    return;
                }
                this.produced = p;
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this);
        }

        public boolean isDisposed() {
            return get() == SubscriptionHelper.CANCELLED;
        }
    }

    static final class MergeSubscriber<T, U> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        static final InnerSubscriber<?, ?>[] CANCELLED = new InnerSubscriber[0];
        static final InnerSubscriber<?, ?>[] EMPTY = new InnerSubscriber[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final Subscriber<? super U> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicThrowable errs = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends Publisher<? extends U>> mapper;
        final int maxConcurrency;
        volatile SimplePlainQueue<U> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription f2547s;
        int scalarEmitted;
        final int scalarLimit;
        final AtomicReference<InnerSubscriber<?, ?>[]> subscribers = new AtomicReference();
        long uniqueId;

        MergeSubscriber(Subscriber<? super U> actual, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
            this.bufferSize = bufferSize;
            this.scalarLimit = Math.max(1, maxConcurrency >> 1);
            this.subscribers.lazySet(EMPTY);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2547s, s)) {
                this.f2547s = s;
                this.actual.onSubscribe(this);
                if (!this.cancelled) {
                    if (this.maxConcurrency == Integer.MAX_VALUE) {
                        s.request(Long.MAX_VALUE);
                    } else {
                        s.request((long) this.maxConcurrency);
                    }
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    Publisher<? extends U> p = (Publisher) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
                    if (p instanceof Callable) {
                        try {
                            U u = ((Callable) p).call();
                            if (u != null) {
                                tryEmitScalar(u);
                                return;
                            } else if (this.maxConcurrency != Integer.MAX_VALUE && !this.cancelled) {
                                int i = this.scalarEmitted + 1;
                                this.scalarEmitted = i;
                                if (i == this.scalarLimit) {
                                    this.scalarEmitted = 0;
                                    this.f2547s.request((long) this.scalarLimit);
                                    return;
                                }
                                return;
                            } else {
                                return;
                            }
                        } catch (Throwable ex) {
                            Exceptions.throwIfFatal(ex);
                            this.errs.addThrowable(ex);
                            drain();
                            return;
                        }
                    }
                    long j = this.uniqueId;
                    this.uniqueId = 1 + j;
                    InnerSubscriber<T, U> inner = new InnerSubscriber(this, j);
                    if (addInner(inner)) {
                        p.subscribe(inner);
                    }
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2547s.cancel();
                    onError(e);
                }
            }
        }

        boolean addInner(InnerSubscriber<T, U> inner) {
            InnerSubscriber[] a;
            InnerSubscriber<?, ?>[] b;
            do {
                a = (InnerSubscriber[]) this.subscribers.get();
                if (a == CANCELLED) {
                    inner.dispose();
                    return false;
                }
                int n = a.length;
                b = new InnerSubscriber[(n + 1)];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = inner;
            } while (!this.subscribers.compareAndSet(a, b));
            return true;
        }

        void removeInner(InnerSubscriber<T, U> inner) {
            InnerSubscriber[] a;
            InnerSubscriber<?, ?>[] b;
            do {
                a = (InnerSubscriber[]) this.subscribers.get();
                if (a != CANCELLED && a != EMPTY) {
                    int n = a.length;
                    int j = -1;
                    for (int i = 0; i < n; i++) {
                        if (a[i] == inner) {
                            j = i;
                            break;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (n == 1) {
                        b = EMPTY;
                    } else {
                        b = new InnerSubscriber[(n - 1)];
                        System.arraycopy(a, 0, b, 0, j);
                        System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(a, b));
        }

        SimpleQueue<U> getMainQueue() {
            SimplePlainQueue<U> q = this.queue;
            if (q == null) {
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    q = new SpscLinkedArrayQueue(this.bufferSize);
                } else {
                    q = new SpscArrayQueue(this.maxConcurrency);
                }
                this.queue = q;
            }
            return q;
        }

        void tryEmitScalar(U value) {
            if (get() == 0 && compareAndSet(0, 1)) {
                long r = this.requested.get();
                SimpleQueue<U> q = this.queue;
                if (r == 0 || !(q == null || q.isEmpty())) {
                    if (q == null) {
                        q = getMainQueue();
                    }
                    if (!q.offer(value)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    }
                }
                this.actual.onNext(value);
                if (r != Long.MAX_VALUE) {
                    this.requested.decrementAndGet();
                }
                if (!(this.maxConcurrency == Integer.MAX_VALUE || this.cancelled)) {
                    int i = this.scalarEmitted + 1;
                    this.scalarEmitted = i;
                    if (i == this.scalarLimit) {
                        this.scalarEmitted = 0;
                        this.f2547s.request((long) this.scalarLimit);
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!getMainQueue().offer(value)) {
                onError(new IllegalStateException("Scalar queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SimpleQueue<U> getInnerQueue(InnerSubscriber<T, U> inner) {
            SimpleQueue<U> q = inner.queue;
            if (q != null) {
                return q;
            }
            q = new SpscArrayQueue(this.bufferSize);
            inner.queue = q;
            return q;
        }

        void tryEmit(U value, InnerSubscriber<T, U> inner) {
            SimpleQueue<U> q;
            if (get() == 0 && compareAndSet(0, 1)) {
                long r = this.requested.get();
                q = inner.queue;
                if (r == 0 || !(q == null || q.isEmpty())) {
                    if (q == null) {
                        q = getInnerQueue(inner);
                    }
                    if (!q.offer(value)) {
                        onError(new MissingBackpressureException("Inner queue full?!"));
                        return;
                    }
                }
                this.actual.onNext(value);
                if (r != Long.MAX_VALUE) {
                    this.requested.decrementAndGet();
                }
                inner.requestMore(1);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            q = inner.queue;
            if (q == null) {
                q = new SpscArrayQueue(this.bufferSize);
                inner.queue = q;
            }
            if (!q.offer(value)) {
                onError(new MissingBackpressureException("Inner queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
            } else if (this.errs.addThrowable(t)) {
                this.done = true;
                drain();
            } else {
                RxJavaPlugins.onError(t);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2547s.cancel();
                disposeAll();
                if (getAndIncrement() == 0) {
                    SimpleQueue<U> q = this.queue;
                    if (q != null) {
                        q.clear();
                    }
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            Subscriber<? super U> child = this.actual;
            int missed = 1;
            while (!checkTerminate()) {
                U u;
                SimplePlainQueue<U> svq = this.queue;
                long r = this.requested.get();
                boolean unbounded = r == Long.MAX_VALUE;
                long replenishMain = 0;
                if (svq != null) {
                    do {
                        long scalarEmission = 0;
                        u = null;
                        while (r != 0) {
                            u = svq.poll();
                            if (!checkTerminate()) {
                                if (u == null) {
                                    break;
                                }
                                child.onNext(u);
                                replenishMain++;
                                scalarEmission++;
                                r--;
                            } else {
                                return;
                            }
                        }
                        if (scalarEmission != 0) {
                            if (unbounded) {
                                r = Long.MAX_VALUE;
                            } else {
                                r = this.requested.addAndGet(-scalarEmission);
                            }
                        }
                        if (r == 0) {
                            break;
                        }
                    } while (u != null);
                }
                boolean d = this.done;
                svq = this.queue;
                InnerSubscriber[] inner = (InnerSubscriber[]) this.subscribers.get();
                int n = inner.length;
                Throwable ex;
                if (d && ((svq == null || svq.isEmpty()) && n == 0)) {
                    ex = this.errs.terminate();
                    if (ex == ExceptionHelper.TERMINATED) {
                        return;
                    }
                    if (ex == null) {
                        child.onComplete();
                        return;
                    } else {
                        child.onError(ex);
                        return;
                    }
                }
                boolean innerCompleted = false;
                if (n != 0) {
                    int j;
                    int i;
                    long startId = this.lastId;
                    int index = this.lastIndex;
                    if (n <= index || inner[index].id != startId) {
                        if (n <= index) {
                            index = 0;
                        }
                        j = index;
                        for (i = 0; i < n && inner[j].id != startId; i++) {
                            j++;
                            if (j == n) {
                                j = 0;
                            }
                        }
                        index = j;
                        this.lastIndex = j;
                        this.lastId = inner[j].id;
                    }
                    j = index;
                    i = 0;
                    while (i < n) {
                        if (!checkTerminate()) {
                            InnerSubscriber<T, U> is = inner[j];
                            u = null;
                            while (!checkTerminate()) {
                                SimpleQueue<U> q = is.queue;
                                if (q != null) {
                                    long produced = 0;
                                    while (r != 0) {
                                        try {
                                            u = q.poll();
                                            if (u == null) {
                                                break;
                                            }
                                            child.onNext(u);
                                            if (!checkTerminate()) {
                                                r--;
                                                produced++;
                                            } else {
                                                return;
                                            }
                                        } catch (Throwable ex2) {
                                            Exceptions.throwIfFatal(ex2);
                                            is.dispose();
                                            this.errs.addThrowable(ex2);
                                            if (!checkTerminate()) {
                                                removeInner(is);
                                                innerCompleted = true;
                                                i++;
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                    if (produced != 0) {
                                        if (unbounded) {
                                            r = Long.MAX_VALUE;
                                        } else {
                                            r = this.requested.addAndGet(-produced);
                                        }
                                        is.requestMore(produced);
                                    }
                                    if (r != 0) {
                                        if (u == null) {
                                        }
                                    }
                                }
                                boolean innerDone = is.done;
                                SimpleQueue<U> innerQueue = is.queue;
                                if (innerDone && (innerQueue == null || innerQueue.isEmpty())) {
                                    removeInner(is);
                                    if (!checkTerminate()) {
                                        replenishMain++;
                                        innerCompleted = true;
                                    } else {
                                        return;
                                    }
                                }
                                if (r == 0) {
                                    break;
                                }
                                j++;
                                if (j == n) {
                                    j = 0;
                                }
                                i++;
                            }
                            return;
                        }
                        return;
                    }
                    this.lastIndex = j;
                    this.lastId = inner[j].id;
                }
                if (!(replenishMain == 0 || this.cancelled)) {
                    this.f2547s.request(replenishMain);
                }
                if (!innerCompleted) {
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                clearScalarQueue();
                return true;
            } else if (this.delayErrors || this.errs.get() == null) {
                return false;
            } else {
                clearScalarQueue();
                Throwable ex = this.errs.terminate();
                if (ex == ExceptionHelper.TERMINATED) {
                    return true;
                }
                this.actual.onError(ex);
                return true;
            }
        }

        void clearScalarQueue() {
            SimpleQueue<U> q = this.queue;
            if (q != null) {
                q.clear();
            }
        }

        void disposeAll() {
            if (((InnerSubscriber[]) this.subscribers.get()) != CANCELLED) {
                InnerSubscriber[] a = (InnerSubscriber[]) this.subscribers.getAndSet(CANCELLED);
                if (a != CANCELLED) {
                    for (InnerSubscriber<?, ?> inner : a) {
                        inner.dispose();
                    }
                    Throwable ex = this.errs.terminate();
                    if (ex != null && ex != ExceptionHelper.TERMINATED) {
                        RxJavaPlugins.onError(ex);
                    }
                }
            }
        }

        void innerError(InnerSubscriber<T, U> inner, Throwable t) {
            if (this.errs.addThrowable(t)) {
                inner.done = true;
                if (!this.delayErrors) {
                    this.f2547s.cancel();
                    for (InnerSubscriber<?, ?> a : (InnerSubscriber[]) this.subscribers.getAndSet(CANCELLED)) {
                        a.dispose();
                    }
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }
    }

    public FlowableFlatMap(Flowable<T> source, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayErrors;
        this.maxConcurrency = maxConcurrency;
        this.bufferSize = bufferSize;
    }

    protected void subscribeActual(Subscriber<? super U> s) {
        if (!FlowableScalarXMap.tryScalarXMapSubscribe(this.source, s, this.mapper)) {
            this.source.subscribe(subscribe(s, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
        }
    }

    public static <T, U> FlowableSubscriber<T> subscribe(Subscriber<? super U> s, Function<? super T, ? extends Publisher<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        return new MergeSubscriber(s, mapper, delayErrors, maxConcurrency, bufferSize);
    }
}
