package com.google.android.gms.common.internal;

import android.app.PendingIntent;
import android.os.Bundle;
import android.support.annotation.BinderThread;
import com.google.android.gms.common.ConnectionResult;

abstract class zze extends zzi<Boolean> {
    private int statusCode;
    private Bundle zzfwf;
    private /* synthetic */ zzd zzfwg;

    @BinderThread
    protected zze(zzd com_google_android_gms_common_internal_zzd, int i, Bundle bundle) {
        this.zzfwg = com_google_android_gms_common_internal_zzd;
        super(com_google_android_gms_common_internal_zzd, Boolean.valueOf(true));
        this.statusCode = i;
        this.zzfwf = bundle;
    }

    protected abstract boolean zzakf();

    protected abstract void zzj(ConnectionResult connectionResult);

    protected final /* synthetic */ void zzv(Object obj) {
        PendingIntent pendingIntent = null;
        if (((Boolean) obj) == null) {
            this.zzfwg.zza(1, null);
            return;
        }
        switch (this.statusCode) {
            case 0:
                if (!zzakf()) {
                    this.zzfwg.zza(1, null);
                    zzj(new ConnectionResult(8, null));
                    return;
                }
                return;
            case 10:
                this.zzfwg.zza(1, null);
                throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
            default:
                this.zzfwg.zza(1, null);
                if (this.zzfwf != null) {
                    pendingIntent = (PendingIntent) this.zzfwf.getParcelable("pendingIntent");
                }
                zzj(new ConnectionResult(this.statusCode, pendingIntent));
                return;
        }
    }
}
