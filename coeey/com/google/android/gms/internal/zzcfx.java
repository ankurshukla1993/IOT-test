package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzcfx extends zzbej {
    public static final Creator<zzcfx> CREATOR = new zzcfy();
    public final String name;
    public final String zzivk;
    public final zzcfu zziwy;
    public final long zziwz;

    zzcfx(zzcfx com_google_android_gms_internal_zzcfx, long j) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfx);
        this.name = com_google_android_gms_internal_zzcfx.name;
        this.zziwy = com_google_android_gms_internal_zzcfx.zziwy;
        this.zzivk = com_google_android_gms_internal_zzcfx.zzivk;
        this.zziwz = j;
    }

    public zzcfx(String str, zzcfu com_google_android_gms_internal_zzcfu, String str2, long j) {
        this.name = str;
        this.zziwy = com_google_android_gms_internal_zzcfu;
        this.zzivk = str2;
        this.zziwz = j;
    }

    public final String toString() {
        String str = this.zzivk;
        String str2 = this.name;
        String valueOf = String.valueOf(this.zziwy);
        return new StringBuilder(((String.valueOf(str).length() + 21) + String.valueOf(str2).length()) + String.valueOf(valueOf).length()).append("origin=").append(str).append(",name=").append(str2).append(",params=").append(valueOf).toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.name, false);
        zzbem.zza(parcel, 3, this.zziwy, i, false);
        zzbem.zza(parcel, 4, this.zzivk, false);
        zzbem.zza(parcel, 5, this.zziwz);
        zzbem.zzai(parcel, zze);
    }
}
