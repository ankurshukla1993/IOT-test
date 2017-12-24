package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcld extends zzfhe<zzcld> {
    private static volatile zzcld[] zzjjw;
    public String name;
    public String zzfzi;
    private Float zzjgp;
    public Double zzjgq;
    public Long zzjiq;
    public Long zzjjx;

    public zzcld() {
        this.zzjjx = null;
        this.name = null;
        this.zzfzi = null;
        this.zzjiq = null;
        this.zzjgp = null;
        this.zzjgq = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzcld[] zzbay() {
        if (zzjjw == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjjw == null) {
                    zzjjw = new zzcld[0];
                }
            }
        }
        return zzjjw;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcld)) {
            return false;
        }
        zzcld com_google_android_gms_internal_zzcld = (zzcld) obj;
        if (this.zzjjx == null) {
            if (com_google_android_gms_internal_zzcld.zzjjx != null) {
                return false;
            }
        } else if (!this.zzjjx.equals(com_google_android_gms_internal_zzcld.zzjjx)) {
            return false;
        }
        if (this.name == null) {
            if (com_google_android_gms_internal_zzcld.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzcld.name)) {
            return false;
        }
        if (this.zzfzi == null) {
            if (com_google_android_gms_internal_zzcld.zzfzi != null) {
                return false;
            }
        } else if (!this.zzfzi.equals(com_google_android_gms_internal_zzcld.zzfzi)) {
            return false;
        }
        if (this.zzjiq == null) {
            if (com_google_android_gms_internal_zzcld.zzjiq != null) {
                return false;
            }
        } else if (!this.zzjiq.equals(com_google_android_gms_internal_zzcld.zzjiq)) {
            return false;
        }
        if (this.zzjgp == null) {
            if (com_google_android_gms_internal_zzcld.zzjgp != null) {
                return false;
            }
        } else if (!this.zzjgp.equals(com_google_android_gms_internal_zzcld.zzjgp)) {
            return false;
        }
        if (this.zzjgq == null) {
            if (com_google_android_gms_internal_zzcld.zzjgq != null) {
                return false;
            }
        } else if (!this.zzjgq.equals(com_google_android_gms_internal_zzcld.zzjgq)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzcld.zzpgy == null || com_google_android_gms_internal_zzcld.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzcld.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzjgq == null ? 0 : this.zzjgq.hashCode()) + (((this.zzjgp == null ? 0 : this.zzjgp.hashCode()) + (((this.zzjiq == null ? 0 : this.zzjiq.hashCode()) + (((this.zzfzi == null ? 0 : this.zzfzi.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + (((this.zzjjx == null ? 0 : this.zzjjx.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                    this.zzjjx = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 18:
                    this.name = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 26:
                    this.zzfzi = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 32:
                    this.zzjiq = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 45:
                    this.zzjgp = Float.valueOf(Float.intBitsToFloat(com_google_android_gms_internal_zzfhb.zzcun()));
                    continue;
                case 49:
                    this.zzjgq = Double.valueOf(Double.longBitsToDouble(com_google_android_gms_internal_zzfhb.zzcuo()));
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
        if (this.zzjjx != null) {
            com_google_android_gms_internal_zzfhc.zzf(1, this.zzjjx.longValue());
        }
        if (this.name != null) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.name);
        }
        if (this.zzfzi != null) {
            com_google_android_gms_internal_zzfhc.zzn(3, this.zzfzi);
        }
        if (this.zzjiq != null) {
            com_google_android_gms_internal_zzfhc.zzf(4, this.zzjiq.longValue());
        }
        if (this.zzjgp != null) {
            com_google_android_gms_internal_zzfhc.zzc(5, this.zzjgp.floatValue());
        }
        if (this.zzjgq != null) {
            com_google_android_gms_internal_zzfhc.zza(6, this.zzjgq.doubleValue());
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzjjx != null) {
            zzo += zzfhc.zzc(1, this.zzjjx.longValue());
        }
        if (this.name != null) {
            zzo += zzfhc.zzo(2, this.name);
        }
        if (this.zzfzi != null) {
            zzo += zzfhc.zzo(3, this.zzfzi);
        }
        if (this.zzjiq != null) {
            zzo += zzfhc.zzc(4, this.zzjiq.longValue());
        }
        if (this.zzjgp != null) {
            this.zzjgp.floatValue();
            zzo += zzfhc.zzkw(5) + 4;
        }
        if (this.zzjgq == null) {
            return zzo;
        }
        this.zzjgq.doubleValue();
        return zzo + (zzfhc.zzkw(6) + 8);
    }
}
