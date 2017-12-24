package com.ihealth.communication.base.protocol;

import com.ihealth.communication.base.comm.BaseComm;
import com.ihealth.communication.base.comm.DataNotify;
import com.ihealth.communication.base.comm.DataNotifyImpl;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.utils.Log;
import java.util.concurrent.ConcurrentHashMap;

public class BtCommProtocol implements BaseCommProtocol {
    private BaseComm f385a;
    private byte f386b = (byte) -80;
    private DataNotify f387c;
    private int f388d = 1;
    private ConcurrentHashMap f389e = new ConcurrentHashMap();

    public BtCommProtocol(BaseComm com, NewDataCallback dataCallBack) {
        this.f385a = com;
        com.addCommNotify(this);
        this.f387c = new DataNotifyImpl();
        this.f387c.attach(dataCallBack);
    }

    private byte m302a(byte[] bArr) {
        int i = 0;
        for (int i2 = 2; i2 < bArr.length - 1; i2++) {
            i += bArr[i2];
        }
        return (byte) i;
    }

    private void m303a() {
        if (this.f388d == 255) {
            this.f388d = 0;
        } else {
            this.f388d++;
        }
    }

    private void m304a(int i) {
        this.f388d = i;
    }

    private boolean m305a(int i, int i2, byte[] bArr) {
        int i3 = bArr[i2 + 1] & 255;
        int i4 = 0;
        while (i < i2 + 1) {
            i4 = (i4 + bArr[i]) & 255;
            i++;
        }
        return i4 == i3;
    }

    private byte[] m306b(int i, int i2, byte[] bArr) {
        byte[] bArr2 = new byte[((i2 - i) + 1)];
        for (int i3 = 0; i3 < bArr2.length; i3++) {
            bArr2[i3] = bArr[i + i3];
        }
        return bArr2;
    }

    public void destroy() {
    }

    public void packageData(String mac, byte[] command) {
        int i = 0;
        this.f389e.clear();
        int length = command.length + 2;
        int i2 = length + 3;
        byte[] bArr = new byte[i2];
        bArr[0] = this.f386b;
        bArr[1] = (byte) length;
        bArr[2] = (byte) 0;
        bArr[3] = (byte) this.f388d;
        while (i < command.length) {
            bArr[i + 4] = command[i];
            i++;
        }
        bArr[i2 - 1] = m302a(bArr);
        this.f385a.sendData(null, bArr);
        this.f389e.put(Integer.valueOf(this.f388d & 255), bArr);
        m303a();
        m303a();
    }

    public void packageData(byte[] ins) {
    }

    public void packageDataAsk(byte[] command) {
        int i = 0;
        int length = command.length + 2;
        int i2 = length + 3;
        byte[] bArr = new byte[i2];
        bArr[0] = this.f386b;
        bArr[1] = (byte) length;
        bArr[2] = (byte) -96;
        bArr[3] = (byte) this.f388d;
        while (i < command.length) {
            bArr[i + 4] = command[i];
            i++;
        }
        bArr[i2 - 1] = m302a(bArr);
        this.f385a.sendData(null, bArr);
        m303a();
    }

    public void packageDataFinish() {
    }

    public void packageDataWithoutProtocol(byte[] command) {
        this.f385a.sendData(null, command);
    }

    public void setInsSet(NewDataCallback dataCallback) {
        this.f387c.attach(dataCallback);
    }

    public void unPackageData(byte[] commandReceive) {
        if (this.f387c == null) {
            Log.e("BtCommProtocol >>>", "btNotify is null ");
        } else if (commandReceive[0] != (byte) -96) {
            Log.w("BtCommProtocol >>>", "head byte is not A0");
        } else {
            int i = commandReceive[1] & 255;
            int length = commandReceive.length;
            if (length == 6) {
                Log.v("BtCommProtocol >>>", "ACK");
                return;
            }
            int i2 = commandReceive[3];
            this.f389e.remove(Integer.valueOf(i2 == (byte) 0 ? 255 : (i2 & 255) - 1));
            m304a(i2);
            if (i != length - 3) {
                Log.w("BtCommProtocol >>>", "This is not full command");
            } else if (m305a(2, length - 2, commandReceive)) {
                byte[] b = m306b(6, length - 2, commandReceive);
                m303a();
                this.f387c.haveNewData(commandReceive[5] & 255, commandReceive[2] & 255, b);
            } else {
                Log.w("BtCommProtocol >>>", "checksum is wrong");
            }
        }
    }

    public void unPackageDataUuid(String uuid, byte[] data) {
    }
}
