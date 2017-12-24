package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbt;

public final class zzcwp implements Creator<zzcwo> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        ConnectionResult connectionResult = null;
        int i = 0;
        zzbt com_google_android_gms_common_internal_zzbt = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    connectionResult = (ConnectionResult) zzbek.zza(parcel, readInt, ConnectionResult.CREATOR);
                    break;
                case 3:
                    com_google_android_gms_common_internal_zzbt = (zzbt) zzbek.zza(parcel, readInt, zzbt.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzcwo(i, connectionResult, com_google_android_gms_common_internal_zzbt);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcwo[i];
    }
}
