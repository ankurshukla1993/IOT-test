package com.google.android.gms.internal;

import java.io.IOException;

public final class zzckp extends zzfhe<zzckp> {
    private static volatile zzckp[] zzjha;
    public Integer zzjhb;
    public String zzjhc;
    public zzckq[] zzjhd;
    private Boolean zzjhe;
    public zzckr zzjhf;

    public zzckp() {
        this.zzjhb = null;
        this.zzjhc = null;
        this.zzjhd = zzckq.zzbaq();
        this.zzjhe = null;
        this.zzjhf = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzckp[] zzbap() {
        if (zzjha == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjha == null) {
                    zzjha = new zzckp[0];
                }
            }
        }
        return zzjha;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzckp)) {
            return false;
        }
        zzckp com_google_android_gms_internal_zzckp = (zzckp) obj;
        if (this.zzjhb == null) {
            if (com_google_android_gms_internal_zzckp.zzjhb != null) {
                return false;
            }
        } else if (!this.zzjhb.equals(com_google_android_gms_internal_zzckp.zzjhb)) {
            return false;
        }
        if (this.zzjhc == null) {
            if (com_google_android_gms_internal_zzckp.zzjhc != null) {
                return false;
            }
        } else if (!this.zzjhc.equals(com_google_android_gms_internal_zzckp.zzjhc)) {
            return false;
        }
        if (!zzfhi.equals(this.zzjhd, com_google_android_gms_internal_zzckp.zzjhd)) {
            return false;
        }
        if (this.zzjhe == null) {
            if (com_google_android_gms_internal_zzckp.zzjhe != null) {
                return false;
            }
        } else if (!this.zzjhe.equals(com_google_android_gms_internal_zzckp.zzjhe)) {
            return false;
        }
        if (this.zzjhf == null) {
            if (com_google_android_gms_internal_zzckp.zzjhf != null) {
                return false;
            }
        } else if (!this.zzjhf.equals(com_google_android_gms_internal_zzckp.zzjhf)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzckp.zzpgy == null || com_google_android_gms_internal_zzckp.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzckp.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzjhe == null ? 0 : this.zzjhe.hashCode()) + (((((this.zzjhc == null ? 0 : this.zzjhc.hashCode()) + (((this.zzjhb == null ? 0 : this.zzjhb.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31) + zzfhi.hashCode(this.zzjhd)) * 31);
        zzckr com_google_android_gms_internal_zzckr = this.zzjhf;
        hashCode = ((com_google_android_gms_internal_zzckr == null ? 0 : com_google_android_gms_internal_zzckr.hashCode()) + (hashCode * 31)) * 31;
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
                    this.zzjhc = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 26:
                    int zzb = zzfhn.zzb(com_google_android_gms_internal_zzfhb, 26);
                    zzcts = this.zzjhd == null ? 0 : this.zzjhd.length;
                    Object obj = new zzckq[(zzb + zzcts)];
                    if (zzcts != 0) {
                        System.arraycopy(this.zzjhd, 0, obj, 0, zzcts);
                    }
                    while (zzcts < obj.length - 1) {
                        obj[zzcts] = new zzckq();
                        com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                        com_google_android_gms_internal_zzfhb.zzcts();
                        zzcts++;
                    }
                    obj[zzcts] = new zzckq();
                    com_google_android_gms_internal_zzfhb.zza(obj[zzcts]);
                    this.zzjhd = obj;
                    continue;
                case 32:
                    this.zzjhe = Boolean.valueOf(com_google_android_gms_internal_zzfhb.zzcty());
                    continue;
                case 42:
                    if (this.zzjhf == null) {
                        this.zzjhf = new zzckr();
                    }
                    com_google_android_gms_internal_zzfhb.zza(this.zzjhf);
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
        if (this.zzjhc != null) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.zzjhc);
        }
        if (this.zzjhd != null && this.zzjhd.length > 0) {
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjhd) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    com_google_android_gms_internal_zzfhc.zza(3, com_google_android_gms_internal_zzfhk);
                }
            }
        }
        if (this.zzjhe != null) {
            com_google_android_gms_internal_zzfhc.zzl(4, this.zzjhe.booleanValue());
        }
        if (this.zzjhf != null) {
            com_google_android_gms_internal_zzfhc.zza(5, this.zzjhf);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzjhb != null) {
            zzo += zzfhc.zzad(1, this.zzjhb.intValue());
        }
        if (this.zzjhc != null) {
            zzo += zzfhc.zzo(2, this.zzjhc);
        }
        if (this.zzjhd != null && this.zzjhd.length > 0) {
            int i = zzo;
            for (zzfhk com_google_android_gms_internal_zzfhk : this.zzjhd) {
                if (com_google_android_gms_internal_zzfhk != null) {
                    i += zzfhc.zzb(3, com_google_android_gms_internal_zzfhk);
                }
            }
            zzo = i;
        }
        if (this.zzjhe != null) {
            this.zzjhe.booleanValue();
            zzo += zzfhc.zzkw(4) + 1;
        }
        return this.zzjhf != null ? zzo + zzfhc.zzb(5, this.zzjhf) : zzo;
    }
}
