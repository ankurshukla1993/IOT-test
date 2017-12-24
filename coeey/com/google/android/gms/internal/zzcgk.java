package com.google.android.gms.internal;

final class zzcgk implements Runnable {
    private /* synthetic */ String zzizi;
    private /* synthetic */ zzcgj zzizj;

    zzcgk(zzcgj com_google_android_gms_internal_zzcgj, String str) {
        this.zzizj = com_google_android_gms_internal_zzcgj;
        this.zzizi = str;
    }

    public final void run() {
        zzcii zzawn = this.zzizj.zzitk.zzawn();
        if (zzawn.isInitialized()) {
            zzawn.zzizv.zzg(this.zzizi, 1);
        } else {
            this.zzizj.zzk(6, "Persisted config not initialized. Not logging error/warn");
        }
    }
}
