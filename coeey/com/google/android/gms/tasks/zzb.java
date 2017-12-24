package com.google.android.gms.tasks;

final class zzb implements Runnable {
    private /* synthetic */ Task zzkrh;
    private /* synthetic */ zza zzkri;

    zzb(zza com_google_android_gms_tasks_zza, Task task) {
        this.zzkri = com_google_android_gms_tasks_zza;
        this.zzkrh = task;
    }

    public final void run() {
        try {
            this.zzkri.zzkrg.setResult(this.zzkri.zzkrf.then(this.zzkrh));
        } catch (Exception e) {
            if (e.getCause() instanceof Exception) {
                this.zzkri.zzkrg.setException((Exception) e.getCause());
            } else {
                this.zzkri.zzkrg.setException(e);
            }
        } catch (Exception e2) {
            this.zzkri.zzkrg.setException(e2);
        }
    }
}
