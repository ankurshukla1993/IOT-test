package io.reactivex.internal.operators.parallel;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.ParallelFailureHandling;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelDoOnNextTry<T> extends ParallelFlowable<T> {
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
    final Consumer<? super T> onNext;
    final ParallelFlowable<T> source;

    static final class ParallelDoOnNextConditionalSubscriber<T> implements ConditionalSubscriber<T>, Subscription {
        final ConditionalSubscriber<? super T> actual;
        boolean done;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Consumer<? super T> onNext;
        Subscription f2764s;

        ParallelDoOnNextConditionalSubscriber(ConditionalSubscriber<? super T> actual, Consumer<? super T> onNext, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
            this.actual = actual;
            this.onNext = onNext;
            this.errorHandler = errorHandler;
        }

        public void request(long n) {
            this.f2764s.request(n);
        }

        public void cancel() {
            this.f2764s.cancel();
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2764s, s)) {
                this.f2764s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!tryOnNext(t) && !this.done) {
                this.f2764s.request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            long retries = 0;
            while (true) {
                try {
                    this.onNext.accept(t);
                    break;
                } catch (Throwable exc) {
                    Exceptions.throwIfFatal(exc);
                    cancel();
                    onError(new CompositeException(ex, exc));
                    return false;
                }
            }
            return this.actual.tryOnNext(t);
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
    }

    static final class ParallelDoOnNextSubscriber<T> implements ConditionalSubscriber<T>, Subscription {
        final Subscriber<? super T> actual;
        boolean done;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Consumer<? super T> onNext;
        Subscription f2765s;

        ParallelDoOnNextSubscriber(Subscriber<? super T> actual, Consumer<? super T> onNext, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
            this.actual = actual;
            this.onNext = onNext;
            this.errorHandler = errorHandler;
        }

        public void request(long n) {
            this.f2765s.request(n);
        }

        public void cancel() {
            this.f2765s.cancel();
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2765s, s)) {
                this.f2765s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!tryOnNext(t)) {
                this.f2765s.request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            long retries = 0;
            while (true) {
                try {
                    this.onNext.accept(t);
                    break;
                } catch (Throwable exc) {
                    Exceptions.throwIfFatal(exc);
                    cancel();
                    onError(new CompositeException(ex, exc));
                    return false;
                }
            }
            this.actual.onNext(t);
            return true;
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
    }

    public ParallelDoOnNextTry(ParallelFlowable<T> source, Consumer<? super T> onNext, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
        this.source = source;
        this.onNext = onNext;
        this.errorHandler = errorHandler;
    }

    public void subscribe(Subscriber<? super T>[] subscribers) {
        if (validate(subscribers)) {
            int n = subscribers.length;
            Subscriber<? super T>[] parents = new Subscriber[n];
            for (int i = 0; i < n; i++) {
                Subscriber<? super T> a = subscribers[i];
                if (a instanceof ConditionalSubscriber) {
                    parents[i] = new ParallelDoOnNextConditionalSubscriber((ConditionalSubscriber) a, this.onNext, this.errorHandler);
                } else {
                    parents[i] = new ParallelDoOnNextSubscriber(a, this.onNext, this.errorHandler);
                }
            }
            this.source.subscribe(parents);
        }
    }

    public int parallelism() {
        return this.source.parallelism();
    }
}
