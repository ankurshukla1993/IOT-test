package com.google.android.gms.internal;

import android.os.Process;
import com.evernote.android.job.JobRequest;
import com.google.android.gms.common.internal.zzbq;
import java.util.concurrent.BlockingQueue;

final class zzchi extends Thread {
    private /* synthetic */ zzche zzjbv;
    private final Object zzjby = new Object();
    private final BlockingQueue<zzchh<?>> zzjbz;

    public zzchi(zzche com_google_android_gms_internal_zzche, String str, BlockingQueue<zzchh<?>> blockingQueue) {
        this.zzjbv = com_google_android_gms_internal_zzche;
        zzbq.checkNotNull(str);
        zzbq.checkNotNull(blockingQueue);
        this.zzjbz = blockingQueue;
        setName(str);
    }

    private final void zza(InterruptedException interruptedException) {
        this.zzjbv.zzawm().zzayt().zzj(String.valueOf(getName()).concat(" was interrupted"), interruptedException);
    }

    public final void run() {
        Object obj = null;
        while (obj == null) {
            try {
                this.zzjbv.zzjbr.acquire();
                obj = 1;
            } catch (InterruptedException e) {
                zza(e);
            }
        }
        int threadPriority = Process.getThreadPriority(Process.myTid());
        while (true) {
            zzchh com_google_android_gms_internal_zzchh = (zzchh) this.zzjbz.poll();
            if (com_google_android_gms_internal_zzchh != null) {
                Process.setThreadPriority(com_google_android_gms_internal_zzchh.zzjbx ? threadPriority : 10);
                com_google_android_gms_internal_zzchh.run();
            } else {
                try {
                    synchronized (this.zzjby) {
                        if (this.zzjbz.peek() == null && !this.zzjbv.zzjbs) {
                            try {
                                this.zzjby.wait(JobRequest.DEFAULT_BACKOFF_MS);
                            } catch (InterruptedException e2) {
                                zza(e2);
                            }
                        }
                    }
                    synchronized (this.zzjbv.zzjbq) {
                        if (this.zzjbz.peek() == null) {
                            break;
                        }
                    }
                } catch (Throwable th) {
                    synchronized (this.zzjbv.zzjbq) {
                        this.zzjbv.zzjbr.release();
                        this.zzjbv.zzjbq.notifyAll();
                        if (this == this.zzjbv.zzjbk) {
                            this.zzjbv.zzjbk = null;
                        } else if (this == this.zzjbv.zzjbl) {
                            this.zzjbv.zzjbl = null;
                        } else {
                            this.zzjbv.zzawm().zzayr().log("Current scheduler thread is neither worker nor network");
                        }
                    }
                }
            }
        }
        synchronized (this.zzjbv.zzjbq) {
            this.zzjbv.zzjbr.release();
            this.zzjbv.zzjbq.notifyAll();
            if (this == this.zzjbv.zzjbk) {
                this.zzjbv.zzjbk = null;
            } else if (this == this.zzjbv.zzjbl) {
                this.zzjbv.zzjbl = null;
            } else {
                this.zzjbv.zzawm().zzayr().log("Current scheduler thread is neither worker nor network");
            }
        }
    }

    public final void zzrb() {
        synchronized (this.zzjby) {
            this.zzjby.notifyAll();
        }
    }
}
