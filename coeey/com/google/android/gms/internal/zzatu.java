package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzatu extends zzbej {
    public static final Creator<zzatu> CREATOR = new zzatv();
    private String accountType;
    private int zzdzm;
    private int zzebv;

    zzatu(int i, String str, int i2) {
        this.zzdzm = 1;
        this.accountType = (String) zzbq.checkNotNull(str);
        this.zzebv = i2;
    }

    public zzatu(String str, int i) {
        this(1, str, i);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.accountType, false);
        zzbem.zzc(parcel, 3, this.zzebv);
        zzbem.zzai(parcel, zze);
    }
}
