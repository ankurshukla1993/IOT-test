package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zzcsv extends zzbej {
    public static final Creator<zzcsv> CREATOR = new zzctb();
    private static byte[][] zzfgh = new byte[0][];
    private static zzcsv zzjtt = new zzcsv("", null, zzfgh, zzfgh, zzfgh, zzfgh, null, null);
    private static final zzcta zzjuc = new zzcsw();
    private static final zzcta zzjud = new zzcsx();
    private static final zzcta zzjue = new zzcsy();
    private static final zzcta zzjuf = new zzcsz();
    private String zzjtu;
    private byte[] zzjtv;
    private byte[][] zzjtw;
    private byte[][] zzjtx;
    private byte[][] zzjty;
    private byte[][] zzjtz;
    private int[] zzjua;
    private byte[][] zzjub;

    public zzcsv(String str, byte[] bArr, byte[][] bArr2, byte[][] bArr3, byte[][] bArr4, byte[][] bArr5, int[] iArr, byte[][] bArr6) {
        this.zzjtu = str;
        this.zzjtv = bArr;
        this.zzjtw = bArr2;
        this.zzjtx = bArr3;
        this.zzjty = bArr4;
        this.zzjtz = bArr5;
        this.zzjua = iArr;
        this.zzjub = bArr6;
    }

    private static void zza(StringBuilder stringBuilder, String str, int[] iArr) {
        stringBuilder.append(str);
        stringBuilder.append("=");
        if (iArr == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append("(");
        int length = iArr.length;
        Object obj = 1;
        int i = 0;
        while (i < length) {
            int i2 = iArr[i];
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append(i2);
            i++;
            obj = null;
        }
        stringBuilder.append(")");
    }

    private static void zza(StringBuilder stringBuilder, String str, byte[][] bArr) {
        stringBuilder.append(str);
        stringBuilder.append("=");
        if (bArr == null) {
            stringBuilder.append("null");
            return;
        }
        stringBuilder.append("(");
        int length = bArr.length;
        Object obj = 1;
        int i = 0;
        while (i < length) {
            byte[] bArr2 = bArr[i];
            if (obj == null) {
                stringBuilder.append(", ");
            }
            stringBuilder.append("'");
            stringBuilder.append(Base64.encodeToString(bArr2, 3));
            stringBuilder.append("'");
            i++;
            obj = null;
        }
        stringBuilder.append(")");
    }

    private static List<String> zzb(byte[][] bArr) {
        if (bArr == null) {
            return Collections.emptyList();
        }
        List<String> arrayList = new ArrayList(bArr.length);
        for (byte[] encodeToString : bArr) {
            arrayList.add(Base64.encodeToString(encodeToString, 3));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    private static List<Integer> zze(int[] iArr) {
        if (iArr == null) {
            return Collections.emptyList();
        }
        List<Integer> arrayList = new ArrayList(iArr.length);
        for (int valueOf : iArr) {
            arrayList.add(Integer.valueOf(valueOf));
        }
        Collections.sort(arrayList);
        return arrayList;
    }

    public final boolean equals(Object obj) {
        if (!(obj instanceof zzcsv)) {
            return false;
        }
        zzcsv com_google_android_gms_internal_zzcsv = (zzcsv) obj;
        return zzctf.equals(this.zzjtu, com_google_android_gms_internal_zzcsv.zzjtu) && Arrays.equals(this.zzjtv, com_google_android_gms_internal_zzcsv.zzjtv) && zzctf.equals(zzb(this.zzjtw), zzb(com_google_android_gms_internal_zzcsv.zzjtw)) && zzctf.equals(zzb(this.zzjtx), zzb(com_google_android_gms_internal_zzcsv.zzjtx)) && zzctf.equals(zzb(this.zzjty), zzb(com_google_android_gms_internal_zzcsv.zzjty)) && zzctf.equals(zzb(this.zzjtz), zzb(com_google_android_gms_internal_zzcsv.zzjtz)) && zzctf.equals(zze(this.zzjua), zze(com_google_android_gms_internal_zzcsv.zzjua)) && zzctf.equals(zzb(this.zzjub), zzb(com_google_android_gms_internal_zzcsv.zzjub));
    }

    public final String toString() {
        String str;
        StringBuilder stringBuilder = new StringBuilder("ExperimentTokens");
        stringBuilder.append("(");
        if (this.zzjtu == null) {
            str = "null";
        } else {
            str = "'";
            String str2 = this.zzjtu;
            String str3 = "'";
            str = new StringBuilder((String.valueOf(str).length() + String.valueOf(str2).length()) + String.valueOf(str3).length()).append(str).append(str2).append(str3).toString();
        }
        stringBuilder.append(str);
        stringBuilder.append(", ");
        byte[] bArr = this.zzjtv;
        stringBuilder.append("direct");
        stringBuilder.append("=");
        if (bArr == null) {
            stringBuilder.append("null");
        } else {
            stringBuilder.append("'");
            stringBuilder.append(Base64.encodeToString(bArr, 3));
            stringBuilder.append("'");
        }
        stringBuilder.append(", ");
        zza(stringBuilder, "GAIA", this.zzjtw);
        stringBuilder.append(", ");
        zza(stringBuilder, "PSEUDO", this.zzjtx);
        stringBuilder.append(", ");
        zza(stringBuilder, "ALWAYS", this.zzjty);
        stringBuilder.append(", ");
        zza(stringBuilder, "OTHER", this.zzjtz);
        stringBuilder.append(", ");
        zza(stringBuilder, "weak", this.zzjua);
        stringBuilder.append(", ");
        zza(stringBuilder, "directs", this.zzjub);
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zza(parcel, 2, this.zzjtu, false);
        zzbem.zza(parcel, 3, this.zzjtv, false);
        zzbem.zza(parcel, 4, this.zzjtw, false);
        zzbem.zza(parcel, 5, this.zzjtx, false);
        zzbem.zza(parcel, 6, this.zzjty, false);
        zzbem.zza(parcel, 7, this.zzjtz, false);
        zzbem.zza(parcel, 8, this.zzjua, false);
        zzbem.zza(parcel, 9, this.zzjub, false);
        zzbem.zzai(parcel, zze);
    }
}
