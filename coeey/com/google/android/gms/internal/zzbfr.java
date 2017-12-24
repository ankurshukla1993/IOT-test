package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public final class zzbfr extends zzbej {
    public static final Creator<zzbfr> CREATOR = new zzbfu();
    final String className;
    private int versionCode;
    private ArrayList<zzbfs> zzfzx;

    zzbfr(int i, String str, ArrayList<zzbfs> arrayList) {
        this.versionCode = i;
        this.className = str;
        this.zzfzx = arrayList;
    }

    zzbfr(String str, Map<String, zzbfl<?, ?>> map) {
        ArrayList arrayList;
        this.versionCode = 1;
        this.className = str;
        if (map == null) {
            arrayList = null;
        } else {
            ArrayList arrayList2 = new ArrayList();
            for (String str2 : map.keySet()) {
                arrayList2.add(new zzbfs(str2, (zzbfl) map.get(str2)));
            }
            arrayList = arrayList2;
        }
        this.zzfzx = arrayList;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.versionCode);
        zzbem.zza(parcel, 2, this.className, false);
        zzbem.zzc(parcel, 3, this.zzfzx, false);
        zzbem.zzai(parcel, zze);
    }

    final HashMap<String, zzbfl<?, ?>> zzalo() {
        HashMap<String, zzbfl<?, ?>> hashMap = new HashMap();
        int size = this.zzfzx.size();
        for (int i = 0; i < size; i++) {
            zzbfs com_google_android_gms_internal_zzbfs = (zzbfs) this.zzfzx.get(i);
            hashMap.put(com_google_android_gms_internal_zzbfs.key, com_google_android_gms_internal_zzbfs.zzfzy);
        }
        return hashMap;
    }
}
