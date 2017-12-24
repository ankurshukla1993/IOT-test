package com.google.common.util.concurrent;

import com.google.common.collect.ImmutableList;
import java.util.Iterator;
import java.util.concurrent.Callable;

final class Futures$CombinerFuture<V> extends ListenableFutureTask<V> {
    ImmutableList<ListenableFuture<?>> futures;

    Futures$CombinerFuture(Callable<V> callable, ImmutableList<ListenableFuture<?>> futures) {
        super(callable);
        this.futures = futures;
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        ImmutableList<ListenableFuture<?>> futures = this.futures;
        if (!super.cancel(mayInterruptIfRunning)) {
            return false;
        }
        Iterator i$ = futures.iterator();
        while (i$.hasNext()) {
            ((ListenableFuture) i$.next()).cancel(mayInterruptIfRunning);
        }
        return true;
    }

    protected void done() {
        super.done();
        this.futures = null;
    }

    protected void setException(Throwable t) {
        super.setException(t);
    }
}
