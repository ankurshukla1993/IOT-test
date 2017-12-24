package com.google.android.gms.internal;

final class zzfdk extends zzfdo {
    private final int zzpao;
    private final int zzpap;

    zzfdk(byte[] bArr, int i, int i2) {
        super(bArr);
        zzfdh.zzh(i, i + i2, bArr.length);
        this.zzpao = i;
        this.zzpap = i2;
    }

    public final int size() {
        return this.zzpap;
    }

    protected final void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzjkl, zzctp() + i, bArr, i2, i3);
    }

    protected final int zzctp() {
        return this.zzpao;
    }

    public final byte zzkd(int i) {
        zzfdh.zzy(i, size());
        return this.zzjkl[this.zzpao + i];
    }
}
