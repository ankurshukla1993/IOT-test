package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

abstract class Futures$ImmediateFuture<V> implements ListenableFuture<V> {
    private static final Logger log = Logger.getLogger(Futures$ImmediateFuture.class.getName());

    public abstract V get() throws ExecutionException;

    private Futures$ImmediateFuture() {
    }

    public void addListener(Runnable listener, Executor executor) {
        Preconditions.checkNotNull(listener, "Runnable was null.");
        Preconditions.checkNotNull(executor, "Executor was null.");
        try {
            executor.execute(listener);
        } catch (RuntimeException e) {
            Logger logger = log;
            Level level = Level.SEVERE;
            String valueOf = String.valueOf(String.valueOf(listener));
            String valueOf2 = String.valueOf(String.valueOf(executor));
            logger.log(level, new StringBuilder((valueOf.length() + 57) + valueOf2.length()).append("RuntimeException while executing runnable ").append(valueOf).append(" with executor ").append(valueOf2).toString(), e);
        }
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    public V get(long timeout, TimeUnit unit) throws ExecutionException {
        Preconditions.checkNotNull(unit);
        return get();
    }

    public boolean isCancelled() {
        return false;
    }

    public boolean isDone() {
        return true;
    }
}
