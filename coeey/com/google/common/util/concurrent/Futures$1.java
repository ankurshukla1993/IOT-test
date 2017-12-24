package com.google.common.util.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicBoolean;

class Futures$1 implements Runnable {
    final /* synthetic */ Executor val$delegateExecutor;
    final /* synthetic */ Runnable val$delegateTask;
    final /* synthetic */ AbstractFuture val$outputFuture;

    Futures$1(Executor executor, Runnable runnable, AbstractFuture abstractFuture) {
        this.val$delegateExecutor = executor;
        this.val$delegateTask = runnable;
        this.val$outputFuture = abstractFuture;
    }

    public void run() {
        final AtomicBoolean thrownFromDelegate = new AtomicBoolean(true);
        try {
            this.val$delegateExecutor.execute(new Runnable() {
                public void run() {
                    thrownFromDelegate.set(false);
                    Futures$1.this.val$delegateTask.run();
                }
            });
        } catch (RejectedExecutionException e) {
            if (thrownFromDelegate.get()) {
                this.val$outputFuture.setException(e);
            }
        }
    }
}
