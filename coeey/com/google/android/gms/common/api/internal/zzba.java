package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final class zzba implements ConnectionCallbacks, OnConnectionFailedListener {
    private /* synthetic */ zzar zzfor;

    private zzba(zzar com_google_android_gms_common_api_internal_zzar) {
        this.zzfor = com_google_android_gms_common_api_internal_zzar;
    }

    public final void onConnected(Bundle bundle) {
        this.zzfor.zzfoj.zza(new zzay(this.zzfor));
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzfor.zzfmy.lock();
        try {
            if (this.zzfor.zzd(connectionResult)) {
                this.zzfor.zzaht();
                this.zzfor.zzahr();
            } else {
                this.zzfor.zze(connectionResult);
            }
            this.zzfor.zzfmy.unlock();
        } catch (Throwable th) {
            this.zzfor.zzfmy.unlock();
        }
    }

    public final void onConnectionSuspended(int i) {
    }
}
