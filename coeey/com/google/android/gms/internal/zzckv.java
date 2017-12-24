package com.google.android.gms.internal;

import java.io.IOException;

public final class zzckv extends zzfhe<zzckv> {
    public String zziux;
    public Long zzjib;
    private Integer zzjic;
    public zzckw[] zzjid;
    public zzcku[] zzjie;
    public zzcko[] zzjif;

    public zzckv() {
        this.zzjib = null;
        this.zziux = null;
        this.zzjic = null;
        this.zzjid = zzckw.zzbat();
        this.zzjie = zzcku.zzbas();
        this.zzjif = zzcko.zzbao();
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzckv)) {
            return false;
        }
        zzckv com_google_android_gms_internal_zzckv = (zzckv) obj;
        if (this.zzjib == null) {
            if (com_google_android_gms_internal_zzckv.zzjib != null) {
                return false;
            }
        } else if (!this.zzjib.equals(com_google_android_gms_internal_zzckv.zzjib)) {
            return false;
        }
        if (this.zziux == null) {
            if (com_google_android_gms_internal_zzckv.zziux != null) {
                return false;
            }
        } else if (!this.zziux.equals(com_google_android_gms_internal_zzckv.zziux)) {
            return false;
        }
        if (this.zzjic == null) {
            if (com_google_android_gms_internal_zzckv.zzjic != null) {
                return false;
            }
        } else if (!this.zzjic.equals(com_google_android_gms_internal_zzckv.zzjic)) {
            return false;
        }
        return !zzfhi.equals(this.zzjid, com_google_android_gms_internal_zzckv.zzjid) ? false : !zzfhi.equals(this.zzjie, com_google_android_gms_internal_zzckv.zzjie) ? false : !zzfhi.equals(this.zzjif, com_google_android_gms_internal_zzckv.zzjif) ? false : (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzckv.zzpgy == null || com_google_android_gms_internal_zzckv.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzckv.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((((((((this.zzjic == null ? 0 : this.zzjic.hashCode()) + (((this.zziux == null ? 0 : this.zziux.hashCode()) + (((this.zzjib == null ? 0 : this.zzjib.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31)) * 31) + zzfhi.hashCode(this.zzjid)) * 31) + zzfhi.hashCode(this.zzjie)) * 31) + zzfhi.hashCode(this.zzjif)) * 31;
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
                    this.zzjib = Long.valueOf(com_google_android_gms_internal_zzfhb.zzcum());
                    continue;
                case 18:
                    this.zziux = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 24:
                    this.zzjic = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 34:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 34);
                    zzcts = this.zzjid == null ? 0 : this.zzjid.length;
                    obj = new zzckw[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjid, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzckw();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzckw();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjid = obj;
                    continue;
                case 42:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 42);
                    zzcts = this.zzjie == null ? 0 : this.zzjie.length;
                    obj = new zzcku[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjie, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzcku();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzcku();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjie = obj;
                    continue;
                case 50:
                    zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 50);
                    zzcts = this.zzjif == null ? 0 : this.zzjif.length;
                    obj = new zzcko[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjif, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzcko();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzcko();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjif = obj;
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
        if (this.zzjib != null) {
            com_google_android_gms_internal_zzfhc.zzf(1, this.zzjib.longValue());
        }
        if (this.zziux != null) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.zziux);
        }
        if (this.zzjic != null) {
            com_google_android_gms_internal_zzfhc.zzaa(3, this.zzjic.intValue());
        }
        if (this.zzjid != null && this.zzjid.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjid) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    com_google_android_gms_internal_zzfhc.zza(4, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        if (this.zzjie != null && this.zzjie.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk2 : this.zzjie) {
                if (com_google_android_gms_internal_zzfhk2 != null) {
                    com_google_android_gms_internal_zzfhc.zza(5, com_google_android_gms_internal_zzfhk2);
                }
            }
        }
        if (this.zzjif != null && this.zzjif.length > 0) {
            while (i < this.zzjif.length) {
                zzfhk com_google_android_gms_internal_zzfhk3 = this.zzjif[i];
                if (com_google_android_gms_internal_zzfhk3 != null) {
                    com_google_android_gms_internal_zzfhc.zza(6, com_google_android_gms_internal_zzfhk3);
                }
                i++;
            }
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int i;
        int i2 = 0;
        int zzo = super.zzo();
        if (this.zzjib != null) {
            zzo += zzfhc.zzc(1, this.zzjib.longValue());
        }
        if (this.zziux != null) {
            zzo += zzfhc.zzo(2, this.zziux);
        }
        if (this.zzjic != null) {
            zzo += zzfhc.zzad(3, this.zzjic.intValue());
        }
        if (this.zzjid != null && this.zzjid.length > 0) {
            i = zzo;
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjid) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    i += zzfhc.zzb(4, com_google_android_gms_internal_zzfhk);
                }
            }
            zzo = i;
        }
        if (this.zzjie != null && this.zzjie.length > 0) {
            i = zzo;
            for (zzfhk com_google_android_gms_internal_zzfhk2 : this.zzjie) {
                if (com_google_android_gms_internal_zzfhk2 != null) {
                    i += zzfhc.zzb(5, com_google_android_gms_internal_zzfhk2);
                }
            }
            zzo = i;
        }
        if (this.zzjif != null && this.zzjif.length > 0) {
            while (i2 < this.zzjif.length) {
                zzfhk com_google_android_gms_internal_zzfhk3 = this.zzjif[i2];
                if (com_google_android_gms_internal_zzfhk3 != null) {
                    zzo += zzfhc.zzb(6, com_google_android_gms_internal_zzfhk3);
                }
                i2++;
            }
        }
        return zzo;
    }
}
