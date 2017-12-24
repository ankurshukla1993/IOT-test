package com.google.firebase.auth;

import com.google.firebase.auth.FirebaseAuth.AuthStateListener;

final class zzk implements Runnable {
    private /* synthetic */ FirebaseAuth zzlyy;

    zzk(FirebaseAuth firebaseAuth) {
        this.zzlyy = firebaseAuth;
    }

    public final void run() {
        for (AuthStateListener onAuthStateChanged : this.zzlyy.zzlyp) {
            onAuthStateChanged.onAuthStateChanged(this.zzlyy);
        }
    }
}
