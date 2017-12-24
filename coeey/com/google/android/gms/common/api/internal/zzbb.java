package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;

abstract class zzbb implements Runnable {
    private /* synthetic */ zzar zzfor;

    private zzbb(zzar com_google_android_gms_common_api_internal_zzar) {
        this.zzfor = com_google_android_gms_common_api_internal_zzar;
    }

    @WorkerThread
    public void run() {
        this.zzfor.zzfmy.lock();
        try {
            if (!Thread.interrupted()) {
                zzahp();
                this.zzfor.zzfmy.unlock();
            }
        } catch (RuntimeException e) {
            this.zzfor.zzfob.zza(e);
        } finally {
            this.zzfor.zzfmy.unlock();
        }
    }

    @WorkerThread
    protected abstract void zzahp();
}
