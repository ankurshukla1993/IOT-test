package io.reactivex.internal.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class SubscriberCompletableObserver<T> implements CompletableObserver, Subscription {
    Disposable f2488d;
    final Subscriber<? super T> subscriber;

    public SubscriberCompletableObserver(Subscriber<? super T> observer) {
        this.subscriber = observer;
    }

    public void onComplete() {
        this.subscriber.onComplete();
    }

    public void onError(Throwable e) {
        this.subscriber.onError(e);
    }

    public void onSubscribe(Disposable d) {
        if (DisposableHelper.validate(this.f2488d, d)) {
            this.f2488d = d;
            this.subscriber.onSubscribe(this);
        }
    }

    public void request(long n) {
    }

    public void cancel() {
        this.f2488d.dispose();
    }
}
