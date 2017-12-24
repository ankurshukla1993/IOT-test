package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth.IdTokenListener;

final class zzh implements Runnable {
    private /* synthetic */ IdTokenListener zzlyx;
    private /* synthetic */ FirebaseAuth zzlyy;

    zzh(FirebaseAuth firebaseAuth, IdTokenListener idTokenListener) {
        this.zzlyy = firebaseAuth;
        this.zzlyx = idTokenListener;
    }

    public final void run() {
        this.zzlyx.onIdTokenChanged(this.zzlyy);
    }
}
