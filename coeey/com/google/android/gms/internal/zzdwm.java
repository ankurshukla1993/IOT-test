package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzdwm extends zzbej {
    public static final Creator<zzdwm> CREATOR = new zzdwn();
    private String zzedt;
    private String zzmch;
    private String zzmci;

    zzdwm(String str, String str2, String str3) {
        this.zzedt = str;
        this.zzmch = str2;
        this.zzmci = str3;
    }

    public final String getEmail() {
        return this.zzedt;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzedt, false);
        zzbem.zza(parcel, 3, this.zzmch, false);
        zzbem.zza(parcel, 4, this.zzmci, false);
        zzbem.zzai(parcel, zze);
    }

    public final String zzbqe() {
        return this.zzmch;
    }

    public final String zzbqf() {
        return this.zzmci;
    }
}
