package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.concurrent.Callable;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

final class Futures$WrappedCombiner<T> implements Callable<T> {
    final Callable<T> delegate;
    Futures$CombinerFuture<T> outputFuture;

    Futures$WrappedCombiner(Callable<T> delegate) {
        this.delegate = (Callable) Preconditions.checkNotNull(delegate);
    }

    public T call() throws Exception {
        try {
            return this.delegate.call();
        } catch (ExecutionException e) {
            this.outputFuture.setException(e.getCause());
            return null;
        } catch (CancellationException e2) {
            this.outputFuture.cancel(false);
            return null;
        }
    }
}
