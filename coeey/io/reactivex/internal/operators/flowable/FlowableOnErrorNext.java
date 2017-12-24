package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableOnErrorNext<T> extends AbstractFlowableWithUpstream<T, T> {
    final boolean allowFatal;
    final Function<? super Throwable, ? extends Publisher<? extends T>> nextSupplier;

    static final class OnErrorNextSubscriber<T> implements FlowableSubscriber<T> {
        final Subscriber<? super T> actual;
        final boolean allowFatal;
        final SubscriptionArbiter arbiter = new SubscriptionArbiter();
        boolean done;
        final Function<? super Throwable, ? extends Publisher<? extends T>> nextSupplier;
        boolean once;

        OnErrorNextSubscriber(Subscriber<? super T> actual, Function<? super Throwable, ? extends Publisher<? extends T>> nextSupplier, boolean allowFatal) {
            this.actual = actual;
            this.nextSupplier = nextSupplier;
            this.allowFatal = allowFatal;
        }

        public void onSubscribe(Subscription s) {
            this.arbiter.setSubscription(s);
        }

        public void onNext(T t) {
            if (!this.done) {
                this.actual.onNext(t);
                if (!this.once) {
                    this.arbiter.produced(1);
                }
            }
        }

        public void onError(Throwable t) {
            if (!this.once) {
                this.once = true;
                if (!this.allowFatal || (t instanceof Exception)) {
                    try {
                        Publisher<? extends T> p = (Publisher) this.nextSupplier.apply(t);
                        if (p == null) {
                            NullPointerException npe = new NullPointerException("Publisher is null");
                            npe.initCause(t);
                            this.actual.onError(npe);
                            return;
                        }
                        p.subscribe(this);
                        return;
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        this.actual.onError(new CompositeException(t, e));
                        return;
                    }
                }
                this.actual.onError(t);
            } else if (this.done) {
                RxJavaPlugins.onError(t);
            } else {
                this.actual.onError(t);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.once = true;
                this.actual.onComplete();
            }
        }
    }

    public FlowableOnErrorNext(Flowable<T> source, Function<? super Throwable, ? extends Publisher<? extends T>> nextSupplier, boolean allowFatal) {
        super(source);
        this.nextSupplier = nextSupplier;
        this.allowFatal = allowFatal;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        OnErrorNextSubscriber<T> parent = new OnErrorNextSubscriber(s, this.nextSupplier, this.allowFatal);
        s.onSubscribe(parent.arbiter);
        this.source.subscribe(parent);
    }
}
