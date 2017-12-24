package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableOnBackpressureLatest<T> extends AbstractFlowableWithUpstream<T, T> {

    static final class BackpressureLatestSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 163080509307634843L;
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final AtomicReference<T> current = new AtomicReference();
        volatile boolean done;
        Throwable error;
        final AtomicLong requested = new AtomicLong();
        Subscription f2568s;

        BackpressureLatestSubscriber(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2568s, s)) {
                this.f2568s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.current.lazySet(t);
            drain();
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

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2568s.cancel();
                if (getAndIncrement() == 0) {
                    this.current.lazySet(null);
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                Subscriber<? super T> a = this.actual;
                int missed = 1;
                AtomicLong r = this.requested;
                AtomicReference<T> q = this.current;
                do {
                    long e = 0;
                    while (e != r.get()) {
                        boolean empty;
                        boolean d = this.done;
                        T v = q.getAndSet(null);
                        if (v == null) {
                            empty = true;
                        } else {
                            empty = false;
                        }
                        if (!checkTerminated(d, empty, a, q)) {
                            if (empty) {
                                break;
                            }
                            a.onNext(v);
                            e++;
                        } else {
                            return;
                        }
                    }
                    if (e == r.get()) {
                        boolean z;
                        boolean z2 = this.done;
                        if (q.get() == null) {
                            z = true;
                        } else {
                            z = false;
                        }
                        if (checkTerminated(z2, z, a, q)) {
                            return;
                        }
                    }
                    if (e != 0) {
                        BackpressureHelper.produced(r, e);
                    }
                    missed = addAndGet(-missed);
                } while (missed != 0);
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, AtomicReference<T> q) {
            if (this.cancelled) {
                q.lazySet(null);
                return true;
            }
            if (d) {
                Throwable e = this.error;
                if (e != null) {
                    q.lazySet(null);
                    a.onError(e);
                    return true;
                } else if (empty) {
                    a.onComplete();
                    return true;
                }
            }
            return false;
        }
    }

    public FlowableOnBackpressureLatest(Flowable<T> source) {
        super(source);
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new BackpressureLatestSubscriber(s));
    }
}
