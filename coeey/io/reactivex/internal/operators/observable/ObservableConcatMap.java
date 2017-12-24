package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.disposables.SequentialDisposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueDisposable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;

public final class ObservableConcatMap<T, U> extends AbstractObservableWithUpstream<T, U> {
    final int bufferSize;
    final ErrorMode delayErrors;
    final Function<? super T, ? extends ObservableSource<? extends U>> mapper;

    static final class ConcatMapDelayErrorObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = -6951100001833242599L;
        volatile boolean active;
        final Observer<? super R> actual;
        final SequentialDisposable arbiter;
        final int bufferSize;
        volatile boolean cancelled;
        Disposable f2675d;
        volatile boolean done;
        final AtomicThrowable error = new AtomicThrowable();
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        final DelayErrorInnerObserver<R> observer;
        SimpleQueue<T> queue;
        int sourceMode;
        final boolean tillTheEnd;

        static final class DelayErrorInnerObserver<R> implements Observer<R> {
            final Observer<? super R> actual;
            final ConcatMapDelayErrorObserver<?, R> parent;

            DelayErrorInnerObserver(Observer<? super R> actual, ConcatMapDelayErrorObserver<?, R> parent) {
                this.actual = actual;
                this.parent = parent;
            }

            public void onSubscribe(Disposable d) {
                this.parent.arbiter.replace(d);
            }

            public void onNext(R value) {
                this.actual.onNext(value);
            }

            public void onError(Throwable e) {
                ConcatMapDelayErrorObserver<?, R> p = this.parent;
                if (p.error.addThrowable(e)) {
                    if (!p.tillTheEnd) {
                        p.f2675d.dispose();
                    }
                    p.active = false;
                    p.drain();
                    return;
                }
                RxJavaPlugins.onError(e);
            }

            public void onComplete() {
                ConcatMapDelayErrorObserver<?, R> p = this.parent;
                p.active = false;
                p.drain();
            }
        }

        ConcatMapDelayErrorObserver(Observer<? super R> actual, Function<? super T, ? extends ObservableSource<? extends R>> mapper, int bufferSize, boolean tillTheEnd) {
            this.actual = actual;
            this.mapper = mapper;
            this.bufferSize = bufferSize;
            this.tillTheEnd = tillTheEnd;
            this.observer = new DelayErrorInnerObserver(actual, this);
            this.arbiter = new SequentialDisposable();
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2675d, d)) {
                this.f2675d = d;
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
                this.queue = new SpscLinkedArrayQueue(this.bufferSize);
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

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void dispose() {
            this.cancelled = true;
            this.f2675d.dispose();
            this.arbiter.dispose();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super R> actual = this.actual;
                SimpleQueue<T> queue = this.queue;
                AtomicThrowable error = this.error;
                while (true) {
                    if (!this.active) {
                        if (this.cancelled) {
                            queue.clear();
                            return;
                        } else if (this.tillTheEnd || ((Throwable) error.get()) == null) {
                            boolean d = this.done;
                            try {
                                T v = queue.poll();
                                boolean empty = v == null;
                                if (d && empty) {
                                    break;
                                } else if (!empty) {
                                    try {
                                        ObservableSource<? extends R> o = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(v), "The mapper returned a null ObservableSource");
                                        if (o instanceof Callable) {
                                            try {
                                                R w = ((Callable) o).call();
                                                if (!(w == null || this.cancelled)) {
                                                    actual.onNext(w);
                                                }
                                            } catch (Throwable ex) {
                                                Exceptions.throwIfFatal(ex);
                                                error.addThrowable(ex);
                                            }
                                        } else {
                                            this.active = true;
                                            o.subscribe(this.observer);
                                        }
                                    } catch (Throwable ex2) {
                                        Exceptions.throwIfFatal(ex2);
                                        this.cancelled = true;
                                        this.f2675d.dispose();
                                        queue.clear();
                                        error.addThrowable(ex2);
                                        actual.onError(error.terminate());
                                        return;
                                    }
                                }
                            } catch (Throwable ex22) {
                                Exceptions.throwIfFatal(ex22);
                                this.cancelled = true;
                                this.f2675d.dispose();
                                error.addThrowable(ex22);
                                actual.onError(error.terminate());
                                return;
                            }
                        } else {
                            queue.clear();
                            this.cancelled = true;
                            actual.onError(error.terminate());
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.cancelled = true;
                Throwable ex222 = error.terminate();
                if (ex222 != null) {
                    actual.onError(ex222);
                } else {
                    actual.onComplete();
                }
            }
        }
    }

    static final class SourceObserver<T, U> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8828587559905699186L;
        volatile boolean active;
        final Observer<? super U> actual;
        final int bufferSize;
        volatile boolean disposed;
        volatile boolean done;
        int fusionMode;
        final Observer<U> inner;
        final Function<? super T, ? extends ObservableSource<? extends U>> mapper;
        SimpleQueue<T> queue;
        Disposable f2676s;
        final SequentialDisposable sa = new SequentialDisposable();

        static final class InnerObserver<U> implements Observer<U> {
            final Observer<? super U> actual;
            final SourceObserver<?, ?> parent;

            InnerObserver(Observer<? super U> actual, SourceObserver<?, ?> parent) {
                this.actual = actual;
                this.parent = parent;
            }

            public void onSubscribe(Disposable s) {
                this.parent.innerSubscribe(s);
            }

            public void onNext(U t) {
                this.actual.onNext(t);
            }

            public void onError(Throwable t) {
                this.parent.dispose();
                this.actual.onError(t);
            }

            public void onComplete() {
                this.parent.innerComplete();
            }
        }

        SourceObserver(Observer<? super U> actual, Function<? super T, ? extends ObservableSource<? extends U>> mapper, int bufferSize) {
            this.actual = actual;
            this.mapper = mapper;
            this.bufferSize = bufferSize;
            this.inner = new InnerObserver(actual, this);
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2676s, s)) {
                this.f2676s = s;
                if (s instanceof QueueDisposable) {
                    QueueDisposable<T> qd = (QueueDisposable) s;
                    int m = qd.requestFusion(3);
                    if (m == 1) {
                        this.fusionMode = m;
                        this.queue = qd;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        drain();
                        return;
                    } else if (m == 2) {
                        this.fusionMode = m;
                        this.queue = qd;
                        this.actual.onSubscribe(this);
                        return;
                    }
                }
                this.queue = new SpscLinkedArrayQueue(this.bufferSize);
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode == 0) {
                    this.queue.offer(t);
                }
                drain();
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            dispose();
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        void innerComplete() {
            this.active = false;
            drain();
        }

        public boolean isDisposed() {
            return this.disposed;
        }

        public void dispose() {
            this.disposed = true;
            this.sa.dispose();
            this.f2676s.dispose();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        void innerSubscribe(Disposable s) {
            this.sa.update(s);
        }

        void drain() {
            if (getAndIncrement() == 0) {
                while (!this.disposed) {
                    if (!this.active) {
                        boolean d = this.done;
                        try {
                            T t = this.queue.poll();
                            boolean empty = t == null;
                            if (d && empty) {
                                this.disposed = true;
                                this.actual.onComplete();
                                return;
                            } else if (!empty) {
                                try {
                                    ObservableSource<? extends U> o = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null ObservableSource");
                                    this.active = true;
                                    o.subscribe(this.inner);
                                } catch (Throwable ex) {
                                    Exceptions.throwIfFatal(ex);
                                    dispose();
                                    this.queue.clear();
                                    this.actual.onError(ex);
                                    return;
                                }
                            }
                        } catch (Throwable ex2) {
                            Exceptions.throwIfFatal(ex2);
                            dispose();
                            this.queue.clear();
                            this.actual.onError(ex2);
                            return;
                        }
                    }
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
                this.queue.clear();
            }
        }
    }

    public ObservableConcatMap(ObservableSource<T> source, Function<? super T, ? extends ObservableSource<? extends U>> mapper, int bufferSize, ErrorMode delayErrors) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayErrors;
        this.bufferSize = Math.max(8, bufferSize);
    }

    public void subscribeActual(Observer<? super U> s) {
        if (!ObservableScalarXMap.tryScalarXMapSubscribe(this.source, s, this.mapper)) {
            if (this.delayErrors == ErrorMode.IMMEDIATE) {
                this.source.subscribe(new SourceObserver(new SerializedObserver(s), this.mapper, this.bufferSize));
            } else {
                this.source.subscribe(new ConcatMapDelayErrorObserver(s, this.mapper, this.bufferSize, this.delayErrors == ErrorMode.END));
            }
        }
    }
}
