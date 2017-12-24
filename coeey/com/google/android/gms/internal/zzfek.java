package com.google.android.gms.internal;

final class zzfek implements zzfen {
    int zzpca = 0;

    zzfek() {
    }

    public final double zza(boolean z, double d, boolean z2, double d2) {
        this.zzpca = (this.zzpca * 53) + zzfer.zzdd(Double.doubleToLongBits(d));
        return d;
    }

    public final int zza(boolean z, int i, boolean z2, int i2) {
        this.zzpca = (this.zzpca * 53) + i;
        return i;
    }

    public final long zza(boolean z, long j, boolean z2, long j2) {
        this.zzpca = (this.zzpca * 53) + zzfer.zzdd(j);
        return j;
    }

    public final zzfdh zza(boolean z, zzfdh com_google_android_gms_internal_zzfdh, boolean z2, zzfdh com_google_android_gms_internal_zzfdh2) {
        this.zzpca = (this.zzpca * 53) + com_google_android_gms_internal_zzfdh.hashCode();
        return com_google_android_gms_internal_zzfdh;
    }

    public final zzfeu zza(zzfeu com_google_android_gms_internal_zzfeu, zzfeu com_google_android_gms_internal_zzfeu2) {
        this.zzpca = (this.zzpca * 53) + com_google_android_gms_internal_zzfeu.hashCode();
        return com_google_android_gms_internal_zzfeu;
    }

    public final <T> zzfev<T> zza(zzfev<T> com_google_android_gms_internal_zzfev_T, zzfev<T> com_google_android_gms_internal_zzfev_T2) {
        this.zzpca = (this.zzpca * 53) + com_google_android_gms_internal_zzfev_T.hashCode();
        return com_google_android_gms_internal_zzfev_T;
    }

    public final <K, V> zzffh<K, V> zza(zzffh<K, V> com_google_android_gms_internal_zzffh_K__V, zzffh<K, V> com_google_android_gms_internal_zzffh_K__V2) {
        this.zzpca = (this.zzpca * 53) + com_google_android_gms_internal_zzffh_K__V.hashCode();
        return com_google_android_gms_internal_zzffh_K__V;
    }

    public final <T extends zzffi> T zza(T t, T t2) {
        int i;
        if (t == null) {
            i = 37;
        } else if (t instanceof zzfee) {
            Object obj = (zzfee) t;
            if (obj.zzpaf == 0) {
                int i2 = this.zzpca;
                this.zzpca = 0;
                obj.zza(zzfem.zzpcd, (Object) this, obj);
                obj.zzpbs = zza(obj.zzpbs, obj.zzpbs);
                obj.zzpaf = this.zzpca;
                this.zzpca = i2;
            }
            i = obj.zzpaf;
        } else {
            i = t.hashCode();
        }
        this.zzpca = i + (this.zzpca * 53);
        return t;
    }

    public final zzfgi zza(zzfgi com_google_android_gms_internal_zzfgi, zzfgi com_google_android_gms_internal_zzfgi2) {
        this.zzpca = (this.zzpca * 53) + com_google_android_gms_internal_zzfgi.hashCode();
        return com_google_android_gms_internal_zzfgi;
    }

    public final Object zza(boolean z, Object obj, Object obj2) {
        this.zzpca = zzfer.zzdc(((Boolean) obj).booleanValue()) + (this.zzpca * 53);
        return obj;
    }

    public final String zza(boolean z, String str, boolean z2, String str2) {
        this.zzpca = (this.zzpca * 53) + str.hashCode();
        return str;
    }

    public final boolean zza(boolean z, boolean z2, boolean z3, boolean z4) {
        this.zzpca = (this.zzpca * 53) + zzfer.zzdc(z2);
        return z2;
    }

    public final Object zzb(boolean z, Object obj, Object obj2) {
        this.zzpca = ((Integer) obj).intValue() + (this.zzpca * 53);
        return obj;
    }

    public final Object zzc(boolean z, Object obj, Object obj2) {
        this.zzpca = zzfer.zzdd(Double.doubleToLongBits(((Double) obj).doubleValue())) + (this.zzpca * 53);
        return obj;
    }

    public final Object zzd(boolean z, Object obj, Object obj2) {
        this.zzpca = zzfer.zzdd(((Long) obj).longValue()) + (this.zzpca * 53);
        return obj;
    }

    public final void zzdb(boolean z) {
        if (z) {
            throw new IllegalStateException();
        }
    }

    public final Object zze(boolean z, Object obj, Object obj2) {
        this.zzpca = (this.zzpca * 53) + obj.hashCode();
        return obj;
    }

    public final Object zzf(boolean z, Object obj, Object obj2) {
        this.zzpca = (this.zzpca * 53) + obj.hashCode();
        return obj;
    }

    public final Object zzg(boolean z, Object obj, Object obj2) {
        return zza((zzffi) obj, (zzffi) obj2);
    }
}
