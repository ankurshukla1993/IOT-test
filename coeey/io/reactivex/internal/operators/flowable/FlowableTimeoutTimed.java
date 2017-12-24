package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.Scheduler.Worker;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscribers.FullArbiterSubscriber;
import io.reactivex.internal.subscriptions.FullArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableTimeoutTimed<T> extends AbstractFlowableWithUpstream<T, T> {
    static final Disposable NEW_TIMER = new EmptyDispose();
    final Publisher<? extends T> other;
    final Scheduler scheduler;
    final long timeout;
    final TimeUnit unit;

    static final class EmptyDispose implements Disposable {
        EmptyDispose() {
        }

        public void dispose() {
        }

        public boolean isDisposed() {
            return true;
        }
    }

    static final class TimeoutTimedOtherSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final Subscriber<? super T> actual;
        final FullArbiter<T> arbiter;
        volatile boolean done;
        volatile long index;
        final Publisher<? extends T> other;
        Subscription f2602s;
        final long timeout;
        Disposable timer;
        final TimeUnit unit;
        final Worker worker;

        final class TimeoutTask implements Runnable {
            private final long idx;

            TimeoutTask(long idx) {
                this.idx = idx;
            }

            public void run() {
                if (this.idx == TimeoutTimedOtherSubscriber.this.index) {
                    TimeoutTimedOtherSubscriber.this.done = true;
                    TimeoutTimedOtherSubscriber.this.f2602s.cancel();
                    TimeoutTimedOtherSubscriber.this.worker.dispose();
                    TimeoutTimedOtherSubscriber.this.subscribeNext();
                }
            }
        }

        TimeoutTimedOtherSubscriber(Subscriber<? super T> actual, long timeout, TimeUnit unit, Worker worker, Publisher<? extends T> other) {
            this.actual = actual;
            this.timeout = timeout;
            this.unit = unit;
            this.worker = worker;
            this.other = other;
            this.arbiter = new FullArbiter(actual, this, 8);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2602s, s)) {
                this.f2602s = s;
                if (this.arbiter.setSubscription(s)) {
                    this.actual.onSubscribe(this.arbiter);
                    scheduleTimeout(0);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long idx = this.index + 1;
                this.index = idx;
                if (this.arbiter.onNext(t, this.f2602s)) {
                    scheduleTimeout(idx);
                }
            }
        }

        void scheduleTimeout(long idx) {
            if (this.timer != null) {
                this.timer.dispose();
            }
            this.timer = this.worker.schedule(new TimeoutTask(idx), this.timeout, this.unit);
        }

        void subscribeNext() {
            this.other.subscribe(new FullArbiterSubscriber(this.arbiter));
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.arbiter.onError(t, this.f2602s);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.arbiter.onComplete(this.f2602s);
                this.worker.dispose();
            }
        }

        public void dispose() {
            this.f2602s.cancel();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }
    }

    static final class TimeoutTimedSubscriber<T> implements FlowableSubscriber<T>, Disposable, Subscription {
        final Subscriber<? super T> actual;
        volatile boolean done;
        volatile long index;
        Subscription f2603s;
        final long timeout;
        Disposable timer;
        final TimeUnit unit;
        final Worker worker;

        final class TimeoutTask implements Runnable {
            private final long idx;

            TimeoutTask(long idx) {
                this.idx = idx;
            }

            public void run() {
                if (this.idx == TimeoutTimedSubscriber.this.index) {
                    TimeoutTimedSubscriber.this.done = true;
                    TimeoutTimedSubscriber.this.dispose();
                    TimeoutTimedSubscriber.this.actual.onError(new TimeoutException());
                }
            }
        }

        TimeoutTimedSubscriber(Subscriber<? super T> actual, long timeout, TimeUnit unit, Worker worker) {
            this.actual = actual;
            this.timeout = timeout;
            this.unit = unit;
            this.worker = worker;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2603s, s)) {
                this.f2603s = s;
                this.actual.onSubscribe(this);
                scheduleTimeout(0);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long idx = this.index + 1;
                this.index = idx;
                this.actual.onNext(t);
                scheduleTimeout(idx);
            }
        }

        void scheduleTimeout(long idx) {
            if (this.timer != null) {
                this.timer.dispose();
            }
            this.timer = this.worker.schedule(new TimeoutTask(idx), this.timeout, this.unit);
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
            this.worker.dispose();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
                this.worker.dispose();
            }
        }

        public void dispose() {
            this.f2603s.cancel();
            this.worker.dispose();
        }

        public boolean isDisposed() {
            return this.worker.isDisposed();
        }

        public void request(long n) {
            this.f2603s.request(n);
        }

        public void cancel() {
            dispose();
        }
    }

    public FlowableTimeoutTimed(Flowable<T> source, long timeout, TimeUnit unit, Scheduler scheduler, Publisher<? extends T> other) {
        super(source);
        this.timeout = timeout;
        this.unit = unit;
        this.scheduler = scheduler;
        this.other = other;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        if (this.other == null) {
            this.source.subscribe(new TimeoutTimedSubscriber(new SerializedSubscriber(s), this.timeout, this.unit, this.scheduler.createWorker()));
            return;
        }
        this.source.subscribe(new TimeoutTimedOtherSubscriber(s, this.timeout, this.unit, this.scheduler.createWorker(), this.other));
    }
}
