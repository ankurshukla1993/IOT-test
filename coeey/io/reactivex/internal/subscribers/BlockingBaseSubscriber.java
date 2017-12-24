package io.reactivex.internal.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.CountDownLatch;
import org.reactivestreams.Subscription;

public abstract class BlockingBaseSubscriber<T> extends CountDownLatch implements FlowableSubscriber<T> {
    volatile boolean cancelled;
    Throwable error;
    Subscription f2802s;
    T value;

    public BlockingBaseSubscriber() {
        super(1);
    }

    public final void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.f2802s, s)) {
            this.f2802s = s;
            if (!this.cancelled) {
                s.request(Long.MAX_VALUE);
                if (this.cancelled) {
                    this.f2802s = SubscriptionHelper.CANCELLED;
                    s.cancel();
                }
            }
        }
    }

    public final void onComplete() {
        countDown();
    }

    public final T blockingGet() {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (InterruptedException ex) {
                Subscription s = this.f2802s;
                this.f2802s = SubscriptionHelper.CANCELLED;
                if (s != null) {
                    s.cancel();
                }
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        Throwable e = this.error;
        if (e == null) {
            return this.value;
        }
        throw ExceptionHelper.wrapOrThrow(e);
    }
}
