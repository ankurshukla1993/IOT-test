package com.google.firebase.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zzr implements Creator<PhoneAuthCredential> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        boolean z = false;
        String str = null;
        int zzd = zzbek.zzd(parcel);
        String str2 = null;
        boolean z2 = false;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str4 = zzbek.zzq(parcel, readInt);
                    break;
                case 2:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 4:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 5:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 6:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new PhoneAuthCredential(str4, str3, z2, str2, z, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new PhoneAuthCredential[i];
    }
}
