package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.Arrays;

public final class zzbdc extends zzbej {
    public static final Creator<zzbdc> CREATOR = new zzbdd();
    private boolean zzfhd;
    private long zzfhe;

    public zzbdc(boolean z, long j) {
        this.zzfhd = z;
        this.zzfhe = j;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbdc)) {
            return false;
        }
        zzbdc com_google_android_gms_internal_zzbdc = (zzbdc) obj;
        return this.zzfhd == com_google_android_gms_internal_zzbdc.zzfhd && this.zzfhe == com_google_android_gms_internal_zzbdc.zzfhe;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Boolean.valueOf(this.zzfhd), Long.valueOf(this.zzfhe)});
    }

    public final String toString() {
        return "CollectForDebugParcelable[skipPersistentStorage: " + this.zzfhd + ",collectForDebugExpiryTimeMillis: " + this.zzfhe + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzfhd);
        zzbem.zza(parcel, 2, this.zzfhe);
        zzbem.zzai(parcel, zze);
    }
}
