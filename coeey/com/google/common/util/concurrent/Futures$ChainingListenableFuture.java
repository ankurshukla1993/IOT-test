package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.lang.reflect.UndeclaredThrowableException;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import javax.annotation.Nullable;

class Futures$ChainingListenableFuture<I, O> extends AbstractFuture<O> implements Runnable {
    private AsyncFunction<? super I, ? extends O> function;
    private ListenableFuture<? extends I> inputFuture;
    private volatile ListenableFuture<? extends O> outputFuture;

    private Futures$ChainingListenableFuture(AsyncFunction<? super I, ? extends O> function, ListenableFuture<? extends I> inputFuture) {
        this.function = (AsyncFunction) Preconditions.checkNotNull(function);
        this.inputFuture = (ListenableFuture) Preconditions.checkNotNull(inputFuture);
    }

    public boolean cancel(boolean mayInterruptIfRunning) {
        if (!super.cancel(mayInterruptIfRunning)) {
            return false;
        }
        cancel(this.inputFuture, mayInterruptIfRunning);
        cancel(this.outputFuture, mayInterruptIfRunning);
        return true;
    }

    private void cancel(@Nullable Future<?> future, boolean mayInterruptIfRunning) {
        if (future != null) {
            future.cancel(mayInterruptIfRunning);
        }
    }

    public void run() {
        try {
            try {
                final ListenableFuture<? extends O> outputFuture = (ListenableFuture) Preconditions.checkNotNull(this.function.apply(Uninterruptibles.getUninterruptibly(this.inputFuture)), "AsyncFunction may not return null.");
                this.outputFuture = outputFuture;
                if (isCancelled()) {
                    outputFuture.cancel(wasInterrupted());
                    this.outputFuture = null;
                    return;
                }
                outputFuture.addListener(new Runnable() {
                    public void run() {
                        try {
                            Futures$ChainingListenableFuture.this.set(Uninterruptibles.getUninterruptibly(outputFuture));
                        } catch (CancellationException e) {
                            Futures$ChainingListenableFuture.this.cancel(false);
                        } catch (ExecutionException e2) {
                            Futures$ChainingListenableFuture.this.setException(e2.getCause());
                        } finally {
                            Futures$ChainingListenableFuture.this.outputFuture = null;
                        }
                    }
                }, MoreExecutors.directExecutor());
                this.function = null;
                this.inputFuture = null;
            } catch (UndeclaredThrowableException e) {
                setException(e.getCause());
            } catch (Throwable t) {
                setException(t);
            } finally {
                this.function = null;
                this.inputFuture = null;
            }
        } catch (CancellationException e2) {
            cancel(false);
            this.function = null;
            this.inputFuture = null;
        } catch (ExecutionException e3) {
            setException(e3.getCause());
            this.function = null;
            this.inputFuture = null;
        }
    }
}
