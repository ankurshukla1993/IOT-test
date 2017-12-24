package com.google.android.gms.common.images;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;

public final class zze implements Creator<WebImage> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        Uri uri = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i2 = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    uri = (Uri) zzbek.zza(parcel, readInt, Uri.CREATOR);
                    break;
                case 3:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 4:
                    i3 = zzbek.zzg(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new WebImage(i2, uri, i, i3);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new WebImage[i];
    }
}
