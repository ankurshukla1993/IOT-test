package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableSkipUntil<T, U> extends AbstractFlowableWithUpstream<T, T> {
    final Publisher<U> other;

    static final class SkipUntilMainSubscriber<T> extends AtomicInteger implements ConditionalSubscriber<T>, Subscription {
        private static final long serialVersionUID = -6270983465606289181L;
        final Subscriber<? super T> actual;
        final AtomicThrowable error = new AtomicThrowable();
        volatile boolean gate;
        final OtherSubscriber other = new OtherSubscriber();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Subscription> f2586s = new AtomicReference();

        final class OtherSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
            private static final long serialVersionUID = -5592042965931999169L;

            OtherSubscriber() {
            }

            public void onSubscribe(Subscription s) {
                if (SubscriptionHelper.setOnce(this, s)) {
                    s.request(Long.MAX_VALUE);
                }
            }

            public void onNext(Object t) {
                SkipUntilMainSubscriber.this.gate = true;
                ((Subscription) get()).cancel();
            }

            public void onError(Throwable t) {
                SubscriptionHelper.cancel(SkipUntilMainSubscriber.this.f2586s);
                HalfSerializer.onError(SkipUntilMainSubscriber.this.actual, t, SkipUntilMainSubscriber.this, SkipUntilMainSubscriber.this.error);
            }

            public void onComplete() {
                SkipUntilMainSubscriber.this.gate = true;
            }
        }

        SkipUntilMainSubscriber(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            SubscriptionHelper.deferredSetOnce(this.f2586s, this.requested, s);
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                ((Subscription) this.f2586s.get()).request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (!this.gate) {
                return false;
            }
            HalfSerializer.onNext(this.actual, (Object) t, (AtomicInteger) this, this.error);
            return true;
        }

        public void onError(Throwable t) {
            SubscriptionHelper.cancel(this.other);
            HalfSerializer.onError(this.actual, t, (AtomicInteger) this, this.error);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            HalfSerializer.onComplete(this.actual, (AtomicInteger) this, this.error);
        }

        public void request(long n) {
            SubscriptionHelper.deferredRequest(this.f2586s, this.requested, n);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.f2586s);
            SubscriptionHelper.cancel(this.other);
        }
    }

    public FlowableSkipUntil(Flowable<T> source, Publisher<U> other) {
        super(source);
        this.other = other;
    }

    protected void subscribeActual(Subscriber<? super T> child) {
        SkipUntilMainSubscriber<T> parent = new SkipUntilMainSubscriber(child);
        child.onSubscribe(parent);
        this.other.subscribe(parent.other);
        this.source.subscribe(parent);
    }
}
