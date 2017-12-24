package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.functions.BiPredicate;
import io.reactivex.internal.fuseable.FuseToFlowable;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.AtomicThrowable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscription;

public final class FlowableSequenceEqualSingle<T> extends Single<Boolean> implements FuseToFlowable<Boolean> {
    final BiPredicate<? super T, ? super T> comparer;
    final Publisher<? extends T> first;
    final int prefetch;
    final Publisher<? extends T> second;

    static final class EqualCoordinator<T> extends AtomicInteger implements Disposable, EqualCoordinatorHelper {
        private static final long serialVersionUID = -6178010334400373240L;
        final SingleObserver<? super Boolean> actual;
        final BiPredicate<? super T, ? super T> comparer;
        final AtomicThrowable error = new AtomicThrowable();
        final EqualSubscriber<T> first;
        final EqualSubscriber<T> second;
        T v1;
        T v2;

        EqualCoordinator(SingleObserver<? super Boolean> actual, int prefetch, BiPredicate<? super T, ? super T> comparer) {
            this.actual = actual;
            this.comparer = comparer;
            this.first = new EqualSubscriber(this, prefetch);
            this.second = new EqualSubscriber(this, prefetch);
        }

        void subscribe(Publisher<? extends T> source1, Publisher<? extends T> source2) {
            source1.subscribe(this.first);
            source2.subscribe(this.second);
        }

        public void dispose() {
            this.first.cancel();
            this.second.cancel();
            if (getAndIncrement() == 0) {
                this.first.clear();
                this.second.clear();
            }
        }

        public boolean isDisposed() {
            return SubscriptionHelper.isCancelled((Subscription) this.first.get());
        }

        void cancelAndClear() {
            this.first.cancel();
            this.first.clear();
            this.second.cancel();
            this.second.clear();
        }

        public void drain() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                do {
                    SimpleQueue<T> q1 = this.first.queue;
                    SimpleQueue<T> q2 = this.second.queue;
                    if (q1 != null && q2 != null) {
                        while (!isDisposed()) {
                            if (((Throwable) this.error.get()) != null) {
                                cancelAndClear();
                                this.actual.onError(this.error.terminate());
                                return;
                            }
                            boolean d1 = this.first.done;
                            T a = this.v1;
                            if (a == null) {
                                try {
                                    a = q1.poll();
                                    this.v1 = a;
                                } catch (Throwable exc) {
                                    Exceptions.throwIfFatal(exc);
                                    cancelAndClear();
                                    this.error.addThrowable(exc);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean e1 = a == null;
                            boolean d2 = this.second.done;
                            T b = this.v2;
                            if (b == null) {
                                try {
                                    b = q2.poll();
                                    this.v2 = b;
                                } catch (Throwable exc2) {
                                    Exceptions.throwIfFatal(exc2);
                                    cancelAndClear();
                                    this.error.addThrowable(exc2);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                            boolean e2 = b == null;
                            if (d1 && d2 && e1 && e2) {
                                this.actual.onSuccess(Boolean.valueOf(true));
                                return;
                            } else if (d1 && d2 && e1 != e2) {
                                cancelAndClear();
                                this.actual.onSuccess(Boolean.valueOf(false));
                                return;
                            } else if (!(e1 || e2)) {
                                try {
                                    if (this.comparer.test(a, b)) {
                                        this.v1 = null;
                                        this.v2 = null;
                                        this.first.request();
                                        this.second.request();
                                    } else {
                                        cancelAndClear();
                                        this.actual.onSuccess(Boolean.valueOf(false));
                                        return;
                                    }
                                } catch (Throwable exc22) {
                                    Exceptions.throwIfFatal(exc22);
                                    cancelAndClear();
                                    this.error.addThrowable(exc22);
                                    this.actual.onError(this.error.terminate());
                                    return;
                                }
                            }
                        }
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (isDisposed()) {
                        this.first.clear();
                        this.second.clear();
                        return;
                    } else if (((Throwable) this.error.get()) != null) {
                        cancelAndClear();
                        this.actual.onError(this.error.terminate());
                        return;
                    }
                    missed = addAndGet(-missed);
                } while (missed != 0);
            }
        }

        public void innerError(Throwable t) {
            if (this.error.addThrowable(t)) {
                drain();
            } else {
                RxJavaPlugins.onError(t);
            }
        }
    }

    public FlowableSequenceEqualSingle(Publisher<? extends T> first, Publisher<? extends T> second, BiPredicate<? super T, ? super T> comparer, int prefetch) {
        this.first = first;
        this.second = second;
        this.comparer = comparer;
        this.prefetch = prefetch;
    }

    public void subscribeActual(SingleObserver<? super Boolean> s) {
        EqualCoordinator<T> parent = new EqualCoordinator(s, this.prefetch, this.comparer);
        s.onSubscribe(parent);
        parent.subscribe(this.first, this.second);
    }

    public Flowable<Boolean> fuseToFlowable() {
        return RxJavaPlugins.onAssembly(new FlowableSequenceEqual(this.first, this.second, this.comparer, this.prefetch));
    }
}
