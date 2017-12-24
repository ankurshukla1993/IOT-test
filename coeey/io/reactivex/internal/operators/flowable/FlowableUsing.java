package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableUsing<T, D> extends Flowable<T> {
    final Consumer<? super D> disposer;
    final boolean eager;
    final Callable<? extends D> resourceSupplier;
    final Function<? super D, ? extends Publisher<? extends T>> sourceSupplier;

    static final class UsingSubscriber<T, D> extends AtomicBoolean implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = 5904473792286235046L;
        final Subscriber<? super T> actual;
        final Consumer<? super D> disposer;
        final boolean eager;
        final D resource;
        Subscription f2607s;

        UsingSubscriber(Subscriber<? super T> actual, D resource, Consumer<? super D> disposer, boolean eager) {
            this.actual = actual;
            this.resource = resource;
            this.disposer = disposer;
            this.eager = eager;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2607s, s)) {
                this.f2607s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            this.actual.onNext(t);
        }

        public void onError(Throwable t) {
            if (this.eager) {
                Throwable innerError = null;
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        innerError = e;
                    }
                }
                this.f2607s.cancel();
                if (innerError != null) {
                    this.actual.onError(new CompositeException(t, innerError));
                    return;
                }
                this.actual.onError(t);
                return;
            }
            this.actual.onError(t);
            this.f2607s.cancel();
            disposeAfter();
        }

        public void onComplete() {
            if (this.eager) {
                if (compareAndSet(false, true)) {
                    try {
                        this.disposer.accept(this.resource);
                    } catch (Throwable e) {
                        Exceptions.throwIfFatal(e);
                        this.actual.onError(e);
                        return;
                    }
                }
                this.f2607s.cancel();
                this.actual.onComplete();
                return;
            }
            this.actual.onComplete();
            this.f2607s.cancel();
            disposeAfter();
        }

        public void request(long n) {
            this.f2607s.request(n);
        }

        public void cancel() {
            disposeAfter();
            this.f2607s.cancel();
        }

        void disposeAfter() {
            if (compareAndSet(false, true)) {
                try {
                    this.disposer.accept(this.resource);
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    RxJavaPlugins.onError(e);
                }
            }
        }
    }

    public FlowableUsing(Callable<? extends D> resourceSupplier, Function<? super D, ? extends Publisher<? extends T>> sourceSupplier, Consumer<? super D> disposer, boolean eager) {
        this.resourceSupplier = resourceSupplier;
        this.sourceSupplier = sourceSupplier;
        this.disposer = disposer;
        this.eager = eager;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        try {
            D resource = this.resourceSupplier.call();
            try {
                ((Publisher) ObjectHelper.requireNonNull(this.sourceSupplier.apply(resource), "The sourceSupplier returned a null Publisher")).subscribe(new UsingSubscriber(s, resource, this.disposer, this.eager));
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                EmptySubscription.error(new CompositeException(e, ex), s);
            }
        } catch (Throwable e) {
            Exceptions.throwIfFatal(e);
            EmptySubscription.error(e, s);
        }
    }
}
