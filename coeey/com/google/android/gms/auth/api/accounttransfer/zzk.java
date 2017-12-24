package com.google.android.gms.auth.api.accounttransfer;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzatl;

final class zzk extends zzatl {
    private /* synthetic */ zzc zzeba;

    zzk(zzc com_google_android_gms_auth_api_accounttransfer_AccountTransferClient_zzc) {
        this.zzeba = com_google_android_gms_auth_api_accounttransfer_AccountTransferClient_zzc;
    }

    public final void onFailure(Status status) {
        this.zzeba.zzd(status);
    }

    public final void zzaai() {
        this.zzeba.setResult(null);
    }
}
