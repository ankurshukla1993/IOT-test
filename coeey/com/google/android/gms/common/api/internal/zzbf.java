package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import java.util.concurrent.atomic.AtomicReference;

final class zzbf implements ConnectionCallbacks {
    private /* synthetic */ zzbd zzfpp;
    private /* synthetic */ AtomicReference zzfpq;
    private /* synthetic */ zzdc zzfpr;

    zzbf(zzbd com_google_android_gms_common_api_internal_zzbd, AtomicReference atomicReference, zzdc com_google_android_gms_common_api_internal_zzdc) {
        this.zzfpp = com_google_android_gms_common_api_internal_zzbd;
        this.zzfpq = atomicReference;
        this.zzfpr = com_google_android_gms_common_api_internal_zzdc;
    }

    public final void onConnected(Bundle bundle) {
        this.zzfpp.zza((GoogleApiClient) this.zzfpq.get(), this.zzfpr, true);
    }

    public final void onConnectionSuspended(int i) {
    }
}
