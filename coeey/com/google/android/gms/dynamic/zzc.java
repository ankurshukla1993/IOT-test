package com.google.android.gms.dynamic;

import android.app.Activity;
import android.os.Bundle;

final class zzc implements zzi {
    private /* synthetic */ Activity val$activity;
    private /* synthetic */ Bundle zzaxh;
    private /* synthetic */ zza zzgtn;
    private /* synthetic */ Bundle zzgto;

    zzc(zza com_google_android_gms_dynamic_zza, Activity activity, Bundle bundle, Bundle bundle2) {
        this.zzgtn = com_google_android_gms_dynamic_zza;
        this.val$activity = activity;
        this.zzgto = bundle;
        this.zzaxh = bundle2;
    }

    public final int getState() {
        return 0;
    }

    public final void zzb(LifecycleDelegate lifecycleDelegate) {
        this.zzgtn.zzgtj.onInflate(this.val$activity, this.zzgto, this.zzaxh);
    }
}
