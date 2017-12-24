package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;

public final class zzbde extends zzbej {
    public static final Creator<zzbde> CREATOR = new zzbdf();
    private int[] zzbbi;
    public final zzbcz zzfgt;
    private boolean zzfgz;
    public final zzfii zzfha;
    public zzbdt zzfhf;
    public byte[] zzfhg;
    private int[] zzfhh;
    private String[] zzfhi;
    private byte[][] zzfhj;
    private zzcsv[] zzfhk;
    public final zzbcz zzfhl;

    public zzbde(zzbdt com_google_android_gms_internal_zzbdt, zzfii com_google_android_gms_internal_zzfii, zzbcz com_google_android_gms_internal_zzbcz, zzbcz com_google_android_gms_internal_zzbcz2, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr, zzcsv[] com_google_android_gms_internal_zzcsvArr, boolean z) {
        this.zzfhf = com_google_android_gms_internal_zzbdt;
        this.zzfha = com_google_android_gms_internal_zzfii;
        this.zzfgt = com_google_android_gms_internal_zzbcz;
        this.zzfhl = null;
        this.zzfhh = iArr;
        this.zzfhi = null;
        this.zzbbi = iArr2;
        this.zzfhj = null;
        this.zzfhk = null;
        this.zzfgz = z;
    }

    zzbde(zzbdt com_google_android_gms_internal_zzbdt, byte[] bArr, int[] iArr, String[] strArr, int[] iArr2, byte[][] bArr2, boolean z, zzcsv[] com_google_android_gms_internal_zzcsvArr) {
        this.zzfhf = com_google_android_gms_internal_zzbdt;
        this.zzfhg = bArr;
        this.zzfhh = iArr;
        this.zzfhi = strArr;
        this.zzfha = null;
        this.zzfgt = null;
        this.zzfhl = null;
        this.zzbbi = iArr2;
        this.zzfhj = bArr2;
        this.zzfhk = com_google_android_gms_internal_zzcsvArr;
        this.zzfgz = z;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof zzbde)) {
            return false;
        }
        zzbde com_google_android_gms_internal_zzbde = (zzbde) obj;
        return zzbg.equal(this.zzfhf, com_google_android_gms_internal_zzbde.zzfhf) && Arrays.equals(this.zzfhg, com_google_android_gms_internal_zzbde.zzfhg) && Arrays.equals(this.zzfhh, com_google_android_gms_internal_zzbde.zzfhh) && Arrays.equals(this.zzfhi, com_google_android_gms_internal_zzbde.zzfhi) && zzbg.equal(this.zzfha, com_google_android_gms_internal_zzbde.zzfha) && zzbg.equal(this.zzfgt, com_google_android_gms_internal_zzbde.zzfgt) && zzbg.equal(this.zzfhl, com_google_android_gms_internal_zzbde.zzfhl) && Arrays.equals(this.zzbbi, com_google_android_gms_internal_zzbde.zzbbi) && Arrays.deepEquals(this.zzfhj, com_google_android_gms_internal_zzbde.zzfhj) && Arrays.equals(this.zzfhk, com_google_android_gms_internal_zzbde.zzfhk) && this.zzfgz == com_google_android_gms_internal_zzbde.zzfgz;
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{this.zzfhf, this.zzfhg, this.zzfhh, this.zzfhi, this.zzfha, this.zzfgt, this.zzfhl, this.zzbbi, this.zzfhj, this.zzfhk, Boolean.valueOf(this.zzfgz)});
    }

    public final String toString() {
        return "LogEventParcelable[" + this.zzfhf + ", LogEventBytes: " + (this.zzfhg == null ? null : new String(this.zzfhg)) + ", TestCodes: " + Arrays.toString(this.zzfhh) + ", MendelPackages: " + Arrays.toString(this.zzfhi) + ", LogEvent: " + this.zzfha + ", ExtensionProducer: " + this.zzfgt + ", VeProducer: " + this.zzfhl + ", ExperimentIDs: " + Arrays.toString(this.zzbbi) + ", ExperimentTokens: " + Arrays.toString(this.zzfhj) + ", ExperimentTokensParcelables: " + Arrays.toString(this.zzfhk) + ", AddPhenotypeExperimentTokens: " + this.zzfgz + "]";
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzfhf, i, false);
        zzbem.zza(parcel, 3, this.zzfhg, false);
        zzbem.zza(parcel, 4, this.zzfhh, false);
        zzbem.zza(parcel, 5, this.zzfhi, false);
        zzbem.zza(parcel, 6, this.zzbbi, false);
        zzbem.zza(parcel, 7, this.zzfhj, false);
        zzbem.zza(parcel, 8, this.zzfgz);
        zzbem.zza(parcel, 9, this.zzfhk, i, false);
        zzbem.zzai(parcel, zze);
    }
}
