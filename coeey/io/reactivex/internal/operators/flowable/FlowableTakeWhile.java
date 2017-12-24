package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Predicate;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableTakeWhile<T> extends AbstractFlowableWithUpstream<T, T> {
    final Predicate<? super T> predicate;

    static final class TakeWhileSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        boolean done;
        final Predicate<? super T> predicate;
        Subscription f2597s;

        TakeWhileSubscriber(Subscriber<? super T> actual, Predicate<? super T> predicate) {
            this.actual = actual;
            this.predicate = predicate;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2597s, s)) {
                this.f2597s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    if (this.predicate.test(t)) {
                        this.actual.onNext(t);
                        return;
                    }
                    this.done = true;
                    this.f2597s.cancel();
                    this.actual.onComplete();
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2597s.cancel();
                    onError(e);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }

        public void request(long n) {
            this.f2597s.request(n);
        }

        public void cancel() {
            this.f2597s.cancel();
        }
    }

    public FlowableTakeWhile(Flowable<T> source, Predicate<? super T> predicate) {
        super(source);
        this.predicate = predicate;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new TakeWhileSubscriber(s, this.predicate));
    }
}
