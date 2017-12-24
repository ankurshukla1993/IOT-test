package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableSkipWhile<T> extends AbstractFlowableWithUpstream<T, T> {
    final Predicate<? super T> predicate;

    static final class SkipWhileSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        boolean notSkipping;
        final Predicate<? super T> predicate;
        Subscription f2587s;

        SkipWhileSubscriber(Subscriber<? super T> actual, Predicate<? super T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2587s, s)) {
                this.f2587s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (this.notSkipping) {
                this.actual.onNext(t);
                return;
            }
            try {
                if (this.predicate.test(t)) {
                    this.f2587s.request(1);
                    return;
                }
                this.notSkipping = true;
                this.actual.onNext(t);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                this.f2587s.cancel();
                this.actual.onError(e);
            }
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void request(long n) {
            this.f2587s.request(n);
        }

        public void cancel() {
            this.f2587s.cancel();
        }
    }

    public FlowableSkipWhile(Flowable<T> source, Predicate<? super T> predicate) {
        super(source);
        this.predicate = predicate;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new SkipWhileSubscriber(s, this.predicate));
    }
}
