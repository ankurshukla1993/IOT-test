package com.google.android.gms.internal;

import java.io.IOException;

public final class zzckt extends zzfhe<zzckt> {
    public Integer zzjht;
    public String zzjhu;
    public Boolean zzjhv;
    public String[] zzjhw;

    public zzckt() {
        this.zzjht = null;
        this.zzjhu = null;
        this.zzjhv = null;
        this.zzjhw = zzfhn.EMPTY_STRING_ARRAY;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.zzckt zzi(com.google.android.gms.internal.zzfhb r8) throws java.io.IOException {
        /*
        r7 = this;
        r1 = 0;
    L_0x0001:
        r0 = r8.zzcts();
        switch(r0) {
            case 0: goto L_0x000e;
            case 8: goto L_0x000f;
            case 18: goto L_0x0044;
            case 24: goto L_0x004b;
            case 34: goto L_0x0056;
            default: goto L_0x0008;
        };
    L_0x0008:
        r0 = super.zza(r8, r0);
        if (r0 != 0) goto L_0x0001;
    L_0x000e:
        return r7;
    L_0x000f:
        r2 = r8.getPosition();
        r3 = r8.zzcuh();	 Catch:{ IllegalArgumentException -> 0x0035 }
        switch(r3) {
            case 0: goto L_0x003d;
            case 1: goto L_0x003d;
            case 2: goto L_0x003d;
            case 3: goto L_0x003d;
            case 4: goto L_0x003d;
            case 5: goto L_0x003d;
            case 6: goto L_0x003d;
            default: goto L_0x001a;
        };	 Catch:{ IllegalArgumentException -> 0x0035 }
    L_0x001a:
        r4 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0035 }
        r5 = 41;
        r6 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0035 }
        r6.<init>(r5);	 Catch:{ IllegalArgumentException -> 0x0035 }
        r3 = r6.append(r3);	 Catch:{ IllegalArgumentException -> 0x0035 }
        r5 = " is not a valid enum MatchType";
        r3 = r3.append(r5);	 Catch:{ IllegalArgumentException -> 0x0035 }
        r3 = r3.toString();	 Catch:{ IllegalArgumentException -> 0x0035 }
        r4.<init>(r3);	 Catch:{ IllegalArgumentException -> 0x0035 }
        throw r4;	 Catch:{ IllegalArgumentException -> 0x0035 }
    L_0x0035:
        r3 = move-exception;
        r8.zzlv(r2);
        r7.zza(r8, r0);
        goto L_0x0001;
    L_0x003d:
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ IllegalArgumentException -> 0x0035 }
        r7.zzjht = r3;	 Catch:{ IllegalArgumentException -> 0x0035 }
        goto L_0x0001;
    L_0x0044:
        r0 = r8.readString();
        r7.zzjhu = r0;
        goto L_0x0001;
    L_0x004b:
        r0 = r8.zzcty();
        r0 = java.lang.Boolean.valueOf(r0);
        r7.zzjhv = r0;
        goto L_0x0001;
    L_0x0056:
        r0 = 34;
        r2 = com.google.android.gms.internal.zzfhn.zzb(r8, r0);
        r0 = r7.zzjhw;
        if (r0 != 0) goto L_0x007c;
    L_0x0060:
        r0 = r1;
    L_0x0061:
        r2 = r2 + r0;
        r2 = new java.lang.String[r2];
        if (r0 == 0) goto L_0x006b;
    L_0x0066:
        r3 = r7.zzjhw;
        java.lang.System.arraycopy(r3, r1, r2, r1, r0);
    L_0x006b:
        r3 = r2.length;
        r3 = r3 + -1;
        if (r0 >= r3) goto L_0x0080;
    L_0x0070:
        r3 = r8.readString();
        r2[r0] = r3;
        r8.zzcts();
        r0 = r0 + 1;
        goto L_0x006b;
    L_0x007c:
        r0 = r7.zzjhw;
        r0 = r0.length;
        goto L_0x0061;
    L_0x0080:
        r3 = r8.readString();
        r2[r0] = r3;
        r7.zzjhw = r2;
        goto L_0x0001;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckt.zzi(com.google.android.gms.internal.zzfhb):com.google.android.gms.internal.zzckt");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzckt)) {
            return false;
        }
        zzckt com_google_android_gms_internal_zzckt = (zzckt) obj;
        if (this.zzjht == null) {
            if (com_google_android_gms_internal_zzckt.zzjht != null) {
                return false;
            }
        } else if (!this.zzjht.equals(com_google_android_gms_internal_zzckt.zzjht)) {
            return false;
        }
        if (this.zzjhu == null) {
            if (com_google_android_gms_internal_zzckt.zzjhu != null) {
                return false;
            }
        } else if (!this.zzjhu.equals(com_google_android_gms_internal_zzckt.zzjhu)) {
            return false;
        }
        if (this.zzjhv == null) {
            if (com_google_android_gms_internal_zzckt.zzjhv != null) {
                return false;
            }
        } else if (!this.zzjhv.equals(com_google_android_gms_internal_zzckt.zzjhv)) {
            return false;
        }
        return !zzfhi.equals(this.zzjhw, com_google_android_gms_internal_zzckt.zzjhw) ? false : (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzckt.zzpgy == null || com_google_android_gms_internal_zzckt.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzckt.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((this.zzjhv == null ? 0 : this.zzjhv.hashCode()) + (((this.zzjhu == null ? 0 : this.zzjhu.hashCode()) + (((this.zzjht == null ? 0 : this.zzjht.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + zzfhi.hashCode(this.zzjhw)) * 31;
        if (!(this.zzpgy == null || this.zzpgy.isEmpty())) {
            i = this.zzpgy.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        return zzi(com_google_android_gms_internal_zzfhb);
    }

    public final void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        if (this.zzjht != null) {
            com_google_android_gms_internal_zzfhc.zzaa(1, this.zzjht.intValue());
        }
        if (this.zzjhu != null) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.zzjhu);
        }
        if (this.zzjhv != null) {
            com_google_android_gms_internal_zzfhc.zzl(3, this.zzjhv.booleanValue());
        }
        if (this.zzjhw != null && this.zzjhw.length > 0) {
            for (String str : this.zzjhw) {
                if (str != null) {
                    com_google_android_gms_internal_zzfhc.zzn(4, str);
                }
            }
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int i = 0;
        int zzo = super.zzo();
        if (this.zzjht != null) {
            zzo += zzfhc.zzad(1, this.zzjht.intValue());
        }
        if (this.zzjhu != null) {
            zzo += zzfhc.zzo(2, this.zzjhu);
        }
        if (this.zzjhv != null) {
            this.zzjhv.booleanValue();
            zzo += zzfhc.zzkw(3) + 1;
        }
        if (this.zzjhw == null || this.zzjhw.length <= 0) {
            return zzo;
        }
        int i2 = 0;
        int i3 = 0;
        while (i < this.zzjhw.length) {
            String str = this.zzjhw[i];
            if (str != null) {
                i3++;
                i2 += zzfhc.zztd(str);
            }
            i++;
        }
        return (zzo + i2) + (i3 * 1);
    }
}
