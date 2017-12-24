package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzd;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

public final class zzche extends zzcii {
    private static final AtomicLong zzjbt = new AtomicLong(Long.MIN_VALUE);
    private ExecutorService zzibs;
    private zzchi zzjbk;
    private zzchi zzjbl;
    private final PriorityBlockingQueue<zzchh<?>> zzjbm = new PriorityBlockingQueue();
    private final BlockingQueue<zzchh<?>> zzjbn = new LinkedBlockingQueue();
    private final UncaughtExceptionHandler zzjbo = new zzchg(this, "Thread death: Uncaught exception on worker thread");
    private final UncaughtExceptionHandler zzjbp = new zzchg(this, "Thread death: Uncaught exception on network thread");
    private final Object zzjbq = new Object();
    private final Semaphore zzjbr = new Semaphore(2);
    private volatile boolean zzjbs;

    zzche(zzchj com_google_android_gms_internal_zzchj) {
        super(com_google_android_gms_internal_zzchj);
    }

    private final void zza(zzchh<?> com_google_android_gms_internal_zzchh_) {
        synchronized (this.zzjbq) {
            this.zzjbm.add(com_google_android_gms_internal_zzchh_);
            if (this.zzjbk == null) {
                this.zzjbk = new zzchi(this, "Measurement Worker", this.zzjbm);
                this.zzjbk.setUncaughtExceptionHandler(this.zzjbo);
                this.zzjbk.start();
            } else {
                this.zzjbk.zzrb();
            }
        }
    }

    public static boolean zzas() {
        return Looper.myLooper() == Looper.getMainLooper();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ void zzavw() {
        super.zzavw();
    }

    public final void zzavx() {
        if (Thread.currentThread() != this.zzjbl) {
            throw new IllegalStateException("Call expected from network thread");
        }
    }

    public final /* bridge */ /* synthetic */ zzcfa zzavy() {
        return super.zzavy();
    }

    public final /* bridge */ /* synthetic */ zzcfh zzavz() {
        return super.zzavz();
    }

    public final /* bridge */ /* synthetic */ zzcik zzawa() {
        return super.zzawa();
    }

    public final /* bridge */ /* synthetic */ zzcge zzawb() {
        return super.zzawb();
    }

    public final /* bridge */ /* synthetic */ zzcfr zzawc() {
        return super.zzawc();
    }

    public final /* bridge */ /* synthetic */ zzcjd zzawd() {
        return super.zzawd();
    }

    public final /* bridge */ /* synthetic */ zzciz zzawe() {
        return super.zzawe();
    }

    public final /* bridge */ /* synthetic */ zzcgf zzawf() {
        return super.zzawf();
    }

    public final /* bridge */ /* synthetic */ zzcfl zzawg() {
        return super.zzawg();
    }

    public final /* bridge */ /* synthetic */ zzcgh zzawh() {
        return super.zzawh();
    }

    public final /* bridge */ /* synthetic */ zzckn zzawi() {
        return super.zzawi();
    }

    public final /* bridge */ /* synthetic */ zzchd zzawj() {
        return super.zzawj();
    }

    public final /* bridge */ /* synthetic */ zzckc zzawk() {
        return super.zzawk();
    }

    public final /* bridge */ /* synthetic */ zzche zzawl() {
        return super.zzawl();
    }

    public final /* bridge */ /* synthetic */ zzcgj zzawm() {
        return super.zzawm();
    }

    public final /* bridge */ /* synthetic */ zzcgu zzawn() {
        return super.zzawn();
    }

    public final /* bridge */ /* synthetic */ zzcfk zzawo() {
        return super.zzawo();
    }

    protected final boolean zzaxn() {
        return false;
    }

    public final boolean zzazg() {
        return Thread.currentThread() == this.zzjbk;
    }

    final ExecutorService zzazh() {
        ExecutorService executorService;
        synchronized (this.zzjbq) {
            if (this.zzibs == null) {
                this.zzibs = new ThreadPoolExecutor(0, 1, 30, TimeUnit.SECONDS, new ArrayBlockingQueue(100));
            }
            executorService = this.zzibs;
        }
        return executorService;
    }

    public final <V> Future<V> zzc(Callable<V> callable) throws IllegalStateException {
        zzwu();
        zzbq.checkNotNull(callable);
        zzchh com_google_android_gms_internal_zzchh = new zzchh(this, (Callable) callable, false, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzjbk) {
            if (!this.zzjbm.isEmpty()) {
                zzawm().zzayt().log("Callable skipped the worker queue.");
            }
            com_google_android_gms_internal_zzchh.run();
        } else {
            zza(com_google_android_gms_internal_zzchh);
        }
        return com_google_android_gms_internal_zzchh;
    }

    public final <V> Future<V> zzd(Callable<V> callable) throws IllegalStateException {
        zzwu();
        zzbq.checkNotNull(callable);
        zzchh com_google_android_gms_internal_zzchh = new zzchh(this, (Callable) callable, true, "Task exception on worker thread");
        if (Thread.currentThread() == this.zzjbk) {
            com_google_android_gms_internal_zzchh.run();
        } else {
            zza(com_google_android_gms_internal_zzchh);
        }
        return com_google_android_gms_internal_zzchh;
    }

    public final void zzg(Runnable runnable) throws IllegalStateException {
        zzwu();
        zzbq.checkNotNull(runnable);
        zza(new zzchh(this, runnable, false, "Task exception on worker thread"));
    }

    public final void zzh(Runnable runnable) throws IllegalStateException {
        zzwu();
        zzbq.checkNotNull(runnable);
        zzchh com_google_android_gms_internal_zzchh = new zzchh(this, runnable, false, "Task exception on network thread");
        synchronized (this.zzjbq) {
            this.zzjbn.add(com_google_android_gms_internal_zzchh);
            if (this.zzjbl == null) {
                this.zzjbl = new zzchi(this, "Measurement Network", this.zzjbn);
                this.zzjbl.setUncaughtExceptionHandler(this.zzjbp);
                this.zzjbl.start();
            } else {
                this.zzjbl.zzrb();
            }
        }
    }

    public final void zzut() {
        if (Thread.currentThread() != this.zzjbk) {
            throw new IllegalStateException("Call expected from worker thread");
        }
    }

    public final /* bridge */ /* synthetic */ zzd zzwh() {
        return super.zzwh();
    }
}
