package com.facebook.common.executors;

import android.os.Handler;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.Callable;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;

public class HandlerExecutorServiceImpl extends AbstractExecutorService implements HandlerExecutorService {
    private final Handler mHandler;

    public HandlerExecutorServiceImpl(Handler handler) {
        this.mHandler = handler;
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }

    public void execute(Runnable command) {
        this.mHandler.post(command);
    }

    protected <T> ScheduledFutureImpl<T> newTaskFor(Runnable runnable, T value) {
        return new ScheduledFutureImpl(this.mHandler, runnable, value);
    }

    protected <T> ScheduledFutureImpl<T> newTaskFor(Callable<T> callable) {
        return new ScheduledFutureImpl(this.mHandler, callable);
    }

    public ScheduledFuture<?> submit(Runnable task) {
        return submit(task, (Void) null);
    }

    public <T> ScheduledFuture<T> submit(Runnable task, @Nullable T result) {
        if (task == null) {
            throw new NullPointerException();
        }
        ScheduledFutureImpl<T> future = newTaskFor(task, (Object) result);
        execute(future);
        return future;
    }

    public <T> ScheduledFuture<T> submit(Callable<T> task) {
        if (task == null) {
            throw new NullPointerException();
        }
        ScheduledFutureImpl<T> future = newTaskFor((Callable) task);
        execute(future);
        return future;
    }

    public ScheduledFuture<?> schedule(Runnable command, long delay, TimeUnit unit) {
        ScheduledFutureImpl<?> future = newTaskFor(command, null);
        this.mHandler.postDelayed(future, unit.toMillis(delay));
        return future;
    }

    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long delay, TimeUnit unit) {
        ScheduledFutureImpl<V> future = newTaskFor((Callable) callable);
        this.mHandler.postDelayed(future, unit.toMillis(delay));
        return future;
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) {
        throw new UnsupportedOperationException();
    }

    public void quit() {
        this.mHandler.getLooper().quit();
    }

    public boolean isHandlerThread() {
        return Thread.currentThread() == this.mHandler.getLooper().getThread();
    }
}
