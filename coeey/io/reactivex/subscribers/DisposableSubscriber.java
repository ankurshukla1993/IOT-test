package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.EndConsumerHelper;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscription;

public abstract class DisposableSubscriber<T> implements FlowableSubscriber<T>, Disposable {
    final AtomicReference<Subscription> f2505s = new AtomicReference();

    public final void onSubscribe(Subscription s) {
        if (EndConsumerHelper.setOnce(this.f2505s, s, getClass())) {
            onStart();
        }
    }

    protected void onStart() {
        ((Subscription) this.f2505s.get()).request(Long.MAX_VALUE);
    }

    protected final void request(long n) {
        ((Subscription) this.f2505s.get()).request(n);
    }

    protected final void cancel() {
        dispose();
    }

    public final boolean isDisposed() {
        return this.f2505s.get() == SubscriptionHelper.CANCELLED;
    }

    public final void dispose() {
        SubscriptionHelper.cancel(this.f2505s);
    }
}
