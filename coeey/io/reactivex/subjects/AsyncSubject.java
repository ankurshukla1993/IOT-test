package io.reactivex.subjects;

import io.reactivex.Observer;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public final class AsyncSubject<T> extends Subject<T> {
    static final AsyncDisposable[] EMPTY = new AsyncDisposable[0];
    static final AsyncDisposable[] TERMINATED = new AsyncDisposable[0];
    Throwable error;
    final AtomicReference<AsyncDisposable<T>[]> subscribers = new AtomicReference(EMPTY);
    T value;

    @CheckReturnValue
    public static <T> AsyncSubject<T> create() {
        return new AsyncSubject();
    }

    AsyncSubject() {
    }

    public void onSubscribe(Disposable s) {
        if (this.subscribers.get() == TERMINATED) {
            s.dispose();
        }
    }

    public void onNext(T t) {
        if (this.subscribers.get() != TERMINATED) {
            if (t == null) {
                nullOnNext();
            } else {
                this.value = t;
            }
        }
    }

    void nullOnNext() {
        this.value = null;
        Throwable ex = new NullPointerException("onNext called with null. Null values are generally not allowed in 2.x operators and sources.");
        this.error = ex;
        for (AsyncDisposable<T> as : (AsyncDisposable[]) this.subscribers.getAndSet(TERMINATED)) {
            as.onError(ex);
        }
    }

    public void onError(Throwable t) {
        if (t == null) {
            t = new NullPointerException("onError called with null. Null values are generally not allowed in 2.x operators and sources.");
        }
        if (this.subscribers.get() == TERMINATED) {
            RxJavaPlugins.onError(t);
            return;
        }
        this.value = null;
        this.error = t;
        for (AsyncDisposable<T> as : (AsyncDisposable[]) this.subscribers.getAndSet(TERMINATED)) {
            as.onError(t);
        }
    }

    public void onComplete() {
        int i = 0;
        if (this.subscribers.get() != TERMINATED) {
            T v = this.value;
            AsyncDisposable[] array = (AsyncDisposable[]) this.subscribers.getAndSet(TERMINATED);
            int length;
            if (v == null) {
                length = array.length;
                while (i < length) {
                    array[i].onComplete();
                    i++;
                }
                return;
            }
            length = array.length;
            while (i < length) {
                array[i].complete(v);
                i++;
            }
        }
    }

    public boolean hasObservers() {
        return ((AsyncDisposable[]) this.subscribers.get()).length != 0;
    }

    public boolean hasThrowable() {
        return this.subscribers.get() == TERMINATED && this.error != null;
    }

    public boolean hasComplete() {
        return this.subscribers.get() == TERMINATED && this.error == null;
    }

    public Throwable getThrowable() {
        return this.subscribers.get() == TERMINATED ? this.error : null;
    }

    protected void subscribeActual(Observer<? super T> s) {
        AsyncDisposable<T> as = new AsyncDisposable(s, this);
        s.onSubscribe(as);
        if (!add(as)) {
            Throwable ex = this.error;
            if (ex != null) {
                s.onError(ex);
                return;
            }
            T v = this.value;
            if (v != null) {
                as.complete(v);
            } else {
                as.onComplete();
            }
        } else if (as.isDisposed()) {
            remove(as);
        }
    }

    boolean add(AsyncDisposable<T> ps) {
        AsyncDisposable[] a;
        AsyncDisposable<T>[] b;
        do {
            a = (AsyncDisposable[]) this.subscribers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new AsyncDisposable[(n + 1)];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = ps;
        } while (!this.subscribers.compareAndSet(a, b));
        return true;
    }

    void remove(AsyncDisposable<T> ps) {
        AsyncDisposable[] a;
        AsyncDisposable<T>[] b;
        do {
            a = (AsyncDisposable[]) this.subscribers.get();
            int n = a.length;
            if (n != 0) {
                int j = -1;
                for (int i = 0; i < n; i++) {
                    if (a[i] == ps) {
                        j = i;
                        break;
                    }
                }
                if (j < 0) {
                    return;
                }
                if (n == 1) {
                    b = EMPTY;
                } else {
                    b = new AsyncDisposable[(n - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.subscribers.compareAndSet(a, b));
    }

    public boolean hasValue() {
        return this.subscribers.get() == TERMINATED && this.value != null;
    }

    public T getValue() {
        return this.subscribers.get() == TERMINATED ? this.value : null;
    }

    public Object[] getValues() {
        if (getValue() == null) {
            return new Object[0];
        }
        return new Object[]{getValue()};
    }

    public T[] getValues(T[] array) {
        T v = getValue();
        if (v == null) {
            if (array.length != 0) {
                array[0] = null;
            }
            return array;
        }
        if (array.length == 0) {
            array = Arrays.copyOf(array, 1);
        }
        array[0] = v;
        if (array.length != 1) {
            array[1] = null;
        }
        return array;
    }
}
