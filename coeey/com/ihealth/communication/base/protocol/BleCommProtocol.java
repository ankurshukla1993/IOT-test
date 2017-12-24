package com.ihealth.communication.base.protocol;

import android.content.Context;
import com.ihealth.communication.base.ble.BleUnpackageData;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.utils.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

public class BleCommProtocol implements BaseCommProtocol {
    private int f355a = 1;
    private BaseComm f356b;
    private BleUnpackageData f357c;
    private String f358d;
    private byte f359e;
    private Context f360f;
    private Queue f361g = new LinkedList();
    private Queue f362h = new LinkedList();
    private int f363i = 2;
    private boolean f364j = false;
    private final Timer f365k = new Timer();
    private final TimerTask f366l = new C2034a(this);
    private Map f367m = Collections.synchronizedMap(new LinkedHashMap());
    private Map f368n = Collections.synchronizedMap(new LinkedHashMap());
    private Timer f369o = new Timer();
    private TimerTask f370p;

    public BleCommProtocol(Context context, BaseComm com, String mac, byte type, NewDataCallback commCallback) {
        this.f356b = com;
        this.f359e = type;
        this.f357c = new BleUnpackageData();
        this.f357c.addBleCommCallback(commCallback);
        this.f358d = mac;
        this.f360f = context;
        this.f356b.addCommNotify(mac, this);
    }

    private synchronized byte m273a() {
        return (byte) (this.f355a & 255);
    }

    private byte m274a(byte[] bArr) {
        int i = 0;
        for (int i2 = 2; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return (byte) i;
    }

    private void m276a(byte b, byte b2) {
        this.f362h.offer(new byte[]{(byte) -80, 3, b, b2, this.f359e, (byte) ((r0[2] + r0[3]) + r0[4])});
        m283b((byte) ((((byte) (b & 15)) * 2) + (b2 & 255)));
        m277a(2);
        if (!m285b()) {
            m280a(true);
            if (this.f363i == 3) {
                this.f365k.schedule(this.f366l, 10);
            } else {
                this.f356b.sendData(this.f358d, (byte[]) this.f362h.poll());
            }
        }
    }

    private synchronized void m277a(int i) {
        this.f355a += i;
    }

    private void m279a(String str) {
        byte byteValue = ((Byte) this.f361g.poll()).byteValue();
        int[] c = m288c(this.f361g.size());
        int length = (c.length - 1) << 4;
        int length2 = c.length - 1;
        List<byte[]> arrayList = new ArrayList();
        for (int i = 0; i < c.length; i++) {
            int i2 = c[i];
            byte[] bArr = new byte[(i2 + 6)];
            bArr[0] = (byte) -80;
            bArr[1] = (byte) (i2 + 3);
            bArr[2] = (byte) ((length + length2) - i);
            bArr[3] = m273a();
            bArr[4] = byteValue;
            for (int i3 = 0; i3 < i2; i3++) {
                bArr[i3 + 5] = ((Byte) this.f361g.poll()).byteValue();
            }
            bArr[i2 + 5] = m274a(bArr);
            arrayList.add(bArr);
            m277a(2);
        }
        for (byte[] offer : arrayList) {
            byte[] offer2;
            this.f362h.offer(offer2);
        }
        if (!m285b()) {
            m280a(true);
            if (this.f363i == 2) {
                this.f365k.schedule(this.f366l, 100);
            } else if (this.f363i == 3) {
                this.f365k.schedule(this.f366l, 10);
            } else {
                offer2 = (byte[]) this.f362h.poll();
                this.f367m.put(Integer.valueOf(offer2[3] & 255), offer2);
                this.f356b.sendData(this.f358d, offer2);
            }
        }
        m289d();
        m287c();
    }

    private void m280a(boolean z) {
        this.f364j = z;
    }

    private boolean m281a(int i, int i2, byte[] bArr) {
        int i3 = bArr[i2 + 1] & 255;
        int i4 = 0;
        while (i < i2 + 1) {
            i4 = (i4 + bArr[i]) & 255;
            i++;
        }
        return i4 == i3;
    }

    private synchronized void m283b(int i) {
        this.f355a = i;
    }

    private void m284b(String str) {
        int size = this.f361g.size() + 2;
        int i = size + 3;
        byte[] bArr = new byte[i];
        bArr[0] = (byte) -80;
        bArr[1] = (byte) size;
        bArr[2] = (byte) 0;
        bArr[3] = m273a();
        for (int i2 = 0; i2 < size - 2; i2++) {
            bArr[i2 + 4] = ((Byte) this.f361g.poll()).byteValue();
        }
        bArr[i - 1] = m274a(bArr);
        this.f362h.offer(bArr);
        m277a(2);
        if (!m285b()) {
            m280a(true);
            if (this.f363i == 3) {
                this.f365k.schedule(this.f366l, 10);
            } else {
                byte[] bArr2 = (byte[]) this.f362h.poll();
                this.f367m.put(Integer.valueOf(bArr2[3] & 255), bArr2);
                this.f356b.sendData(this.f358d, bArr2);
            }
        }
        m289d();
        m287c();
    }

    private boolean m285b() {
        return this.f364j;
    }

    private void m287c() {
        this.f370p = new C2035b(this);
        this.f369o.schedule(this.f370p, 800, 300);
    }

    private int[] m288c(int i) {
        int i2 = (i / 14) + 1;
        int i3 = i % 14;
        int[] iArr = new int[i2];
        for (int i4 = 0; i4 < i2 - 1; i4++) {
            iArr[i4] = 14;
        }
        iArr[i2 - 1] = i3;
        return iArr;
    }

    private void m289d() {
        if (this.f370p != null) {
            this.f370p.cancel();
            this.f370p = null;
        }
    }

    private void m291e() {
        try {
            byte[] bArr;
            for (Object next : this.f367m.keySet()) {
                bArr = (byte[]) this.f367m.get(next);
                if (bArr != null) {
                    int i = 0;
                    if (this.f368n.containsKey(next)) {
                        i = ((Integer) this.f368n.get(next)).intValue();
                    }
                    if (i < 3) {
                        this.f368n.put(Integer.valueOf(((Integer) next).intValue()), Integer.valueOf(i + 1));
                        this.f362h.offer(bArr);
                    } else {
                        if (this.f368n.containsKey(next)) {
                            this.f368n.remove(next);
                        }
                        this.f367m.remove(next);
                        m289d();
                    }
                }
            }
            if (this.f367m.size() == 0) {
                m289d();
            }
            if (!m285b() && this.f362h.size() > 0) {
                m280a(true);
                bArr = (byte[]) this.f362h.poll();
                if (bArr != null) {
                    this.f356b.sendData(this.f358d, bArr);
                }
            }
        } catch (Exception e) {
            this.f367m.clear();
        }
    }

    public void destroy() {
        if (this.f356b != null) {
            this.f356b.removeCommNotify(this.f358d);
        }
        this.f356b = null;
        this.f357c = null;
        this.f360f = null;
        if (this.f367m != null) {
            this.f367m.clear();
        }
        this.f367m = null;
        if (this.f368n != null) {
            this.f368n.clear();
        }
        this.f368n = null;
        if (this.f362h != null) {
            this.f362h.clear();
        }
        this.f362h = null;
        if (this.f369o != null) {
            this.f369o.cancel();
        }
        this.f369o = null;
        m289d();
    }

    public synchronized void packageData(String mac, byte[] ins) {
        this.f361g.clear();
        for (byte valueOf : ins) {
            this.f361g.offer(Byte.valueOf(valueOf));
        }
        if (this.f361g.size() <= 15) {
            m284b(mac);
        } else {
            m279a(mac);
        }
    }

    public void packageData(byte[] ins) {
    }

    public void packageDataAsk(byte[] returnCommand) {
    }

    public void packageDataFinish() {
        if (this.f363i == 2) {
            this.f366l.cancel();
            this.f365k.cancel();
            this.f363i = 4;
        }
        Log.v("BleCommProtocol", "packageDataFinish commandGueue.size():" + this.f362h.size());
        if (this.f362h.size() > 0) {
            byte[] bArr = (byte[]) this.f362h.poll();
            if (bArr.length > 6) {
                this.f367m.put(Integer.valueOf(bArr[3] & 255), bArr);
            }
            this.f356b.sendData(this.f358d, bArr);
            return;
        }
        m280a(false);
    }

    public void setInsSet(NewDataCallback dataCallback) {
        this.f357c.addBleCommCallback(dataCallback);
    }

    public synchronized void unPackageData(byte[] characteristicChangedValue) {
        if (characteristicChangedValue[0] != (byte) -96) {
            Log.w("BleCommProtocol", "head byte is not A0");
        } else {
            byte b = characteristicChangedValue[1];
            int length = characteristicChangedValue.length;
            if (length < 6) {
                Log.w("BleCommProtocol", "command length is not wrong");
            } else {
                byte b2 = characteristicChangedValue[3];
                int i = b2 == (byte) 0 ? 255 : (b2 & 255) - 1;
                if (length == 6) {
                    this.f367m.remove(Integer.valueOf(i));
                }
                if (b != length - 3) {
                    Log.w("BleCommProtocol", "This is not full command");
                } else if (!m281a(2, length - 2, characteristicChangedValue)) {
                    Log.w("BleCommProtocol", "checksum is wrong");
                } else if (characteristicChangedValue.length != 6) {
                    int i2 = characteristicChangedValue[2] & 15;
                    if (characteristicChangedValue[2] != (byte) -16) {
                        m276a((byte) (i2 + 160), (byte) (i + 2));
                    }
                    this.f357c.unPackageData(characteristicChangedValue);
                }
            }
        }
    }

    public void unPackageDataUuid(String uuid, byte[] data) {
    }
}
