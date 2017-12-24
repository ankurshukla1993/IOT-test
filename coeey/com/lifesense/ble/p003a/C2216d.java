package com.lifesense.ble.p003a;

import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import com.lifesense.ble.bean.C2248h;
import com.lifesense.ble.bean.C2249i;
import java.util.ArrayList;
import java.util.UUID;

class C2216d extends BluetoothGattCallback {
    final /* synthetic */ C2214b f2298a;

    C2216d(C2214b c2214b) {
        this.f2298a = c2214b;
    }

    public void onCharacteristicChanged(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        UUID uuid2 = bluetoothGattCharacteristic.getService().getUuid();
        C2220h.m1596a((Object) this, "undate value from characteristic(" + this.f2298a.m1558a(uuid) + ")" + ",length=" + value.length + ",source data=" + C2221i.m1614e(value), 3);
        if (this.f2298a.f2284g != null) {
            this.f2298a.f2284g.mo5577a(uuid2, uuid, value);
        }
    }

    public void onCharacteristicRead(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        if (i == 0) {
            byte[] value = bluetoothGattCharacteristic.getValue();
            UUID uuid = bluetoothGattCharacteristic.getUuid();
            UUID uuid2 = bluetoothGattCharacteristic.getService().getUuid();
            C2220h.m1596a((Object) this, "read info from characteristic(" + this.f2298a.m1558a(uuid) + "),data=" + C2221i.m1614e(value), 3);
            if (this.f2298a.f2284g != null) {
                this.f2298a.f2284g.mo5579b(uuid2, uuid, value);
                return;
            }
            return;
        }
        C2220h.m1596a((Object) this, "Failed to read info from characteristic...", 1);
    }

    public void onCharacteristicWrite(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic, int i) {
        byte[] value = bluetoothGattCharacteristic.getValue();
        UUID uuid = bluetoothGattCharacteristic.getUuid();
        UUID uuid2 = bluetoothGattCharacteristic.getService().getUuid();
        C2220h.m1596a((Object) this, "write command to characteristic(" + this.f2298a.m1558a(uuid) + "),length=" + value.length + ",source data=" + C2221i.m1614e(value), 3);
        if (this.f2298a.f2284g != null) {
            this.f2298a.f2284g.mo5580c(uuid2, uuid, value);
        }
    }

    public void onConnectionStateChange(BluetoothGatt bluetoothGatt, int i, int i2) {
        this.f2298a.f2294q = true;
        C2220h.m1596a((Object) this, "connection status changes,oldStatus:" + i + ",newStatus:" + i2 + ",deviceName:" + bluetoothGatt.getDevice().getName(), 3);
        if (i == 0 && i2 == 2) {
            if (this.f2298a.f2286i && !this.f2298a.f2287j) {
                this.f2298a.f2280c.discoverServices();
            }
        } else if (i == 0 && i2 == 0) {
            C2220h.m1596a((Object) this, "Disconnect of device...", 1);
            this.f2298a.m1573f();
        } else if (i != 0) {
            C2220h.m1596a((Object) this, "Failed to connect gatt...", 1);
            this.f2298a.m1573f();
        }
    }

    public void onServicesDiscovered(BluetoothGatt bluetoothGatt, int i) {
        if (i == 0) {
            this.f2298a.f2287j = true;
            ArrayList arrayList = new ArrayList();
            Object obj = null;
            for (BluetoothGattService bluetoothGattService : bluetoothGatt.getServices()) {
                C2220h.m1596a((Object) this, "service uuid-" + this.f2298a.m1558a(bluetoothGattService.getUuid()), 2);
                C2248h c2248h = new C2248h();
                c2248h.m1846a(C2249i.SERVICE);
                c2248h.m1848a(bluetoothGattService.getUuid());
                ArrayList arrayList2 = new ArrayList();
                Object obj2 = obj;
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : bluetoothGattService.getCharacteristics()) {
                    C2220h.m1596a((Object) this, "characteristic uuid-" + this.f2298a.m1558a(bluetoothGattCharacteristic.getUuid()), 2);
                    C2248h c2248h2 = new C2248h();
                    c2248h2.m1846a(C2249i.CHARACTER);
                    c2248h2.m1848a(bluetoothGattCharacteristic.getUuid());
                    c2248h2.m1845a(bluetoothGattCharacteristic.getProperties());
                    ArrayList arrayList3 = new ArrayList();
                    for (BluetoothGattDescriptor bluetoothGattDescriptor : bluetoothGattCharacteristic.getDescriptors()) {
                        C2220h.m1596a((Object) this, "descriptor uuid-" + this.f2298a.m1558a(bluetoothGattDescriptor.getUuid()), 2);
                        C2248h c2248h3 = new C2248h();
                        c2248h3.m1846a(C2249i.DESCRIPTOR);
                        c2248h3.m1848a(bluetoothGattDescriptor.getUuid());
                        arrayList3.add(c2248h3);
                        obj2 = 1;
                    }
                    c2248h2.m1847a(arrayList3, bluetoothGattCharacteristic.getUuid().toString().substring(4, 8));
                    arrayList2.add(c2248h2);
                }
                c2248h.m1847a(arrayList2, bluetoothGattService.getUuid().toString().substring(4, 8));
                arrayList.add(c2248h);
                obj = obj2;
            }
            if (obj != null) {
                C2220h.m1596a((Object) this, "successfully discovered gatt services...", 3);
                this.f2298a.f2283f.m1847a(arrayList, this.f2298a.f2283f.f2429a);
                if (this.f2298a.f2284g != null) {
                    this.f2298a.f2284g.mo5576a(this.f2298a.f2283f);
                    return;
                }
                return;
            }
            C2220h.m1596a((Object) this, "Failed to discovered gatt services,try again ...", 3);
            if (this.f2298a.f2284g != null) {
                this.f2298a.f2284g.mo5578b();
                return;
            }
            return;
        }
        C2220h.m1596a((Object) this, "Failed to discover gatt services...", 1);
        if (this.f2298a.f2284g != null) {
            this.f2298a.f2284g.mo5578b();
        }
    }
}
