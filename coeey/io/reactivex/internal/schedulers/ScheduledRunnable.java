package io.reactivex.internal.schedulers;

import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableContainer;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReferenceArray;

public final class ScheduledRunnable extends AtomicReferenceArray<Object> implements Runnable, Callable<Object>, Disposable {
    static final Object DISPOSED = new Object();
    static final Object DONE = new Object();
    static final int FUTURE_INDEX = 1;
    static final int PARENT_INDEX = 0;
    static final int THREAD_INDEX = 2;
    private static final long serialVersionUID = -6120223772001106981L;
    final Runnable actual;

    public ScheduledRunnable(Runnable actual, DisposableContainer parent) {
        super(3);
        this.actual = actual;
        lazySet(0, parent);
    }

    public Object call() {
        run();
        return null;
    }

    public void run() {
        Object o;
        lazySet(2, Thread.currentThread());
        try {
            this.actual.run();
        } catch (Throwable th) {
            Throwable th2 = th;
            lazySet(2, null);
            o = get(0);
            if (!(o == DISPOSED || o == null || !compareAndSet(0, o, DONE))) {
                ((DisposableContainer) o).delete(this);
            }
            do {
                o = get(1);
                if (o == DISPOSED) {
                    break;
                }
            } while (!compareAndSet(1, o, DONE));
        }
        lazySet(2, null);
        o = get(0);
        if (!(o == DISPOSED || o == null || !compareAndSet(0, o, DONE))) {
            ((DisposableContainer) o).delete(this);
        }
        do {
            o = get(1);
            if (o == DISPOSED) {
                return;
            }
        } while (!compareAndSet(1, o, DONE));
    }

    public void setFuture(Future<?> f) {
        boolean z = true;
        Object o;
        do {
            o = get(1);
            if (o != DONE) {
                if (o == DISPOSED) {
                    if (get(2) == Thread.currentThread()) {
                        z = false;
                    }
                    f.cancel(z);
                    return;
                }
            } else {
                return;
            }
        } while (!compareAndSet(1, o, f));
    }

    public void dispose() {
        Object o;
        boolean z = true;
        do {
            o = get(1);
            if (o == DONE || o == DISPOSED) {
                break;
            }
        } while (!compareAndSet(1, o, DISPOSED));
        if (o != null) {
            Future future = (Future) o;
            if (get(2) == Thread.currentThread()) {
                z = false;
            }
            future.cancel(z);
        }
        do {
            o = get(0);
            if (o == DONE || o == DISPOSED || o == null) {
                return;
            }
        } while (!compareAndSet(0, o, DISPOSED));
        ((DisposableContainer) o).delete(this);
    }

    public boolean isDisposed() {
        Object o = get(1);
        if (o == DISPOSED || o == DONE) {
            return true;
        }
        return false;
    }
}
