package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableDelay<T> extends AbstractFlowableWithUpstream<T, T> {
    final long delay;
    final boolean delayError;
    final Scheduler scheduler;
    final TimeUnit unit;

    static final class DelaySubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        final long delay;
        final boolean delayError;
        Subscription f2534s;
        final TimeUnit unit;
        final Worker f2535w;

        final class OnComplete implements Runnable {
            OnComplete() {
            }

            public void run() {
                try {
                    DelaySubscriber.this.actual.onComplete();
                } finally {
                    DelaySubscriber.this.f2535w.dispose();
                }
            }
        }

        final class OnError implements Runnable {
            private final Throwable f2532t;

            OnError(Throwable t) {
                this.f2532t = t;
            }

            public void run() {
                try {
                    DelaySubscriber.this.actual.onError(this.f2532t);
                } finally {
                    DelaySubscriber.this.f2535w.dispose();
                }
            }
        }

        final class OnNext implements Runnable {
            private final T f2533t;

            OnNext(T t) {
                this.f2533t = t;
            }

            public void run() {
                DelaySubscriber.this.actual.onNext(this.f2533t);
            }
        }

        DelaySubscriber(Subscriber<? super T> actual, long delay, TimeUnit unit, Worker w, boolean delayError) {
            this.actual = actual;
            this.delay = delay;
            this.unit = unit;
            this.f2535w = w;
            this.delayError = delayError;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2534s, s)) {
                this.f2534s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.f2535w.schedule(new OnNext(t), this.delay, this.unit);
        }

        public void onError(Throwable t) {
            this.f2535w.schedule(new OnError(t), this.delayError ? this.delay : 0, this.unit);
        }

        public void onComplete() {
            this.f2535w.schedule(new OnComplete(), this.delay, this.unit);
        }

        public void request(long n) {
            this.f2534s.request(n);
        }

        public void cancel() {
            this.f2534s.cancel();
            this.f2535w.dispose();
        }
    }

    public FlowableDelay(Flowable<T> source, long delay, TimeUnit unit, Scheduler scheduler, boolean delayError) {
        super(source);
        this.delay = delay;
        this.unit = unit;
        this.scheduler = scheduler;
        this.delayError = delayError;
    }

    protected void subscribeActual(Subscriber<? super T> t) {
        Subscriber<? super T> s;
        if (this.delayError) {
            s = t;
        } else {
            s = new SerializedSubscriber(t);
        }
        this.source.subscribe(new DelaySubscriber(s, this.delay, this.unit, this.scheduler.createWorker(), this.delayError));
    }
}
