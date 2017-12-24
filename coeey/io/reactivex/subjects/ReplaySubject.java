package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.Scheduler;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public final class ReplaySubject<T> extends Subject<T> {
    static final ReplayDisposable[] EMPTY = new ReplayDisposable[0];
    private static final Object[] EMPTY_ARRAY = new Object[0];
    static final ReplayDisposable[] TERMINATED = new ReplayDisposable[0];
    final ReplayBuffer<T> buffer;
    boolean done;
    final AtomicReference<ReplayDisposable<T>[]> observers = new AtomicReference(EMPTY);

    @CheckReturnValue
    public static <T> ReplaySubject<T> create() {
        return new ReplaySubject(new UnboundedReplayBuffer(16));
    }

    @CheckReturnValue
    public static <T> ReplaySubject<T> create(int capacityHint) {
        return new ReplaySubject(new UnboundedReplayBuffer(capacityHint));
    }

    @CheckReturnValue
    public static <T> ReplaySubject<T> createWithSize(int maxSize) {
        return new ReplaySubject(new SizeBoundReplayBuffer(maxSize));
    }

    static <T> ReplaySubject<T> createUnbounded() {
        return new ReplaySubject(new SizeBoundReplayBuffer(Integer.MAX_VALUE));
    }

    @CheckReturnValue
    public static <T> ReplaySubject<T> createWithTime(long maxAge, TimeUnit unit, Scheduler scheduler) {
        return new ReplaySubject(new SizeAndTimeBoundReplayBuffer(Integer.MAX_VALUE, maxAge, unit, scheduler));
    }

    @CheckReturnValue
    public static <T> ReplaySubject<T> createWithTimeAndSize(long maxAge, TimeUnit unit, Scheduler scheduler, int maxSize) {
        return new ReplaySubject(new SizeAndTimeBoundReplayBuffer(maxSize, maxAge, unit, scheduler));
    }

    ReplaySubject(ReplayBuffer<T> buffer) {
        this.buffer = buffer;
    }

    protected void subscribeActual(Observer<? super T> observer) {
        ReplayDisposable<T> rs = new ReplayDisposable(observer, this);
        observer.onSubscribe(rs);
        if (!rs.cancelled) {
            if (add(rs) && rs.cancelled) {
                remove(rs);
            } else {
                this.buffer.replay(rs);
            }
        }
    }

    public void onSubscribe(Disposable s) {
        if (this.done) {
            s.dispose();
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (!this.done) {
            ReplayBuffer<T> b = this.buffer;
            b.add(t);
            for (ReplayDisposable<T> rs : (ReplayDisposable[]) this.observers.get()) {
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
        Object o = NotificationLite.error(t);
        ReplayBuffer<T> b = this.buffer;
        b.addFinal(o);
        for (ReplayDisposable<T> rs : terminate(o)) {
            b.replay(rs);
        }
    }

    public void onComplete() {
        if (!this.done) {
            this.done = true;
            Object o = NotificationLite.complete();
            ReplayBuffer<T> b = this.buffer;
            b.addFinal(o);
            for (ReplayDisposable<T> rs : terminate(o)) {
                b.replay(rs);
            }
        }
    }

    public boolean hasObservers() {
        return ((ReplayDisposable[]) this.observers.get()).length != 0;
    }

    int observerCount() {
        return ((ReplayDisposable[]) this.observers.get()).length;
    }

    public Throwable getThrowable() {
        Object o = this.buffer.get();
        if (NotificationLite.isError(o)) {
            return NotificationLite.getError(o);
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
        return NotificationLite.isComplete(this.buffer.get());
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.buffer.get());
    }

    public boolean hasValue() {
        return this.buffer.size() != 0;
    }

    int size() {
        return this.buffer.size();
    }

    boolean add(ReplayDisposable<T> rs) {
        ReplayDisposable[] a;
        ReplayDisposable<T>[] b;
        do {
            a = (ReplayDisposable[]) this.observers.get();
            if (a == TERMINATED) {
                return false;
            }
            int len = a.length;
            b = new ReplayDisposable[(len + 1)];
            System.arraycopy(a, 0, b, 0, len);
            b[len] = rs;
        } while (!this.observers.compareAndSet(a, b));
        return true;
    }

    void remove(ReplayDisposable<T> rs) {
        ReplayDisposable[] a;
        ReplayDisposable<T>[] b;
        do {
            a = (ReplayDisposable[]) this.observers.get();
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
                    b = new ReplayDisposable[(len - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (len - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(a, b));
    }

    ReplayDisposable<T>[] terminate(Object terminalValue) {
        if (this.buffer.compareAndSet(null, terminalValue)) {
            return (ReplayDisposable[]) this.observers.getAndSet(TERMINATED);
        }
        return TERMINATED;
    }
}
