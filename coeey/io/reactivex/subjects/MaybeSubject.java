package io.reactivex.subjects;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class MaybeSubject<T> extends Maybe<T> implements MaybeObserver<T> {
    static final MaybeDisposable[] EMPTY = new MaybeDisposable[0];
    static final MaybeDisposable[] TERMINATED = new MaybeDisposable[0];
    Throwable error;
    final AtomicReference<MaybeDisposable<T>[]> observers = new AtomicReference(EMPTY);
    final AtomicBoolean once = new AtomicBoolean();
    T value;

    @CheckReturnValue
    public static <T> MaybeSubject<T> create() {
        return new MaybeSubject();
    }

    MaybeSubject() {
    }

    public void onSubscribe(Disposable d) {
        if (this.observers.get() == TERMINATED) {
            d.dispose();
        }
    }

    public void onSuccess(T value) {
        int i = 0;
        if (value == null) {
            onError(new NullPointerException("Null values are not allowed in 2.x"));
        } else if (this.once.compareAndSet(false, true)) {
            this.value = value;
            MaybeDisposable[] maybeDisposableArr = (MaybeDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = maybeDisposableArr.length;
            while (i < length) {
                maybeDisposableArr[i].actual.onSuccess(value);
                i++;
            }
        }
    }

    public void onError(Throwable e) {
        int i = 0;
        if (e == null) {
            e = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.once.compareAndSet(false, true)) {
            this.error = e;
            MaybeDisposable[] maybeDisposableArr = (MaybeDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = maybeDisposableArr.length;
            while (i < length) {
                maybeDisposableArr[i].actual.onError(e);
                i++;
            }
            return;
        }
        RxJavaPlugins.onError(e);
    }

    public void onComplete() {
        int i = 0;
        if (this.once.compareAndSet(false, true)) {
            MaybeDisposable[] maybeDisposableArr = (MaybeDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = maybeDisposableArr.length;
            while (i < length) {
                maybeDisposableArr[i].actual.onComplete();
                i++;
            }
        }
    }

    protected void subscribeActual(MaybeObserver<? super T> observer) {
        MaybeDisposable<T> md = new MaybeDisposable(observer, this);
        observer.onSubscribe(md);
        if (!add(md)) {
            Throwable ex = this.error;
            if (ex != null) {
                observer.onError(ex);
                return;
            }
            T v = this.value;
            if (v == null) {
                observer.onComplete();
            } else {
                observer.onSuccess(v);
            }
        } else if (md.isDisposed()) {
            remove(md);
        }
    }

    boolean add(MaybeDisposable<T> inner) {
        MaybeDisposable[] a;
        MaybeDisposable<T>[] b;
        do {
            a = (MaybeDisposable[]) this.observers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new MaybeDisposable[(n + 1)];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = inner;
        } while (!this.observers.compareAndSet(a, b));
        return true;
    }

    void remove(MaybeDisposable<T> inner) {
        MaybeDisposable[] a;
        MaybeDisposable<T>[] b;
        do {
            a = (MaybeDisposable[]) this.observers.get();
            int n = a.length;
            if (n != 0) {
                int j = -1;
                for (int i = 0; i < n; i++) {
                    if (a[i] == inner) {
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
                    b = new MaybeDisposable[(n - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(a, b));
    }

    public T getValue() {
        if (this.observers.get() == TERMINATED) {
            return this.value;
        }
        return null;
    }

    public boolean hasValue() {
        return this.observers.get() == TERMINATED && this.value != null;
    }

    public Throwable getThrowable() {
        if (this.observers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.observers.get() == TERMINATED && this.error != null;
    }

    public boolean hasComplete() {
        return this.observers.get() == TERMINATED && this.value == null && this.error == null;
    }

    public boolean hasObservers() {
        return ((MaybeDisposable[]) this.observers.get()).length != 0;
    }

    int observerCount() {
        return ((MaybeDisposable[]) this.observers.get()).length;
    }
}
