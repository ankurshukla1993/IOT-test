package com.google.android.gms.internal;

import java.io.IOException;

public final class zzcko extends zzfhe<zzcko> {
    private static volatile zzcko[] zzjgw;
    public Integer zzjgx;
    public zzcks[] zzjgy;
    public zzckp[] zzjgz;

    public zzcko() {
        this.zzjgx = null;
        this.zzjgy = zzcks.zzbar();
        this.zzjgz = zzckp.zzbap();
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzcko[] zzbao() {
        if (zzjgw == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjgw == null) {
                    zzjgw = new zzcko[0];
                }
            }
        }
        return zzjgw;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzcko)) {
            return false;
        }
        zzcko com_google_android_gms_internal_zzcko = (zzcko) obj;
        if (this.zzjgx == null) {
            if (com_google_android_gms_internal_zzcko.zzjgx != null) {
                return false;
            }
        } else if (!this.zzjgx.equals(com_google_android_gms_internal_zzcko.zzjgx)) {
            return false;
        }
        return !zzfhi.equals(this.zzjgy, com_google_android_gms_internal_zzcko.zzjgy) ? false : !zzfhi.equals(this.zzjgz, com_google_android_gms_internal_zzcko.zzjgz) ? false : (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzcko.zzpgy == null || com_google_android_gms_internal_zzcko.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzcko.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((this.zzjgx == null ? 0 : this.zzjgx.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31) + zzfhi.hashCode(this.zzjgy)) * 31) + zzfhi.hashCode(this.zzjgz)) * 31;
        if (!(this.zzpgy == null || this.zzpgy.isEmpty())) {
            i = this.zzpgy.hashCode();
        }
        return hashCode + i;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            int zzb;
            Object obj;
            switch (zzcts) {
                case 0:
                    break;
                case 8:
                    this.zzjgx = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 18:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 18);
                    zzcts = this.zzjgy == null ? 0 : this.zzjgy.length;
                    obj = new zzcks[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjgy, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzcks();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzcks();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjgy = obj;
                    continue;
                case 26:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 26);
                    zzcts = this.zzjgz == null ? 0 : this.zzjgz.length;
                    obj = new zzckp[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjgz, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzckp();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzckp();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjgz = obj;
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
        if (this.zzjgx != null) {
            com_google_android_gms_internal_zzfhc.zzaa(1, this.zzjgx.intValue());
        }
        if (this.zzjgy != null && this.zzjgy.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjgy) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    com_google_android_gms_internal_zzfhc.zza(2, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        if (this.zzjgz != null && this.zzjgz.length > 0) {
            while (i < this.zzjgz.length) {
                zzfhk com_google_android_gms_internal_zzfhk2 = this.zzjgz[i];
                if (com_google_android_gms_internal_zzfhk2 != null) {
                    com_google_android_gms_internal_zzfhc.zza(3, com_google_android_gms_internal_zzfhk2);
                }
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int i = 0;
        int zzo = super.zzo();
        if (this.zzjgx != null) {
            zzo += zzfhc.zzad(1, this.zzjgx.intValue());
        }
        if (this.zzjgy != null && this.zzjgy.length > 0) {
            int i2 = zzo;
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjgy) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    i2 += zzfhc.zzb(2, com_google_android_gms_internal_zzfhk);
                }
            }
            zzo = i2;
        }
        if (this.zzjgz != null && this.zzjgz.length > 0) {
            while (i < this.zzjgz.length) {
                zzfhk com_google_android_gms_internal_zzfhk2 = this.zzjgz[i];
                if (com_google_android_gms_internal_zzfhk2 != null) {
                    zzo += zzfhc.zzb(3, com_google_android_gms_internal_zzfhk2);
                }
                i++;
            }
        }
        return zzo;
    }
}
