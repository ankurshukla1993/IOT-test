package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcjg implements Runnable {
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ zzcjd zzjfo;
    private /* synthetic */ AtomicReference zzjfp;

    zzcjg(zzcjd com_google_android_gms_internal_zzcjd, AtomicReference atomicReference, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjfp = atomicReference;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r4 = this;
        r1 = r4.zzjfp;
        monitor-enter(r1);
        r0 = r4.zzjfo;	 Catch:{ RemoteException -> 0x0059 }
        r0 = r0.zzjfi;	 Catch:{ RemoteException -> 0x0059 }
        if (r0 != 0) goto L_0x0021;
    L_0x000b:
        r0 = r4.zzjfo;	 Catch:{ RemoteException -> 0x0059 }
        r0 = r0.zzawm();	 Catch:{ RemoteException -> 0x0059 }
        r0 = r0.zzayr();	 Catch:{ RemoteException -> 0x0059 }
        r2 = "Failed to get app instance id";
        r0.log(r2);	 Catch:{ RemoteException -> 0x0059 }
        r0 = r4.zzjfp;	 Catch:{ all -> 0x0056 }
        r0.notify();	 Catch:{ all -> 0x0056 }
        monitor-exit(r1);	 Catch:{ all -> 0x0056 }
    L_0x0020:
        return;
    L_0x0021:
        r2 = r4.zzjfp;	 Catch:{ RemoteException -> 0x0059 }
        r3 = r4.zzjds;	 Catch:{ RemoteException -> 0x0059 }
        r0 = r0.zzc(r3);	 Catch:{ RemoteException -> 0x0059 }
        r2.set(r0);	 Catch:{ RemoteException -> 0x0059 }
        r0 = r4.zzjfp;	 Catch:{ RemoteException -> 0x0059 }
        r0 = r0.get();	 Catch:{ RemoteException -> 0x0059 }
        r0 = (java.lang.String) r0;	 Catch:{ RemoteException -> 0x0059 }
        if (r0 == 0) goto L_0x004a;
    L_0x0036:
        r2 = r4.zzjfo;	 Catch:{ RemoteException -> 0x0059 }
        r2 = r2.zzawa();	 Catch:{ RemoteException -> 0x0059 }
        r2.zzjj(r0);	 Catch:{ RemoteException -> 0x0059 }
        r2 = r4.zzjfo;	 Catch:{ RemoteException -> 0x0059 }
        r2 = r2.zzawn();	 Catch:{ RemoteException -> 0x0059 }
        r2 = r2.zzjac;	 Catch:{ RemoteException -> 0x0059 }
        r2.zzjk(r0);	 Catch:{ RemoteException -> 0x0059 }
    L_0x004a:
        r0 = r4.zzjfo;	 Catch:{ RemoteException -> 0x0059 }
        r0.zzxg();	 Catch:{ RemoteException -> 0x0059 }
        r0 = r4.zzjfp;	 Catch:{ all -> 0x0056 }
        r0.notify();	 Catch:{ all -> 0x0056 }
    L_0x0054:
        monitor-exit(r1);	 Catch:{ all -> 0x0056 }
        goto L_0x0020;
    L_0x0056:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0056 }
        throw r0;
    L_0x0059:
        r0 = move-exception;
        r2 = r4.zzjfo;	 Catch:{ all -> 0x006f }
        r2 = r2.zzawm();	 Catch:{ all -> 0x006f }
        r2 = r2.zzayr();	 Catch:{ all -> 0x006f }
        r3 = "Failed to get app instance id";
        r2.zzj(r3, r0);	 Catch:{ all -> 0x006f }
        r0 = r4.zzjfp;	 Catch:{ all -> 0x0056 }
        r0.notify();	 Catch:{ all -> 0x0056 }
        goto L_0x0054;
    L_0x006f:
        r0 = move-exception;
        r2 = r4.zzjfp;	 Catch:{ all -> 0x0056 }
        r2.notify();	 Catch:{ all -> 0x0056 }
        throw r0;	 Catch:{ all -> 0x0056 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcjg.run():void");
    }
}
