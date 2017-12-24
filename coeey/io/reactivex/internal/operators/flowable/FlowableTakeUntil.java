package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.internal.util.HalfSerializer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableTakeUntil<T, U> extends AbstractFlowableWithUpstream<T, T> {
    final Publisher<? extends U> other;

    static final class TakeUntilMainSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -4945480365982832967L;
        final Subscriber<? super T> actual;
        final AtomicThrowable error = new AtomicThrowable();
        final OtherSubscriber other = new OtherSubscriber();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Subscription> f2595s = new AtomicReference();

        final class OtherSubscriber extends AtomicReference<Subscription> implements FlowableSubscriber<Object> {
            private static final long serialVersionUID = -3592821756711087922L;

            OtherSubscriber() {
            }

            public void onSubscribe(Subscription s) {
                if (SubscriptionHelper.setOnce(this, s)) {
                    s.request(Long.MAX_VALUE);
                }
            }

            public void onNext(Object t) {
                SubscriptionHelper.cancel(this);
                onComplete();
            }

            public void onError(Throwable t) {
                SubscriptionHelper.cancel(TakeUntilMainSubscriber.this.f2595s);
                HalfSerializer.onError(TakeUntilMainSubscriber.this.actual, t, TakeUntilMainSubscriber.this, TakeUntilMainSubscriber.this.error);
            }

            public void onComplete() {
                SubscriptionHelper.cancel(TakeUntilMainSubscriber.this.f2595s);
                HalfSerializer.onComplete(TakeUntilMainSubscriber.this.actual, TakeUntilMainSubscriber.this, TakeUntilMainSubscriber.this.error);
            }
        }

        TakeUntilMainSubscriber(Subscriber<? super T> actual) {
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            SubscriptionHelper.deferredSetOnce(this.f2595s, this.requested, s);
        }

        public void onNext(T t) {
            HalfSerializer.onNext(this.actual, (Object) t, (AtomicInteger) this, this.error);
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
            SubscriptionHelper.deferredRequest(this.f2595s, this.requested, n);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.f2595s);
            SubscriptionHelper.cancel(this.other);
        }
    }

    public FlowableTakeUntil(Flowable<T> source, Publisher<? extends U> other) {
        super(source);
        this.other = other;
    }

    protected void subscribeActual(Subscriber<? super T> child) {
        TakeUntilMainSubscriber<T> parent = new TakeUntilMainSubscriber(child);
        child.onSubscribe(parent);
        this.other.subscribe(parent.other);
        this.source.subscribe(parent);
    }
}
