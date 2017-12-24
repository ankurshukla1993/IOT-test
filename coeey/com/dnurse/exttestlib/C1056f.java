package com.dnurse.exttestlib;

import java.util.TimerTask;

class C1056f extends TimerTask {
    final /* synthetic */ DnurseDeviceTest f167a;

    C1056f(DnurseDeviceTest dnurseDeviceTest) {
        this.f167a = dnurseDeviceTest;
    }

    public void run() {
        if (this.f167a.f146k == 7) {
            this.f167a.f124I = (byte) (this.f167a.f124I - 1);
            if (this.f167a.f124I > (byte) 0) {
                this.f167a.f130O.onMeasuring(this.f167a.f146k, this.f167a.f124I);
                return;
            }
            this.f167a.f132Q.cancel();
            this.f167a.m50a(3000);
        }
    }
}
