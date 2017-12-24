package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcks extends zzfhe<zzcks> {
    private static volatile zzcks[] zzjhq;
    public Integer zzjhb;
    public String zzjhr;
    public zzckq zzjhs;

    public zzcks() {
        this.zzjhb = null;
        this.zzjhr = null;
        this.zzjhs = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzcks[] zzbar() {
        if (zzjhq == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjhq == null) {
                    zzjhq = new zzcks[0];
                }
            }
        }
        return zzjhq;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcks)) {
            return false;
        }
        zzcks com_google_android_gms_internal_zzcks = (zzcks) obj;
        if (this.zzjhb == null) {
            if (com_google_android_gms_internal_zzcks.zzjhb != null) {
                return false;
            }
        } else if (!this.zzjhb.equals(com_google_android_gms_internal_zzcks.zzjhb)) {
            return false;
        }
        if (this.zzjhr == null) {
            if (com_google_android_gms_internal_zzcks.zzjhr != null) {
                return false;
            }
        } else if (!this.zzjhr.equals(com_google_android_gms_internal_zzcks.zzjhr)) {
            return false;
        }
        if (this.zzjhs == null) {
            if (com_google_android_gms_internal_zzcks.zzjhs != null) {
                return false;
            }
        } else if (!this.zzjhs.equals(com_google_android_gms_internal_zzcks.zzjhs)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzcks.zzpgy == null || com_google_android_gms_internal_zzcks.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzcks.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzjhr == null ? 0 : this.zzjhr.hashCode()) + (((this.zzjhb == null ? 0 : this.zzjhb.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31);
        zzckq com_google_android_gms_internal_zzckq = this.zzjhs;
        hashCode = ((com_google_android_gms_internal_zzckq == null ? 0 : com_google_android_gms_internal_zzckq.hashCode()) + (hashCode * 31)) * 31;
        if (!(this.zzpgy == null || this.zzpgy.isEmpty())) {
            i = this.zzpgy.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            switch (zzcts) {
                case 0:
                    break;
                case 8:
                    this.zzjhb = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 18:
                    this.zzjhr = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 26:
                    if (this.zzjhs == null) {
                        this.zzjhs = new zzckq();
                    }
                    com_google_android_gms_internal_zzfhb.zza(this.zzjhs);
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
        if (this.zzjhb != null) {
            com_google_android_gms_internal_zzfhc.zzaa(1, this.zzjhb.intValue());
        }
        if (this.zzjhr != null) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.zzjhr);
        }
        if (this.zzjhs != null) {
            com_google_android_gms_internal_zzfhc.zza(3, this.zzjhs);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzjhb != null) {
            zzo += zzfhc.zzad(1, this.zzjhb.intValue());
        }
        if (this.zzjhr != null) {
            zzo += zzfhc.zzo(2, this.zzjhr);
        }
        return this.zzjhs != null ? zzo + zzfhc.zzb(3, this.zzjhs) : zzo;
    }
}
