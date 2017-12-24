package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableSubscribeOn<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean nonScheduledRequests;
    final Scheduler scheduler;

    static final class SubscribeOnSubscriber<T> extends AtomicReference<Thread> implements FlowableSubscriber<T>, Subscription, Runnable {
        private static final long serialVersionUID = 8094547886072529208L;
        final Subscriber<? super T> actual;
        final boolean nonScheduledRequests;
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Subscription> f2590s = new AtomicReference();
        Publisher<T> source;
        final Worker worker;

        static final class Request implements Runnable {
            private final long f2588n;
            private final Subscription f2589s;

            Request(Subscription s, long n) {
                this.f2589s = s;
                this.f2588n = n;
            }

            public void run() {
                this.f2589s.request(this.f2588n);
            }
        }

        SubscribeOnSubscriber(Subscriber<? super T> actual, Worker worker, Publisher<T> source, boolean requestOn) {
            this.actual = actual;
            this.worker = worker;
            this.source = source;
            this.nonScheduledRequests = !requestOn;
        }

        public void run() {
            lazySet(Thread.currentThread());
            Publisher<T> src = this.source;
            this.source = null;
            src.subscribe(this);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this.f2590s, s)) {
                long r = this.requested.getAndSet(0);
                if (r != 0) {
                    requestUpstream(r, s);
                }
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
            this.worker.dispose();
        }

        public void onComplete() {
            this.actual.onComplete();
            this.worker.dispose();
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                Subscription s = (Subscription) this.f2590s.get();
                if (s != null) {
                    requestUpstream(n, s);
                    return;
                }
                BackpressureHelper.add(this.requested, n);
                s = (Subscription) this.f2590s.get();
                if (s != null) {
                    long r = this.requested.getAndSet(0);
                    if (r != 0) {
                        requestUpstream(r, s);
                    }
                }
            }
        }

        void requestUpstream(long n, Subscription s) {
            if (this.nonScheduledRequests || Thread.currentThread() == get()) {
                s.request(n);
            } else {
                this.worker.schedule(new Request(s, n));
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.f2590s);
            this.worker.dispose();
        }
    }

    public FlowableSubscribeOn(Flowable<T> source, Scheduler scheduler, boolean nonScheduledRequests) {
        super(source);
        this.scheduler = scheduler;
        this.nonScheduledRequests = nonScheduledRequests;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        Worker w = this.scheduler.createWorker();
        SubscribeOnSubscriber<T> sos = new SubscribeOnSubscriber(s, w, this.source, this.nonScheduledRequests);
        s.onSubscribe(sos);
        w.schedule(sos);
    }
}
