package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Scheduler;
import io.reactivex.disposables.Disposable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.flowables.ConnectableFlowable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.fuseable.HasUpstreamPublisher;
import io.reactivex.internal.subscribers.SubscriberResourceWrapper;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Timed;
import java.util.ArrayList;
import java.util.Collection;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableReplay<T> extends ConnectableFlowable<T> implements HasUpstreamPublisher<T>, Disposable {
    static final Callable DEFAULT_UNBOUNDED_FACTORY = new DefaultUnboundedFactory();
    final Callable<? extends ReplayBuffer<T>> bufferFactory;
    final AtomicReference<ReplaySubscriber<T>> current;
    final Publisher<T> onSubscribe;
    final Flowable<T> source;

    interface ReplayBuffer<T> {
        void complete();

        void error(Throwable th);

        void next(T t);

        void replay(InnerSubscription<T> innerSubscription);
    }

    static class BoundedReplayBuffer<T> extends AtomicReference<Node> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 2346567790059478686L;
        long index;
        int size;
        Node tail;

        BoundedReplayBuffer() {
            Node n = new Node(null, 0);
            this.tail = n;
            set(n);
        }

        final void addLast(Node n) {
            this.tail.set(n);
            this.tail = n;
            this.size++;
        }

        final void removeFirst() {
            Node next = (Node) ((Node) get()).get();
            if (next == null) {
                throw new IllegalStateException("Empty list!");
            }
            this.size--;
            setFirst(next);
        }

        final void removeSome(int n) {
            Node head = (Node) get();
            while (n > 0) {
                head = (Node) head.get();
                n--;
                this.size--;
            }
            setFirst(head);
        }

        final void setFirst(Node n) {
            set(n);
        }

        public final void next(T value) {
            Object o = enterTransform(NotificationLite.next(value));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncate();
        }

        public final void error(Throwable e) {
            Object o = enterTransform(NotificationLite.error(e));
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncateFinal();
        }

        public final void complete() {
            Object o = enterTransform(NotificationLite.complete());
            long j = this.index + 1;
            this.index = j;
            addLast(new Node(o, j));
            truncateFinal();
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r13) {
            /*
            r12 = this;
            monitor-enter(r13);
            r9 = r13.emitting;	 Catch:{ all -> 0x0075 }
            if (r9 == 0) goto L_0x000a;
        L_0x0005:
            r9 = 1;
            r13.missed = r9;	 Catch:{ all -> 0x0075 }
            monitor-exit(r13);	 Catch:{ all -> 0x0075 }
        L_0x0009:
            return;
        L_0x000a:
            r9 = 1;
            r13.emitting = r9;	 Catch:{ all -> 0x0075 }
            monitor-exit(r13);	 Catch:{ all -> 0x0075 }
        L_0x000e:
            r9 = r13.isDisposed();
            if (r9 != 0) goto L_0x0009;
        L_0x0014:
            r6 = r13.get();
            r10 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r9 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
            if (r9 != 0) goto L_0x0078;
        L_0x0021:
            r5 = 1;
        L_0x0022:
            r0 = 0;
            r3 = r13.index();
            r3 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r3;
            if (r3 != 0) goto L_0x0039;
        L_0x002c:
            r3 = r12.getHead();
            r13.index = r3;
            r9 = r13.totalRequested;
            r10 = r3.index;
            io.reactivex.internal.util.BackpressureHelper.add(r9, r10);
        L_0x0039:
            r10 = 0;
            r9 = (r6 > r10 ? 1 : (r6 == r10 ? 0 : -1));
            if (r9 == 0) goto L_0x0088;
        L_0x003f:
            r8 = r3.get();
            r8 = (io.reactivex.internal.operators.flowable.FlowableReplay.Node) r8;
            if (r8 == 0) goto L_0x0088;
        L_0x0047:
            r9 = r8.value;
            r4 = r12.leaveTransform(r9);
            r9 = r13.child;	 Catch:{ Throwable -> 0x0059 }
            r9 = io.reactivex.internal.util.NotificationLite.accept(r4, r9);	 Catch:{ Throwable -> 0x0059 }
            if (r9 == 0) goto L_0x007a;
        L_0x0055:
            r9 = 0;
            r13.index = r9;	 Catch:{ Throwable -> 0x0059 }
            goto L_0x0009;
        L_0x0059:
            r2 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r2);
            r9 = 0;
            r13.index = r9;
            r13.dispose();
            r9 = io.reactivex.internal.util.NotificationLite.isError(r4);
            if (r9 != 0) goto L_0x0009;
        L_0x0069:
            r9 = io.reactivex.internal.util.NotificationLite.isComplete(r4);
            if (r9 != 0) goto L_0x0009;
        L_0x006f:
            r9 = r13.child;
            r9.onError(r2);
            goto L_0x0009;
        L_0x0075:
            r9 = move-exception;
            monitor-exit(r13);	 Catch:{ all -> 0x0075 }
            throw r9;
        L_0x0078:
            r5 = 0;
            goto L_0x0022;
        L_0x007a:
            r10 = 1;
            r0 = r0 + r10;
            r10 = 1;
            r6 = r6 - r10;
            r3 = r8;
            r9 = r13.isDisposed();
            if (r9 == 0) goto L_0x0039;
        L_0x0087:
            goto L_0x0009;
        L_0x0088:
            r10 = 0;
            r9 = (r0 > r10 ? 1 : (r0 == r10 ? 0 : -1));
            if (r9 == 0) goto L_0x0095;
        L_0x008e:
            r13.index = r3;
            if (r5 != 0) goto L_0x0095;
        L_0x0092:
            r13.produced(r0);
        L_0x0095:
            monitor-enter(r13);
            r9 = r13.missed;	 Catch:{ all -> 0x00a0 }
            if (r9 != 0) goto L_0x00a3;
        L_0x009a:
            r9 = 0;
            r13.emitting = r9;	 Catch:{ all -> 0x00a0 }
            monitor-exit(r13);	 Catch:{ all -> 0x00a0 }
            goto L_0x0009;
        L_0x00a0:
            r9 = move-exception;
            monitor-exit(r13);	 Catch:{ all -> 0x00a0 }
            throw r9;
        L_0x00a3:
            r9 = 0;
            r13.missed = r9;	 Catch:{ all -> 0x00a0 }
            monitor-exit(r13);	 Catch:{ all -> 0x00a0 }
            goto L_0x000e;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.BoundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
        }

        Object enterTransform(Object value) {
            return value;
        }

        Object leaveTransform(Object value) {
            return value;
        }

        void truncate() {
        }

        void truncateFinal() {
        }

        final void collect(Collection<? super T> output) {
            Node n = getHead();
            while (true) {
                Node next = (Node) n.get();
                if (next != null) {
                    Object v = leaveTransform(next.value);
                    if (!NotificationLite.isComplete(v) && !NotificationLite.isError(v)) {
                        output.add(NotificationLite.getValue(v));
                        n = next;
                    } else {
                        return;
                    }
                }
                return;
            }
        }

        boolean hasError() {
            return this.tail.value != null && NotificationLite.isError(leaveTransform(this.tail.value));
        }

        boolean hasCompleted() {
            return this.tail.value != null && NotificationLite.isComplete(leaveTransform(this.tail.value));
        }

        Node getHead() {
            return (Node) get();
        }
    }

    static final class ConnectableFlowableReplay<T> extends ConnectableFlowable<T> {
        private final ConnectableFlowable<T> co;
        private final Flowable<T> observable;

        ConnectableFlowableReplay(ConnectableFlowable<T> co, Flowable<T> observable) {
            this.co = co;
            this.observable = observable;
        }

        public void connect(Consumer<? super Disposable> connection) {
            this.co.connect(connection);
        }

        protected void subscribeActual(Subscriber<? super T> s) {
            this.observable.subscribe(s);
        }
    }

    static final class DefaultUnboundedFactory implements Callable<Object> {
        DefaultUnboundedFactory() {
        }

        public Object call() {
            return new UnboundedReplayBuffer(16);
        }
    }

    static final class InnerSubscription<T> extends AtomicLong implements Subscription, Disposable {
        static final long CANCELLED = Long.MIN_VALUE;
        private static final long serialVersionUID = -4453897557930727610L;
        final Subscriber<? super T> child;
        boolean emitting;
        Object index;
        boolean missed;
        final ReplaySubscriber<T> parent;
        final AtomicLong totalRequested = new AtomicLong();

        InnerSubscription(ReplaySubscriber<T> parent, Subscriber<? super T> child) {
            this.parent = parent;
            this.child = child;
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                long r;
                do {
                    r = get();
                    if (r != Long.MIN_VALUE) {
                        if (r >= 0 && n == 0) {
                            return;
                        }
                    } else {
                        return;
                    }
                } while (!compareAndSet(r, BackpressureHelper.addCap(r, n)));
                BackpressureHelper.add(this.totalRequested, n);
                this.parent.manageRequests();
                this.parent.buffer.replay(this);
            }
        }

        public long produced(long n) {
            return BackpressureHelper.producedCancel(this, n);
        }

        public boolean isDisposed() {
            return get() == Long.MIN_VALUE;
        }

        public void cancel() {
            dispose();
        }

        public void dispose() {
            if (getAndSet(Long.MIN_VALUE) != Long.MIN_VALUE) {
                this.parent.remove(this);
                this.parent.manageRequests();
            }
        }

        <U> U index() {
            return this.index;
        }
    }

    static final class MultiCastPublisher<R, U> implements Publisher<R> {
        private final Callable<? extends ConnectableFlowable<U>> connectableFactory;
        private final Function<? super Flowable<U>, ? extends Publisher<R>> selector;

        final class DisposableConsumer implements Consumer<Disposable> {
            private final SubscriberResourceWrapper<R> srw;

            DisposableConsumer(SubscriberResourceWrapper<R> srw) {
                this.srw = srw;
            }

            public void accept(Disposable r) {
                this.srw.setResource(r);
            }
        }

        MultiCastPublisher(Callable<? extends ConnectableFlowable<U>> connectableFactory, Function<? super Flowable<U>, ? extends Publisher<R>> selector) {
            this.connectableFactory = connectableFactory;
            this.selector = selector;
        }

        public void subscribe(Subscriber<? super R> child) {
            try {
                ConnectableFlowable<U> co = (ConnectableFlowable) ObjectHelper.requireNonNull(this.connectableFactory.call(), "The connectableFactory returned null");
                try {
                    Publisher<R> observable = (Publisher) ObjectHelper.requireNonNull(this.selector.apply(co), "The selector returned a null Publisher");
                    SubscriberResourceWrapper<R> srw = new SubscriberResourceWrapper(child);
                    observable.subscribe(srw);
                    co.connect(new DisposableConsumer(srw));
                } catch (Throwable e) {
                    Exceptions.throwIfFatal(e);
                    EmptySubscription.error(e, child);
                }
            } catch (Throwable e2) {
                Exceptions.throwIfFatal(e2);
                EmptySubscription.error(e2, child);
            }
        }
    }

    static final class Node extends AtomicReference<Node> {
        private static final long serialVersionUID = 245354315435971818L;
        final long index;
        final Object value;

        Node(Object value, long index) {
            this.value = value;
            this.index = index;
        }
    }

    static final class ReplayBufferTask<T> implements Callable<ReplayBuffer<T>> {
        private final int bufferSize;

        ReplayBufferTask(int bufferSize) {
            this.bufferSize = bufferSize;
        }

        public ReplayBuffer<T> call() {
            return new SizeBoundReplayBuffer(this.bufferSize);
        }
    }

    static final class ReplayPublisher<T> implements Publisher<T> {
        private final Callable<? extends ReplayBuffer<T>> bufferFactory;
        private final AtomicReference<ReplaySubscriber<T>> curr;

        ReplayPublisher(AtomicReference<ReplaySubscriber<T>> curr, Callable<? extends ReplayBuffer<T>> bufferFactory) {
            this.curr = curr;
            this.bufferFactory = bufferFactory;
        }

        public void subscribe(Subscriber<? super T> child) {
            ReplaySubscriber<T> u;
            ReplaySubscriber<T> r;
            do {
                r = (ReplaySubscriber) this.curr.get();
                if (r != null) {
                    break;
                }
                try {
                    u = new ReplaySubscriber((ReplayBuffer) this.bufferFactory.call());
                } catch (Throwable ex) {
                    Exceptions.throwIfFatal(ex);
                    RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(ex);
                }
            } while (!this.curr.compareAndSet(null, u));
            r = u;
            InnerSubscription<T> inner = new InnerSubscription(r, child);
            child.onSubscribe(inner);
            r.add(inner);
            if (inner.isDisposed()) {
                r.remove(inner);
                return;
            }
            r.manageRequests();
            r.buffer.replay(inner);
        }
    }

    static final class ReplaySubscriber<T> extends AtomicReference<Subscription> implements FlowableSubscriber<T>, Disposable {
        static final InnerSubscription[] EMPTY = new InnerSubscription[0];
        static final InnerSubscription[] TERMINATED = new InnerSubscription[0];
        private static final long serialVersionUID = 7224554242710036740L;
        final ReplayBuffer<T> buffer;
        boolean done;
        final AtomicInteger management = new AtomicInteger();
        long maxChildRequested;
        long maxUpstreamRequested;
        final AtomicBoolean shouldConnect = new AtomicBoolean();
        final AtomicReference<InnerSubscription<T>[]> subscribers = new AtomicReference(EMPTY);

        ReplaySubscriber(ReplayBuffer<T> buffer) {
            this.buffer = buffer;
        }

        public boolean isDisposed() {
            return this.subscribers.get() == TERMINATED;
        }

        public void dispose() {
            this.subscribers.set(TERMINATED);
            SubscriptionHelper.cancel(this);
        }

        boolean add(InnerSubscription<T> producer) {
            if (producer == null) {
                throw new NullPointerException();
            }
            InnerSubscription[] c;
            InnerSubscription<T>[] u;
            do {
                c = (InnerSubscription[]) this.subscribers.get();
                if (c == TERMINATED) {
                    return false;
                }
                int len = c.length;
                u = new InnerSubscription[(len + 1)];
                System.arraycopy(c, 0, u, 0, len);
                u[len] = producer;
            } while (!this.subscribers.compareAndSet(c, u));
            return true;
        }

        void remove(InnerSubscription<T> p) {
            InnerSubscription[] c;
            InnerSubscription<T>[] u;
            do {
                c = (InnerSubscription[]) this.subscribers.get();
                int len = c.length;
                if (len != 0) {
                    int j = -1;
                    for (int i = 0; i < len; i++) {
                        if (c[i].equals(p)) {
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
                        u = new InnerSubscription[(len - 1)];
                        System.arraycopy(c, 0, u, 0, j);
                        System.arraycopy(c, j + 1, u, j, (len - j) - 1);
                    }
                } else {
                    return;
                }
            } while (!this.subscribers.compareAndSet(c, u));
        }

        public void onSubscribe(Subscription p) {
            if (SubscriptionHelper.setOnce(this, p)) {
                manageRequests();
                for (InnerSubscription<T> rp : (InnerSubscription[]) this.subscribers.get()) {
                    this.buffer.replay(rp);
                }
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                this.buffer.next(t);
                for (InnerSubscription<T> rp : (InnerSubscription[]) this.subscribers.get()) {
                    this.buffer.replay(rp);
                }
            }
        }

        public void onError(Throwable e) {
            if (this.done) {
                RxJavaPlugins.onError(e);
                return;
            }
            this.done = true;
            this.buffer.error(e);
            for (InnerSubscription<T> rp : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                this.buffer.replay(rp);
            }
        }

        public void onComplete() {
            if (!this.done) {
                this.done = true;
                this.buffer.complete();
                for (InnerSubscription<T> rp : (InnerSubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                    this.buffer.replay(rp);
                }
            }
        }

        void manageRequests() {
            if (this.management.getAndIncrement() == 0) {
                int missed = 1;
                while (!isDisposed()) {
                    InnerSubscription[] a = (InnerSubscription[]) this.subscribers.get();
                    long ri = this.maxChildRequested;
                    long maxTotalRequests = ri;
                    for (InnerSubscription<T> rp : a) {
                        maxTotalRequests = Math.max(maxTotalRequests, rp.totalRequested.get());
                    }
                    long ur = this.maxUpstreamRequested;
                    Subscription p = (Subscription) get();
                    long diff = maxTotalRequests - ri;
                    if (diff != 0) {
                        this.maxChildRequested = maxTotalRequests;
                        if (p == null) {
                            long u = ur + diff;
                            if (u < 0) {
                                u = Long.MAX_VALUE;
                            }
                            this.maxUpstreamRequested = u;
                        } else if (ur != 0) {
                            this.maxUpstreamRequested = 0;
                            p.request(ur + diff);
                        } else {
                            p.request(diff);
                        }
                    } else if (!(ur == 0 || p == null)) {
                        this.maxUpstreamRequested = 0;
                        p.request(ur);
                    }
                    missed = this.management.addAndGet(-missed);
                    if (missed == 0) {
                        return;
                    }
                }
            }
        }
    }

    static final class ScheduledReplayBufferTask<T> implements Callable<ReplayBuffer<T>> {
        private final int bufferSize;
        private final long maxAge;
        private final Scheduler scheduler;
        private final TimeUnit unit;

        ScheduledReplayBufferTask(int bufferSize, long maxAge, TimeUnit unit, Scheduler scheduler) {
            this.bufferSize = bufferSize;
            this.maxAge = maxAge;
            this.unit = unit;
            this.scheduler = scheduler;
        }

        public ReplayBuffer<T> call() {
            return new SizeAndTimeBoundReplayBuffer(this.bufferSize, this.maxAge, this.unit, this.scheduler);
        }
    }

    static final class SizeAndTimeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = 3457957419649567404L;
        final int limit;
        final long maxAge;
        final Scheduler scheduler;
        final TimeUnit unit;

        SizeAndTimeBoundReplayBuffer(int limit, long maxAge, TimeUnit unit, Scheduler scheduler) {
            this.scheduler = scheduler;
            this.limit = limit;
            this.maxAge = maxAge;
            this.unit = unit;
        }

        Object enterTransform(Object value) {
            return new Timed(value, this.scheduler.now(this.unit), this.unit);
        }

        Object leaveTransform(Object value) {
            return ((Timed) value).value();
        }

        void truncate() {
            long timeLimit = this.scheduler.now(this.unit) - this.maxAge;
            Node prev = (Node) get();
            Node next = (Node) prev.get();
            int e = 0;
            while (next != null) {
                if (this.size <= this.limit) {
                    if (next.value.time() > timeLimit) {
                        break;
                    }
                    e++;
                    this.size--;
                    prev = next;
                    next = (Node) next.get();
                } else {
                    e++;
                    this.size--;
                    prev = next;
                    next = (Node) next.get();
                }
            }
            if (e != 0) {
                setFirst(prev);
            }
        }

        void truncateFinal() {
            long timeLimit = this.scheduler.now(this.unit) - this.maxAge;
            Node prev = (Node) get();
            Node next = (Node) prev.get();
            int e = 0;
            while (next != null && this.size > 1 && next.value.time() <= timeLimit) {
                e++;
                this.size--;
                prev = next;
                next = (Node) next.get();
            }
            if (e != 0) {
                setFirst(prev);
            }
        }

        Node getHead() {
            long timeLimit = this.scheduler.now(this.unit) - this.maxAge;
            Node prev = (Node) get();
            for (Node next = (Node) prev.get(); next != null; next = (Node) next.get()) {
                Timed<?> v = next.value;
                if (NotificationLite.isComplete(v.value()) || NotificationLite.isError(v.value()) || v.time() > timeLimit) {
                    break;
                }
                prev = next;
            }
            return prev;
        }
    }

    static final class SizeBoundReplayBuffer<T> extends BoundedReplayBuffer<T> {
        private static final long serialVersionUID = -5898283885385201806L;
        final int limit;

        SizeBoundReplayBuffer(int limit) {
            this.limit = limit;
        }

        void truncate() {
            if (this.size > this.limit) {
                removeFirst();
            }
        }
    }

    static final class UnboundedReplayBuffer<T> extends ArrayList<Object> implements ReplayBuffer<T> {
        private static final long serialVersionUID = 7063189396499112664L;
        volatile int size;

        UnboundedReplayBuffer(int capacityHint) {
            super(capacityHint);
        }

        public void next(T value) {
            add(NotificationLite.next(value));
            this.size++;
        }

        public void error(Throwable e) {
            add(NotificationLite.error(e));
            this.size++;
        }

        public void complete() {
            add(NotificationLite.complete());
            this.size++;
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void replay(io.reactivex.internal.operators.flowable.FlowableReplay.InnerSubscription<T> r15) {
            /*
            r14 = this;
            monitor-enter(r15);
            r12 = r15.emitting;	 Catch:{ all -> 0x004c }
            if (r12 == 0) goto L_0x000a;
        L_0x0005:
            r12 = 1;
            r15.missed = r12;	 Catch:{ all -> 0x004c }
            monitor-exit(r15);	 Catch:{ all -> 0x004c }
        L_0x0009:
            return;
        L_0x000a:
            r12 = 1;
            r15.emitting = r12;	 Catch:{ all -> 0x004c }
            monitor-exit(r15);	 Catch:{ all -> 0x004c }
            r0 = r15.child;
        L_0x0010:
            r12 = r15.isDisposed();
            if (r12 != 0) goto L_0x0009;
        L_0x0016:
            r7 = r14.size;
            r2 = r15.index();
            r2 = (java.lang.Integer) r2;
            if (r2 == 0) goto L_0x004f;
        L_0x0020:
            r1 = r2.intValue();
        L_0x0024:
            r8 = r15.get();
            r10 = r8;
            r4 = 0;
        L_0x002b:
            r12 = 0;
            r12 = (r8 > r12 ? 1 : (r8 == r12 ? 0 : -1));
            if (r12 == 0) goto L_0x0068;
        L_0x0031:
            if (r1 >= r7) goto L_0x0068;
        L_0x0033:
            r6 = r14.get(r1);
            r12 = io.reactivex.internal.util.NotificationLite.accept(r6, r0);	 Catch:{ Throwable -> 0x0051 }
            if (r12 != 0) goto L_0x0009;
        L_0x003d:
            r12 = r15.isDisposed();
            if (r12 != 0) goto L_0x0009;
        L_0x0043:
            r1 = r1 + 1;
            r12 = 1;
            r8 = r8 - r12;
            r12 = 1;
            r4 = r4 + r12;
            goto L_0x002b;
        L_0x004c:
            r12 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x004c }
            throw r12;
        L_0x004f:
            r1 = 0;
            goto L_0x0024;
        L_0x0051:
            r3 = move-exception;
            io.reactivex.exceptions.Exceptions.throwIfFatal(r3);
            r15.dispose();
            r12 = io.reactivex.internal.util.NotificationLite.isError(r6);
            if (r12 != 0) goto L_0x0009;
        L_0x005e:
            r12 = io.reactivex.internal.util.NotificationLite.isComplete(r6);
            if (r12 != 0) goto L_0x0009;
        L_0x0064:
            r0.onError(r3);
            goto L_0x0009;
        L_0x0068:
            r12 = 0;
            r12 = (r4 > r12 ? 1 : (r4 == r12 ? 0 : -1));
            if (r12 == 0) goto L_0x0080;
        L_0x006e:
            r12 = java.lang.Integer.valueOf(r1);
            r15.index = r12;
            r12 = 9223372036854775807; // 0x7fffffffffffffff float:NaN double:NaN;
            r12 = (r10 > r12 ? 1 : (r10 == r12 ? 0 : -1));
            if (r12 == 0) goto L_0x0080;
        L_0x007d:
            r15.produced(r4);
        L_0x0080:
            monitor-enter(r15);
            r12 = r15.missed;	 Catch:{ all -> 0x008a }
            if (r12 != 0) goto L_0x008d;
        L_0x0085:
            r12 = 0;
            r15.emitting = r12;	 Catch:{ all -> 0x008a }
            monitor-exit(r15);	 Catch:{ all -> 0x008a }
            goto L_0x0009;
        L_0x008a:
            r12 = move-exception;
            monitor-exit(r15);	 Catch:{ all -> 0x008a }
            throw r12;
        L_0x008d:
            r12 = 0;
            r15.missed = r12;	 Catch:{ all -> 0x008a }
            monitor-exit(r15);	 Catch:{ all -> 0x008a }
            goto L_0x0010;
            */
            throw new UnsupportedOperationException("Method not decompiled: io.reactivex.internal.operators.flowable.FlowableReplay.UnboundedReplayBuffer.replay(io.reactivex.internal.operators.flowable.FlowableReplay$InnerSubscription):void");
        }
    }

    public static <U, R> Flowable<R> multicastSelector(Callable<? extends ConnectableFlowable<U>> connectableFactory, Function<? super Flowable<U>, ? extends Publisher<R>> selector) {
        return Flowable.unsafeCreate(new MultiCastPublisher(connectableFactory, selector));
    }

    public static <T> ConnectableFlowable<T> observeOn(ConnectableFlowable<T> co, Scheduler scheduler) {
        return RxJavaPlugins.onAssembly(new ConnectableFlowableReplay(co, co.observeOn(scheduler)));
    }

    public static <T> ConnectableFlowable<T> createFrom(Flowable<? extends T> source) {
        return create((Flowable) source, DEFAULT_UNBOUNDED_FACTORY);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> source, int bufferSize) {
        if (bufferSize == Integer.MAX_VALUE) {
            return createFrom(source);
        }
        return create((Flowable) source, new ReplayBufferTask(bufferSize));
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> source, long maxAge, TimeUnit unit, Scheduler scheduler) {
        return create(source, maxAge, unit, scheduler, Integer.MAX_VALUE);
    }

    public static <T> ConnectableFlowable<T> create(Flowable<T> source, long maxAge, TimeUnit unit, Scheduler scheduler, int bufferSize) {
        return create((Flowable) source, new ScheduledReplayBufferTask(bufferSize, maxAge, unit, scheduler));
    }

    static <T> ConnectableFlowable<T> create(Flowable<T> source, Callable<? extends ReplayBuffer<T>> bufferFactory) {
        AtomicReference<ReplaySubscriber<T>> curr = new AtomicReference();
        return RxJavaPlugins.onAssembly(new FlowableReplay(new ReplayPublisher(curr, bufferFactory), source, curr, bufferFactory));
    }

    private FlowableReplay(Publisher<T> onSubscribe, Flowable<T> source, AtomicReference<ReplaySubscriber<T>> current, Callable<? extends ReplayBuffer<T>> bufferFactory) {
        this.onSubscribe = onSubscribe;
        this.source = source;
        this.current = current;
        this.bufferFactory = bufferFactory;
    }

    public Publisher<T> source() {
        return this.source;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        this.onSubscribe.subscribe(s);
    }

    public void dispose() {
        this.current.lazySet(null);
    }

    public boolean isDisposed() {
        Disposable d = (Disposable) this.current.get();
        return d == null || d.isDisposed();
    }

    public void connect(Consumer<? super Disposable> connection) {
        ReplaySubscriber<T> ps;
        ReplaySubscriber<T> u;
        boolean doConnect;
        do {
            ps = (ReplaySubscriber) this.current.get();
            if (ps != null && !ps.isDisposed()) {
                break;
            }
            try {
                u = new ReplaySubscriber((ReplayBuffer) this.bufferFactory.call());
            } catch (Throwable ex) {
                Exceptions.throwIfFatal(ex);
                RuntimeException wrapOrThrow = ExceptionHelper.wrapOrThrow(ex);
            }
        } while (!this.current.compareAndSet(ps, u));
        ps = u;
        if (ps.shouldConnect.get() || !ps.shouldConnect.compareAndSet(false, true)) {
            doConnect = false;
        } else {
            doConnect = true;
        }
        try {
            connection.accept(ps);
            if (doConnect) {
                this.source.subscribe(ps);
            }
        } catch (Throwable ex2) {
            if (doConnect) {
                ps.shouldConnect.compareAndSet(true, false);
            }
            Exceptions.throwIfFatal(ex2);
            wrapOrThrow = ExceptionHelper.wrapOrThrow(ex2);
        }
    }
}
