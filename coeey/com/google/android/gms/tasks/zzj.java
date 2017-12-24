package com.google.android.gms.tasks;

final class zzj implements Runnable {
    private /* synthetic */ Task zzkrh;
    private /* synthetic */ zzi zzkrp;

    zzj(zzi com_google_android_gms_tasks_zzi, Task task) {
        this.zzkrp = com_google_android_gms_tasks_zzi;
        this.zzkrh = task;
    }

    public final void run() {
        synchronized (this.zzkrp.mLock) {
            if (this.zzkrp.zzkro != null) {
                this.zzkrp.zzkro.onSuccess(this.zzkrh.getResult());
            }
        }
    }
}
