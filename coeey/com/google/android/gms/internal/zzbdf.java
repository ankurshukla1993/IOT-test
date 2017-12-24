package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbdf implements Creator<zzbde> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzcsv[] com_google_android_gms_internal_zzcsvArr = null;
        int zzd = zzbek.zzd(parcel);
        boolean z = true;
        byte[][] bArr = null;
        int[] iArr = null;
        String[] strArr = null;
        int[] iArr2 = null;
        byte[] bArr2 = null;
        zzbdt com_google_android_gms_internal_zzbdt = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 2:
                    com_google_android_gms_internal_zzbdt = (zzbdt) zzbek.zza(parcel, readInt, zzbdt.CREATOR);
                    break;
                case 3:
                    bArr2 = zzbek.zzt(parcel, readInt);
                    break;
                case 4:
                    iArr2 = zzbek.zzw(parcel, readInt);
                    break;
                case 5:
                    strArr = zzbek.zzaa(parcel, readInt);
                    break;
                case 6:
                    iArr = zzbek.zzw(parcel, readInt);
                    break;
                case 7:
                    bArr = zzbek.zzu(parcel, readInt);
                    break;
                case 8:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 9:
                    com_google_android_gms_internal_zzcsvArr = (zzcsv[]) zzbek.zzb(parcel, readInt, zzcsv.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new zzbde(com_google_android_gms_internal_zzbdt, bArr2, iArr2, strArr, iArr, bArr, z, com_google_android_gms_internal_zzcsvArr);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzbde[i];
    }
}
