package io.reactivex.internal.operators.observable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.observers.QueueDrainObserver;
import io.reactivex.internal.queue.MpscLinkedQueue;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.SerializedObserver;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subjects.UnicastSubject;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class ObservableWindowBoundarySupplier<T, B> extends AbstractObservableWithUpstream<T, Observable<T>> {
    final int bufferSize;
    final Callable<? extends ObservableSource<B>> other;

    static final class WindowBoundaryInnerObserver<T, B> extends DisposableObserver<B> {
        boolean done;
        final WindowBoundaryMainObserver<T, B> parent;

        WindowBoundaryInnerObserver(WindowBoundaryMainObserver<T, B> parent) {
            this.parent = parent;
        }

        public void onNext(B b) {
            if (!this.done) {
                this.done = true;
                dispose();
                this.parent.next();
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.parent.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.onComplete();
            }
        }
    }

    static final class WindowBoundaryMainObserver<T, B> extends QueueDrainObserver<T, Object, Observable<T>> implements Disposable {
        static final Object NEXT = new Object();
        final AtomicReference<Disposable> boundary = new AtomicReference();
        final int bufferSize;
        final Callable<? extends ObservableSource<B>> other;
        Disposable f2753s;
        UnicastSubject<T> window;
        final AtomicLong windows = new AtomicLong();

        WindowBoundaryMainObserver(Observer<? super Observable<T>> actual, Callable<? extends ObservableSource<B>> other, int bufferSize) {
            super(actual, new MpscLinkedQueue());
            this.other = other;
            this.bufferSize = bufferSize;
            this.windows.lazySet(1);
        }

        public void onSubscribe(Disposable s) {
            if (DisposableHelper.validate(this.f2753s, s)) {
                this.f2753s = s;
                Observer<? super Observable<T>> a = this.actual;
                a.onSubscribe(this);
                if (!this.cancelled) {
                    try {
                        ObservableSource<B> p = (ObservableSource) ObjectHelper.requireNonNull(this.other.call(), "The first window ObservableSource supplied is null");
                        UnicastSubject<T> w = UnicastSubject.create(this.bufferSize);
                        this.window = w;
                        a.onNext(w);
                        WindowBoundaryInnerObserver<T, B> inner = new WindowBoundaryInnerObserver(this);
                        if (this.boundary.compareAndSet(null, inner)) {
                            this.windows.getAndIncrement();
                            p.subscribe(inner);
                        }
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        s.dispose();
                        a.onError(e);
                    }
                }
            }
        }

        public void onNext(T t) {
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

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.error = t;
            this.done = true;
            if (enter()) {
                drainLoop();
            }
            if (this.windows.decrementAndGet() == 0) {
                DisposableHelper.dispose(this.boundary);
            }
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                if (enter()) {
                    drainLoop();
                }
                if (this.windows.decrementAndGet() == 0) {
                    DisposableHelper.dispose(this.boundary);
                }
                this.actual.onComplete();
            }
        }

        public void dispose() {
            this.cancelled = true;
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void drainLoop() {
            MpscLinkedQueue<Object> q = this.queue;
            Observer<? super Observable<T>> a = this.actual;
            int missed = 1;
            UnicastSubject<T> w = this.window;
            while (true) {
                boolean d = this.done;
                Object o = q.poll();
                boolean empty = o == null;
                if (d && empty) {
                    break;
                } else if (empty) {
                    missed = leave(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else if (o == NEXT) {
                    w.onComplete();
                    if (this.windows.decrementAndGet() == 0) {
                        DisposableHelper.dispose(this.boundary);
                        return;
                    } else if (this.cancelled) {
                        continue;
                    } else {
                        try {
                            ObservableSource<B> p = (ObservableSource) ObjectHelper.requireNonNull(this.other.call(), "The ObservableSource supplied is null");
                            w = UnicastSubject.create(this.bufferSize);
                            this.windows.getAndIncrement();
                            this.window = w;
                            a.onNext(w);
                            WindowBoundaryInnerObserver<T, B> b = new WindowBoundaryInnerObserver(this);
                            if (this.boundary.compareAndSet(this.boundary.get(), b)) {
                                p.subscribe(b);
                            }
                        } catch (Throwable e) {
                            Exceptions.throwIfFatal(e);
                            DisposableHelper.dispose(this.boundary);
                            a.onError(e);
                            return;
                        }
                    }
                } else {
                    w.onNext(NotificationLite.getValue(o));
                }
            }
            DisposableHelper.dispose(this.boundary);
            Throwable e2 = this.error;
            if (e2 != null) {
                w.onError(e2);
            } else {
                w.onComplete();
            }
        }

        void next() {
            this.queue.offer(NEXT);
            if (enter()) {
                drainLoop();
            }
        }
    }

    public ObservableWindowBoundarySupplier(ObservableSource<T> source, Callable<? extends ObservableSource<B>> other, int bufferSize) {
        super(source);
        this.other = other;
        this.bufferSize = bufferSize;
    }

    public void subscribeActual(Observer<? super Observable<T>> t) {
        this.source.subscribe(new WindowBoundaryMainObserver(new SerializedObserver(t), this.other, this.bufferSize));
    }
}
