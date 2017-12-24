package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zza;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.util.zzo;
import com.google.android.gms.common.util.zzp;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzbfv extends zzbfn {
    public static final Creator<zzbfv> CREATOR = new zzbfw();
    private final String mClassName;
    private final int zzdzm;
    private final zzbfq zzfzs;
    private final Parcel zzfzz;
    private final int zzgaa = 2;
    private int zzgab;
    private int zzgac;

    zzbfv(int i, Parcel parcel, zzbfq com_google_android_gms_internal_zzbfq) {
        this.zzdzm = i;
        this.zzfzz = (Parcel) zzbq.checkNotNull(parcel);
        this.zzfzs = com_google_android_gms_internal_zzbfq;
        if (this.zzfzs == null) {
            this.mClassName = null;
        } else {
            this.mClassName = this.zzfzs.zzaln();
        }
        this.zzgab = 2;
    }

    private static void zza(StringBuilder stringBuilder, int i, Object obj) {
        switch (i) {
            case 0:
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                stringBuilder.append(obj);
                return;
            case 7:
                stringBuilder.append("\"").append(zzo.zzgm(obj.toString())).append("\"");
                return;
            case 8:
                stringBuilder.append("\"").append(zzb.zzj((byte[]) obj)).append("\"");
                return;
            case 9:
                stringBuilder.append("\"").append(zzb.zzk((byte[]) obj));
                stringBuilder.append("\"");
                return;
            case 10:
                zzp.zza(stringBuilder, (HashMap) obj);
                return;
            case 11:
                throw new IllegalArgumentException("Method does not accept concrete type.");
            default:
                throw new IllegalArgumentException("Unknown type = " + i);
        }
    }

    private final void zza(StringBuilder stringBuilder, zzbfl<?, ?> com_google_android_gms_internal_zzbfl___, Parcel parcel, int i) {
        double[] dArr = null;
        int i2 = 0;
        int length;
        if (com_google_android_gms_internal_zzbfl___.zzfzn) {
            stringBuilder.append("[");
            int dataPosition;
            switch (com_google_android_gms_internal_zzbfl___.zzfzm) {
                case 0:
                    int[] zzw = zzbek.zzw(parcel, i);
                    length = zzw.length;
                    while (i2 < length) {
                        if (i2 != 0) {
                            stringBuilder.append(",");
                        }
                        stringBuilder.append(Integer.toString(zzw[i2]));
                        i2++;
                    }
                    break;
                case 1:
                    Object[] objArr;
                    length = zzbek.zza(parcel, i);
                    dataPosition = parcel.dataPosition();
                    if (length != 0) {
                        int readInt = parcel.readInt();
                        objArr = new BigInteger[readInt];
                        while (i2 < readInt) {
                            objArr[i2] = new BigInteger(parcel.createByteArray());
                            i2++;
                        }
                        parcel.setDataPosition(length + dataPosition);
                    }
                    zza.zza(stringBuilder, objArr);
                    break;
                case 2:
                    zza.zza(stringBuilder, zzbek.zzx(parcel, i));
                    break;
                case 3:
                    zza.zza(stringBuilder, zzbek.zzy(parcel, i));
                    break;
                case 4:
                    length = zzbek.zza(parcel, i);
                    i2 = parcel.dataPosition();
                    if (length != 0) {
                        dArr = parcel.createDoubleArray();
                        parcel.setDataPosition(length + i2);
                    }
                    zza.zza(stringBuilder, dArr);
                    break;
                case 5:
                    zza.zza(stringBuilder, zzbek.zzz(parcel, i));
                    break;
                case 6:
                    zza.zza(stringBuilder, zzbek.zzv(parcel, i));
                    break;
                case 7:
                    zza.zza(stringBuilder, zzbek.zzaa(parcel, i));
                    break;
                case 8:
                case 9:
                case 10:
                    throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
                case 11:
                    Parcel[] zzae = zzbek.zzae(parcel, i);
                    dataPosition = zzae.length;
                    for (int i3 = 0; i3 < dataPosition; i3++) {
                        if (i3 > 0) {
                            stringBuilder.append(",");
                        }
                        zzae[i3].setDataPosition(0);
                        zza(stringBuilder, com_google_android_gms_internal_zzbfl___.zzall(), zzae[i3]);
                    }
                    break;
                default:
                    throw new IllegalStateException("Unknown field type out.");
            }
            stringBuilder.append("]");
            return;
        }
        switch (com_google_android_gms_internal_zzbfl___.zzfzm) {
            case 0:
                stringBuilder.append(zzbek.zzg(parcel, i));
                return;
            case 1:
                stringBuilder.append(zzbek.zzk(parcel, i));
                return;
            case 2:
                stringBuilder.append(zzbek.zzi(parcel, i));
                return;
            case 3:
                stringBuilder.append(zzbek.zzl(parcel, i));
                return;
            case 4:
                stringBuilder.append(zzbek.zzn(parcel, i));
                return;
            case 5:
                stringBuilder.append(zzbek.zzp(parcel, i));
                return;
            case 6:
                stringBuilder.append(zzbek.zzc(parcel, i));
                return;
            case 7:
                stringBuilder.append("\"").append(zzo.zzgm(zzbek.zzq(parcel, i))).append("\"");
                return;
            case 8:
                stringBuilder.append("\"").append(zzb.zzj(zzbek.zzt(parcel, i))).append("\"");
                return;
            case 9:
                stringBuilder.append("\"").append(zzb.zzk(zzbek.zzt(parcel, i)));
                stringBuilder.append("\"");
                return;
            case 10:
                Bundle zzs = zzbek.zzs(parcel, i);
                Set<String> keySet = zzs.keySet();
                keySet.size();
                stringBuilder.append("{");
                length = 1;
                for (String str : keySet) {
                    if (length == 0) {
                        stringBuilder.append(",");
                    }
                    stringBuilder.append("\"").append(str).append("\"");
                    stringBuilder.append(":");
                    stringBuilder.append("\"").append(zzo.zzgm(zzs.getString(str))).append("\"");
                    length = 0;
                }
                stringBuilder.append("}");
                return;
            case 11:
                Parcel zzad = zzbek.zzad(parcel, i);
                zzad.setDataPosition(0);
                zza(stringBuilder, com_google_android_gms_internal_zzbfl___.zzall(), zzad);
                return;
            default:
                throw new IllegalStateException("Unknown field type out");
        }
    }

    private final void zza(StringBuilder stringBuilder, Map<String, zzbfl<?, ?>> map, Parcel parcel) {
        SparseArray sparseArray = new SparseArray();
        for (Entry entry : map.entrySet()) {
            Entry entry2;
            sparseArray.put(((zzbfl) entry2.getValue()).zzfzp, entry2);
        }
        stringBuilder.append('{');
        int zzd = zzbek.zzd(parcel);
        Object obj = null;
        while (parcel.dataPosition() < zzd) {
            int readInt = parcel.readInt();
            entry2 = (Entry) sparseArray.get(65535 & readInt);
            if (entry2 != null) {
                if (obj != null) {
                    stringBuilder.append(",");
                }
                String str = (String) entry2.getKey();
                zzbfl com_google_android_gms_internal_zzbfl = (zzbfl) entry2.getValue();
                stringBuilder.append("\"").append(str).append("\":");
                if (com_google_android_gms_internal_zzbfl.zzalk()) {
                    switch (com_google_android_gms_internal_zzbfl.zzfzm) {
                        case 0:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, Integer.valueOf(zzbek.zzg(parcel, readInt))));
                            break;
                        case 1:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, zzbek.zzk(parcel, readInt)));
                            break;
                        case 2:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, Long.valueOf(zzbek.zzi(parcel, readInt))));
                            break;
                        case 3:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, Float.valueOf(zzbek.zzl(parcel, readInt))));
                            break;
                        case 4:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, Double.valueOf(zzbek.zzn(parcel, readInt))));
                            break;
                        case 5:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, zzbek.zzp(parcel, readInt)));
                            break;
                        case 6:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, Boolean.valueOf(zzbek.zzc(parcel, readInt))));
                            break;
                        case 7:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, zzbek.zzq(parcel, readInt)));
                            break;
                        case 8:
                        case 9:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, zzbek.zzt(parcel, readInt)));
                            break;
                        case 10:
                            zzb(stringBuilder, com_google_android_gms_internal_zzbfl, zzbfk.zza(com_google_android_gms_internal_zzbfl, zzl(zzbek.zzs(parcel, readInt))));
                            break;
                        case 11:
                            throw new IllegalArgumentException("Method does not accept concrete type.");
                        default:
                            throw new IllegalArgumentException("Unknown field out type = " + com_google_android_gms_internal_zzbfl.zzfzm);
                    }
                }
                zza(stringBuilder, com_google_android_gms_internal_zzbfl, parcel, readInt);
                obj = 1;
            }
        }
        if (parcel.dataPosition() != zzd) {
            throw new zzbel("Overread allowed size end=" + zzd, parcel);
        }
        stringBuilder.append('}');
    }

    private Parcel zzalp() {
        switch (this.zzgab) {
            case 0:
                this.zzgac = zzbem.zze(this.zzfzz);
                break;
            case 1:
                break;
        }
        zzbem.zzai(this.zzfzz, this.zzgac);
        this.zzgab = 2;
        return this.zzfzz;
    }

    private final void zzb(StringBuilder stringBuilder, zzbfl<?, ?> com_google_android_gms_internal_zzbfl___, Object obj) {
        if (com_google_android_gms_internal_zzbfl___.zzfzl) {
            ArrayList arrayList = (ArrayList) obj;
            stringBuilder.append("[");
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                if (i != 0) {
                    stringBuilder.append(",");
                }
                zza(stringBuilder, com_google_android_gms_internal_zzbfl___.zzfzk, arrayList.get(i));
            }
            stringBuilder.append("]");
            return;
        }
        zza(stringBuilder, com_google_android_gms_internal_zzbfl___.zzfzk, obj);
    }

    private static HashMap<String, String> zzl(Bundle bundle) {
        HashMap<String, String> hashMap = new HashMap();
        for (String str : bundle.keySet()) {
            hashMap.put(str, bundle.getString(str));
        }
        return hashMap;
    }

    public String toString() {
        zzbq.checkNotNull(this.zzfzs, "Cannot convert to JSON on client side.");
        Parcel zzalp = zzalp();
        zzalp.setDataPosition(0);
        StringBuilder stringBuilder = new StringBuilder(100);
        zza(stringBuilder, this.zzfzs.zzgl(this.mClassName), zzalp);
        return stringBuilder.toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        Parcelable parcelable;
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        zzbem.zza(parcel, 2, zzalp(), false);
        switch (this.zzgaa) {
            case 0:
                parcelable = null;
                break;
            case 1:
                parcelable = this.zzfzs;
                break;
            case 2:
                parcelable = this.zzfzs;
                break;
            default:
                throw new IllegalStateException("Invalid creation type: " + this.zzgaa);
        }
        zzbem.zza(parcel, 3, parcelable, i, false);
        zzbem.zzai(parcel, zze);
    }

    public final Map<String, zzbfl<?, ?>> zzaaj() {
        return this.zzfzs == null ? null : this.zzfzs.zzgl(this.mClassName);
    }

    public final Object zzgj(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }

    public final boolean zzgk(String str) {
        throw new UnsupportedOperationException("Converting to JSON does not require this method.");
    }
}
