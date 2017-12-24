package com.google.android.gms.internal;

final class zzfeh implements zzfen {
    static final zzfeh zzpbx = new zzfeh();
    private static zzfei zzpby = new zzfei();

    private zzfeh() {
    }

    public final double zza(boolean z, double d, boolean z2, double d2) {
        if (z == z2 && d == d2) {
            return d;
        }
        throw zzpby;
    }

    public final int zza(boolean z, int i, boolean z2, int i2) {
        if (z == z2 && i == i2) {
            return i;
        }
        throw zzpby;
    }

    public final long zza(boolean z, long j, boolean z2, long j2) {
        if (z == z2 && j == j2) {
            return j;
        }
        throw zzpby;
    }

    public final zzfdh zza(boolean z, zzfdh com_google_android_gms_internal_zzfdh, boolean z2, zzfdh com_google_android_gms_internal_zzfdh2) {
        if (z == z2 && com_google_android_gms_internal_zzfdh.equals(com_google_android_gms_internal_zzfdh2)) {
            return com_google_android_gms_internal_zzfdh;
        }
        throw zzpby;
    }

    public final zzfeu zza(zzfeu com_google_android_gms_internal_zzfeu, zzfeu com_google_android_gms_internal_zzfeu2) {
        if (com_google_android_gms_internal_zzfeu.equals(com_google_android_gms_internal_zzfeu2)) {
            return com_google_android_gms_internal_zzfeu;
        }
        throw zzpby;
    }

    public final <T> zzfev<T> zza(zzfev<T> com_google_android_gms_internal_zzfev_T, zzfev<T> com_google_android_gms_internal_zzfev_T2) {
        if (com_google_android_gms_internal_zzfev_T.equals(com_google_android_gms_internal_zzfev_T2)) {
            return com_google_android_gms_internal_zzfev_T;
        }
        throw zzpby;
    }

    public final <K, V> zzffh<K, V> zza(zzffh<K, V> com_google_android_gms_internal_zzffh_K__V, zzffh<K, V> com_google_android_gms_internal_zzffh_K__V2) {
        if (com_google_android_gms_internal_zzffh_K__V.equals(com_google_android_gms_internal_zzffh_K__V2)) {
            return com_google_android_gms_internal_zzffh_K__V;
        }
        throw zzpby;
    }

    public final <T extends zzffi> T zza(T t, T t2) {
        if (t == null && t2 == null) {
            return null;
        }
        if (t == null || t2 == null) {
            throw zzpby;
        }
        T t3 = (zzfee) t;
        if (t3 == t2 || !((zzfee) t3.zza(zzfem.zzpci, null, null)).getClass().isInstance(t2)) {
            return t;
        }
        Object obj = (zzfee) t2;
        t3.zza(zzfem.zzpcd, (Object) this, obj);
        t3.zzpbs = zza(t3.zzpbs, obj.zzpbs);
        return t;
    }

    public final zzfgi zza(zzfgi com_google_android_gms_internal_zzfgi, zzfgi com_google_android_gms_internal_zzfgi2) {
        if (com_google_android_gms_internal_zzfgi.equals(com_google_android_gms_internal_zzfgi2)) {
            return com_google_android_gms_internal_zzfgi;
        }
        throw zzpby;
    }

    public final Object zza(boolean z, Object obj, Object obj2) {
        if (z && obj.equals(obj2)) {
            return obj;
        }
        throw zzpby;
    }

    public final String zza(boolean z, String str, boolean z2, String str2) {
        if (z == z2 && str.equals(str2)) {
            return str;
        }
        throw zzpby;
    }

    public final boolean zza(boolean z, boolean z2, boolean z3, boolean z4) {
        if (z == z3 && z2 == z4) {
            return z2;
        }
        throw zzpby;
    }

    public final Object zzb(boolean z, Object obj, Object obj2) {
        if (z && obj.equals(obj2)) {
            return obj;
        }
        throw zzpby;
    }

    public final Object zzc(boolean z, Object obj, Object obj2) {
        if (z && obj.equals(obj2)) {
            return obj;
        }
        throw zzpby;
    }

    public final Object zzd(boolean z, Object obj, Object obj2) {
        if (z && obj.equals(obj2)) {
            return obj;
        }
        throw zzpby;
    }

    public final void zzdb(boolean z) {
        if (z) {
            throw zzpby;
        }
    }

    public final Object zze(boolean z, Object obj, Object obj2) {
        if (z && obj.equals(obj2)) {
            return obj;
        }
        throw zzpby;
    }

    public final Object zzf(boolean z, Object obj, Object obj2) {
        if (z && obj.equals(obj2)) {
            return obj;
        }
        throw zzpby;
    }

    public final Object zzg(boolean z, Object obj, Object obj2) {
        if (z) {
            Object obj3;
            zzfee com_google_android_gms_internal_zzfee = (zzfee) obj;
            obj2 = (zzffi) obj2;
            if (com_google_android_gms_internal_zzfee == obj2) {
                obj3 = 1;
            } else if (((zzfee) com_google_android_gms_internal_zzfee.zza(zzfem.zzpci, null, null)).getClass().isInstance(obj2)) {
                obj2 = (zzfee) obj2;
                com_google_android_gms_internal_zzfee.zza(zzfem.zzpcd, (Object) this, obj2);
                com_google_android_gms_internal_zzfee.zzpbs = zza(com_google_android_gms_internal_zzfee.zzpbs, obj2.zzpbs);
                int i = 1;
            } else {
                obj3 = null;
            }
            if (obj3 != null) {
                return obj;
            }
        }
        throw zzpby;
    }
}
