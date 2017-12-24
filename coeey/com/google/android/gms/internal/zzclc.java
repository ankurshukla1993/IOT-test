package com.google.android.gms.internal;

import java.io.IOException;

public final class zzclc extends zzfhe<zzclc> {
    public long[] zzjju;
    public long[] zzjjv;

    public zzclc() {
        this.zzjju = zzfhn.zzphm;
        this.zzjjv = zzfhn.zzphm;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzclc)) {
            return false;
        }
        zzclc com_google_android_gms_internal_zzclc = (zzclc) obj;
        return !zzfhi.equals(this.zzjju, com_google_android_gms_internal_zzclc.zzjju) ? false : !zzfhi.equals(this.zzjjv, com_google_android_gms_internal_zzclc.zzjjv) ? false : (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzclc.zzpgy == null || com_google_android_gms_internal_zzclc.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzclc.zzpgy);
    }

    public final int hashCode() {
        int hashCode = (((((getClass().getName().hashCode() + 527) * 31) + zzfhi.hashCode(this.zzjju)) * 31) + zzfhi.hashCode(this.zzjjv)) * 31;
        int hashCode2 = (this.zzpgy == null || this.zzpgy.isEmpty()) ? 0 : this.zzpgy.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            int zzb;
            Object obj;
            int zzki;
            Object obj2;
            switch (zzcts) {
                case 0:
                    break;
                case 8:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 8);
                    zzcts = this.zzjju == null ? 0 : this.zzjju.length;
                    obj = new long[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjju, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = com_google_android_gms_internal_zzfhb.zzcum();
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = com_google_android_gms_internal_zzfhb.zzcum();
                    this.zzjju = obj;
                    continue;
                case 10:
                    zzki = com_google_android_gms_internal_zzfhb.zzki(com_google_android_gms_internal_zzfhb.zzcuh());
                    zzb = com_google_android_gms_internal_zzfhb.getPosition();
                    zzcts = 0;
                    while (com_google_android_gms_internal_zzfhb.zzcuj() > 0) {
                        com_google_android_gms_internal_zzfhb.zzcum();
                        zzcts++;
                    }
                    com_google_android_gms_internal_zzfhb.zzlv(zzb);
                    zzb = this.zzjju == null ? 0 : this.zzjju.length;
                    obj2 = new long[(zzcts + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzjju, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfhb.zzcum();
                        zzb++;
                    }
                    this.zzjju = obj2;
                    com_google_android_gms_internal_zzfhb.zzkj(zzki);
                    continue;
                case 16:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 16);
                    zzcts = this.zzjjv == null ? 0 : this.zzjjv.length;
                    obj = new long[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjjv, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = com_google_android_gms_internal_zzfhb.zzcum();
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = com_google_android_gms_internal_zzfhb.zzcum();
                    this.zzjjv = obj;
                    continue;
                case 18:
                    zzki = com_google_android_gms_internal_zzfhb.zzki(com_google_android_gms_internal_zzfhb.zzcuh());
                    zzb = com_google_android_gms_internal_zzfhb.getPosition();
                    zzcts = 0;
                    while (com_google_android_gms_internal_zzfhb.zzcuj() > 0) {
                        com_google_android_gms_internal_zzfhb.zzcum();
                        zzcts++;
                    }
                    com_google_android_gms_internal_zzfhb.zzlv(zzb);
                    zzb = this.zzjjv == null ? 0 : this.zzjjv.length;
                    obj2 = new long[(zzcts + zzb)];
                    if (zzb != 0) {
                        System.arraycopy(this.zzjjv, 0, obj2, 0, zzb);
                    }
                    while (zzb < obj2.length) {
                        obj2[zzb] = com_google_android_gms_internal_zzfhb.zzcum();
                        zzb++;
                    }
                    this.zzjjv = obj2;
                    com_google_android_gms_internal_zzfhb.zzkj(zzki);
                    continue;
                default:
                    if (!super.zza(com_google_android_gms_internal_zzfhb, zzcts)) {
                        break;
                    }
                    continue;
            }
            return this;
        }
    }

    public final void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        int i = 0;
        if (this.zzjju != null && this.zzjju.length > 0) {
            for (long zza : this.zzjju) {
                com_google_android_gms_internal_zzfhc.zza(1, zza);
            }
        }
        if (this.zzjjv != null && this.zzjjv.length > 0) {
            while (i < this.zzjjv.length) {
                com_google_android_gms_internal_zzfhc.zza(2, this.zzjjv[i]);
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int i;
        int i2;
        int i3 = 0;
        int zzo = super.zzo();
        if (this.zzjju == null || this.zzjju.length <= 0) {
            i = zzo;
        } else {
            i2 = 0;
            for (long zzdh : this.zzjju) {
                i2 += zzfhc.zzdh(zzdh);
            }
            i = (zzo + i2) + (this.zzjju.length * 1);
        }
        if (this.zzjjv == null || this.zzjjv.length <= 0) {
            return i;
        }
        i2 = 0;
        while (i3 < this.zzjjv.length) {
            i2 += zzfhc.zzdh(this.zzjjv[i3]);
            i3++;
        }
        return (i + i2) + (this.zzjjv.length * 1);
    }
}
