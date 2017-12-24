package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfil extends zzfhe<zzfil> {
    private static volatile zzfil[] zzplc;
    public String zzpld;

    public zzfil() {
        this.zzpld = "";
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzfil[] zzcyc() {
        if (zzplc == null) {
            synchronized (zzfhi.zzphg) {
                if (zzplc == null) {
                    zzplc = new zzfil[0];
                }
            }
        }
        return zzplc;
    }

    public final /* synthetic */ zzfhk zza(zzfhb com_google_android_gms_internal_zzfhb) throws IOException {
        while (true) {
            int zzcts = com_google_android_gms_internal_zzfhb.zzcts();
            switch (zzcts) {
                case 0:
                    break;
                case 10:
                    this.zzpld = com_google_android_gms_internal_zzfhb.readString();
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
        if (!(this.zzpld == null || this.zzpld.equals(""))) {
            com_google_android_gms_internal_zzfhc.zzn(1, this.zzpld);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        return (this.zzpld == null || this.zzpld.equals("")) ? zzo : zzo + zzfhc.zzo(1, this.zzpld);
    }
}
