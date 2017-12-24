package com.ihealth.communication.manager;

import android.os.SystemClock;
import com.ihealth.communication.base.ble.BleComm;

final class C2138d extends C2136h {
    C2138d() {
    }

    void mo5529a(BleComm bleComm, C2134a c2134a) {
        if (bleComm != null) {
            bleComm.scan(false);
        }
    }

    public void mo5528a(BleComm bleComm, C2134a c2134a, long j) {
        if (bleComm != null) {
            bleComm.scan(true);
        }
        SystemClock.sleep(j);
        if (bleComm != null) {
            bleComm.scan(false);
        }
        c2134a.m1056c();
    }
}
