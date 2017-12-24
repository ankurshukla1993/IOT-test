package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbej;
import com.google.android.gms.internal.zzbem;

public final class Scope extends zzbej implements ReflectedParcelable {
    public static final Creator<Scope> CREATOR = new zzf();
    private int zzdzm;
    private final String zzfkn;

    Scope(int i, String str) {
        zzbq.zzh(str, "scopeUri must not be null or empty");
        this.zzdzm = i;
        this.zzfkn = str;
    }

    public Scope(String str) {
        this(1, str);
    }

    public final boolean equals(Object obj) {
        return this == obj ? true : !(obj instanceof Scope) ? false : this.zzfkn.equals(((Scope) obj).zzfkn);
    }

    public final int hashCode() {
        return this.zzfkn.hashCode();
    }

    public final String toString() {
        return this.zzfkn;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.zzfkn, false);
        zzbem.zzai(parcel, zze);
    }

    public final String zzagj() {
        return this.zzfkn;
    }
}
