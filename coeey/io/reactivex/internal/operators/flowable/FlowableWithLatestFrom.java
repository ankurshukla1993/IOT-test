package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableWithLatestFrom<T, U, R> extends AbstractFlowableWithUpstream<T, R> {
    final BiFunction<? super T, ? super U, ? extends R> combiner;
    final Publisher<? extends U> other;

    final class FlowableWithLatestSubscriber implements FlowableSubscriber<U> {
        private final WithLatestFromSubscriber<T, U, R> wlf;

        FlowableWithLatestSubscriber(WithLatestFromSubscriber<T, U, R> wlf) {
            this.wlf = wlf;
        }

        public void onSubscribe(Subscription s) {
            if (this.wlf.setOther(s)) {
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(U t) {
            this.wlf.lazySet(t);
        }

        public void onError(Throwable t) {
            this.wlf.otherError(t);
        }

        public void onComplete() {
        }
    }

    static final class WithLatestFromSubscriber<T, U, R> extends AtomicReference<U> implements ConditionalSubscriber<T>, Subscription {
        private static final long serialVersionUID = -312246233408980075L;
        final Subscriber<? super R> actual;
        final BiFunction<? super T, ? super U, ? extends R> combiner;
        final AtomicReference<Subscription> other = new AtomicReference();
        final AtomicLong requested = new AtomicLong();
        final AtomicReference<Subscription> f2620s = new AtomicReference();

        WithLatestFromSubscriber(Subscriber<? super R> actual, BiFunction<? super T, ? super U, ? extends R> combiner) {
            this.actual = actual;
            this.combiner = combiner;
        }

        public void onSubscribe(Subscription s) {
            SubscriptionHelper.deferredSetOnce(this.f2620s, this.requested, s);
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                ((Subscription) this.f2620s.get()).request(1);
            }
        }

        public boolean tryOnNext(T t) {
            U u = get();
            if (u == null) {
                return false;
            }
            try {
                this.actual.onNext(ObjectHelper.requireNonNull(this.combiner.apply(t, u), "The combiner returned a null value"));
                return true;
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                cancel();
                this.actual.onError(e);
                return false;
            }
        }

        public void onError(Throwable t) {
            SubscriptionHelper.cancel(this.other);
            this.actual.onError(t);
        }

        public void onComplete() {
            SubscriptionHelper.cancel(this.other);
            this.actual.onComplete();
        }

        public void request(long n) {
            SubscriptionHelper.deferredRequest(this.f2620s, this.requested, n);
        }

        public void cancel() {
            SubscriptionHelper.cancel(this.f2620s);
            SubscriptionHelper.cancel(this.other);
        }

        public boolean setOther(Subscription o) {
            return SubscriptionHelper.setOnce(this.other, o);
        }

        public void otherError(Throwable e) {
            SubscriptionHelper.cancel(this.f2620s);
            this.actual.onError(e);
        }
    }

    public FlowableWithLatestFrom(Flowable<T> source, BiFunction<? super T, ? super U, ? extends R> combiner, Publisher<? extends U> other) {
        super(source);
        this.combiner = combiner;
        this.other = other;
    }

    protected void subscribeActual(Subscriber<? super R> s) {
        SerializedSubscriber<R> serial = new SerializedSubscriber(s);
        WithLatestFromSubscriber<T, U, R> wlf = new WithLatestFromSubscriber(serial, this.combiner);
        serial.onSubscribe(wlf);
        this.other.subscribe(new FlowableWithLatestSubscriber(wlf));
        this.source.subscribe(wlf);
    }
}
