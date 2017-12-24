package com.google.android.gms.internal;

import java.util.List;
import java.util.concurrent.Callable;

final class zzchx implements Callable<List<zzcfi>> {
    private /* synthetic */ String zzijk;
    private /* synthetic */ zzcho zzjdt;
    private /* synthetic */ String zzjdv;
    private /* synthetic */ String zzjdw;

    zzchx(zzcho com_google_android_gms_internal_zzcho, String str, String str2, String str3) {
        this.zzjdt = com_google_android_gms_internal_zzcho;
        this.zzijk = str;
        this.zzjdv = str2;
        this.zzjdw = str3;
    }

    public final /* synthetic */ Object call() throws Exception {
        this.zzjdt.zzitk.zzazz();
        return this.zzjdt.zzitk.zzawg().zzh(this.zzijk, this.zzjdv, this.zzjdw);
    }
}
