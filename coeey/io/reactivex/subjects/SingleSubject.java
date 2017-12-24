package io.reactivex.subjects;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.annotations.NonNull;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleSubject<T> extends Single<T> implements SingleObserver<T> {
    static final SingleDisposable[] EMPTY = new SingleDisposable[0];
    static final SingleDisposable[] TERMINATED = new SingleDisposable[0];
    Throwable error;
    final AtomicReference<SingleDisposable<T>[]> observers = new AtomicReference(EMPTY);
    final AtomicBoolean once = new AtomicBoolean();
    T value;

    @CheckReturnValue
    @NonNull
    public static <T> SingleSubject<T> create() {
        return new SingleSubject();
    }

    SingleSubject() {
    }

    public void onSubscribe(@NonNull Disposable d) {
        if (this.observers.get() == TERMINATED) {
            d.dispose();
        }
    }

    public void onSuccess(@NonNull T value) {
        int i = 0;
        if (value == null) {
            onError(new NullPointerException("Null values are not allowed in 2.x"));
        } else if (this.once.compareAndSet(false, true)) {
            this.value = value;
            SingleDisposable[] singleDisposableArr = (SingleDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = singleDisposableArr.length;
            while (i < length) {
                singleDisposableArr[i].actual.onSuccess(value);
                i++;
            }
        }
    }

    public void onError(@NonNull Throwable e) {
        int i = 0;
        if (e == null) {
            e = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.once.compareAndSet(false, true)) {
            this.error = e;
            SingleDisposable[] singleDisposableArr = (SingleDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = singleDisposableArr.length;
            while (i < length) {
                singleDisposableArr[i].actual.onError(e);
                i++;
            }
            return;
        }
        RxJavaPlugins.onError(e);
    }

    protected void subscribeActual(@NonNull SingleObserver<? super T> observer) {
        SingleDisposable<T> md = new SingleDisposable(observer, this);
        observer.onSubscribe(md);
        if (!add(md)) {
            Throwable ex = this.error;
            if (ex != null) {
                observer.onError(ex);
            } else {
                observer.onSuccess(this.value);
            }
        } else if (md.isDisposed()) {
            remove(md);
        }
    }

    boolean add(@NonNull SingleDisposable<T> inner) {
        SingleDisposable[] a;
        SingleDisposable<T>[] b;
        do {
            a = (SingleDisposable[]) this.observers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new SingleDisposable[(n + 1)];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = inner;
        } while (!this.observers.compareAndSet(a, b));
        return true;
    }

    void remove(@NonNull SingleDisposable<T> inner) {
        SingleDisposable[] a;
        SingleDisposable<T>[] b;
        do {
            a = (SingleDisposable[]) this.observers.get();
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
                    b = new SingleDisposable[(n - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(a, b));
    }

    @Nullable
    public T getValue() {
        if (this.observers.get() == TERMINATED) {
            return this.value;
        }
        return null;
    }

    public boolean hasValue() {
        return this.observers.get() == TERMINATED && this.value != null;
    }

    @Nullable
    public Throwable getThrowable() {
        if (this.observers.get() == TERMINATED) {
            return this.error;
        }
        return null;
    }

    public boolean hasThrowable() {
        return this.observers.get() == TERMINATED && this.error != null;
    }

    public boolean hasObservers() {
        return ((SingleDisposable[]) this.observers.get()).length != 0;
    }

    int observerCount() {
        return ((SingleDisposable[]) this.observers.get()).length;
    }
}
