package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableScanSeed<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final BiFunction<R, ? super T, R> accumulator;
    final Callable<R> seedSupplier;

    static final class ScanSeedSubscriber<T, R> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -1776795561228106469L;
        final BiFunction<R, ? super T, R> accumulator;
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        int consumed;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        final SimplePlainQueue<R> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription f2578s;
        R value;

        ScanSeedSubscriber(Subscriber<? super R> actual, BiFunction<R, ? super T, R> accumulator, R value, int prefetch) {
            this.actual = actual;
            this.accumulator = accumulator;
            this.value = value;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
            this.queue = new SpscArrayQueue(prefetch);
            this.queue.offer(value);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2578s, s)) {
                this.f2578s = s;
                this.actual.onSubscribe(this);
                s.request((long) (this.prefetch - 1));
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    R v = ObjectHelper.requireNonNull(this.accumulator.apply(this.value, t), "The accumulator returned a null value");
                    this.value = v;
                    this.queue.offer(v);
                    drain();
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    this.f2578s.cancel();
                    onError(ex);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.error = t;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void cancel() {
            this.cancelled = true;
            this.f2578s.cancel();
            if (getAndIncrement() == 0) {
                this.queue.clear();
            }
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super R> a = this.actual;
                SimplePlainQueue<R> q = this.queue;
                int lim = this.limit;
                int c = this.consumed;
                do {
                    Throwable ex;
                    long r = this.requested.get();
                    long e = 0;
                    while (e != r) {
                        if (this.cancelled) {
                            q.clear();
                            return;
                        }
                        boolean d = this.done;
                        if (d) {
                            ex = this.error;
                            if (ex != null) {
                                q.clear();
                                a.onError(ex);
                                return;
                            }
                        }
                        R v = q.poll();
                        boolean empty = v == null;
                        if (d && empty) {
                            a.onComplete();
                            return;
                        } else if (empty) {
                            break;
                        } else {
                            a.onNext(v);
                            e++;
                            c++;
                            if (c == lim) {
                                c = 0;
                                this.f2578s.request((long) lim);
                            }
                        }
                    }
                    if (e == r && this.done) {
                        ex = this.error;
                        if (ex != null) {
                            q.clear();
                            a.onError(ex);
                            return;
                        } else if (q.isEmpty()) {
                            a.onComplete();
                            return;
                        }
                    }
                    if (e != 0) {
                        BackpressureHelper.produced(this.requested, e);
                    }
                    this.consumed = c;
                    missed = addAndGet(-missed);
                } while (missed != 0);
            }
        }
    }

    public FlowableScanSeed(Flowable<T> source, Callable<R> seedSupplier, BiFunction<R, ? super T, R> accumulator) {
        super(source);
        this.accumulator = accumulator;
        this.seedSupplier = seedSupplier;
    }

    protected void subscribeActual(Subscriber<? super R> s) {
        try {
            this.source.subscribe(new ScanSeedSubscriber(s, this.accumulator, ObjectHelper.requireNonNull(this.seedSupplier.call(), "The seed supplied is null"), bufferSize()));
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            EmptySubscription.error(e, s);
        }
    }
}
