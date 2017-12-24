package io.reactivex.processors;

import io.reactivex.Scheduler;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public final class ReplayProcessor<T> extends FlowableProcessor<T> {
    static final ReplaySubscription[] EMPTY = new ReplaySubscription[0];
    private static final Object[] EMPTY_ARRAY = new Object[0];
    static final ReplaySubscription[] TERMINATED = new ReplaySubscription[0];
    final ReplayBuffer<T> buffer;
    boolean done;
    final AtomicReference<ReplaySubscription<T>[]> subscribers = new AtomicReference(EMPTY);

    @CheckReturnValue
    public static <T> ReplayProcessor<T> create() {
        return new ReplayProcessor(new UnboundedReplayBuffer(16));
    }

    @CheckReturnValue
    public static <T> ReplayProcessor<T> create(int capacityHint) {
        return new ReplayProcessor(new UnboundedReplayBuffer(capacityHint));
    }

    @CheckReturnValue
    public static <T> ReplayProcessor<T> createWithSize(int maxSize) {
        return new ReplayProcessor(new SizeBoundReplayBuffer(maxSize));
    }

    static <T> ReplayProcessor<T> createUnbounded() {
        return new ReplayProcessor(new SizeBoundReplayBuffer(Integer.MAX_VALUE));
    }

    @CheckReturnValue
    public static <T> ReplayProcessor<T> createWithTime(long maxAge, TimeUnit unit, Scheduler scheduler) {
        return new ReplayProcessor(new SizeAndTimeBoundReplayBuffer(Integer.MAX_VALUE, maxAge, unit, scheduler));
    }

    @CheckReturnValue
    public static <T> ReplayProcessor<T> createWithTimeAndSize(long maxAge, TimeUnit unit, Scheduler scheduler, int maxSize) {
        return new ReplayProcessor(new SizeAndTimeBoundReplayBuffer(maxSize, maxAge, unit, scheduler));
    }

    ReplayProcessor(ReplayBuffer<T> buffer) {
        this.buffer = buffer;
    }

    protected void subscribeActual(Subscriber<? super T> s) {
        ReplaySubscription<T> rs = new ReplaySubscription(s, this);
        s.onSubscribe(rs);
        if (add(rs) && rs.cancelled) {
            remove(rs);
        } else {
            this.buffer.replay(rs);
        }
    }

    public void onSubscribe(Subscription s) {
        if (this.done) {
            s.cancel();
        } else {
            s.request(Long.MAX_VALUE);
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (!this.done) {
            ReplayBuffer<T> b = this.buffer;
            b.next(t);
            for (ReplaySubscription<T> rs : (ReplaySubscription[]) this.subscribers.get()) {
                b.replay(rs);
            }
        }
    }

    public void onError(Throwable t) {
        if (t == null) {
            t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.done) {
            RxJavaPlugins.onError(t);
            return;
        }
        this.done = true;
        ReplayBuffer<T> b = this.buffer;
        b.error(t);
        for (ReplaySubscription<T> rs : (ReplaySubscription[]) this.subscribers.getAndSet(TERMINATED)) {
            b.replay(rs);
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            ReplayBuffer<T> b = this.buffer;
            b.complete();
            for (ReplaySubscription<T> rs : (ReplaySubscription[]) this.subscribers.getAndSet(TERMINATED)) {
                b.replay(rs);
            }
        }
    }

    public boolean hasSubscribers() {
        return ((ReplaySubscription[]) this.subscribers.get()).length != 0;
    }

    int subscriberCount() {
        return ((ReplaySubscription[]) this.subscribers.get()).length;
    }

    public Throwable getThrowable() {
        ReplayBuffer<T> b = this.buffer;
        if (b.isDone()) {
            return b.getError();
        }
        return null;
    }

    public T getValue() {
        return this.buffer.getValue();
    }

    public Object[] getValues() {
        T[] b = getValues((Object[]) EMPTY_ARRAY);
        if (b == EMPTY_ARRAY) {
            return new Object[0];
        }
        return b;
    }

    public T[] getValues(T[] array) {
        return this.buffer.getValues(array);
    }

    public boolean hasComplete() {
        ReplayBuffer<T> b = this.buffer;
        return b.isDone() && b.getError() == null;
    }

    public boolean hasThrowable() {
        ReplayBuffer<T> b = this.buffer;
        return b.isDone() && b.getError() != null;
    }

    public boolean hasValue() {
        return this.buffer.size() != 0;
    }

    int size() {
        return this.buffer.size();
    }

    boolean add(ReplaySubscription<T> rs) {
        ReplaySubscription[] a;
        ReplaySubscription<T>[] b;
        do {
            a = (ReplaySubscription[]) this.subscribers.get();
            if (a == TERMINATED) {
                return false;
            }
            int len = a.length;
            b = new ReplaySubscription[(len + 1)];
            System.arraycopy(a, 0, b, 0, len);
            b[len] = rs;
        } while (!this.subscribers.compareAndSet(a, b));
        return true;
    }

    void remove(ReplaySubscription<T> rs) {
        ReplaySubscription[] a;
        ReplaySubscription<T>[] b;
        do {
            a = (ReplaySubscription[]) this.subscribers.get();
            if (a != TERMINATED && a != EMPTY) {
                int len = a.length;
                int j = -1;
                for (int i = 0; i < len; i++) {
                    if (a[i] == rs) {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (len == 1) {
                    b = EMPTY;
                } else {
                    b = new ReplaySubscription[(len - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (len - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(a, b));
    }
}
