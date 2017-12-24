package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzr;

final class zzcvz extends zza<zzcwl, zzcwc> {
    zzcvz() {
    }

    public final /* synthetic */ zze zza(Context context, Looper looper, zzr com_google_android_gms_common_internal_zzr, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
        zzcwc com_google_android_gms_internal_zzcwc = (zzcwc) obj;
        return new zzcwl(context, looper, true, com_google_android_gms_common_internal_zzr, com_google_android_gms_internal_zzcwc == null ? zzcwc.zzjyz : com_google_android_gms_internal_zzcwc, connectionCallbacks, onConnectionFailedListener);
    }
}
