package com.google.android.gms.internal;

import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

final class zzdvw implements zzdwa {
    private /* synthetic */ PhoneAuthCredential zzmbq;

    zzdvw(zzdvu com_google_android_gms_internal_zzdvu, PhoneAuthCredential phoneAuthCredential) {
        this.zzmbq = phoneAuthCredential;
    }

    public final void zza(OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationCompleted(this.zzmbq);
    }
}
