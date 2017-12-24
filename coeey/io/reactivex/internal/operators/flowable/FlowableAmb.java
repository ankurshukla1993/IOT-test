package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableAmb<T> extends Flowable<T> {
    final Publisher<? extends T>[] sources;
    final Iterable<? extends Publisher<? extends T>> sourcesIterable;

    static final class AmbCoordinator<T> implements Subscription {
        final Subscriber<? super T> actual;
        final AmbInnerSubscriber<T>[] subscribers;
        final AtomicInteger winner = new AtomicInteger();

        AmbCoordinator(Subscriber<? super T> actual, int count) {
            this.actual = actual;
            this.subscribers = new AmbInnerSubscriber[count];
        }

        public void subscribe(Publisher<? extends T>[] sources) {
            int i;
            AmbInnerSubscriber<T>[] as = this.subscribers;
            int len = as.length;
            for (i = 0; i < len; i++) {
                as[i] = new AmbInnerSubscriber(this, i + 1, this.actual);
            }
            this.winner.lazySet(0);
            this.actual.onSubscribe(this);
            for (i = 0; i < len && this.winner.get() == 0; i++) {
                sources[i].subscribe(as[i]);
            }
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                int w = this.winner.get();
                if (w > 0) {
                    this.subscribers[w - 1].request(n);
                } else if (w == 0) {
                    for (AmbInnerSubscriber<T> a : this.subscribers) {
                        a.request(n);
                    }
                }
            }
        }

        public boolean win(int index) {
            if (this.winner.get() != 0 || !this.winner.compareAndSet(0, index)) {
                return false;
            }
            AmbInnerSubscriber<T>[] a = this.subscribers;
            int n = a.length;
            for (int i = 0; i < n; i++) {
                if (i + 1 != index) {
                    a[i].cancel();
                }
            }
            return true;
        }

        public void cancel() {
            if (this.winner.get() != -1) {
                this.winner.lazySet(-1);
                for (AmbInnerSubscriber<T> a : this.subscribers) {
                    a.cancel();
                }
            }
        }
    }

    static final class AmbInnerSubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Subscription {
        private static final long serialVersionUID = -1185974347409665484L;
        final Subscriber<? super T> actual;
        final int index;
        final AtomicLong missedRequested = new AtomicLong();
        final AmbCoordinator<T> parent;
        boolean won;

        AmbInnerSubscriber(AmbCoordinator<T> parent, int index, Subscriber<? super T> actual) {
            this.parent = parent;
            this.index = index;
            this.actual = actual;
        }

        public void onSubscribe(Subscription s) {
            SubscriptionHelper.deferredSetOnce(this, this.missedRequested, s);
        }

        public void request(long n) {
            SubscriptionHelper.deferredRequest(this, this.missedRequested, n);
        }

        public void onNext(T t) {
            if (this.won) {
                this.actual.onNext(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onNext(t);
            } else {
                ((Subscription) get()).cancel();
            }
        }

        public void onError(Throwable t) {
            if (this.won) {
                this.actual.onError(t);
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onError(t);
            } else {
                ((Subscription) get()).cancel();
                RxJavaPlugins.onError(t);
            }
        }

        public void onComplete() {
            if (this.won) {
                this.actual.onComplete();
            } else if (this.parent.win(this.index)) {
                this.won = true;
                this.actual.onComplete();
            } else {
                ((Subscription) get()).cancel();
            }
        }

        public void cancel() {
            SubscriptionHelper.cancel(this);
        }
    }

    public FlowableAmb(Publisher<? extends T>[] sources, Iterable<? extends Publisher<? extends T>> sourcesIterable) {
        this.sources = sources;
        this.sourcesIterable = sourcesIterable;
    }

    public void subscribeActual(Subscriber<? super T> s) {
        int i;
        Throwable e;
        Publisher<? extends T>[] sources = this.sources;
        if (sources == null) {
            sources = new Publisher[8];
            try {
                int count = 0;
                for (Publisher<? extends T> p : this.sourcesIterable) {
                    try {
                        if (p == null) {
                            EmptySubscription.error(new NullPointerException("One of the sources is null"), s);
                            i = count;
                            return;
                        }
                        if (count == sources.length) {
                            Publisher<? extends T>[] b = new Publisher[((count >> 2) + count)];
                            System.arraycopy(sources, 0, b, 0, count);
                            sources = b;
                        }
                        i = count + 1;
                        sources[count] = p;
                        count = i;
                    } catch (Throwable th) {
                        e = th;
                        i = count;
                    }
                }
                i = count;
            } catch (Throwable th2) {
                e = th2;
            }
        } else {
            i = sources.length;
        }
        if (i == 0) {
            EmptySubscription.complete(s);
            return;
        } else if (i == 1) {
            sources[0].subscribe(s);
            return;
        } else {
            new AmbCoordinator(s, i).subscribe(sources);
            return;
        }
        Exceptions.throwIfFatal(e);
        EmptySubscription.error(e, s);
    }
}
