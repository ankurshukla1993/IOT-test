package com.google.android.gms.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.FacebookAuthCredential;
import com.google.firebase.auth.GithubAuthCredential;
import com.google.firebase.auth.GoogleAuthCredential;
import com.google.firebase.auth.TwitterAuthCredential;
import com.google.firebase.auth.zzd;

public final class zzdwv {
    @NonNull
    public static zzdws zza(@NonNull AuthCredential authCredential) {
        zzbq.checkNotNull(authCredential);
        if (GoogleAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return GoogleAuthCredential.zza((GoogleAuthCredential) authCredential);
        }
        if (FacebookAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return FacebookAuthCredential.zza((FacebookAuthCredential) authCredential);
        }
        if (TwitterAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return TwitterAuthCredential.zza((TwitterAuthCredential) authCredential);
        }
        if (GithubAuthCredential.class.isAssignableFrom(authCredential.getClass())) {
            return GithubAuthCredential.zza((GithubAuthCredential) authCredential);
        }
        if (zzd.class.isAssignableFrom(authCredential.getClass())) {
            return zzd.zza((zzd) authCredential);
        }
        throw new IllegalArgumentException("Unsupported credential type.");
    }
}
