package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzato extends zzbej {
    public static final Creator<zzato> CREATOR = new zzatp();
    private String accountType;
    private int zzdzm;

    zzato(int i, String str) {
        this.zzdzm = 1;
        this.accountType = (String) zzbq.checkNotNull(str);
    }

    public zzato(String str) {
        this(1, str);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.accountType, false);
        zzbem.zzai(parcel, zze);
    }
}
