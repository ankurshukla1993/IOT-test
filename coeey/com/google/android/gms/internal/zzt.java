package com.google.android.gms.internal;

public final class zzt<T> {
    public final T result;
    public final zzc zzbe;
    public final zzaa zzbf;
    public boolean zzbg;

    private zzt(zzaa com_google_android_gms_internal_zzaa) {
        this.zzbg = false;
        this.result = null;
        this.zzbe = null;
        this.zzbf = com_google_android_gms_internal_zzaa;
    }

    private zzt(T t, zzc com_google_android_gms_internal_zzc) {
        this.zzbg = false;
        this.result = t;
        this.zzbe = com_google_android_gms_internal_zzc;
        this.zzbf = null;
    }

    public static <T> zzt<T> zza(T t, zzc com_google_android_gms_internal_zzc) {
        return new zzt(t, com_google_android_gms_internal_zzc);
    }

    public static <T> zzt<T> zzc(zzaa com_google_android_gms_internal_zzaa) {
        return new zzt(com_google_android_gms_internal_zzaa);
    }
}
