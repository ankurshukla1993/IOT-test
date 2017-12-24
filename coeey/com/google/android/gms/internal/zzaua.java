package com.google.android.gms.internal;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzaua extends zzbej {
    public static final Creator<zzaua> CREATOR = new zzaub();
    private String accountType;
    private int zzdzm;
    private PendingIntent zzebw;

    zzaua(int i, String str, PendingIntent pendingIntent) {
        this.zzdzm = 1;
        this.accountType = (String) zzbq.checkNotNull(str);
        this.zzebw = (PendingIntent) zzbq.checkNotNull(pendingIntent);
    }

    public zzaua(String str, PendingIntent pendingIntent) {
        this(1, str, pendingIntent);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.accountType, false);
        zzbem.zza(parcel, 3, this.zzebw, i, false);
        zzbem.zzai(parcel, zze);
    }
}
