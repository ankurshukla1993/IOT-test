package com.google.android.gms.common;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class zzc extends zzbej {
    public static final Creator<zzc> CREATOR = new zzd();
    private String name;
    private int version;

    public zzc(String str, int i) {
        this.name = str;
        this.version = i;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.name, false);
        zzbem.zzc(parcel, 2, this.version);
        zzbem.zzai(parcel, zze);
    }
}
