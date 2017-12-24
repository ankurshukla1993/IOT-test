package com.google.android.gms.internal;

import com.google.android.gms.auth.api.proxy.ProxyResponse;

final class zzavo extends zzauw {
    private /* synthetic */ zzavn zzedq;

    zzavo(zzavn com_google_android_gms_internal_zzavn) {
        this.zzedq = com_google_android_gms_internal_zzavn;
    }

    public final void zza(ProxyResponse proxyResponse) {
        this.zzedq.setResult(new zzavp(proxyResponse));
    }
}
