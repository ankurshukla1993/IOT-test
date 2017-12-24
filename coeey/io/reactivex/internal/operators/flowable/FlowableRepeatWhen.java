package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.processors.FlowableProcessor;
import io.reactivex.processors.UnicastProcessor;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableRepeatWhen<T> extends AbstractFlowableWithUpstream<T, T> {
    final Function<? super Flowable<Object>, ? extends Publisher<?>> handler;

    static abstract class WhenSourceSubscriber<T, U> extends SubscriptionArbiter implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -5604623027276966720L;
        protected final Subscriber<? super T> actual;
        protected final FlowableProcessor<U> processor;
        private long produced;
        protected final Subscription receiver;

        WhenSourceSubscriber(Subscriber<? super T> actual, FlowableProcessor<U> processor, Subscription receiver) {
            this.actual = actual;
            this.processor = processor;
            this.receiver = receiver;
        }

        public final void onSubscribe(Subscription s) {
            setSubscription(s);
        }

        public final void onNext(T t) {
            this.produced++;
            this.actual.onNext(t);
        }

        protected final void again(U signal) {
            long p = this.produced;
            if (p != 0) {
                this.produced = 0;
                produced(p);
            }
            this.receiver.request(1);
            this.processor.onNext(signal);
        }

        public final void cancel() {
            super.cancel();
            this.receiver.cancel();
        }
    }

    static final class RepeatWhenSubscriber<T> extends WhenSourceSubscriber<T, Object> {
        private static final long serialVersionUID = -2680129890138081029L;

        RepeatWhenSubscriber(Subscriber<? super T> actual, FlowableProcessor<Object> processor, Subscription receiver) {
            super(actual, processor, receiver);
        }

        public void onError(Throwable t) {
            this.receiver.cancel();
            this.actual.onError(t);
        }

        public void onComplete() {
            again(Integer.valueOf(0));
        }
    }

    static final class WhenReceiver<T, U> extends AtomicInteger implements FlowableSubscriber<Object>, Subscription {
        private static final long serialVersionUID = 2827772011130406689L;
        final AtomicLong requested = new AtomicLong();
        final Publisher<T> source;
        WhenSourceSubscriber<T, U> subscriber;
        final AtomicReference<Subscription> subscription = new AtomicReference();

        WhenReceiver(Publisher<T> source) {
            this.source = source;
        }

        public void onSubscribe(Subscription s) {
            SubscriptionHelper.deferredSetOnce(this.subscription, this.requested, s);
        }

        public void onNext(Object t) {
            if (getAndIncrement() == 0) {
                while (!SubscriptionHelper.isCancelled((Subscription) this.subscription.get())) {
                    this.source.subscribe(this.subscriber);
                    if (decrementAndGet() == 0) {
                        return;
                    }
                }
            }
        }

        public void onError(Throwable t) {
            this.subscriber.cancel();
            this.subscriber.actual.onError(t);
        }

        public void onComplete() {
            this.subscriber.cancel();
            this.subscriber.actual.onComplete();
        }

        public void request(long n) {
            SubscriptionHelper.deferredRequest(this.subscription, this.requested, n);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.subscription);
        }
    }

    public FlowableRepeatWhen(Flowable<T> source, Function<? super Flowable<Object>, ? extends Publisher<?>> handler) {
        super(source);
        this.handler = handler;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        SerializedSubscriber<T> z = new SerializedSubscriber(s);
        FlowableProcessor<Object> processor = UnicastProcessor.create(8).toSerialized();
        try {
            Publisher<?> when = (Publisher) ObjectHelper.requireNonNull(this.handler.apply(processor), "handler returned a null Publisher");
            WhenReceiver<T, Object> receiver = new WhenReceiver(this.source);
            RepeatWhenSubscriber<T> subscriber = new RepeatWhenSubscriber(z, processor, receiver);
            receiver.subscriber = subscriber;
            s.onSubscribe(subscriber);
            when.subscribe(receiver);
            receiver.onNext(Integer.valueOf(0));
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            EmptySubscription.error(ex, s);
        }
    }
}
