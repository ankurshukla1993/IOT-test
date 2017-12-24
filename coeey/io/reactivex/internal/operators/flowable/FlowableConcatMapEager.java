package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscribers.InnerQueuedSubscriber;
import io.reactivex.internal.subscribers.InnerQueuedSubscriberSupport;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ErrorMode;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableConcatMapEager<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final ErrorMode errorMode;
    final Function<? super T, ? extends Publisher<? extends R>> mapper;
    final int maxConcurrency;
    final int prefetch;

    static final class ConcatMapEagerDelayErrorSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription, InnerQueuedSubscriberSupport<R> {
        private static final long serialVersionUID = -4255299542215038287L;
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        volatile InnerQueuedSubscriber<R> current;
        volatile boolean done;
        final ErrorMode errorMode;
        final AtomicThrowable errors = new AtomicThrowable();
        final Function<? super T, ? extends Publisher<? extends R>> mapper;
        final int maxConcurrency;
        final int prefetch;
        final AtomicLong requested = new AtomicLong();
        Subscription f2527s;
        final SpscLinkedArrayQueue<InnerQueuedSubscriber<R>> subscribers;

        ConcatMapEagerDelayErrorSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends Publisher<? extends R>> mapper, int maxConcurrency, int prefetch, ErrorMode errorMode) {
            this.actual = actual;
            this.mapper = mapper;
            this.maxConcurrency = maxConcurrency;
            this.prefetch = prefetch;
            this.errorMode = errorMode;
            this.subscribers = new SpscLinkedArrayQueue(Math.min(prefetch, maxConcurrency));
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2527s, s)) {
                this.f2527s = s;
                this.actual.onSubscribe(this);
                s.request(this.maxConcurrency == Integer.MAX_VALUE ? Long.MAX_VALUE : (long) this.maxConcurrency);
            }
        }

        public void onNext(T t) {
            try {
                Publisher<? extends R> p = (Publisher) ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null Publisher");
                InnerQueuedSubscriber<R> inner = new InnerQueuedSubscriber(this, this.prefetch);
                if (!this.cancelled) {
                    this.subscribers.offer(inner);
                    if (!this.cancelled) {
                        p.subscribe(inner);
                        if (this.cancelled) {
                            inner.cancel();
                            drainAndCancel();
                        }
                    }
                }
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                this.f2527s.cancel();
                onError(ex);
            }
        }

        public void onError(Throwable t) {
            if (this.errors.addThrowable(t)) {
                this.done = true;
                drain();
                return;
            }
            RxJavaPlugins.onError(t);
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2527s.cancel();
                drainAndCancel();
            }
        }

        void drainAndCancel() {
            if (getAndIncrement() == 0) {
                do {
                    cancelAll();
                } while (decrementAndGet() != 0);
            }
        }

        void cancelAll() {
            while (true) {
                InnerQueuedSubscriber<R> inner = (InnerQueuedSubscriber) this.subscribers.poll();
                if (inner != null) {
                    inner.cancel();
                } else {
                    return;
                }
            }
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void innerNext(InnerQueuedSubscriber<R> inner, R value) {
            if (inner.queue().offer(value)) {
                drain();
                return;
            }
            inner.cancel();
            innerError(inner, new MissingBackpressureException());
        }

        public void innerError(InnerQueuedSubscriber<R> inner, Throwable e) {
            if (this.errors.addThrowable(e)) {
                inner.setDone();
                if (this.errorMode != ErrorMode.END) {
                    this.f2527s.cancel();
                }
                drain();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void innerComplete(InnerQueuedSubscriber<R> inner) {
            inner.setDone();
            drain();
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                InnerQueuedSubscriber<R> inner = this.current;
                Subscriber<? super R> a = this.actual;
                ErrorMode em = this.errorMode;
                while (true) {
                    long r = this.requested.get();
                    long e = 0;
                    if (inner == null) {
                        if (em == ErrorMode.END || ((Throwable) this.errors.get()) == null) {
                            inner = (InnerQueuedSubscriber) this.subscribers.poll();
                            if (this.done && inner == null) {
                                break;
                            } else if (inner != null) {
                                this.current = inner;
                            }
                        } else {
                            cancelAll();
                            a.onError(this.errors.terminate());
                            return;
                        }
                    }
                    boolean continueNextSource = false;
                    if (inner != null) {
                        SimpleQueue<R> q = inner.queue();
                        if (q != null) {
                            boolean d;
                            boolean empty;
                            while (e != r) {
                                if (this.cancelled) {
                                    cancelAll();
                                    return;
                                } else if (em != ErrorMode.IMMEDIATE || ((Throwable) this.errors.get()) == null) {
                                    d = inner.isDone();
                                    try {
                                        R v = q.poll();
                                        empty = v == null;
                                        if (!d || !empty) {
                                            if (empty) {
                                                break;
                                            }
                                            a.onNext(v);
                                            e++;
                                            inner.requestOne();
                                        } else {
                                            inner = null;
                                            this.current = null;
                                            this.f2527s.request(1);
                                            continueNextSource = true;
                                            break;
                                        }
                                    } catch (Throwable ex) {
                                        Exceptions.throwIfFatal(ex);
                                        this.current = null;
                                        inner.cancel();
                                        cancelAll();
                                        a.onError(ex);
                                        return;
                                    }
                                } else {
                                    this.current = null;
                                    inner.cancel();
                                    cancelAll();
                                    a.onError(this.errors.terminate());
                                    return;
                                }
                            }
                            if (e == r) {
                                if (this.cancelled) {
                                    cancelAll();
                                    return;
                                } else if (em != ErrorMode.IMMEDIATE || ((Throwable) this.errors.get()) == null) {
                                    d = inner.isDone();
                                    empty = q.isEmpty();
                                    if (d && empty) {
                                        inner = null;
                                        this.current = null;
                                        this.f2527s.request(1);
                                        continueNextSource = true;
                                    }
                                } else {
                                    this.current = null;
                                    inner.cancel();
                                    cancelAll();
                                    a.onError(this.errors.terminate());
                                    return;
                                }
                            }
                        }
                    }
                    if (!(e == 0 || r == Long.MAX_VALUE)) {
                        this.requested.addAndGet(-e);
                    }
                    if (!continueNextSource) {
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    }
                }
                Throwable ex2 = this.errors.terminate();
                if (ex2 != null) {
                    a.onError(ex2);
                } else {
                    a.onComplete();
                }
            }
        }
    }

    public FlowableConcatMapEager(Flowable<T> source, Function<? super T, ? extends Publisher<? extends R>> mapper, int maxConcurrency, int prefetch, ErrorMode errorMode) {
        super(source);
        this.mapper = mapper;
        this.maxConcurrency = maxConcurrency;
        this.prefetch = prefetch;
        this.errorMode = errorMode;
    }

    protected void subscribeActual(Subscriber<? super R> s) {
        this.source.subscribe(new ConcatMapEagerDelayErrorSubscriber(s, this.mapper, this.maxConcurrency, this.prefetch, this.errorMode));
    }
}
