package com.ihealth.communication.manager;

import android.os.SystemClock;
import com.ihealth.communication.base.ble.BleComm;

final class C2137c extends C2136h {
    C2137c() {
    }

    public void mo5528a(BleComm bleComm, C2134a c2134a, long j) {
        SystemClock.sleep(j);
        c2134a.m1056c();
    }
}
