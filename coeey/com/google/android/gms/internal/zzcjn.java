package com.google.android.gms.internal;

import java.util.concurrent.atomic.AtomicReference;

final class zzcjn implements Runnable {
    private /* synthetic */ String zzijk;
    private /* synthetic */ zzcff zzjds;
    private /* synthetic */ String zzjdv;
    private /* synthetic */ String zzjdw;
    private /* synthetic */ zzcjd zzjfo;
    private /* synthetic */ AtomicReference zzjfp;

    zzcjn(zzcjd com_google_android_gms_internal_zzcjd, AtomicReference atomicReference, String str, String str2, String str3, zzcff com_google_android_gms_internal_zzcff) {
        this.zzjfo = com_google_android_gms_internal_zzcjd;
        this.zzjfp = atomicReference;
        this.zzijk = str;
        this.zzjdv = str2;
        this.zzjdw = str3;
        this.zzjds = com_google_android_gms_internal_zzcff;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void run() {
        /*
        r6 = this;
        r1 = r6.zzjfp;
        monitor-enter(r1);
        r0 = r6.zzjfo;	 Catch:{ RemoteException -> 0x006a }
        r0 = r0.zzjfi;	 Catch:{ RemoteException -> 0x006a }
        if (r0 != 0) goto L_0x0034;
    L_0x000b:
        r0 = r6.zzjfo;	 Catch:{ RemoteException -> 0x006a }
        r0 = r0.zzawm();	 Catch:{ RemoteException -> 0x006a }
        r0 = r0.zzayr();	 Catch:{ RemoteException -> 0x006a }
        r2 = "Failed to get conditional properties";
        r3 = r6.zzijk;	 Catch:{ RemoteException -> 0x006a }
        r3 = com.google.android.gms.internal.zzcgj.zzje(r3);	 Catch:{ RemoteException -> 0x006a }
        r4 = r6.zzjdv;	 Catch:{ RemoteException -> 0x006a }
        r5 = r6.zzjdw;	 Catch:{ RemoteException -> 0x006a }
        r0.zzd(r2, r3, r4, r5);	 Catch:{ RemoteException -> 0x006a }
        r0 = r6.zzjfp;	 Catch:{ RemoteException -> 0x006a }
        r2 = java.util.Collections.emptyList();	 Catch:{ RemoteException -> 0x006a }
        r0.set(r2);	 Catch:{ RemoteException -> 0x006a }
        r0 = r6.zzjfp;	 Catch:{ all -> 0x0057 }
        r0.notify();	 Catch:{ all -> 0x0057 }
        monitor-exit(r1);	 Catch:{ all -> 0x0057 }
    L_0x0033:
        return;
    L_0x0034:
        r2 = r6.zzijk;	 Catch:{ RemoteException -> 0x006a }
        r2 = android.text.TextUtils.isEmpty(r2);	 Catch:{ RemoteException -> 0x006a }
        if (r2 == 0) goto L_0x005a;
    L_0x003c:
        r2 = r6.zzjfp;	 Catch:{ RemoteException -> 0x006a }
        r3 = r6.zzjdv;	 Catch:{ RemoteException -> 0x006a }
        r4 = r6.zzjdw;	 Catch:{ RemoteException -> 0x006a }
        r5 = r6.zzjds;	 Catch:{ RemoteException -> 0x006a }
        r0 = r0.zza(r3, r4, r5);	 Catch:{ RemoteException -> 0x006a }
        r2.set(r0);	 Catch:{ RemoteException -> 0x006a }
    L_0x004b:
        r0 = r6.zzjfo;	 Catch:{ RemoteException -> 0x006a }
        r0.zzxg();	 Catch:{ RemoteException -> 0x006a }
        r0 = r6.zzjfp;	 Catch:{ all -> 0x0057 }
        r0.notify();	 Catch:{ all -> 0x0057 }
    L_0x0055:
        monitor-exit(r1);	 Catch:{ all -> 0x0057 }
        goto L_0x0033;
    L_0x0057:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0057 }
        throw r0;
    L_0x005a:
        r2 = r6.zzjfp;	 Catch:{ RemoteException -> 0x006a }
        r3 = r6.zzijk;	 Catch:{ RemoteException -> 0x006a }
        r4 = r6.zzjdv;	 Catch:{ RemoteException -> 0x006a }
        r5 = r6.zzjdw;	 Catch:{ RemoteException -> 0x006a }
        r0 = r0.zzj(r3, r4, r5);	 Catch:{ RemoteException -> 0x006a }
        r2.set(r0);	 Catch:{ RemoteException -> 0x006a }
        goto L_0x004b;
    L_0x006a:
        r0 = move-exception;
        r2 = r6.zzjfo;	 Catch:{ all -> 0x0091 }
        r2 = r2.zzawm();	 Catch:{ all -> 0x0091 }
        r2 = r2.zzayr();	 Catch:{ all -> 0x0091 }
        r3 = "Failed to get conditional properties";
        r4 = r6.zzijk;	 Catch:{ all -> 0x0091 }
        r4 = com.google.android.gms.internal.zzcgj.zzje(r4);	 Catch:{ all -> 0x0091 }
        r5 = r6.zzjdv;	 Catch:{ all -> 0x0091 }
        r2.zzd(r3, r4, r5, r0);	 Catch:{ all -> 0x0091 }
        r0 = r6.zzjfp;	 Catch:{ all -> 0x0091 }
        r2 = java.util.Collections.emptyList();	 Catch:{ all -> 0x0091 }
        r0.set(r2);	 Catch:{ all -> 0x0091 }
        r0 = r6.zzjfp;	 Catch:{ all -> 0x0057 }
        r0.notify();	 Catch:{ all -> 0x0057 }
        goto L_0x0055;
    L_0x0091:
        r0 = move-exception;
        r2 = r6.zzjfp;	 Catch:{ all -> 0x0057 }
        r2.notify();	 Catch:{ all -> 0x0057 }
        throw r0;	 Catch:{ all -> 0x0057 }
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcjn.run():void");
    }
}
