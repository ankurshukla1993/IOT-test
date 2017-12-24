package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zzc implements Creator<CredentialPickerConfig> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int i = 0;
        int zzd = zzbek.zzd(parcel);
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        int i2 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    z3 = zzbek.zzc(parcel, readInt);
                    break;
                case 2:
                    z2 = zzbek.zzc(parcel, readInt);
                    break;
                case 3:
                    z = zzbek.zzc(parcel, readInt);
                    break;
                case 4:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 1000:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new CredentialPickerConfig(i2, z3, z2, z, i);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new CredentialPickerConfig[i];
    }
}
