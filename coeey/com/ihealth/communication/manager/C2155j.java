package com.ihealth.communication.manager;

import com.ihealth.communication.base.ble.BleComm;

class C2155j {
    private static final C2135b[] f2007c = new C2135b[]{C2135b.BT_DISCOVERY, C2135b.IDLE, C2135b.BT_DISCOVERY, C2135b.IDLE, C2135b.BT_DISCOVERY, C2135b.IDLE, C2135b.BT_DISCOVERY, C2135b.STOP};
    private static final C2135b[] f2008d = new C2135b[]{C2135b.BLE_DISCOVERY_LONG, C2135b.STOP};
    private static final C2135b[] f2009e = new C2135b[]{C2135b.BLE_DISCOVERY, C2135b.IDLE, C2135b.BT_DISCOVERY, C2135b.IDLE, C2135b.BLE_DISCOVERY, C2135b.IDLE, C2135b.BT_DISCOVERY, C2135b.STOP};
    private boolean f2010a = false;
    private C2134a f2011b;

    C2155j() {
    }

    private C2135b[] m1188a(long j) {
        return j < iHealthDevicesManager.DISCOVERY_BP3M ? f2008d : (4194303 & j) > 0 ? f2009e : f2007c;
    }

    void m1191a() {
        this.f2010a = false;
        if (this.f2011b != null && this.f2011b.m1060b()) {
            this.f2011b.m1058a();
        }
    }

    void m1192a(BleComm bleComm, long j, C2146o c2146o) {
        m1191a();
        this.f2010a = true;
        new C2156k(this, c2146o, bleComm, j).start();
    }

    void m1193a(C2146o c2146o) {
        m1191a();
        this.f2010a = true;
        new C2158m(this, c2146o).start();
    }

    boolean m1194b() {
        return this.f2010a;
    }
}
