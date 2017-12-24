package com.ihealth.communication.base.ble;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.os.SystemClock;
import com.facebook.GraphResponse;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

class C2028b extends BluetoothGattCallback {
    final /* synthetic */ Ble f316a;

    C2028b(Ble ble) {
        this.f316a = ble;
    }

    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
        this.f316a.f281c.onCharacteristicChanged(gatt.getDevice(), characteristic.getValue(), characteristic.getUuid());
    }

    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        if (status == 0) {
            this.f316a.f281c.onCharacteristicRead(gatt.getDevice(), characteristic.getValue(), characteristic.getUuid(), status);
            return;
        }
        Log.e(Ble.f279a, "onCharacteristicRead() -- failed");
        gatt.disconnect();
    }

    public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
        if (status == 0) {
            this.f316a.f281c.onCharacteristicWrite(gatt.getDevice(), characteristic.getUuid(), status);
        } else {
            Log.e(Ble.f279a, "onCharacteristicWrite() -- failed");
        }
    }

    public synchronized void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
        String address = gatt.getDevice().getAddress();
        Log.p(Ble.f279a, Level.VERBOSE, "onConnectionStateChange", new Object[]{address.replace(":", ""), Integer.valueOf(status), Integer.valueOf(newState)});
        if (status != 0) {
            this.f316a.m242a(gatt, status, newState);
        } else if (newState == 2) {
            this.f316a.f283e.put(r1, this.f316a.f290l);
            this.f316a.m253b(address);
            if (this.f316a.f286h.contains(address)) {
                this.f316a.f286h.remove(address);
            }
            if (this.f316a.f287i.contains(address)) {
                this.f316a.f287i.remove(address);
            }
            if (this.f316a.f288j.contains(address)) {
                Log.v(Ble.f279a, "Duplicate connection success");
            } else {
                this.f316a.f288j.add(address);
                Log.v(Ble.f279a, "Connection Success");
                this.f316a.f281c.onConnectionStateChange(gatt.getDevice(), status, newState);
                SystemClock.sleep(300);
                gatt.discoverServices();
            }
        } else if (newState == 0) {
            this.f316a.m242a(gatt, status, newState);
        } else {
            this.f316a.f281c.onConnectionStateChange(gatt.getDevice(), status, newState);
        }
    }

    public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
    }

    public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
        if (status == 0) {
            Log.p(Ble.f279a, Level.VERBOSE, "onDescriptorWrite", new Object[]{GraphResponse.SUCCESS_KEY});
            UUID uuid = descriptor.getCharacteristic().getUuid();
            if (this.f316a.f299u) {
                this.f316a.f281c.onServicesObtain();
                this.f316a.f281c.onServicesObtain(uuid, gatt.getDevice(), null);
                this.f316a.f299u = false;
                return;
            } else if (this.f316a.f300v) {
                this.f316a.f281c.onServicesObtain();
                this.f316a.f281c.onServicesObtain(uuid, gatt.getDevice(), null);
                this.f316a.f300v = false;
                return;
            } else {
                return;
            }
        }
        Log.e(Ble.f279a, "onDescriptorWrite() -- failed");
        gatt.disconnect();
    }

    public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
        this.f316a.f281c.onRssi(rssi);
    }

    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
        if (status == 0) {
            Log.p(Ble.f279a, Level.VERBOSE, "onServicesDiscovered", new Object[]{Integer.valueOf(gatt.getServices().size())});
            List arrayList = new ArrayList();
            for (BluetoothGattService bluetoothGattService : r0) {
                arrayList.add(bluetoothGattService.getUuid());
                for (BluetoothGattCharacteristic uuid : bluetoothGattService.getCharacteristics()) {
                    arrayList.add(uuid.getUuid());
                }
            }
            this.f316a.f281c.onServicesDiscovered(gatt.getDevice(), arrayList, status);
            return;
        }
        Log.e(Ble.f279a, "onServicesDiscovered() -- failed");
        gatt.disconnect();
    }
}
