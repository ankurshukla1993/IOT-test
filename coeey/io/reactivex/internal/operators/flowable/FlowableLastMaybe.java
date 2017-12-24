package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class FlowableLastMaybe<T> extends Maybe<T> {
    final Publisher<T> source;

    static final class LastSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final MaybeObserver<? super T> actual;
        T item;
        Subscription f2560s;

        LastSubscriber(MaybeObserver<? super T> actual) {
            this.actual = actual;
        }

        public void dispose() {
            this.f2560s.cancel();
            this.f2560s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2560s == SubscriptionHelper.CANCELLED;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2560s, s)) {
                this.f2560s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.item = t;
        }

        public void onError(Throwable t) {
            this.f2560s = SubscriptionHelper.CANCELLED;
            this.item = null;
            this.actual.onError(t);
        }

        public void onComplete() {
            this.f2560s = SubscriptionHelper.CANCELLED;
            T v = this.item;
            if (v != null) {
                this.item = null;
                this.actual.onSuccess(v);
                return;
            }
            this.actual.onComplete();
        }
    }

    public FlowableLastMaybe(Publisher<T> source) {
        this.source = source;
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        this.source.subscribe(new LastSubscriber(observer));
    }
}
