package com.google.android.gms.internal;

import java.util.concurrent.Callable;

final class zzchl implements Callable<String> {
    private /* synthetic */ String zzijk;
    private /* synthetic */ zzchj zzjdm;

    zzchl(zzchj com_google_android_gms_internal_zzchj, String str) {
        this.zzjdm = com_google_android_gms_internal_zzchj;
        this.zzijk = str;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzcfe zziv = this.zzjdm.zzawg().zziv(this.zzijk);
        if (zziv != null) {
            return zziv.getAppInstanceId();
        }
        this.zzjdm.zzawm().zzayt().log("App info was null when attempting to get app instance id");
        return null;
    }
}
