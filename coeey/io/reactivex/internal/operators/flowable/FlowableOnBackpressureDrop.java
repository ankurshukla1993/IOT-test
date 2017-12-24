package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicLong;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableOnBackpressureDrop<T> extends AbstractFlowableWithUpstream<T, T> implements Consumer<T> {
    final Consumer<? super T> onDrop;

    static final class BackpressureDropSubscriber<T> extends AtomicLong implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -6246093802440953054L;
        final Subscriber<? super T> actual;
        boolean done;
        final Consumer<? super T> onDrop;
        Subscription f2566s;

        BackpressureDropSubscriber(Subscriber<? super T> actual, Consumer<? super T> onDrop) {
            this.actual = actual;
            this.onDrop = onDrop;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2566s, s)) {
                this.f2566s = s;
                this.actual.onSubscribe(this);
                s.request(Long.MAX_VALUE);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (get() != 0) {
                    this.actual.onNext(t);
                    BackpressureHelper.produced(this, 1);
                    return;
                }
                try {
                    this.onDrop.accept(t);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    cancel();
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
                this.actual.onComplete();
            }
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this, n);
            }
        }

        public void cancel() {
            this.f2566s.cancel();
        }
    }

    public FlowableOnBackpressureDrop(Flowable<T> source) {
        super(source);
        this.onDrop = this;
    }

    public FlowableOnBackpressureDrop(Flowable<T> source, Consumer<? super T> onDrop) {
        super(source);
        this.onDrop = onDrop;
    }

    public void accept(T t) {
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.source.subscribe(new BackpressureDropSubscriber(s, this.onDrop));
    }
}
