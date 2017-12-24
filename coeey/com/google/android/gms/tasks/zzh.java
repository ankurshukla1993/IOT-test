package com.google.android.gms.tasks;

final class zzh implements Runnable {
    private /* synthetic */ Task zzkrh;
    private /* synthetic */ zzg zzkrn;

    zzh(zzg com_google_android_gms_tasks_zzg, Task task) {
        this.zzkrn = com_google_android_gms_tasks_zzg;
        this.zzkrh = task;
    }

    public final void run() {
        synchronized (this.zzkrn.mLock) {
            if (this.zzkrn.zzkrm != null) {
                this.zzkrn.zzkrm.onFailure(this.zzkrh.getException());
            }
        }
    }
}
