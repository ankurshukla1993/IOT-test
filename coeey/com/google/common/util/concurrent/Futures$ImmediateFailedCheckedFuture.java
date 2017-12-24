package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

class Futures$ImmediateFailedCheckedFuture<V, X extends Exception> extends Futures$ImmediateFuture<V> implements CheckedFuture<V, X> {
    private final X thrown;

    Futures$ImmediateFailedCheckedFuture(X thrown) {
        super();
        this.thrown = thrown;
    }

    public V get() throws ExecutionException {
        throw new ExecutionException(this.thrown);
    }

    public V checkedGet() throws Exception {
        throw this.thrown;
    }

    public V checkedGet(long timeout, TimeUnit unit) throws Exception {
        Preconditions.checkNotNull(unit);
        throw this.thrown;
    }
}
