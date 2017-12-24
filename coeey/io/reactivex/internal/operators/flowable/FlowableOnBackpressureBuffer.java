package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Action;
import io.reactivex.internal.fuseable.SimplePlainQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableOnBackpressureBuffer<T> extends AbstractFlowableWithUpstream<T, T> {
    final int bufferSize;
    final boolean delayError;
    final Action onOverflow;
    final boolean unbounded;

    static final class BackpressureBufferSubscriber<T> extends BasicIntQueueSubscription<T> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -2514538129242366402L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final Action onOverflow;
        boolean outputFused;
        final SimplePlainQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription f2564s;

        BackpressureBufferSubscriber(Subscriber<? super T> actual, int bufferSize, boolean unbounded, boolean delayError, Action onOverflow) {
            SimplePlainQueue<T> q;
            this.actual = actual;
            this.onOverflow = onOverflow;
            this.delayError = delayError;
            if (unbounded) {
                q = new SpscLinkedArrayQueue(bufferSize);
            } else {
                q = new SpscArrayQueue(bufferSize);
            }
            this.queue = q;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2564s, s)) {
                this.f2564s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.queue.offer(t)) {
                this.f2564s.cancel();
                MissingBackpressureException ex = new MissingBackpressureException("Buffer is full");
                try {
                    this.onOverflow.run();
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    ex.initCause(e);
                }
                onError(ex);
            } else if (this.outputFused) {
                this.actual.onNext(null);
            } else {
                drain();
            }
        }

        public void onError(Throwable t) {
            this.error = t;
            this.done = true;
            if (this.outputFused) {
                this.actual.onError(t);
            } else {
                drain();
            }
        }

        public void onComplete() {
            this.done = true;
            if (this.outputFused) {
                this.actual.onComplete();
            } else {
                drain();
            }
        }

        public void request(long n) {
            if (!this.outputFused && SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2564s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                SimplePlainQueue<T> q = this.queue;
                Subscriber<? super T> a = this.actual;
                while (!checkTerminated(this.done, q.isEmpty(), a)) {
                    long r = this.requested.get();
                    long e = 0;
                    while (e != r) {
                        boolean d = this.done;
                        T v = q.poll();
                        boolean empty = v == null;
                        if (!checkTerminated(d, empty, a)) {
                            if (empty) {
                                break;
                            }
                            a.onNext(v);
                            e++;
                        } else {
                            return;
                        }
                    }
                    if (e != r || !checkTerminated(this.done, q.isEmpty(), a)) {
                        if (!(e == 0 || r == Long.MAX_VALUE)) {
                            this.requested.addAndGet(-e);
                        }
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    }
                    return;
                }
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<? super T> a) {
            if (this.cancelled) {
                this.queue.clear();
                return true;
            }
            if (d) {
                Throwable e;
                if (!this.delayError) {
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
            }
            return false;
        }

        public int requestFusion(int mode) {
            if ((mode & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Nullable
        public T poll() throws Exception {
            return this.queue.poll();
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    public FlowableOnBackpressureBuffer(Flowable<T> source, int bufferSize, boolean unbounded, boolean delayError, Action onOverflow) {
        super(source);
        this.bufferSize = bufferSize;
        this.unbounded = unbounded;
        this.delayError = delayError;
        this.onOverflow = onOverflow;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new BackpressureBufferSubscriber(s, this.bufferSize, this.unbounded, this.delayError, this.onOverflow));
    }
}
