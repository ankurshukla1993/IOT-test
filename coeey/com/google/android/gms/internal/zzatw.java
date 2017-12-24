package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzatw extends zzbej {
    public static final Creator<zzatw> CREATOR = new zzatx();
    private String accountType;
    private int zzdzm;

    zzatw(int i, String str) {
        this.zzdzm = 1;
        this.accountType = (String) zzbq.checkNotNull(str);
    }

    public zzatw(String str) {
        this(1, str);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.accountType, false);
        zzbem.zzai(parcel, zze);
    }
}
