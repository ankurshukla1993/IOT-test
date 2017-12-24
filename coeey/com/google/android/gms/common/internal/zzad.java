package com.google.android.gms.common.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;

final class zzad implements zzg {
    private /* synthetic */ OnConnectionFailedListener zzfxg;

    zzad(OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfxg = onConnectionFailedListener;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzfxg.onConnectionFailed(connectionResult);
    }
}
