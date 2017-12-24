package com.google.android.gms.internal;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.MainThread;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzbq;

class zzcgs extends BroadcastReceiver {
    private static String zzdvi = zzcgs.class.getName();
    private boolean mRegistered;
    private boolean zzdvj;
    private final zzchj zzitk;

    zzcgs(zzchj com_google_android_gms_internal_zzchj) {
        zzbq.checkNotNull(com_google_android_gms_internal_zzchj);
        this.zzitk = com_google_android_gms_internal_zzchj;
    }

    @MainThread
    public void onReceive(Context context, Intent intent) {
        this.zzitk.zzwu();
        String action = intent.getAction();
        this.zzitk.zzawm().zzayx().zzj("NetworkBroadcastReceiver received action", action);
        if ("android.net.conn.CONNECTIVITY_CHANGE".equals(action)) {
            boolean zzzh = this.zzitk.zzazp().zzzh();
            if (this.zzdvj != zzzh) {
                this.zzdvj = zzzh;
                this.zzitk.zzawl().zzg(new zzcgt(this, zzzh));
                return;
            }
            return;
        }
        this.zzitk.zzawm().zzayt().zzj("NetworkBroadcastReceiver received unknown action", action);
    }

    @WorkerThread
    public final void unregister() {
        this.zzitk.zzwu();
        this.zzitk.zzawl().zzut();
        this.zzitk.zzawl().zzut();
        if (this.mRegistered) {
            this.zzitk.zzawm().zzayx().log("Unregistering connectivity change receiver");
            this.mRegistered = false;
            this.zzdvj = false;
            try {
                this.zzitk.getContext().unregisterReceiver(this);
            } catch (IllegalArgumentException e) {
                this.zzitk.zzawm().zzayr().zzj("Failed to unregister the network broadcast receiver", e);
            }
        }
    }

    @WorkerThread
    public final void zzze() {
        this.zzitk.zzwu();
        this.zzitk.zzawl().zzut();
        if (!this.mRegistered) {
            this.zzitk.getContext().registerReceiver(this, new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE"));
            this.zzdvj = this.zzitk.zzazp().zzzh();
            this.zzitk.zzawm().zzayx().zzj("Registering connectivity change receiver. Network connected", Boolean.valueOf(this.zzdvj));
            this.mRegistered = true;
        }
    }
}
