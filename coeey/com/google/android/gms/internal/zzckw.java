package com.google.android.gms.internal;

import java.io.IOException;

public final class zzckw extends zzfhe<zzckw> {
    private static volatile zzckw[] zzjig;
    public String key;
    public String value;

    public zzckw() {
        this.key = null;
        this.value = null;
        this.zzpgy = null;
        this.zzpai = -1;
    }

    public static zzckw[] zzbat() {
        if (zzjig == null) {
            synchronized (zzfhi.zzphg) {
                if (zzjig == null) {
                    zzjig = new zzckw[0];
                }
            }
        }
        return zzjig;
    }

    public final boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzckw)) {
            return false;
        }
        zzckw com_google_android_gms_internal_zzckw = (zzckw) obj;
        if (this.key == null) {
            if (com_google_android_gms_internal_zzckw.key != null) {
                return false;
            }
        } else if (!this.key.equals(com_google_android_gms_internal_zzckw.key)) {
            return false;
        }
        if (this.value == null) {
            if (com_google_android_gms_internal_zzckw.value != null) {
                return false;
            }
        } else if (!this.value.equals(com_google_android_gms_internal_zzckw.value)) {
            return false;
        }
        return (this.zzpgy == null || this.zzpgy.isEmpty()) ? com_google_android_gms_internal_zzckw.zzpgy == null || com_google_android_gms_internal_zzckw.zzpgy.isEmpty() : this.zzpgy.equals(com_google_android_gms_internal_zzckw.zzpgy);
    }

    public final int hashCode() {
        int i = 0;
        int hashCode = ((this.value == null ? 0 : this.value.hashCode()) + (((this.key == null ? 0 : this.key.hashCode()) + ((getClass().getName().hashCode() + 527) * 31)) * 31)) * 31;
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
                    this.key = com_google_android_gms_internal_zzfhb.readString();
                    continue;
                case 18:
                    this.value = com_google_android_gms_internal_zzfhb.readString();
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
        if (this.key != null) {
            com_google_android_gms_internal_zzfhc.zzn(1, this.key);
        }
        if (this.value != null) {
            com_google_android_gms_internal_zzfhc.zzn(2, this.value);
        }
        super.zza(com_google_android_gms_internal_zzfhc);
    }

    protected final int zzo() {
        int zzo = super.zzo();
        if (this.key != null) {
            zzo += zzfhc.zzo(1, this.key);
        }
        return this.value != null ? zzo + zzfhc.zzo(2, this.value) : zzo;
    }
}
