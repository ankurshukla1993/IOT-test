package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdwo extends zzbej {
    public static final Creator<zzdwo> CREATOR = new zzdwp();
    private final String zzijn;
    private final long zzmcj;
    private final boolean zzmck;
    private final String zzmcl;

    public zzdwo(String str, long j, boolean z, String str2) {
        this.zzijn = str;
        this.zzmcj = j;
        this.zzmck = z;
        this.zzmcl = str2;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzijn, false);
        zzbem.zza(parcel, 2, this.zzmcj);
        zzbem.zza(parcel, 3, this.zzmck);
        zzbem.zza(parcel, 4, this.zzmcl, false);
        zzbem.zzai(parcel, zze);
    }
}
