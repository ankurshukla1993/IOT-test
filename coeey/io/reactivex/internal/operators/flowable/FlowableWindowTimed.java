package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.subscribers.QueueDrainSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableWindowTimed<T> extends AbstractFlowableWithUpstream<T, Flowable<T>> {
    final int bufferSize;
    final long maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    static final class WindowExactBoundedSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription {
        final int bufferSize;
        long count;
        final long maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        Subscription f2616s;
        final Scheduler scheduler;
        volatile boolean terminated;
        final SequentialDisposable timer = new SequentialDisposable();
        final long timespan;
        final TimeUnit unit;
        UnicastProcessor<T> window;
        final Worker worker;

        static final class ConsumerIndexHolder implements Runnable {
            final long index;
            final WindowExactBoundedSubscriber<?> parent;

            ConsumerIndexHolder(long index, WindowExactBoundedSubscriber<?> parent) {
                this.index = index;
                this.parent = parent;
            }

            public void run() {
                WindowExactBoundedSubscriber<?> p = this.parent;
                if (p.cancelled) {
                    p.terminated = true;
                    p.dispose();
                } else {
                    p.queue.offer(this);
                }
                if (p.enter()) {
                    p.drainLoop();
                }
            }
        }

        WindowExactBoundedSubscriber(Subscriber<? super Flowable<T>> actual, long timespan, TimeUnit unit, Scheduler scheduler, int bufferSize, long maxSize, boolean restartTimerOnMaxSize) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan;
            this.unit = unit;
            this.scheduler = scheduler;
            this.bufferSize = bufferSize;
            this.maxSize = maxSize;
            this.restartTimerOnMaxSize = restartTimerOnMaxSize;
            if (restartTimerOnMaxSize) {
                this.worker = scheduler.createWorker();
            } else {
                this.worker = null;
            }
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2616s, s)) {
                this.f2616s = s;
                Subscriber<? super Flowable<T>> a = this.actual;
                a.onSubscribe(this);
                if (!this.cancelled) {
                    UnicastProcessor<T> w = UnicastProcessor.create(this.bufferSize);
                    this.window = w;
                    long r = requested();
                    if (r != 0) {
                        Disposable d;
                        a.onNext(w);
                        if (r != Long.MAX_VALUE) {
                            produced(1);
                        }
                        ConsumerIndexHolder consumerIndexHolder = new ConsumerIndexHolder(this.producerIndex, this);
                        if (this.restartTimerOnMaxSize) {
                            d = this.worker.schedulePeriodically(consumerIndexHolder, this.timespan, this.timespan, this.unit);
                        } else {
                            d = this.scheduler.schedulePeriodicallyDirect(consumerIndexHolder, this.timespan, this.timespan, this.unit);
                        }
                        if (this.timer.replace(d)) {
                            s.request(Long.MAX_VALUE);
                            return;
                        }
                        return;
                    }
                    this.cancelled = true;
                    s.cancel();
                    a.onError(new MissingBackpressureException("Could not deliver initial window due to lack of requests."));
                }
            }
        }

        public void onNext(T t) {
            if (!this.terminated) {
                if (fastEnter()) {
                    UnicastProcessor<T> w = this.window;
                    w.onNext(t);
                    long c = this.count + 1;
                    if (c >= this.maxSize) {
                        this.producerIndex++;
                        this.count = 0;
                        w.onComplete();
                        long r = requested();
                        if (r != 0) {
                            w = UnicastProcessor.create(this.bufferSize);
                            this.window = w;
                            this.actual.onNext(w);
                            if (r != Long.MAX_VALUE) {
                                produced(1);
                            }
                            if (this.restartTimerOnMaxSize) {
                                Disposable tm = (Disposable) this.timer.get();
                                tm.dispose();
                                Disposable task = this.worker.schedulePeriodically(new ConsumerIndexHolder(this.producerIndex, this), this.timespan, this.timespan, this.unit);
                                if (!this.timer.compareAndSet(tm, task)) {
                                    task.dispose();
                                }
                            }
                        } else {
                            this.window = null;
                            this.f2616s.cancel();
                            this.actual.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                            dispose();
                            return;
                        }
                    }
                    this.count = c;
                    if (leave(-1) == 0) {
                        return;
                    }
                }
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
                drainLoop();
            }
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onError(t);
            dispose();
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onComplete();
            dispose();
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            this.cancelled = true;
        }

        public void dispose() {
            DisposableHelper.dispose(this.timer);
            Worker w = this.worker;
            if (w != null) {
                w.dispose();
            }
        }

        void drainLoop() {
            SimplePlainQueue<Object> q = this.queue;
            Subscriber<? super Flowable<T>> a = this.actual;
            UnicastProcessor<T> w = this.window;
            int missed = 1;
            while (!this.terminated) {
                boolean d = this.done;
                ConsumerIndexHolder o = q.poll();
                boolean empty = o == null;
                boolean isHolder = o instanceof ConsumerIndexHolder;
                if (d && (empty || isHolder)) {
                    this.window = null;
                    q.clear();
                    Throwable err = this.error;
                    if (err != null) {
                        w.onError(err);
                    } else {
                        w.onComplete();
                    }
                    dispose();
                    return;
                } else if (empty) {
                    missed = leave(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else if (isHolder) {
                    ConsumerIndexHolder consumerIndexHolder = o;
                    if (this.restartTimerOnMaxSize || this.producerIndex == consumerIndexHolder.index) {
                        w.onComplete();
                        this.count = 0;
                        w = UnicastProcessor.create(this.bufferSize);
                        this.window = w;
                        r = requested();
                        if (r != 0) {
                            a.onNext(w);
                            if (r != Long.MAX_VALUE) {
                                produced(1);
                            }
                        } else {
                            this.window = null;
                            this.queue.clear();
                            this.f2616s.cancel();
                            a.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
                            dispose();
                            return;
                        }
                    }
                } else {
                    w.onNext(NotificationLite.getValue(o));
                    long c = this.count + 1;
                    if (c >= this.maxSize) {
                        this.producerIndex++;
                        this.count = 0;
                        w.onComplete();
                        r = requested();
                        if (r != 0) {
                            w = UnicastProcessor.create(this.bufferSize);
                            this.window = w;
                            this.actual.onNext(w);
                            if (r != Long.MAX_VALUE) {
                                produced(1);
                            }
                            if (this.restartTimerOnMaxSize) {
                                Disposable tm = (Disposable) this.timer.get();
                                tm.dispose();
                                Disposable task = this.worker.schedulePeriodically(new ConsumerIndexHolder(this.producerIndex, this), this.timespan, this.timespan, this.unit);
                                if (!this.timer.compareAndSet(tm, task)) {
                                    task.dispose();
                                }
                            }
                        } else {
                            this.window = null;
                            this.f2616s.cancel();
                            this.actual.onError(new MissingBackpressureException("Could not deliver window due to lack of requests"));
                            dispose();
                            return;
                        }
                    }
                    this.count = c;
                }
            }
            this.f2616s.cancel();
            q.clear();
            dispose();
        }
    }

    static final class WindowExactUnboundedSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements FlowableSubscriber<T>, Subscription, Runnable {
        static final Object NEXT = new Object();
        final int bufferSize;
        Subscription f2617s;
        final Scheduler scheduler;
        volatile boolean terminated;
        final SequentialDisposable timer = new SequentialDisposable();
        final long timespan;
        final TimeUnit unit;
        UnicastProcessor<T> window;

        WindowExactUnboundedSubscriber(Subscriber<? super Flowable<T>> actual, long timespan, TimeUnit unit, Scheduler scheduler, int bufferSize) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan;
            this.unit = unit;
            this.scheduler = scheduler;
            this.bufferSize = bufferSize;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2617s, s)) {
                this.f2617s = s;
                this.window = UnicastProcessor.create(this.bufferSize);
                Subscriber<? super Flowable<T>> a = this.actual;
                a.onSubscribe(this);
                long r = requested();
                if (r != 0) {
                    a.onNext(this.window);
                    if (r != Long.MAX_VALUE) {
                        produced(1);
                    }
                    if (!this.cancelled && this.timer.replace(this.scheduler.schedulePeriodicallyDirect(this, this.timespan, this.timespan, this.unit))) {
                        s.request(Long.MAX_VALUE);
                        return;
                    }
                    return;
                }
                this.cancelled = true;
                s.cancel();
                a.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
            }
        }

        public void onNext(T t) {
            if (!this.terminated) {
                if (fastEnter()) {
                    this.window.onNext(t);
                    if (leave(-1) == 0) {
                        return;
                    }
                }
                this.queue.offer(NotificationLite.next(t));
                if (!enter()) {
                    return;
                }
                drainLoop();
            }
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onError(t);
            dispose();
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onComplete();
            dispose();
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            this.cancelled = true;
        }

        public void dispose() {
            DisposableHelper.dispose(this.timer);
        }

        public void run() {
            if (this.cancelled) {
                this.terminated = true;
                dispose();
            }
            this.queue.offer(NEXT);
            if (enter()) {
                drainLoop();
            }
        }

        void drainLoop() {
            SimplePlainQueue<Object> q = this.queue;
            Subscriber<? super Flowable<T>> a = this.actual;
            UnicastProcessor<T> w = this.window;
            int missed = 1;
            while (true) {
                boolean term = this.terminated;
                boolean d = this.done;
                Object o = q.poll();
                if (!(d && (o == null || o == NEXT))) {
                    if (o == null) {
                        missed = leave(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else if (o == NEXT) {
                        w.onComplete();
                        if (term) {
                            this.f2617s.cancel();
                        } else {
                            w = UnicastProcessor.create(this.bufferSize);
                            this.window = w;
                            long r = requested();
                            if (r != 0) {
                                a.onNext(w);
                                if (r != Long.MAX_VALUE) {
                                    produced(1);
                                }
                            } else {
                                this.window = null;
                                this.queue.clear();
                                this.f2617s.cancel();
                                dispose();
                                a.onError(new MissingBackpressureException("Could not deliver first window due to lack of requests."));
                                return;
                            }
                        }
                    } else {
                        w.onNext(NotificationLite.getValue(o));
                    }
                }
            }
            this.window = null;
            q.clear();
            dispose();
            Throwable err = this.error;
            if (err != null) {
                w.onError(err);
            } else {
                w.onComplete();
            }
        }
    }

    static final class WindowSkipSubscriber<T> extends QueueDrainSubscriber<T, Object, Flowable<T>> implements Subscription, Runnable {
        final int bufferSize;
        Subscription f2619s;
        volatile boolean terminated;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        final List<UnicastProcessor<T>> windows = new LinkedList();
        final Worker worker;

        final class Completion implements Runnable {
            private final UnicastProcessor<T> processor;

            Completion(UnicastProcessor<T> processor) {
                this.processor = processor;
            }

            public void run() {
                WindowSkipSubscriber.this.complete(this.processor);
            }
        }

        static final class SubjectWork<T> {
            final boolean open;
            final UnicastProcessor<T> f2618w;

            SubjectWork(UnicastProcessor<T> w, boolean open) {
                this.f2618w = w;
                this.open = open;
            }
        }

        WindowSkipSubscriber(Subscriber<? super Flowable<T>> actual, long timespan, long timeskip, TimeUnit unit, Worker worker, int bufferSize) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan;
            this.timeskip = timeskip;
            this.unit = unit;
            this.worker = worker;
            this.bufferSize = bufferSize;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2619s, s)) {
                this.f2619s = s;
                this.actual.onSubscribe(this);
                if (!this.cancelled) {
                    long r = requested();
                    if (r != 0) {
                        UnicastProcessor<T> w = UnicastProcessor.create(this.bufferSize);
                        this.windows.add(w);
                        this.actual.onNext(w);
                        if (r != Long.MAX_VALUE) {
                            produced(1);
                        }
                        this.worker.schedule(new Completion(w), this.timespan, this.unit);
                        this.worker.schedulePeriodically(this, this.timeskip, this.timeskip, this.unit);
                        s.request(Long.MAX_VALUE);
                        return;
                    }
                    s.cancel();
                    this.actual.onError(new MissingBackpressureException("Could not emit the first window due to lack of requests"));
                }
            }
        }

        public void onNext(T t) {
            if (fastEnter()) {
                for (UnicastProcessor<T> w : this.windows) {
                    w.onNext(t);
                }
                if (leave(-1) == 0) {
                    return;
                }
            }
            this.queue.offer(t);
            if (!enter()) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onError(t);
            dispose();
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onComplete();
            dispose();
        }

        public void request(long n) {
            requested(n);
        }

        public void cancel() {
            this.cancelled = true;
        }

        public void dispose() {
            this.worker.dispose();
        }

        void complete(UnicastProcessor<T> w) {
            this.queue.offer(new SubjectWork(w, false));
            if (enter()) {
                drainLoop();
            }
        }

        void drainLoop() {
            SimplePlainQueue<Object> q = this.queue;
            Subscriber<? super Flowable<T>> a = this.actual;
            List<UnicastProcessor<T>> ws = this.windows;
            int missed = 1;
            while (!this.terminated) {
                boolean d = this.done;
                SubjectWork<T> v = q.poll();
                boolean empty = v == null;
                boolean sw = v instanceof SubjectWork;
                UnicastProcessor<T> w;
                if (d && (empty || sw)) {
                    q.clear();
                    Throwable e = this.error;
                    if (e != null) {
                        for (UnicastProcessor<T> w2 : ws) {
                            w2.onError(e);
                        }
                    } else {
                        for (UnicastProcessor<T> w22 : ws) {
                            w22.onComplete();
                        }
                    }
                    ws.clear();
                    dispose();
                    return;
                } else if (empty) {
                    missed = leave(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else if (sw) {
                    SubjectWork<T> work = v;
                    if (!work.open) {
                        ws.remove(work.f2618w);
                        work.f2618w.onComplete();
                        if (ws.isEmpty() && this.cancelled) {
                            this.terminated = true;
                        }
                    } else if (!this.cancelled) {
                        long r = requested();
                        if (r != 0) {
                            w22 = UnicastProcessor.create(this.bufferSize);
                            ws.add(w22);
                            a.onNext(w22);
                            if (r != Long.MAX_VALUE) {
                                produced(1);
                            }
                            this.worker.schedule(new Completion(w22), this.timespan, this.unit);
                        } else {
                            a.onError(new MissingBackpressureException("Can't emit window due to lack of requests"));
                        }
                    }
                } else {
                    for (UnicastProcessor<T> w222 : ws) {
                        w222.onNext(v);
                    }
                }
            }
            this.f2619s.cancel();
            dispose();
            q.clear();
            ws.clear();
        }

        public void run() {
            SubjectWork<T> sw = new SubjectWork(UnicastProcessor.create(this.bufferSize), true);
            if (!this.cancelled) {
                this.queue.offer(sw);
            }
            if (enter()) {
                drainLoop();
            }
        }
    }

    public FlowableWindowTimed(Flowable<T> source, long timespan, long timeskip, TimeUnit unit, Scheduler scheduler, long maxSize, int bufferSize, boolean restartTimerOnMaxSize) {
        super(source);
        this.timespan = timespan;
        this.timeskip = timeskip;
        this.unit = unit;
        this.scheduler = scheduler;
        this.maxSize = maxSize;
        this.bufferSize = bufferSize;
        this.restartTimerOnMaxSize = restartTimerOnMaxSize;
    }

    protected void subscribeActual(Subscriber<? super Flowable<T>> s) {
        SerializedSubscriber<Flowable<T>> actual = new SerializedSubscriber(s);
        if (this.timespan != this.timeskip) {
            this.source.subscribe(new WindowSkipSubscriber(actual, this.timespan, this.timeskip, this.unit, this.scheduler.createWorker(), this.bufferSize));
        } else if (this.maxSize == Long.MAX_VALUE) {
            this.source.subscribe(new WindowExactUnboundedSubscriber(actual, this.timespan, this.unit, this.scheduler, this.bufferSize));
        } else {
            this.source.subscribe(new WindowExactBoundedSubscriber(actual, this.timespan, this.unit, this.scheduler, this.bufferSize, this.maxSize, this.restartTimerOnMaxSize));
        }
    }
}
