package com.google.android.gms.auth.api.accounttransfer;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public class DeviceMetaData extends zzbej {
    public static final Creator<DeviceMetaData> CREATOR = new zzw();
    private int zzdzm;
    private boolean zzebr;
    private long zzebs;
    private final boolean zzebt;

    DeviceMetaData(int i, boolean z, long j, boolean z2) {
        this.zzdzm = i;
        this.zzebr = z;
        this.zzebs = j;
        this.zzebt = z2;
    }

    public long getMinAgeOfLockScreen() {
        return this.zzebs;
    }

    public boolean isChallengeAllowed() {
        return this.zzebt;
    }

    public boolean isLockScreenSolved() {
        return this.zzebr;
    }

    public void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, isLockScreenSolved());
        zzbem.zza(parcel, 3, getMinAgeOfLockScreen());
        zzbem.zza(parcel, 4, isChallengeAllowed());
        zzbem.zzai(parcel, zze);
    }
}
