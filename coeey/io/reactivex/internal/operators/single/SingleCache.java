package io.reactivex.internal.operators.single;

import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.SingleSource;
import io.reactivex.disposables.Disposable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public final class SingleCache<T> extends Single<T> implements SingleObserver<T> {
    static final CacheDisposable[] EMPTY = new CacheDisposable[0];
    static final CacheDisposable[] TERMINATED = new CacheDisposable[0];
    Throwable error;
    final AtomicReference<CacheDisposable<T>[]> observers = new AtomicReference(EMPTY);
    final SingleSource<? extends T> source;
    T value;
    final AtomicInteger wip = new AtomicInteger();

    static final class CacheDisposable<T> extends AtomicBoolean implements Disposable {
        private static final long serialVersionUID = 7514387411091976596L;
        final SingleObserver<? super T> actual;
        final SingleCache<T> parent;

        CacheDisposable(SingleObserver<? super T> actual, SingleCache<T> parent) {
            this.actual = actual;
            this.parent = parent;
        }

        public boolean isDisposed() {
            return get();
        }

        public void dispose() {
            if (compareAndSet(false, true)) {
                this.parent.remove(this);
            }
        }
    }

    public SingleCache(SingleSource<? extends T> source) {
        this.source = source;
    }

    protected void subscribeActual(SingleObserver<? super T> s) {
        CacheDisposable<T> d = new CacheDisposable(s, this);
        s.onSubscribe(d);
        if (add(d)) {
            if (d.isDisposed()) {
                remove(d);
            }
            if (this.wip.getAndIncrement() == 0) {
                this.source.subscribe(this);
                return;
            }
            return;
        }
        Throwable ex = this.error;
        if (ex != null) {
            s.onError(ex);
        } else {
            s.onSuccess(this.value);
        }
    }

    boolean add(CacheDisposable<T> observer) {
        CacheDisposable[] a;
        CacheDisposable<T>[] b;
        do {
            a = (CacheDisposable[]) this.observers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new CacheDisposable[(n + 1)];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = observer;
        } while (!this.observers.compareAndSet(a, b));
        return true;
    }

    void remove(CacheDisposable<T> observer) {
        CacheDisposable[] a;
        CacheDisposable<T>[] b;
        do {
            a = (CacheDisposable[]) this.observers.get();
            int n = a.length;
            if (n != 0) {
                int j = -1;
                for (int i = 0; i < n; i++) {
                    if (a[i] == observer) {
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
                    b = new CacheDisposable[(n - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(a, b));
    }

    public void onSubscribe(Disposable d) {
    }

    public void onSuccess(T value) {
        this.value = value;
        for (CacheDisposable<T> d : (CacheDisposable[]) this.observers.getAndSet(TERMINATED)) {
            if (!d.isDisposed()) {
                d.actual.onSuccess(value);
            }
        }
    }

    public void onError(Throwable e) {
        this.error = e;
        for (CacheDisposable<T> d : (CacheDisposable[]) this.observers.getAndSet(TERMINATED)) {
            if (!d.isDisposed()) {
                d.actual.onError(e);
            }
        }
    }
}
