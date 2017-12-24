package com.google.android.gms.internal;

final class zzcjb implements Runnable {
    private /* synthetic */ zzciz zzjfe;
    private /* synthetic */ zzcjc zzjff;

    zzcjb(zzciz com_google_android_gms_internal_zzciz, zzcjc com_google_android_gms_internal_zzcjc) {
        this.zzjfe = com_google_android_gms_internal_zzciz;
        this.zzjff = com_google_android_gms_internal_zzcjc;
    }

    public final void run() {
        this.zzjfe.zza(this.zzjff);
        this.zzjfe.zzjes = null;
        this.zzjfe.zzawd().zza(null);
    }
}
