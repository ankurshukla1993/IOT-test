package com.ihealth.communication.utils;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class DataThreadPoolManager {
    Queue f2076a;
    final RejectedExecutionHandler f2077b;
    final Runnable f2078c;
    final ThreadPoolExecutor f2079d;
    final ScheduledExecutorService f2080e;
    final ScheduledFuture f2081f;

    private DataThreadPoolManager() {
        this.f2076a = new LinkedList();
        this.f2077b = new C2172a(this);
        this.f2078c = new C2173b(this);
        this.f2079d = new ThreadPoolExecutor(1, 1, 0, TimeUnit.SECONDS, new ArrayBlockingQueue(10), this.f2077b);
        this.f2080e = Executors.newScheduledThreadPool(1);
        this.f2081f = this.f2080e.scheduleAtFixedRate(this.f2078c, 0, 10, TimeUnit.MILLISECONDS);
    }

    private boolean m1208a() {
        return !this.f2076a.isEmpty();
    }

    public static final DataThreadPoolManager getInstance() {
        return C2174c.f2122a;
    }

    public void addExecuteTask(Runnable task) {
        if (task != null) {
            this.f2079d.execute(task);
            Log.m1214v("DataThreadPoolManager", "add a task and execute and active thread num = " + this.f2079d.getActiveCount());
        }
    }
}
