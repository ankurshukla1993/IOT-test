package com.google.common.util.concurrent;

import java.util.concurrent.ExecutionException;

class Futures$ImmediateFailedFuture<V> extends Futures$ImmediateFuture<V> {
    private final Throwable thrown;

    Futures$ImmediateFailedFuture(Throwable thrown) {
        super();
        this.thrown = thrown;
    }

    public V get() throws ExecutionException {
        throw new ExecutionException(this.thrown);
    }
}
