package com.google.firebase.auth;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzdxf;

final class zzm implements zzdxf {
    private /* synthetic */ FirebaseAuth zzlyy;
    private /* synthetic */ FirebaseUser zzlzb;

    zzm(FirebaseAuth firebaseAuth, FirebaseUser firebaseUser) {
        this.zzlyy = firebaseAuth;
        this.zzlzb = firebaseUser;
    }

    public final void onError(Status status) {
        if (status.getStatusCode() == 17011 || status.getStatusCode() == 17021 || status.getStatusCode() == 17005) {
            this.zzlyy.signOut();
        }
    }

    public final void zzbpl() {
        if (this.zzlyy.zzlyr.getUid().equalsIgnoreCase(this.zzlzb.getUid())) {
            this.zzlyy.zzbpk();
        }
    }
}
