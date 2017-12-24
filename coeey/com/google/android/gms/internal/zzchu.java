package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzchu implements Callable<List<zzckm>> {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ String zzjdv;
    private /* synthetic */ String zzjdw;

    zzchu(zzcho com_google_android_gms_internal_zzcho, zzcff com_google_android_gms_internal_zzcff, String str, String str2) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjds = com_google_android_gms_internal_zzcff;
        this.zzjdv = str;
        this.zzjdw = str2;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzjdt.zzitk.zzazz();
        return this.zzjdt.zzitk.zzawg().zzg(this.zzjds.packageName, this.zzjdv, this.zzjdw);
    }
}
