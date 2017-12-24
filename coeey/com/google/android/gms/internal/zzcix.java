package com.google.android.gms.internal;

final class zzcix implements Runnable {
    private /* synthetic */ zzcik zzjeh;

    zzcix(zzcik com_google_android_gms_internal_zzcik) {
        this.zzjeh = com_google_android_gms_internal_zzcik;
    }

    public final void run() {
        zzcih com_google_android_gms_internal_zzcih = this.zzjeh;
        com_google_android_gms_internal_zzcih.zzut();
        com_google_android_gms_internal_zzcih.zzwu();
        com_google_android_gms_internal_zzcih.zzawm().zzayw().log("Resetting analytics data (FE)");
        com_google_android_gms_internal_zzcih.zzawd().resetAnalyticsData();
    }
}
