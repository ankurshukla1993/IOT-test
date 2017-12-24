package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcku extends zzfhe<zzcku> {
    private static volatile zzcku[] zzjhx;
    public String name;
    public Boolean zzjhy;
    public Boolean zzjhz;
    public Integer zzjia;

    public zzcku() {
        this.name = null;
        this.zzjhy = null;
        this.zzjhz = null;
        this.zzjia = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzcku[] zzbas() {
        if (zzjhx == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjhx == null) {
                    zzjhx = new zzcku[0];
                }
            }
        }
        return zzjhx;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcku)) {
            return false;
        }
        zzcku com_google_android_gms_internal_zzcku = (zzcku) obj;
        if (this.name == null) {
            if (com_google_android_gms_internal_zzcku.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzcku.name)) {
            return false;
        }
        if (this.zzjhy == null) {
            if (com_google_android_gms_internal_zzcku.zzjhy != null) {
                return false;
            }
        } else if (!this.zzjhy.equals(com_google_android_gms_internal_zzcku.zzjhy)) {
            return false;
        }
        if (this.zzjhz == null) {
            if (com_google_android_gms_internal_zzcku.zzjhz != null) {
                return false;
            }
        } else if (!this.zzjhz.equals(com_google_android_gms_internal_zzcku.zzjhz)) {
            return false;
        }
        if (this.zzjia == null) {
            if (com_google_android_gms_internal_zzcku.zzjia != null) {
                return false;
            }
        } else if (!this.zzjia.equals(com_google_android_gms_internal_zzcku.zzjia)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzcku.zzpgy == null || com_google_android_gms_internal_zzcku.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzcku.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzjia == null ? 0 : this.zzjia.hashCode()) + (((this.zzjhz == null ? 0 : this.zzjhz.hashCode()) + (((this.zzjhy == null ? 0 : this.zzjhy.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31;
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
                case 10:
                    this.name = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 16:
                    this.zzjhy = Boolean.valueOf(com_google_android_gms_internal_zzfhb.zzcty());
                    continue;
                case 24:
                    this.zzjhz = Boolean.valueOf(com_google_android_gms_internal_zzfhb.zzcty());
                    continue;
                case 32:
                    this.zzjia = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
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
        if (this.name != null) {
            com_google_android_gms_internal_zzfhc.zzn(1, this.name);
        }
        if (this.zzjhy != null) {
            com_google_android_gms_internal_zzfhc.zzl(2, this.zzjhy.booleanValue());
        }
        if (this.zzjhz != null) {
            com_google_android_gms_internal_zzfhc.zzl(3, this.zzjhz.booleanValue());
        }
        if (this.zzjia != null) {
            com_google_android_gms_internal_zzfhc.zzaa(4, this.zzjia.intValue());
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.name != null) {
            zzo += zzfhc.zzo(1, this.name);
        }
        if (this.zzjhy != null) {
            this.zzjhy.booleanValue();
            zzo += zzfhc.zzkw(2) + 1;
        }
        if (this.zzjhz != null) {
            this.zzjhz.booleanValue();
            zzo += zzfhc.zzkw(3) + 1;
        }
        return this.zzjia != null ? zzo + zzfhc.zzad(4, this.zzjia.intValue()) : zzo;
    }
}
