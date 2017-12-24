package com.google.android.gms.auth.api.credentials;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;
import java.util.List;

public final class zza implements Creator<Credential> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        String str = null;
        int zzd = zzbek.zzd(parcel);
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        String str6 = null;
        List list = null;
        Uri uri = null;
        String str7 = null;
        String str8 = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    str8 = zzbek.zzq(parcel, readInt);
                    break;
                case 2:
                    str7 = zzbek.zzq(parcel, readInt);
                    break;
                case 3:
                    uri = (Uri) zzbek.zza(parcel, readInt, Uri.CREATOR);
                    break;
                case 4:
                    list = zzbek.zzc(parcel, readInt, IdToken.CREATOR);
                    break;
                case 5:
                    str6 = zzbek.zzq(parcel, readInt);
                    break;
                case 6:
                    str5 = zzbek.zzq(parcel, readInt);
                    break;
                case 7:
                    str4 = zzbek.zzq(parcel, readInt);
                    break;
                case 8:
                    str3 = zzbek.zzq(parcel, readInt);
                    break;
                case 9:
                    str2 = zzbek.zzq(parcel, readInt);
                    break;
                case 10:
                    str = zzbek.zzq(parcel, readInt);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new Credential(str8, str7, uri, list, str6, str5, str4, str3, str2, str);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new Credential[i];
    }
}
