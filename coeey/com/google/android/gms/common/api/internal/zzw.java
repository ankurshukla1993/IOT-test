package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbq;

public final class zzw implements ConnectionCallbacks, OnConnectionFailedListener {
    public final Api<?> zzffv;
    private final boolean zzfmm;
    private zzx zzfmn;

    public zzw(Api<?> api, boolean z) {
        this.zzffv = api;
        this.zzfmm = z;
    }

    private final void zzagx() {
        zzbq.checkNotNull(this.zzfmn, "Callbacks must be attached to a ClientConnectionHelper instance before connecting the client.");
    }

    public final void onConnected(@Nullable Bundle bundle) {
        zzagx();
        this.zzfmn.onConnected(bundle);
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        zzagx();
        this.zzfmn.zza(connectionResult, this.zzffv, this.zzfmm);
    }

    public final void onConnectionSuspended(int i) {
        zzagx();
        this.zzfmn.onConnectionSuspended(i);
    }

    public final void zza(zzx com_google_android_gms_common_api_internal_zzx) {
        this.zzfmn = com_google_android_gms_common_api_internal_zzx;
    }
}
