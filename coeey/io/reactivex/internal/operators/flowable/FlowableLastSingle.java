package io.reactivex.internal.operators.flowable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import java.util.NoSuchElementException;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class FlowableLastSingle<T> extends Single<T> {
    final T defaultItem;
    final Publisher<T> source;

    static final class LastSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final SingleObserver<? super T> actual;
        final T defaultItem;
        T item;
        Subscription f2561s;

        LastSubscriber(SingleObserver<? super T> actual, T defaultItem) {
            this.actual = actual;
            this.defaultItem = defaultItem;
        }

        public void dispose() {
            this.f2561s.cancel();
            this.f2561s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2561s == SubscriptionHelper.CANCELLED;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2561s, s)) {
                this.f2561s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            this.item = t;
        }

        public void onError(Throwable t) {
            this.f2561s = SubscriptionHelper.CANCELLED;
            this.item = null;
            this.actual.onError(t);
        }

        public void onComplete() {
            this.f2561s = SubscriptionHelper.CANCELLED;
            T v = this.item;
            if (v != null) {
                this.item = null;
                this.actual.onSuccess(v);
                return;
            }
            v = this.defaultItem;
            if (v != null) {
                this.actual.onSuccess(v);
            } else {
                this.actual.onError(new NoSuchElementException());
            }
        }
    }

    public FlowableLastSingle(Publisher<T> source, T defaultItem) {
        this.source = source;
        this.defaultItem = defaultItem;
    }

    protected void subscribeActual(SingleObserver<? super T> observer) {
        this.source.subscribe(new LastSubscriber(observer, this.defaultItem));
    }
}
