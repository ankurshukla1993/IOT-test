package com.ihealth.communication.base.ble;

import android.bluetooth.BluetoothDevice;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface BtleCallback {
    void onCharacteristicChanged(BluetoothDevice bluetoothDevice, byte[] bArr);

    void onCharacteristicRead(byte[] bArr, UUID uuid, int i);

    void onConnectionStateChange(BluetoothDevice bluetoothDevice, int i, int i2);

    void onScanResult(BluetoothDevice bluetoothDevice, int i, String str, Map map);

    void onServicesDiscovered(BluetoothDevice bluetoothDevice, List list, int i);

    void onServicesObtain();
}
