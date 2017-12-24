package com.ihealth.communication.base.protocol;

import android.content.Context;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.HandlerThread;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.BaseCommCallback;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.base.wifi.WifiSendThread;
import com.ihealth.communication.ins.InsCallback;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class WifiCommProtocol implements BaseCommProtocol {
    int f395a = 0;
    byte f396b = (byte) -1;
    byte[] f397c = null;
    private BaseComm f398d;
    private byte f399e = (byte) -80;
    private String f400f = "";
    private String f401g = "";
    private Context f402h;
    private byte[] f403i;
    private NewDataCallback f404j;
    private BaseCommCallback f405k;
    private String f406l;
    private HandlerThread f407m;
    private Handler f408n;
    private WifiSendThread f409o;
    private InsCallback f410p;
    private int f411q = 1;
    private int f412r = 1;
    private ConcurrentHashMap f413s = new ConcurrentHashMap();
    private Timer f414t;
    private TimerTask f415u;
    private final int f416v = 6;

    public WifiCommProtocol(Context context, BaseComm comm, String deviceMac, String deviceIP, String type, BaseCommCallback baseCommCallback, InsCallback insCallback) {
        this.f402h = context;
        this.f398d = comm;
        this.f401g = deviceIP;
        this.f400f = deviceMac;
        this.f406l = type;
        this.f410p = insCallback;
        this.f405k = baseCommCallback;
        this.f403i = ((WifiManager) context.getSystemService("wifi")).getConnectionInfo().getMacAddress().getBytes();
        this.f409o = new WifiSendThread(comm);
        if (this.f407m == null || !this.f407m.isAlive()) {
            this.f407m = new HandlerThread("wificommthread");
            this.f407m.start();
        } else {
            Log.v("HS5Wifi", "handlerthread != null");
        }
        if (this.f408n != null) {
            Log.v("HS5Wifi", "mHandler != null");
        } else {
            this.f408n = new Handler(this.f407m.getLooper());
        }
    }

    private byte m312a(byte[] bArr) {
        int i = 0;
        for (int i2 = 2; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return (byte) i;
    }

    private void m314a() {
        if (this.f411q == 255) {
            this.f411q = 0;
        } else {
            this.f411q++;
        }
    }

    private void m315a(int i) {
        this.f411q = i;
    }

    private boolean m316a(int i, int i2, byte[] bArr) {
        int i3 = bArr[i2 + 1] & 255;
        int i4 = 0;
        while (i < i2 + 1) {
            i4 = (i4 + bArr[i]) & 255;
            i++;
        }
        return i4 == i3;
    }

    private void m318b() {
        this.f414t = new Timer();
        this.f415u = new C2037d(this);
        this.f414t.schedule(this.f415u, 500, 500);
    }

    private void m320c() {
        this.f395a = 0;
        if (this.f415u != null) {
            this.f415u.cancel();
            this.f414t.cancel();
        }
    }

    private void m321d() {
        for (Object next : this.f413s.keySet()) {
            if (!(this.f413s.get(next) == null || this.f408n == null || this.f409o == null)) {
                this.f409o.setData(this.f400f, this.f401g, (byte[]) this.f413s.get(next));
                this.f408n.post(this.f409o);
            }
        }
    }

    public void destroy() {
    }

    public void packageData(String mac, byte[] ins) {
    }

    public void packageData(byte[] command) {
        int i = 0;
        m320c();
        this.f413s.clear();
        int length = command.length + 8;
        int i2 = length + 3;
        byte[] bArr = new byte[i2];
        bArr[0] = this.f399e;
        bArr[1] = (byte) length;
        bArr[2] = (byte) 0;
        bArr[3] = (byte) this.f411q;
        for (length = 0; length < command.length; length++) {
            bArr[length + 4] = command[length];
        }
        while (i < 6) {
            bArr[(command.length + 4) + i] = this.f403i[i];
            i++;
        }
        bArr[i2 - 1] = m312a(bArr);
        if (this.f408n != null && this.f409o != null) {
            this.f409o.setData(this.f400f, this.f401g, bArr);
            this.f408n.post(this.f409o);
            this.f413s.put(Integer.valueOf(this.f411q), bArr);
            this.f412r = this.f411q;
            m314a();
            m314a();
            m318b();
        }
    }

    public void packageDataAsk(byte[] command) {
        int i = 0;
        int length = command.length + 8;
        int i2 = length + 3;
        byte[] bArr = new byte[i2];
        bArr[0] = this.f399e;
        bArr[1] = (byte) length;
        bArr[2] = (byte) -96;
        bArr[3] = (byte) this.f411q;
        for (length = 0; length < command.length; length++) {
            bArr[length + 4] = command[length];
        }
        while (i < 6) {
            bArr[(command.length + 4) + i] = this.f403i[i];
            i++;
        }
        bArr[i2 - 1] = m312a(bArr);
        if (this.f408n != null && this.f409o != null) {
            this.f409o.setData(this.f400f, this.f401g, bArr);
            this.f408n.post(this.f409o);
            m314a();
        }
    }

    public void packageDataFinish() {
    }

    public void setInsSet(NewDataCallback dataCallback) {
        this.f404j = dataCallback;
    }

    public void unPackageData(byte[] commandReceive) {
        if (commandReceive[0] != (byte) -96) {
            Log.w("WifiCommProtocol-----", "head byte is not A0");
            return;
        }
        int i = commandReceive[1] & 255;
        int length = commandReceive.length;
        if (i != length - 3) {
            Log.w("WifiCommProtocol-----", "This is not full command");
        } else if (m316a(2, length - 2, commandReceive)) {
            byte b = commandReceive[3];
            i = b == (byte) 0 ? 255 : (b & 255) - 1;
            if (b == (byte) 0 || b != this.f396b) {
                this.f413s.remove(Integer.valueOf(i));
                if (i == this.f412r) {
                    m320c();
                }
                this.f413s.remove(Integer.valueOf(i));
                this.f396b = b;
                this.f397c = commandReceive;
                m315a(b & 255);
                m314a();
                byte b2 = commandReceive[2];
                this.f404j.haveNewData(commandReceive[5] & 255, commandReceive[2] & 255, ByteBufferUtil.bytesCutt(6, length - 2, commandReceive));
                return;
            }
            Log.v("HS5Wifi", "Duplicate command");
        } else {
            Log.w("WifiCommProtocol-----", "checksum is wrong");
        }
    }

    public void unPackageDataUuid(String uuid, byte[] data) {
    }
}
