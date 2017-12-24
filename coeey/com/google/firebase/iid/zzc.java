package com.google.firebase.iid;

import android.content.Intent;

final class zzc implements Runnable {
    private /* synthetic */ Intent val$intent;
    private /* synthetic */ Intent zzibw;
    private /* synthetic */ zzb zzntm;

    zzc(zzb com_google_firebase_iid_zzb, Intent intent, Intent intent2) {
        this.zzntm = com_google_firebase_iid_zzb;
        this.val$intent = intent;
        this.zzibw = intent2;
    }

    public final void run() {
        this.zzntm.handleIntent(this.val$intent);
        this.zzntm.zzh(this.zzibw);
    }
}
