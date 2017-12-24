package com.ihealth.communication.utils;

class C2173b implements Runnable {
    final /* synthetic */ DataThreadPoolManager f2121a;

    C2173b(DataThreadPoolManager dataThreadPoolManager) {
        this.f2121a = dataThreadPoolManager;
    }

    public void run() {
        if (this.f2121a.m1208a()) {
            this.f2121a.f2079d.execute((Runnable) this.f2121a.f2076a.poll());
        }
    }
}
