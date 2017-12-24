package io.reactivex.subscribers;

import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class SafeSubscriber<T> implements FlowableSubscriber<T>, Subscription {
    final Subscriber<? super T> actual;
    boolean done;
    Subscription f2820s;

    public SafeSubscriber(Subscriber<? super T> actual) {
        this.actual = actual;
    }

    public void onSubscribe(Subscription s) {
        if (SubscriptionHelper.validate(this.f2820s, s)) {
            this.f2820s = s;
            try {
                this.actual.onSubscribe(this);
            } catch (Throwable e1) {
                Exceptions.throwIfFatal(e1);
                RxJavaPlugins.onError(new CompositeException(e, e1));
            }
        }
    }

    public void onNext(T t) {
        if (!this.done) {
            if (this.f2820s == null) {
                onNextNoSubscription();
            } else if (t == null) {
                Throwable ex = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
                try {
                    this.f2820s.cancel();
                    onError(ex);
                } catch (Throwable e1) {
                    Exceptions.throwIfFatal(e1);
                    onError(new CompositeException(ex, e1));
                }
            } else {
                try {
                    this.actual.onNext(t);
                } catch (Throwable e12) {
                    Exceptions.throwIfFatal(e12);
                    onError(new CompositeException(e, e12));
                }
            }
        }
    }

    void onNextNoSubscription() {
        this.done = true;
        Throwable ex = new NullPointerException("Subscription not set!");
        try {
            this.actual.onSubscribe(EmptySubscription.INSTANCE);
            try {
                this.actual.onError(ex);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.onError(new CompositeException(ex, e));
            }
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            RxJavaPlugins.onError(new CompositeException(ex, e2));
        }
    }

    public void onError(Throwable t) {
        if (this.done) {
            RxJavaPlugins.onError(t);
            return;
        }
        this.done = true;
        if (this.f2820s == null) {
            Throwable npe = new NullPointerException("Subscription not set!");
            try {
                this.actual.onSubscribe(EmptySubscription.INSTANCE);
                try {
                    this.actual.onError(new CompositeException(t, npe));
                    return;
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    RxJavaPlugins.onError(new CompositeException(t, npe, e));
                    return;
                }
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                RxJavaPlugins.onError(new CompositeException(t, npe, e2));
                return;
            }
        }
        if (t == null) {
            t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        try {
            this.actual.onError(t);
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            RxJavaPlugins.onError(new CompositeException(t, ex));
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            if (this.f2820s == null) {
                onCompleteNoSubscription();
                return;
            }
            try {
                this.actual.onComplete();
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.onError(e);
            }
        }
    }

    void onCompleteNoSubscription() {
        Throwable ex = new NullPointerException("Subscription not set!");
        try {
            this.actual.onSubscribe(EmptySubscription.INSTANCE);
            try {
                this.actual.onError(ex);
            } catch (Throwable e) {
                Exceptions.throwIfFatal(e);
                RxJavaPlugins.onError(new CompositeException(ex, e));
            }
        } catch (Throwable e2) {
            Exceptions.throwIfFatal(e2);
            RxJavaPlugins.onError(new CompositeException(ex, e2));
        }
    }

    public void request(long n) {
        try {
            this.f2820s.request(n);
        } catch (Throwable e1) {
            Exceptions.throwIfFatal(e1);
            RxJavaPlugins.onError(new CompositeException(e, e1));
        }
    }

    public void cancel() {
        try {
            this.f2820s.cancel();
        } catch (Throwable e1) {
            Exceptions.throwIfFatal(e1);
            RxJavaPlugins.onError(e1);
        }
    }
}
