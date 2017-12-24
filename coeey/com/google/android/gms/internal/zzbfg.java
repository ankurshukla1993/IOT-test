package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public final class zzbfg extends zzbej implements zzbfm<String, Integer> {
    public static final Creator<zzbfg> CREATOR = new zzbfi();
    private int zzdzm;
    private final HashMap<String, Integer> zzfzf;
    private final SparseArray<String> zzfzg;
    private final ArrayList<zzbfh> zzfzh;

    public zzbfg() {
        this.zzdzm = 1;
        this.zzfzf = new HashMap();
        this.zzfzg = new SparseArray();
        this.zzfzh = null;
    }

    zzbfg(int i, ArrayList<zzbfh> arrayList) {
        this.zzdzm = i;
        this.zzfzf = new HashMap();
        this.zzfzg = new SparseArray();
        this.zzfzh = null;
        zzd(arrayList);
    }

    private final void zzd(ArrayList<zzbfh> arrayList) {
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzbfh com_google_android_gms_internal_zzbfh = (zzbfh) obj;
            zzi(com_google_android_gms_internal_zzbfh.zzfzi, com_google_android_gms_internal_zzbfh.zzfzj);
        }
    }

    public final /* synthetic */ Object convertBack(Object obj) {
        String str = (String) this.zzfzg.get(((Integer) obj).intValue());
        return (str == null && this.zzfzf.containsKey("gms_unknown")) ? "gms_unknown" : str;
    }

    public final void writeToParcel(Parcel parcel, int i) {
        int zze = zzbem.zze(parcel);
        zzbem.zzc(parcel, 1, this.zzdzm);
        List arrayList = new ArrayList();
        for (String str : this.zzfzf.keySet()) {
            arrayList.add(new zzbfh(str, ((Integer) this.zzfzf.get(str)).intValue()));
        }
        zzbem.zzc(parcel, 2, arrayList, false);
        zzbem.zzai(parcel, zze);
    }

    public final zzbfg zzi(String str, int i) {
        this.zzfzf.put(str, Integer.valueOf(i));
        this.zzfzg.put(i, str);
        return this;
    }
}
