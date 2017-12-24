package com.google.android.gms.internal;

import java.io.IOException;

public final class zzckq extends zzfhe<zzckq> {
    private static volatile zzckq[] zzjhg;
    public zzckt zzjhh;
    public zzckr zzjhi;
    public Boolean zzjhj;
    public String zzjhk;

    public zzckq() {
        this.zzjhh = null;
        this.zzjhi = null;
        this.zzjhj = null;
        this.zzjhk = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzckq[] zzbaq() {
        if (zzjhg == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjhg == null) {
                    zzjhg = new zzckq[0];
                }
            }
        }
        return zzjhg;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzckq)) {
            return false;
        }
        zzckq com_google_android_gms_internal_zzckq = (zzckq) obj;
        if (this.zzjhh == null) {
            if (com_google_android_gms_internal_zzckq.zzjhh != null) {
                return false;
            }
        } else if (!this.zzjhh.equals(com_google_android_gms_internal_zzckq.zzjhh)) {
            return false;
        }
        if (this.zzjhi == null) {
            if (com_google_android_gms_internal_zzckq.zzjhi != null) {
                return false;
            }
        } else if (!this.zzjhi.equals(com_google_android_gms_internal_zzckq.zzjhi)) {
            return false;
        }
        if (this.zzjhj == null) {
            if (com_google_android_gms_internal_zzckq.zzjhj != null) {
                return false;
            }
        } else if (!this.zzjhj.equals(com_google_android_gms_internal_zzckq.zzjhj)) {
            return false;
        }
        if (this.zzjhk == null) {
            if (com_google_android_gms_internal_zzckq.zzjhk != null) {
                return false;
            }
        } else if (!this.zzjhk.equals(com_google_android_gms_internal_zzckq.zzjhk)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzckq.zzpgy == null || com_google_android_gms_internal_zzckq.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzckq.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = getClass().getName().hashCode() + 527;
        zzckt com_google_android_gms_internal_zzckt = this.zzjhh;
        hashCode = (com_google_android_gms_internal_zzckt == null ? 0 : com_google_android_gms_internal_zzckt.hashCode()) + (hashCode * 31);
        zzckr com_google_android_gms_internal_zzckr = this.zzjhi;
        hashCode = ((this.zzjhk == null ? 0 : this.zzjhk.hashCode()) + (((this.zzjhj == null ? 0 : this.zzjhj.hashCode()) + (((com_google_android_gms_internal_zzckr == null ? 0 : com_google_android_gms_internal_zzckr.hashCode()) + (hashCode * 31)) * 31)) * 31)) * 31;
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
                    if (this.zzjhh == null) {
                        this.zzjhh = new zzckt();
                    }
                    com_google_android_gms_internal_zzfhb.zza(this.zzjhh);
                    continue;
                case 18:
                    if (this.zzjhi == null) {
                        this.zzjhi = new zzckr();
                    }
                    com_google_android_gms_internal_zzfhb.zza(this.zzjhi);
                    continue;
                case 24:
                    this.zzjhj = Boolean.valueOf(com_google_android_gms_internal_zzfhb.zzcty());
                    continue;
                case 34:
                    this.zzjhk = com_google_android_gms_internal_zzfhb.readString();
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
        if (this.zzjhh != null) {
            com_google_android_gms_internal_zzfhc.zza(1, this.zzjhh);
        }
        if (this.zzjhi != null) {
            com_google_android_gms_internal_zzfhc.zza(2, this.zzjhi);
        }
        if (this.zzjhj != null) {
            com_google_android_gms_internal_zzfhc.zzl(3, this.zzjhj.booleanValue());
        }
        if (this.zzjhk != null) {
            com_google_android_gms_internal_zzfhc.zzn(4, this.zzjhk);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzjhh != null) {
            zzo += zzfhc.zzb(1, this.zzjhh);
        }
        if (this.zzjhi != null) {
            zzo += zzfhc.zzb(2, this.zzjhi);
        }
        if (this.zzjhj != null) {
            this.zzjhj.booleanValue();
            zzo += zzfhc.zzkw(3) + 1;
        }
        return this.zzjhk != null ? zzo + zzfhc.zzo(4, this.zzjhk) : zzo;
    }
}
