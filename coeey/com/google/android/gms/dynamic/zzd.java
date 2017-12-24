package com.google.android.gms.dynamic;

import android.os.Bundle;

final class zzd implements zzi {
    private /* synthetic */ Bundle zzaxh;
    private /* synthetic */ zza zzgtn;

    zzd(zza com_google_android_gms_dynamic_zza, Bundle bundle) {
        this.zzgtn = com_google_android_gms_dynamic_zza;
        this.zzaxh = bundle;
    }

    public final int getState() {
        return 1;
    }

    public final void zzb(LifecycleDelegate lifecycleDelegate) {
        this.zzgtn.zzgtj.onCreate(this.zzaxh);
    }
}
