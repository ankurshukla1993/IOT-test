package com.facebook.common.executors;

import com.facebook.common.logging.FLog;
import java.util.List;
import java.util.concurrent.AbstractExecutorService;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class ConstrainedExecutorService extends AbstractExecutorService {
    private static final Class<?> TAG = ConstrainedExecutorService.class;
    private final Executor mExecutor;
    private volatile int mMaxConcurrency;
    private final AtomicInteger mMaxQueueSize;
    private final String mName;
    private final AtomicInteger mPendingWorkers;
    private final Worker mTaskRunner;
    private final BlockingQueue<Runnable> mWorkQueue;

    private class Worker implements Runnable {
        private Worker() {
        }

        /* JADX WARNING: inconsistent code. */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
            r7 = this;
            r2 = com.facebook.common.executors.ConstrainedExecutorService.this;	 Catch:{ all -> 0x003d }
            r2 = r2.mWorkQueue;	 Catch:{ all -> 0x003d }
            r0 = r2.poll();	 Catch:{ all -> 0x003d }
            r0 = (java.lang.Runnable) r0;	 Catch:{ all -> 0x003d }
            if (r0 == 0) goto L_0x002d;
        L_0x000e:
            r0.run();	 Catch:{ all -> 0x003d }
        L_0x0011:
            r2 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r2 = r2.mPendingWorkers;
            r1 = r2.decrementAndGet();
            r2 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r2 = r2.mWorkQueue;
            r2 = r2.isEmpty();
            if (r2 != 0) goto L_0x005a;
        L_0x0027:
            r2 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r2.startWorkerIfNeeded();
        L_0x002c:
            return;
        L_0x002d:
            r2 = com.facebook.common.executors.ConstrainedExecutorService.TAG;	 Catch:{ all -> 0x003d }
            r3 = "%s: Worker has nothing to run";
            r4 = com.facebook.common.executors.ConstrainedExecutorService.this;	 Catch:{ all -> 0x003d }
            r4 = r4.mName;	 Catch:{ all -> 0x003d }
            com.facebook.common.logging.FLog.m132v(r2, r3, r4);	 Catch:{ all -> 0x003d }
            goto L_0x0011;
        L_0x003d:
            r2 = move-exception;
            r3 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r3 = r3.mPendingWorkers;
            r1 = r3.decrementAndGet();
            r3 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r3 = r3.mWorkQueue;
            r3 = r3.isEmpty();
            if (r3 != 0) goto L_0x006e;
        L_0x0054:
            r3 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r3.startWorkerIfNeeded();
        L_0x0059:
            throw r2;
        L_0x005a:
            r2 = com.facebook.common.executors.ConstrainedExecutorService.TAG;
            r3 = "%s: worker finished; %d workers left";
            r4 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r4 = r4.mName;
            r5 = java.lang.Integer.valueOf(r1);
            com.facebook.common.logging.FLog.m133v(r2, r3, r4, r5);
            goto L_0x002c;
        L_0x006e:
            r3 = com.facebook.common.executors.ConstrainedExecutorService.TAG;
            r4 = "%s: worker finished; %d workers left";
            r5 = com.facebook.common.executors.ConstrainedExecutorService.this;
            r5 = r5.mName;
            r6 = java.lang.Integer.valueOf(r1);
            com.facebook.common.logging.FLog.m133v(r3, r4, r5, r6);
            goto L_0x0059;
            */
            throw new UnsupportedOperationException("Method not decompiled: com.facebook.common.executors.ConstrainedExecutorService.Worker.run():void");
        }
    }

    public ConstrainedExecutorService(String name, int maxConcurrency, Executor executor, BlockingQueue<Runnable> workQueue) {
        if (maxConcurrency <= 0) {
            throw new IllegalArgumentException("max concurrency must be > 0");
        }
        this.mName = name;
        this.mExecutor = executor;
        this.mMaxConcurrency = maxConcurrency;
        this.mWorkQueue = workQueue;
        this.mTaskRunner = new Worker();
        this.mPendingWorkers = new AtomicInteger(0);
        this.mMaxQueueSize = new AtomicInteger(0);
    }

    public static ConstrainedExecutorService newConstrainedExecutor(String name, int maxConcurrency, int queueSize, Executor executor) {
        return new ConstrainedExecutorService(name, maxConcurrency, executor, new LinkedBlockingQueue(queueSize));
    }

    public boolean isIdle() {
        return this.mWorkQueue.isEmpty() && this.mPendingWorkers.get() == 0;
    }

    public void execute(Runnable runnable) {
        if (runnable == null) {
            throw new NullPointerException("runnable parameter is null");
        } else if (this.mWorkQueue.offer(runnable)) {
            int queueSize = this.mWorkQueue.size();
            int maxSize = this.mMaxQueueSize.get();
            if (queueSize > maxSize && this.mMaxQueueSize.compareAndSet(maxSize, queueSize)) {
                FLog.m133v(TAG, "%s: max pending work in queue = %d", this.mName, Integer.valueOf(queueSize));
            }
            startWorkerIfNeeded();
        } else {
            throw new RejectedExecutionException(this.mName + " queue is full, size=" + this.mWorkQueue.size());
        }
    }

    private void startWorkerIfNeeded() {
        int currentCount = this.mPendingWorkers.get();
        while (currentCount < this.mMaxConcurrency) {
            int updatedCount = currentCount + 1;
            if (this.mPendingWorkers.compareAndSet(currentCount, updatedCount)) {
                FLog.m134v(TAG, "%s: starting worker %d of %d", this.mName, Integer.valueOf(updatedCount), Integer.valueOf(this.mMaxConcurrency));
                this.mExecutor.execute(this.mTaskRunner);
                return;
            }
            FLog.m132v(TAG, "%s: race in startWorkerIfNeeded; retrying", this.mName);
            currentCount = this.mPendingWorkers.get();
        }
    }

    public void shutdown() {
        throw new UnsupportedOperationException();
    }

    public List<Runnable> shutdownNow() {
        throw new UnsupportedOperationException();
    }

    public boolean isShutdown() {
        return false;
    }

    public boolean isTerminated() {
        return false;
    }

    public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
        throw new UnsupportedOperationException();
    }
}
