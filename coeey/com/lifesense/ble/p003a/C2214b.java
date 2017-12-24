package com.lifesense.ble.p003a;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothAdapter.LeScanCallback;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.content.Context;
import android.os.Message;
import com.lifesense.ble.bean.C2248h;
import com.lifesense.ble.bean.C2249i;
import java.util.Timer;
import java.util.UUID;

@SuppressLint({"NewApi"})
public class C2214b {
    private BluetoothManager f2278a;
    private BluetoothAdapter f2279b;
    private BluetoothGatt f2280c;
    private BluetoothDevice f2281d;
    private Context f2282e;
    private C2248h f2283f;
    private C2222j f2284g;
    private C2228p f2285h;
    private boolean f2286i;
    private boolean f2287j;
    private int f2288k;
    private boolean f2289l;
    private C2219g f2290m;
    private String f2291n;
    private Thread f2292o;
    private boolean f2293p;
    private boolean f2294q;
    private LeScanCallback f2295r = new C2215c(this);
    private BluetoothGattCallback f2296s = new C2216d(this);

    private String m1558a(UUID uuid) {
        if (uuid == null) {
            return "unknown";
        }
        String uuid2 = uuid.toString();
        return uuid2.length() > 8 ? uuid2.substring(4, 8) : uuid2.substring(0);
    }

    private void m1559a(int i) {
        new Timer().schedule(new C2217e(this), (long) (i + 8000));
    }

    private boolean m1564a(C2248h c2248h, UUID uuid) {
        if (c2248h == null || c2248h.m1849b().isEmpty()) {
            return false;
        }
        for (C2248h a : c2248h.m1849b()) {
            if (a.m1844a().equals(uuid)) {
                return true;
            }
        }
        return false;
    }

    private boolean m1567b(UUID uuid, UUID uuid2, UUID uuid3) {
        String str;
        boolean z;
        if (this.f2283f == null || !m1564a(this.f2283f, uuid)) {
            str = "Failed to find service (" + m1558a(uuid) + ")from uuid profile...";
            z = false;
        } else {
            z = false;
            for (C2248h c2248h : this.f2283f.m1849b()) {
                if (m1564a(c2248h, uuid2)) {
                    if (uuid3 != null) {
                        z = false;
                        for (C2248h c2248h2 : c2248h2.m1849b()) {
                            if (m1564a(c2248h2, uuid3)) {
                                z = true;
                            }
                        }
                    } else {
                        z = true;
                    }
                }
            }
            str = null;
        }
        if (str != null && str.length() > 0) {
            C2220h.m1596a((Object) this, str, 1);
        }
        return z;
    }

    private void m1573f() {
        if (this.f2286i && !this.f2287j && this.f2288k < 3) {
            this.f2294q = false;
            this.f2288k++;
            if (this.f2280c != null) {
                this.f2280c.close();
                this.f2280c = null;
            }
            C2220h.m1596a((Object) this, "try to reconnect device with count:" + this.f2288k, 1);
            this.f2292o = new Thread(new C2218f(this));
            this.f2292o.start();
        } else if (this.f2284g != null) {
            this.f2284g.mo5575a();
        }
    }

    public void m1581a(C2222j c2222j) {
        if (c2222j != null) {
            this.f2284g = c2222j;
        }
    }

    public boolean m1582a() {
        if (this.f2282e.getPackageManager().hasSystemFeature("android.hardware.bluetooth_le")) {
            C2220h.m1596a((Object) this, "Supported Bluetooth Low Energy...", 2);
            return true;
        }
        C2220h.m1596a((Object) this, "Error ! Unsupported Bluetooth Low Energy...", 2);
        return false;
    }

    public boolean m1583a(Context context) {
        if (context != null) {
            this.f2289l = false;
            this.f2282e = context;
            this.f2278a = (BluetoothManager) context.getSystemService("bluetooth");
            this.f2279b = this.f2278a.getAdapter();
            this.f2283f = new C2248h();
            this.f2283f.m1848a(null);
            this.f2283f.m1846a(C2249i.DEVICE);
            this.f2290m = new C2219g(this, context.getMainLooper());
            return true;
        }
        C2220h.m1596a((Object) this, "Failed to initialize connector instance...", 1);
        return false;
    }

    public boolean m1584a(C2228p c2228p) {
        if (c2228p == null) {
            C2220h.m1596a((Object) this, "Failed to start scanning,for null...", 1);
            return false;
        } else if (this.f2279b != null && m1590b() && m1582a()) {
            C2220h.m1596a((Object) this, "success to start scanning...", 2);
            this.f2289l = true;
            this.f2285h = c2228p;
            this.f2279b = this.f2278a.getAdapter();
            return this.f2279b.startLeScan(this.f2295r);
        } else {
            C2220h.m1596a((Object) this, "Failed to start scanning,for bleadapter...", 2);
            this.f2289l = false;
            return false;
        }
    }

    public boolean m1585a(String str) {
        if (str != null) {
            C2220h.m1596a((Object) this, "try to connect device with address-" + str, 3);
            this.f2286i = true;
            this.f2287j = false;
            this.f2288k = 0;
            this.f2291n = str;
            this.f2294q = false;
            Message obtainMessage = this.f2290m.obtainMessage();
            obtainMessage.obj = str;
            obtainMessage.arg1 = 1;
            this.f2290m.sendMessage(obtainMessage);
            m1559a(0);
            return true;
        }
        C2220h.m1596a((Object) this, "Failed to connect gatt...", 1);
        return false;
    }

    public boolean m1586a(UUID uuid, UUID uuid2) {
        if (uuid == null || uuid2 == null) {
            C2220h.m1596a((Object) this, "Failed to read value from characteristic,for null-", 1);
            return false;
        } else if (this.f2280c == null || !m1567b(uuid, uuid2, null)) {
            return false;
        } else {
            BluetoothGattService service = this.f2280c.getService(uuid);
            if (service == null) {
                return false;
            }
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
            if (characteristic == null) {
                return false;
            }
            if ((characteristic.getProperties() & 2) != 0) {
                return this.f2280c.readCharacteristic(characteristic);
            }
            C2220h.m1596a((Object) this, "Error ! no read permission for characteristic-" + m1558a(uuid2), 1);
            return false;
        }
    }

    public boolean m1587a(UUID uuid, UUID uuid2, UUID uuid3) {
        if (uuid == null || uuid2 == null || uuid3 == null) {
            C2220h.m1596a((Object) this, "Failed to set indicat to descriptor，for null...", 1);
            return false;
        } else if (this.f2280c == null || !m1567b(uuid, uuid2, uuid3)) {
            return false;
        } else {
            BluetoothGattService service = this.f2280c.getService(uuid);
            if (service == null) {
                return false;
            }
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
            if (characteristic == null) {
                return false;
            }
            if ((characteristic.getProperties() & 32) == 0) {
                C2220h.m1596a((Object) this, "Error ! no indicat permission for characteristic-" + m1558a(characteristic.getUuid()), 1);
                return false;
            }
            BluetoothGattDescriptor descriptor = characteristic.getDescriptor(uuid3);
            if (descriptor == null) {
                return false;
            }
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            return this.f2280c.writeDescriptor(descriptor);
        }
    }

    public boolean m1588a(UUID uuid, UUID uuid2, boolean z) {
        if (uuid == null || uuid2 == null) {
            C2220h.m1596a((Object) this, "Failed to set notify to characteristic,for null...", 1);
        } else if (this.f2280c != null && m1567b(uuid, uuid2, null)) {
            BluetoothGattService service = this.f2280c.getService(uuid);
            if (service != null) {
                BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
                if (characteristic != null) {
                    return this.f2280c.setCharacteristicNotification(characteristic, z);
                }
            }
        }
        return false;
    }

    public boolean m1589a(UUID uuid, UUID uuid2, byte[] bArr) {
        if (uuid == null || uuid2 == null || bArr == null) {
            C2220h.m1596a((Object) this, "Failed to write value to characteristic,for null-", 1);
            return false;
        } else if (this.f2280c == null || !m1567b(uuid, uuid2, null)) {
            return false;
        } else {
            BluetoothGattService service = this.f2280c.getService(uuid);
            if (service == null) {
                return false;
            }
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(uuid2);
            if (characteristic == null) {
                return false;
            }
            if ((characteristic.getProperties() & 8) == 0) {
                C2220h.m1596a((Object) this, "Error ! no write permission for characteristic-" + m1558a(characteristic.getUuid()), 1);
                return false;
            }
            characteristic.setWriteType(2);
            characteristic.setValue(bArr);
            return this.f2280c.writeCharacteristic(characteristic);
        }
    }

    public boolean m1590b() {
        if (this.f2279b == null || !this.f2279b.isEnabled()) {
            C2220h.m1596a((Object) this, "Bluetooth funcation is closed...", 2);
            return false;
        }
        C2220h.m1596a((Object) this, "Bluetooth funcation is open...", 2);
        return true;
    }

    public boolean m1591c() {
        return this.f2289l;
    }

    public boolean m1592d() {
        if (this.f2279b != null) {
            C2220h.m1596a((Object) this, "stop scanning...", 2);
            this.f2279b.stopLeScan(this.f2295r);
            this.f2289l = false;
            return true;
        }
        this.f2289l = false;
        return false;
    }

    public boolean m1593e() {
        if (this.f2280c == null) {
            return false;
        }
        C2220h.m1596a((Object) this, "Disconnect of mobile phone...", 3);
        this.f2280c.close();
        this.f2280c = null;
        return true;
    }
}
