package com.google.android.gms.tasks;

final class zzf implements Runnable {
    private /* synthetic */ Task zzkrh;
    private /* synthetic */ zze zzkrl;

    zzf(zze com_google_android_gms_tasks_zze, Task task) {
        this.zzkrl = com_google_android_gms_tasks_zze;
        this.zzkrh = task;
    }

    public final void run() {
        synchronized (this.zzkrl.mLock) {
            if (this.zzkrl.zzkrk != null) {
                this.zzkrl.zzkrk.onComplete(this.zzkrh);
            }
        }
    }
}
