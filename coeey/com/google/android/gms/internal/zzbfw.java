package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbfw implements Creator<zzbfv> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzbfq com_google_android_gms_internal_zzbfq = null;
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    parcel2 = zzbek.zzad(parcel, readInt);
                    break;
                case 3:
                    com_google_android_gms_internal_zzbfq = (zzbfq) zzbek.zza(parcel, readInt, zzbfq.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbfv(i, parcel2, com_google_android_gms_internal_zzbfq);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbfv[i];
    }
}
