package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

public final class zzbdt extends zzbej {
    public static final Creator<zzbdt> CREATOR = new zzbdu();
    private String packageName;
    private int zzfgi;
    public final String zzfgj;
    public final int zzfgk;
    private String zzfgl;
    private String zzfgm;
    private boolean zzfgn;
    private int zzfgo;
    private boolean zzfhv;

    public zzbdt(String str, int i, int i2, String str2, String str3, String str4, boolean z, int i3) {
        this.packageName = (String) zzbq.checkNotNull(str);
        this.zzfgi = i;
        this.zzfgk = i2;
        this.zzfgj = str2;
        this.zzfgl = str3;
        this.zzfgm = str4;
        this.zzfhv = !z;
        this.zzfgn = z;
        this.zzfgo = i3;
    }

    public zzbdt(String str, int i, int i2, String str2, String str3, boolean z, String str4, boolean z2, int i3) {
        this.packageName = str;
        this.zzfgi = i;
        this.zzfgk = i2;
        this.zzfgl = str2;
        this.zzfgm = str3;
        this.zzfhv = z;
        this.zzfgj = str4;
        this.zzfgn = z2;
        this.zzfgo = i3;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbdt)) {
            return false;
        }
        zzbdt com_google_android_gms_internal_zzbdt = (zzbdt) obj;
        return zzbg.equal(this.packageName, com_google_android_gms_internal_zzbdt.packageName) && this.zzfgi == com_google_android_gms_internal_zzbdt.zzfgi && this.zzfgk == com_google_android_gms_internal_zzbdt.zzfgk && zzbg.equal(this.zzfgj, com_google_android_gms_internal_zzbdt.zzfgj) && zzbg.equal(this.zzfgl, com_google_android_gms_internal_zzbdt.zzfgl) && zzbg.equal(this.zzfgm, com_google_android_gms_internal_zzbdt.zzfgm) && this.zzfhv == com_google_android_gms_internal_zzbdt.zzfhv && this.zzfgn == com_google_android_gms_internal_zzbdt.zzfgn && this.zzfgo == com_google_android_gms_internal_zzbdt.zzfgo;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.packageName, Integer.valueOf(this.zzfgi), Integer.valueOf(this.zzfgk), this.zzfgj, this.zzfgl, this.zzfgm, Boolean.valueOf(this.zzfhv), Boolean.valueOf(this.zzfgn), Integer.valueOf(this.zzfgo)});
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("PlayLoggerContext[");
        stringBuilder.append("package=").append(this.packageName).append(',');
        stringBuilder.append("packageVersionCode=").append(this.zzfgi).append(',');
        stringBuilder.append("logSource=").append(this.zzfgk).append(',');
        stringBuilder.append("logSourceName=").append(this.zzfgj).append(',');
        stringBuilder.append("uploadAccount=").append(this.zzfgl).append(',');
        stringBuilder.append("loggingId=").append(this.zzfgm).append(',');
        stringBuilder.append("logAndroidId=").append(this.zzfhv).append(',');
        stringBuilder.append("isAnonymous=").append(this.zzfgn).append(',');
        stringBuilder.append("qosTier=").append(this.zzfgo);
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.packageName, false);
        zzbem.zzc(parcel, 3, this.zzfgi);
        zzbem.zzc(parcel, 4, this.zzfgk);
        zzbem.zza(parcel, 5, this.zzfgl, false);
        zzbem.zza(parcel, 6, this.zzfgm, false);
        zzbem.zza(parcel, 7, this.zzfhv);
        zzbem.zza(parcel, 8, this.zzfgj, false);
        zzbem.zza(parcel, 9, this.zzfgn);
        zzbem.zzc(parcel, 10, this.zzfgo);
        zzbem.zzai(parcel, zze);
    }
}
