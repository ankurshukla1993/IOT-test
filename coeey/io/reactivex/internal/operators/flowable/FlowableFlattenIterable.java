package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Iterator;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableFlattenIterable<T, R> extends AbstractFlowableWithUpstream<T, R> {
    final Function<? super T, ? extends Iterable<? extends R>> mapper;
    final int prefetch;

    static final class FlattenIterableSubscriber<T, R> extends BasicIntQueueSubscription<R> implements FlowableSubscriber<T> {
        private static final long serialVersionUID = -3096000382929934955L;
        final Subscriber<? super R> actual;
        volatile boolean cancelled;
        int consumed;
        Iterator<? extends R> current;
        volatile boolean done;
        final AtomicReference<Throwable> error = new AtomicReference();
        int fusionMode;
        final int limit;
        final Function<? super T, ? extends Iterable<? extends R>> mapper;
        final int prefetch;
        SimpleQueue<T> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription f2552s;

        FlattenIterableSubscriber(Subscriber<? super R> actual, Function<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
            this.actual = actual;
            this.mapper = mapper;
            this.prefetch = prefetch;
            this.limit = prefetch - (prefetch >> 2);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2552s, s)) {
                this.f2552s = s;
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(3);
                    if (m == 1) {
                        this.fusionMode = m;
                        this.queue = qs;
                        this.done = true;
                        this.actual.onSubscribe(this);
                        return;
                    } else if (m == 2) {
                        this.fusionMode = m;
                        this.queue = qs;
                        this.actual.onSubscribe(this);
                        s.request((long) this.prefetch);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.prefetch);
                this.actual.onSubscribe(this);
                s.request((long) this.prefetch);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                if (this.fusionMode != 0 || this.queue.offer(t)) {
                    drain();
                } else {
                    onError(new MissingBackpressureException("Queue is full?!"));
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done || !ExceptionHelper.addThrowable(this.error, t)) {
                RxJavaPlugins.onError(t);
                return;
            }
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                drain();
            }
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            if (!this.cancelled) {
                this.cancelled = true;
                this.f2552s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            Throwable ex;
            if (getAndIncrement() == 0) {
                Subscriber<? super R> a = this.actual;
                SimpleQueue<T> q = this.queue;
                boolean replenish = this.fusionMode != 1;
                int missed = 1;
                Iterator it = this.current;
                while (true) {
                    boolean d;
                    if (it == null) {
                        d = this.done;
                        try {
                            T t = q.poll();
                            if (!checkTerminated(d, t == null, a, q)) {
                                if (t != null) {
                                    try {
                                        it = ((Iterable) this.mapper.apply(t)).iterator();
                                        if (it.hasNext()) {
                                            this.current = it;
                                        } else {
                                            it = null;
                                            consumedOne(replenish);
                                        }
                                    } catch (Throwable ex2) {
                                        Exceptions.throwIfFatal(ex2);
                                        this.f2552s.cancel();
                                        ExceptionHelper.addThrowable(this.error, ex2);
                                        a.onError(ExceptionHelper.terminate(this.error));
                                        return;
                                    }
                                }
                            }
                            return;
                        } catch (Throwable ex22) {
                            Exceptions.throwIfFatal(ex22);
                            this.f2552s.cancel();
                            ExceptionHelper.addThrowable(this.error, ex22);
                            ex22 = ExceptionHelper.terminate(this.error);
                            this.current = null;
                            q.clear();
                            a.onError(ex22);
                            return;
                        }
                    }
                    Iterator<? extends R> it2;
                    if (it2 != null) {
                        long r = this.requested.get();
                        long e = 0;
                        while (e != r) {
                            if (!checkTerminated(this.done, false, a, q)) {
                                try {
                                    a.onNext(ObjectHelper.requireNonNull(it2.next(), "The iterator returned a null value"));
                                    if (!checkTerminated(this.done, false, a, q)) {
                                        e++;
                                        try {
                                            if (!it2.hasNext()) {
                                                consumedOne(replenish);
                                                it2 = null;
                                                this.current = null;
                                                break;
                                            }
                                        } catch (Throwable ex222) {
                                            Exceptions.throwIfFatal(ex222);
                                            this.current = null;
                                            this.f2552s.cancel();
                                            ExceptionHelper.addThrowable(this.error, ex222);
                                            a.onError(ExceptionHelper.terminate(this.error));
                                            return;
                                        }
                                    }
                                    return;
                                } catch (Throwable ex2222) {
                                    Exceptions.throwIfFatal(ex2222);
                                    this.current = null;
                                    this.f2552s.cancel();
                                    ExceptionHelper.addThrowable(this.error, ex2222);
                                    a.onError(ExceptionHelper.terminate(this.error));
                                    return;
                                }
                            }
                            return;
                        }
                        if (e == r) {
                            d = this.done;
                            boolean empty = q.isEmpty() && it == null;
                            if (checkTerminated(d, empty, a, q)) {
                                return;
                            }
                        }
                        if (!(e == 0 || r == Long.MAX_VALUE)) {
                            this.requested.addAndGet(-e);
                        }
                        if (it == null) {
                            continue;
                        }
                    }
                    missed = addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }

        void consumedOne(boolean enabled) {
            if (enabled) {
                int c = this.consumed + 1;
                if (c == this.limit) {
                    this.consumed = 0;
                    this.f2552s.request((long) c);
                    return;
                }
                this.consumed = c;
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, SimpleQueue<?> q) {
            if (this.cancelled) {
                this.current = null;
                q.clear();
                return true;
            }
            if (d) {
                if (((Throwable) this.error.get()) != null) {
                    Throwable ex = ExceptionHelper.terminate(this.error);
                    this.current = null;
                    q.clear();
                    a.onError(ex);
                    return true;
                } else if (empty) {
                    a.onComplete();
                    return true;
                }
            }
            return false;
        }

        public void clear() {
            this.current = null;
            this.queue.clear();
        }

        public boolean isEmpty() {
            Iterator<? extends R> it = this.current;
            if (it == null) {
                return this.queue.isEmpty();
            }
            return !it.hasNext();
        }

        @Nullable
        public R poll() throws Exception {
            Iterator<? extends R> it = this.current;
            while (it == null) {
                T v = this.queue.poll();
                if (v != null) {
                    it = ((Iterable) this.mapper.apply(v)).iterator();
                    if (it.hasNext()) {
                        this.current = it;
                        break;
                    }
                    it = null;
                } else {
                    return null;
                }
            }
            R r = ObjectHelper.requireNonNull(it.next(), "The iterator returned a null value");
            if (it.hasNext()) {
                return r;
            }
            this.current = null;
            return r;
        }

        public int requestFusion(int requestedMode) {
            if ((requestedMode & 1) == 0 || this.fusionMode != 1) {
                return 0;
            }
            return 1;
        }
    }

    public FlowableFlattenIterable(Flowable<T> source, Function<? super T, ? extends Iterable<? extends R>> mapper, int prefetch) {
        super(source);
        this.mapper = mapper;
        this.prefetch = prefetch;
    }

    public void subscribeActual(Subscriber<? super R> s) {
        if (this.source instanceof Callable) {
            try {
                T v = ((Callable) this.source).call();
                if (v == null) {
                    EmptySubscription.complete(s);
                    return;
                }
                try {
                    FlowableFromIterable.subscribe(s, ((Iterable) this.mapper.apply(v)).iterator());
                    return;
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    EmptySubscription.error(ex, s);
                    return;
                }
            } catch (Throwable ex2) {
                Exceptions.throwIfFatal(ex2);
                EmptySubscription.error(ex2, s);
                return;
            }
        }
        this.source.subscribe(new FlattenIterableSubscriber(s, this.mapper, this.prefetch));
    }
}
