package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.subjects.UnicastSubject;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWindowTimed<T> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int bufferSize;
    final long maxSize;
    final boolean restartTimerOnMaxSize;
    final Scheduler scheduler;
    final long timeskip;
    final long timespan;
    final TimeUnit unit;

    static final class WindowExactBoundedObserver<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {
        final int bufferSize;
        long count;
        final long maxSize;
        long producerIndex;
        final boolean restartTimerOnMaxSize;
        Disposable f2754s;
        final Scheduler scheduler;
        volatile boolean terminated;
        final AtomicReference<Disposable> timer = new AtomicReference();
        final long timespan;
        final TimeUnit unit;
        UnicastSubject<T> window;
        final Worker worker;

        static final class ConsumerIndexHolder implements Runnable {
            final long index;
            final WindowExactBoundedObserver<?> parent;

            ConsumerIndexHolder(long index, WindowExactBoundedObserver<?> parent) {
                this.index = index;
                this.parent = parent;
            }

            public void run() {
                WindowExactBoundedObserver<?> p = this.parent;
                if (p.cancelled) {
                    p.terminated = true;
                    p.disposeTimer();
                } else {
                    p.queue.offer(this);
                }
                if (p.enter()) {
                    p.drainLoop();
                }
            }
        }

        WindowExactBoundedObserver(Observer<? super Observable<T>> actual, long timespan, TimeUnit unit, Scheduler scheduler, int bufferSize, long maxSize, boolean restartTimerOnMaxSize) {
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

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2754s, s)) {
                this.f2754s = s;
                Observer<? super Observable<T>> a = this.actual;
                a.onSubscribe(this);
                if (!this.cancelled) {
                    Disposable d;
                    UnicastSubject<T> w = UnicastSubject.create(this.bufferSize);
                    this.window = w;
                    a.onNext(w);
                    ConsumerIndexHolder consumerIndexHolder = new ConsumerIndexHolder(this.producerIndex, this);
                    if (this.restartTimerOnMaxSize) {
                        d = this.worker.schedulePeriodically(consumerIndexHolder, this.timespan, this.timespan, this.unit);
                    } else {
                        d = this.scheduler.schedulePeriodicallyDirect(consumerIndexHolder, this.timespan, this.timespan, this.unit);
                    }
                    DisposableHelper.replace(this.timer, d);
                }
            }
        }

        public void onNext(T t) {
            if (!this.terminated) {
                if (fastEnter()) {
                    UnicastSubject<T> w = this.window;
                    w.onNext(t);
                    long c = this.count + 1;
                    if (c >= this.maxSize) {
                        this.producerIndex++;
                        this.count = 0;
                        w.onComplete();
                        w = UnicastSubject.create(this.bufferSize);
                        this.window = w;
                        this.actual.onNext(w);
                        if (this.restartTimerOnMaxSize) {
                            ((Disposable) this.timer.get()).dispose();
                            DisposableHelper.replace(this.timer, this.worker.schedulePeriodically(new ConsumerIndexHolder(this.producerIndex, this), this.timespan, this.timespan, this.unit));
                        }
                    } else {
                        this.count = c;
                    }
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
            disposeTimer();
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onComplete();
            disposeTimer();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeTimer() {
            DisposableHelper.dispose(this.timer);
            Worker w = this.worker;
            if (w != null) {
                w.dispose();
            }
        }

        void drainLoop() {
            MpscLinkedQueue<Object> q = (MpscLinkedQueue) this.queue;
            Observer<? super Observable<T>> a = this.actual;
            UnicastSubject<T> w = this.window;
            int missed = 1;
            while (!this.terminated) {
                boolean d = this.done;
                ConsumerIndexHolder o = q.poll();
                boolean empty = o == null;
                boolean isHolder = o instanceof ConsumerIndexHolder;
                if (d && (empty || isHolder)) {
                    this.window = null;
                    q.clear();
                    disposeTimer();
                    Throwable err = this.error;
                    if (err != null) {
                        w.onError(err);
                        return;
                    } else {
                        w.onComplete();
                        return;
                    }
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
                        w = UnicastSubject.create(this.bufferSize);
                        this.window = w;
                        a.onNext(w);
                    }
                } else {
                    w.onNext(NotificationLite.getValue(o));
                    long c = this.count + 1;
                    if (c >= this.maxSize) {
                        this.producerIndex++;
                        this.count = 0;
                        w.onComplete();
                        w = UnicastSubject.create(this.bufferSize);
                        this.window = w;
                        this.actual.onNext(w);
                        if (this.restartTimerOnMaxSize) {
                            Disposable tm = (Disposable) this.timer.get();
                            tm.dispose();
                            Disposable task = this.worker.schedulePeriodically(new ConsumerIndexHolder(this.producerIndex, this), this.timespan, this.timespan, this.unit);
                            if (!this.timer.compareAndSet(tm, task)) {
                                task.dispose();
                            }
                        }
                    } else {
                        this.count = c;
                    }
                }
            }
            this.f2754s.dispose();
            q.clear();
            disposeTimer();
        }
    }

    static final class WindowExactUnboundedObserver<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Observer<T>, Disposable, Runnable {
        static final Object NEXT = new Object();
        final int bufferSize;
        Disposable f2755s;
        final Scheduler scheduler;
        volatile boolean terminated;
        final AtomicReference<Disposable> timer = new AtomicReference();
        final long timespan;
        final TimeUnit unit;
        UnicastSubject<T> window;

        WindowExactUnboundedObserver(Observer<? super Observable<T>> actual, long timespan, TimeUnit unit, Scheduler scheduler, int bufferSize) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan;
            this.unit = unit;
            this.scheduler = scheduler;
            this.bufferSize = bufferSize;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2755s, s)) {
                this.f2755s = s;
                this.window = UnicastSubject.create(this.bufferSize);
                Observer<? super Observable<T>> a = this.actual;
                a.onSubscribe(this);
                a.onNext(this.window);
                if (!this.cancelled) {
                    DisposableHelper.replace(this.timer, this.scheduler.schedulePeriodicallyDirect(this, this.timespan, this.timespan, this.unit));
                }
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
            disposeTimer();
            this.actual.onError(t);
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            disposeTimer();
            this.actual.onComplete();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeTimer() {
            DisposableHelper.dispose(this.timer);
        }

        public void run() {
            if (this.cancelled) {
                this.terminated = true;
                disposeTimer();
            }
            this.queue.offer(NEXT);
            if (enter()) {
                drainLoop();
            }
        }

        void drainLoop() {
            MpscLinkedQueue<Object> q = this.queue;
            Observer<? super Observable<T>> a = this.actual;
            UnicastSubject<T> w = this.window;
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
                            this.f2755s.dispose();
                        } else {
                            w = UnicastSubject.create(this.bufferSize);
                            this.window = w;
                            a.onNext(w);
                        }
                    } else {
                        w.onNext(NotificationLite.getValue(o));
                    }
                }
            }
            this.window = null;
            q.clear();
            disposeTimer();
            Throwable err = this.error;
            if (err != null) {
                w.onError(err);
            } else {
                w.onComplete();
            }
        }
    }

    static final class WindowSkipObserver<T> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable, Runnable {
        final int bufferSize;
        Disposable f2758s;
        volatile boolean terminated;
        final long timeskip;
        final long timespan;
        final TimeUnit unit;
        final List<UnicastSubject<T>> windows = new LinkedList();
        final Worker worker;

        final class CompletionTask implements Runnable {
            private final UnicastSubject<T> f2756w;

            CompletionTask(UnicastSubject<T> w) {
                this.f2756w = w;
            }

            public void run() {
                WindowSkipObserver.this.complete(this.f2756w);
            }
        }

        static final class SubjectWork<T> {
            final boolean open;
            final UnicastSubject<T> f2757w;

            SubjectWork(UnicastSubject<T> w, boolean open) {
                this.f2757w = w;
                this.open = open;
            }
        }

        WindowSkipObserver(Observer<? super Observable<T>> actual, long timespan, long timeskip, TimeUnit unit, Worker worker, int bufferSize) {
            super(actual, new MpscLinkedQueue());
            this.timespan = timespan;
            this.timeskip = timeskip;
            this.unit = unit;
            this.worker = worker;
            this.bufferSize = bufferSize;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2758s, s)) {
                this.f2758s = s;
                this.actual.onSubscribe(this);
                if (!this.cancelled) {
                    UnicastSubject<T> w = UnicastSubject.create(this.bufferSize);
                    this.windows.add(w);
                    this.actual.onNext(w);
                    this.worker.schedule(new CompletionTask(w), this.timespan, this.unit);
                    this.worker.schedulePeriodically(this, this.timeskip, this.timeskip, this.unit);
                }
            }
        }

        public void onNext(T t) {
            if (fastEnter()) {
                for (UnicastSubject<T> w : this.windows) {
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
            disposeWorker();
        }

        public void onComplete() {
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            this.actual.onComplete();
            disposeWorker();
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeWorker() {
            this.worker.dispose();
        }

        void complete(UnicastSubject<T> w) {
            this.queue.offer(new SubjectWork(w, false));
            if (enter()) {
                drainLoop();
            }
        }

        void drainLoop() {
            MpscLinkedQueue<Object> q = this.queue;
            Observer<? super Observable<T>> a = this.actual;
            List<UnicastSubject<T>> ws = this.windows;
            int missed = 1;
            while (!this.terminated) {
                boolean d = this.done;
                SubjectWork<T> v = q.poll();
                boolean empty = v == null;
                boolean sw = v instanceof SubjectWork;
                UnicastSubject<T> w;
                if (d && (empty || sw)) {
                    q.clear();
                    Throwable e = this.error;
                    if (e != null) {
                        for (UnicastSubject<T> w2 : ws) {
                            w2.onError(e);
                        }
                    } else {
                        for (UnicastSubject<T> w22 : ws) {
                            w22.onComplete();
                        }
                    }
                    disposeWorker();
                    ws.clear();
                    return;
                } else if (empty) {
                    missed = leave(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else if (sw) {
                    SubjectWork<T> work = v;
                    if (!work.open) {
                        ws.remove(work.f2757w);
                        work.f2757w.onComplete();
                        if (ws.isEmpty() && this.cancelled) {
                            this.terminated = true;
                        }
                    } else if (!this.cancelled) {
                        w22 = UnicastSubject.create(this.bufferSize);
                        ws.add(w22);
                        a.onNext(w22);
                        this.worker.schedule(new CompletionTask(w22), this.timespan, this.unit);
                    }
                } else {
                    for (UnicastSubject<T> w222 : ws) {
                        w222.onNext(v);
                    }
                }
            }
            this.f2758s.dispose();
            disposeWorker();
            q.clear();
            ws.clear();
        }

        public void run() {
            SubjectWork<T> sw = new SubjectWork(UnicastSubject.create(this.bufferSize), true);
            if (!this.cancelled) {
                this.queue.offer(sw);
            }
            if (enter()) {
                drainLoop();
            }
        }
    }

    public ObservableWindowTimed(ObservableSource<T> source, long timespan, long timeskip, TimeUnit unit, Scheduler scheduler, long maxSize, int bufferSize, boolean restartTimerOnMaxSize) {
        super(source);
        this.timespan = timespan;
        this.timeskip = timeskip;
        this.unit = unit;
        this.scheduler = scheduler;
        this.maxSize = maxSize;
        this.bufferSize = bufferSize;
        this.restartTimerOnMaxSize = restartTimerOnMaxSize;
    }

    public void subscribeActual(Observer<? super Observable<T>> t) {
        SerializedObserver<Observable<T>> actual = new SerializedObserver(t);
        if (this.timespan != this.timeskip) {
            this.source.subscribe(new WindowSkipObserver(actual, this.timespan, this.timeskip, this.unit, this.scheduler.createWorker(), this.bufferSize));
        } else if (this.maxSize == Long.MAX_VALUE) {
            this.source.subscribe(new WindowExactUnboundedObserver(actual, this.timespan, this.unit, this.scheduler, this.bufferSize));
        } else {
            this.source.subscribe(new WindowExactBoundedObserver(actual, this.timespan, this.unit, this.scheduler, this.bufferSize, this.maxSize, this.restartTimerOnMaxSize));
        }
    }
}
