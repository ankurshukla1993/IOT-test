package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.exceptions.MissingBackpressureException;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.fuseable.QueueSubscription;
import io.reactivex.internal.fuseable.SimpleQueue;
import io.reactivex.internal.queue.SpscArrayQueue;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowablePublish<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T> {
    static final long CANCELLED = Long.MIN_VALUE;
    final int bufferSize;
    final AtomicReference<PublishSubscriber<T>> current;
    final Publisher<T> onSubscribe;
    final Flowable<T> source;

    static final class FlowablePublisher<T> implements Publisher<T> {
        private final int bufferSize;
        private final AtomicReference<PublishSubscriber<T>> curr;

        FlowablePublisher(AtomicReference<PublishSubscriber<T>> curr, int bufferSize) {
            this.curr = curr;
            this.bufferSize = bufferSize;
        }

        public void subscribe(Subscriber<? super T> child) {
            PublishSubscriber<T> r;
            InnerSubscriber<T> inner = new InnerSubscriber(child);
            child.onSubscribe(inner);
            while (true) {
                r = (PublishSubscriber) this.curr.get();
                if (r == null || r.isDisposed()) {
                    PublishSubscriber<T> u = new PublishSubscriber(this.curr, this.bufferSize);
                    if (this.curr.compareAndSet(r, u)) {
                        r = u;
                    } else {
                        continue;
                    }
                }
                if (r.add(inner)) {
                    break;
                }
            }
            if (inner.get() == Long.MIN_VALUE) {
                r.remove(inner);
            } else {
                inner.parent = r;
            }
            r.dispatch();
        }
    }

    static final class InnerSubscriber<T> extends AtomicLong implements Subscription {
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        volatile PublishSubscriber<T> parent;

        InnerSubscriber(Subscriber<? super T> child) {
            this.child = child;
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.addCancel(this, n);
                PublishSubscriber<T> p = this.parent;
                if (p != null) {
                    p.dispatch();
                }
            }
        }

        public long produced(long n) {
            return BackpressureHelper.producedCancel(this, n);
        }

        public void cancel() {
            if (get() != Long.MIN_VALUE && getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                PublishSubscriber<T> p = this.parent;
                if (p != null) {
                    p.remove(this);
                    p.dispatch();
                }
            }
        }
    }

    static final class PublishSubscriber<T> extends AtomicInteger implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscriber[] EMPTY = new InnerSubscriber[0];
        static final InnerSubscriber[] TERMINATED = new InnerSubscriber[0];
        private static final long serialVersionUID = -202316842419149694L;
        final int bufferSize;
        final AtomicReference<PublishSubscriber<T>> current;
        volatile SimpleQueue<T> queue;
        final AtomicReference<Subscription> f2569s = new AtomicReference();
        final AtomicBoolean shouldConnect;
        int sourceMode;
        final AtomicReference<InnerSubscriber[]> subscribers = new AtomicReference(EMPTY);
        volatile Object terminalEvent;

        PublishSubscriber(AtomicReference<PublishSubscriber<T>> current, int bufferSize) {
            this.current = current;
            this.shouldConnect = new AtomicBoolean();
            this.bufferSize = bufferSize;
        }

        public void dispose() {
            if (this.subscribers.get() != TERMINATED && ((InnerSubscriber[]) this.subscribers.getAndSet(TERMINATED)) != TERMINATED) {
                this.current.compareAndSet(this, null);
                SubscriptionHelper.cancel(this.f2569s);
            }
        }

        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.setOnce(this.f2569s, s)) {
                if (s instanceof QueueSubscription) {
                    QueueSubscription<T> qs = (QueueSubscription) s;
                    int m = qs.requestFusion(3);
                    if (m == 1) {
                        this.sourceMode = m;
                        this.queue = qs;
                        this.terminalEvent = NotificationLite.complete();
                        dispatch();
                        return;
                    } else if (m == 2) {
                        this.sourceMode = m;
                        this.queue = qs;
                        s.request((long) this.bufferSize);
                        return;
                    }
                }
                this.queue = new SpscArrayQueue(this.bufferSize);
                s.request((long) this.bufferSize);
            }
        }

        public void onNext(T t) {
            if (this.sourceMode != 0 || this.queue.offer(t)) {
                dispatch();
            } else {
                onError(new MissingBackpressureException("Prefetch queue is full?!"));
            }
        }

        public void onError(Throwable e) {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.error(e);
                dispatch();
                return;
            }
            RxJavaPlugins.onError(e);
        }

        public void onComplete() {
            if (this.terminalEvent == null) {
                this.terminalEvent = NotificationLite.complete();
                dispatch();
            }
        }

        boolean add(InnerSubscriber<T> producer) {
            InnerSubscriber[] c;
            InnerSubscriber[] u;
            do {
                c = (InnerSubscriber[]) this.subscribers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerSubscriber[(len + 1)];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = producer;
            } while (!this.subscribers.compareAndSet(c, u));
            return true;
        }

        void remove(InnerSubscriber<T> producer) {
            InnerSubscriber[] c;
            InnerSubscriber[] u;
            do {
                c = (InnerSubscriber[]) this.subscribers.get();
                int len = c.length;
                if (len != 0) {
                    int j = -1;
                    for (int i = 0; i < len; i++) {
                        if (c[i].equals(producer)) {
                            j = i;
                            break;
                        }
                    }
                    if (j < 0) {
                        return;
                    }
                    if (len == 1) {
                        u = EMPTY;
                    } else {
                        u = new InnerSubscriber[(len - 1)];
                        System.arraycopy(c, 0, u, 0, j);
                        System.arraycopy(c, j + 1, u, j, (len - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(c, u));
        }

        boolean checkTerminated(Object term, boolean empty) {
            int i = 0;
            if (term != null) {
                if (!NotificationLite.isComplete(term)) {
                    Throwable t = NotificationLite.getError(term);
                    this.current.compareAndSet(this, null);
                    InnerSubscriber[] a = (InnerSubscriber[]) this.subscribers.getAndSet(TERMINATED);
                    if (a.length != 0) {
                        int length = a.length;
                        while (i < length) {
                            a[i].child.onError(t);
                            i++;
                        }
                    } else {
                        RxJavaPlugins.onError(t);
                    }
                    return true;
                } else if (empty) {
                    this.current.compareAndSet(this, null);
                    InnerSubscriber[] innerSubscriberArr = (InnerSubscriber[]) this.subscribers.getAndSet(TERMINATED);
                    int length2 = innerSubscriberArr.length;
                    while (i < length2) {
                        innerSubscriberArr[i].child.onComplete();
                        i++;
                    }
                    return true;
                }
            }
            return false;
        }

        void dispatch() {
            if (getAndIncrement() == 0) {
                int missed = 1;
                while (true) {
                    Object term = this.terminalEvent;
                    SimpleQueue<T> q = this.queue;
                    boolean z = q == null || q.isEmpty();
                    if (!checkTerminated(term, z)) {
                        if (!z) {
                            InnerSubscriber[] ps = (InnerSubscriber[]) this.subscribers.get();
                            int len = ps.length;
                            long maxRequested = Long.MAX_VALUE;
                            int cancelled = 0;
                            for (InnerSubscriber<T> ip : ps) {
                                long r = ip.get();
                                if (r >= 0) {
                                    maxRequested = Math.min(maxRequested, r);
                                } else if (r == Long.MIN_VALUE) {
                                    cancelled++;
                                }
                            }
                            Object poll;
                            if (len == cancelled) {
                                term = this.terminalEvent;
                                try {
                                    poll = q.poll();
                                } catch (Throwable ex) {
                                    Exceptions.throwIfFatal(ex);
                                    ((Subscription) this.f2569s.get()).cancel();
                                    term = NotificationLite.error(ex);
                                    this.terminalEvent = term;
                                    poll = null;
                                }
                                if (!checkTerminated(term, poll == null)) {
                                    if (this.sourceMode != 1) {
                                        ((Subscription) this.f2569s.get()).request(1);
                                    }
                                } else {
                                    return;
                                }
                            }
                            int d = 0;
                            while (((long) d) < maxRequested) {
                                term = this.terminalEvent;
                                try {
                                    poll = q.poll();
                                } catch (Throwable ex2) {
                                    Exceptions.throwIfFatal(ex2);
                                    ((Subscription) this.f2569s.get()).cancel();
                                    term = NotificationLite.error(ex2);
                                    this.terminalEvent = term;
                                    poll = null;
                                }
                                z = poll == null;
                                if (!checkTerminated(term, z)) {
                                    if (z) {
                                        break;
                                    }
                                    T value = NotificationLite.getValue(poll);
                                    for (InnerSubscriber<T> ip2 : ps) {
                                        if (ip2.get() > 0) {
                                            ip2.child.onNext(value);
                                            ip2.produced(1);
                                        }
                                    }
                                    d++;
                                } else {
                                    return;
                                }
                            }
                            if (d > 0 && this.sourceMode != 1) {
                                ((Subscription) this.f2569s.get()).request((long) d);
                            }
                            if (!(maxRequested == 0 || empty)) {
                            }
                        }
                        missed = addAndGet(-missed);
                        if (missed == 0) {
                            return;
                        }
                    } else {
                        return;
                    }
                }
            }
        }
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> source, int bufferSize) {
        AtomicReference<PublishSubscriber<T>> curr = new AtomicReference();
        return RxJavaPlugins.onAssembly(new FlowablePublish(new FlowablePublisher(curr, bufferSize), source, curr, bufferSize));
    }

    private FlowablePublish(Publisher<T> onSubscribe, Flowable<T> source, AtomicReference<PublishSubscriber<T>> current, int bufferSize) {
        this.onSubscribe = onSubscribe;
        this.source = source;
        this.current = current;
        this.bufferSize = bufferSize;
    }

    public Publisher<T> source() {
        return this.source;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.onSubscribe.subscribe(s);
    }

    public void connect(Consumer<? super Disposable> connection) {
        PublishSubscriber<T> ps;
        PublishSubscriber<T> u;
        boolean doConnect = true;
        do {
            ps = (PublishSubscriber) this.current.get();
            if (ps != null && !ps.isDisposed()) {
                break;
            }
            u = new PublishSubscriber(this.current, this.bufferSize);
        } while (!this.current.compareAndSet(ps, u));
        ps = u;
        if (ps.shouldConnect.get() || !ps.shouldConnect.compareAndSet(false, true)) {
            doConnect = false;
        }
        try {
            connection.accept(ps);
            if (doConnect) {
                this.source.subscribe(ps);
            }
        } catch (Throwable ex) {
            Exceptions.throwIfFatal(ex);
            RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(ex);
        }
    }
}
