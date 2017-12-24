package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth.AuthStateListener;

final class zzi implements Runnable {
    private /* synthetic */ FirebaseAuth zzlyy;
    private /* synthetic */ AuthStateListener zzlyz;

    zzi(FirebaseAuth firebaseAuth, AuthStateListener authStateListener) {
        this.zzlyy = firebaseAuth;
        this.zzlyz = authStateListener;
    }

    public final void run() {
        this.zzlyz.onAuthStateChanged(this.zzlyy);
    }
}
