package com.google.android.gms.internal;

import java.io.IOException;

public final class zzckx extends zzfhe<zzckx> {
    private static volatile zzckx[] zzjih;
    public Integer zzjgx;
    public zzclc zzjii;
    public zzclc zzjij;
    public Boolean zzjik;

    public zzckx() {
        this.zzjgx = null;
        this.zzjii = null;
        this.zzjij = null;
        this.zzjik = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzckx[] zzbau() {
        if (zzjih == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjih == null) {
                    zzjih = new zzckx[0];
                }
            }
        }
        return zzjih;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzckx)) {
            return false;
        }
        zzckx com_google_android_gms_internal_zzckx = (zzckx) obj;
        if (this.zzjgx == null) {
            if (com_google_android_gms_internal_zzckx.zzjgx != null) {
                return false;
            }
        } else if (!this.zzjgx.equals(com_google_android_gms_internal_zzckx.zzjgx)) {
            return false;
        }
        if (this.zzjii == null) {
            if (com_google_android_gms_internal_zzckx.zzjii != null) {
                return false;
            }
        } else if (!this.zzjii.equals(com_google_android_gms_internal_zzckx.zzjii)) {
            return false;
        }
        if (this.zzjij == null) {
            if (com_google_android_gms_internal_zzckx.zzjij != null) {
                return false;
            }
        } else if (!this.zzjij.equals(com_google_android_gms_internal_zzckx.zzjij)) {
            return false;
        }
        if (this.zzjik == null) {
            if (com_google_android_gms_internal_zzckx.zzjik != null) {
                return false;
            }
        } else if (!this.zzjik.equals(com_google_android_gms_internal_zzckx.zzjik)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzckx.zzpgy == null || com_google_android_gms_internal_zzckx.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzckx.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = (this.zzjgx == null ? 0 : this.zzjgx.hashCode()) + ((getClass().getName().hashCode() + 527) * 31);
        zzclc com_google_android_gms_internal_zzclc = this.zzjii;
        hashCode = (com_google_android_gms_internal_zzclc == null ? 0 : com_google_android_gms_internal_zzclc.hashCode()) + (hashCode * 31);
        com_google_android_gms_internal_zzclc = this.zzjij;
        hashCode = ((this.zzjik == null ? 0 : this.zzjik.hashCode()) + (((com_google_android_gms_internal_zzclc == null ? 0 : com_google_android_gms_internal_zzclc.hashCode()) + (hashCode * 31)) * 31)) * 31;
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
                    this.zzjgx = Integer.valueOf(com_google_android_gms_internal_zzfhb.zzcuh());
                    continue;
                case 18:
                    if (this.zzjii == null) {
                        this.zzjii = new zzclc();
                    }
                    com_google_android_gms_internal_zzfhb.zza(this.zzjii);
                    continue;
                case 26:
                    if (this.zzjij == null) {
                        this.zzjij = new zzclc();
                    }
                    com_google_android_gms_internal_zzfhb.zza(this.zzjij);
                    continue;
                case 32:
                    this.zzjik = Boolean.valueOf(com_google_android_gms_internal_zzfhb.zzcty());
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
        if (this.zzjgx != null) {
            com_google_android_gms_internal_zzfhc.zzaa(1, this.zzjgx.intValue());
        }
        if (this.zzjii != null) {
            com_google_android_gms_internal_zzfhc.zza(2, this.zzjii);
        }
        if (this.zzjij != null) {
            com_google_android_gms_internal_zzfhc.zza(3, this.zzjij);
        }
        if (this.zzjik != null) {
            com_google_android_gms_internal_zzfhc.zzl(4, this.zzjik.booleanValue());
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.zzjgx != null) {
            zzo += zzfhc.zzad(1, this.zzjgx.intValue());
        }
        if (this.zzjii != null) {
            zzo += zzfhc.zzb(2, this.zzjii);
        }
        if (this.zzjij != null) {
            zzo += zzfhc.zzb(3, this.zzjij);
        }
        if (this.zzjik == null) {
            return zzo;
        }
        this.zzjik.booleanValue();
        return zzo + (zzfhc.zzkw(4) + 1);
    }
}
