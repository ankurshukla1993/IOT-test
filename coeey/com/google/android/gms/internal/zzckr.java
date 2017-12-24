package com.google.android.gms.internal;

import java.io.IOException;

public final class zzckr extends zzfhe<zzckr> {
    public Integer zzjhl;
    public Boolean zzjhm;
    public String zzjhn;
    public String zzjho;
    public String zzjhp;

    public zzckr() {
        this.zzjhl = null;
        this.zzjhm = null;
        this.zzjhn = null;
        this.zzjho = null;
        this.zzjhp = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final com.google.android.gms.internal.zzckr zzh(com.google.android.gms.internal.zzfhb r7) throws java.io.IOException {
        /*
        r6 = this;
    L_0x0000:
        r0 = r7.zzcts();
        switch(r0) {
            case 0: goto L_0x000d;
            case 8: goto L_0x000e;
            case 16: goto L_0x0043;
            case 26: goto L_0x004e;
            case 34: goto L_0x0055;
            case 42: goto L_0x005c;
            default: goto L_0x0007;
        };
    L_0x0007:
        r0 = super.zza(r7, r0);
        if (r0 != 0) goto L_0x0000;
    L_0x000d:
        return r6;
    L_0x000e:
        r1 = r7.getPosition();
        r2 = r7.zzcuh();	 Catch:{ IllegalArgumentException -> 0x0034 }
        switch(r2) {
            case 0: goto L_0x003c;
            case 1: goto L_0x003c;
            case 2: goto L_0x003c;
            case 3: goto L_0x003c;
            case 4: goto L_0x003c;
            default: goto L_0x0019;
        };	 Catch:{ IllegalArgumentException -> 0x0034 }
    L_0x0019:
        r3 = new java.lang.IllegalArgumentException;	 Catch:{ IllegalArgumentException -> 0x0034 }
        r4 = 46;
        r5 = new java.lang.StringBuilder;	 Catch:{ IllegalArgumentException -> 0x0034 }
        r5.<init>(r4);	 Catch:{ IllegalArgumentException -> 0x0034 }
        r2 = r5.append(r2);	 Catch:{ IllegalArgumentException -> 0x0034 }
        r4 = " is not a valid enum ComparisonType";
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
        r2 = java.lang.Integer.valueOf(r2);	 Catch:{ IllegalArgumentException -> 0x0034 }
        r6.zzjhl = r2;	 Catch:{ IllegalArgumentException -> 0x0034 }
        goto L_0x0000;
    L_0x0043:
        r0 = r7.zzcty();
        r0 = java.lang.Boolean.valueOf(r0);
        r6.zzjhm = r0;
        goto L_0x0000;
    L_0x004e:
        r0 = r7.readString();
        r6.zzjhn = r0;
        goto L_0x0000;
    L_0x0055:
        r0 = r7.readString();
        r6.zzjho = r0;
        goto L_0x0000;
    L_0x005c:
        r0 = r7.readString();
        r6.zzjhp = r0;
        goto L_0x0000;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzckr.zzh(com.google.android.gms.internal.zzfhb):com.google.android.gms.internal.zzckr");
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzckr)) {
            return false;
        }
        zzckr com_google_android_gms_internal_zzckr = (zzckr) obj;
        if (this.zzjhl == null) {
            if (com_google_android_gms_internal_zzckr.zzjhl != null) {
                return false;
            }
        } else if (!this.zzjhl.equals(com_google_android_gms_internal_zzckr.zzjhl)) {
            return false;
        }
        if (this.zzjhm == null) {
            if (com_google_android_gms_internal_zzckr.zzjhm != null) {
                return false;
            }
        } else if (!this.zzjhm.equals(com_google_android_gms_internal_zzckr.zzjhm)) {
            return false;
        }
        if (this.zzjhn == null) {
            if (com_google_android_gms_internal_zzckr.zzjhn != null) {
                return false;
            }
        } else if (!this.zzjhn.equals(com_google_android_gms_internal_zzckr.zzjhn)) {
            return false;
        }
        if (this.zzjho == null) {
            if (com_google_android_gms_internal_zzckr.zzjho != null) {
                return false;
            }
        } else if (!this.zzjho.equals(com_google_android_gms_internal_zzckr.zzjho)) {
            return false;
        }
        if (this.zzjhp == null) {
            if (com_google_android_gms_internal_zzckr.zzjhp != null) {
                return false;
            }
        } else if (!this.zzjhp.equals(com_google_android_gms_internal_zzckr.zzjhp)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzckr.zzpgy == null || com_google_android_gms_internal_zzckr.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzckr.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzjhp == null ? 0 : this.zzjhp.hashCode()) + (((this.zzjho == null ? 0 : this.zzjho.hashCode()) + (((this.zzjhn == null ? 0 : this.zzjhn.hashCode()) + (((this.zzjhm == null ? 0 : this.zzjhm.hashCode()) + (((this.zzjhl == null ? 0 : this.zzjhl.intValue()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
        if (!(this.zzpgy == null || this.zzpgy.isEmpty())) {
            i = this.zzpgy.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        return zzh(com_google_android_gms_internal_zzfhb);
    }

    public final void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        if (this.zzjhl != null) {
            com_google_android_gms_internal_zzfhc.zzaa(1, this.zzjhl.intValue());
        }
        if (this.zzjhm != null) {
            com_google_android_gms_internal_zzfhc.zzl(2, this.zzjhm.booleanValue());
        }
        if (this.zzjhn != null) {
            com_google_android_gms_internal_zzfhc.zzn(3, this.zzjhn);
        }
        if (this.zzjho != null) {
            com_google_android_gms_internal_zzfhc.zzn(4, this.zzjho);
        }
        if (this.zzjhp != null) {
            com_google_android_gms_internal_zzfhc.zzn(5, this.zzjhp);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzjhl != null) {
            zzo += zzfhc.zzad(1, this.zzjhl.intValue());
        }
        if (this.zzjhm != null) {
            this.zzjhm.booleanValue();
            zzo += zzfhc.zzkw(2) + 1;
        }
        if (this.zzjhn != null) {
            zzo += zzfhc.zzo(3, this.zzjhn);
        }
        if (this.zzjho != null) {
            zzo += zzfhc.zzo(4, this.zzjho);
        }
        return this.zzjhp != null ? zzo + zzfhc.zzo(5, this.zzjhp) : zzo;
    }
}
