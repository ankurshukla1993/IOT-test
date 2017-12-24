package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfik extends zzfhe<zzfik> implements Cloneable {
    private int zzpla;
    private int zzplb;

    public zzfik() {
        this.zzpla = -1;
        this.zzplb = 0;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.zzfik zzas(com.google.android.gms.internal.zzfhb r7) throws java.io.IOException {
        /*
        r6 = this;
    L_0x0000:
        r0 = r7.zzcts();
        switch(r0) {
            case 0: goto L_0x000d;
            case 8: goto L_0x000e;
            case 16: goto L_0x003f;
            default: goto L_0x0007;
        };
    L_0x0007:
        r0 = super.zza(r7, r0);
        if (r0 != 0) goto L_0x0000;
    L_0x000d:
        return r6;
    L_0x000e:
        r1 = r7.getPosition();
        r2 = r7.zzctv();	 Catch:{ IllegalArgumentException -> 0x0034 }
        switch(r2) {
            case -1: goto L_0x003c;
            case 0: goto L_0x003c;
            case 1: goto L_0x003c;
            case 2: goto L_0x003c;
            case 3: goto L_0x003c;
            case 4: goto L_0x003c;
            case 5: goto L_0x003c;
            case 6: goto L_0x003c;
            case 7: goto L_0x003c;
            case 8: goto L_0x003c;
            case 9: goto L_0x003c;
            case 10: goto L_0x003c;
            case 11: goto L_0x003c;
            case 12: goto L_0x003c;
            case 13: goto L_0x003c;
            case 14: goto L_0x003c;
            case 15: goto L_0x003c;
            case 16: goto L_0x003c;
            case 17: goto L_0x003c;
            default: goto L_0x0019;
        };	 Catch:{ IllegalArgumentException -> 0x0034 }
    L_0x0019:
        r3 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0034 }
        r4 = 43;
        r5 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0034 }
        r5.<init>(r4);	 Catch:{ IllegalArgumentException -> 0x0034 }
        r2 = r5.append(r2);	 Catch:{ IllegalArgumentException -> 0x0034 }
        r4 = " is not a valid enum NetworkType";
        r2 = r2.append(r4);	 Catch:{ IllegalArgumentException -> 0x0034 }
        r2 = r2.toString();	 Catch:{ IllegalArgumentException -> 0x0034 }
        r3.<init>(r2);	 Catch:{ IllegalArgumentException -> 0x0034 }
        throw r3;	 Catch:{ IllegalArgumentException -> 0x0034 }
    L_0x0034:
        r2 = move-exception;
        r7.zzlv(r1);
        r6.zza(r7, r0);
        goto L_0x0000;
    L_0x003c:
        r6.zzpla = r2;	 Catch:{ IllegalArgumentException -> 0x0034 }
        goto L_0x0000;
    L_0x003f:
        r1 = r7.getPosition();
        r2 = r7.zzctv();	 Catch:{ IllegalArgumentException -> 0x0065 }
        switch(r2) {
            case 0: goto L_0x006d;
            case 1: goto L_0x006d;
            case 2: goto L_0x006d;
            case 3: goto L_0x006d;
            case 4: goto L_0x006d;
            case 5: goto L_0x006d;
            case 6: goto L_0x006d;
            case 7: goto L_0x006d;
            case 8: goto L_0x006d;
            case 9: goto L_0x006d;
            case 10: goto L_0x006d;
            case 11: goto L_0x006d;
            case 12: goto L_0x006d;
            case 13: goto L_0x006d;
            case 14: goto L_0x006d;
            case 15: goto L_0x006d;
            case 16: goto L_0x006d;
            case 100: goto L_0x006d;
            default: goto L_0x004a;
        };	 Catch:{ IllegalArgumentException -> 0x0065 }
    L_0x004a:
        r3 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0065 }
        r4 = 45;
        r5 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0065 }
        r5.<init>(r4);	 Catch:{ IllegalArgumentException -> 0x0065 }
        r2 = r5.append(r2);	 Catch:{ IllegalArgumentException -> 0x0065 }
        r4 = " is not a valid enum MobileSubtype";
        r2 = r2.append(r4);	 Catch:{ IllegalArgumentException -> 0x0065 }
        r2 = r2.toString();	 Catch:{ IllegalArgumentException -> 0x0065 }
        r3.<init>(r2);	 Catch:{ IllegalArgumentException -> 0x0065 }
        throw r3;	 Catch:{ IllegalArgumentException -> 0x0065 }
    L_0x0065:
        r2 = move-exception;
        r7.zzlv(r1);
        r6.zza(r7, r0);
        goto L_0x0000;
    L_0x006d:
        r6.zzplb = r2;	 Catch:{ IllegalArgumentException -> 0x0065 }
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzfik.zzas(com.google.android.gms.internal.zzfhb):com.google.android.gms.internal.zzfik");
    }

    private zzfik zzcyb() {
        try {
            return (zzfik) super.zzcxe();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }

    public final /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcyb();
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzfik)) {
            return false;
        }
        zzfik com_google_android_gms_internal_zzfik = (zzfik) obj;
        return this.zzpla != com_google_android_gms_internal_zzfik.zzpla ? false : this.zzplb != com_google_android_gms_internal_zzfik.zzplb ? false : (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzfik.zzpgy == null || com_google_android_gms_internal_zzfik.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzfik.zzpgy);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + this.zzpla) * 31) + this.zzplb) * 31;
        int hashCode2 = (this.zzpgy == null || this.zzpgy.isEmpty()) ? 0 : this.zzpgy.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        return zzas(com_google_android_gms_internal_zzfhb);
    }

    public final void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        if (this.zzpla != -1) {
            com_google_android_gms_internal_zzfhc.zzaa(1, this.zzpla);
        }
        if (this.zzplb != 0) {
            com_google_android_gms_internal_zzfhc.zzaa(2, this.zzplb);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    public final /* synthetic */ zzfhe zzcxe() throws CloneNotSupportedException {
        return (zzfik) clone();
    }

    public final /* synthetic */ zzfhk zzcxf() throws CloneNotSupportedException {
        return (zzfik) clone();
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzpla != -1) {
            zzo += zzfhc.zzad(1, this.zzpla);
        }
        return this.zzplb != 0 ? zzo + zzfhc.zzad(2, this.zzplb) : zzo;
    }
}
