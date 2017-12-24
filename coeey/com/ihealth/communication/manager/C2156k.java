package com.ihealth.communication.manager;

import com.ihealth.communication.base.ble.BleComm;

class C2156k extends Thread {
    final /* synthetic */ C2146o f2012a;
    final /* synthetic */ BleComm f2013b;
    final /* synthetic */ long f2014c;
    final /* synthetic */ C2155j f2015d;

    C2156k(C2155j c2155j, C2146o c2146o, BleComm bleComm, long j) {
        this.f2015d = c2155j;
        this.f2012a = c2146o;
        this.f2013b = bleComm;
        this.f2014c = j;
    }

    public void run() {
        super.run();
        this.f2015d.f2011b = new C2134a(new C2157l(this));
        this.f2015d.f2011b.m1059a(this.f2013b, this.f2015d.m1188a(this.f2014c));
    }
}
