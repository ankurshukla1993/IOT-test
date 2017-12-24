package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzbq;

public final class zzcff extends zzbej {
    public static final Creator<zzcff> CREATOR = new zzcfg();
    public final String packageName;
    public final String zzicq;
    public final String zziux;
    public final String zziuy;
    public final long zziuz;
    public final long zziva;
    public final String zzivb;
    public final boolean zzivc;
    public final boolean zzivd;
    public final long zzive;
    public final String zzivf;
    public final long zzivg;
    public final long zzivh;
    public final int zzivi;
    public final boolean zzivj;

    zzcff(String str, String str2, String str3, long j, String str4, long j2, long j3, String str5, boolean z, boolean z2, String str6, long j4, long j5, int i, boolean z3) {
        zzbq.zzgh(str);
        this.packageName = str;
        if (TextUtils.isEmpty(str2)) {
            str2 = null;
        }
        this.zziux = str2;
        this.zzicq = str3;
        this.zzive = j;
        this.zziuy = str4;
        this.zziuz = j2;
        this.zziva = j3;
        this.zzivb = str5;
        this.zzivc = z;
        this.zzivd = z2;
        this.zzivf = str6;
        this.zzivg = j4;
        this.zzivh = j5;
        this.zzivi = i;
        this.zzivj = z3;
    }

    zzcff(String str, String str2, String str3, String str4, long j, long j2, String str5, boolean z, boolean z2, long j3, String str6, long j4, long j5, int i, boolean z3) {
        this.packageName = str;
        this.zziux = str2;
        this.zzicq = str3;
        this.zzive = j3;
        this.zziuy = str4;
        this.zziuz = j;
        this.zziva = j2;
        this.zzivb = str5;
        this.zzivc = z;
        this.zzivd = z2;
        this.zzivf = str6;
        this.zzivg = j4;
        this.zzivh = j5;
        this.zzivi = i;
        this.zzivj = z3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.packageName, false);
        zzbem.zza(parcel, 3, this.zziux, false);
        zzbem.zza(parcel, 4, this.zzicq, false);
        zzbem.zza(parcel, 5, this.zziuy, false);
        zzbem.zza(parcel, 6, this.zziuz);
        zzbem.zza(parcel, 7, this.zziva);
        zzbem.zza(parcel, 8, this.zzivb, false);
        zzbem.zza(parcel, 9, this.zzivc);
        zzbem.zza(parcel, 10, this.zzivd);
        zzbem.zza(parcel, 11, this.zzive);
        zzbem.zza(parcel, 12, this.zzivf, false);
        zzbem.zza(parcel, 13, this.zzivg);
        zzbem.zza(parcel, 14, this.zzivh);
        zzbem.zzc(parcel, 15, this.zzivi);
        zzbem.zza(parcel, 16, this.zzivj);
        zzbem.zzai(parcel, zze);
    }
}
