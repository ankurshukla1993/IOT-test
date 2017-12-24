package com.google.common.util.concurrent;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Throwables;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import javax.annotation.concurrent.GuardedBy;

@Beta
public final class Monitor {
    @GuardedBy("lock")
    private Guard activeGuards;
    private final boolean fair;
    private final ReentrantLock lock;

    @Beta
    public static abstract class Guard {
        final Condition condition;
        final Monitor monitor;
        @GuardedBy("monitor.lock")
        Guard next;
        @GuardedBy("monitor.lock")
        int waiterCount = 0;

        public abstract boolean isSatisfied();

        protected Guard(Monitor monitor) {
            this.monitor = (Monitor) Preconditions.checkNotNull(monitor, "monitor");
            this.condition = monitor.lock.newCondition();
        }
    }

    public boolean enterIf(com.google.common.util.concurrent.Monitor.Guard r5, long r6, java.util.concurrent.TimeUnit r8) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        r1 = r5.monitor;
        if (r1 == r4) goto L_0x000a;
    L_0x0004:
        r1 = new java.lang.IllegalMonitorStateException;
        r1.<init>();
        throw r1;
    L_0x000a:
        r1 = r4.enter(r6, r8);
        if (r1 != 0) goto L_0x0012;
    L_0x0010:
        r0 = 0;
    L_0x0011:
        return r0;
    L_0x0012:
        r0 = 0;
        r0 = r5.isSatisfied();	 Catch:{ all -> 0x001f }
        if (r0 != 0) goto L_0x0011;
    L_0x0019:
        r1 = r4.lock;
        r1.unlock();
        goto L_0x0011;
    L_0x001f:
        r1 = move-exception;
        if (r0 != 0) goto L_0x0027;
    L_0x0022:
        r2 = r4.lock;
        r2.unlock();
    L_0x0027:
        throw r1;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterIf(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean enterIfInterruptibly(com.google.common.util.concurrent.Monitor.Guard r5, long r6, java.util.concurrent.TimeUnit r8) throws java.lang.InterruptedException {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r4 = this;
        r2 = r5.monitor;
        if (r2 == r4) goto L_0x000a;
    L_0x0004:
        r2 = new java.lang.IllegalMonitorStateException;
        r2.<init>();
        throw r2;
    L_0x000a:
        r0 = r4.lock;
        r2 = r0.tryLock(r6, r8);
        if (r2 != 0) goto L_0x0014;
    L_0x0012:
        r1 = 0;
    L_0x0013:
        return r1;
    L_0x0014:
        r1 = 0;
        r1 = r5.isSatisfied();	 Catch:{ all -> 0x001f }
        if (r1 != 0) goto L_0x0013;
    L_0x001b:
        r0.unlock();
        goto L_0x0013;
    L_0x001f:
        r2 = move-exception;
        if (r1 != 0) goto L_0x0025;
    L_0x0022:
        r0.unlock();
    L_0x0025:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.enterIfInterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public boolean tryEnterIf(com.google.common.util.concurrent.Monitor.Guard r4) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r3 = this;
        r2 = r4.monitor;
        if (r2 == r3) goto L_0x000a;
    L_0x0004:
        r2 = new java.lang.IllegalMonitorStateException;
        r2.<init>();
        throw r2;
    L_0x000a:
        r0 = r3.lock;
        r2 = r0.tryLock();
        if (r2 != 0) goto L_0x0014;
    L_0x0012:
        r1 = 0;
    L_0x0013:
        return r1;
    L_0x0014:
        r1 = 0;
        r1 = r4.isSatisfied();	 Catch:{ all -> 0x001f }
        if (r1 != 0) goto L_0x0013;
    L_0x001b:
        r0.unlock();
        goto L_0x0013;
    L_0x001f:
        r2 = move-exception;
        if (r1 != 0) goto L_0x0025;
    L_0x0022:
        r0.unlock();
    L_0x0025:
        throw r2;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.tryEnterIf(com.google.common.util.concurrent.Monitor$Guard):boolean");
    }

    public boolean waitForUninterruptibly(com.google.common.util.concurrent.Monitor.Guard r11, long r12, java.util.concurrent.TimeUnit r14) {
        /* JADX: method processing error */
/*
Error: java.util.NoSuchElementException
	at java.util.HashMap$HashIterator.nextNode(HashMap.java:1431)
	at java.util.HashMap$KeyIterator.next(HashMap.java:1453)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.applyRemove(BlockFinallyExtract.java:535)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.extractFinally(BlockFinallyExtract.java:175)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.processExceptionHandler(BlockFinallyExtract.java:79)
	at jadx.core.dex.visitors.blocksmaker.BlockFinallyExtract.visit(BlockFinallyExtract.java:51)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:31)
	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:17)
	at jadx.core.ProcessClass.process(ProcessClass.java:37)
	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:59)
	at jadx.core.ProcessClass.process(ProcessClass.java:42)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
*/
        /*
        r10 = this;
        r6 = r14.toNanos(r12);
        r5 = r11.monitor;
        if (r5 != r10) goto L_0x0018;
    L_0x0008:
        r5 = 1;
    L_0x0009:
        r8 = r10.lock;
        r8 = r8.isHeldByCurrentThread();
        r5 = r5 & r8;
        if (r5 != 0) goto L_0x001a;
    L_0x0012:
        r5 = new java.lang.IllegalMonitorStateException;
        r5.<init>();
        throw r5;
    L_0x0018:
        r5 = 0;
        goto L_0x0009;
    L_0x001a:
        r5 = r11.isSatisfied();
        if (r5 == 0) goto L_0x0022;
    L_0x0020:
        r5 = 1;
    L_0x0021:
        return r5;
    L_0x0022:
        r4 = 1;
        r8 = java.lang.System.nanoTime();
        r0 = r8 + r6;
        r3 = java.lang.Thread.interrupted();
    L_0x002d:
        r5 = r10.awaitNanos(r11, r6, r4);	 Catch:{ InterruptedException -> 0x003b, all -> 0x0056 }
        if (r3 == 0) goto L_0x0021;
    L_0x0033:
        r8 = java.lang.Thread.currentThread();
        r8.interrupt();
        goto L_0x0021;
    L_0x003b:
        r2 = move-exception;
        r3 = 1;
        r5 = r11.isSatisfied();	 Catch:{ InterruptedException -> 0x003b, all -> 0x0056 }
        if (r5 == 0) goto L_0x004e;
    L_0x0043:
        r5 = 1;
        if (r3 == 0) goto L_0x0021;
    L_0x0046:
        r8 = java.lang.Thread.currentThread();
        r8.interrupt();
        goto L_0x0021;
    L_0x004e:
        r4 = 0;
        r8 = java.lang.System.nanoTime();	 Catch:{ InterruptedException -> 0x003b, all -> 0x0056 }
        r6 = r0 - r8;
        goto L_0x002d;
    L_0x0056:
        r5 = move-exception;
        if (r3 == 0) goto L_0x0060;
    L_0x0059:
        r8 = java.lang.Thread.currentThread();
        r8.interrupt();
    L_0x0060:
        throw r5;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.common.util.concurrent.Monitor.waitForUninterruptibly(com.google.common.util.concurrent.Monitor$Guard, long, java.util.concurrent.TimeUnit):boolean");
    }

    public Monitor() {
        this(false);
    }

    public Monitor(boolean fair) {
        this.activeGuards = null;
        this.fair = fair;
        this.lock = new ReentrantLock(fair);
    }

    public void enter() {
        this.lock.lock();
    }

    public void enterInterruptibly() throws InterruptedException {
        this.lock.lockInterruptibly();
    }

    public boolean enter(long time, TimeUnit unit) {
        long timeoutNanos = unit.toNanos(time);
        ReentrantLock lock = this.lock;
        if (!this.fair && lock.tryLock()) {
            return true;
        }
        long deadline = System.nanoTime() + timeoutNanos;
        boolean interrupted = Thread.interrupted();
        while (true) {
            try {
                boolean tryLock = lock.tryLock(timeoutNanos, TimeUnit.NANOSECONDS);
                break;
            } catch (InterruptedException e) {
                interrupted = true;
                timeoutNanos = deadline - System.nanoTime();
            } catch (Throwable th) {
                if (1 != null) {
                    Thread.currentThread().interrupt();
                }
            }
        }
        if (!interrupted) {
            return tryLock;
        }
        Thread.currentThread().interrupt();
        return tryLock;
    }

    public boolean enterInterruptibly(long time, TimeUnit unit) throws InterruptedException {
        return this.lock.tryLock(time, unit);
    }

    public boolean tryEnter() {
        return this.lock.tryLock();
    }

    public void enterWhen(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
        lock.lockInterruptibly();
        boolean satisfied = false;
        try {
            if (!guard.isSatisfied()) {
                await(guard, signalBeforeWaiting);
            }
            satisfied = true;
        } finally {
            if (!satisfied) {
                leave();
            }
        }
    }

    public void enterWhenUninterruptibly(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
        lock.lock();
        boolean satisfied = false;
        try {
            if (!guard.isSatisfied()) {
                awaitUninterruptibly(guard, signalBeforeWaiting);
            }
            satisfied = true;
        } finally {
            if (!satisfied) {
                leave();
            }
        }
    }

    public boolean enterWhen(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        long timeoutNanos = unit.toNanos(time);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        boolean reentrant = lock.isHeldByCurrentThread();
        if (this.fair || !lock.tryLock()) {
            long deadline = System.nanoTime() + timeoutNanos;
            if (!lock.tryLock(time, unit)) {
                return false;
            }
            timeoutNanos = deadline - System.nanoTime();
        }
        try {
            boolean satisfied = guard.isSatisfied() || awaitNanos(guard, timeoutNanos, reentrant);
            if (satisfied) {
                return satisfied;
            }
            if (false && !reentrant) {
                try {
                    signalNextWaiter();
                } catch (Throwable th) {
                    lock.unlock();
                }
            }
            lock.unlock();
            return satisfied;
        } catch (Throwable th2) {
            lock.unlock();
        }
    }

    public boolean enterWhenUninterruptibly(Guard guard, long time, TimeUnit unit) {
        long timeoutNanos = unit.toNanos(time);
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        long deadline = System.nanoTime() + timeoutNanos;
        boolean signalBeforeWaiting = lock.isHeldByCurrentThread();
        boolean interrupted = Thread.interrupted();
        try {
            boolean z;
            if (this.fair || !lock.tryLock()) {
                boolean locked = false;
                do {
                    try {
                        locked = lock.tryLock(timeoutNanos, TimeUnit.NANOSECONDS);
                        if (!locked) {
                            z = false;
                            if (interrupted) {
                                Thread.currentThread().interrupt();
                            }
                        }
                    } catch (InterruptedException e) {
                        interrupted = true;
                    }
                    timeoutNanos = deadline - System.nanoTime();
                } while (!locked);
                while (true) {
                    try {
                        break;
                    } catch (InterruptedException e2) {
                        interrupted = true;
                        signalBeforeWaiting = false;
                        timeoutNanos = deadline - System.nanoTime();
                    } catch (Throwable th) {
                        if (!false) {
                            lock.unlock();
                        }
                    }
                }
                z = guard.isSatisfied() || awaitNanos(guard, timeoutNanos, signalBeforeWaiting);
                if (!z) {
                    lock.unlock();
                }
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            } else {
                while (true) {
                    break;
                }
                if (!guard.isSatisfied()) {
                }
                if (z) {
                    lock.unlock();
                }
                if (interrupted) {
                    Thread.currentThread().interrupt();
                }
            }
            return z;
        } catch (Throwable th2) {
            if (interrupted) {
                Thread.currentThread().interrupt();
            }
        }
    }

    public boolean enterIf(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        lock.lock();
        boolean satisfied = false;
        try {
            satisfied = guard.isSatisfied();
            return satisfied;
        } finally {
            if (!satisfied) {
                lock.unlock();
            }
        }
    }

    public boolean enterIfInterruptibly(Guard guard) throws InterruptedException {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        ReentrantLock lock = this.lock;
        lock.lockInterruptibly();
        boolean satisfied = false;
        try {
            satisfied = guard.isSatisfied();
            return satisfied;
        } finally {
            if (!satisfied) {
                lock.unlock();
            }
        }
    }

    public void waitFor(Guard guard) throws InterruptedException {
        if (((guard.monitor == this ? 1 : 0) & this.lock.isHeldByCurrentThread()) == 0) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            await(guard, true);
        }
    }

    public void waitForUninterruptibly(Guard guard) {
        if (((guard.monitor == this ? 1 : 0) & this.lock.isHeldByCurrentThread()) == 0) {
            throw new IllegalMonitorStateException();
        } else if (!guard.isSatisfied()) {
            awaitUninterruptibly(guard, true);
        }
    }

    public boolean waitFor(Guard guard, long time, TimeUnit unit) throws InterruptedException {
        int i;
        long timeoutNanos = unit.toNanos(time);
        if (guard.monitor == this) {
            i = 1;
        } else {
            i = 0;
        }
        if ((i & this.lock.isHeldByCurrentThread()) == 0) {
            throw new IllegalMonitorStateException();
        } else if (guard.isSatisfied() || awaitNanos(guard, timeoutNanos, true)) {
            return true;
        } else {
            return false;
        }
    }

    public void leave() {
        ReentrantLock lock = this.lock;
        try {
            if (lock.getHoldCount() == 1) {
                signalNextWaiter();
            }
            lock.unlock();
        } catch (Throwable th) {
            lock.unlock();
        }
    }

    public boolean isFair() {
        return this.fair;
    }

    public boolean isOccupied() {
        return this.lock.isLocked();
    }

    public boolean isOccupiedByCurrentThread() {
        return this.lock.isHeldByCurrentThread();
    }

    public int getOccupiedDepth() {
        return this.lock.getHoldCount();
    }

    public int getQueueLength() {
        return this.lock.getQueueLength();
    }

    public boolean hasQueuedThreads() {
        return this.lock.hasQueuedThreads();
    }

    public boolean hasQueuedThread(Thread thread) {
        return this.lock.hasQueuedThread(thread);
    }

    public boolean hasWaiters(Guard guard) {
        return getWaitQueueLength(guard) > 0;
    }

    public int getWaitQueueLength(Guard guard) {
        if (guard.monitor != this) {
            throw new IllegalMonitorStateException();
        }
        this.lock.lock();
        try {
            int i = guard.waiterCount;
            return i;
        } finally {
            this.lock.unlock();
        }
    }

    @GuardedBy("lock")
    private void signalNextWaiter() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            if (isSatisfied(guard)) {
                guard.condition.signal();
                return;
            }
        }
    }

    @GuardedBy("lock")
    private boolean isSatisfied(Guard guard) {
        try {
            return guard.isSatisfied();
        } catch (Throwable throwable) {
            signalAllWaiters();
            RuntimeException propagate = Throwables.propagate(throwable);
        }
    }

    @GuardedBy("lock")
    private void signalAllWaiters() {
        for (Guard guard = this.activeGuards; guard != null; guard = guard.next) {
            guard.condition.signalAll();
        }
    }

    @GuardedBy("lock")
    private void beginWaitingFor(Guard guard) {
        int waiters = guard.waiterCount;
        guard.waiterCount = waiters + 1;
        if (waiters == 0) {
            guard.next = this.activeGuards;
            this.activeGuards = guard;
        }
    }

    @GuardedBy("lock")
    private void endWaitingFor(Guard guard) {
        int waiters = guard.waiterCount - 1;
        guard.waiterCount = waiters;
        if (waiters == 0) {
            Guard p = this.activeGuards;
            Guard pred = null;
            while (p != guard) {
                pred = p;
                p = p.next;
            }
            if (pred == null) {
                this.activeGuards = p.next;
            } else {
                pred.next = p.next;
            }
            p.next = null;
        }
    }

    @GuardedBy("lock")
    private void await(Guard guard, boolean signalBeforeWaiting) throws InterruptedException {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        while (true) {
            try {
                guard.condition.await();
                if (guard.isSatisfied()) {
                    break;
                }
            } finally {
                endWaitingFor(guard);
            }
        }
    }

    @GuardedBy("lock")
    private void awaitUninterruptibly(Guard guard, boolean signalBeforeWaiting) {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        while (true) {
            try {
                guard.condition.awaitUninterruptibly();
                if (guard.isSatisfied()) {
                    break;
                }
            } finally {
                endWaitingFor(guard);
            }
        }
    }

    @GuardedBy("lock")
    private boolean awaitNanos(Guard guard, long nanos, boolean signalBeforeWaiting) throws InterruptedException {
        if (signalBeforeWaiting) {
            signalNextWaiter();
        }
        beginWaitingFor(guard);
        while (nanos >= 0) {
            try {
                nanos = guard.condition.awaitNanos(nanos);
                if (guard.isSatisfied()) {
                    return true;
                }
            } finally {
                endWaitingFor(guard);
            }
        }
        endWaitingFor(guard);
        return false;
    }
}
