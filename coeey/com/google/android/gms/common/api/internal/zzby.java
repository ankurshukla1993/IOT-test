package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.ConnectionResult;
import java.util.Collections;

final class zzby implements Runnable {
    private /* synthetic */ ConnectionResult zzfqy;
    private /* synthetic */ zzbx zzfrb;

    zzby(zzbx com_google_android_gms_common_api_internal_zzbx, ConnectionResult connectionResult) {
        this.zzfrb = com_google_android_gms_common_api_internal_zzbx;
        this.zzfqy = connectionResult;
    }

    public final void run() {
        if (this.zzfqy.isSuccess()) {
            this.zzfrb.zzfra = true;
            if (this.zzfrb.zzfnb.zzaam()) {
                this.zzfrb.zzaiu();
                return;
            } else {
                this.zzfrb.zzfnb.zza(null, Collections.emptySet());
                return;
            }
        }
        ((zzbr) this.zzfrb.zzfqo.zzfne.get(this.zzfrb.zzfjl)).onConnectionFailed(this.zzfqy);
    }
}
