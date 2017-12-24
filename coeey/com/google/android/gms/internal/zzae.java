package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public final class zzae {
    private static Comparator<byte[]> zzbs = new zzaf();
    private final List<byte[]> zzbo = new LinkedList();
    private final List<byte[]> zzbp = new ArrayList(64);
    private int zzbq = 0;
    private final int zzbr = 4096;

    public zzae(int i) {
    }

    private final synchronized void zzm() {
        while (this.zzbq > this.zzbr) {
            byte[] bArr = (byte[]) this.zzbo.remove(0);
            this.zzbp.remove(bArr);
            this.zzbq -= bArr.length;
        }
    }

    public final synchronized void zza(byte[] bArr) {
        if (bArr != null) {
            if (bArr.length <= this.zzbr) {
                this.zzbo.add(bArr);
                int binarySearch = Collections.binarySearch(this.zzbp, bArr, zzbs);
                if (binarySearch < 0) {
                    binarySearch = (-binarySearch) - 1;
                }
                this.zzbp.add(binarySearch, bArr);
                this.zzbq += bArr.length;
                zzm();
            }
        }
    }

    public final synchronized byte[] zzb(int i) {
        byte[] bArr;
        for (int i2 = 0; i2 < this.zzbp.size(); i2++) {
            bArr = (byte[]) this.zzbp.get(i2);
            if (bArr.length >= i) {
                this.zzbq -= bArr.length;
                this.zzbp.remove(i2);
                this.zzbo.remove(bArr);
                break;
            }
        }
        bArr = new byte[i];
        return bArr;
    }
}
