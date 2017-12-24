package io.reactivex.internal.schedulers;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicLong;

public final class RxThreadFactory extends AtomicLong implements ThreadFactory {
    private static final long serialVersionUID = -7789753024099756196L;
    final boolean nonBlocking;
    final String prefix;
    final int priority;

    static final class RxCustomThread extends Thread implements NonBlockingThread {
        RxCustomThread(Runnable run, String name) {
            super(run, name);
        }
    }

    public RxThreadFactory(String prefix) {
        this(prefix, 5, false);
    }

    public RxThreadFactory(String prefix, int priority) {
        this(prefix, priority, false);
    }

    public RxThreadFactory(String prefix, int priority, boolean nonBlocking) {
        this.prefix = prefix;
        this.priority = priority;
        this.nonBlocking = nonBlocking;
    }

    public Thread newThread(Runnable r) {
        String name = new StringBuilder(this.prefix).append('-').append(incrementAndGet()).toString();
        Thread t = this.nonBlocking ? new RxCustomThread(r, name) : new Thread(r, name);
        t.setPriority(this.priority);
        t.setDaemon(true);
        return t;
    }

    public String toString() {
        return "RxThreadFactory[" + this.prefix + "]";
    }
}
