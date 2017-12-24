package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbfp implements Creator<zzbfs> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzbfl com_google_android_gms_internal_zzbfl = null;
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        String str = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    com_google_android_gms_internal_zzbfl = (zzbfl) zzbek.zza(parcel, readInt, zzbfl.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbfs(i, str, com_google_android_gms_internal_zzbfl);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbfs[i];
    }
}
