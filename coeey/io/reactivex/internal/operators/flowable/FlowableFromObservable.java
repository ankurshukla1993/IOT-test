package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableFromObservable<T> extends Flowable<T> {
    private final Observable<T> upstream;

    static class SubscriberObserver<T> implements Observer<T>, Subscription {
        private Disposable f2553d;
        private final Subscriber<? super T> f2554s;

        SubscriberObserver(Subscriber<? super T> s) {
            this.f2554s = s;
        }

        public void onComplete() {
            this.f2554s.onComplete();
        }

        public void onError(Throwable e) {
            this.f2554s.onError(e);
        }

        public void onNext(T value) {
            this.f2554s.onNext(value);
        }

        public void onSubscribe(Disposable d) {
            this.f2553d = d;
            this.f2554s.onSubscribe(this);
        }

        public void cancel() {
            this.f2553d.dispose();
        }

        public void request(long n) {
        }
    }

    public FlowableFromObservable(Observable<T> upstream) {
        this.upstream = upstream;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.upstream.subscribe(new SubscriberObserver(s));
    }
}
