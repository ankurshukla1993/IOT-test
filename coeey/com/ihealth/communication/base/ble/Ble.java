package com.ihealth.communication.base.ble;

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
import android.os.SystemClock;
import com.ihealth.communication.utils.Log;
import com.ihealth.communication.utils.Log.Level;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class Ble {
    private static String f279a = "Runtime_ble";
    private Context f280b;
    private BleCallback f281c;
    private BluetoothAdapter f282d;
    private Map f283e = new ConcurrentHashMap();
    private Map f284f = new ConcurrentHashMap();
    private Map f285g = new ConcurrentHashMap();
    private List f286h = new ArrayList();
    private List f287i = new ArrayList();
    private List f288j = new ArrayList();
    private int f289k = 20000;
    private BluetoothGatt f290l;
    private final BluetoothGattCallback f291m = new C2028b(this);
    public LeScanCallback mLeScanCallback = new C2027a(this);
    private BluetoothGattService f292n;
    private BluetoothGattService f293o;
    private BluetoothGattCharacteristic f294p;
    private BluetoothGattCharacteristic f295q;
    private String f296r = "00002902-0000-1000-8000-00805f9b34fb";
    private Timer f297s = new Timer();
    private TimerTask f298t = null;
    private boolean f299u = false;
    private boolean f300v = false;
    private Timer f301w;
    private TimerTask f302x;
    private BluetoothGatt f303y;

    public Ble(Context context, BleCallback bleCallback) {
        this.f280b = context;
        this.f281c = bleCallback;
        this.f282d = ((BluetoothManager) this.f280b.getSystemService("bluetooth")).getAdapter();
        this.f283e.clear();
        this.f285g.clear();
        this.f285g.clear();
        this.f286h.clear();
        this.f287i.clear();
        this.f288j.clear();
    }

    private void m242a(BluetoothGatt bluetoothGatt, int i, int i2) {
        String address = bluetoothGatt.getDevice().getAddress();
        String replace = address.replace(":", "");
        m253b(address);
        if (this.f288j.contains(address)) {
            this.f288j.remove(address);
        }
        Object obj = null;
        if (!this.f287i.contains(address)) {
            Log.v(f279a, "DisConnection Success");
        } else if (!this.f286h.contains(address) && bluetoothGatt.connect()) {
            Log.v(f279a, "Reconnecting...");
            this.f286h.add(address);
            m246a(address, bluetoothGatt, i, i2);
            obj = 1;
        }
        if (obj == null) {
            m245a(replace);
            this.f286h.remove(address);
            if (this.f287i.contains(address)) {
                this.f287i.remove(address);
            }
            this.f281c.onConnectionStateChange(bluetoothGatt.getDevice(), i, i2);
            return;
        }
        this.f281c.onConnectionStateChange(bluetoothGatt.getDevice(), i, 4);
    }

    private void m245a(String str) {
        SystemClock.sleep(500);
        if (this.f283e.get(str) != null) {
            ((BluetoothGatt) this.f283e.get(str)).disconnect();
            ((BluetoothGatt) this.f283e.get(str)).close();
            this.f283e.remove(str);
            this.f285g.remove(str);
            this.f284f.remove(str);
            return;
        }
        if (this.f290l != null) {
            this.f290l.disconnect();
            this.f290l.close();
        }
        this.f290l = null;
    }

    private void m246a(String str, BluetoothGatt bluetoothGatt, int i, int i2) {
        if (this.f298t != null) {
            this.f298t.cancel();
            this.f298t = null;
        }
        this.f298t = new C2029c(this, str, bluetoothGatt);
        this.f297s.schedule(this.f298t, (long) this.f289k);
    }

    private boolean m247a(BluetoothGatt bluetoothGatt) {
        try {
            Method method = bluetoothGatt.getClass().getMethod("refresh", new Class[0]);
            if (method != null) {
                return ((Boolean) method.invoke(bluetoothGatt, new Object[0])).booleanValue();
            }
        } catch (Exception e) {
            Log.w(f279a, "refreshDeviceCache() -- Exception: " + e);
        }
        return false;
    }

    private boolean m248a(BluetoothGatt bluetoothGatt, BluetoothGattCharacteristic bluetoothGattCharacteristic) {
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, false)) {
            return false;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString(this.f296r));
        if (descriptor == null) {
            return false;
        }
        Log.v(f279a, "disable notification");
        descriptor.setValue(BluetoothGattDescriptor.DISABLE_NOTIFICATION_VALUE);
        return bluetoothGatt.writeDescriptor(descriptor);
    }

    private final boolean m249a(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGatt bluetoothGatt) {
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null || !bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true)) {
            return false;
        }
        BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString(this.f296r));
        if (descriptor == null) {
            return false;
        }
        Log.v(f279a, "enable notification");
        this.f299u = true;
        descriptor.setValue(BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE);
        return this.f290l.writeDescriptor(descriptor);
    }

    private void m252b() {
        if (this.f302x != null) {
            this.f302x.cancel();
            this.f302x = null;
        }
        if (this.f301w != null) {
            this.f301w.cancel();
            this.f301w = null;
        }
    }

    private void m253b(String str) {
        if (this.f298t != null) {
            this.f298t.cancel();
            this.f298t = null;
        }
    }

    private final boolean m254b(BluetoothGattCharacteristic bluetoothGattCharacteristic, BluetoothGatt bluetoothGatt) {
        this.f300v = true;
        if (bluetoothGatt == null || bluetoothGattCharacteristic == null) {
            Log.w(f279a, "gatt == null || characteristic == null");
            return false;
        } else if (!bluetoothGatt.setCharacteristicNotification(bluetoothGattCharacteristic, true)) {
            return false;
        } else {
            Log.v(f279a, "enable indications");
            BluetoothGattDescriptor descriptor = bluetoothGattCharacteristic.getDescriptor(UUID.fromString(this.f296r));
            if (descriptor == null) {
                return false;
            }
            descriptor.setValue(BluetoothGattDescriptor.ENABLE_INDICATION_VALUE);
            return bluetoothGatt.writeDescriptor(descriptor);
        }
    }

    void m263a(String str, UUID uuid, UUID uuid2, UUID uuid3, UUID uuid4, boolean z) {
        BluetoothGatt bluetoothGatt = (BluetoothGatt) this.f283e.get(str);
        if (bluetoothGatt == null) {
            Log.e(f279a, "bluetoothGatt -- null");
            return;
        }
        this.f292n = bluetoothGatt.getService(uuid4);
        if (this.f292n == null) {
            Log.e(f279a, "mGattServiceIDPS --- ERROR");
            bluetoothGatt.disconnect();
            return;
        }
        this.f293o = bluetoothGatt.getService(uuid);
        if (this.f293o == null) {
            Log.e(f279a, "mGattServiceComm --- ERROR");
            bluetoothGatt.disconnect();
            return;
        }
        if (uuid2 != null) {
            this.f294p = this.f293o.getCharacteristic(uuid2);
        }
        if (this.f294p == null) {
            for (BluetoothGattService characteristics : bluetoothGatt.getServices()) {
                for (BluetoothGattCharacteristic bluetoothGattCharacteristic : characteristics.getCharacteristics()) {
                    if (bluetoothGattCharacteristic.getUuid().equals(uuid2)) {
                        this.f294p = bluetoothGattCharacteristic;
                        break;
                    }
                }
            }
        }
        if (this.f294p != null) {
            this.f294p.setWriteType(1);
            this.f285g.put(str, this.f294p);
        } else {
            Log.v(f279a, "mGattCharacteristicTrans -- Null");
        }
        this.f295q = this.f293o.getCharacteristic(uuid3);
        if (this.f295q == null) {
            Log.v(f279a, "mGattcharacteristicReceive --- ERROR");
        }
        SystemClock.sleep(300);
        if (!(z ? m254b(this.f295q, bluetoothGatt) : m249a(this.f295q, bluetoothGatt))) {
            Log.e(f279a, "enableNotifications(true, mGattcharacteristicReceive) --- failed");
            bluetoothGatt.disconnect();
        } else if (!z) {
            this.f284f.put(str, this.f295q);
        }
    }

    public void commandClearCache() {
        this.f286h.clear();
        this.f287i.clear();
        this.f288j.clear();
    }

    public boolean connectDevice(String address) {
        Log.p(f279a, Level.VERBOSE, "connectDevice", new Object[]{address, this.f282d});
        if (this.f282d == null || address == null || !this.f282d.isEnabled() || this.f287i.size() > 0 || this.f287i.contains(address) || this.f288j.contains(address)) {
            return false;
        }
        this.f287i.add(address);
        BluetoothDevice remoteDevice = this.f282d.getRemoteDevice(address);
        if (remoteDevice == null) {
            Log.e(f279a, "Device not found.  Unable to connect.");
            return false;
        }
        this.f290l = remoteDevice.connectGatt(this.f280b, false, this.f291m);
        if (this.f290l == null) {
            return false;
        }
        m246a(address, this.f290l, 0, 0);
        return true;
    }

    public void disconnect(String mac) {
        Log.p(f279a, Level.VERBOSE, "disconnect", new Object[]{mac});
        if (mac.length() == 12) {
            String str = "";
            for (int i = 0; i < 6; i++) {
                str = str + mac.substring(i * 2, (i * 2) + 2);
                if (i < 5) {
                    str = str + ":";
                }
            }
            if (this.f287i.contains(str)) {
                this.f287i.remove(str);
                m253b(mac);
            }
            BluetoothGatt bluetoothGatt = (BluetoothGatt) this.f283e.get(mac);
            BluetoothGattCharacteristic bluetoothGattCharacteristic = (BluetoothGattCharacteristic) this.f284f.get(mac);
            if (bluetoothGatt != null) {
                m248a(bluetoothGatt, bluetoothGattCharacteristic);
                SystemClock.sleep(300);
                bluetoothGatt.disconnect();
            } else if (this.f290l != null) {
                m248a(bluetoothGatt, this.f295q);
                SystemClock.sleep(300);
                this.f290l.disconnect();
            }
        }
    }

    public void readCharacteristic(UUID uuid) {
        if (this.f292n == null) {
            Log.w(f279a, "Device Information Service Not Found!!!");
            return;
        }
        BluetoothGattCharacteristic characteristic = this.f292n.getCharacteristic(uuid);
        if (characteristic == null) {
            m247a(this.f290l);
            this.f303y = this.f290l;
            m252b();
            this.f301w = new Timer();
            this.f302x = new C2031e(this);
            this.f301w.schedule(this.f302x, 4000);
            return;
        }
        this.f290l.readCharacteristic(characteristic);
    }

    public boolean readCharacteristicExtra(String mac, UUID serviceUuid, UUID characteristicUUid) {
        BluetoothGatt bluetoothGatt = (BluetoothGatt) this.f283e.get(mac);
        if (bluetoothGatt == null || serviceUuid == null || characteristicUUid == null) {
            return false;
        }
        BluetoothGattService service = bluetoothGatt.getService(serviceUuid);
        if (service == null) {
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(characteristicUUid);
        return characteristic == null ? false : bluetoothGatt.readCharacteristic(characteristic);
    }

    public boolean readRemoteRssi(String mac) {
        return this.f283e.get(mac) == null ? false : ((BluetoothGatt) this.f283e.get(mac)).readRemoteRssi();
    }

    public boolean refresh(String mac) {
        BluetoothGatt bluetoothGatt = (BluetoothGatt) this.f283e.get(mac);
        if (bluetoothGatt == null) {
            return false;
        }
        this.f303y = bluetoothGatt;
        m252b();
        this.f301w = new Timer();
        this.f302x = new C2032f(this);
        this.f301w.schedule(this.f302x, 4000);
        return m247a((BluetoothGatt) this.f283e.get(mac));
    }

    public void scan(boolean b) {
        Log.p(f279a, Level.VERBOSE, "scan", new Object[]{Boolean.valueOf(b)});
        if (b) {
            this.f282d.startLeScan(this.mLeScanCallback);
        } else {
            this.f282d.stopLeScan(this.mLeScanCallback);
        }
    }

    public void sendData(String mac, byte[] command) {
        BluetoothGatt bluetoothGatt = (BluetoothGatt) this.f283e.get(mac);
        BluetoothGattCharacteristic bluetoothGattCharacteristic = (BluetoothGattCharacteristic) this.f285g.get(mac);
        if (bluetoothGatt == null) {
            Log.e(f279a, "sendData() not find valid device");
        } else if (bluetoothGattCharacteristic == null) {
            Log.e(f279a, "mGattCharacteristicTrans == null");
        } else {
            if (bluetoothGattCharacteristic.getUuid().toString().equals("0000ff03-0000-1000-8000-00805f9b34fb")) {
                bluetoothGattCharacteristic.setWriteType(2);
            }
            bluetoothGattCharacteristic.setValue(command);
            bluetoothGatt.writeCharacteristic(bluetoothGattCharacteristic);
        }
    }

    public boolean writeCharacteristicExtra(String mac, UUID serviceUuid, UUID characteristicUUid, byte[] command) {
        BluetoothGatt bluetoothGatt = (BluetoothGatt) this.f283e.get(mac);
        if (bluetoothGatt == null || serviceUuid == null || characteristicUUid == null) {
            return false;
        }
        BluetoothGattService service = bluetoothGatt.getService(serviceUuid);
        if (service == null) {
            m247a(bluetoothGatt);
            return false;
        }
        BluetoothGattCharacteristic characteristic = service.getCharacteristic(characteristicUUid);
        if (characteristic == null) {
            return false;
        }
        characteristic.setValue(command);
        return bluetoothGatt.writeCharacteristic(characteristic);
    }
}
