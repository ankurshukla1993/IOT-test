package com.lifesense.ble.p003a;

class C2218f implements Runnable {
    final /* synthetic */ C2214b f2300a;

    C2218f(C2214b c2214b) {
        this.f2300a = c2214b;
    }

    public void run() {
        try {
            Thread.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.f2300a.f2281d = this.f2300a.f2279b.getRemoteDevice(this.f2300a.f2291n);
        this.f2300a.f2280c = this.f2300a.f2281d.connectGatt(this.f2300a.f2282e, false, this.f2300a.f2296s);
        this.f2300a.m1559a(100);
    }
}
