package com.ihealth.communication.manager;

class C2158m extends Thread {
    final /* synthetic */ C2146o f2017a;
    final /* synthetic */ C2155j f2018b;

    C2158m(C2155j c2155j, C2146o c2146o) {
        this.f2018b = c2155j;
        this.f2017a = c2146o;
    }

    public void run() {
        super.run();
        this.f2018b.f2011b = new C2134a(new C2159n(this));
        this.f2018b.f2011b.m1059a(null, C2155j.f2007c);
    }
}
