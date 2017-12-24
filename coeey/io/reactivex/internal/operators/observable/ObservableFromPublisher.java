package io.reactivex.internal.operators.observable;

import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class ObservableFromPublisher<T> extends Observable<T> {
    final Publisher<? extends T> source;

    static final class PublisherSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final Observer<? super T> actual;
        Subscription f2698s;

        PublisherSubscriber(Observer<? super T> o) {
            this.actual = o;
        }

        public void onComplete() {
            this.actual.onComplete();
        }

        public void onError(Throwable t) {
            this.actual.onError(t);
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2698s, s)) {
                this.f2698s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void dispose() {
            this.f2698s.cancel();
            this.f2698s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2698s == SubscriptionHelper.CANCELLED;
        }
    }

    public ObservableFromPublisher(Publisher<? extends T> publisher) {
        this.source = publisher;
    }

    protected void subscribeActual(Observer<? super T> o) {
        this.source.subscribe(new PublisherSubscriber(o));
    }
}
