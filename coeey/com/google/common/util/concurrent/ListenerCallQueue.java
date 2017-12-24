package com.google.common.util.concurrent;

import com.google.common.base.Preconditions;
import com.google.common.collect.Queues;
import java.util.Queue;
import java.util.concurrent.Executor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.concurrent.GuardedBy;

final class ListenerCallQueue<L> implements Runnable {
    private static final Logger logger = Logger.getLogger(ListenerCallQueue.class.getName());
    private final Executor executor;
    @GuardedBy("this")
    private boolean isThreadScheduled;
    private final L listener;
    @GuardedBy("this")
    private final Queue<Callback<L>> waitQueue = Queues.newArrayDeque();

    static abstract class Callback<L> {
        private final String methodCall;

        abstract void call(L l);

        Callback(String methodCall) {
            this.methodCall = methodCall;
        }

        void enqueueOn(Iterable<ListenerCallQueue<L>> queues) {
            for (ListenerCallQueue<L> queue : queues) {
                queue.add(this);
            }
        }
    }

    ListenerCallQueue(L listener, Executor executor) {
        this.listener = Preconditions.checkNotNull(listener);
        this.executor = (Executor) Preconditions.checkNotNull(executor);
    }

    synchronized void add(Callback<L> callback) {
        this.waitQueue.add(callback);
    }

    void execute() {
        boolean scheduleTaskRunner = false;
        synchronized (this) {
            if (!this.isThreadScheduled) {
                this.isThreadScheduled = true;
                scheduleTaskRunner = true;
            }
        }
        if (scheduleTaskRunner) {
            try {
                this.executor.execute(this);
            } catch (RuntimeException e) {
                synchronized (this) {
                    this.isThreadScheduled = false;
                    Logger logger = logger;
                    Level level = Level.SEVERE;
                    String valueOf = String.valueOf(String.valueOf(this.listener));
                    String valueOf2 = String.valueOf(String.valueOf(this.executor));
                    logger.log(level, new StringBuilder((valueOf.length() + 42) + valueOf2.length()).append("Exception while running callbacks for ").append(valueOf).append(" on ").append(valueOf2).toString(), e);
                    throw e;
                }
            }
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void run() {
        /*
        r10 = this;
        r2 = 1;
    L_0x0001:
        monitor-enter(r10);	 Catch:{ all -> 0x006c }
        r3 = r10.isThreadScheduled;	 Catch:{ all -> 0x0075 }
        com.google.common.base.Preconditions.checkState(r3);	 Catch:{ all -> 0x0075 }
        r3 = r10.waitQueue;	 Catch:{ all -> 0x0075 }
        r1 = r3.poll();	 Catch:{ all -> 0x0075 }
        r1 = (com.google.common.util.concurrent.ListenerCallQueue.Callback) r1;	 Catch:{ all -> 0x0075 }
        if (r1 != 0) goto L_0x001e;
    L_0x0011:
        r3 = 0;
        r10.isThreadScheduled = r3;	 Catch:{ all -> 0x0075 }
        r2 = 0;
        monitor-exit(r10);	 Catch:{ all -> 0x0075 }
        if (r2 == 0) goto L_0x001d;
    L_0x0018:
        monitor-enter(r10);
        r3 = 0;
        r10.isThreadScheduled = r3;	 Catch:{ all -> 0x0078 }
        monitor-exit(r10);	 Catch:{ all -> 0x0078 }
    L_0x001d:
        return;
    L_0x001e:
        monitor-exit(r10);	 Catch:{ all -> 0x0075 }
        r3 = r10.listener;	 Catch:{ RuntimeException -> 0x0025 }
        r1.call(r3);	 Catch:{ RuntimeException -> 0x0025 }
        goto L_0x0001;
    L_0x0025:
        r0 = move-exception;
        r3 = logger;	 Catch:{ all -> 0x006c }
        r4 = java.util.logging.Level.SEVERE;	 Catch:{ all -> 0x006c }
        r5 = r10.listener;	 Catch:{ all -> 0x006c }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ all -> 0x006c }
        r5 = java.lang.String.valueOf(r5);	 Catch:{ all -> 0x006c }
        r6 = r1.methodCall;	 Catch:{ all -> 0x006c }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x006c }
        r6 = java.lang.String.valueOf(r6);	 Catch:{ all -> 0x006c }
        r7 = new java.lang.StringBuilder;	 Catch:{ all -> 0x006c }
        r8 = r5.length();	 Catch:{ all -> 0x006c }
        r8 = r8 + 37;
        r9 = r6.length();	 Catch:{ all -> 0x006c }
        r8 = r8 + r9;
        r7.<init>(r8);	 Catch:{ all -> 0x006c }
        r8 = "Exception while executing callback: ";
        r7 = r7.append(r8);	 Catch:{ all -> 0x006c }
        r5 = r7.append(r5);	 Catch:{ all -> 0x006c }
        r7 = ".";
        r5 = r5.append(r7);	 Catch:{ all -> 0x006c }
        r5 = r5.append(r6);	 Catch:{ all -> 0x006c }
        r5 = r5.toString();	 Catch:{ all -> 0x006c }
        r3.log(r4, r5, r0);	 Catch:{ all -> 0x006c }
        goto L_0x0001;
    L_0x006c:
        r3 = move-exception;
        if (r2 == 0) goto L_0x0074;
    L_0x006f:
        monitor-enter(r10);
        r4 = 0;
        r10.isThreadScheduled = r4;	 Catch:{ all -> 0x007b }
        monitor-exit(r10);	 Catch:{ all -> 0x007b }
    L_0x0074:
        throw r3;
    L_0x0075:
        r3 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0075 }
        throw r3;	 Catch:{ all -> 0x006c }
    L_0x0078:
        r3 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x0078 }
        throw r3;
    L_0x007b:
        r3 = move-exception;
        monitor-exit(r10);	 Catch:{ all -> 0x007b }
        throw r3;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.ListenerCallQueue.run():void");
    }
}
