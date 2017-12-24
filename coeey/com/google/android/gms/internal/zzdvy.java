package com.google.android.gms.internal;

import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

final class zzdvy implements zzdwa {
    private /* synthetic */ Status zzekv;

    zzdvy(zzdvu com_google_android_gms_internal_zzdvu, Status status) {
        this.zzekv = status;
    }

    public final void zza(OnVerificationStateChangedCallbacks onVerificationStateChangedCallbacks, Object... objArr) {
        onVerificationStateChangedCallbacks.onVerificationFailed(zzdvg.zzao(this.zzekv));
    }
}
