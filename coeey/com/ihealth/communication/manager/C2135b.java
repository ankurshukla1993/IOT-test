package com.ihealth.communication.manager;

import com.ihealth.communication.base.ble.BleComm;

enum C2135b {
    IDLE(500, new C2137c()),
    BLE_DISCOVERY(3000, new C2138d()),
    BLE_DISCOVERY_LONG(12000, new C2139e()),
    BT_DISCOVERY(3000, new C2140f()),
    STOP(0, new C2141g());
    
    private long f1939f;
    private C2136h f1940g;

    private C2135b(long j, C2136h c2136h) {
        this.f1939f = 0;
        this.f1940g = null;
        this.f1939f = j;
        this.f1940g = c2136h;
    }

    void m1061a(BleComm bleComm, C2134a c2134a) {
        this.f1940g.mo5528a(bleComm, c2134a, this.f1939f);
    }

    void m1062b(BleComm bleComm, C2134a c2134a) {
        this.f1940g.mo5529a(bleComm, c2134a);
    }
}
