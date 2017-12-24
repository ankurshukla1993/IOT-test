package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcky extends zzfhe<zzcky> {
    private static volatile zzcky[] zzjil;
    public Integer count;
    public String name;
    public zzckz[] zzjim;
    public Long zzjin;
    public Long zzjio;

    public zzcky() {
        this.zzjim = zzckz.zzbaw();
        this.name = null;
        this.zzjin = null;
        this.zzjio = null;
        this.count = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzcky[] zzbav() {
        if (zzjil == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjil == null) {
                    zzjil = new zzcky[0];
                }
            }
        }
        return zzjil;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcky)) {
            return false;
        }
        zzcky com_google_android_gms_internal_zzcky = (zzcky) obj;
        if (!zzfhi.equals(this.zzjim, com_google_android_gms_internal_zzcky.zzjim)) {
            return false;
        }
        if (this.name == null) {
            if (com_google_android_gms_internal_zzcky.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzcky.name)) {
            return false;
        }
        if (this.zzjin == null) {
            if (com_google_android_gms_internal_zzcky.zzjin != null) {
                return false;
            }
        } else if (!this.zzjin.equals(com_google_android_gms_internal_zzcky.zzjin)) {
            return false;
        }
        if (this.zzjio == null) {
            if (com_google_android_gms_internal_zzcky.zzjio != null) {
                return false;
            }
        } else if (!this.zzjio.equals(com_google_android_gms_internal_zzcky.zzjio)) {
            return false;
        }
        if (this.count == null) {
            if (com_google_android_gms_internal_zzcky.count != null) {
                return false;
            }
        } else if (!this.count.equals(com_google_android_gms_internal_zzcky.count)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzcky.zzpgy == null || com_google_android_gms_internal_zzcky.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzcky.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.count == null ? 0 : this.count.hashCode()) + (((this.zzjio == null ? 0 : this.zzjio.hashCode()) + (((this.zzjin == null ? 0 : this.zzjin.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((((getClass().getName().hashCode() + 527) * 31) + zzfhi.hashCode(this.zzjim)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    int zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 10);
                    zzcts = this.zzjim == null ? 0 : this.zzjim.length;
                    Object obj = new zzckz[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjim, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzckz();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzckz();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjim = obj;
                    continue;
                case 18:
                    this.name = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 24:
                    this.zzjin = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 32:
                    this.zzjio = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 40:
                    this.count = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
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
        if (this.zzjim != null && this.zzjim.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjim) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    com_google_android_gms_internal_zzfhc.zza(1, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        if (this.name != null) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.name);
        }
        if (this.zzjin != null) {
            com_google_android_gms_internal_zzfhc.zzf(3, this.zzjin.longValue());
        }
        if (this.zzjio != null) {
            com_google_android_gms_internal_zzfhc.zzf(4, this.zzjio.longValue());
        }
        if (this.count != null) {
            com_google_android_gms_internal_zzfhc.zzaa(5, this.count.intValue());
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzjim != null && this.zzjim.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjim) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    zzo += zzfhc.zzb(1, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        if (this.name != null) {
            zzo += zzfhc.zzo(2, this.name);
        }
        if (this.zzjin != null) {
            zzo += zzfhc.zzc(3, this.zzjin.longValue());
        }
        if (this.zzjio != null) {
            zzo += zzfhc.zzc(4, this.zzjio.longValue());
        }
        return this.count != null ? zzo + zzfhc.zzad(5, this.count.intValue()) : zzo;
    }
}
