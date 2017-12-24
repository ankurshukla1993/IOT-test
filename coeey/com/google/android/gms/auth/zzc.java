package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbek;
import java.util.List;

public final class zzc implements Creator<AccountChangeEventsResponse> {
    public final /* synthetic */ Object createFromParcel(Parcel parcel) {
        int zzd = zzbek.zzd(parcel);
        int i = 0;
        List list = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            switch (65535 & readInt) {
                case 1:
                    i = zzbek.zzg(parcel, readInt);
                    break;
                case 2:
                    list = zzbek.zzc(parcel, readInt, AccountChangeEvent.CREATOR);
                    break;
                default:
                    zzbek.zzb(parcel, readInt);
                    break;
            }
        }
        zzbek.zzaf(parcel, zzd);
        return new AccountChangeEventsResponse(i, list);
    }

    public final /* synthetic */ Object[] newArray(int i) {
        return new AccountChangeEventsResponse[i];
    }
}
