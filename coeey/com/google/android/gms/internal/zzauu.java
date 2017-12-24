package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.auth.api.credentials.Credential;

public final class zzauu extends zzbej {
    public static final Creator<zzauu> CREATOR = new zzauv();
    private final Credential zzedd;

    public zzauu(Credential credential) {
        this.zzedd = credential;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 1, this.zzedd, i, false);
        zzbem.zzai(parcel, zze);
    }
}
