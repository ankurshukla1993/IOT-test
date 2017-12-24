package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzaty extends zzbej {
    public static final Creator<zzaty> CREATOR = new zzatz();
    private String accountType;
    private int zzdzm;
    private byte[] zzebo;

    zzaty(int i, String str, byte[] bArr) {
        this.zzdzm = 1;
        this.accountType = (String) zzbq.checkNotNull(str);
        this.zzebo = (byte[]) zzbq.checkNotNull(bArr);
    }

    public zzaty(String str, byte[] bArr) {
        this(1, str, bArr);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.accountType, false);
        zzbem.zza(parcel, 3, this.zzebo, false);
        zzbem.zzai(parcel, zze);
    }
}
