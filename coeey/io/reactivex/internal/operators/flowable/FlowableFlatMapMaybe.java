package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.MaybeObserver;
import io.reactivex.MaybeSource;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableFlatMapMaybe<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayErrors;
    final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
    final int maxConcurrency;

    static final class FlatMapMaybeSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 8600231336733376951L;
        final AtomicInteger active = new AtomicInteger(1);
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        final boolean delayErrors;
        final AtomicThrowable errors = new AtomicThrowable();
        final Function<? super T, ? extends MaybeSource<? extends R>> mapper;
        final int maxConcurrency;
        final AtomicReference<SpscLinkedArrayQueue<R>> queue = new AtomicReference();
        final AtomicLong requested = new AtomicLong();
        Subscription f2550s;
        final CompositeDisposable set = new CompositeDisposable();

        final class InnerObserver extends AtomicReference<Disposable> implements MaybeObserver<R>, Disposable {
            private static final long serialVersionUID = -502562646270949838L;

            InnerObserver() {
            }

            public void onSubscribe(Disposable d) {
                DisposableHelper.setOnce(this, d);
            }

            public void onSuccess(R value) {
                FlatMapMaybeSubscriber.this.innerSuccess(this, value);
            }

            public void onError(Throwable e) {
                FlatMapMaybeSubscriber.this.innerError(this, e);
            }

            public void onComplete() {
                FlatMapMaybeSubscriber.this.innerComplete(this);
            }

            public boolean isDisposed() {
                return DisposableHelper.isDisposed((Disposable) get());
            }

            public void dispose() {
                DisposableHelper.dispose(this);
            }
        }

        FlatMapMaybeSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean delayErrors, int maxConcurrency) {
            this.actual = actual;
            this.mapper = mapper;
            this.delayErrors = delayErrors;
            this.maxConcurrency = maxConcurrency;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2550s, s)) {
                this.f2550s = s;
                this.actual.onSubscribe(this);
                if (this.maxConcurrency == Integer.MAX_VALUE) {
                    s.request(Long.MAX_VALUE);
                } else {
                    s.request((long) this.maxConcurrency);
                }
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
                this.f2550s.cancel();
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

        public void cancel() {
            this.cancelled = true;
            this.f2550s.cancel();
            this.set.dispose();
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        void innerSuccess(InnerObserver inner, R value) {
            boolean d = true;
            this.set.delete(inner);
            SpscLinkedArrayQueue<R> q;
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.active.decrementAndGet() != 0) {
                    d = false;
                }
                if (this.requested.get() != 0) {
                    this.actual.onNext(value);
                    q = (SpscLinkedArrayQueue) this.queue.get();
                    if (d && (q == null || q.isEmpty())) {
                        Throwable ex = this.errors.terminate();
                        if (ex != null) {
                            this.actual.onError(ex);
                            return;
                        } else {
                            this.actual.onComplete();
                            return;
                        }
                    }
                    BackpressureHelper.produced(this.requested, 1);
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        this.f2550s.request(1);
                    }
                } else {
                    q = getOrCreateQueue();
                    synchronized (q) {
                        q.offer(value);
                    }
                }
                if (decrementAndGet() == 0) {
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
                current = new SpscLinkedArrayQueue(Flowable.bufferSize());
            } while (!this.queue.compareAndSet(null, current));
            return current;
        }

        void innerError(InnerObserver inner, Throwable e) {
            this.set.delete(inner);
            if (this.errors.addThrowable(e)) {
                if (!this.delayErrors) {
                    this.f2550s.cancel();
                    this.set.dispose();
                } else if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.f2550s.request(1);
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
                }
                if (this.maxConcurrency != Integer.MAX_VALUE) {
                    this.f2550s.request(1);
                }
                if (decrementAndGet() != 0) {
                    drainLoop();
                    return;
                }
                return;
            }
            this.active.decrementAndGet();
            if (this.maxConcurrency != Integer.MAX_VALUE) {
                this.f2550s.request(1);
            }
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
            Subscriber<? super R> a = this.actual;
            AtomicInteger n = this.active;
            AtomicReference<SpscLinkedArrayQueue<R>> qr = this.queue;
            do {
                long r = this.requested.get();
                long e = 0;
                while (e != r) {
                    boolean d;
                    Throwable ex;
                    if (this.cancelled) {
                        clear();
                        return;
                    } else if (this.delayErrors || ((Throwable) this.errors.get()) == null) {
                        d = n.get() == 0;
                        SpscLinkedArrayQueue<R> q = (SpscLinkedArrayQueue) qr.get();
                        R v = q != null ? q.poll() : null;
                        boolean empty = v == null;
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
                            break;
                        } else {
                            a.onNext(v);
                            e++;
                        }
                    } else {
                        ex = this.errors.terminate();
                        clear();
                        a.onError(ex);
                        return;
                    }
                }
                if (e == r) {
                    if (this.cancelled) {
                        clear();
                        return;
                    } else if (this.delayErrors || ((Throwable) this.errors.get()) == null) {
                        d = n.get() == 0;
                        q = (SpscLinkedArrayQueue) qr.get();
                        empty = q == null || q.isEmpty();
                        if (d && empty) {
                            ex = this.errors.terminate();
                            if (ex != null) {
                                a.onError(ex);
                                return;
                            } else {
                                a.onComplete();
                                return;
                            }
                        }
                    } else {
                        ex = this.errors.terminate();
                        clear();
                        a.onError(ex);
                        return;
                    }
                }
                if (e != 0) {
                    BackpressureHelper.produced(this.requested, e);
                    if (this.maxConcurrency != Integer.MAX_VALUE) {
                        this.f2550s.request(e);
                    }
                }
                missed = addAndGet(-missed);
            } while (missed != 0);
        }
    }

    public FlowableFlatMapMaybe(Flowable<T> source, Function<? super T, ? extends MaybeSource<? extends R>> mapper, boolean delayError, int maxConcurrency) {
        super(source);
        this.mapper = mapper;
        this.delayErrors = delayError;
        this.maxConcurrency = maxConcurrency;
    }

    protected void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe(new FlatMapMaybeSubscriber(s, this.mapper, this.delayErrors, this.maxConcurrency));
    }
}
