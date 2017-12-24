package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class zzbt extends zzbej {
    public static final Creator<zzbt> CREATOR = new zzbu();
    private int zzdzm;
    private ConnectionResult zzflt;
    private boolean zzfoo;
    private IBinder zzfyt;
    private boolean zzfyu;

    zzbt(int i, IBinder iBinder, ConnectionResult connectionResult, boolean z, boolean z2) {
        this.zzdzm = i;
        this.zzfyt = iBinder;
        this.zzflt = connectionResult;
        this.zzfoo = z;
        this.zzfyu = z2;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbt)) {
            return false;
        }
        zzbt com_google_android_gms_common_internal_zzbt = (zzbt) obj;
        return this.zzflt.equals(com_google_android_gms_common_internal_zzbt.zzflt) && zzald().equals(com_google_android_gms_common_internal_zzbt.zzald());
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.zzfyt, false);
        zzbem.zza(parcel, 3, this.zzflt, i, false);
        zzbem.zza(parcel, 4, this.zzfoo);
        zzbem.zza(parcel, 5, this.zzfyu);
        zzbem.zzai(parcel, zze);
    }

    public final ConnectionResult zzagt() {
        return this.zzflt;
    }

    public final zzan zzald() {
        IBinder iBinder = this.zzfyt;
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IAccountAccessor");
        return queryLocalInterface instanceof zzan ? (zzan) queryLocalInterface : new zzap(iBinder);
    }

    public final boolean zzale() {
        return this.zzfoo;
    }

    public final boolean zzalf() {
        return this.zzfyu;
    }
}
