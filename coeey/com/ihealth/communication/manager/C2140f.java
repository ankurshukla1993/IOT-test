package com.ihealth.communication.manager;

import android.bluetooth.BluetoothAdapter;
import android.os.SystemClock;
import com.ihealth.communication.base.ble.BleComm;

final class C2140f extends C2136h {
    private BluetoothAdapter f1941a = BluetoothAdapter.getDefaultAdapter();

    C2140f() {
    }

    void mo5529a(BleComm bleComm, C2134a c2134a) {
        if (this.f1941a != null && this.f1941a.isEnabled()) {
            this.f1941a.cancelDiscovery();
        }
    }

    public void mo5528a(BleComm bleComm, C2134a c2134a, long j) {
        if (this.f1941a != null && this.f1941a.isEnabled()) {
            this.f1941a.startDiscovery();
        }
        SystemClock.sleep(j);
        if (this.f1941a != null && this.f1941a.isEnabled()) {
            this.f1941a.cancelDiscovery();
        }
        c2134a.m1056c();
    }
}
