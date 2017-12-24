package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableRetryBiPredicate<T> extends AbstractFlowableWithUpstream<T, T> {
    final BiPredicate<? super Integer, ? super Throwable> predicate;

    static final class RetryBiSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -7098360935104053232L;
        final Subscriber<? super T> actual;
        final BiPredicate<? super Integer, ? super Throwable> predicate;
        int retries;
        final SubscriptionArbiter sa;
        final Publisher<? extends T> source;

        RetryBiSubscriber(Subscriber<? super T> actual, BiPredicate<? super Integer, ? super Throwable> predicate, SubscriptionArbiter sa, Publisher<? extends T> source) {
            this.actual = actual;
            this.sa = sa;
            this.source = source;
            this.predicate = predicate;
        }

        public void onSubscribe(Subscription s) {
            this.sa.setSubscription(s);
        }

        public void onNext(T t) {
            this.actual.onNext(t);
            this.sa.produced(1);
        }

        public void onError(Throwable t) {
            try {
                BiPredicate biPredicate = this.predicate;
                int i = this.retries + 1;
                this.retries = i;
                if (biPredicate.test(Integer.valueOf(i), t)) {
                    subscribeNext();
                } else {
                    this.actual.onError(t);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.actual.onError(new CompositeException(t, e));
            }
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        void subscribeNext() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                while (!this.sa.isCancelled()) {
                    this.source.subscribe(this);
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }
    }

    public FlowableRetryBiPredicate(Flowable<T> source, BiPredicate<? super Integer, ? super Throwable> predicate) {
        super(source);
        this.predicate = predicate;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        SubscriptionArbiter sa = new SubscriptionArbiter();
        s.onSubscribe(sa);
        new RetryBiSubscriber(s, this.predicate, sa, this.source).subscribeNext();
    }
}
