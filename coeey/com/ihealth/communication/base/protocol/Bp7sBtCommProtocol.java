package com.ihealth.communication.base.protocol;

import android.content.Context;
import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.DataNotify;
import com.ihealth.communication.base.comm.DataNotifyImpl;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.utils.Log;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;

public class Bp7sBtCommProtocol implements BaseCommProtocol {
    int f371a;
    byte f372b = (byte) -1;
    byte[] f373c = null;
    private BaseComm f374d;
    private byte f375e = (byte) -80;
    private DataNotify f376f;
    private int f377g = 1;
    private Context f378h;
    private String f379i;
    private String f380j;
    private int f381k = 1;
    private ConcurrentHashMap f382l = new ConcurrentHashMap();
    private Timer f383m;
    private TimerTask f384n;

    public Bp7sBtCommProtocol(Context context, String mac, String type, BaseComm com, NewDataCallback dataCallBack) {
        this.f374d = com;
        this.f378h = context;
        this.f379i = mac;
        this.f380j = type;
        com.addCommNotify(this);
        this.f376f = new DataNotifyImpl();
        this.f376f.attach(dataCallBack);
    }

    private byte m292a(byte[] bArr) {
        int i = 0;
        for (int i2 = 2; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return (byte) i;
    }

    private void m293a() {
        m300c();
        this.f383m = new Timer();
        this.f384n = new C2036c(this);
        this.f383m.schedule(this.f384n, 500, 500);
    }

    private void m294a(int i) {
        this.f381k = i;
    }

    private boolean m296a(int i, int i2, byte[] bArr) {
        int i3 = bArr[i2 + 1] & 255;
        int i4 = 0;
        while (i < i2 + 1) {
            i4 = (i4 + bArr[i]) & 255;
            i++;
        }
        return i4 == i3;
    }

    private void m297b() {
        try {
            for (Object next : this.f382l.keySet()) {
                if (this.f382l.get(next) != null) {
                    this.f374d.sendData(null, (byte[]) this.f382l.get(next));
                }
            }
        } catch (Exception e) {
            this.f382l.clear();
        }
    }

    private byte[] m299b(int i, int i2, byte[] bArr) {
        byte[] bArr2 = new byte[((i2 - i) + 1)];
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            bArr2[i3] = bArr[i + i3];
        }
        return bArr2;
    }

    private void m300c() {
        this.f371a = 0;
        if (this.f384n != null) {
            this.f384n.cancel();
            this.f384n = null;
        }
        if (this.f383m != null) {
            this.f383m.cancel();
            this.f383m = null;
        }
    }

    private void m301d() {
        if (this.f381k == 255) {
            this.f381k = 0;
        } else {
            this.f381k++;
        }
    }

    public void destroy() {
    }

    public void packageData(String mac, byte[] command) {
        int i = 0;
        this.f382l.clear();
        int length = command.length + 2;
        int i2 = length + 3;
        byte[] bArr = new byte[i2];
        bArr[0] = this.f375e;
        bArr[1] = (byte) length;
        bArr[2] = (byte) 0;
        bArr[3] = (byte) this.f381k;
        while (i < command.length) {
            bArr[i + 4] = command[i];
            i++;
        }
        bArr[i2 - 1] = m292a(bArr);
        this.f374d.sendData(null, bArr);
        this.f382l.put(Integer.valueOf(this.f381k & 255), bArr);
        this.f377g = this.f381k & 255;
        m301d();
        m301d();
        m293a();
    }

    public void packageData(byte[] ins) {
    }

    public void packageDataAsk(byte[] command) {
        int i = 0;
        int length = command.length + 2;
        int i2 = length + 3;
        byte[] bArr = new byte[i2];
        bArr[0] = this.f375e;
        bArr[1] = (byte) length;
        bArr[2] = (byte) -96;
        bArr[3] = (byte) this.f381k;
        while (i < command.length) {
            bArr[i + 4] = command[i];
            i++;
        }
        bArr[i2 - 1] = m292a(bArr);
        this.f374d.sendData(null, bArr);
        m301d();
    }

    public void packageDataFinish() {
    }

    public void packageDataWithoutProtocol(byte[] command) {
        this.f374d.sendData(null, command);
    }

    public void setInsSet(NewDataCallback dataCallback) {
    }

    public void unPackageData(byte[] commandReceive) {
        if (commandReceive[0] != (byte) -96) {
            Log.w("BtCommProtocol >>>", "head byte is not A0");
            return;
        }
        int i = commandReceive[1] & 255;
        int length = commandReceive.length;
        if (length == 6) {
            Log.w("BtCommProtocol >>>", "lenR == 6");
            return;
        }
        int i2 = commandReceive[3];
        int i3 = i2 == (byte) 0 ? 255 : (i2 & 255) - 1;
        this.f382l.remove(Integer.valueOf(i3));
        this.f372b = i2;
        this.f373c = commandReceive;
        if (i3 == this.f377g) {
            m300c();
        }
        m294a(i2);
        if (i != length - 3) {
            Log.w("BtCommProtocol >>>", "This is not full command");
        } else if (m296a(2, length - 2, commandReceive)) {
            byte[] b = m299b(6, length - 2, commandReceive);
            m301d();
            this.f376f.haveNewData(commandReceive[5] & 255, commandReceive[2] & 255, b);
        } else {
            Log.w("BtCommProtocol >>>", "checksum is wrong");
        }
    }

    public void unPackageDataUuid(String uuid, byte[] data) {
    }
}
