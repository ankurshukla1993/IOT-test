package com.google.firebase.auth;

import com.google.android.gms.internal.zzeuj;
import com.google.firebase.auth.FirebaseAuth.IdTokenListener;

final class zzj implements Runnable {
    private /* synthetic */ FirebaseAuth zzlyy;
    private /* synthetic */ zzeuj zzlza;

    zzj(FirebaseAuth firebaseAuth, zzeuj com_google_android_gms_internal_zzeuj) {
        this.zzlyy = firebaseAuth;
        this.zzlza = com_google_android_gms_internal_zzeuj;
    }

    public final void run() {
        this.zzlyy.zzlyo.zza(this.zzlza);
        for (IdTokenListener onIdTokenChanged : this.zzlyy.zzlwn) {
            onIdTokenChanged.onIdTokenChanged(this.zzlyy);
        }
    }
}
