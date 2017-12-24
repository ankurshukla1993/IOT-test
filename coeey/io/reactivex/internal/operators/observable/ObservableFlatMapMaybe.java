package io.reactivex.internal.operators.observable;

import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
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

public final class ObservableFlatMapMaybe<T, R> extends AbstractObservableWithUpstream<T, R> {
    final boolean delayErrors;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;

    static final class FlatMapMaybeObserver<T, R> extends AtomicInteger implements Observer<T>, Disposable {
        private static final long serialVersionUID = 8600231336733376951L;
        final AtomicInteger active = new AtomicInteger(1);
        final Observer<? super R> actual;
        volatile boolean cancelled;
        Disposable f2695d;
        final boolean delayErrors;
        final AtomicThrowable errors = new AtomicThrowable();
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference();
        final CompositeDisposable set = new CompositeDisposable();

        final class InnerObserver extends AtomicReference<Disposable> implements MaybeObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            public void onSuccess(R value) {
                FlatMapMaybeObserver.this.innerSuccess(this, value);
            }

            public void onError(Throwable e) {
                FlatMapMaybeObserver.this.innerError(this, e);
            }

            public void onComplete() {
                FlatMapMaybeObserver.this.innerComplete(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((Disposable) get());
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        FlatMapMaybeObserver(Observer<? super R> actual, Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean delayErrors) {
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
        }

        public void onSubscribe(Disposable d) {
            if (DisposableHelper.validate(this.f2695d, d)) {
                this.f2695d = d;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            try {
                MaybeSource<? extends R> ms = (MaybeSource) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null MaybeSource");
                this.active.getAndIncrement();
                InnerObserver inner = new InnerObserver();
                if (!this.cancelled && this.set.add(inner)) {
                    ms.subscribe(inner);
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.f2695d.dispose();
                onError(ex);
            }
        }

        public void onError(Throwable t) {
            this.active.decrementAndGet();
            if (this.errors.addThrowable(t)) {
                if (!this.delayErrors) {
                    this.set.dispose();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            this.active.decrementAndGet();
            drain();
        }

        public void dispose() {
            this.cancelled = true;
            this.f2695d.dispose();
            this.set.dispose();
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        void innerSuccess(InnerObserver inner, R value) {
            boolean d = true;
            this.set.delete(inner);
            if (get() == 0 && compareAndSet(0, 1)) {
                this.actual.onNext(value);
                if (this.active.decrementAndGet() != 0) {
                    d = false;
                }
                SpscLinkedArrayQueue<R> q = (SpscLinkedArrayQueue) this.queue.get();
                if (d && (q == null || q.isEmpty())) {
                    Throwable ex = this.errors.terminate();
                    if (ex != null) {
                        this.actual.onError(ex);
                        return;
                    } else {
                        this.actual.onComplete();
                        return;
                    }
                } else if (decrementAndGet() == 0) {
                    return;
                }
            }
            q = getOrCreateQueue();
            synchronized (q) {
                q.offer(value);
            }
            this.active.decrementAndGet();
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        SpscLinkedArrayQueue<R> getOrCreateQueue() {
            SpscLinkedArrayQueue<R> current;
            do {
                current = (SpscLinkedArrayQueue) this.queue.get();
                if (current != null) {
                    return current;
                }
                current = new SpscLinkedArrayQueue(Observable.bufferSize());
            } while (!this.queue.compareAndSet(null, current));
            return current;
        }

        void innerError(InnerObserver inner, Throwable e) {
            this.set.delete(inner);
            if (this.errors.addThrowable(e)) {
                if (!this.delayErrors) {
                    this.f2695d.dispose();
                    this.set.dispose();
                }
                this.active.decrementAndGet();
                drain();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        void innerComplete(InnerObserver inner) {
            boolean d = true;
            this.set.delete(inner);
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.active.decrementAndGet() != 0) {
                    d = false;
                }
                SpscLinkedArrayQueue<R> q = (SpscLinkedArrayQueue) this.queue.get();
                if (d && (q == null || q.isEmpty())) {
                    Throwable ex = this.errors.terminate();
                    if (ex != null) {
                        this.actual.onError(ex);
                        return;
                    } else {
                        this.actual.onComplete();
                        return;
                    }
                } else if (decrementAndGet() != 0) {
                    drainLoop();
                    return;
                } else {
                    return;
                }
            }
            this.active.decrementAndGet();
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void clear() {
            SpscLinkedArrayQueue<R> q = (SpscLinkedArrayQueue) this.queue.get();
            if (q != null) {
                q.clear();
            }
        }

        void drainLoop() {
            int missed = 1;
            Observer<? super R> a = this.actual;
            AtomicInteger n = this.active;
            AtomicReference<SpscLinkedArrayQueue<R>> qr = this.queue;
            while (!this.cancelled) {
                Throwable ex;
                if (this.delayErrors || ((Throwable) this.errors.get()) == null) {
                    boolean d;
                    boolean empty;
                    if (n.get() == 0) {
                        d = true;
                    } else {
                        d = false;
                    }
                    SpscLinkedArrayQueue<R> q = (SpscLinkedArrayQueue) qr.get();
                    R v = q != null ? q.poll() : null;
                    if (v == null) {
                        empty = true;
                    } else {
                        empty = false;
                    }
                    if (d && empty) {
                        ex = this.errors.terminate();
                        if (ex != null) {
                            a.onError(ex);
                            return;
                        } else {
                            a.onComplete();
                            return;
                        }
                    } else if (empty) {
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else {
                        a.onNext(v);
                    }
                } else {
                    ex = this.errors.terminate();
                    clear();
                    a.onError(ex);
                    return;
                }
            }
            clear();
        }
    }

    public ObservableFlatMapMaybe(ObservableSource<T> source, Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean delayError) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayError;
    }

    protected void subscribeActual(Observer<? super R> s) {
        this.source.subscribe(new FlatMapMaybeObserver(s, this.mapper, this.delayErrors));
    }
}
