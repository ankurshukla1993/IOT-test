package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;

public final class zzm implements zzj {
    private /* synthetic */ zzd zzfwg;

    public zzm(zzd com_google_android_gms_common_internal_zzd) {
        this.zzfwg = com_google_android_gms_common_internal_zzd;
    }

    public final void zzf(@NonNull ConnectionResult connectionResult) {
        if (connectionResult.isSuccess()) {
            this.zzfwg.zza(null, this.zzfwg.zzakd());
        } else if (this.zzfwg.zzfvy != null) {
            this.zzfwg.zzfvy.onConnectionFailed(connectionResult);
        }
    }
}
