package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzcie implements Callable<List<zzckm>> {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcho zzjdt;

    zzcie(zzcho com_google_android_gms_internal_zzcho, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzjdt.zzitk.zzazz();
        return this.zzjdt.zzitk.zzawg().zziu(this.zzjds.packageName);
    }
}
