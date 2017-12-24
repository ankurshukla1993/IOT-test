package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.Map;

public final class zzbfl<I, O> extends zzbej {
    public static final zzbfo CREATOR = new zzbfo();
    private final int zzdzm;
    protected final int zzfzk;
    protected final boolean zzfzl;
    protected final int zzfzm;
    protected final boolean zzfzn;
    protected final String zzfzo;
    protected final int zzfzp;
    protected final Class<? extends zzbfk> zzfzq;
    private String zzfzr;
    private zzbfq zzfzs;
    private zzbfm<I, O> zzfzt;

    zzbfl(int i, int i2, boolean z, int i3, boolean z2, String str, int i4, String str2, zzbfe com_google_android_gms_internal_zzbfe) {
        this.zzdzm = i;
        this.zzfzk = i2;
        this.zzfzl = z;
        this.zzfzm = i3;
        this.zzfzn = z2;
        this.zzfzo = str;
        this.zzfzp = i4;
        if (str2 == null) {
            this.zzfzq = null;
            this.zzfzr = null;
        } else {
            this.zzfzq = zzbfv.class;
            this.zzfzr = str2;
        }
        if (com_google_android_gms_internal_zzbfe == null) {
            this.zzfzt = null;
        } else {
            this.zzfzt = com_google_android_gms_internal_zzbfe.zzalh();
        }
    }

    private zzbfl(int i, boolean z, int i2, boolean z2, String str, int i3, Class<? extends zzbfk> cls, zzbfm<I, O> com_google_android_gms_internal_zzbfm_I__O) {
        this.zzdzm = 1;
        this.zzfzk = i;
        this.zzfzl = z;
        this.zzfzm = i2;
        this.zzfzn = z2;
        this.zzfzo = str;
        this.zzfzp = i3;
        this.zzfzq = cls;
        if (cls == null) {
            this.zzfzr = null;
        } else {
            this.zzfzr = cls.getCanonicalName();
        }
        this.zzfzt = com_google_android_gms_internal_zzbfm_I__O;
    }

    public static zzbfl zza(String str, int i, zzbfm<?, ?> com_google_android_gms_internal_zzbfm___, boolean z) {
        return new zzbfl(7, false, 0, false, str, i, null, com_google_android_gms_internal_zzbfm___);
    }

    public static <T extends zzbfk> zzbfl<T, T> zza(String str, int i, Class<T> cls) {
        return new zzbfl(11, false, 11, false, str, i, cls, null);
    }

    private String zzalj() {
        return this.zzfzr == null ? null : this.zzfzr;
    }

    public static <T extends zzbfk> zzbfl<ArrayList<T>, ArrayList<T>> zzb(String str, int i, Class<T> cls) {
        return new zzbfl(11, true, 11, true, str, i, cls, null);
    }

    public static zzbfl<Integer, Integer> zzj(String str, int i) {
        return new zzbfl(0, false, 0, false, str, i, null, null);
    }

    public static zzbfl<Boolean, Boolean> zzk(String str, int i) {
        return new zzbfl(6, false, 6, false, str, i, null, null);
    }

    public static zzbfl<String, String> zzl(String str, int i) {
        return new zzbfl(7, false, 7, false, str, i, null, null);
    }

    public static zzbfl<ArrayList<String>, ArrayList<String>> zzm(String str, int i) {
        return new zzbfl(7, true, 7, true, str, i, null, null);
    }

    public static zzbfl<byte[], byte[]> zzn(String str, int i) {
        return new zzbfl(8, false, 8, false, str, 4, null, null);
    }

    public final I convertBack(O o) {
        return this.zzfzt.convertBack(o);
    }

    public final String toString() {
        zzbi zzg = zzbg.zzw(this).zzg("versionCode", Integer.valueOf(this.zzdzm)).zzg("typeIn", Integer.valueOf(this.zzfzk)).zzg("typeInArray", Boolean.valueOf(this.zzfzl)).zzg("typeOut", Integer.valueOf(this.zzfzm)).zzg("typeOutArray", Boolean.valueOf(this.zzfzn)).zzg("outputFieldName", this.zzfzo).zzg("safeParcelFieldId", Integer.valueOf(this.zzfzp)).zzg("concreteTypeName", zzalj());
        Class cls = this.zzfzq;
        if (cls != null) {
            zzg.zzg("concreteType.class", cls.getCanonicalName());
        }
        if (this.zzfzt != null) {
            zzg.zzg("converterName", this.zzfzt.getClass().getCanonicalName());
        }
        return zzg.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zzc(parcel, 2, this.zzfzk);
        zzbem.zza(parcel, 3, this.zzfzl);
        zzbem.zzc(parcel, 4, this.zzfzm);
        zzbem.zza(parcel, 5, this.zzfzn);
        zzbem.zza(parcel, 6, this.zzfzo, false);
        zzbem.zzc(parcel, 7, this.zzfzp);
        zzbem.zza(parcel, 8, zzalj(), false);
        zzbem.zza(parcel, 9, this.zzfzt == null ? null : zzbfe.zza(this.zzfzt), i, false);
        zzbem.zzai(parcel, zze);
    }

    public final void zza(zzbfq com_google_android_gms_internal_zzbfq) {
        this.zzfzs = com_google_android_gms_internal_zzbfq;
    }

    public final int zzali() {
        return this.zzfzp;
    }

    public final boolean zzalk() {
        return this.zzfzt != null;
    }

    public final Map<String, zzbfl<?, ?>> zzall() {
        zzbq.checkNotNull(this.zzfzr);
        zzbq.checkNotNull(this.zzfzs);
        return this.zzfzs.zzgl(this.zzfzr);
    }
}
