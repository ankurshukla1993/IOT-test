package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzdwg;
import com.google.android.gms.internal.zzdwu;
import com.google.android.gms.internal.zzdxg;

final class zzl implements zzdwu, zzdxg {
    private /* synthetic */ FirebaseAuth zzlyy;

    zzl(FirebaseAuth firebaseAuth) {
        this.zzlyy = firebaseAuth;
    }

    public final void onError(Status status) {
        int statusCode = status.getStatusCode();
        if (statusCode == 17011 || statusCode == 17021 || statusCode == 17005) {
            this.zzlyy.signOut();
        }
    }

    public final void zza(@NonNull zzdwg com_google_android_gms_internal_zzdwg, @NonNull FirebaseUser firebaseUser) {
        this.zzlyy.zza(firebaseUser, com_google_android_gms_internal_zzdwg, true);
    }
}
