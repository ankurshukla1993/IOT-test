package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

final class zzbi extends Handler {
    private /* synthetic */ zzbd zzfpp;

    zzbi(zzbd com_google_android_gms_common_api_internal_zzbd, Looper looper) {
        this.zzfpp = com_google_android_gms_common_api_internal_zzbd;
        super(looper);
    }

    public final void handleMessage(Message message) {
        switch (message.what) {
            case 1:
                this.zzfpp.zzahx();
                return;
            case 2:
                this.zzfpp.resume();
                return;
            default:
                Log.w("GoogleApiClientImpl", "Unknown message id: " + message.what);
                return;
        }
    }
}
