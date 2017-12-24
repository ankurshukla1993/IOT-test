package com.google.android.gms.internal;

public abstract class zzbzn<T> {
    private final int zzbfn;
    private final String zzbfo;
    private final T zzbfp;

    private zzbzn(int i, String str, T t) {
        this.zzbfn = i;
        this.zzbfo = str;
        this.zzbfp = t;
        zzbzy.zzaqp().zza(this);
    }

    public static zzbzp zzb(int i, String str, Boolean bool) {
        return new zzbzp(0, str, bool);
    }

    public static zzbzq zzb(int i, String str, int i2) {
        return new zzbzq(0, str, Integer.valueOf(i2));
    }

    public static zzbzr zzb(int i, String str, long j) {
        return new zzbzr(0, str, Long.valueOf(j));
    }

    public final String getKey() {
        return this.zzbfo;
    }

    public final int getSource() {
        return this.zzbfn;
    }

    protected abstract T zza(zzbzv com_google_android_gms_internal_zzbzv);

    public final T zzip() {
        return this.zzbfp;
    }
}
