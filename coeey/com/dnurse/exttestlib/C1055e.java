package com.dnurse.exttestlib;

class C1055e implements Runnable {
    final /* synthetic */ DnurseDeviceTest f166a;

    C1055e(DnurseDeviceTest dnurseDeviceTest) {
        this.f166a = dnurseDeviceTest;
    }

    public void run() {
        switch (this.f166a.f146k) {
            case 1:
                if (!this.f166a.f128M.f53a) {
                    this.f166a.m58b(14);
                    return;
                } else if (this.f166a.f127L.m35b()) {
                    this.f166a.m58b(15);
                    return;
                } else {
                    this.f166a.m58b(16);
                    return;
                }
            case 2:
                this.f166a.m58b(16);
                return;
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case 8:
                this.f166a.m58b(17);
                return;
            default:
                return;
        }
    }
}
