package com.google.android.gms.dynamic;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;

final class zze implements zzi {
    private /* synthetic */ ViewGroup val$container;
    private /* synthetic */ Bundle zzaxh;
    private /* synthetic */ zza zzgtn;
    private /* synthetic */ FrameLayout zzgtp;
    private /* synthetic */ LayoutInflater zzgtq;

    zze(zza com_google_android_gms_dynamic_zza, FrameLayout frameLayout, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.zzgtn = com_google_android_gms_dynamic_zza;
        this.zzgtp = frameLayout;
        this.zzgtq = layoutInflater;
        this.val$container = viewGroup;
        this.zzaxh = bundle;
    }

    public final int getState() {
        return 2;
    }

    public final void zzb(LifecycleDelegate lifecycleDelegate) {
        this.zzgtp.removeAllViews();
        this.zzgtp.addView(this.zzgtn.zzgtj.onCreateView(this.zzgtq, this.val$container, this.zzaxh));
    }
}
