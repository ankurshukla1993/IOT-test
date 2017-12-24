package com.lifesense.ble.p003a;

import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;

class C2215c implements LeScanCallback {
    final /* synthetic */ C2214b f2297a;

    C2215c(C2214b c2214b) {
        this.f2297a = c2214b;
    }

    public void onLeScan(BluetoothDevice bluetoothDevice, int i, byte[] bArr) {
        this.f2297a.f2293p = true;
        if (this.f2297a.f2285h != null && bluetoothDevice != null && bArr != null) {
            this.f2297a.f2285h.mo5581a(bluetoothDevice.getName(), bluetoothDevice.getAddress(), bArr);
        }
    }
}
