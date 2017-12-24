package com.dnurse.exttestlib;

class C1053c implements Runnable {
    final /* synthetic */ DnurseDeviceTest f164a;

    C1053c(DnurseDeviceTest dnurseDeviceTest) {
        this.f164a = dnurseDeviceTest;
    }

    public void run() {
        if (this.f164a.f146k == 0) {
            return;
        }
        if (this.f164a.f127L.m34a().booleanValue()) {
            this.f164a.m58b(0);
            if (this.f164a.f122G) {
                this.f164a.wakeupDevice();
                return;
            }
            return;
        }
        this.f164a.f131P.postDelayed(this.f164a.f137b, (long) this.f164a.f157v);
    }
}
