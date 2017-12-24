package com.ihealth.communication.base.wifi;

import com.ihealth.communication.base.protocol.BaseCommProtocol;
import com.ihealth.communication.utils.ByteBufferUtil;
import com.ihealth.communication.utils.Log;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

public class WifiUnpackageData {
    private boolean f465a = true;
    private Queue f466b = new LinkedList();
    private Map f467c = new ConcurrentHashMap();
    private UdpSearchCallback f468d;

    public WifiUnpackageData(UdpSearchCallback udpSearchCallback) {
        this.f468d = udpSearchCallback;
    }

    private void m338a() {
        if (this.f466b.size() < 11) {
            Log.w("WifiUnpackageData::", "command length is not wrong");
        } else if (160 == (((Byte) this.f466b.peek()).byteValue() & 255)) {
            this.f466b.poll();
            int byteValue = ((Byte) this.f466b.peek()).byteValue() & 255;
            int i = byteValue + 3;
            if (this.f466b.size() >= byteValue + 2) {
                byte[] bArr = new byte[i];
                bArr[0] = (byte) -96;
                for (int i2 = 1; i2 < i; i2++) {
                    Byte valueOf = Byte.valueOf(((Byte) this.f466b.poll()).byteValue());
                    if (valueOf != null) {
                        bArr[i2] = valueOf.byteValue();
                    }
                }
                m339a(bArr);
                return;
            }
            Log.w("WifiUnpackageData::", "checksum is wrong");
        } else {
            Log.w("HS5Wifi", "head byte is not A0");
            this.f466b.poll();
        }
    }

    private void m339a(byte[] bArr) {
        if (bArr != null && bArr.length >= 13) {
            if ((bArr[5] & 255) == 240) {
                this.f468d.searchUdpNotify(bArr);
                return;
            }
            ((BaseCommProtocol) this.f467c.get(ByteBufferUtil.Bytes2HexString(ByteBufferUtil.bytesCutt(bArr.length - 7, bArr.length - 2, bArr)))).unPackageData(bArr);
        }
    }

    public void addCommNotify(String mac, BaseCommProtocol mWifiCommProtocol) {
        this.f467c.put(mac, mWifiCommProtocol);
    }

    public void addReadData(byte[] data, int count) {
        for (int i = 0; i < count; i++) {
            this.f466b.offer(Byte.valueOf(data[i]));
        }
        m338a();
    }
}
