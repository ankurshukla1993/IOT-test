package com.google.common.util.concurrent;

import java.util.concurrent.CancellationException;

class Futures$ImmediateCancelledFuture<V> extends Futures$ImmediateFuture<V> {
    private final CancellationException thrown = new CancellationException("Immediate cancelled future.");

    Futures$ImmediateCancelledFuture() {
        super();
    }

    public boolean isCancelled() {
        return true;
    }

    public V get() {
        throw AbstractFuture.cancellationExceptionWithCause("Task was cancelled.", this.thrown);
    }
}
