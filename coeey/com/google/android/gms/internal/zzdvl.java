package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;

public final class zzdvl {
    private static zzf<zzdve> zzdyh = new zzf();
    private static final zza<zzdve, zzdvn> zzmar = new zzdvm();
    public static final Api<zzdvn> zzmas = new Api("InternalFirebaseAuth.FIREBASE_AUTH_API", zzmar, zzdyh);

    public static zzdtw zza(Context context, zzdvn com_google_android_gms_internal_zzdvn) {
        return new zzdtw(context, com_google_android_gms_internal_zzdvn);
    }
}
