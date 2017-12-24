package com.google.firebase.iid;

import android.util.Log;

final class zzg implements Runnable {
    private /* synthetic */ zzd zzntp;
    private /* synthetic */ zzf zzntq;

    zzg(zzf com_google_firebase_iid_zzf, zzd com_google_firebase_iid_zzd) {
        this.zzntq = com_google_firebase_iid_zzf;
        this.zzntp = com_google_firebase_iid_zzd;
    }

    public final void run() {
        if (Log.isLoggable("EnhancedIntentService", 3)) {
            Log.d("EnhancedIntentService", "bg processing of the intent starting now");
        }
        this.zzntq.zznto.handleIntent(this.zzntp.intent);
        this.zzntp.finish();
    }
}
