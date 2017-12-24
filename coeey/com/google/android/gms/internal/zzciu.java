package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzciu implements Runnable {
    private /* synthetic */ zzcik zzjeh;
    private /* synthetic */ AtomicReference zzjej;
    private /* synthetic */ boolean zzjek;

    zzciu(zzcik com_google_android_gms_internal_zzcik, AtomicReference atomicReference, boolean z) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
        this.zzjej = atomicReference;
        this.zzjek = z;
    }

    public final void run() {
        this.zzjeh.zzawd().zza(this.zzjej, this.zzjek);
    }
}
