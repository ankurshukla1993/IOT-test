package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdxr extends zzbej {
    public static final Creator<zzdxr> CREATOR = new zzdxs();
    private String zzlwt;
    private String zzmeb;

    public zzdxr(String str, String str2) {
        this.zzmeb = str;
        this.zzlwt = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzmeb, false);
        zzbem.zza(parcel, 3, this.zzlwt, false);
        zzbem.zzai(parcel, zze);
    }
}
