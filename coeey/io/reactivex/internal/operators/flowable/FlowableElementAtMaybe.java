package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscription;

public final class FlowableElementAtMaybe<T> extends Maybe<T> implements FuseToFlowable<T> {
    final long index;
    final Flowable<T> source;

    static final class ElementAtSubscriber<T> implements FlowableSubscriber<T>, Disposable {
        final MaybeObserver<? super T> actual;
        long count;
        boolean done;
        final long index;
        Subscription f2545s;

        ElementAtSubscriber(MaybeObserver<? super T> actual, long index) {
            this.actual = actual;
            this.index = index;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2545s, s)) {
                this.f2545s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long c = this.count;
                if (c == this.index) {
                    this.done = true;
                    this.f2545s.cancel();
                    this.f2545s = SubscriptionHelper.CANCELLED;
                    this.actual.onSuccess(t);
                    return;
                }
                this.count = 1 + c;
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.f2545s = SubscriptionHelper.CANCELLED;
            this.actual.onError(t);
        }

        public void onComplete() {
            this.f2545s = SubscriptionHelper.CANCELLED;
            if (!this.done) {
                this.done = true;
                this.actual.onComplete();
            }
        }

        public void dispose() {
            this.f2545s.cancel();
            this.f2545s = SubscriptionHelper.CANCELLED;
        }

        public boolean isDisposed() {
            return this.f2545s == SubscriptionHelper.CANCELLED;
        }
    }

    public FlowableElementAtMaybe(Flowable<T> source, long index) {
        this.source = source;
        this.index = index;
    }

    protected void subscribeActual(MaybeObserver<? super T> s) {
        this.source.subscribe(new ElementAtSubscriber(s, this.index));
    }

    public Flowable<T> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableElementAt(this.source, this.index, null, false));
    }
}
