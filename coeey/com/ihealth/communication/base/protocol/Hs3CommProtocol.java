package com.ihealth.communication.base.protocol;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.DataNotify;
import com.ihealth.communication.base.comm.DataNotifyImpl;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.utils.Log;
import java.util.concurrent.ConcurrentHashMap;

public class Hs3CommProtocol implements BaseCommProtocol {
    private BaseComm f390a;
    private byte f391b = (byte) -80;
    private DataNotify f392c;
    private int f393d = 1;
    private ConcurrentHashMap f394e = new ConcurrentHashMap();

    public Hs3CommProtocol(BaseComm com, NewDataCallback dataCallBack) {
        this.f390a = com;
        com.addCommNotify(this);
        this.f392c = new DataNotifyImpl();
        this.f392c.attach(dataCallBack);
    }

    private byte m307a(byte[] bArr) {
        int i = 0;
        for (int i2 = 2; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return (byte) i;
    }

    private void m308a() {
        if (this.f393d == 255) {
            this.f393d = 0;
        } else {
            this.f393d++;
        }
    }

    private void m309a(int i) {
        this.f393d = i;
    }

    private byte[] m310a(int i, int i2, byte[] bArr) {
        byte[] bArr2 = new byte[((i2 - i) + 1)];
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            bArr2[i3] = bArr[i + i3];
        }
        return bArr2;
    }

    private boolean m311b(int i, int i2, byte[] bArr) {
        int i3 = bArr[i2 + 1] & 255;
        int i4 = 0;
        while (i < i2 + 1) {
            i4 = (i4 + bArr[i]) & 255;
            i++;
        }
        return i4 == i3;
    }

    public void destroy() {
    }

    public void packageData(String mac, byte[] command) {
        int i = 2;
        this.f394e.clear();
        int length = command.length + 1;
        int i2 = length + 3;
        byte[] bArr = new byte[i2];
        bArr[0] = this.f391b;
        bArr[1] = (byte) length;
        bArr[2] = command[0];
        bArr[3] = command[1];
        bArr[4] = (byte) this.f393d;
        while (i < command.length) {
            bArr[i + 3] = command[i];
            i++;
        }
        bArr[i2 - 1] = m307a(bArr);
        this.f390a.sendData(null, bArr);
        this.f394e.put(Integer.valueOf(this.f393d & 255), bArr);
        m308a();
        m308a();
    }

    public void packageData(byte[] ins) {
    }

    public void packageDataAsk(byte[] command) {
        int i = 0;
        int length = command.length + 2;
        int i2 = length + 3;
        byte[] bArr = new byte[i2];
        bArr[0] = this.f391b;
        bArr[1] = (byte) length;
        bArr[2] = (byte) -96;
        bArr[3] = (byte) this.f393d;
        while (i < command.length) {
            bArr[i + 4] = command[i];
            i++;
        }
        bArr[i2 - 1] = m307a(bArr);
        this.f390a.sendData(null, bArr);
        m308a();
    }

    public void packageDataFinish() {
    }

    public void setInsSet(NewDataCallback dataCallback) {
    }

    public void unPackageData(byte[] commandReceive) {
        if (commandReceive[0] != (byte) -96) {
            Log.w("Hs3CommProtocol >>>", "head byte is not A0");
            return;
        }
        int i = commandReceive[1] & 255;
        int length = commandReceive.length;
        int i2 = commandReceive[4];
        this.f394e.remove(Integer.valueOf(i2 == (byte) 0 ? 255 : (i2 & 255) - 1));
        m309a(i2);
        if (i != length - 3) {
            Log.w("Hs3CommProtocol >>>", "This is not full command");
        } else if (m311b(2, length - 2, commandReceive)) {
            byte[] a = m310a(5, length - 2, commandReceive);
            m308a();
            this.f392c.haveNewData(commandReceive[3] & 255, commandReceive[2] & 255, a);
        } else {
            Log.w("Hs3CommProtocol >>>", "checksum is wrong");
        }
    }

    public void unPackageDataUuid(String uuid, byte[] data) {
    }
}
