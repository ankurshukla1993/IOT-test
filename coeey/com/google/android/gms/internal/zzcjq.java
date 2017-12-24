package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcjq implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ boolean zzjek;
    private /* synthetic */ zzcjd zzjfo;
    private /* synthetic */ AtomicReference zzjfp;

    zzcjq(zzcjd com_google_android_gms_internal_zzcjd, AtomicReference atomicReference, zzcff com_google_android_gms_internal_zzcff, boolean z) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjfp = atomicReference;
        this.zzjds = com_google_android_gms_internal_zzcff;
        this.zzjek = z;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r5 = this;
        r1 = r5.zzjfp;
        monitor-enter(r1);
        r0 = r5.zzjfo;	 Catch:{ RemoteException -> 0x003d }
        r0 = r0.zzjfi;	 Catch:{ RemoteException -> 0x003d }
        if (r0 != 0) goto L_0x0021;
    L_0x000b:
        r0 = r5.zzjfo;	 Catch:{ RemoteException -> 0x003d }
        r0 = r0.zzawm();	 Catch:{ RemoteException -> 0x003d }
        r0 = r0.zzayr();	 Catch:{ RemoteException -> 0x003d }
        r2 = "Failed to get user properties";
        r0.log(r2);	 Catch:{ RemoteException -> 0x003d }
        r0 = r5.zzjfp;	 Catch:{ all -> 0x003a }
        r0.notify();	 Catch:{ all -> 0x003a }
        monitor-exit(r1);	 Catch:{ all -> 0x003a }
    L_0x0020:
        return;
    L_0x0021:
        r2 = r5.zzjfp;	 Catch:{ RemoteException -> 0x003d }
        r3 = r5.zzjds;	 Catch:{ RemoteException -> 0x003d }
        r4 = r5.zzjek;	 Catch:{ RemoteException -> 0x003d }
        r0 = r0.zza(r3, r4);	 Catch:{ RemoteException -> 0x003d }
        r2.set(r0);	 Catch:{ RemoteException -> 0x003d }
        r0 = r5.zzjfo;	 Catch:{ RemoteException -> 0x003d }
        r0.zzxg();	 Catch:{ RemoteException -> 0x003d }
        r0 = r5.zzjfp;	 Catch:{ all -> 0x003a }
        r0.notify();	 Catch:{ all -> 0x003a }
    L_0x0038:
        monitor-exit(r1);	 Catch:{ all -> 0x003a }
        goto L_0x0020;
    L_0x003a:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x003a }
        throw r0;
    L_0x003d:
        r0 = move-exception;
        r2 = r5.zzjfo;	 Catch:{ all -> 0x0053 }
        r2 = r2.zzawm();	 Catch:{ all -> 0x0053 }
        r2 = r2.zzayr();	 Catch:{ all -> 0x0053 }
        r3 = "Failed to get user properties";
        r2.zzj(r3, r0);	 Catch:{ all -> 0x0053 }
        r0 = r5.zzjfp;	 Catch:{ all -> 0x003a }
        r0.notify();	 Catch:{ all -> 0x003a }
        goto L_0x0038;
    L_0x0053:
        r0 = move-exception;
        r2 = r5.zzjfp;	 Catch:{ all -> 0x003a }
        r2.notify();	 Catch:{ all -> 0x003a }
        throw r0;	 Catch:{ all -> 0x003a }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcjq.run():void");
    }
}
