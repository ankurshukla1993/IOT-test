package io.reactivex.internal.operators.parallel;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.parallel.ParallelFlowable;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLongArray;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelFromPublisher<T> extends ParallelFlowable<T> {
    final int parallelism;
    final int prefetch;
    final Publisher<? extends T> source;

    static final class ParallelDispatcher<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -4470634016609963609L;
        volatile boolean cancelled;
        volatile boolean done;
        final long[] emissions;
        Throwable error;
        int index;
        final int limit;
        final int prefetch;
        int produced;
        SimpleQueue<T> queue;
        final AtomicLongArray requests;
        Subscription f2770s;
        int sourceMode;
        final AtomicInteger subscriberCount = new AtomicInteger();
        final Subscriber<? super T>[] subscribers;

        final class RailSubscription implements Subscription {
            final int f2768j;
            final int f2769m;

            RailSubscription(int j, int m) {
                this.f2768j = j;
                this.f2769m = m;
            }

            public void request(long n) {
                if (SubscriptionHelper.validate(n)) {
                    AtomicLongArray ra = ParallelDispatcher.this.requests;
                    long r;
                    do {
                        r = ra.get(this.f2768j);
                        if (r != Long.MAX_VALUE) {
                        } else {
                            return;
                        }
                    } while (!ra.compareAndSet(this.f2768j, r, BackpressureHelper.addCap(r, n)));
                    if (ParallelDispatcher.this.subscriberCount.get() == this.f2769m) {
                        ParallelDispatcher.this.drain();
                    }
                }
            }

            public void cancel() {
                if (ParallelDispatcher.this.requests.compareAndSet(this.f2769m + this.f2768j, 0, 1)) {
                    ParallelDispatcher.this.cancel(this.f2769m + this.f2769m);
                }
            }
        }

        ParallelDispatcher(Subscriber<? super T>[] subscribers, int prefetch) {
            this.subscribers = subscribers;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
            int m = subscribers.length;
            this.requests = new AtomicLongArray((m + m) + 1);
            this.requests.lazySet(m + m, (long) m);
            this.emissions = new long[m];
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2770s, s)) {
                this.f2770s = s;
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(3);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = qs;
                        this.done = true;
                        setupSubscribers();
                        drain();
                        return;
                    } else if (m == 2) {
                        this.sourceMode = m;
                        this.queue = qs;
                        setupSubscribers();
                        s.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                setupSubscribers();
                s.request((long) this.prefetch);
            }
        }

        void setupSubscribers() {
            Subscriber<? super T>[] subs = this.subscribers;
            int m = subs.length;
            for (int i = 0; i < m && !this.cancelled; i++) {
                this.subscriberCount.lazySet(i + 1);
                subs[i].onSubscribe(new RailSubscription(i, m));
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                drain();
                return;
            }
            this.f2770s.cancel();
            onError(new MissingBackpressureException("Queue is full?"));
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void cancel(int m) {
            if (this.requests.decrementAndGet(m) == 0) {
                this.cancelled = true;
                this.f2770s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drainAsync() {
            /*
            r28 = this;
            r14 = 1;
            r0 = r28;
            r0 = r0.queue;
            r17 = r0;
            r0 = r28;
            r4 = r0.subscribers;
            r0 = r28;
            r0 = r0.requests;
            r18 = r0;
            r0 = r28;
            r8 = r0.emissions;
            r15 = r8.length;
            r0 = r28;
            r13 = r0.index;
            r0 = r28;
            r6 = r0.produced;
        L_0x001e:
            r16 = 0;
        L_0x0020:
            r0 = r28;
            r0 = r0.cancelled;
            r24 = r0;
            if (r24 == 0) goto L_0x002c;
        L_0x0028:
            r17.clear();
        L_0x002b:
            return;
        L_0x002c:
            r0 = r28;
            r7 = r0.done;
            if (r7 == 0) goto L_0x0050;
        L_0x0032:
            r0 = r28;
            r12 = r0.error;
            if (r12 == 0) goto L_0x0050;
        L_0x0038:
            r17.clear();
            r0 = r4.length;
            r25 = r0;
            r24 = 0;
        L_0x0040:
            r0 = r24;
            r1 = r25;
            if (r0 >= r1) goto L_0x002b;
        L_0x0046:
            r19 = r4[r24];
            r0 = r19;
            r0.onError(r12);
            r24 = r24 + 1;
            goto L_0x0040;
        L_0x0050:
            r9 = r17.isEmpty();
            if (r7 == 0) goto L_0x006b;
        L_0x0056:
            if (r9 == 0) goto L_0x006b;
        L_0x0058:
            r0 = r4.length;
            r25 = r0;
            r24 = 0;
        L_0x005d:
            r0 = r24;
            r1 = r25;
            if (r0 >= r1) goto L_0x002b;
        L_0x0063:
            r19 = r4[r24];
            r19.onComplete();
            r24 = r24 + 1;
            goto L_0x005d;
        L_0x006b:
            if (r9 == 0) goto L_0x008b;
        L_0x006d:
            r23 = r28.get();
            r0 = r23;
            if (r0 != r14) goto L_0x010b;
        L_0x0075:
            r0 = r28;
            r0.index = r13;
            r0 = r28;
            r0.produced = r6;
            r0 = -r14;
            r24 = r0;
            r0 = r28;
            r1 = r24;
            r14 = r0.addAndGet(r1);
            if (r14 != 0) goto L_0x001e;
        L_0x008a:
            goto L_0x002b;
        L_0x008b:
            r0 = r18;
            r20 = r0.get(r13);
            r10 = r8[r13];
            r24 = (r20 > r10 ? 1 : (r20 == r10 ? 0 : -1));
            if (r24 == 0) goto L_0x0108;
        L_0x0097:
            r24 = r15 + r13;
            r0 = r18;
            r1 = r24;
            r24 = r0.get(r1);
            r26 = 0;
            r24 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1));
            if (r24 != 0) goto L_0x0108;
        L_0x00a7:
            r22 = r17.poll();	 Catch:{ Throwable -> 0x00e6 }
            if (r22 == 0) goto L_0x006d;
        L_0x00ad:
            r24 = r4[r13];
            r0 = r24;
            r1 = r22;
            r0.onNext(r1);
            r24 = 1;
            r24 = r24 + r10;
            r8[r13] = r24;
            r6 = r6 + 1;
            r5 = r6;
            r0 = r28;
            r0 = r0.limit;
            r24 = r0;
            r0 = r24;
            if (r5 != r0) goto L_0x00da;
        L_0x00c9:
            r6 = 0;
            r0 = r28;
            r0 = r0.f2770s;
            r24 = r0;
            r0 = (long) r5;
            r26 = r0;
            r0 = r24;
            r1 = r26;
            r0.request(r1);
        L_0x00da:
            r16 = 0;
        L_0x00dc:
            r13 = r13 + 1;
            if (r13 != r15) goto L_0x00e1;
        L_0x00e0:
            r13 = 0;
        L_0x00e1:
            r0 = r16;
            if (r0 != r15) goto L_0x0020;
        L_0x00e5:
            goto L_0x006d;
        L_0x00e6:
            r12 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r12);
            r0 = r28;
            r0 = r0.f2770s;
            r24 = r0;
            r24.cancel();
            r0 = r4.length;
            r25 = r0;
            r24 = 0;
        L_0x00f8:
            r0 = r24;
            r1 = r25;
            if (r0 >= r1) goto L_0x002b;
        L_0x00fe:
            r19 = r4[r24];
            r0 = r19;
            r0.onError(r12);
            r24 = r24 + 1;
            goto L_0x00f8;
        L_0x0108:
            r16 = r16 + 1;
            goto L_0x00dc;
        L_0x010b:
            r14 = r23;
            goto L_0x001e;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelFromPublisher.ParallelDispatcher.drainAsync():void");
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drainSync() {
            /*
            r24 = this;
            r9 = 1;
            r0 = r24;
            r12 = r0.queue;
            r0 = r24;
            r2 = r0.subscribers;
            r0 = r24;
            r13 = r0.requests;
            r0 = r24;
            r3 = r0.emissions;
            r10 = r3.length;
            r0 = r24;
            r8 = r0.index;
        L_0x0016:
            r11 = 0;
        L_0x0017:
            r0 = r24;
            r0 = r0.cancelled;
            r19 = r0;
            if (r19 == 0) goto L_0x0023;
        L_0x001f:
            r12.clear();
        L_0x0022:
            return;
        L_0x0023:
            r6 = r12.isEmpty();
            if (r6 == 0) goto L_0x003c;
        L_0x0029:
            r0 = r2.length;
            r20 = r0;
            r19 = 0;
        L_0x002e:
            r0 = r19;
            r1 = r20;
            if (r0 >= r1) goto L_0x0022;
        L_0x0034:
            r16 = r2[r19];
            r16.onComplete();
            r19 = r19 + 1;
            goto L_0x002e;
        L_0x003c:
            r14 = r13.get(r8);
            r4 = r3[r8];
            r19 = (r14 > r4 ? 1 : (r14 == r4 ? 0 : -1));
            if (r19 == 0) goto L_0x00c1;
        L_0x0046:
            r19 = r10 + r8;
            r0 = r19;
            r20 = r13.get(r0);
            r22 = 0;
            r19 = (r20 > r22 ? 1 : (r20 == r22 ? 0 : -1));
            if (r19 != 0) goto L_0x00c1;
        L_0x0054:
            r17 = r12.poll();	 Catch:{ Throwable -> 0x006d }
            if (r17 != 0) goto L_0x008f;
        L_0x005a:
            r0 = r2.length;
            r20 = r0;
            r19 = 0;
        L_0x005f:
            r0 = r19;
            r1 = r20;
            if (r0 >= r1) goto L_0x0022;
        L_0x0065:
            r16 = r2[r19];
            r16.onComplete();
            r19 = r19 + 1;
            goto L_0x005f;
        L_0x006d:
            r7 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r7);
            r0 = r24;
            r0 = r0.f2770s;
            r19 = r0;
            r19.cancel();
            r0 = r2.length;
            r20 = r0;
            r19 = 0;
        L_0x007f:
            r0 = r19;
            r1 = r20;
            if (r0 >= r1) goto L_0x0022;
        L_0x0085:
            r16 = r2[r19];
            r0 = r16;
            r0.onError(r7);
            r19 = r19 + 1;
            goto L_0x007f;
        L_0x008f:
            r19 = r2[r8];
            r0 = r19;
            r1 = r17;
            r0.onNext(r1);
            r20 = 1;
            r20 = r20 + r4;
            r3[r8] = r20;
            r11 = 0;
        L_0x009f:
            r8 = r8 + 1;
            if (r8 != r10) goto L_0x00a4;
        L_0x00a3:
            r8 = 0;
        L_0x00a4:
            if (r11 != r10) goto L_0x0017;
        L_0x00a6:
            r18 = r24.get();
            r0 = r18;
            if (r0 != r9) goto L_0x00c4;
        L_0x00ae:
            r0 = r24;
            r0.index = r8;
            r0 = -r9;
            r19 = r0;
            r0 = r24;
            r1 = r19;
            r9 = r0.addAndGet(r1);
            if (r9 != 0) goto L_0x0016;
        L_0x00bf:
            goto L_0x0022;
        L_0x00c1:
            r11 = r11 + 1;
            goto L_0x009f;
        L_0x00c4:
            r9 = r18;
            goto L_0x0016;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.parallel.ParallelFromPublisher.ParallelDispatcher.drainSync():void");
        }

        void drain() {
            if (getAndIncrement() == 0) {
                if (this.sourceMode == 1) {
                    drainSync();
                } else {
                    drainAsync();
                }
            }
        }
    }

    public ParallelFromPublisher(Publisher<? extends T> source, int parallelism, int prefetch) {
        this.source = source;
        this.parallelism = parallelism;
        this.prefetch = prefetch;
    }

    public int parallelism() {
        return this.parallelism;
    }

    public void subscribe(Subscriber<? super T>[] subscribers) {
        if (validate(subscribers)) {
            this.source.subscribe(new ParallelDispatcher(subscribers, this.prefetch));
        }
    }
}
