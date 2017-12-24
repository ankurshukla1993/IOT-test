package com.google.android.gms.common.internal;

import android.util.Log;

public abstract class zzi<TListener> {
    private TListener zzfrq;
    private /* synthetic */ zzd zzfwg;
    private boolean zzfwh = false;

    public zzi(zzd com_google_android_gms_common_internal_zzd, TListener tListener) {
        this.zzfwg = com_google_android_gms_common_internal_zzd;
        this.zzfrq = tListener;
    }

    public final void removeListener() {
        synchronized (this) {
            this.zzfrq = null;
        }
    }

    public final void unregister() {
        removeListener();
        synchronized (this.zzfwg.zzfvu) {
            this.zzfwg.zzfvu.remove(this);
        }
    }

    public final void zzakg() {
        synchronized (this) {
            Object obj = this.zzfrq;
            if (this.zzfwh) {
                String valueOf = String.valueOf(this);
                Log.w("GmsClient", new StringBuilder(String.valueOf(valueOf).length() + 47).append("Callback proxy ").append(valueOf).append(" being reused. This is not safe.").toString());
            }
        }
        if (obj != null) {
            try {
                zzv(obj);
            } catch (RuntimeException e) {
                throw e;
            }
        }
        synchronized (this) {
            this.zzfwh = true;
        }
        unregister();
    }

    protected abstract void zzv(TListener tListener);
}
