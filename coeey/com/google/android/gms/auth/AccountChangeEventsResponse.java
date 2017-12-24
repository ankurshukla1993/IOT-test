package com.google.android.gms.auth;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;
import java.util.List;

public class AccountChangeEventsResponse extends zzbej {
    public static final Creator<AccountChangeEventsResponse> CREATOR = new zzc();
    private int mVersion;
    private List<AccountChangeEvent> zzaof;

    AccountChangeEventsResponse(int i, List<AccountChangeEvent> list) {
        this.mVersion = i;
        this.zzaof = (List) zzbq.checkNotNull(list);
    }

    public AccountChangeEventsResponse(List<AccountChangeEvent> list) {
        this.mVersion = 1;
        this.zzaof = (List) zzbq.checkNotNull(list);
    }

    public List<AccountChangeEvent> getEvents() {
        return this.zzaof;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.mVersion);
        zzbem.zzc(parcel, 2, this.zzaof, false);
        zzbem.zzai(parcel, zze);
    }
}
