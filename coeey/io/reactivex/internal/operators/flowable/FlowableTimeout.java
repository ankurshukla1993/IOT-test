package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Function;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscribers.FullArbiterSubscriber;
import io.reactivex.internal.subscriptions.FullArbiter;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.subscribers.DisposableSubscriber;
import io.reactivex.subscribers.SerializedSubscriber;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableTimeout<T, U, V> extends AbstractFlowableWithUpstream<T, T> {
    final Publisher<U> firstTimeoutIndicator;
    final Function<? super T, ? extends Publisher<V>> itemTimeoutIndicator;
    final Publisher<? extends T> other;

    interface OnTimeout {
        void onError(Throwable th);

        void timeout(long j);
    }

    static final class TimeoutInnerSubscriber<T, U, V> extends DisposableSubscriber<Object> {
        boolean done;
        final long index;
        final OnTimeout parent;

        TimeoutInnerSubscriber(OnTimeout parent, long index) {
            this.parent = parent;
            this.index = index;
        }

        public void onNext(Object t) {
            if (!this.done) {
                this.done = true;
                cancel();
                this.parent.timeout(this.index);
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            this.parent.onError(t);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.parent.timeout(this.index);
            }
        }
    }

    static final class TimeoutOtherSubscriber<T, U, V> implements FlowableSubscriber<T>, Disposable, OnTimeout {
        final Subscriber<? super T> actual;
        final FullArbiter<T> arbiter;
        volatile boolean cancelled;
        boolean done;
        final Publisher<U> firstTimeoutIndicator;
        volatile long index;
        final Function<? super T, ? extends Publisher<V>> itemTimeoutIndicator;
        final Publisher<? extends T> other;
        Subscription f2600s;
        final AtomicReference<Disposable> timeout = new AtomicReference();

        TimeoutOtherSubscriber(Subscriber<? super T> actual, Publisher<U> firstTimeoutIndicator, Function<? super T, ? extends Publisher<V>> itemTimeoutIndicator, Publisher<? extends T> other) {
            this.actual = actual;
            this.firstTimeoutIndicator = firstTimeoutIndicator;
            this.itemTimeoutIndicator = itemTimeoutIndicator;
            this.other = other;
            this.arbiter = new FullArbiter(actual, this, 8);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2600s, s)) {
                this.f2600s = s;
                if (this.arbiter.setSubscription(s)) {
                    Subscriber<? super T> a = this.actual;
                    Publisher<U> p = this.firstTimeoutIndicator;
                    if (p != null) {
                        TimeoutInnerSubscriber<T, U, V> tis = new TimeoutInnerSubscriber(this, 0);
                        if (this.timeout.compareAndSet(null, tis)) {
                            a.onSubscribe(this.arbiter);
                            p.subscribe(tis);
                            return;
                        }
                        return;
                    }
                    a.onSubscribe(this.arbiter);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                long idx = this.index + 1;
                this.index = idx;
                if (this.arbiter.onNext(t, this.f2600s)) {
                    Disposable d = (Disposable) this.timeout.get();
                    if (d != null) {
                        d.dispose();
                    }
                    try {
                        Publisher<V> p = (Publisher) ObjectHelper.requireNonNull(this.itemTimeoutIndicator.apply(t), "The publisher returned is null");
                        TimeoutInnerSubscriber<T, U, V> tis = new TimeoutInnerSubscriber(this, idx);
                        if (this.timeout.compareAndSet(d, tis)) {
                            p.subscribe(tis);
                        }
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        this.actual.onError(e);
                    }
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            dispose();
            this.arbiter.onError(t, this.f2600s);
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                dispose();
                this.arbiter.onComplete(this.f2600s);
            }
        }

        public void dispose() {
            this.cancelled = true;
            this.f2600s.cancel();
            DisposableHelper.dispose(this.timeout);
        }

        public boolean isDisposed() {
            return this.cancelled;
        }

        public void timeout(long idx) {
            if (idx == this.index) {
                dispose();
                this.other.subscribe(new FullArbiterSubscriber(this.arbiter));
            }
        }
    }

    static final class TimeoutSubscriber<T, U, V> implements FlowableSubscriber<T>, Subscription, OnTimeout {
        final Subscriber<? super T> actual;
        volatile boolean cancelled;
        final Publisher<U> firstTimeoutIndicator;
        volatile long index;
        final Function<? super T, ? extends Publisher<V>> itemTimeoutIndicator;
        Subscription f2601s;
        final AtomicReference<Disposable> timeout = new AtomicReference();

        TimeoutSubscriber(Subscriber<? super T> actual, Publisher<U> firstTimeoutIndicator, Function<? super T, ? extends Publisher<V>> itemTimeoutIndicator) {
            this.actual = actual;
            this.firstTimeoutIndicator = firstTimeoutIndicator;
            this.itemTimeoutIndicator = itemTimeoutIndicator;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2601s, s)) {
                this.f2601s = s;
                if (!this.cancelled) {
                    Subscriber<? super T> a = this.actual;
                    Publisher<U> p = this.firstTimeoutIndicator;
                    if (p != null) {
                        TimeoutInnerSubscriber<T, U, V> tis = new TimeoutInnerSubscriber(this, 0);
                        if (this.timeout.compareAndSet(null, tis)) {
                            a.onSubscribe(this);
                            p.subscribe(tis);
                            return;
                        }
                        return;
                    }
                    a.onSubscribe(this);
                }
            }
        }

        public void onNext(T t) {
            long idx = this.index + 1;
            this.index = idx;
            this.actual.onNext(t);
            Disposable d = (Disposable) this.timeout.get();
            if (d != null) {
                d.dispose();
            }
            try {
                Publisher<V> p = (Publisher) ObjectHelper.requireNonNull(this.itemTimeoutIndicator.apply(t), "The publisher returned is null");
                TimeoutInnerSubscriber<T, U, V> tis = new TimeoutInnerSubscriber(this, idx);
                if (this.timeout.compareAndSet(d, tis)) {
                    p.subscribe(tis);
                }
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                cancel();
                this.actual.onError(e);
            }
        }

        public void onError(Throwable t) {
            cancel();
            this.actual.onError(t);
        }

        public void onComplete() {
            cancel();
            this.actual.onComplete();
        }

        public void request(long n) {
            this.f2601s.request(n);
        }

        public void cancel() {
            this.cancelled = true;
            this.f2601s.cancel();
            DisposableHelper.dispose(this.timeout);
        }

        public void timeout(long idx) {
            if (idx == this.index) {
                cancel();
                this.actual.onError(new TimeoutException());
            }
        }
    }

    public FlowableTimeout(Flowable<T> source, Publisher<U> firstTimeoutIndicator, Function<? super T, ? extends Publisher<V>> itemTimeoutIndicator, Publisher<? extends T> other) {
        super(source);
        this.firstTimeoutIndicator = firstTimeoutIndicator;
        this.itemTimeoutIndicator = itemTimeoutIndicator;
        this.other = other;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        if (this.other == null) {
            this.source.subscribe(new TimeoutSubscriber(new SerializedSubscriber(s), this.firstTimeoutIndicator, this.itemTimeoutIndicator));
        } else {
            this.source.subscribe(new TimeoutOtherSubscriber(s, this.firstTimeoutIndicator, this.itemTimeoutIndicator, this.other));
        }
    }
}
