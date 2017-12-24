package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbfo implements Creator<zzbfl> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzbfe com_google_android_gms_internal_zzbfe = null;
        int i = 0;
        int zzd = zzbek.zzd(parcel);
        String str = null;
        String str2 = null;
        boolean z = false;
        int i2 = 0;
        boolean z2 = false;
        int i3 = 0;
        int i4 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i4 = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    i3 = zzbek.zzg(parcel, readInt);
                    break;
                case 3:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 4:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 5:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 6:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 7:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 8:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 9:
                    com_google_android_gms_internal_zzbfe = (zzbfe) zzbek.zza(parcel, readInt, zzbfe.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbfl(i4, i3, z2, i2, z, str2, i, str, com_google_android_gms_internal_zzbfe);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbfl[i];
    }
}
