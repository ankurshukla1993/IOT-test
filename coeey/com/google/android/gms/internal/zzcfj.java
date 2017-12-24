package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzcfj implements Creator<zzcfi> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        zzckk com_google_android_gms_internal_zzckk = null;
        long j = 0;
        boolean z = false;
        String str3 = null;
        zzcfx com_google_android_gms_internal_zzcfx = null;
        long j2 = 0;
        zzcfx com_google_android_gms_internal_zzcfx2 = null;
        long j3 = 0;
        zzcfx com_google_android_gms_internal_zzcfx3 = null;
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
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 4:
                    com_google_android_gms_internal_zzckk = (zzckk) zzbek.zza(parcel, readInt, zzckk.CREATOR);
                    break;
                case 5:
                    j = zzbek.zzi(parcel, readInt);
                    break;
                case 6:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 7:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 8:
                    com_google_android_gms_internal_zzcfx = (zzcfx) zzbek.zza(parcel, readInt, zzcfx.CREATOR);
                    break;
                case 9:
                    j2 = zzbek.zzi(parcel, readInt);
                    break;
                case 10:
                    com_google_android_gms_internal_zzcfx2 = (zzcfx) zzbek.zza(parcel, readInt, zzcfx.CREATOR);
                    break;
                case 11:
                    j3 = zzbek.zzi(parcel, readInt);
                    break;
                case 12:
                    com_google_android_gms_internal_zzcfx3 = (zzcfx) zzbek.zza(parcel, readInt, zzcfx.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzcfi(i, str, str2, com_google_android_gms_internal_zzckk, j, z, str3, com_google_android_gms_internal_zzcfx, j2, com_google_android_gms_internal_zzcfx2, j3, com_google_android_gms_internal_zzcfx3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzcfi[i];
    }
}
