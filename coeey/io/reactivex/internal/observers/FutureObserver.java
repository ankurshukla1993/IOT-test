package io.reactivex.internal.observers;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.disposables.DisposableHelper;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.plugins.RxJavaPlugins;
import java.util.NoSuchElementException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.atomic.AtomicReference;

public final class FutureObserver<T> extends CountDownLatch implements Observer<T>, Future<T>, Disposable {
    Throwable error;
    final AtomicReference<Disposable> f2486s = new AtomicReference();
    T value;

    public FutureObserver() {
        super(1);
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        DisposableHelper a;
        do {
            a = (Disposable) this.f2486s.get();
            if (a == this || a == DisposableHelper.DISPOSED) {
                return false;
            }
        } while (!this.f2486s.compareAndSet(a, DisposableHelper.DISPOSED));
        if (a != null) {
            a.dispose();
        }
        countDown();
        return true;
    }

    public boolean isCancelled() {
        return DisposableHelper.isDisposed((Disposable) this.f2486s.get());
    }

    public boolean isDone() {
        return getCount() == 0;
    }

    public T get() throws InterruptedException, ExecutionException {
        if (getCount() != 0) {
            BlockingHelper.verifyNonBlocking();
            await();
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable ex = this.error;
        if (ex == null) {
            return this.value;
        }
        throw new ExecutionException(ex);
    }

    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (getCount() != 0) {
            BlockingHelper.verifyNonBlocking();
            if (!await(timeout, unit)) {
                throw new TimeoutException();
            }
        }
        if (isCancelled()) {
            throw new CancellationException();
        }
        Throwable ex = this.error;
        if (ex == null) {
            return this.value;
        }
        throw new ExecutionException(ex);
    }

    public void onSubscribe(Disposable s) {
        DisposableHelper.setOnce(this.f2486s, s);
    }

    public void onNext(T t) {
        if (this.value != null) {
            ((Disposable) this.f2486s.get()).dispose();
            onError(new IndexOutOfBoundsException("More than one element received"));
            return;
        }
        this.value = t;
    }

    public void onError(Throwable t) {
        if (this.error == null) {
            this.error = t;
            DisposableHelper a;
            do {
                a = (Disposable) this.f2486s.get();
                if (a == this || a == DisposableHelper.DISPOSED) {
                    RxJavaPlugins.onError(t);
                    return;
                }
            } while (!this.f2486s.compareAndSet(a, this));
            countDown();
            return;
        }
        RxJavaPlugins.onError(t);
    }

    public void onComplete() {
        if (this.value == null) {
            onError(new NoSuchElementException("The source is empty"));
            return;
        }
        DisposableHelper a;
        do {
            a = (Disposable) this.f2486s.get();
            if (a == this || a == DisposableHelper.DISPOSED) {
                return;
            }
        } while (!this.f2486s.compareAndSet(a, this));
        countDown();
    }

    public void dispose() {
    }

    public boolean isDisposed() {
        return isDone();
    }
}
