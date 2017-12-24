package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcfy implements Creator<zzcfx> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbek.zzd(parcel);
        long j = 0;
        zzcfu com_google_android_gms_internal_zzcfu = null;
        String str2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    com_google_android_gms_internal_zzcfu = (zzcfu) zzbek.zza(parcel, readInt, zzcfu.CREATOR);
                    break;
                case 4:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 5:
                    j = zzbek.zzi(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzcfx(str2, com_google_android_gms_internal_zzcfu, str, j);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcfx[i];
    }
}
