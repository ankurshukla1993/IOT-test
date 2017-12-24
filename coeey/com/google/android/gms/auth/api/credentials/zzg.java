package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zzg implements Creator<HintRequest> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        boolean z = false;
        int zzd = zzbek.zzd(parcel);
        String str2 = null;
        String[] strArr = null;
        boolean z2 = false;
        boolean z3 = false;
        CredentialPickerConfig credentialPickerConfig = null;
        int i = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    credentialPickerConfig = (CredentialPickerConfig) zzbek.zza(parcel, readInt, CredentialPickerConfig.CREATOR);
                    break;
                case 2:
                    z3 = zzbek.zzc(parcel, readInt);
                    break;
                case 3:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 4:
                    strArr = zzbek.zzaa(parcel, readInt);
                    break;
                case 5:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 6:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 7:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                case 1000:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new HintRequest(i, credentialPickerConfig, z3, z2, strArr, z, str2, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new HintRequest[i];
    }
}
