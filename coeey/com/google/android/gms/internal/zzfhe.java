package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzfhe<M extends zzfhe<M>> extends zzfhk {
    protected zzfhg zzpgy;

    public /* synthetic */ Object clone() throws CloneNotSupportedException {
        return zzcxe();
    }

    public final <T> T zza(zzfhf<M, T> com_google_android_gms_internal_zzfhf_M__T) {
        if (this.zzpgy == null) {
            return null;
        }
        zzfhh zzlz = this.zzpgy.zzlz(com_google_android_gms_internal_zzfhf_M__T.tag >>> 3);
        return zzlz != null ? zzlz.zzb(com_google_android_gms_internal_zzfhf_M__T) : null;
    }

    public void zza(zzfhc com_google_android_gms_internal_zzfhc) throws IOException {
        if (this.zzpgy != null) {
            for (int i = 0; i < this.zzpgy.size(); i++) {
                this.zzpgy.zzma(i).zza(com_google_android_gms_internal_zzfhc);
            }
        }
    }

    protected final boolean zza(zzfhb com_google_android_gms_internal_zzfhb, int i) throws IOException {
        int position = com_google_android_gms_internal_zzfhb.getPosition();
        if (!com_google_android_gms_internal_zzfhb.zzkg(i)) {
            return false;
        }
        int i2 = i >>> 3;
        zzfhm com_google_android_gms_internal_zzfhm = new zzfhm(i, com_google_android_gms_internal_zzfhb.zzal(position, com_google_android_gms_internal_zzfhb.getPosition() - position));
        zzfhh com_google_android_gms_internal_zzfhh = null;
        if (this.zzpgy == null) {
            this.zzpgy = new zzfhg();
        } else {
            com_google_android_gms_internal_zzfhh = this.zzpgy.zzlz(i2);
        }
        if (com_google_android_gms_internal_zzfhh == null) {
            com_google_android_gms_internal_zzfhh = new zzfhh();
            this.zzpgy.zza(i2, com_google_android_gms_internal_zzfhh);
        }
        com_google_android_gms_internal_zzfhh.zza(com_google_android_gms_internal_zzfhm);
        return true;
    }

    public M zzcxe() throws CloneNotSupportedException {
        zzfhe com_google_android_gms_internal_zzfhe = (zzfhe) super.zzcxf();
        zzfhi.zza(this, com_google_android_gms_internal_zzfhe);
        return com_google_android_gms_internal_zzfhe;
    }

    public /* synthetic */ zzfhk zzcxf() throws CloneNotSupportedException {
        return (zzfhe) clone();
    }

    protected int zzo() {
        int i = 0;
        if (this.zzpgy == null) {
            return 0;
        }
        int i2 = 0;
        while (i < this.zzpgy.size()) {
            i2 += this.zzpgy.zzma(i).zzo();
            i++;
        }
        return i2;
    }
}
