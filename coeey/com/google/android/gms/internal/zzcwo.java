package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbt;

public final class zzcwo extends zzbej {
    public static final Creator<zzcwo> CREATOR = new zzcwp();
    private int zzdzm;
    private final ConnectionResult zzflt;
    private final zzbt zzjzj;

    public zzcwo(int i) {
        this(new ConnectionResult(8, null), null);
    }

    zzcwo(int i, ConnectionResult connectionResult, zzbt com_google_android_gms_common_internal_zzbt) {
        this.zzdzm = i;
        this.zzflt = connectionResult;
        this.zzjzj = com_google_android_gms_common_internal_zzbt;
    }

    private zzcwo(ConnectionResult connectionResult, zzbt com_google_android_gms_common_internal_zzbt) {
        this(1, connectionResult, null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.zzflt, i, false);
        zzbem.zza(parcel, 3, this.zzjzj, i, false);
        zzbem.zzai(parcel, zze);
    }

    public final ConnectionResult zzagt() {
        return this.zzflt;
    }

    public final zzbt zzbcw() {
        return this.zzjzj;
    }
}
