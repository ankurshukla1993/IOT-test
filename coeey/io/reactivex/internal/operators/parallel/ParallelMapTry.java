package io.reactivex.internal.operators.parallel;

import io.reactivex.exceptions.CompositeException;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.ConditionalSubscriber;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.parallel.ParallelFailureHandling;
import io.reactivex.parallel.ParallelFlowable;
import io.reactivex.plugins.RxJavaPlugins;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ParallelMapTry<T, R> extends ParallelFlowable<R> {
    final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
    final Function<? super T, ? extends R> mapper;
    final ParallelFlowable<T> source;

    static final class ParallelMapTryConditionalSubscriber<T, R> implements ConditionalSubscriber<T>, Subscription {
        final ConditionalSubscriber<? super R> actual;
        boolean done;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Function<? super T, ? extends R> mapper;
        Subscription f2773s;

        ParallelMapTryConditionalSubscriber(ConditionalSubscriber<? super R> actual, Function<? super T, ? extends R> mapper, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
            this.actual = actual;
            this.mapper = mapper;
            this.errorHandler = errorHandler;
        }

        public void request(long n) {
            this.f2773s.request(n);
        }

        public void cancel() {
            this.f2773s.cancel();
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2773s, s)) {
                this.f2773s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!tryOnNext(t) && !this.done) {
                this.f2773s.request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            long retries = 0;
            while (true) {
                try {
                    break;
                } catch (Throwable exc) {
                    Exceptions.throwIfFatal(exc);
                    cancel();
                    onError(new CompositeException(ex, exc));
                    return false;
                }
            }
            return this.actual.tryOnNext(ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null value"));
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

    static final class ParallelMapTrySubscriber<T, R> implements ConditionalSubscriber<T>, Subscription {
        final Subscriber<? super R> actual;
        boolean done;
        final BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler;
        final Function<? super T, ? extends R> mapper;
        Subscription f2774s;

        ParallelMapTrySubscriber(Subscriber<? super R> actual, Function<? super T, ? extends R> mapper, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
            this.actual = actual;
            this.mapper = mapper;
            this.errorHandler = errorHandler;
        }

        public void request(long n) {
            this.f2774s.request(n);
        }

        public void cancel() {
            this.f2774s.cancel();
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2774s, s)) {
                this.f2774s = s;
                this.actual.onSubscribe(this);
            }
        }

        public void onNext(T t) {
            if (!tryOnNext(t) && !this.done) {
                this.f2774s.request(1);
            }
        }

        public boolean tryOnNext(T t) {
            if (this.done) {
                return false;
            }
            long retries = 0;
            while (true) {
                try {
                    break;
                } catch (Throwable exc) {
                    Exceptions.throwIfFatal(exc);
                    cancel();
                    onError(new CompositeException(ex, exc));
                    return false;
                }
            }
            this.actual.onNext(ObjectHelper.requireNonNull(this.mapper.apply(t), "The mapper returned a null value"));
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

    public ParallelMapTry(ParallelFlowable<T> source, Function<? super T, ? extends R> mapper, BiFunction<? super Long, ? super Throwable, ParallelFailureHandling> errorHandler) {
        this.source = source;
        this.mapper = mapper;
        this.errorHandler = errorHandler;
    }

    public void subscribe(Subscriber<? super R>[] subscribers) {
        if (validate(subscribers)) {
            int n = subscribers.length;
            Subscriber<? super T>[] parents = new Subscriber[n];
            for (int i = 0; i < n; i++) {
                Subscriber<? super R> a = subscribers[i];
                if (a instanceof ConditionalSubscriber) {
                    parents[i] = new ParallelMapTryConditionalSubscriber((ConditionalSubscriber) a, this.mapper, this.errorHandler);
                } else {
                    parents[i] = new ParallelMapTrySubscriber(a, this.mapper, this.errorHandler);
                }
            }
            this.source.subscribe(parents);
        }
    }

    public int parallelism() {
        return this.source.parallelism();
    }
}
