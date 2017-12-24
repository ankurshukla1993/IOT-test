package com.ihealth.communication.base.bt;

import com.ihealth.communication.base.protocol.BaseCommProtocol;
import java.util.LinkedList;
import java.util.Queue;

public class BtUnpackageData {
    private byte[] f345a;
    private Queue f346b = new LinkedList();
    private int f347c;
    private boolean f348d = false;
    private BaseCommProtocol f349e;

    private void m272a() {
        int i = 1;
        if (this.f346b.size() >= 6) {
            if (160 == (((Byte) this.f346b.peek()).byteValue() & 255)) {
                this.f348d = true;
                this.f346b.poll();
            }
            if (this.f348d) {
                int byteValue = ((Byte) this.f346b.peek()).byteValue() & 255;
                int i2 = byteValue + 3;
                if (this.f346b.size() >= byteValue + 2) {
                    byte[] bArr = new byte[i2];
                    bArr[0] = (byte) -96;
                    while (i < i2) {
                        Byte valueOf = Byte.valueOf(((Byte) this.f346b.poll()).byteValue());
                        if (valueOf != null) {
                            bArr[i] = valueOf.byteValue();
                        }
                        i++;
                    }
                    if (bArr.length > 3) {
                        byteValue = bArr[3] & 255;
                        if (this.f347c != byteValue) {
                            this.f347c = byteValue;
                        }
                        this.f345a = new byte[bArr.length];
                        for (byteValue = 0; byteValue < bArr.length; byteValue++) {
                            this.f345a[byteValue] = bArr[byteValue];
                        }
                        this.f348d = false;
                        this.f349e.unPackageData(this.f345a);
                        return;
                    }
                    return;
                }
                return;
            }
            this.f346b.poll();
        }
    }

    public void addBtCommProtocol(BaseCommProtocol btCommCallback) {
        this.f349e = btCommCallback;
    }

    public void addReadUsbData(byte[] data, int count) {
        for (int i = 0; i < count; i++) {
            this.f346b.offer(Byte.valueOf(data[i]));
        }
        m272a();
        m272a();
    }
}
