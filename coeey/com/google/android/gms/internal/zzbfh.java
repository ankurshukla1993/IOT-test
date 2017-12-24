package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbfh extends zzbej {
    public static final Creator<zzbfh> CREATOR = new zzbfj();
    private int versionCode;
    final String zzfzi;
    final int zzfzj;

    zzbfh(int i, String str, int i2) {
        this.versionCode = i;
        this.zzfzi = str;
        this.zzfzj = i2;
    }

    zzbfh(String str, int i) {
        this.versionCode = 1;
        this.zzfzi = str;
        this.zzfzj = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.versionCode);
        zzbem.zza(parcel, 2, this.zzfzi, false);
        zzbem.zzc(parcel, 3, this.zzfzj);
        zzbem.zzai(parcel, zze);
    }
}
