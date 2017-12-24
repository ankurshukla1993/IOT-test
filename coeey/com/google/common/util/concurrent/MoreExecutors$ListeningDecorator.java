package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

class MoreExecutors$ListeningDecorator extends AbstractListeningExecutorService {
    private final ExecutorService delegate;

    MoreExecutors$ListeningDecorator(ExecutorService delegate) {
        this.delegate = (ExecutorService) Preconditions.checkNotNull(delegate);
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        return this.delegate.awaitTermination(timeout, unit);
    }

    public boolean isShutdown() {
        return this.delegate.isShutdown();
    }

    public boolean isTerminated() {
        return this.delegate.isTerminated();
    }

    public void shutdown() {
        this.delegate.shutdown();
    }

    public List<Runnable> shutdownNow() {
        return this.delegate.shutdownNow();
    }

    public void execute(Runnable command) {
        this.delegate.execute(command);
    }
}
