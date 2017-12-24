package com.ihealth.communication.base.ble;

import android.support.v4.view.InputDeviceCompat;
import com.ihealth.communication.base.comm.NewDataCallback;
import com.ihealth.communication.utils.ByteBufferUtil;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BleUnpackageData {
    private NewDataCallback f304a;
    private final Map f305b = new HashMap();
    private int[] f306c = null;
    private int f307d;

    public BleUnpackageData() {
        this.f305b.clear();
    }

    private int m264a(int i) {
        if (this.f306c == null) {
            return 101;
        }
        for (int i2 : this.f306c) {
            if (i2 == i) {
                return 103;
            }
        }
        return 102;
    }

    private void m265a() {
        int i = 0;
        for (Entry value : this.f305b.entrySet()) {
            i = ((byte[]) value.getValue()).length + i;
        }
        byte[] bArr = new byte[i];
        int i2 = 0;
        for (int valueOf : this.f306c) {
            byte[] bArr2 = (byte[]) this.f305b.get(Integer.valueOf(valueOf));
            int i3 = 0;
            while (i3 < bArr2.length) {
                bArr[i2] = bArr2[i3];
                i3++;
                i2++;
            }
        }
        this.f304a.haveNewData(this.f307d, 0, bArr);
    }

    private void m266a(int i, int i2, int i3) {
        int i4;
        this.f306c = null;
        this.f306c = new int[i];
        this.f306c[i2] = i3;
        int i5 = (i - i2) - 1;
        for (i4 = 0; i4 <= i5; i4++) {
            int i6 = i3 - ((i5 - i4) * 2);
            if (i6 < 0) {
                i6 += 256;
            }
            this.f306c[i4] = i6;
        }
        for (i4 = i5 + 1; i4 < i; i4++) {
            i6 = ((i4 - i5) * 2) + i3;
            if (i6 > 255) {
                i6 += InputDeviceCompat.SOURCE_ANY;
            }
            this.f306c[i4] = i6;
        }
    }

    private void m267a(int i, int i2, int i3, byte[] bArr) {
        if (this.f305b.get(Integer.valueOf(i3)) == null) {
            if (i - 1 == i2) {
                this.f307d = bArr[5] & 255;
                this.f305b.put(Integer.valueOf(i3), ByteBufferUtil.bytesCuttForProductProtocol(6, bArr));
            } else {
                this.f305b.put(Integer.valueOf(i3), ByteBufferUtil.bytesCuttForProductProtocol(5, bArr));
            }
            if (this.f305b.size() == i) {
                m265a();
            }
        }
    }

    private void m268b(int i, int i2, int i3) {
        this.f306c = new int[i];
        m266a(i, i2, i3);
    }

    public void addBleCommCallback(NewDataCallback commCallback) {
        this.f304a = commCallback;
    }

    public synchronized void unPackageData(byte[] data) {
        int i = data[2] & 255;
        if (i == 0) {
            this.f304a.haveNewData(data[5] & 255, 0, ByteBufferUtil.bytesCuttForProductProtocol(6, data));
        } else if (i == 240) {
            this.f304a.haveNewData(data[5] & 255, 0, ByteBufferUtil.bytesCuttForProductProtocol(6, data));
        } else if (i < 160) {
            int i2 = (i >> 4) + 1;
            i &= 15;
            int i3 = data[3] & 255;
            switch (m264a(i3)) {
                case 101:
                    break;
                case 102:
                    this.f307d = 0;
                    this.f305b.clear();
                    break;
                case 103:
                    break;
                default:
                    break;
            }
            m268b(i2, i, i3);
            m267a(i2, i, i3, data);
        }
    }
}
