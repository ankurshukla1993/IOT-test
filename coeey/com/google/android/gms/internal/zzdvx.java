package com.google.android.gms.internal;

import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

final class zzdvx implements zzdwa {
    private /* synthetic */ String zzmbp;

    zzdvx(zzdvu com_google_android_gms_internal_zzdvu, String str) {
        this.zzmbp = str;
    }

    public final void zza(OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeAutoRetrievalTimeOut(this.zzmbp);
    }
}
