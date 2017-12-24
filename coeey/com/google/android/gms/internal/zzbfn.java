package com.google.android.gms.internal;

public abstract class zzbfn extends zzbfk implements zzben {
    public final int describeContents() {
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!getClass().isInstance(obj)) {
            return false;
        }
        zzbfk com_google_android_gms_internal_zzbfk = (zzbfk) obj;
        for (zzbfl com_google_android_gms_internal_zzbfl : zzaaj().values()) {
            if (zza(com_google_android_gms_internal_zzbfl)) {
                if (!com_google_android_gms_internal_zzbfk.zza(com_google_android_gms_internal_zzbfl)) {
                    return false;
                }
                if (!zzb(com_google_android_gms_internal_zzbfl).equals(com_google_android_gms_internal_zzbfk.zzb(com_google_android_gms_internal_zzbfl))) {
                    return false;
                }
            } else if (com_google_android_gms_internal_zzbfk.zza(com_google_android_gms_internal_zzbfl)) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        int i = 0;
        for (zzbfl com_google_android_gms_internal_zzbfl : zzaaj().values()) {
            int hashCode;
            if (zza(com_google_android_gms_internal_zzbfl)) {
                hashCode = zzb(com_google_android_gms_internal_zzbfl).hashCode() + (i * 31);
            } else {
                hashCode = i;
            }
            i = hashCode;
        }
        return i;
    }

    public Object zzgj(String str) {
        return null;
    }

    public boolean zzgk(String str) {
        return false;
    }
}
