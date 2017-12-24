package com.google.android.gms.common.api.internal;

import android.support.annotation.MainThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApiActivity;

final class zzq implements Runnable {
    private final zzp zzflu;
    final /* synthetic */ zzo zzflv;

    zzq(zzo com_google_android_gms_common_api_internal_zzo, zzp com_google_android_gms_common_api_internal_zzp) {
        this.zzflv = com_google_android_gms_common_api_internal_zzo;
        this.zzflu = com_google_android_gms_common_api_internal_zzp;
    }

    @MainThread
    public final void run() {
        if (this.zzflv.mStarted) {
            ConnectionResult zzagt = this.zzflu.zzagt();
            if (zzagt.hasResolution()) {
                this.zzflv.zzfrj.startActivityForResult(GoogleApiActivity.zza(this.zzflv.getActivity(), zzagt.getResolution(), this.zzflu.zzags(), false), 1);
            } else if (this.zzflv.zzfke.isUserResolvableError(zzagt.getErrorCode())) {
                this.zzflv.zzfke.zza(this.zzflv.getActivity(), this.zzflv.zzfrj, zzagt.getErrorCode(), 2, this.zzflv);
            } else if (zzagt.getErrorCode() == 18) {
                GoogleApiAvailability.zza(this.zzflv.getActivity().getApplicationContext(), new zzr(this, GoogleApiAvailability.zza(this.zzflv.getActivity(), this.zzflv)));
            } else {
                this.zzflv.zza(zzagt, this.zzflu.zzags());
            }
        }
    }
}
