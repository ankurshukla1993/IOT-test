package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.internal.zzcwc;

public final class zzac<O extends ApiOptions> extends GoogleApi<O> {
    private final zza<? extends zzcwb, zzcwc> zzfkf;
    private final zze zzfnb;
    private final zzw zzfnc;
    private final zzr zzfnd;

    public zzac(@NonNull Context context, Api<O> api, Looper looper, @NonNull zze com_google_android_gms_common_api_Api_zze, @NonNull zzw com_google_android_gms_common_api_internal_zzw, zzr com_google_android_gms_common_internal_zzr, zza<? extends zzcwb, zzcwc> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc) {
        super(context, api, looper);
        this.zzfnb = com_google_android_gms_common_api_Api_zze;
        this.zzfnc = com_google_android_gms_common_api_internal_zzw;
        this.zzfnd = com_google_android_gms_common_internal_zzr;
        this.zzfkf = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc;
        this.zzfjo.zza((GoogleApi) this);
    }

    public final zze zza(Looper looper, zzbr<O> com_google_android_gms_common_api_internal_zzbr_O) {
        this.zzfnc.zza(com_google_android_gms_common_api_internal_zzbr_O);
        return this.zzfnb;
    }

    public final zzcy zza(Context context, Handler handler) {
        return new zzcy(context, handler, this.zzfnd, this.zzfkf);
    }

    public final zze zzahd() {
        return this.zzfnb;
    }
}
