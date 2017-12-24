package com.google.android.gms.internal;

import java.io.IOException;

public final class zzckz extends zzfhe<zzckz> {
    private static volatile zzckz[] zzjip;
    public String name;
    public String zzfzi;
    private Float zzjgp;
    public Double zzjgq;
    public Long zzjiq;

    public zzckz() {
        this.name = null;
        this.zzfzi = null;
        this.zzjiq = null;
        this.zzjgp = null;
        this.zzjgq = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzckz[] zzbaw() {
        if (zzjip == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjip == null) {
                    zzjip = new zzckz[0];
                }
            }
        }
        return zzjip;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzckz)) {
            return false;
        }
        zzckz com_google_android_gms_internal_zzckz = (zzckz) obj;
        if (this.name == null) {
            if (com_google_android_gms_internal_zzckz.name != null) {
                return false;
            }
        } else if (!this.name.equals(com_google_android_gms_internal_zzckz.name)) {
            return false;
        }
        if (this.zzfzi == null) {
            if (com_google_android_gms_internal_zzckz.zzfzi != null) {
                return false;
            }
        } else if (!this.zzfzi.equals(com_google_android_gms_internal_zzckz.zzfzi)) {
            return false;
        }
        if (this.zzjiq == null) {
            if (com_google_android_gms_internal_zzckz.zzjiq != null) {
                return false;
            }
        } else if (!this.zzjiq.equals(com_google_android_gms_internal_zzckz.zzjiq)) {
            return false;
        }
        if (this.zzjgp == null) {
            if (com_google_android_gms_internal_zzckz.zzjgp != null) {
                return false;
            }
        } else if (!this.zzjgp.equals(com_google_android_gms_internal_zzckz.zzjgp)) {
            return false;
        }
        if (this.zzjgq == null) {
            if (com_google_android_gms_internal_zzckz.zzjgq != null) {
                return false;
            }
        } else if (!this.zzjgq.equals(com_google_android_gms_internal_zzckz.zzjgq)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzckz.zzpgy == null || com_google_android_gms_internal_zzckz.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzckz.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.zzjgq == null ? 0 : this.zzjgq.hashCode()) + (((this.zzjgp == null ? 0 : this.zzjgp.hashCode()) + (((this.zzjiq == null ? 0 : this.zzjiq.hashCode()) + (((this.zzfzi == null ? 0 : this.zzfzi.hashCode()) + (((this.name == null ? 0 : this.name.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31)) * 31)) * 31;
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
                case 18:
                    this.zzfzi = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 24:
                    this.zzjiq = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 37:
                    this.zzjgp = Float.valueOf(Float.intBitsToFloat(com_google_android_gms_internal_zzfhb.zzcun()));
                    continue;
                case 41:
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
        if (this.name != null) {
            com_google_android_gms_internal_zzfhc.zzn(1, this.name);
        }
        if (this.zzfzi != null) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.zzfzi);
        }
        if (this.zzjiq != null) {
            com_google_android_gms_internal_zzfhc.zzf(3, this.zzjiq.longValue());
        }
        if (this.zzjgp != null) {
            com_google_android_gms_internal_zzfhc.zzc(4, this.zzjgp.floatValue());
        }
        if (this.zzjgq != null) {
            com_google_android_gms_internal_zzfhc.zza(5, this.zzjgq.doubleValue());
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.name != null) {
            zzo += zzfhc.zzo(1, this.name);
        }
        if (this.zzfzi != null) {
            zzo += zzfhc.zzo(2, this.zzfzi);
        }
        if (this.zzjiq != null) {
            zzo += zzfhc.zzc(3, this.zzjiq.longValue());
        }
        if (this.zzjgp != null) {
            this.zzjgp.floatValue();
            zzo += zzfhc.zzkw(4) + 4;
        }
        if (this.zzjgq == null) {
            return zzo;
        }
        this.zzjgq.doubleValue();
        return zzo + (zzfhc.zzkw(5) + 8);
    }
}
