package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfgi {
    private static final zzfgi zzpel = new zzfgi(0, new int[0], new Object[0], false);
    private int count;
    private boolean zzpah;
    private int zzpbt;
    private int[] zzpem;
    private Object[] zzpen;

    private zzfgi() {
        this(0, new int[8], new Object[8], true);
    }

    private zzfgi(int i, int[] iArr, Object[] objArr, boolean z) {
        this.zzpbt = -1;
        this.count = i;
        this.zzpem = iArr;
        this.zzpen = objArr;
        this.zzpah = z;
    }

    private final zzfgi zza(zzfdq com_google_android_gms_internal_zzfdq) throws IOException {
        int zzcts;
        do {
            zzcts = com_google_android_gms_internal_zzfdq.zzcts();
            if (zzcts == 0) {
                break;
            }
        } while (zzb(zzcts, com_google_android_gms_internal_zzfdq));
        return this;
    }

    static zzfgi zzb(zzfgi com_google_android_gms_internal_zzfgi, zzfgi com_google_android_gms_internal_zzfgi2) {
        int i = com_google_android_gms_internal_zzfgi.count + com_google_android_gms_internal_zzfgi2.count;
        Object copyOf = Arrays.copyOf(com_google_android_gms_internal_zzfgi.zzpem, i);
        System.arraycopy(com_google_android_gms_internal_zzfgi2.zzpem, 0, copyOf, com_google_android_gms_internal_zzfgi.count, com_google_android_gms_internal_zzfgi2.count);
        Object copyOf2 = Arrays.copyOf(com_google_android_gms_internal_zzfgi.zzpen, i);
        System.arraycopy(com_google_android_gms_internal_zzfgi2.zzpen, 0, copyOf2, com_google_android_gms_internal_zzfgi.count, com_google_android_gms_internal_zzfgi2.count);
        return new zzfgi(i, copyOf, copyOf2, true);
    }

    private void zzb(int i, Object obj) {
        if (this.count == this.zzpem.length) {
            int i2 = (this.count < 4 ? 8 : this.count >> 1) + this.count;
            this.zzpem = Arrays.copyOf(this.zzpem, i2);
            this.zzpen = Arrays.copyOf(this.zzpen, i2);
        }
        this.zzpem[this.count] = i;
        this.zzpen[this.count] = obj;
        this.count++;
    }

    public static zzfgi zzcwu() {
        return zzpel;
    }

    static zzfgi zzcwv() {
        return new zzfgi();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof zzfgi)) {
            return false;
        }
        zzfgi com_google_android_gms_internal_zzfgi = (zzfgi) obj;
        if (this.count == com_google_android_gms_internal_zzfgi.count) {
            int i;
            boolean z;
            int[] iArr = this.zzpem;
            int[] iArr2 = com_google_android_gms_internal_zzfgi.zzpem;
            int i2 = this.count;
            for (i = 0; i < i2; i++) {
                if (iArr[i] != iArr2[i]) {
                    z = false;
                    break;
                }
            }
            z = true;
            if (z) {
                Object[] objArr = this.zzpen;
                Object[] objArr2 = com_google_android_gms_internal_zzfgi.zzpen;
                i2 = this.count;
                for (i = 0; i < i2; i++) {
                    if (!objArr[i].equals(objArr2[i])) {
                        z = false;
                        break;
                    }
                }
                z = true;
                if (z) {
                    return true;
                }
            }
        }
        return false;
    }

    public final int hashCode() {
        return ((((this.count + 527) * 31) + Arrays.hashCode(this.zzpem)) * 31) + Arrays.deepHashCode(this.zzpen);
    }

    public final void zza(zzfdv com_google_android_gms_internal_zzfdv) throws IOException {
        for (int i = 0; i < this.count; i++) {
            int i2 = this.zzpem[i];
            int i3 = i2 >>> 3;
            switch (i2 & 7) {
                case 0:
                    com_google_android_gms_internal_zzfdv.zza(i3, ((Long) this.zzpen[i]).longValue());
                    break;
                case 1:
                    com_google_android_gms_internal_zzfdv.zzb(i3, ((Long) this.zzpen[i]).longValue());
                    break;
                case 2:
                    com_google_android_gms_internal_zzfdv.zza(i3, (zzfdh) this.zzpen[i]);
                    break;
                case 3:
                    com_google_android_gms_internal_zzfdv.zzz(i3, 3);
                    ((zzfgi) this.zzpen[i]).zza(com_google_android_gms_internal_zzfdv);
                    com_google_android_gms_internal_zzfdv.zzz(i3, 4);
                    break;
                case 5:
                    com_google_android_gms_internal_zzfdv.zzac(i3, ((Integer) this.zzpen[i]).intValue());
                    break;
                default:
                    throw zzfew.zzcvw();
            }
        }
    }

    final boolean zzb(int i, zzfdq com_google_android_gms_internal_zzfdq) throws IOException {
        if (this.zzpah) {
            int i2 = i >>> 3;
            switch (i & 7) {
                case 0:
                    zzb(i, Long.valueOf(com_google_android_gms_internal_zzfdq.zzctu()));
                    return true;
                case 1:
                    zzb(i, Long.valueOf(com_google_android_gms_internal_zzfdq.zzctw()));
                    return true;
                case 2:
                    zzb(i, com_google_android_gms_internal_zzfdq.zzcua());
                    return true;
                case 3:
                    Object com_google_android_gms_internal_zzfgi = new zzfgi();
                    com_google_android_gms_internal_zzfgi.zza(com_google_android_gms_internal_zzfdq);
                    com_google_android_gms_internal_zzfdq.zzkf((i2 << 3) | 4);
                    zzb(i, com_google_android_gms_internal_zzfgi);
                    return true;
                case 4:
                    return false;
                case 5:
                    zzb(i, Integer.valueOf(com_google_android_gms_internal_zzfdq.zzctx()));
                    return true;
                default:
                    throw zzfew.zzcvw();
            }
        }
        throw new UnsupportedOperationException();
    }

    public final void zzbim() {
        this.zzpah = false;
    }

    final void zzd(StringBuilder stringBuilder, int i) {
        for (int i2 = 0; i2 < this.count; i2++) {
            zzffl.zzb(stringBuilder, i, String.valueOf(this.zzpem[i2] >>> 3), this.zzpen[i2]);
        }
    }

    public final int zzhl() {
        int i = this.zzpbt;
        if (i == -1) {
            i = 0;
            for (int i2 = 0; i2 < this.count; i2++) {
                int i3 = this.zzpem[i2];
                int i4 = i3 >>> 3;
                switch (i3 & 7) {
                    case 0:
                        i += zzfdv.zzd(i4, ((Long) this.zzpen[i2]).longValue());
                        break;
                    case 1:
                        i += zzfdv.zze(i4, ((Long) this.zzpen[i2]).longValue());
                        break;
                    case 2:
                        i += zzfdv.zzb(i4, (zzfdh) this.zzpen[i2]);
                        break;
                    case 3:
                        i += ((zzfgi) this.zzpen[i2]).zzhl() + (zzfdv.zzkw(i4) << 1);
                        break;
                    case 5:
                        i += zzfdv.zzaf(i4, ((Integer) this.zzpen[i2]).intValue());
                        break;
                    default:
                        throw new IllegalStateException(zzfew.zzcvw());
                }
            }
            this.zzpbt = i;
        }
        return i;
    }
}
