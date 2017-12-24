package com.google.android.gms.internal;

import android.net.Uri;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;

public final class zzctc {
    @Deprecated
    private static Api<NoOptions> API = new Api("Phenotype.API", zzdyi, zzdyh);
    private static final zzf<zzctr> zzdyh = new zzf();
    private static final zza<zzctr, NoOptions> zzdyi = new zzctd();
    @Deprecated
    private static zzcte zzjug = new zzctq();

    public static Uri zzkm(String str) {
        String str2 = "content://com.google.android.gms.phenotype/";
        String valueOf = String.valueOf(Uri.encode(str));
        return Uri.parse(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
    }
}
