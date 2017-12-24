package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;

public final class zzbfe extends zzbej {
    public static final Creator<zzbfe> CREATOR = new zzbff();
    private int zzdzm;
    private final zzbfg zzfze;

    zzbfe(int i, zzbfg com_google_android_gms_internal_zzbfg) {
        this.zzdzm = i;
        this.zzfze = com_google_android_gms_internal_zzbfg;
    }

    private zzbfe(zzbfg com_google_android_gms_internal_zzbfg) {
        this.zzdzm = 1;
        this.zzfze = com_google_android_gms_internal_zzbfg;
    }

    public static zzbfe zza(zzbfm<?, ?> com_google_android_gms_internal_zzbfm___) {
        if (com_google_android_gms_internal_zzbfm___ instanceof zzbfg) {
            return new zzbfe((zzbfg) com_google_android_gms_internal_zzbfm___);
        }
        throw new IllegalArgumentException("Unsupported safe parcelable field converter class.");
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, this.zzfze, i, false);
        zzbem.zzai(parcel, zze);
    }

    public final zzbfm<?, ?> zzalh() {
        if (this.zzfze != null) {
            return this.zzfze;
        }
        throw new IllegalStateException("There was no converter wrapped in this ConverterWrapper.");
    }
}
