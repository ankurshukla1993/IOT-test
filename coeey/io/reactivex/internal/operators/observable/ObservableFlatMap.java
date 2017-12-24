package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableFlatMap<T, U> extends AbstractObservableWithUpstream<T, U> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
    final int maxConcurrency;

    static final class InnerObserver<T, U> extends AtomicReference<Disposable> implements Observer<U> {
        private static final long serialVersionUID = -4606175640614850599L;
        volatile boolean done;
        int fusionMode;
        final long id;
        final MergeObserver<T, U> parent;
        volatile SimpleQueue<U> queue;

        InnerObserver(MergeObserver<T, U> parent, long id) {
            this.id = id;
            this.parent = parent;
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.setOnce(this, s) && (s instanceof QueueDisposable)) {
                QueueDisposable<U> qd = (QueueDisposable) s;
                int m = qd.requestFusion(7);
                if (m == 1) {
                    this.fusionMode = m;
                    this.queue = qd;
                    this.done = true;
                    this.parent.drain();
                } else if (m == 2) {
                    this.fusionMode = m;
                    this.queue = qd;
                }
            }
        }

        public void onNext(U t) {
            if (this.fusionMode == 0) {
                this.parent.tryEmit(t, this);
            } else {
                this.parent.drain();
            }
        }

        public void onError(Throwable t) {
            if (this.parent.errors.addThrowable(t)) {
                if (!this.parent.delayErrors) {
                    this.parent.disposeAll();
                }
                this.done = true;
                this.parent.drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            this.done = true;
            this.parent.drain();
        }

        public void dispose() {
            DisposableHelper.dispose(this);
        }
    }

    static final class MergeObserver<T, U> extends AtomicInteger implements Disposable, Observer<T> {
        static final InnerObserver<?, ?>[] CANCELLED = new InnerObserver[0];
        static final InnerObserver<?, ?>[] EMPTY = new InnerObserver[0];
        private static final long serialVersionUID = -2117620485640801370L;
        final Observer<? super U> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicThrowable errors = new AtomicThrowable();
        long lastId;
        int lastIndex;
        final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
        final int maxConcurrency;
        final AtomicReference<InnerObserver<?, ?>[]> observers;
        volatile SimplePlainQueue<U> queue;
        Disposable f2692s;
        Queue<ObservableSource<? extends U>> sources;
        long uniqueId;
        int wip;

        MergeObserver(Observer<? super U> actual, Function<? super T, ? extends ObservableSource<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
            this.bufferSize = bufferSize;
            if (maxConcurrency != Integer.MAX_VALUE) {
                this.sources = new ArrayDeque(maxConcurrency);
            }
            this.observers = new AtomicReference(EMPTY);
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2692s, s)) {
                this.f2692s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    ObservableSource<? extends U> p = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        synchronized (this) {
                            if (this.wip == this.maxConcurrency) {
                                this.sources.offer(p);
                                return;
                            }
                            this.wip++;
                        }
                    }
                    subscribeInner(p);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2692s.dispose();
                    onError(e);
                }
            }
        }

        void subscribeInner(ObservableSource<? extends U> p) {
            while (p instanceof Callable) {
                tryEmitScalar((Callable) p);
                if (this.maxConcurrency != Integer.MAX_VALUE) {
                    synchronized (this) {
                        p = (ObservableSource) this.sources.poll();
                        if (p == null) {
                            this.wip--;
                            return;
                        }
                    }
                }
                return;
            }
            long j = this.uniqueId;
            this.uniqueId = 1 + j;
            InnerObserver<T, U> inner = new InnerObserver(this, j);
            if (addInner(inner)) {
                p.subscribe(inner);
            }
        }

        boolean addInner(InnerObserver<T, U> inner) {
            InnerObserver[] a;
            InnerObserver<?, ?>[] b;
            do {
                a = (InnerObserver[]) this.observers.get();
                if (a == CANCELLED) {
                    inner.dispose();
                    return false;
                }
                int n = a.length;
                b = new InnerObserver[(n + 1)];
                System.arraycopy(a, 0, b, 0, n);
                b[n] = inner;
            } while (!this.observers.compareAndSet(a, b));
            return true;
        }

        void removeInner(InnerObserver<T, U> inner) {
            InnerObserver[] a;
            InnerObserver<?, ?>[] b;
            do {
                a = (InnerObserver[]) this.observers.get();
                int n = a.length;
                if (n != 0) {
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
                        b = new InnerObserver[(n - 1)];
                        System.arraycopy(a, 0, b, 0, j);
                        System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.observers.compareAndSet(a, b));
        }

        void tryEmitScalar(Callable<? extends U> value) {
            try {
                U u = value.call();
                if (u != null) {
                    if (get() == 0 && compareAndSet(0, 1)) {
                        this.actual.onNext(u);
                        if (decrementAndGet() == 0) {
                            return;
                        }
                    }
                    SimplePlainQueue<U> q = this.queue;
                    if (q == null) {
                        if (this.maxConcurrency == Integer.MAX_VALUE) {
                            q = new SpscLinkedArrayQueue(this.bufferSize);
                        } else {
                            q = new SpscArrayQueue(this.maxConcurrency);
                        }
                        this.queue = q;
                    }
                    if (!q.offer(u)) {
                        onError(new IllegalStateException("Scalar queue full?!"));
                        return;
                    } else if (getAndIncrement() != 0) {
                        return;
                    }
                    drainLoop();
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.errors.addThrowable(ex);
                drain();
            }
        }

        void tryEmit(U value, InnerObserver<T, U> inner) {
            if (get() == 0 && compareAndSet(0, 1)) {
                this.actual.onNext(value);
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            SimpleQueue<U> q = inner.queue;
            if (q == null) {
                q = new SpscLinkedArrayQueue(this.bufferSize);
                inner.queue = q;
            }
            q.offer(value);
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
            } else if (this.errors.addThrowable(t)) {
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

        public void dispose() {
            if (!this.cancelled) {
                this.cancelled = true;
                if (disposeAll()) {
                    Throwable ex = this.errors.terminate();
                    if (ex != null && ex != ExceptionHelper.TERMINATED) {
                        RxJavaPlugins.onError(ex);
                    }
                }
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            Throwable ex;
            Observer<? super U> child = this.actual;
            int missed = 1;
            while (!checkTerminate()) {
                U o;
                SimplePlainQueue<U> svq = this.queue;
                if (svq != null) {
                    while (!checkTerminate()) {
                        o = svq.poll();
                        if (o != null) {
                            child.onNext(o);
                        } else if (o == null) {
                        }
                    }
                    return;
                }
                boolean d = this.done;
                svq = this.queue;
                InnerObserver[] inner = (InnerObserver[]) this.observers.get();
                int n = inner.length;
                if (d && ((svq == null || svq.isEmpty()) && n == 0)) {
                    ex = this.errors.terminate();
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
                            InnerObserver<T, U> is = inner[j];
                            while (!checkTerminate()) {
                                SimpleQueue<U> q = is.queue;
                                if (q != null) {
                                    while (true) {
                                        try {
                                            o = q.poll();
                                            if (o == null) {
                                                break;
                                            }
                                            child.onNext(o);
                                            if (checkTerminate()) {
                                                return;
                                            }
                                        } catch (Throwable ex2) {
                                            Exceptions.throwIfFatal(ex2);
                                            is.dispose();
                                            this.errors.addThrowable(ex2);
                                            if (!checkTerminate()) {
                                                removeInner(is);
                                                innerCompleted = true;
                                                i++;
                                            } else {
                                                return;
                                            }
                                        }
                                    }
                                    if (o == null) {
                                    }
                                }
                                boolean innerDone = is.done;
                                SimpleQueue<U> innerQueue = is.queue;
                                if (innerDone && (innerQueue == null || innerQueue.isEmpty())) {
                                    removeInner(is);
                                    if (!checkTerminate()) {
                                        innerCompleted = true;
                                    } else {
                                        return;
                                    }
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
                if (!innerCompleted) {
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    synchronized (this) {
                        ObservableSource<? extends U> p = (ObservableSource) this.sources.poll();
                        if (p == null) {
                            this.wip--;
                        } else {
                            subscribeInner(p);
                        }
                    }
                } else {
                    continue;
                }
            }
        }

        boolean checkTerminate() {
            if (this.cancelled) {
                return true;
            }
            Throwable e = (Throwable) this.errors.get();
            if (this.delayErrors || e == null) {
                return false;
            }
            disposeAll();
            e = this.errors.terminate();
            if (e == ExceptionHelper.TERMINATED) {
                return true;
            }
            this.actual.onError(e);
            return true;
        }

        boolean disposeAll() {
            int i = 0;
            this.f2692s.dispose();
            if (((InnerObserver[]) this.observers.get()) == CANCELLED) {
                return false;
            }
            InnerObserver[] a = (InnerObserver[]) this.observers.getAndSet(CANCELLED);
            if (a == CANCELLED) {
                return false;
            }
            int length = a.length;
            while (i < length) {
                a[i].dispose();
                i++;
            }
            return true;
        }
    }

    public ObservableFlatMap(ObservableSource<T> source, Function<? super T, ? extends ObservableSource<? extends U>> mapper, boolean delayErrors, int maxConcurrency, int bufferSize) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayErrors;
        this.maxConcurrency = maxConcurrency;
        this.bufferSize = bufferSize;
    }

    public void subscribeActual(Observer<? super U> t) {
        if (!ObservableScalarXMap.tryScalarXMapSubscribe(this.source, t, this.mapper)) {
            this.source.subscribe(new MergeObserver(t, this.mapper, this.delayErrors, this.maxConcurrency, this.bufferSize));
        }
    }
}
