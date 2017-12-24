package io.reactivex.internal.operators.observable;

import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableSwitchMap<T, R> extends AbstractObservableWithUpstream<T, R> {
    final int bufferSize;
    final boolean delayErrors;
    final Function<? super T, ? extends ObservableSource<? extends R>> mapper;

    static final class SwitchMapInnerObserver<T, R> extends AtomicReference<Disposable> implements Observer<R> {
        private static final long serialVersionUID = 3837284832786408377L;
        volatile boolean done;
        final long index;
        final SwitchMapObserver<T, R> parent;
        final SpscLinkedArrayQueue<R> queue;

        SwitchMapInnerObserver(SwitchMapObserver<T, R> parent, long index, int bufferSize) {
            this.parent = parent;
            this.index = index;
            this.queue = new SpscLinkedArrayQueue(bufferSize);
        }

        public void onSubscribe(Disposable s) {
            DisposableHelper.setOnce(this, s);
        }

        public void onNext(R t) {
            if (this.index == this.parent.unique) {
                this.queue.offer(t);
                this.parent.drain();
            }
        }

        public void onError(Throwable t) {
            this.parent.innerError(this, t);
        }

        public void onComplete() {
            if (this.index == this.parent.unique) {
                this.done = true;
                this.parent.drain();
            }
        }

        public void cancel() {
            DisposableHelper.dispose(this);
        }
    }

    static final class SwitchMapObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        static final SwitchMapInnerObserver<Object, Object> CANCELLED = new SwitchMapInnerObserver(null, -1, 1);
        private static final long serialVersionUID = -3491074160481096299L;
        final AtomicReference<SwitchMapInnerObserver<T, R>> active = new AtomicReference();
        final Observer<? super R> actual;
        final int bufferSize;
        volatile boolean cancelled;
        final boolean delayErrors;
        volatile boolean done;
        final AtomicThrowable errors;
        final Function<? super T, ? extends ObservableSource<? extends R>> mapper;
        Disposable f2730s;
        volatile long unique;

        static {
            CANCELLED.cancel();
        }

        SwitchMapObserver(Observer<? super R> actual, Function<? super T, ? extends ObservableSource<? extends R>> mapper, int bufferSize, boolean delayErrors) {
            this.actual = actual;
            this.mapper = mapper;
            this.bufferSize = bufferSize;
            this.delayErrors = delayErrors;
            this.errors = new AtomicThrowable();
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2730s, s)) {
                this.f2730s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long c = this.unique + 1;
            this.unique = c;
            SwitchMapInnerObserver<T, R> inner = (SwitchMapInnerObserver) this.active.get();
            if (inner != null) {
                inner.cancel();
            }
            try {
                ObservableSource<? extends R> p = (ObservableSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The ObservableSource returned is null");
                SwitchMapInnerObserver<T, R> nextInner = new SwitchMapInnerObserver(this, c, this.bufferSize);
                do {
                    inner = (SwitchMapInnerObserver) this.active.get();
                    if (inner == CANCELLED) {
                        return;
                    }
                } while (!this.active.compareAndSet(inner, nextInner));
                p.subscribe(nextInner);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.f2730s.dispose();
                onError(e);
            }
        }

        public void onError(Throwable t) {
            if (this.done || !this.errors.addThrowable(t)) {
                if (!this.delayErrors) {
                    disposeInner();
                }
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            drain();
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
                this.f2730s.dispose();
                disposeInner();
            }
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void disposeInner() {
            if (((SwitchMapInnerObserver) this.active.get()) != CANCELLED) {
                SwitchMapInnerObserver<T, R> a = (SwitchMapInnerObserver) this.active.getAndSet(CANCELLED);
                if (a != CANCELLED && a != null) {
                    a.cancel();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Observer<? super R> a = this.actual;
                int missing = 1;
                while (!this.cancelled) {
                    boolean empty;
                    if (this.done) {
                        if (this.active.get() == null) {
                            empty = true;
                        } else {
                            empty = false;
                        }
                        if (this.delayErrors) {
                            if (empty) {
                                Throwable ex = (Throwable) this.errors.get();
                                if (ex != null) {
                                    a.onError(ex);
                                    return;
                                } else {
                                    a.onComplete();
                                    return;
                                }
                            }
                        } else if (((Throwable) this.errors.get()) != null) {
                            a.onError(this.errors.terminate());
                            return;
                        } else if (empty) {
                            a.onComplete();
                            return;
                        }
                    }
                    SwitchMapInnerObserver<T, R> inner = (SwitchMapInnerObserver) this.active.get();
                    if (inner != null) {
                        SpscLinkedArrayQueue<R> q = inner.queue;
                        if (inner.done) {
                            empty = q.isEmpty();
                            if (this.delayErrors) {
                                if (empty) {
                                    this.active.compareAndSet(inner, null);
                                }
                            } else if (((Throwable) this.errors.get()) != null) {
                                a.onError(this.errors.terminate());
                                return;
                            } else if (empty) {
                                this.active.compareAndSet(inner, null);
                            }
                        }
                        boolean retry = false;
                        while (!this.cancelled) {
                            if (inner != this.active.get()) {
                                retry = true;
                            } else if (this.delayErrors || ((Throwable) this.errors.get()) == null) {
                                boolean d = inner.done;
                                R v = q.poll();
                                if (v == null) {
                                    empty = true;
                                } else {
                                    empty = false;
                                }
                                if (d && empty) {
                                    this.active.compareAndSet(inner, null);
                                    retry = true;
                                } else if (!empty) {
                                    a.onNext(v);
                                }
                            } else {
                                a.onError(this.errors.terminate());
                                return;
                            }
                            if (retry) {
                                continue;
                            }
                        }
                        return;
                    }
                    missing = addAndGet(-missing);
                    if (missing == 0) {
                        return;
                    }
                }
            }
        }

        void innerError(SwitchMapInnerObserver<T, R> inner, Throwable ex) {
            if (inner.index == this.unique && this.errors.addThrowable(ex)) {
                if (!this.delayErrors) {
                    this.f2730s.dispose();
                }
                inner.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(ex);
        }
    }

    public ObservableSwitchMap(ObservableSource<T> source, Function<? super T, ? extends ObservableSource<? extends R>> mapper, int bufferSize, boolean delayErrors) {
        super(source);
        this.mapper = mapper;
        this.bufferSize = bufferSize;
        this.delayErrors = delayErrors;
    }

    public void subscribeActual(Observer<? super R> t) {
        if (!ObservableScalarXMap.tryScalarXMapSubscribe(this.source, t, this.mapper)) {
            this.source.subscribe(new SwitchMapObserver(t, this.mapper, this.bufferSize, this.delayErrors));
        }
    }
}
