package com.google.android.gms.common.api.internal;

import com.google.android.gms.internal.zzcwo;

final class zzcz implements Runnable {
    private /* synthetic */ zzcwo zzfoz;
    private /* synthetic */ zzcy zzfsb;

    zzcz(zzcy com_google_android_gms_common_api_internal_zzcy, zzcwo com_google_android_gms_internal_zzcwo) {
        this.zzfsb = com_google_android_gms_common_api_internal_zzcy;
        this.zzfoz = com_google_android_gms_internal_zzcwo;
    }

    public final void run() {
        this.zzfsb.zzc(this.zzfoz);
    }
}
