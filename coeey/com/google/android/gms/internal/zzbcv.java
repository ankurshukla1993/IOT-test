package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageManager.NameNotFoundException;
import android.util.Log;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import java.util.ArrayList;

public final class zzbcv {
    @Deprecated
    public static final Api<NoOptions> API = new Api("ClearcutLogger.API", zzdyi, zzdyh);
    private static zzf<zzbdl> zzdyh = new zzf();
    private static zza<zzbdl, NoOptions> zzdyi = new zzbcw();
    private static final zzcsv[] zzfgf = new zzcsv[0];
    private static final String[] zzfgg = new String[0];
    private static final byte[][] zzfgh = new byte[0][];
    private final String packageName;
    private final int zzfgi;
    private String zzfgj;
    private int zzfgk = -1;
    private String zzfgl;
    private String zzfgm;
    private final boolean zzfgn;
    private int zzfgo = 0;
    private final zzbdb zzfgp;
    private final zzd zzfgq;
    private zzbda zzfgr;
    private final zzbcy zzfgs;

    public zzbcv(Context context, int i, String str, String str2, String str3, boolean z, zzbdb com_google_android_gms_internal_zzbdb, zzd com_google_android_gms_common_util_zzd, zzbda com_google_android_gms_internal_zzbda, zzbcy com_google_android_gms_internal_zzbcy) {
        this.packageName = context.getPackageName();
        this.zzfgi = zzbx(context);
        this.zzfgk = -1;
        this.zzfgj = str;
        this.zzfgl = null;
        this.zzfgm = null;
        this.zzfgn = true;
        this.zzfgp = com_google_android_gms_internal_zzbdb;
        this.zzfgq = com_google_android_gms_common_util_zzd;
        this.zzfgr = new zzbda();
        this.zzfgo = 0;
        this.zzfgs = com_google_android_gms_internal_zzbcy;
        zzbq.checkArgument(true, "can't be anonymous with an upload account");
    }

    private static int[] zzb(ArrayList<Integer> arrayList) {
        if (arrayList == null) {
            return null;
        }
        int[] iArr = new int[arrayList.size()];
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        int i2 = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            int intValue = ((Integer) obj).intValue();
            int i3 = i2 + 1;
            iArr[i2] = intValue;
            i2 = i3;
        }
        return iArr;
    }

    private static int zzbx(Context context) {
        int i = 0;
        try {
            return context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionCode;
        } catch (NameNotFoundException e) {
            Log.wtf("ClearcutLogger", "This can't happen.");
            return i;
        }
    }

    public final zzbcx zzh(byte[] bArr) {
        return new zzbcx(this, bArr, null);
    }
}
