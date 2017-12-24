package com.google.android.gms.internal;

import com.google.firebase.auth.PhoneAuthProvider.ForceResendingToken;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

final class zzdvv implements zzdwa {
    private /* synthetic */ String zzmbp;

    zzdvv(zzdvu com_google_android_gms_internal_zzdvu, String str) {
        this.zzmbp = str;
    }

    public final void zza(OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onCodeSent(this.zzmbp, ForceResendingToken.zzbpq());
    }
}
