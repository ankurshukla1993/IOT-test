package io.reactivex.subjects;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.annotations.CheckReturnValue;
import io.reactivex.disposables.Disposable;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class CompletableSubject extends Completable implements CompletableObserver {
    static final CompletableDisposable[] EMPTY = new CompletableDisposable[0];
    static final CompletableDisposable[] TERMINATED = new CompletableDisposable[0];
    Throwable error;
    final AtomicReference<CompletableDisposable[]> observers = new AtomicReference(EMPTY);
    final AtomicBoolean once = new AtomicBoolean();

    @CheckReturnValue
    public static CompletableSubject create() {
        return new CompletableSubject();
    }

    CompletableSubject() {
    }

    public void onSubscribe(Disposable d) {
        if (this.observers.get() == TERMINATED) {
            d.dispose();
        }
    }

    public void onError(Throwable e) {
        int i = 0;
        if (e == null) {
            e = new NullPointerException("Null errors are not allowed in 2.x");
        }
        if (this.once.compareAndSet(false, true)) {
            this.error = e;
            CompletableDisposable[] completableDisposableArr = (CompletableDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = completableDisposableArr.length;
            while (i < length) {
                completableDisposableArr[i].actual.onError(e);
                i++;
            }
            return;
        }
        RxJavaPlugins.onError(e);
    }

    public void onComplete() {
        int i = 0;
        if (this.once.compareAndSet(false, true)) {
            CompletableDisposable[] completableDisposableArr = (CompletableDisposable[]) this.observers.getAndSet(TERMINATED);
            int length = completableDisposableArr.length;
            while (i < length) {
                completableDisposableArr[i].actual.onComplete();
                i++;
            }
        }
    }

    protected void subscribeActual(CompletableObserver observer) {
        CompletableDisposable md = new CompletableDisposable(observer, this);
        observer.onSubscribe(md);
        if (!add(md)) {
            Throwable ex = this.error;
            if (ex != null) {
                observer.onError(ex);
            } else {
                observer.onComplete();
            }
        } else if (md.isDisposed()) {
            remove(md);
        }
    }

    boolean add(CompletableDisposable inner) {
        CompletableDisposable[] a;
        CompletableDisposable[] b;
        do {
            a = (CompletableDisposable[]) this.observers.get();
            if (a == TERMINATED) {
                return false;
            }
            int n = a.length;
            b = new CompletableDisposable[(n + 1)];
            System.arraycopy(a, 0, b, 0, n);
            b[n] = inner;
        } while (!this.observers.compareAndSet(a, b));
        return true;
    }

    void remove(CompletableDisposable inner) {
        CompletableDisposable[] a;
        CompletableDisposable[] b;
        do {
            a = (CompletableDisposable[]) this.observers.get();
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
                    b = new CompletableDisposable[(n - 1)];
                    System.arraycopy(a, 0, b, 0, j);
                    System.arraycopy(a, j + 1, b, j, (n - j) - 1);
                }
            } else {
                return;
            }
        } while (!this.observers.compareAndSet(a, b));
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
        return this.observers.get() == TERMINATED && this.error == null;
    }

    public boolean hasObservers() {
        return ((CompletableDisposable[]) this.observers.get()).length != 0;
    }

    int observerCount() {
        return ((CompletableDisposable[]) this.observers.get()).length;
    }
}
