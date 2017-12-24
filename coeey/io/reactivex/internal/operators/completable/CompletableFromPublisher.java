package io.reactivex.internal.operators.completable;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class CompletableFromPublisher<T> extends Completable {
    final Publisher<T> flowable;

    static final class FromPublisherSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final CompletableObserver cs;
        Subscription f2496s;

        FromPublisherSubscriber(CompletableObserver actual) {
            this.cs = actual;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2496s, s)) {
                this.f2496s = s;
                this.cs.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
        }

        public void onError(Throwable t) {
            this.cs.onError(t);
        }

        public void onComplete() {
            this.cs.onComplete();
        }

        public void dispose() {
            this.f2496s.cancel();
            this.f2496s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2496s == SubscriptionHelper.CANCELLED;
        }
    }

    public CompletableFromPublisher(Publisher<T> flowable) {
        this.flowable = flowable;
    }

    protected void subscribeActual(CompletableObserver cs) {
        this.flowable.subscribe(new FromPublisherSubscriber(cs));
    }
}
