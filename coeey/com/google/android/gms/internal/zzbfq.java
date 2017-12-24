package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzbfq extends zzbej {
    public static final Creator<zzbfq> CREATOR = new zzbft();
    private int zzdzm;
    private final HashMap<String, Map<String, zzbfl<?, ?>>> zzfzu;
    private final ArrayList<zzbfr> zzfzv = null;
    private final String zzfzw;

    zzbfq(int i, ArrayList<zzbfr> arrayList, String str) {
        this.zzdzm = i;
        HashMap hashMap = new HashMap();
        int size = arrayList.size();
        for (int i2 = 0; i2 < size; i2++) {
            zzbfr com_google_android_gms_internal_zzbfr = (zzbfr) arrayList.get(i2);
            hashMap.put(com_google_android_gms_internal_zzbfr.className, com_google_android_gms_internal_zzbfr.zzalo());
        }
        this.zzfzu = hashMap;
        this.zzfzw = (String) zzbq.checkNotNull(str);
        zzalm();
    }

    private final void zzalm() {
        for (String str : this.zzfzu.keySet()) {
            Map map = (Map) this.zzfzu.get(str);
            for (String str2 : map.keySet()) {
                ((zzbfl) map.get(str2)).zza(this);
            }
        }
    }

    public final String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (String str : this.zzfzu.keySet()) {
            stringBuilder.append(str).append(":\n");
            Map map = (Map) this.zzfzu.get(str);
            for (String str2 : map.keySet()) {
                stringBuilder.append("  ").append(str2).append(": ");
                stringBuilder.append(map.get(str2));
            }
        }
        return stringBuilder.toString();
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        List arrayList = new ArrayList();
        for (String str : this.zzfzu.keySet()) {
            arrayList.add(new zzbfr(str, (Map) this.zzfzu.get(str)));
        }
        zzbem.zzc(parcel, 2, arrayList, false);
        zzbem.zza(parcel, 3, this.zzfzw, false);
        zzbem.zzai(parcel, zze);
    }

    public final String zzaln() {
        return this.zzfzw;
    }

    public final Map<String, zzbfl<?, ?>> zzgl(String str) {
        return (Map) this.zzfzu.get(str);
    }
}
