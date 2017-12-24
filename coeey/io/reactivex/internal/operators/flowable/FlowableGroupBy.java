package io.reactivex.internal.operators.flowable;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.annotations.Nullable;
import io.reactivex.exceptions.Exceptions;
import io.reactivex.flowables.GroupedFlowable;
import io.reactivex.functions.Function;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.queue.SpscLinkedArrayQueue;
import io.reactivex.internal.subscriptions.BasicIntQueueSubscription;
import io.reactivex.internal.subscriptions.EmptySubscription;
import io.reactivex.internal.subscriptions.SubscriptionHelper;
import io.reactivex.internal.util.BackpressureHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class FlowableGroupBy<T, K, V> extends AbstractFlowableWithUpstream<T, GroupedFlowable<K, V>> {
    final int bufferSize;
    final boolean delayError;
    final Function<? super T, ? extends K> keySelector;
    final Function<? super T, ? extends V> valueSelector;

    public static final class GroupBySubscriber<T, K, V> extends BasicIntQueueSubscription<GroupedFlowable<K, V>> implements FlowableSubscriber<T> {
        static final Object NULL_KEY = new Object();
        private static final long serialVersionUID = -3688291656102519502L;
        final Subscriber<? super GroupedFlowable<K, V>> actual;
        final int bufferSize;
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final AtomicInteger groupCount = new AtomicInteger(1);
        final Map<Object, GroupedUnicast<K, V>> groups;
        final Function<? super T, ? extends K> keySelector;
        boolean outputFused;
        final SpscLinkedArrayQueue<GroupedFlowable<K, V>> queue;
        final AtomicLong requested = new AtomicLong();
        Subscription f2555s;
        final Function<? super T, ? extends V> valueSelector;

        public GroupBySubscriber(Subscriber<? super GroupedFlowable<K, V>> actual, Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector, int bufferSize, boolean delayError) {
            this.actual = actual;
            this.keySelector = keySelector;
            this.valueSelector = valueSelector;
            this.bufferSize = bufferSize;
            this.delayError = delayError;
            this.groups = new ConcurrentHashMap();
            this.queue = new SpscLinkedArrayQueue(bufferSize);
        }

        public void onSubscribe(Subscription s) {
            if (SubscriptionHelper.validate(this.f2555s, s)) {
                this.f2555s = s;
                this.actual.onSubscribe(this);
                s.request((long) this.bufferSize);
            }
        }

        public void onNext(T t) {
            if (!this.done) {
                SpscLinkedArrayQueue<GroupedFlowable<K, V>> q = this.queue;
                try {
                    K key = this.keySelector.apply(t);
                    boolean newGroup = false;
                    Object mapKey = key != null ? key : NULL_KEY;
                    GroupedUnicast<K, V> group = (GroupedUnicast) this.groups.get(mapKey);
                    if (group == null) {
                        if (!this.cancelled.get()) {
                            group = GroupedUnicast.createWith(key, this.bufferSize, this, this.delayError);
                            this.groups.put(mapKey, group);
                            this.groupCount.getAndIncrement();
                            newGroup = true;
                        } else {
                            return;
                        }
                    }
                    try {
                        group.onNext(ObjectHelper.requireNonNull(this.valueSelector.apply(t), "The valueSelector returned null"));
                        if (newGroup) {
                            q.offer(group);
                            drain();
                        }
                    } catch (Throwable ex) {
                        Exceptions.throwIfFatal(ex);
                        this.f2555s.cancel();
                        onError(ex);
                    }
                } catch (Throwable ex2) {
                    Exceptions.throwIfFatal(ex2);
                    this.f2555s.cancel();
                    onError(ex2);
                }
            }
        }

        public void onError(Throwable t) {
            if (this.done) {
                RxJavaPlugins.onError(t);
                return;
            }
            for (GroupedUnicast<K, V> g : this.groups.values()) {
                g.onError(t);
            }
            this.groups.clear();
            this.error = t;
            this.done = true;
            drain();
        }

        public void onComplete() {
            if (!this.done) {
                for (GroupedUnicast<K, V> g : this.groups.values()) {
                    g.onComplete();
                }
                this.groups.clear();
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
            if (this.cancelled.compareAndSet(false, true) && this.groupCount.decrementAndGet() == 0) {
                this.f2555s.cancel();
            }
        }

        public void cancel(K key) {
            this.groups.remove(key != null ? key : NULL_KEY);
            if (this.groupCount.decrementAndGet() == 0) {
                this.f2555s.cancel();
                if (getAndIncrement() == 0) {
                    this.queue.clear();
                }
            }
        }

        void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainFused();
                } else {
                    drainNormal();
                }
            }
        }

        void drainFused() {
            int missed = 1;
            SpscLinkedArrayQueue<GroupedFlowable<K, V>> q = this.queue;
            Subscriber<? super GroupedFlowable<K, V>> a = this.actual;
            while (!this.cancelled.get()) {
                Throwable ex;
                boolean d = this.done;
                if (d && !this.delayError) {
                    ex = this.error;
                    if (ex != null) {
                        q.clear();
                        a.onError(ex);
                        return;
                    }
                }
                a.onNext(null);
                if (d) {
                    ex = this.error;
                    if (ex != null) {
                        a.onError(ex);
                        return;
                    } else {
                        a.onComplete();
                        return;
                    }
                }
                missed = addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
            }
            q.clear();
        }

        void drainNormal() {
            int missed = 1;
            SpscLinkedArrayQueue<GroupedFlowable<K, V>> q = this.queue;
            Subscriber<? super GroupedFlowable<K, V>> a = this.actual;
            do {
                long r = this.requested.get();
                long e = 0;
                while (e != r) {
                    boolean d = this.done;
                    GroupedFlowable<K, V> t = (GroupedFlowable) q.poll();
                    boolean empty = t == null;
                    if (!checkTerminated(d, empty, a, q)) {
                        if (empty) {
                            break;
                        }
                        a.onNext(t);
                        e++;
                    } else {
                        return;
                    }
                }
                if (e != r || !checkTerminated(this.done, q.isEmpty(), a, q)) {
                    if (e != 0) {
                        if (r != Long.MAX_VALUE) {
                            this.requested.addAndGet(-e);
                        }
                        this.f2555s.request(e);
                    }
                    missed = addAndGet(-missed);
                } else {
                    return;
                }
            } while (missed != 0);
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<?> a, SpscLinkedArrayQueue<?> q) {
            if (this.cancelled.get()) {
                q.clear();
                return true;
            }
            Throwable ex;
            if (this.delayError) {
                if (d && empty) {
                    ex = this.error;
                    if (ex != null) {
                        a.onError(ex);
                        return true;
                    }
                    a.onComplete();
                    return true;
                }
            } else if (d) {
                ex = this.error;
                if (ex != null) {
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

        public int requestFusion(int mode) {
            if ((mode & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Nullable
        public GroupedFlowable<K, V> poll() {
            return (GroupedFlowable) this.queue.poll();
        }

        public void clear() {
            this.queue.clear();
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }
    }

    static final class GroupedUnicast<K, T> extends GroupedFlowable<K, T> {
        final State<T, K> state;

        public static <T, K> GroupedUnicast<K, T> createWith(K key, int bufferSize, GroupBySubscriber<?, K, T> parent, boolean delayError) {
            return new GroupedUnicast(key, new State(bufferSize, parent, key, delayError));
        }

        protected GroupedUnicast(K key, State<T, K> state) {
            super(key);
            this.state = state;
        }

        protected void subscribeActual(Subscriber<? super T> s) {
            this.state.subscribe(s);
        }

        public void onNext(T t) {
            this.state.onNext(t);
        }

        public void onError(Throwable e) {
            this.state.onError(e);
        }

        public void onComplete() {
            this.state.onComplete();
        }
    }

    static final class State<T, K> extends BasicIntQueueSubscription<T> implements Publisher<T> {
        private static final long serialVersionUID = -3852313036005250360L;
        final AtomicReference<Subscriber<? super T>> actual = new AtomicReference();
        final AtomicBoolean cancelled = new AtomicBoolean();
        final boolean delayError;
        volatile boolean done;
        Throwable error;
        final K key;
        final AtomicBoolean once = new AtomicBoolean();
        boolean outputFused;
        final GroupBySubscriber<?, K, T> parent;
        int produced;
        final SpscLinkedArrayQueue<T> queue;
        final AtomicLong requested = new AtomicLong();

        State(int bufferSize, GroupBySubscriber<?, K, T> parent, K key, boolean delayError) {
            this.queue = new SpscLinkedArrayQueue(bufferSize);
            this.parent = parent;
            this.key = key;
            this.delayError = delayError;
        }

        public void request(long n) {
            if (SubscriptionHelper.validate(n)) {
                BackpressureHelper.add(this.requested, n);
                drain();
            }
        }

        public void cancel() {
            if (this.cancelled.compareAndSet(false, true)) {
                this.parent.cancel(this.key);
            }
        }

        public void subscribe(Subscriber<? super T> s) {
            if (this.once.compareAndSet(false, true)) {
                s.onSubscribe(this);
                this.actual.lazySet(s);
                drain();
                return;
            }
            EmptySubscription.error(new IllegalStateException("Only one Subscriber allowed!"), s);
        }

        public void onNext(T t) {
            this.queue.offer(t);
            drain();
        }

        public void onError(Throwable e) {
            this.error = e;
            this.done = true;
            drain();
        }

        public void onComplete() {
            this.done = true;
            drain();
        }

        void drain() {
            if (getAndIncrement() == 0) {
                if (this.outputFused) {
                    drainFused();
                } else {
                    drainNormal();
                }
            }
        }

        void drainFused() {
            Throwable ex;
            int missed = 1;
            SpscLinkedArrayQueue<T> q = this.queue;
            Subscriber<? super T> a = (Subscriber) this.actual.get();
            while (true) {
                if (a != null) {
                    if (!this.cancelled.get()) {
                        boolean d = this.done;
                        if (d && !this.delayError) {
                            ex = this.error;
                            if (ex != null) {
                                q.clear();
                                a.onError(ex);
                                return;
                            }
                        }
                        a.onNext(null);
                        if (d) {
                            break;
                        }
                    }
                    q.clear();
                    return;
                }
                missed = addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
                if (a == null) {
                    a = (Subscriber) this.actual.get();
                }
            }
            ex = this.error;
            if (ex != null) {
                a.onError(ex);
            } else {
                a.onComplete();
            }
        }

        void drainNormal() {
            int missed = 1;
            SpscLinkedArrayQueue<T> q = this.queue;
            boolean delayError = this.delayError;
            Subscriber<? super T> a = (Subscriber) this.actual.get();
            while (true) {
                if (a != null) {
                    long r = this.requested.get();
                    long e = 0;
                    while (e != r) {
                        boolean d = this.done;
                        T v = q.poll();
                        boolean empty = v == null;
                        if (!checkTerminated(d, empty, a, delayError)) {
                            if (empty) {
                                break;
                            }
                            a.onNext(v);
                            e++;
                        } else {
                            return;
                        }
                    }
                    if (e != r || !checkTerminated(this.done, q.isEmpty(), a, delayError)) {
                        if (e != 0) {
                            if (r != Long.MAX_VALUE) {
                                this.requested.addAndGet(-e);
                            }
                            this.parent.f2555s.request(e);
                        }
                    } else {
                        return;
                    }
                }
                missed = addAndGet(-missed);
                if (missed == 0) {
                    return;
                }
                if (a == null) {
                    a = (Subscriber) this.actual.get();
                }
            }
        }

        boolean checkTerminated(boolean d, boolean empty, Subscriber<? super T> a, boolean delayError) {
            if (this.cancelled.get()) {
                this.queue.clear();
                return true;
            }
            if (d) {
                Throwable e;
                if (!delayError) {
                    e = this.error;
                    if (e != null) {
                        this.queue.clear();
                        a.onError(e);
                        return true;
                    } else if (empty) {
                        a.onComplete();
                        return true;
                    }
                } else if (empty) {
                    e = this.error;
                    if (e != null) {
                        a.onError(e);
                        return true;
                    }
                    a.onComplete();
                    return true;
                }
            }
            return false;
        }

        public int requestFusion(int mode) {
            if ((mode & 2) == 0) {
                return 0;
            }
            this.outputFused = true;
            return 2;
        }

        @Nullable
        public T poll() {
            T v = this.queue.poll();
            if (v != null) {
                this.produced++;
                return v;
            }
            int p = this.produced;
            if (p != 0) {
                this.produced = 0;
                this.parent.f2555s.request((long) p);
            }
            return null;
        }

        public boolean isEmpty() {
            return this.queue.isEmpty();
        }

        public void clear() {
            this.queue.clear();
        }
    }

    public FlowableGroupBy(Flowable<T> source, Function<? super T, ? extends K> keySelector, Function<? super T, ? extends V> valueSelector, int bufferSize, boolean delayError) {
        super(source);
        this.keySelector = keySelector;
        this.valueSelector = valueSelector;
        this.bufferSize = bufferSize;
        this.delayError = delayError;
    }

    protected void subscribeActual(Subscriber<? super GroupedFlowable<K, V>> s) {
        this.source.subscribe(new GroupBySubscriber(s, this.keySelector, this.valueSelector, this.bufferSize, this.delayError));
    }
}
