package io.reactivex.internal.observers;

import io.reactivex.CompletableObserver;
import io.reactivex.MaybeObserver;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.util.BlockingHelper;
import io.reactivex.internal.util.ExceptionHelper;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public final class BlockingMultiObserver<T> extends CountDownLatch implements SingleObserver<T>, CompletableObserver, MaybeObserver<T> {
    volatile boolean cancelled;
    Disposable f2482d;
    Throwable error;
    T value;

    public BlockingMultiObserver() {
        super(1);
    }

    void dispose() {
        this.cancelled = true;
        Disposable d = this.f2482d;
        if (d != null) {
            d.dispose();
        }
    }

    public void onSubscribe(Disposable d) {
        this.f2482d = d;
        if (this.cancelled) {
            d.dispose();
        }
    }

    public void onSuccess(T value) {
        this.value = value;
        countDown();
    }

    public void onError(Throwable e) {
        this.error = e;
        countDown();
    }

    public void onComplete() {
        countDown();
    }

    public T blockingGet() {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (Throwable ex) {
                dispose();
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        Throwable ex2 = this.error;
        if (ex2 == null) {
            return this.value;
        }
        throw ExceptionHelper.wrapOrThrow(ex2);
    }

    public T blockingGet(T defaultValue) {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (Throwable ex) {
                dispose();
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        Throwable ex2 = this.error;
        if (ex2 != null) {
            throw ExceptionHelper.wrapOrThrow(ex2);
        }
        T v = this.value;
        return v != null ? v : defaultValue;
    }

    public Throwable blockingGetError() {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                await();
            } catch (Throwable ex) {
                dispose();
                return ex;
            }
        }
        return this.error;
    }

    public Throwable blockingGetError(long timeout, TimeUnit unit) {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                if (!await(timeout, unit)) {
                    dispose();
                    throw ExceptionHelper.wrapOrThrow(new TimeoutException());
                }
            } catch (InterruptedException ex) {
                dispose();
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        return this.error;
    }

    public boolean blockingAwait(long timeout, TimeUnit unit) {
        if (getCount() != 0) {
            try {
                BlockingHelper.verifyNonBlocking();
                if (!await(timeout, unit)) {
                    dispose();
                    return false;
                }
            } catch (InterruptedException ex) {
                dispose();
                throw ExceptionHelper.wrapOrThrow(ex);
            }
        }
        Throwable ex2 = this.error;
        if (ex2 == null) {
            return true;
        }
        throw ExceptionHelper.wrapOrThrow(ex2);
    }
}
