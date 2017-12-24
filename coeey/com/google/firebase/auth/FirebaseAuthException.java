package com.google.firebase.auth;

import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.FirebaseException;

public class FirebaseAuthException extends FirebaseException {
    private final String zzlzc;

    public FirebaseAuthException(@NonNull String str, @NonNull String str2) {
        super(str2);
        this.zzlzc = zzbq.zzgh(str);
    }

    @NonNull
    public String getErrorCode() {
        return this.zzlzc;
    }
}
