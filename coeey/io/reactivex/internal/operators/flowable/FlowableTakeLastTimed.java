package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableTakeLastTimed<T> extends AbstractFlowableWithUpstream<T, T> {
    final int bufferSize;
    final long count;
    final boolean delayError;
    final Scheduler scheduler;
    final long time;
    final TimeUnit unit;

    static final class TakeLastTimedSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -5677354903406201275L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final long count;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final SpscLinkedArrayQueue<Object> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription f2594s;
        final Scheduler scheduler;
        final long time;
        final TimeUnit unit;

        TakeLastTimedSubscriber(Subscriber<? super T> actual, long count, long time, TimeUnit unit, Scheduler scheduler, int bufferSize, boolean delayError) {
            this.actual = actual;
            this.count = count;
            this.time = time;
            this.unit = unit;
            this.scheduler = scheduler;
            this.queue = new SpscLinkedArrayQueue(bufferSize);
            this.delayError = delayError;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2594s, s)) {
                this.f2594s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            SpscLinkedArrayQueue<Object> q = this.queue;
            long now = this.scheduler.now(this.unit);
            q.offer(Long.valueOf(now), t);
            trim(now, q);
        }

        public void onError(Throwable t) {
            if (this.delayError) {
                trim(this.scheduler.now(this.unit), this.queue);
            }
            this.error = t;
            this.done = true;
            drain();
        }

        public void onComplete() {
            trim(this.scheduler.now(this.unit), this.queue);
            this.done = true;
            drain();
        }

        void trim(long now, SpscLinkedArrayQueue<Object> q) {
            long time = this.time;
            long c = this.count;
            boolean unbounded = c == Long.MAX_VALUE;
            while (!q.isEmpty()) {
                if (((Long) q.peek()).longValue() < now - time || (!unbounded && ((long) (q.size() >> 1)) > c)) {
                    q.poll();
                    q.poll();
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

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2594s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                Subscriber<? super T> a = this.actual;
                SpscLinkedArrayQueue<Object> q = this.queue;
                boolean delayError = this.delayError;
                do {
                    if (this.done) {
                        if (!checkTerminated(q.isEmpty(), a, delayError)) {
                            long r = this.requested.get();
                            long e = 0;
                            while (true) {
                                if (!checkTerminated(q.peek() == null, a, delayError)) {
                                    if (r == e) {
                                        break;
                                    }
                                    q.poll();
                                    a.onNext(q.poll());
                                    e++;
                                } else {
                                    return;
                                }
                            }
                            if (e != 0) {
                                BackpressureHelper.produced(this.requested, e);
                            }
                        } else {
                            return;
                        }
                    }
                    missed = addAndGet(-missed);
                } while (missed != 0);
            }
        }

        boolean checkTerminated(boolean empty, Subscriber<? super T> a, boolean delayError) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            Throwable e;
            if (!delayError) {
                e = this.error;
                if (e != null) {
                    this.queue.clear();
                    a.onError(e);
                    return true;
                } else if (empty) {
                    a.onComplete();
                    return true;
                }
            } else if (empty) {
                e = this.error;
                if (e != null) {
                    a.onError(e);
                    return true;
                }
                a.onComplete();
                return true;
            }
            return false;
        }
    }

    public FlowableTakeLastTimed(Flowable<T> source, long count, long time, TimeUnit unit, Scheduler scheduler, int bufferSize, boolean delayError) {
        super(source);
        this.count = count;
        this.time = time;
        this.unit = unit;
        this.scheduler = scheduler;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new TakeLastTimedSubscriber(s, this.count, this.time, this.unit, this.scheduler, this.bufferSize, this.delayError));
    }
}
