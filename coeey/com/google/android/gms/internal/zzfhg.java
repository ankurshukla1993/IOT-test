package com.google.android.gms.internal;

public final class zzfhg implements Cloneable {
    private static final zzfhh zzpha = new zzfhh();
    private int mSize;
    private boolean zzphb;
    private int[] zzphc;
    private zzfhh[] zzphd;

    zzfhg() {
        this(10);
    }

    private zzfhg(int i) {
        this.zzphb = false;
        int idealIntArraySize = idealIntArraySize(i);
        this.zzphc = new int[idealIntArraySize];
        this.zzphd = new zzfhh[idealIntArraySize];
        this.mSize = 0;
    }

    private static int idealIntArraySize(int i) {
        int i2 = i << 2;
        for (int i3 = 4; i3 < 32; i3++) {
            if (i2 <= (1 << i3) - 12) {
                i2 = (1 << i3) - 12;
                break;
            }
        }
        return i2 / 4;
    }

    private final int zzmb(int i) {
        int i2 = 0;
        int i3 = this.mSize - 1;
        while (i2 <= i3) {
            int i4 = (i2 + i3) >>> 1;
            int i5 = this.zzphc[i4];
            if (i5 < i) {
                i2 = i4 + 1;
            } else if (i5 <= i) {
                return i4;
            } else {
                i3 = i4 - 1;
            }
        }
        return i2 ^ -1;
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        int i = this.mSize;
        zzfhg com_google_android_gms_internal_zzfhg = new zzfhg(i);
        System.arraycopy(this.zzphc, 0, com_google_android_gms_internal_zzfhg.zzphc, 0, i);
        for (int i2 = 0; i2 < i; i2++) {
            if (this.zzphd[i2] != null) {
                com_google_android_gms_internal_zzfhg.zzphd[i2] = (zzfhh) this.zzphd[i2].clone();
            }
        }
        com_google_android_gms_internal_zzfhg.mSize = i;
        return com_google_android_gms_internal_zzfhg;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfhg)) {
            return false;
        }
        zzfhg com_google_android_gms_internal_zzfhg = (zzfhg) obj;
        if (this.mSize != com_google_android_gms_internal_zzfhg.mSize) {
            return false;
        }
        int i;
        boolean z;
        int[] iArr = this.zzphc;
        int[] iArr2 = com_google_android_gms_internal_zzfhg.zzphc;
        int i2 = this.mSize;
        for (i = 0; i < i2; i++) {
            if (iArr[i] != iArr2[i]) {
                z = false;
                break;
            }
        }
        z = true;
        if (z) {
            zzfhh[] com_google_android_gms_internal_zzfhhArr = this.zzphd;
            zzfhh[] com_google_android_gms_internal_zzfhhArr2 = com_google_android_gms_internal_zzfhg.zzphd;
            i2 = this.mSize;
            for (i = 0; i < i2; i++) {
                if (!com_google_android_gms_internal_zzfhhArr[i].equals(com_google_android_gms_internal_zzfhhArr2[i])) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i = 17;
        for (int i2 = 0; i2 < this.mSize; i2++) {
            i = (((i * 31) + this.zzphc[i2]) * 31) + this.zzphd[i2].hashCode();
        }
        return i;
    }

    public final boolean isEmpty() {
        return this.mSize == 0;
    }

    final int size() {
        return this.mSize;
    }

    final void zza(int i, zzfhh com_google_android_gms_internal_zzfhh) {
        int zzmb = zzmb(i);
        if (zzmb >= 0) {
            this.zzphd[zzmb] = com_google_android_gms_internal_zzfhh;
            return;
        }
        zzmb ^= -1;
        if (zzmb >= this.mSize || this.zzphd[zzmb] != zzpha) {
            if (this.mSize >= this.zzphc.length) {
                int idealIntArraySize = idealIntArraySize(this.mSize + 1);
                Object obj = new int[idealIntArraySize];
                Object obj2 = new zzfhh[idealIntArraySize];
                System.arraycopy(this.zzphc, 0, obj, 0, this.zzphc.length);
                System.arraycopy(this.zzphd, 0, obj2, 0, this.zzphd.length);
                this.zzphc = obj;
                this.zzphd = obj2;
            }
            if (this.mSize - zzmb != 0) {
                System.arraycopy(this.zzphc, zzmb, this.zzphc, zzmb + 1, this.mSize - zzmb);
                System.arraycopy(this.zzphd, zzmb, this.zzphd, zzmb + 1, this.mSize - zzmb);
            }
            this.zzphc[zzmb] = i;
            this.zzphd[zzmb] = com_google_android_gms_internal_zzfhh;
            this.mSize++;
            return;
        }
        this.zzphc[zzmb] = i;
        this.zzphd[zzmb] = com_google_android_gms_internal_zzfhh;
    }

    final zzfhh zzlz(int i) {
        int zzmb = zzmb(i);
        return (zzmb < 0 || this.zzphd[zzmb] == zzpha) ? null : this.zzphd[zzmb];
    }

    final zzfhh zzma(int i) {
        return this.zzphd[i];
    }
}
