package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;

final class zzbg implements OnConnectionFailedListener {
    private /* synthetic */ zzdc zzfpr;

    zzbg(zzbd com_google_android_gms_common_api_internal_zzbd, zzdc com_google_android_gms_common_api_internal_zzdc) {
        this.zzfpr = com_google_android_gms_common_api_internal_zzdc;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzfpr.setResult(new Status(8));
    }
}
