package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.schedulers.Timed;
import java.util.concurrent.TimeUnit;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableTimeInterval<T> extends AbstractFlowableWithUpstream<T, Timed<T>> {
    final Scheduler scheduler;
    final TimeUnit unit;

    static final class TimeIntervalSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super Timed<T>> actual;
        long lastTime;
        Subscription f2599s;
        final Scheduler scheduler;
        final TimeUnit unit;

        TimeIntervalSubscriber(Subscriber<? super Timed<T>> actual, TimeUnit unit, Scheduler scheduler) {
            this.actual = actual;
            this.scheduler = scheduler;
            this.unit = unit;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2599s, s)) {
                this.lastTime = this.scheduler.now(this.unit);
                this.f2599s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            long now = this.scheduler.now(this.unit);
            long last = this.lastTime;
            this.lastTime = now;
            this.actual.onNext(new Timed(t, now - last, this.unit));
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void request(long n) {
            this.f2599s.request(n);
        }

        public void cancel() {
            this.f2599s.cancel();
        }
    }

    public FlowableTimeInterval(Flowable<T> source, TimeUnit unit, Scheduler scheduler) {
        super(source);
        this.scheduler = scheduler;
        this.unit = unit;
    }

    protected void subscribeActual(Subscriber<? super Timed<T>> s) {
        this.source.subscribe(new TimeIntervalSubscriber(s, this.unit, this.scheduler));
    }
}
