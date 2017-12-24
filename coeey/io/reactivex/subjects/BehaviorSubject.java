package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.functions.ObjectHelper;
import io.reactivex.internal.util.ExceptionHelper;
import io.reactivex.internal.util.NotificationLite;
import io.reactivex.plugins.RxJavaPlugins;
import java.lang.reflect.Array;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public final class BehaviorSubject<T> extends Subject<T> {
    static final BehaviorDisposable[] EMPTY = new BehaviorDisposable[0];
    private static final Object[] EMPTY_ARRAY = new Object[0];
    static final BehaviorDisposable[] TERMINATED = new BehaviorDisposable[0];
    long index;
    final ReadWriteLock lock;
    final Lock readLock;
    final AtomicReference<BehaviorDisposable<T>[]> subscribers;
    final AtomicReference<Throwable> terminalEvent;
    final AtomicReference<Object> value;
    final Lock writeLock;

    @CheckReturnValue
    public static <T> BehaviorSubject<T> create() {
        return new BehaviorSubject();
    }

    @CheckReturnValue
    public static <T> BehaviorSubject<T> createDefault(T defaultValue) {
        return new BehaviorSubject(defaultValue);
    }

    BehaviorSubject() {
        this.lock = new ReentrantReadWriteLock();
        this.readLock = this.lock.readLock();
        this.writeLock = this.lock.writeLock();
        this.subscribers = new AtomicReference(EMPTY);
        this.value = new AtomicReference();
        this.terminalEvent = new AtomicReference();
    }

    BehaviorSubject(T defaultValue) {
        this();
        this.value.lazySet(ObjectHelper.requireNonNull(defaultValue, "defaultValue is null"));
    }

    protected void subscribeActual(Observer<? super T> observer) {
        BehaviorDisposable<T> bs = new BehaviorDisposable(observer, this);
        observer.onSubscribe(bs);
        if (!add(bs)) {
            Throwable ex = (Throwable) this.terminalEvent.get();
            if (ex == ExceptionHelper.TERMINATED) {
                observer.onComplete();
            } else {
                observer.onError(ex);
            }
        } else if (bs.cancelled) {
            remove(bs);
        } else {
            bs.emitFirst();
        }
    }

    public void onSubscribe(Disposable s) {
        if (this.terminalEvent.get() != null) {
            s.dispose();
        }
    }

    public void onNext(T t) {
        if (t == null) {
            onError(new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources."));
        } else if (this.terminalEvent.get() == null) {
            Object o = NotificationLite.next(t);
            setCurrent(o);
            for (BehaviorDisposable<T> bs : (BehaviorDisposable[]) this.subscribers.get()) {
                bs.emitNext(o, this.index);
            }
        }
    }

    public void onError(Throwable t) {
        if (t == null) {
            t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.terminalEvent.compareAndSet(null, t)) {
            Object o = NotificationLite.error(t);
            for (BehaviorDisposable<T> bs : terminate(o)) {
                bs.emitNext(o, this.index);
            }
            return;
        }
        RxJavaPlugins.onError(t);
    }

    public void onComplete() {
        if (this.terminalEvent.compareAndSet(null, ExceptionHelper.TERMINATED)) {
            Object o = NotificationLite.complete();
            for (BehaviorDisposable<T> bs : terminate(o)) {
                bs.emitNext(o, this.index);
            }
        }
    }

    public boolean hasObservers() {
        return ((BehaviorDisposable[]) this.subscribers.get()).length != 0;
    }

    int subscriberCount() {
        return ((BehaviorDisposable[]) this.subscribers.get()).length;
    }

    public Throwable getThrowable() {
        Object o = this.value.get();
        if (NotificationLite.isError(o)) {
            return NotificationLite.getError(o);
        }
        return null;
    }

    public T getValue() {
        Object o = this.value.get();
        if (NotificationLite.isComplete(o) || NotificationLite.isError(o)) {
            return null;
        }
        return NotificationLite.getValue(o);
    }

    public Object[] getValues() {
        T[] b = getValues((Object[]) EMPTY_ARRAY);
        if (b == EMPTY_ARRAY) {
            return new Object[0];
        }
        return b;
    }

    public T[] getValues(T[] array) {
        Object o = this.value.get();
        if (o == null || NotificationLite.isComplete(o) || NotificationLite.isError(o)) {
            if (array.length != 0) {
                array[0] = null;
            }
            return array;
        }
        T v = NotificationLite.getValue(o);
        if (array.length != 0) {
            array[0] = v;
            if (array.length != 1) {
                array[1] = null;
            }
        } else {
            Object[] array2 = (Object[]) ((Object[]) Array.newInstance(array.getClass().getComponentType(), 1));
            array2[0] = v;
        }
        return array;
    }

    public boolean hasComplete() {
        return NotificationLite.isComplete(this.value.get());
    }

    public boolean hasThrowable() {
        return NotificationLite.isError(this.value.get());
    }

    public boolean hasValue() {
        Object o = this.value.get();
        return (o == null || NotificationLite.isComplete(o) || NotificationLite.isError(o)) ? false : true;
    }

    boolean add(BehaviorDisposable<T> rs) {
        BehaviorDisposable[] a;
        BehaviorDisposable<T>[] b;
        do {
            a = (BehaviorDisposable[]) this.subscribers.get();
            if (a == TERMINATED) {
                return false;
            }
            int len = a.length;
            b = new BehaviorDisposable[(len + 1)];
            System.arraycopy(a, 0, b, 0, len);
            b[len] = rs;
        } while (!this.subscribers.compareAndSet(a, b));
        return true;
    }

    void remove(BehaviorDisposable<T> rs) {
        BehaviorDisposable[] a;
        BehaviorDisposable<T>[] b;
        do {
            a = (BehaviorDisposable[]) this.subscribers.get();
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
                    b = new BehaviorDisposable[(len - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (len - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(a, b));
    }

    BehaviorDisposable<T>[] terminate(Object terminalValue) {
        BehaviorDisposable<T>[] a = (BehaviorDisposable[]) this.subscribers.get();
        if (a != TERMINATED) {
            a = (BehaviorDisposable[]) this.subscribers.getAndSet(TERMINATED);
            if (a != TERMINATED) {
                setCurrent(terminalValue);
            }
        }
        return a;
    }

    void setCurrent(Object o) {
        this.writeLock.lock();
        try {
            this.index++;
            this.value.lazySet(o);
        } finally {
            this.writeLock.unlock();
        }
    }
}
