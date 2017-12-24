package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.zzdb;

final class zzdts {
    private Context mContext;
    private Api zzffv;
    private zzdb zzfjn;

    zzdts(zzdtp com_google_android_gms_internal_zzdtp, Context context, Api api, zzdb com_google_android_gms_common_api_internal_zzdb) {
        this.mContext = context;
        this.zzffv = api;
        this.zzfjn = com_google_android_gms_common_api_internal_zzdb;
    }

    final GoogleApi<O> zza(zzdtq com_google_android_gms_internal_zzdtq) {
        return new zzdtt(this.mContext, this.zzffv, com_google_android_gms_internal_zzdtq, this.zzfjn);
    }
}
