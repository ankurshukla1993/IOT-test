package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzciw implements Runnable {
    private /* synthetic */ zzcik zzjeh;
    private /* synthetic */ AtomicReference zzjej;

    zzciw(zzcik com_google_android_gms_internal_zzcik, AtomicReference atomicReference) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.zzjej = atomicReference;
    }

    public final void run() {
        this.zzjeh.zzawd().zza(this.zzjej);
    }
}
