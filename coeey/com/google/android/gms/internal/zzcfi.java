package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzcfi extends zzbej {
    public static final Creator<zzcfi> CREATOR = new zzcfj();
    public String packageName;
    private int versionCode;
    public String zzivk;
    public zzckk zzivl;
    public long zzivm;
    public boolean zzivn;
    public String zzivo;
    public zzcfx zzivp;
    public long zzivq;
    public zzcfx zzivr;
    public long zzivs;
    public zzcfx zzivt;

    zzcfi(int i, String str, String str2, zzckk com_google_android_gms_internal_zzckk, long j, boolean z, String str3, zzcfx com_google_android_gms_internal_zzcfx, long j2, zzcfx com_google_android_gms_internal_zzcfx2, long j3, zzcfx com_google_android_gms_internal_zzcfx3) {
        this.versionCode = i;
        this.packageName = str;
        this.zzivk = str2;
        this.zzivl = com_google_android_gms_internal_zzckk;
        this.zzivm = j;
        this.zzivn = z;
        this.zzivo = str3;
        this.zzivp = com_google_android_gms_internal_zzcfx;
        this.zzivq = j2;
        this.zzivr = com_google_android_gms_internal_zzcfx2;
        this.zzivs = j3;
        this.zzivt = com_google_android_gms_internal_zzcfx3;
    }

    zzcfi(zzcfi com_google_android_gms_internal_zzcfi) {
        this.versionCode = 1;
        zzbq.checkNotNull(com_google_android_gms_internal_zzcfi);
        this.packageName = com_google_android_gms_internal_zzcfi.packageName;
        this.zzivk = com_google_android_gms_internal_zzcfi.zzivk;
        this.zzivl = com_google_android_gms_internal_zzcfi.zzivl;
        this.zzivm = com_google_android_gms_internal_zzcfi.zzivm;
        this.zzivn = com_google_android_gms_internal_zzcfi.zzivn;
        this.zzivo = com_google_android_gms_internal_zzcfi.zzivo;
        this.zzivp = com_google_android_gms_internal_zzcfi.zzivp;
        this.zzivq = com_google_android_gms_internal_zzcfi.zzivq;
        this.zzivr = com_google_android_gms_internal_zzcfi.zzivr;
        this.zzivs = com_google_android_gms_internal_zzcfi.zzivs;
        this.zzivt = com_google_android_gms_internal_zzcfi.zzivt;
    }

    zzcfi(String str, String str2, zzckk com_google_android_gms_internal_zzckk, long j, boolean z, String str3, zzcfx com_google_android_gms_internal_zzcfx, long j2, zzcfx com_google_android_gms_internal_zzcfx2, long j3, zzcfx com_google_android_gms_internal_zzcfx3) {
        this.versionCode = 1;
        this.packageName = str;
        this.zzivk = str2;
        this.zzivl = com_google_android_gms_internal_zzckk;
        this.zzivm = j;
        this.zzivn = z;
        this.zzivo = str3;
        this.zzivp = com_google_android_gms_internal_zzcfx;
        this.zzivq = j2;
        this.zzivr = com_google_android_gms_internal_zzcfx2;
        this.zzivs = j3;
        this.zzivt = com_google_android_gms_internal_zzcfx3;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.versionCode);
        zzbem.zza(parcel, 2, this.packageName, false);
        zzbem.zza(parcel, 3, this.zzivk, false);
        zzbem.zza(parcel, 4, this.zzivl, i, false);
        zzbem.zza(parcel, 5, this.zzivm);
        zzbem.zza(parcel, 6, this.zzivn);
        zzbem.zza(parcel, 7, this.zzivo, false);
        zzbem.zza(parcel, 8, this.zzivp, i, false);
        zzbem.zza(parcel, 9, this.zzivq);
        zzbem.zza(parcel, 10, this.zzivr, i, false);
        zzbem.zza(parcel, 11, this.zzivs);
        zzbem.zza(parcel, 12, this.zzivt, i, false);
        zzbem.zzai(parcel, zze);
    }
}
