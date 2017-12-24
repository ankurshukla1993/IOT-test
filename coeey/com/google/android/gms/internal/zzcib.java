package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzcib implements Callable<byte[]> {
    private /* synthetic */ String zzijk;
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ zzcfx zzjdx;

    zzcib(zzcho com_google_android_gms_internal_zzcho, zzcfx com_google_android_gms_internal_zzcfx, String str) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzjdx = com_google_android_gms_internal_zzcfx;
        this.zzijk = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzjdt.zzitk.zzazz();
        return this.zzjdt.zzitk.zza(this.zzjdx, this.zzijk);
    }
}
