package com.google.android.gms.internal;

import java.io.IOException;

class zzfdo extends zzfdn {
    protected final byte[] zzjkl;

    zzfdo(byte[] bArr) {
        this.zzjkl = bArr;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfdh)) {
            return false;
        }
        if (size() != ((zzfdh) obj).size()) {
            return false;
        }
        if (size() == 0) {
            return true;
        }
        if (!(obj instanceof zzfdo)) {
            return obj.equals(this);
        }
        zzfdo com_google_android_gms_internal_zzfdo = (zzfdo) obj;
        int zzcto = zzcto();
        int zzcto2 = com_google_android_gms_internal_zzfdo.zzcto();
        return (zzcto == 0 || zzcto2 == 0 || zzcto == zzcto2) ? zza((zzfdo) obj, 0, size()) : false;
    }

    public int size() {
        return this.zzjkl.length;
    }

    final void zza(zzfdg com_google_android_gms_internal_zzfdg) throws IOException {
        com_google_android_gms_internal_zzfdg.zzd(this.zzjkl, zzctp(), size());
    }

    final boolean zza(zzfdh com_google_android_gms_internal_zzfdh, int i, int i2) {
        if (i2 > com_google_android_gms_internal_zzfdh.size()) {
            throw new IllegalArgumentException("Length too large: " + i2 + size());
        } else if (i + i2 > com_google_android_gms_internal_zzfdh.size()) {
            throw new IllegalArgumentException("Ran off end of other: " + i + ", " + i2 + ", " + com_google_android_gms_internal_zzfdh.size());
        } else if (!(com_google_android_gms_internal_zzfdh instanceof zzfdo)) {
            return com_google_android_gms_internal_zzfdh.zzx(i, i + i2).equals(zzx(0, i2));
        } else {
            zzfdo com_google_android_gms_internal_zzfdo = (zzfdo) com_google_android_gms_internal_zzfdh;
            byte[] bArr = this.zzjkl;
            byte[] bArr2 = com_google_android_gms_internal_zzfdo.zzjkl;
            int zzctp = zzctp() + i2;
            int zzctp2 = zzctp();
            int zzctp3 = com_google_android_gms_internal_zzfdo.zzctp() + i;
            while (zzctp2 < zzctp) {
                if (bArr[zzctp2] != bArr2[zzctp3]) {
                    return false;
                }
                zzctp2++;
                zzctp3++;
            }
            return true;
        }
    }

    protected void zzb(byte[] bArr, int i, int i2, int i3) {
        System.arraycopy(this.zzjkl, i, bArr, i2, i3);
    }

    public final zzfdq zzctl() {
        return zzfdq.zzb(this.zzjkl, zzctp(), size(), true);
    }

    protected int zzctp() {
        return 0;
    }

    protected final int zzg(int i, int i2, int i3) {
        return zzfer.zza(i, this.zzjkl, zzctp() + i2, i3);
    }

    public byte zzkd(int i) {
        return this.zzjkl[i];
    }

    public final zzfdh zzx(int i, int i2) {
        int zzh = zzfdh.zzh(i, i2, size());
        return zzh == 0 ? zzfdh.zzpal : new zzfdk(this.zzjkl, zzctp() + i, zzh);
    }
}
