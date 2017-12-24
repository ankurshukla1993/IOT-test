package com.ihealth.communication.base.ble;

import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;

class C2027a implements LeScanCallback {
    final /* synthetic */ Ble f315a;

    C2027a(Ble ble) {
        this.f315a = ble;
    }

    public void onLeScan(BluetoothDevice arg0, int arg1, byte[] arg2) {
        this.f315a.f281c.onScanResult(arg0, arg1, arg2);
    }
}
