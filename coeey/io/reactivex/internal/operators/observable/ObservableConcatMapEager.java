package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.observers.InnerQueuedObserver;
import io.reactivex.internal.observers.InnerQueuedObserverSupport;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableConcatMapEager<T, R> extends AbstractObservableWithUpstream<T, R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
    final int maxConcurrency;
    final int prefetch;

    static final class ConcatMapEagerMainObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable, InnerQueuedObserverSupport<R> {
        private static final long serialVersionUID = 8080567949447303262L;
        int activeCount;
        final Observer<? super R> actual;
        volatile boolean cancelled;
        InnerQueuedObserver<R> current;
        Disposable f2677d;
        volatile boolean done;
        final AtomicThrowable error = new AtomicThrowable();
        final ErrorMode errorMode;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        final int maxConcurrency;
        final ArrayDeque<InnerQueuedObserver<R>> observers = new ArrayDeque();
        final int prefetch;
        SimpleQueue<T> queue;
        int sourceMode;

        ConcatMapEagerMainObserver(Observer<? super R> actual, Function<? super T, ? extends ObservableSource<? extends R>> mapper, int maxConcurrency, int prefetch, ErrorMode errorMode) {
            this.actual = actual;
            this.mapper = mapper;
            this.maxConcurrency = maxConcurrency;
            this.prefetch = prefetch;
            this.errorMode = errorMode;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2677d, d)) {
                this.f2677d = d;
                if (d instanceof QueueDisposable) {
                    QueueDisposable<T> qd = (QueueDisposable) d;
                    int m = qd.requestFusion(3);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = qd;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        drain();
                        return;
                    } else if (m == 2) {
                        this.sourceMode = m;
                        this.queue = qd;
                        this.actual.onSubscribe(this);
                        return;
                    }
                }
                this.queue = QueueDrainHelper.createQueue(this.prefetch);
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T value) {
            if (this.sourceMode == 0) {
                this.queue.offer(value);
            }
            drain();
        }

        public void onError(Throwable e) {
            if (this.error.addThrowable(e)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void dispose() {
            this.cancelled = true;
            if (getAndIncrement() == 0) {
                this.queue.clear();
                disposeAll();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeAll() {
            InnerQueuedObserver<R> inner = this.current;
            if (inner != null) {
                inner.dispose();
            }
            while (true) {
                inner = (InnerQueuedObserver) this.observers.poll();
                if (inner != null) {
                    inner.dispose();
                } else {
                    return;
                }
            }
        }

        public void innerNext(InnerQueuedObserver<R> inner, R value) {
            inner.queue().offer(value);
            drain();
        }

        public void innerError(InnerQueuedObserver<R> inner, Throwable e) {
            if (this.error.addThrowable(e)) {
                if (this.errorMode == ErrorMode.IMMEDIATE) {
                    this.f2677d.dispose();
                }
                inner.setDone();
                drain();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void innerComplete(InnerQueuedObserver<R> inner) {
            inner.setDone();
            drain();
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                SimpleQueue<T> q = this.queue;
                ArrayDeque<InnerQueuedObserver<R>> observers = this.observers;
                Observer<? super R> a = this.actual;
                ErrorMode errorMode = this.errorMode;
                while (true) {
                    int ac = this.activeCount;
                    while (ac != this.maxConcurrency) {
                        if (this.cancelled) {
                            q.clear();
                            disposeAll();
                            return;
                        } else if (errorMode != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                            T v = q.poll();
                            if (v == null) {
                                break;
                            }
                            try {
                                ObservableSource<? extends R> source = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(v), "The mapper returned a null ObservableSource");
                                InnerQueuedObserver<R> inner = new InnerQueuedObserver(this, this.prefetch);
                                observers.offer(inner);
                                source.subscribe(inner);
                                ac++;
                            } catch (Throwable ex) {
                                Exceptions.throwIfFatal(ex);
                                this.f2677d.dispose();
                                q.clear();
                                disposeAll();
                                this.error.addThrowable(ex);
                                a.onError(this.error.terminate());
                                return;
                            }
                        } else {
                            q.clear();
                            disposeAll();
                            a.onError(this.error.terminate());
                            return;
                        }
                    }
                    this.activeCount = ac;
                    if (this.cancelled) {
                        q.clear();
                        disposeAll();
                        return;
                    } else if (errorMode != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                        boolean d;
                        boolean empty;
                        InnerQueuedObserver<R> active = this.current;
                        if (active == null) {
                            if (errorMode != ErrorMode.BOUNDARY || ((Throwable) this.error.get()) == null) {
                                d = this.done;
                                active = (InnerQueuedObserver) observers.poll();
                                empty = active == null;
                                if (d && empty) {
                                    break;
                                } else if (!empty) {
                                    this.current = active;
                                }
                            } else {
                                q.clear();
                                disposeAll();
                                a.onError(this.error.terminate());
                                return;
                            }
                        }
                        if (active != null) {
                            SimpleQueue<R> aq = active.queue();
                            while (!this.cancelled) {
                                d = active.isDone();
                                if (errorMode != ErrorMode.IMMEDIATE || ((Throwable) this.error.get()) == null) {
                                    try {
                                        R w = aq.poll();
                                        empty = w == null;
                                        if (d && empty) {
                                            this.current = null;
                                            this.activeCount--;
                                        } else if (!empty) {
                                            a.onNext(w);
                                        }
                                    } catch (Throwable ex2) {
                                        Exceptions.throwIfFatal(ex2);
                                        this.error.addThrowable(ex2);
                                        this.current = null;
                                        this.activeCount--;
                                    }
                                } else {
                                    q.clear();
                                    disposeAll();
                                    a.onError(this.error.terminate());
                                    return;
                                }
                            }
                            q.clear();
                            disposeAll();
                            return;
                        }
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else {
                        q.clear();
                        disposeAll();
                        a.onError(this.error.terminate());
                        return;
                    }
                }
                if (((Throwable) this.error.get()) != null) {
                    q.clear();
                    disposeAll();
                    a.onError(this.error.terminate());
                    return;
                }
                a.onComplete();
            }
        }
    }

    public ObservableConcatMapEager(ObservableSource<T> source, Function<? super T, ? extends ObservableSource<? extends R>> mapper, ErrorMode errorMode, int maxConcurrency, int prefetch) {
        super(source);
        this.mapper = mapper;
        this.errorMode = errorMode;
        this.maxConcurrency = maxConcurrency;
        this.prefetch = prefetch;
    }

    protected void subscribeActual(Observer<? super R> observer) {
        this.source.subscribe(new ConcatMapEagerMainObserver(observer, this.mapper, this.maxConcurrency, this.prefetch, this.errorMode));
    }
}
