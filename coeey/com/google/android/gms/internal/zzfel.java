package com.google.android.gms.internal;

public final class zzfel implements zzfen {
    public static final zzfel zzpcb = new zzfel();

    private zzfel() {
    }

    public final double zza(boolean z, double d, boolean z2, double d2) {
        return z2 ? d2 : d;
    }

    public final int zza(boolean z, int i, boolean z2, int i2) {
        return z2 ? i2 : i;
    }

    public final long zza(boolean z, long j, boolean z2, long j2) {
        return z2 ? j2 : j;
    }

    public final zzfdh zza(boolean z, zzfdh com_google_android_gms_internal_zzfdh, boolean z2, zzfdh com_google_android_gms_internal_zzfdh2) {
        return z2 ? com_google_android_gms_internal_zzfdh2 : com_google_android_gms_internal_zzfdh;
    }

    public final zzfeu zza(zzfeu com_google_android_gms_internal_zzfeu, zzfeu com_google_android_gms_internal_zzfeu2) {
        int size = com_google_android_gms_internal_zzfeu.size();
        int size2 = com_google_android_gms_internal_zzfeu2.size();
        if (size > 0 && size2 > 0) {
            if (!com_google_android_gms_internal_zzfeu.zzcth()) {
                com_google_android_gms_internal_zzfeu = com_google_android_gms_internal_zzfeu.zzlj(size2 + size);
            }
            com_google_android_gms_internal_zzfeu.addAll(com_google_android_gms_internal_zzfeu2);
        }
        return size > 0 ? com_google_android_gms_internal_zzfeu : com_google_android_gms_internal_zzfeu2;
    }

    public final <T> zzfev<T> zza(zzfev<T> com_google_android_gms_internal_zzfev_T, zzfev<T> com_google_android_gms_internal_zzfev_T2) {
        int size = com_google_android_gms_internal_zzfev_T.size();
        int size2 = com_google_android_gms_internal_zzfev_T2.size();
        if (size > 0 && size2 > 0) {
            if (!com_google_android_gms_internal_zzfev_T.zzcth()) {
                com_google_android_gms_internal_zzfev_T = com_google_android_gms_internal_zzfev_T.zzln(size2 + size);
            }
            com_google_android_gms_internal_zzfev_T.addAll(com_google_android_gms_internal_zzfev_T2);
        }
        return size > 0 ? com_google_android_gms_internal_zzfev_T : com_google_android_gms_internal_zzfev_T2;
    }

    public final <K, V> zzffh<K, V> zza(zzffh<K, V> com_google_android_gms_internal_zzffh_K__V, zzffh<K, V> com_google_android_gms_internal_zzffh_K__V2) {
        if (!com_google_android_gms_internal_zzffh_K__V2.isEmpty()) {
            if (!com_google_android_gms_internal_zzffh_K__V.isMutable()) {
                com_google_android_gms_internal_zzffh_K__V = com_google_android_gms_internal_zzffh_K__V.zzcwd();
            }
            com_google_android_gms_internal_zzffh_K__V.zza(com_google_android_gms_internal_zzffh_K__V2);
        }
        return com_google_android_gms_internal_zzffh_K__V;
    }

    public final <T extends zzffi> T zza(T t, T t2) {
        return (t == null || t2 == null) ? t == null ? t2 : t : t.zzcvg().zzc(t2).zzcvm();
    }

    public final zzfgi zza(zzfgi com_google_android_gms_internal_zzfgi, zzfgi com_google_android_gms_internal_zzfgi2) {
        return com_google_android_gms_internal_zzfgi2 == zzfgi.zzcwu() ? com_google_android_gms_internal_zzfgi : zzfgi.zzb(com_google_android_gms_internal_zzfgi, com_google_android_gms_internal_zzfgi2);
    }

    public final Object zza(boolean z, Object obj, Object obj2) {
        return obj2;
    }

    public final String zza(boolean z, String str, boolean z2, String str2) {
        return z2 ? str2 : str;
    }

    public final boolean zza(boolean z, boolean z2, boolean z3, boolean z4) {
        return z3 ? z4 : z2;
    }

    public final Object zzb(boolean z, Object obj, Object obj2) {
        return obj2;
    }

    public final Object zzc(boolean z, Object obj, Object obj2) {
        return obj2;
    }

    public final Object zzd(boolean z, Object obj, Object obj2) {
        return obj2;
    }

    public final void zzdb(boolean z) {
    }

    public final Object zze(boolean z, Object obj, Object obj2) {
        return obj2;
    }

    public final Object zzf(boolean z, Object obj, Object obj2) {
        return obj2;
    }

    public final Object zzg(boolean z, Object obj, Object obj2) {
        return z ? zza((zzffi) obj, (zzffi) obj2) : obj2;
    }
}
