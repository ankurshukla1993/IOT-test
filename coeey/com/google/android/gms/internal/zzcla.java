package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcla extends zzfhe<zzcla> {
    public zzclb[] zzjir;

    public zzcla() {
        this.zzjir = zzclb.zzbax();
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcla)) {
            return false;
        }
        zzcla com_google_android_gms_internal_zzcla = (zzcla) obj;
        return !zzfhi.equals(this.zzjir, com_google_android_gms_internal_zzcla.zzjir) ? false : (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzcla.zzpgy == null || com_google_android_gms_internal_zzcla.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzcla.zzpgy);
    }

    public final int hashCode() {
        int hashCode = (((getClass().getName().hashCode() + 527) * 31) + zzfhi.hashCode(this.zzjir)) * 31;
        int hashCode2 = (this.zzpgy == null || this.zzpgy.isEmpty()) ? 0 : this.zzpgy.hashCode();
        return hashCode2 + hashCode;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            switch (zzcts) {
                case 0:
                    break;
                case 10:
                    int zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 10);
                    zzcts = this.zzjir == null ? 0 : this.zzjir.length;
                    Object obj = new zzclb[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjir, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzclb();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzclb();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjir = obj;
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
        if (this.zzjir != null && this.zzjir.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjir) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    com_google_android_gms_internal_zzfhc.zza(1, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzjir != null && this.zzjir.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjir) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    zzo += zzfhc.zzb(1, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        return zzo;
    }
}
