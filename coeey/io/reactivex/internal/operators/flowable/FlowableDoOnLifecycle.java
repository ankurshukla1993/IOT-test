package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.LongConsumer;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableDoOnLifecycle<T> extends AbstractFlowableWithUpstream<T, T> {
    private final Action onCancel;
    private final LongConsumer onRequest;
    private final Consumer<? super Subscription> onSubscribe;

    static final class SubscriptionLambdaSubscriber<T> implements FlowableSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        final Action onCancel;
        final LongConsumer onRequest;
        final Consumer<? super Subscription> onSubscribe;
        Subscription f2543s;

        SubscriptionLambdaSubscriber(Subscriber<? super T> actual, Consumer<? super Subscription> onSubscribe, LongConsumer onRequest, Action onCancel) {
            this.actual = actual;
            this.onSubscribe = onSubscribe;
            this.onCancel = onCancel;
            this.onRequest = onRequest;
        }

        public void onSubscribe(Subscription s) {
            try {
                this.onSubscribe.accept(s);
                if (SubscriptionHelper.validate(this.f2543s, s)) {
                    this.f2543s = s;
                    this.actual.onSubscribe(this);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                s.cancel();
                this.f2543s = SubscriptionHelper.CANCELLED;
                EmptySubscription.error(e, this.actual);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            if (this.f2543s != SubscriptionHelper.CANCELLED) {
                this.actual.onError(t);
            } else {
                RxJavaPlugins.onError(t);
            }
        }

        public void onComplete() {
            if (this.f2543s != SubscriptionHelper.CANCELLED) {
                this.actual.onComplete();
            }
        }

        public void request(long n) {
            try {
                this.onRequest.accept(n);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.onError(e);
            }
            this.f2543s.request(n);
        }

        public void cancel() {
            try {
                this.onCancel.run();
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.onError(e);
            }
            this.f2543s.cancel();
        }
    }

    public FlowableDoOnLifecycle(Flowable<T> source, Consumer<? super Subscription> onSubscribe, LongConsumer onRequest, Action onCancel) {
        super(source);
        this.onSubscribe = onSubscribe;
        this.onRequest = onRequest;
        this.onCancel = onCancel;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new SubscriptionLambdaSubscriber(s, this.onSubscribe, this.onRequest, this.onCancel));
    }
}
