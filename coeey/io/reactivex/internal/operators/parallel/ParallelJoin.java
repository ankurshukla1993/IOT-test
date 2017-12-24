package io.reactivex.internal.operators.parallel;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelJoin<T> extends Flowable<T> {
    final boolean delayErrors;
    final int prefetch;
    final ParallelFlowable<? extends T> source;

    static final class JoinInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = 8410034718427740355L;
        final int limit;
        final JoinSubscriptionBase<T> parent;
        final int prefetch;
        long produced;
        volatile SimplePlainQueue<T> queue;

        JoinInnerSubscriber(JoinSubscriptionBase<T> parent, int prefetch) {
            this.parent = parent;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this, s)) {
                s.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            this.parent.onNext(this, t);
        }

        public void onError(Throwable t) {
            this.parent.onError(t);
        }

        public void onComplete() {
            this.parent.onComplete();
        }

        public void requestOne() {
            long p = this.produced + 1;
            if (p == ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(p);
                return;
            }
            this.produced = p;
        }

        public void request(long n) {
            long p = this.produced + n;
            if (p >= ((long) this.limit)) {
                this.produced = 0;
                ((Subscription) get()).request(p);
                return;
            }
            this.produced = p;
        }

        public boolean cancel() {
            return SubscriptionHelper.cancel(this);
        }

        SimplePlainQueue<T> getQueue() {
            SimplePlainQueue<T> q = this.queue;
            if (q != null) {
                return q;
            }
            q = new SpscArrayQueue(this.prefetch);
            this.queue = q;
            return q;
        }
    }

    static abstract class JoinSubscriptionBase<T> extends AtomicInteger implements Subscription {
        private static final long serialVersionUID = 3100232009247827843L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final AtomicInteger done = new AtomicInteger();
        final AtomicThrowable errors = new AtomicThrowable();
        final AtomicLong requested = new AtomicLong();
        final JoinInnerSubscriber<T>[] subscribers;

        abstract void drain();

        abstract void onComplete();

        abstract void onError(Throwable th);

        abstract void onNext(JoinInnerSubscriber<T> joinInnerSubscriber, T t);

        JoinSubscriptionBase(Subscriber<? super T> actual, int n, int prefetch) {
            this.actual = actual;
            JoinInnerSubscriber<T>[] a = new JoinInnerSubscriber[n];
            for (int i = 0; i < n; i++) {
                a[i] = new JoinInnerSubscriber(this, prefetch);
            }
            this.subscribers = a;
            this.done.lazySet(n);
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                cancelAll();
                if (getAndIncrement() == 0) {
                    cleanup();
                }
            }
        }

        void cancelAll() {
            for (JoinInnerSubscriber<T> s : this.subscribers) {
                s.cancel();
            }
        }

        void cleanup() {
            for (JoinInnerSubscriber<T> s : this.subscribers) {
                s.queue = null;
            }
        }
    }

    static final class JoinSubscription<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = 6312374661811000451L;

        JoinSubscription(Subscriber<? super T> actual, int n, int prefetch) {
            super(actual, n, prefetch);
        }

        public void onNext(JoinInnerSubscriber<T> inner, T value) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.actual.onNext(value);
                    if (this.requested.get() != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    inner.request(1);
                } else if (!inner.getQueue().offer(value)) {
                    cancelAll();
                    Throwable mbe = new MissingBackpressureException("Queue full?!");
                    if (this.errors.compareAndSet(null, mbe)) {
                        this.actual.onError(mbe);
                        return;
                    } else {
                        RxJavaPlugins.onError(mbe);
                        return;
                    }
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            } else if (!inner.getQueue().offer(value)) {
                cancelAll();
                onError(new MissingBackpressureException("Queue full?!"));
                return;
            } else if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        public void onError(Throwable e) {
            if (this.errors.compareAndSet(null, e)) {
                cancelAll();
                drain();
            } else if (e != this.errors.get()) {
                RxJavaPlugins.onError(e);
            }
        }

        public void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        void drainLoop() {
            int missed = 1;
            JoinInnerSubscriber<T>[] s = this.subscribers;
            Subscriber<? super T> a = this.actual;
            while (true) {
                long r = this.requested.get();
                long e = 0;
                while (e != r) {
                    if (this.cancelled) {
                        cleanup();
                        return;
                    }
                    Throwable ex = (Throwable) this.errors.get();
                    if (ex != null) {
                        cleanup();
                        a.onError(ex);
                        return;
                    }
                    boolean d = this.done.get() == 0;
                    boolean empty = true;
                    for (JoinInnerSubscriber<T> inner : s) {
                        SimplePlainQueue<T> q = inner.queue;
                        if (q != null) {
                            T v = q.poll();
                            if (v != null) {
                                empty = false;
                                a.onNext(v);
                                inner.requestOne();
                                e++;
                                if (e == r) {
                                    break;
                                }
                            } else {
                                continue;
                            }
                        }
                    }
                    if (!d || !empty) {
                        if (empty) {
                            break;
                        }
                    } else {
                        a.onComplete();
                        return;
                    }
                }
                if (e == r) {
                    if (this.cancelled) {
                        cleanup();
                        return;
                    }
                    ex = (Throwable) this.errors.get();
                    if (ex != null) {
                        cleanup();
                        a.onError(ex);
                        return;
                    }
                    d = this.done.get() == 0;
                    empty = true;
                    for (JoinInnerSubscriber<T> inner2 : s) {
                        SimpleQueue<T> q2 = inner2.queue;
                        if (q2 != null && !q2.isEmpty()) {
                            empty = false;
                            break;
                        }
                    }
                    if (d && empty) {
                        a.onComplete();
                        return;
                    }
                }
                if (!(e == 0 || r == Long.MAX_VALUE)) {
                    this.requested.addAndGet(-e);
                }
                int w = get();
                if (w == missed) {
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                } else {
                    missed = w;
                }
            }
        }
    }

    static final class JoinSubscriptionDelayError<T> extends JoinSubscriptionBase<T> {
        private static final long serialVersionUID = -5737965195918321883L;

        JoinSubscriptionDelayError(Subscriber<? super T> actual, int n, int prefetch) {
            super(actual, n, prefetch);
        }

        void onNext(JoinInnerSubscriber<T> inner, T value) {
            if (get() == 0 && compareAndSet(0, 1)) {
                if (this.requested.get() != 0) {
                    this.actual.onNext(value);
                    if (this.requested.get() != Long.MAX_VALUE) {
                        this.requested.decrementAndGet();
                    }
                    inner.request(1);
                } else if (!inner.getQueue().offer(value)) {
                    inner.cancel();
                    this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                    this.done.decrementAndGet();
                    drainLoop();
                    return;
                }
                if (decrementAndGet() == 0) {
                    return;
                }
            }
            if (!inner.getQueue().offer(value) && inner.cancel()) {
                this.errors.addThrowable(new MissingBackpressureException("Queue full?!"));
                this.done.decrementAndGet();
            }
            if (getAndIncrement() != 0) {
                return;
            }
            drainLoop();
        }

        void onError(Throwable e) {
            this.errors.addThrowable(e);
            this.done.decrementAndGet();
            drain();
        }

        void onComplete() {
            this.done.decrementAndGet();
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                drainLoop();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drainLoop() {
            /*
            r22 = this;
            r10 = 1;
            r0 = r22;
            r0 = r0.subscribers;
            r16 = r0;
            r0 = r16;
            r11 = r0.length;
            r0 = r22;
            r2 = r0.actual;
        L_0x000e:
            r0 = r22;
            r0 = r0.requested;
            r19 = r0;
            r14 = r19.get();
            r4 = 0;
        L_0x001a:
            r19 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
            if (r19 == 0) goto L_0x0058;
        L_0x001e:
            r0 = r22;
            r0 = r0.cancelled;
            r19 = r0;
            if (r19 == 0) goto L_0x002a;
        L_0x0026:
            r22.cleanup();
        L_0x0029:
            return;
        L_0x002a:
            r0 = r22;
            r0 = r0.done;
            r19 = r0;
            r19 = r19.get();
            if (r19 != 0) goto L_0x0068;
        L_0x0036:
            r3 = 1;
        L_0x0037:
            r6 = 1;
            r8 = 0;
        L_0x0039:
            if (r8 >= r11) goto L_0x006d;
        L_0x003b:
            r9 = r16[r8];
            r12 = r9.queue;
            if (r12 == 0) goto L_0x006a;
        L_0x0041:
            r17 = r12.poll();
            if (r17 == 0) goto L_0x006a;
        L_0x0047:
            r6 = 0;
            r0 = r17;
            r2.onNext(r0);
            r9.requestOne();
            r20 = 1;
            r4 = r4 + r20;
            r19 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
            if (r19 != 0) goto L_0x006a;
        L_0x0058:
            r19 = (r4 > r14 ? 1 : (r4 == r14 ? 0 : -1));
            if (r19 != 0) goto L_0x00e1;
        L_0x005c:
            r0 = r22;
            r0 = r0.cancelled;
            r19 = r0;
            if (r19 == 0) goto L_0x0096;
        L_0x0064:
            r22.cleanup();
            goto L_0x0029;
        L_0x0068:
            r3 = 0;
            goto L_0x0037;
        L_0x006a:
            r8 = r8 + 1;
            goto L_0x0039;
        L_0x006d:
            if (r3 == 0) goto L_0x0093;
        L_0x006f:
            if (r6 == 0) goto L_0x0093;
        L_0x0071:
            r0 = r22;
            r0 = r0.errors;
            r19 = r0;
            r7 = r19.get();
            r7 = (java.lang.Throwable) r7;
            if (r7 == 0) goto L_0x008f;
        L_0x007f:
            r0 = r22;
            r0 = r0.errors;
            r19 = r0;
            r19 = r19.terminate();
            r0 = r19;
            r2.onError(r0);
            goto L_0x0029;
        L_0x008f:
            r2.onComplete();
            goto L_0x0029;
        L_0x0093:
            if (r6 == 0) goto L_0x001a;
        L_0x0095:
            goto L_0x0058;
        L_0x0096:
            r0 = r22;
            r0 = r0.done;
            r19 = r0;
            r19 = r19.get();
            if (r19 != 0) goto L_0x00d7;
        L_0x00a2:
            r3 = 1;
        L_0x00a3:
            r6 = 1;
            r8 = 0;
        L_0x00a5:
            if (r8 >= r11) goto L_0x00b4;
        L_0x00a7:
            r9 = r16[r8];
            r13 = r9.queue;
            if (r13 == 0) goto L_0x00d9;
        L_0x00ad:
            r19 = r13.isEmpty();
            if (r19 != 0) goto L_0x00d9;
        L_0x00b3:
            r6 = 0;
        L_0x00b4:
            if (r3 == 0) goto L_0x00e1;
        L_0x00b6:
            if (r6 == 0) goto L_0x00e1;
        L_0x00b8:
            r0 = r22;
            r0 = r0.errors;
            r19 = r0;
            r7 = r19.get();
            r7 = (java.lang.Throwable) r7;
            if (r7 == 0) goto L_0x00dc;
        L_0x00c6:
            r0 = r22;
            r0 = r0.errors;
            r19 = r0;
            r19 = r19.terminate();
            r0 = r19;
            r2.onError(r0);
            goto L_0x0029;
        L_0x00d7:
            r3 = 0;
            goto L_0x00a3;
        L_0x00d9:
            r8 = r8 + 1;
            goto L_0x00a5;
        L_0x00dc:
            r2.onComplete();
            goto L_0x0029;
        L_0x00e1:
            r20 = 0;
            r19 = (r4 > r20 ? 1 : (r4 == r20 ? 0 : -1));
            if (r19 == 0) goto L_0x00fc;
        L_0x00e7:
            r20 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r19 = (r14 > r20 ? 1 : (r14 == r20 ? 0 : -1));
            if (r19 == 0) goto L_0x00fc;
        L_0x00f0:
            r0 = r22;
            r0 = r0.requested;
            r19 = r0;
            r0 = -r4;
            r20 = r0;
            r19.addAndGet(r20);
        L_0x00fc:
            r18 = r22.get();
            r0 = r18;
            if (r0 != r10) goto L_0x0113;
        L_0x0104:
            r0 = -r10;
            r19 = r0;
            r0 = r22;
            r1 = r19;
            r10 = r0.addAndGet(r1);
            if (r10 != 0) goto L_0x000e;
        L_0x0111:
            goto L_0x0029;
        L_0x0113:
            r10 = r18;
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelJoin.JoinSubscriptionDelayError.drainLoop():void");
        }
    }

    public ParallelJoin(ParallelFlowable<? extends T> source, int prefetch, boolean delayErrors) {
        this.source = source;
        this.prefetch = prefetch;
        this.delayErrors = delayErrors;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        JoinSubscriptionBase<T> parent;
        if (this.delayErrors) {
            parent = new JoinSubscriptionDelayError(s, this.source.parallelism(), this.prefetch);
        } else {
            parent = new JoinSubscription(s, this.source.parallelism(), this.prefetch);
        }
        s.onSubscribe(parent);
        this.source.subscribe(parent.subscribers);
    }
}
