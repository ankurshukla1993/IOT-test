package com.google.android.gms.common.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class zzbv extends zzbej {
    public static final Creator<zzbv> CREATOR = new zzbw();
    private int zzdzm;
    private final int zzfyv;
    private final int zzfyw;
    @Deprecated
    private final Scope[] zzfyx;

    zzbv(int i, int i2, int i3, Scope[] scopeArr) {
        this.zzdzm = i;
        this.zzfyv = i2;
        this.zzfyw = i3;
        this.zzfyx = scopeArr;
    }

    public zzbv(int i, int i2, Scope[] scopeArr) {
        this(1, i, i2, null);
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zzc(parcel, 2, this.zzfyv);
        zzbem.zzc(parcel, 3, this.zzfyw);
        zzbem.zza(parcel, 4, this.zzfyx, i, false);
        zzbem.zzai(parcel, zze);
    }
}
