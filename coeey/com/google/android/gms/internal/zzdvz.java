package com.google.android.gms.internal;

import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

final class zzdvz implements Runnable {
    private /* synthetic */ zzdwa zzmbr;
    private /* synthetic */ zzdvu zzmbs;

    zzdvz(zzdvu com_google_android_gms_internal_zzdvu, zzdwa com_google_android_gms_internal_zzdwa) {
        this.zzmbs = com_google_android_gms_internal_zzdvu;
        this.zzmbr = com_google_android_gms_internal_zzdwa;
    }

    public final void run() {
        synchronized (this.zzmbs.zzmbo.zzmba) {
            if (!this.zzmbs.zzmbo.zzmba.isEmpty()) {
                this.zzmbr.zza((OnVerificationStateChangedCallbacks) this.zzmbs.zzmbo.zzmba.get(0), new Object[0]);
            }
        }
    }
}
