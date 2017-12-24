package com.google.android.gms.tasks;

final class zzd implements Runnable {
    private /* synthetic */ Task zzkrh;
    private /* synthetic */ zzc zzkrj;

    zzd(zzc com_google_android_gms_tasks_zzc, Task task) {
        this.zzkrj = com_google_android_gms_tasks_zzc;
        this.zzkrh = task;
    }

    public final void run() {
        try {
            Task task = (Task) this.zzkrj.zzkrf.then(this.zzkrh);
            if (task == null) {
                this.zzkrj.onFailure(new NullPointerException("Continuation returned null"));
                return;
            }
            task.addOnSuccessListener(TaskExecutors.zzkrt, this.zzkrj);
            task.addOnFailureListener(TaskExecutors.zzkrt, this.zzkrj);
        } catch (Exception e) {
            if (e.getCause() instanceof Exception) {
                this.zzkrj.zzkrg.setException((Exception) e.getCause());
            } else {
                this.zzkrj.zzkrg.setException(e);
            }
        } catch (Exception e2) {
            this.zzkrj.zzkrg.setException(e2);
        }
    }
}
