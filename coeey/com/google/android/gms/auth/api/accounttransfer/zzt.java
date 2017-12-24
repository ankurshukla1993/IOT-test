package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;
import com.google.android.gms.internal.zzbel;
import java.util.HashSet;
import java.util.Set;

public final class zzt implements Creator<zzs> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbek.zzd(parcel);
        Set hashSet = new HashSet();
        int i = 0;
        String str2 = null;
        zzu com_google_android_gms_auth_api_accounttransfer_zzu = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    zzu com_google_android_gms_auth_api_accounttransfer_zzu2 = (zzu) zzbek.zza(parcel, readInt, zzu.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    com_google_android_gms_auth_api_accounttransfer_zzu = com_google_android_gms_auth_api_accounttransfer_zzu2;
                    break;
                case 3:
                    str2 = zzbek.zzq(parcel, readInt);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    str = zzbek.zzq(parcel, readInt);
                    hashSet.add(Integer.valueOf(4));
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == zzd) {
            return new zzs(hashSet, i, com_google_android_gms_auth_api_accounttransfer_zzu, str2, str);
        }
        throw new zzbel("Overread allowed size end=" + zzd, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzs[i];
    }
}
