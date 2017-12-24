package com.ihealth.communication.base.ble;

import android.bluetooth.BluetoothGatt;
import java.util.TimerTask;

class C2029c extends TimerTask {
    final /* synthetic */ String f317a;
    final /* synthetic */ BluetoothGatt f318b;
    final /* synthetic */ Ble f319c;

    C2029c(Ble ble, String str, BluetoothGatt bluetoothGatt) {
        this.f319c = ble;
        this.f317a = str;
        this.f318b = bluetoothGatt;
    }

    public void run() {
        new Thread(new C2030d(this)).start();
    }
}
