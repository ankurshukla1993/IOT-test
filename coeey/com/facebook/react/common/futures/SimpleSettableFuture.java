package com.facebook.react.common.futures;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import javax.annotation.Nullable;

public class SimpleSettableFuture<T> implements Future<T> {
    @Nullable
    private Exception mException;
    private final CountDownLatch mReadyLatch = new CountDownLatch(1);
    @Nullable
    private T mResult;

    public void set(@Nullable T result) {
        checkNotSet();
        this.mResult = result;
        this.mReadyLatch.countDown();
    }

    public void setException(Exception exception) {
        checkNotSet();
        this.mException = exception;
        this.mReadyLatch.countDown();
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return this.mReadyLatch.getCount() == 0;
    }

    @Nullable
    public T get() throws InterruptedException, ExecutionException {
        this.mReadyLatch.await();
        if (this.mException == null) {
            return this.mResult;
        }
        throw new ExecutionException(this.mException);
    }

    @Nullable
    public T get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        if (!this.mReadyLatch.await(timeout, unit)) {
            throw new TimeoutException("Timed out waiting for result");
        } else if (this.mException == null) {
            return this.mResult;
        } else {
            throw new ExecutionException(this.mException);
        }
    }

    @Nullable
    public T getOrThrow() {
        Exception e;
        try {
            return get();
        } catch (InterruptedException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (ExecutionException e3) {
            e = e3;
            throw new RuntimeException(e);
        }
    }

    @Nullable
    public T getOrThrow(long timeout, TimeUnit unit) {
        Exception e;
        try {
            return get(timeout, unit);
        } catch (InterruptedException e2) {
            e = e2;
            throw new RuntimeException(e);
        } catch (ExecutionException e3) {
            e = e3;
            throw new RuntimeException(e);
        } catch (TimeoutException e4) {
            e = e4;
            throw new RuntimeException(e);
        }
    }

    private void checkNotSet() {
        if (this.mReadyLatch.getCount() == 0) {
            throw new RuntimeException("Result has already been set!");
        }
    }
}
