package com.google.common.util.concurrent;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class MoreExecutors$DirectExecutorService extends AbstractListeningExecutorService {
    private final Lock lock;
    private int runningTasks;
    private boolean shutdown;
    private final Condition termination;

    private MoreExecutors$DirectExecutorService() {
        this.lock = new ReentrantLock();
        this.termination = this.lock.newCondition();
        this.runningTasks = 0;
        this.shutdown = false;
    }

    public void execute(Runnable command) {
        startTask();
        try {
            command.run();
        } finally {
            endTask();
        }
    }

    public boolean isShutdown() {
        this.lock.lock();
        try {
            boolean z = this.shutdown;
            return z;
        } finally {
            this.lock.unlock();
        }
    }

    public void shutdown() {
        this.lock.lock();
        try {
            this.shutdown = true;
        } finally {
            this.lock.unlock();
        }
    }

    public List<Runnable> shutdownNow() {
        shutdown();
        return Collections.emptyList();
    }

    public boolean isTerminated() {
        this.lock.lock();
        try {
            boolean z = this.shutdown && this.runningTasks == 0;
            this.lock.unlock();
            return z;
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        long nanos = unit.toNanos(timeout);
        this.lock.lock();
        while (!isTerminated()) {
            if (nanos <= 0) {
                return false;
            }
            try {
                nanos = this.termination.awaitNanos(nanos);
            } finally {
                this.lock.unlock();
            }
        }
        this.lock.unlock();
        return true;
    }

    private void startTask() {
        this.lock.lock();
        try {
            if (isShutdown()) {
                throw new RejectedExecutionException("Executor already shutdown");
            }
            this.runningTasks++;
        } finally {
            this.lock.unlock();
        }
    }

    private void endTask() {
        this.lock.lock();
        try {
            this.runningTasks--;
            if (isTerminated()) {
                this.termination.signalAll();
            }
            this.lock.unlock();
        } catch (Throwable th) {
            this.lock.unlock();
        }
    }
}
