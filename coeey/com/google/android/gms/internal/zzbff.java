package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbff implements Creator<zzbfe> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        zzbfg com_google_android_gms_internal_zzbfg = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    com_google_android_gms_internal_zzbfg = (zzbfg) zzbek.zza(parcel, readInt, zzbfg.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbfe(i, com_google_android_gms_internal_zzbfg);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbfe[i];
    }
}
