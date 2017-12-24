package com.ihealth.communication.base.ble;

import android.bluetooth.BluetoothDevice;
import java.util.List;
import java.util.UUID;

public interface BleCallback {
    void onCharacteristicChanged(BluetoothDevice bluetoothDevice, byte[] bArr, UUID uuid);

    void onCharacteristicRead(BluetoothDevice bluetoothDevice, byte[] bArr, UUID uuid, int i);

    void onCharacteristicWrite(BluetoothDevice bluetoothDevice, UUID uuid, int i);

    void onConnectionStateChange(BluetoothDevice bluetoothDevice, int i, int i2);

    void onDescriptorRead(byte[] bArr, UUID uuid, int i);

    void onDescriptorWrite();

    void onRssi(int i);

    void onScanResult(BluetoothDevice bluetoothDevice, int i, byte[] bArr);

    void onServicesDiscovered(BluetoothDevice bluetoothDevice, List list, int i);

    void onServicesObtain();

    void onServicesObtain(UUID uuid, BluetoothDevice bluetoothDevice, String str);
}
