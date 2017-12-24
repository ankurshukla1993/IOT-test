package com.ihealth.communication.utils;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

class C2172a implements RejectedExecutionHandler {
    final /* synthetic */ DataThreadPoolManager f2120a;

    C2172a(DataThreadPoolManager dataThreadPoolManager) {
        this.f2120a = dataThreadPoolManager;
    }

    public void rejectedExecution(Runnable runnable, ThreadPoolExecutor executor) {
        this.f2120a.f2076a.offer(runnable);
    }
}
