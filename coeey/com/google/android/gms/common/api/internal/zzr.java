package com.google.android.gms.common.api.internal;

import android.app.Dialog;

final class zzr extends zzcb {
    private /* synthetic */ Dialog zzflw;
    private /* synthetic */ zzq zzflx;

    zzr(zzq com_google_android_gms_common_api_internal_zzq, Dialog dialog) {
        this.zzflx = com_google_android_gms_common_api_internal_zzq;
        this.zzflw = dialog;
    }

    public final void zzagu() {
        this.zzflx.zzflv.zzagr();
        if (this.zzflw.isShowing()) {
            this.zzflw.dismiss();
        }
    }
}
