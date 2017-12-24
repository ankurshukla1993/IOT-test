package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;

public final class zzckk extends zzbej {
    public static final Creator<zzckk> CREATOR = new zzckl();
    public final String name;
    private int versionCode;
    private String zzfzi;
    public final String zzivk;
    public final long zzjgn;
    private Long zzjgo;
    private Float zzjgp;
    private Double zzjgq;

    zzckk(int i, String str, long j, Long l, Float f, String str2, String str3, Double d) {
        Double d2 = null;
        this.versionCode = i;
        this.name = str;
        this.zzjgn = j;
        this.zzjgo = l;
        this.zzjgp = null;
        if (i == 1) {
            if (f != null) {
                d2 = Double.valueOf(f.doubleValue());
            }
            this.zzjgq = d2;
        } else {
            this.zzjgq = d;
        }
        this.zzfzi = str2;
        this.zzivk = str3;
    }

    zzckk(zzckm com_google_android_gms_internal_zzckm) {
        this(com_google_android_gms_internal_zzckm.mName, com_google_android_gms_internal_zzckm.zzjgr, com_google_android_gms_internal_zzckm.mValue, com_google_android_gms_internal_zzckm.mOrigin);
    }

    zzckk(String str, long j, Object obj, String str2) {
        zzbq.zzgh(str);
        this.versionCode = 2;
        this.name = str;
        this.zzjgn = j;
        this.zzivk = str2;
        if (obj == null) {
            this.zzjgo = null;
            this.zzjgp = null;
            this.zzjgq = null;
            this.zzfzi = null;
        } else if (obj instanceof Long) {
            this.zzjgo = (Long) obj;
            this.zzjgp = null;
            this.zzjgq = null;
            this.zzfzi = null;
        } else if (obj instanceof String) {
            this.zzjgo = null;
            this.zzjgp = null;
            this.zzjgq = null;
            this.zzfzi = (String) obj;
        } else if (obj instanceof Double) {
            this.zzjgo = null;
            this.zzjgp = null;
            this.zzjgq = (Double) obj;
            this.zzfzi = null;
        } else {
            throw new IllegalArgumentException("User attribute given of un-supported type");
        }
    }

    public final Object getValue() {
        return this.zzjgo != null ? this.zzjgo : this.zzjgq != null ? this.zzjgq : this.zzfzi != null ? this.zzfzi : null;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.versionCode);
        zzbem.zza(parcel, 2, this.name, false);
        zzbem.zza(parcel, 3, this.zzjgn);
        zzbem.zza(parcel, 4, this.zzjgo, false);
        zzbem.zza(parcel, 5, null, false);
        zzbem.zza(parcel, 6, this.zzfzi, false);
        zzbem.zza(parcel, 7, this.zzivk, false);
        zzbem.zza(parcel, 8, this.zzjgq, false);
        zzbem.zzai(parcel, zze);
    }
}
