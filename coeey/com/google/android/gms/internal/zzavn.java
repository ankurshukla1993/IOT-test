package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.common.api.GoogleApiClient;

final class zzavn extends zzavl {
    private /* synthetic */ ProxyRequest zzedp;

    zzavn(zzavm com_google_android_gms_internal_zzavm, GoogleApiClient googleApiClient, ProxyRequest proxyRequest) {
        this.zzedp = proxyRequest;
        super(googleApiClient);
    }

    protected final void zza(Context context, zzava com_google_android_gms_internal_zzava) throws RemoteException {
        com_google_android_gms_internal_zzava.zza(new zzavo(this), this.zzedp);
    }
}
