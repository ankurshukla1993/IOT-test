package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.internal.zzbq;
import com.google.firebase.auth.PhoneAuthProvider.OnVerificationStateChangedCallbacks;

final class zzdvd extends zzdvr<Void, OnVerificationStateChangedCallbacks> {
    private final zzdwo zzmao;

    public zzdvd(zzdwo com_google_android_gms_internal_zzdwo) {
        super(8);
        this.zzmao = (zzdwo) zzbq.checkNotNull(com_google_android_gms_internal_zzdwo);
    }

    public final void dispatch() throws RemoteException {
        this.zzmaw.zza(this.zzmao, this.zzmau);
    }

    public final void zzbpt() {
    }
}
