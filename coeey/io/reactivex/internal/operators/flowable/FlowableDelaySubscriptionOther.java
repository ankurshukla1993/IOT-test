package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableDelaySubscriptionOther<T, U> extends Flowable<T> {
    final Publisher<? extends T> main;
    final Publisher<U> other;

    final class DelaySubscriber implements FlowableSubscriber<U> {
        final Subscriber<? super T> child;
        boolean done;
        final SubscriptionArbiter serial;

        final class DelaySubscription implements Subscription {
            private final Subscription f2536s;

            DelaySubscription(Subscription s) {
                this.f2536s = s;
            }

            public void request(long n) {
            }

            public void cancel() {
                this.f2536s.cancel();
            }
        }

        final class OnCompleteSubscriber implements FlowableSubscriber<T> {
            OnCompleteSubscriber() {
            }

            public void onSubscribe(Subscription s) {
                DelaySubscriber.this.serial.setSubscription(s);
            }

            public void onNext(T t) {
                DelaySubscriber.this.child.onNext(t);
            }

            public void onError(Throwable t) {
                DelaySubscriber.this.child.onError(t);
            }

            public void onComplete() {
                DelaySubscriber.this.child.onComplete();
            }
        }

        DelaySubscriber(SubscriptionArbiter serial, Subscriber<? super T> child) {
            this.serial = serial;
            this.child = child;
        }

        public void onSubscribe(Subscription s) {
            this.serial.setSubscription(new DelaySubscription(s));
            s.request(Long.MAX_VALUE);
        }

        public void onNext(U u) {
            onComplete();
        }

        public void onError(Throwable e) {
            if (this.done) {
                RxJavaPlugins.onError(e);
                return;
            }
            this.done = true;
            this.child.onError(e);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                FlowableDelaySubscriptionOther.this.main.subscribe(new OnCompleteSubscriber());
            }
        }
    }

    public FlowableDelaySubscriptionOther(Publisher<? extends T> main, Publisher<U> other) {
        this.main = main;
        this.other = other;
    }

    public void subscribeActual(Subscriber<? super T> child) {
        SubscriptionArbiter serial = new SubscriptionArbiter();
        child.onSubscribe(serial);
        this.other.subscribe(new DelaySubscriber(serial, child));
    }
}
