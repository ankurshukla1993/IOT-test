package com.google.android.gms.common.api.internal;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.google.android.gms.common.internal.zzbq;

final class zzcm extends Handler {
    private /* synthetic */ zzcl zzfrs;

    public zzcm(zzcl com_google_android_gms_common_api_internal_zzcl, Looper looper) {
        this.zzfrs = com_google_android_gms_common_api_internal_zzcl;
        super(looper);
    }

    public final void handleMessage(Message message) {
        boolean z = true;
        if (message.what != 1) {
            z = false;
        }
        zzbq.checkArgument(z);
        this.zzfrs.zzb((zzco) message.obj);
    }
}
