package com.google.android.gms.common.api.internal;

public final class zzcn<L> {
    private final L zzfrq;
    private final String zzfrt;

    zzcn(L l, String str) {
        this.zzfrq = l;
        this.zzfrt = str;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzcn)) {
            return false;
        }
        zzcn com_google_android_gms_common_api_internal_zzcn = (zzcn) obj;
        return this.zzfrq == com_google_android_gms_common_api_internal_zzcn.zzfrq && this.zzfrt.equals(com_google_android_gms_common_api_internal_zzcn.zzfrt);
    }

    public final int hashCode() {
        return (System.identityHashCode(this.zzfrq) * 31) + this.zzfrt.hashCode();
    }
}
