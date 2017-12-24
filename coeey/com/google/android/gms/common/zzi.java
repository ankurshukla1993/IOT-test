package com.google.android.gms.common;

import java.lang.ref.WeakReference;

abstract class zzi extends zzg {
    private static final WeakReference<byte[]> zzfik = new WeakReference(null);
    private WeakReference<byte[]> zzfij = zzfik;

    zzi(byte[] bArr) {
        super(bArr);
    }

    final byte[] getBytes() {
        byte[] bArr;
        synchronized (this) {
            bArr = (byte[]) this.zzfij.get();
            if (bArr == null) {
                bArr = zzafq();
                this.zzfij = new WeakReference(bArr);
            }
        }
        return bArr;
    }

    protected abstract byte[] zzafq();
}
