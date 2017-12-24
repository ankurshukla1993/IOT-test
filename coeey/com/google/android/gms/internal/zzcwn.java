package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbr;

public final class zzcwn implements Creator<zzcwm> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        zzbr com_google_android_gms_common_internal_zzbr = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    com_google_android_gms_common_internal_zzbr = (zzbr) zzbek.zza(parcel, readInt, zzbr.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzcwm(i, com_google_android_gms_common_internal_zzbr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcwm[i];
    }
}
