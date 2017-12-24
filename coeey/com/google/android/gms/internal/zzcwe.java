package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;

public final class zzcwe extends zzbej implements Result {
    public static final Creator<zzcwe> CREATOR = new zzcwf();
    private int zzdzm;
    private int zzjze;
    private Intent zzjzf;

    public zzcwe() {
        this(0, null);
    }

    zzcwe(int i, int i2, Intent intent) {
        this.zzdzm = i;
        this.zzjze = i2;
        this.zzjzf = intent;
    }

    private zzcwe(int i, Intent intent) {
        this(2, 0, null);
    }

    public final Status getStatus() {
        return this.zzjze == 0 ? Status.zzfko : Status.zzfks;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zzc(parcel, 2, this.zzjze);
        zzbem.zza(parcel, 3, this.zzjzf, i, false);
        zzbem.zzai(parcel, zze);
    }
}
