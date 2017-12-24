package com.google.android.gms.common.internal;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.IInterface;

public final class zzl implements ServiceConnection {
    private /* synthetic */ zzd zzfwg;
    private final int zzfwj;

    public zzl(zzd com_google_android_gms_common_internal_zzd, int i) {
        this.zzfwg = com_google_android_gms_common_internal_zzd;
        this.zzfwj = i;
    }

    public final void onServiceConnected(ComponentName componentName, IBinder iBinder) {
        if (iBinder == null) {
            this.zzfwg.zzcf(16);
            return;
        }
        synchronized (this.zzfwg.zzfvq) {
            zzay com_google_android_gms_common_internal_zzay;
            zzd com_google_android_gms_common_internal_zzd = this.zzfwg;
            if (iBinder == null) {
                com_google_android_gms_common_internal_zzay = null;
            } else {
                IInterface queryLocalInterface = iBinder.queryLocalInterface("com.google.android.gms.common.internal.IGmsServiceBroker");
                com_google_android_gms_common_internal_zzay = (queryLocalInterface == null || !(queryLocalInterface instanceof zzay)) ? new zzaz(iBinder) : (zzay) queryLocalInterface;
            }
            com_google_android_gms_common_internal_zzd.zzfvr = com_google_android_gms_common_internal_zzay;
        }
        this.zzfwg.zza(0, null, this.zzfwj);
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        synchronized (this.zzfwg.zzfvq) {
            this.zzfwg.zzfvr = null;
        }
        this.zzfwg.mHandler.sendMessage(this.zzfwg.mHandler.obtainMessage(6, this.zzfwj, 1));
    }
}
