package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbfs extends zzbej {
    public static final Creator<zzbfs> CREATOR = new zzbfp();
    final String key;
    private int versionCode;
    final zzbfl<?, ?> zzfzy;

    zzbfs(int i, String str, zzbfl<?, ?> com_google_android_gms_internal_zzbfl___) {
        this.versionCode = i;
        this.key = str;
        this.zzfzy = com_google_android_gms_internal_zzbfl___;
    }

    zzbfs(String str, zzbfl<?, ?> com_google_android_gms_internal_zzbfl___) {
        this.versionCode = 1;
        this.key = str;
        this.zzfzy = com_google_android_gms_internal_zzbfl___;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.versionCode);
        zzbem.zza(parcel, 2, this.key, false);
        zzbem.zza(parcel, 3, this.zzfzy, i, false);
        zzbem.zzai(parcel, zze);
    }
}
