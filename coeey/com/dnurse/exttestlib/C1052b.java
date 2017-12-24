package com.dnurse.exttestlib;

class C1052b implements Runnable {
    final /* synthetic */ DnurseDeviceTest f163a;

    C1052b(DnurseDeviceTest dnurseDeviceTest) {
        this.f163a = dnurseDeviceTest;
    }

    public void run() {
        if (this.f163a.f127L.m34a().booleanValue()) {
            this.f163a.m57b();
        } else {
            this.f163a.f131P.postDelayed(this.f163a.f136a, (long) this.f163a.f155t);
        }
    }
}
