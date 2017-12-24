package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.QueueDrainHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowablePublishMulticast<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final boolean delayError;
    final int prefetch;
    final Function<? super Flowable<T>, ? extends Publisher<? extends R>> selector;

    static final class MulticastProcessor<T> extends Flowable<T> implements FlowableSubscriber<T>, Disposable {
        static final MulticastSubscription[] EMPTY = new MulticastSubscription[0];
        static final MulticastSubscription[] TERMINATED = new MulticastSubscription[0];
        int consumed;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final int limit;
        final int prefetch;
        volatile SimpleQueue<T> queue;
        final AtomicReference<Subscription> f2570s = new AtomicReference();
        int sourceMode;
        final AtomicReference<MulticastSubscription<T>[]> subscribers = new AtomicReference(EMPTY);
        final AtomicInteger wip = new AtomicInteger();

        MulticastProcessor(int prefetch, boolean delayError) {
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
            this.delayError = delayError;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this.f2570s, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(3);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = qs;
                        this.done = true;
                        drain();
                        return;
                    } else if (m == 2) {
                        this.sourceMode = m;
                        this.queue = qs;
                        QueueDrainHelper.request(s, this.prefetch);
                        return;
                    }
                }
                this.queue = QueueDrainHelper.createQueue(this.prefetch);
                QueueDrainHelper.request(s, this.prefetch);
            }
        }

        public void dispose() {
            SubscriptionHelper.cancel(this.f2570s);
            if (this.wip.getAndIncrement() == 0) {
                SimpleQueue<T> q = this.queue;
                if (q != null) {
                    q.clear();
                }
            }
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) this.f2570s.get());
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.sourceMode != 0 || this.queue.offer(t)) {
                    drain();
                    return;
                }
                ((Subscription) this.f2570s.get()).cancel();
                onError(new MissingBackpressureException());
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

        boolean add(MulticastSubscription<T> s) {
            MulticastSubscription[] current;
            MulticastSubscription<T>[] next;
            do {
                current = (MulticastSubscription[]) this.subscribers.get();
                if (current == TERMINATED) {
                    return false;
                }
                int n = current.length;
                next = new MulticastSubscription[(n + 1)];
                System.arraycopy(current, 0, next, 0, n);
                next[n] = s;
            } while (!this.subscribers.compareAndSet(current, next));
            return true;
        }

        void remove(MulticastSubscription<T> s) {
            MulticastSubscription[] current;
            MulticastSubscription<T>[] next;
            do {
                current = (MulticastSubscription[]) this.subscribers.get();
                if (current != TERMINATED && current != EMPTY) {
                    int n = current.length;
                    int j = -1;
                    for (int i = 0; i < n; i++) {
                        if (current[i] == s) {
                            j = i;
                            break;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (n == 1) {
                        next = EMPTY;
                    } else {
                        next = new MulticastSubscription[(n - 1)];
                        System.arraycopy(current, 0, next, 0, j);
                        System.arraycopy(current, j + 1, next, j, (n - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(current, next));
        }

        protected void subscribeActual(Subscriber<? super T> s) {
            MulticastSubscription<T> ms = new MulticastSubscription(s, this);
            s.onSubscribe(ms);
            if (!add(ms)) {
                Throwable ex = this.error;
                if (ex != null) {
                    s.onError(ex);
                } else {
                    s.onComplete();
                }
            } else if (ms.isCancelled()) {
                remove(ms);
            } else {
                drain();
            }
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        void drain() {
            /*
            r28 = this;
            r0 = r28;
            r0 = r0.wip;
            r22 = r0;
            r22 = r22.getAndIncrement();
            if (r22 == 0) goto L_0x000d;
        L_0x000c:
            return;
        L_0x000d:
            r12 = 1;
            r0 = r28;
            r15 = r0.queue;
            r0 = r28;
            r0 = r0.consumed;
            r20 = r0;
            r0 = r28;
            r11 = r0.limit;
            r0 = r28;
            r0 = r0.sourceMode;
            r22 = r0;
            r23 = 1;
            r0 = r22;
            r1 = r23;
            if (r0 == r1) goto L_0x0061;
        L_0x002a:
            r5 = 1;
        L_0x002b:
            r0 = r28;
            r0 = r0.subscribers;
            r22 = r0;
            r4 = r22.get();
            r4 = (io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastSubscription[]) r4;
            r14 = r4.length;
            if (r15 == 0) goto L_0x0164;
        L_0x003a:
            if (r14 == 0) goto L_0x0164;
        L_0x003c:
            r16 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r0 = r4.length;
            r23 = r0;
            r22 = 0;
        L_0x0046:
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x0063;
        L_0x004c:
            r13 = r4[r22];
            r18 = r13.get();
            r24 = -9223372036854775808;
            r24 = (r18 > r24 ? 1 : (r18 == r24 ? 0 : -1));
            if (r24 == 0) goto L_0x005e;
        L_0x0058:
            r24 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1));
            if (r24 <= 0) goto L_0x005e;
        L_0x005c:
            r16 = r18;
        L_0x005e:
            r22 = r22 + 1;
            goto L_0x0046;
        L_0x0061:
            r5 = 0;
            goto L_0x002b;
        L_0x0063:
            r8 = 0;
        L_0x0065:
            r22 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1));
            if (r22 == 0) goto L_0x00c2;
        L_0x0069:
            r22 = r28.isDisposed();
            if (r22 == 0) goto L_0x0073;
        L_0x006f:
            r15.clear();
            goto L_0x000c;
        L_0x0073:
            r0 = r28;
            r6 = r0.done;
            if (r6 == 0) goto L_0x008d;
        L_0x0079:
            r0 = r28;
            r0 = r0.delayError;
            r22 = r0;
            if (r22 != 0) goto L_0x008d;
        L_0x0081:
            r0 = r28;
            r10 = r0.error;
            if (r10 == 0) goto L_0x008d;
        L_0x0087:
            r0 = r28;
            r0.errorAll(r10);
            goto L_0x000c;
        L_0x008d:
            r21 = r15.poll();	 Catch:{ Throwable -> 0x00a5 }
            if (r21 != 0) goto L_0x00b9;
        L_0x0093:
            r7 = 1;
        L_0x0094:
            if (r6 == 0) goto L_0x00c0;
        L_0x0096:
            if (r7 == 0) goto L_0x00c0;
        L_0x0098:
            r0 = r28;
            r10 = r0.error;
            if (r10 == 0) goto L_0x00bb;
        L_0x009e:
            r0 = r28;
            r0.errorAll(r10);
            goto L_0x000c;
        L_0x00a5:
            r10 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r10);
            r0 = r28;
            r0 = r0.f2570s;
            r22 = r0;
            io.reactivex.internal.subscriptions.SubscriptionHelper.cancel(r22);
            r0 = r28;
            r0.errorAll(r10);
            goto L_0x000c;
        L_0x00b9:
            r7 = 0;
            goto L_0x0094;
        L_0x00bb:
            r28.completeAll();
            goto L_0x000c;
        L_0x00c0:
            if (r7 == 0) goto L_0x00d1;
        L_0x00c2:
            r22 = (r8 > r16 ? 1 : (r8 == r16 ? 0 : -1));
            if (r22 != 0) goto L_0x0151;
        L_0x00c6:
            r22 = r28.isDisposed();
            if (r22 == 0) goto L_0x011c;
        L_0x00cc:
            r15.clear();
            goto L_0x000c;
        L_0x00d1:
            r0 = r4.length;
            r23 = r0;
            r22 = 0;
        L_0x00d6:
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x00f6;
        L_0x00dc:
            r13 = r4[r22];
            r24 = r13.get();
            r26 = -9223372036854775808;
            r24 = (r24 > r26 ? 1 : (r24 == r26 ? 0 : -1));
            if (r24 == 0) goto L_0x00f3;
        L_0x00e8:
            r0 = r13.actual;
            r24 = r0;
            r0 = r24;
            r1 = r21;
            r0.onNext(r1);
        L_0x00f3:
            r22 = r22 + 1;
            goto L_0x00d6;
        L_0x00f6:
            r22 = 1;
            r8 = r8 + r22;
            if (r5 == 0) goto L_0x0065;
        L_0x00fc:
            r20 = r20 + 1;
            r0 = r20;
            if (r0 != r11) goto L_0x0065;
        L_0x0102:
            r20 = 0;
            r0 = r28;
            r0 = r0.f2570s;
            r22 = r0;
            r22 = r22.get();
            r22 = (org.reactivestreams.Subscription) r22;
            r0 = (long) r11;
            r24 = r0;
            r0 = r22;
            r1 = r24;
            r0.request(r1);
            goto L_0x0065;
        L_0x011c:
            r0 = r28;
            r6 = r0.done;
            if (r6 == 0) goto L_0x0137;
        L_0x0122:
            r0 = r28;
            r0 = r0.delayError;
            r22 = r0;
            if (r22 != 0) goto L_0x0137;
        L_0x012a:
            r0 = r28;
            r10 = r0.error;
            if (r10 == 0) goto L_0x0137;
        L_0x0130:
            r0 = r28;
            r0.errorAll(r10);
            goto L_0x000c;
        L_0x0137:
            if (r6 == 0) goto L_0x0151;
        L_0x0139:
            r22 = r15.isEmpty();
            if (r22 == 0) goto L_0x0151;
        L_0x013f:
            r0 = r28;
            r10 = r0.error;
            if (r10 == 0) goto L_0x014c;
        L_0x0145:
            r0 = r28;
            r0.errorAll(r10);
            goto L_0x000c;
        L_0x014c:
            r28.completeAll();
            goto L_0x000c;
        L_0x0151:
            r0 = r4.length;
            r23 = r0;
            r22 = 0;
        L_0x0156:
            r0 = r22;
            r1 = r23;
            if (r0 >= r1) goto L_0x0164;
        L_0x015c:
            r13 = r4[r22];
            io.reactivex.internal.util.BackpressureHelper.produced(r13, r8);
            r22 = r22 + 1;
            goto L_0x0156;
        L_0x0164:
            r0 = r20;
            r1 = r28;
            r1.consumed = r0;
            r0 = r28;
            r0 = r0.wip;
            r22 = r0;
            r0 = -r12;
            r23 = r0;
            r12 = r22.addAndGet(r23);
            if (r12 == 0) goto L_0x000c;
        L_0x0179:
            if (r15 != 0) goto L_0x002b;
        L_0x017b:
            r0 = r28;
            r15 = r0.queue;
            goto L_0x002b;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowablePublishMulticast.MulticastProcessor.drain():void");
        }

        void errorAll(Throwable ex) {
            for (MulticastSubscription<T> ms : (MulticastSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                if (ms.get() != Long.MIN_VALUE) {
                    ms.actual.onError(ex);
                }
            }
        }

        void completeAll() {
            for (MulticastSubscription<T> ms : (MulticastSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                if (ms.get() != Long.MIN_VALUE) {
                    ms.actual.onComplete();
                }
            }
        }
    }

    static final class MulticastSubscription<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = 8664815189257569791L;
        final Subscriber<? super T> actual;
        final MulticastProcessor<T> parent;

        MulticastSubscription(Subscriber<? super T> actual, MulticastProcessor<T> parent) {
            this.actual = actual;
            this.parent = parent;
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.addCancel(this, n);
                this.parent.drain();
            }
        }

        public void cancel() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.drain();
            }
        }

        public boolean isCancelled() {
            return get() == Long.MIN_VALUE;
        }
    }

    static final class OutputCanceller<R> implements FlowableSubscriber<R>, Subscription {
        final Subscriber<? super R> actual;
        final MulticastProcessor<?> processor;
        Subscription f2571s;

        OutputCanceller(Subscriber<? super R> actual, MulticastProcessor<?> processor) {
            this.actual = actual;
            this.processor = processor;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2571s, s)) {
                this.f2571s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(R t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
            this.processor.dispose();
        }

        public void onComplete() {
            this.actual.onComplete();
            this.processor.dispose();
        }

        public void request(long n) {
            this.f2571s.request(n);
        }

        public void cancel() {
            this.f2571s.cancel();
            this.processor.dispose();
        }
    }

    public FlowablePublishMulticast(Flowable<T> source, Function<? super Flowable<T>, ? extends Publisher<? extends R>> selector, int prefetch, boolean delayError) {
        super(source);
        this.selector = selector;
        this.prefetch = prefetch;
        this.delayError = delayError;
    }

    protected void subscribeActual(Subscriber<? super R> s) {
        MulticastProcessor<T> mp = new MulticastProcessor(this.prefetch, this.delayError);
        try {
            ((Publisher) ObjectHelper.requireNonNull(this.selector.apply(mp), "selector returned a null Publisher")).subscribe(new OutputCanceller(s, mp));
            this.source.subscribe(mp);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptySubscription.error(ex, s);
        }
    }
}
