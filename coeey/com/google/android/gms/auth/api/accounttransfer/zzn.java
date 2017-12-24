package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;
import com.google.android.gms.internal.zzbel;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public final class zzn implements Creator<zzm> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        zzp com_google_android_gms_auth_api_accounttransfer_zzp = null;
        int i = 0;
        int zzd = zzbek.zzd(parcel);
        Set hashSet = new HashSet();
        ArrayList arrayList = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbek.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(1));
                    break;
                case 2:
                    arrayList = zzbek.zzc(parcel, readInt, zzs.CREATOR);
                    hashSet.add(Integer.valueOf(2));
                    break;
                case 3:
                    i = zzbek.zzg(parcel, readInt);
                    hashSet.add(Integer.valueOf(3));
                    break;
                case 4:
                    zzp com_google_android_gms_auth_api_accounttransfer_zzp2 = (zzp) zzbek.zza(parcel, readInt, zzp.CREATOR);
                    hashSet.add(Integer.valueOf(4));
                    com_google_android_gms_auth_api_accounttransfer_zzp = com_google_android_gms_auth_api_accounttransfer_zzp2;
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        if (parcel.dataPosition() == zzd) {
            return new zzm(hashSet, i2, arrayList, i, com_google_android_gms_auth_api_accounttransfer_zzp);
        }
        throw new zzbel("Overread allowed size end=" + zzd, parcel);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new zzm[i];
    }
}
