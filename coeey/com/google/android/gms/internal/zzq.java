package com.google.android.gms.internal;

final class zzq implements Runnable {
    private /* synthetic */ String zzao;
    private /* synthetic */ long zzap;
    private /* synthetic */ zzp zzaq;

    zzq(zzp com_google_android_gms_internal_zzp, String str, long j) {
        this.zzaq = com_google_android_gms_internal_zzp;
        this.zzao = str;
        this.zzap = j;
    }

    public final void run() {
        this.zzaq.zzab.zza(this.zzao, this.zzap);
        this.zzaq.zzab.zzc(toString());
    }
}
