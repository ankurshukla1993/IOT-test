package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.PendingResult.zza;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzaq;
import com.google.android.gms.common.internal.zzbq;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzs<R extends Result> extends PendingResult<R> {
    static final ThreadLocal<Boolean> zzfly = new zzt();
    private Status mStatus;
    private boolean zzaj;
    private final CountDownLatch zzaoi;
    private R zzfkk;
    private final Object zzflz;
    private zzu<R> zzfma;
    private WeakReference<GoogleApiClient> zzfmb;
    private final ArrayList<zza> zzfmc;
    private ResultCallback<? super R> zzfmd;
    private final AtomicReference<zzdo> zzfme;
    private zzv zzfmf;
    private volatile boolean zzfmg;
    private boolean zzfmh;
    private zzaq zzfmi;
    private volatile zzdi<R> zzfmj;
    private boolean zzfmk;

    @Deprecated
    zzs() {
        this.zzflz = new Object();
        this.zzaoi = new CountDownLatch(1);
        this.zzfmc = new ArrayList();
        this.zzfme = new AtomicReference();
        this.zzfmk = false;
        this.zzfma = new zzu(Looper.getMainLooper());
        this.zzfmb = new WeakReference(null);
    }

    @Deprecated
    protected zzs(Looper looper) {
        this.zzflz = new Object();
        this.zzaoi = new CountDownLatch(1);
        this.zzfmc = new ArrayList();
        this.zzfme = new AtomicReference();
        this.zzfmk = false;
        this.zzfma = new zzu(looper);
        this.zzfmb = new WeakReference(null);
    }

    protected zzs(GoogleApiClient googleApiClient) {
        this.zzflz = new Object();
        this.zzaoi = new CountDownLatch(1);
        this.zzfmc = new ArrayList();
        this.zzfme = new AtomicReference();
        this.zzfmk = false;
        this.zzfma = new zzu(googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
        this.zzfmb = new WeakReference(googleApiClient);
    }

    private final R get() {
        R r;
        boolean z = true;
        synchronized (this.zzflz) {
            if (this.zzfmg) {
                z = false;
            }
            zzbq.zza(z, "Result has already been consumed.");
            zzbq.zza(isReady(), "Result is not ready.");
            r = this.zzfkk;
            this.zzfkk = null;
            this.zzfmd = null;
            this.zzfmg = true;
        }
        zzdo com_google_android_gms_common_api_internal_zzdo = (zzdo) this.zzfme.getAndSet(null);
        if (com_google_android_gms_common_api_internal_zzdo != null) {
            com_google_android_gms_common_api_internal_zzdo.zzc(this);
        }
        return r;
    }

    private final void zzc(R r) {
        this.zzfkk = r;
        this.zzfmi = null;
        this.zzaoi.countDown();
        this.mStatus = this.zzfkk.getStatus();
        if (this.zzaj) {
            this.zzfmd = null;
        } else if (this.zzfmd != null) {
            this.zzfma.removeMessages(2);
            this.zzfma.zza(this.zzfmd, get());
        } else if (this.zzfkk instanceof Releasable) {
            this.zzfmf = new zzv(this, null);
        }
        ArrayList arrayList = this.zzfmc;
        int size = arrayList.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList.get(i);
            i++;
            ((zza) obj).zzr(this.mStatus);
        }
        this.zzfmc.clear();
    }

    public static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("BasePendingResult", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    public final R await() {
        boolean z = true;
        zzbq.zza(Looper.myLooper() != Looper.getMainLooper(), "await must not be called on the UI thread");
        zzbq.zza(!this.zzfmg, "Result has already been consumed");
        if (this.zzfmj != null) {
            z = false;
        }
        zzbq.zza(z, "Cannot await if then() has been called.");
        try {
            this.zzaoi.await();
        } catch (InterruptedException e) {
            zzv(Status.zzfkp);
        }
        zzbq.zza(isReady(), "Result is not ready.");
        return get();
    }

    public final R await(long j, TimeUnit timeUnit) {
        boolean z = true;
        boolean z2 = j <= 0 || Looper.myLooper() != Looper.getMainLooper();
        zzbq.zza(z2, "await must not be called on the UI thread when time is greater than zero.");
        zzbq.zza(!this.zzfmg, "Result has already been consumed.");
        if (this.zzfmj != null) {
            z = false;
        }
        zzbq.zza(z, "Cannot await if then() has been called.");
        try {
            if (!this.zzaoi.await(j, timeUnit)) {
                zzv(Status.zzfkr);
            }
        } catch (InterruptedException e) {
            zzv(Status.zzfkp);
        }
        zzbq.zza(isReady(), "Result is not ready.");
        return get();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void cancel() {
        /*
        r2 = this;
        r1 = r2.zzflz;
        monitor-enter(r1);
        r0 = r2.zzaj;	 Catch:{ all -> 0x0029 }
        if (r0 != 0) goto L_0x000b;
    L_0x0007:
        r0 = r2.zzfmg;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x000d;
    L_0x000b:
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
    L_0x000c:
        return;
    L_0x000d:
        r0 = r2.zzfmi;	 Catch:{ all -> 0x0029 }
        if (r0 == 0) goto L_0x0016;
    L_0x0011:
        r0 = r2.zzfmi;	 Catch:{ RemoteException -> 0x002c }
        r0.cancel();	 Catch:{ RemoteException -> 0x002c }
    L_0x0016:
        r0 = r2.zzfkk;	 Catch:{ all -> 0x0029 }
        zzd(r0);	 Catch:{ all -> 0x0029 }
        r0 = 1;
        r2.zzaj = r0;	 Catch:{ all -> 0x0029 }
        r0 = com.google.android.gms.common.api.Status.zzfks;	 Catch:{ all -> 0x0029 }
        r0 = r2.zzb(r0);	 Catch:{ all -> 0x0029 }
        r2.zzc(r0);	 Catch:{ all -> 0x0029 }
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        goto L_0x000c;
    L_0x0029:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0029 }
        throw r0;
    L_0x002c:
        r0 = move-exception;
        goto L_0x0016;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzs.cancel():void");
    }

    public boolean isCanceled() {
        boolean z;
        synchronized (this.zzflz) {
            z = this.zzaj;
        }
        return z;
    }

    public final boolean isReady() {
        return this.zzaoi.getCount() == 0;
    }

    public final void setResult(R r) {
        boolean z = true;
        synchronized (this.zzflz) {
            if (this.zzfmh || this.zzaj) {
                zzd(r);
                return;
            }
            if (isReady()) {
            }
            zzbq.zza(!isReady(), "Results have already been set");
            if (this.zzfmg) {
                z = false;
            }
            zzbq.zza(z, "Result has already been consumed");
            zzc(r);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r6) {
        /*
        r5 = this;
        r0 = 1;
        r1 = 0;
        r3 = r5.zzflz;
        monitor-enter(r3);
        if (r6 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r5.zzfmd = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r5.zzfmg;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzbq.zza(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r5.zzfmj;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzbq.zza(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r5.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r5.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r5.zzfma;	 Catch:{ all -> 0x0027 }
        r1 = r5.get();	 Catch:{ all -> 0x0027 }
        r0.zza(r6, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r5.zzfmd = r6;	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzs.setResultCallback(com.google.android.gms.common.api.ResultCallback):void");
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void setResultCallback(com.google.android.gms.common.api.ResultCallback<? super R> r7, long r8, java.util.concurrent.TimeUnit r10) {
        /*
        r6 = this;
        r0 = 1;
        r1 = 0;
        r3 = r6.zzflz;
        monitor-enter(r3);
        if (r7 != 0) goto L_0x000c;
    L_0x0007:
        r0 = 0;
        r6.zzfmd = r0;	 Catch:{ all -> 0x0027 }
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
    L_0x000b:
        return;
    L_0x000c:
        r2 = r6.zzfmg;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002a;
    L_0x0010:
        r2 = r0;
    L_0x0011:
        r4 = "Result has already been consumed.";
        com.google.android.gms.common.internal.zzbq.zza(r2, r4);	 Catch:{ all -> 0x0027 }
        r2 = r6.zzfmj;	 Catch:{ all -> 0x0027 }
        if (r2 != 0) goto L_0x002c;
    L_0x001a:
        r1 = "Cannot set callbacks if then() has been called.";
        com.google.android.gms.common.internal.zzbq.zza(r0, r1);	 Catch:{ all -> 0x0027 }
        r0 = r6.isCanceled();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x002e;
    L_0x0025:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x0027:
        r0 = move-exception;
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        throw r0;
    L_0x002a:
        r2 = r1;
        goto L_0x0011;
    L_0x002c:
        r0 = r1;
        goto L_0x001a;
    L_0x002e:
        r0 = r6.isReady();	 Catch:{ all -> 0x0027 }
        if (r0 == 0) goto L_0x003f;
    L_0x0034:
        r0 = r6.zzfma;	 Catch:{ all -> 0x0027 }
        r1 = r6.get();	 Catch:{ all -> 0x0027 }
        r0.zza(r7, r1);	 Catch:{ all -> 0x0027 }
    L_0x003d:
        monitor-exit(r3);	 Catch:{ all -> 0x0027 }
        goto L_0x000b;
    L_0x003f:
        r6.zzfmd = r7;	 Catch:{ all -> 0x0027 }
        r0 = r6.zzfma;	 Catch:{ all -> 0x0027 }
        r4 = r10.toMillis(r8);	 Catch:{ all -> 0x0027 }
        r1 = 2;
        r1 = r0.obtainMessage(r1, r6);	 Catch:{ all -> 0x0027 }
        r0.sendMessageDelayed(r1, r4);	 Catch:{ all -> 0x0027 }
        goto L_0x003d;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzs.setResultCallback(com.google.android.gms.common.api.ResultCallback, long, java.util.concurrent.TimeUnit):void");
    }

    public <S extends Result> TransformedResult<S> then(ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult<S> then;
        boolean z = true;
        zzbq.zza(!this.zzfmg, "Result has already been consumed.");
        synchronized (this.zzflz) {
            zzbq.zza(this.zzfmj == null, "Cannot call then() twice.");
            zzbq.zza(this.zzfmd == null, "Cannot call then() if callbacks are set.");
            if (this.zzaj) {
                z = false;
            }
            zzbq.zza(z, "Cannot call then() if result was canceled.");
            this.zzfmk = true;
            this.zzfmj = new zzdi(this.zzfmb);
            then = this.zzfmj.then(resultTransform);
            if (isReady()) {
                this.zzfma.zza(this.zzfmj, get());
            } else {
                this.zzfmd = this.zzfmj;
            }
        }
        return then;
    }

    public final void zza(zza com_google_android_gms_common_api_PendingResult_zza) {
        zzbq.checkArgument(com_google_android_gms_common_api_PendingResult_zza != null, "Callback cannot be null.");
        synchronized (this.zzflz) {
            if (isReady()) {
                com_google_android_gms_common_api_PendingResult_zza.zzr(this.mStatus);
            } else {
                this.zzfmc.add(com_google_android_gms_common_api_PendingResult_zza);
            }
        }
    }

    public final void zza(zzdo com_google_android_gms_common_api_internal_zzdo) {
        this.zzfme.set(com_google_android_gms_common_api_internal_zzdo);
    }

    protected final void zza(zzaq com_google_android_gms_common_internal_zzaq) {
        synchronized (this.zzflz) {
            this.zzfmi = com_google_android_gms_common_internal_zzaq;
        }
    }

    public final Integer zzagi() {
        return null;
    }

    public final boolean zzagv() {
        boolean isCanceled;
        synchronized (this.zzflz) {
            if (((GoogleApiClient) this.zzfmb.get()) == null || !this.zzfmk) {
                cancel();
            }
            isCanceled = isCanceled();
        }
        return isCanceled;
    }

    public final void zzagw() {
        boolean z = this.zzfmk || ((Boolean) zzfly.get()).booleanValue();
        this.zzfmk = z;
    }

    @NonNull
    protected abstract R zzb(Status status);

    public final void zzv(Status status) {
        synchronized (this.zzflz) {
            if (!isReady()) {
                setResult(zzb(status));
                this.zzfmh = true;
            }
        }
    }
}
