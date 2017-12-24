package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiConsumer;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.DeferredScalarSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableCollect<T, U> extends AbstractFlowableWithUpstream<T, U> {
    final BiConsumer<? super U, ? super T> collector;
    final Callable<? extends U> initialSupplier;

    static final class CollectSubscriber<T, U> extends DeferredScalarSubscription<U> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -3589550218733891694L;
        final BiConsumer<? super U, ? super T> collector;
        boolean done;
        Subscription f2522s;
        final U f2523u;

        CollectSubscriber(Subscriber<? super U> actual, U u, BiConsumer<? super U, ? super T> collector) {
            super(actual);
            this.collector = collector;
            this.f2523u = u;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2522s, s)) {
                this.f2522s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                try {
                    this.collector.accept(this.f2523u, t);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    this.f2522s.cancel();
                    onError(e);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.actual.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                complete(this.f2523u);
            }
        }

        public void cancel() {
            super.cancel();
            this.f2522s.cancel();
        }
    }

    public FlowableCollect(Flowable<T> source, Callable<? extends U> initialSupplier, BiConsumer<? super U, ? super T> collector) {
        super(source);
        this.initialSupplier = initialSupplier;
        this.collector = collector;
    }

    protected void subscribeActual(Subscriber<? super U> s) {
        try {
            this.source.subscribe(new CollectSubscriber(s, ObjectHelper.requireNonNull(this.initialSupplier.call(), "The initial value supplied is null"), this.collector));
        } catch (Throwable e) {
            EmptySubscription.error(e, s);
        }
    }
}
