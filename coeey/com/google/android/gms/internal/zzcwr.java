package com.google.android.gms.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.os.WorkSource;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzu;
import com.google.android.gms.common.util.zzy;
import java.util.HashMap;
import java.util.Map;

public final class zzcwr {
    private static boolean DEBUG = false;
    private static String TAG = "WakeLock";
    private static String zzjzk = "*gcore*:";
    private final Context mContext;
    private final String zzgat;
    private final String zzgav;
    private final WakeLock zzjzl;
    private WorkSource zzjzm;
    private final int zzjzn;
    private final String zzjzo;
    private boolean zzjzp;
    private final Map<String, Integer[]> zzjzq;
    private int zzjzr;

    public zzcwr(Context context, int i, String str) {
        this(context, 1, str, null, context == null ? null : context.getPackageName());
    }

    @SuppressLint({"UnwrappedWakeLock"})
    private zzcwr(Context context, int i, String str, String str2, String str3) {
        this(context, 1, str, null, str3, null);
    }

    @SuppressLint({"UnwrappedWakeLock"})
    private zzcwr(Context context, int i, String str, String str2, String str3, String str4) {
        this.zzjzp = true;
        this.zzjzq = new HashMap();
        zzbq.zzh(str, "Wake lock name can NOT be empty");
        this.zzjzn = i;
        this.zzjzo = null;
        this.zzgav = null;
        this.mContext = context.getApplicationContext();
        if ("com.google.android.gms".equals(context.getPackageName())) {
            this.zzgat = str;
        } else {
            String valueOf = String.valueOf(zzjzk);
            String valueOf2 = String.valueOf(str);
            this.zzgat = valueOf2.length() != 0 ? valueOf.concat(valueOf2) : new String(valueOf);
        }
        this.zzjzl = ((PowerManager) context.getSystemService("power")).newWakeLock(i, str);
        if (zzy.zzcv(this.mContext)) {
            if (zzu.zzgn(str3)) {
                str3 = context.getPackageName();
            }
            this.zzjzm = zzy.zzaa(context, str3);
            WorkSource workSource = this.zzjzm;
            if (workSource != null && zzy.zzcv(this.mContext)) {
                if (this.zzjzm != null) {
                    this.zzjzm.add(workSource);
                } else {
                    this.zzjzm = workSource;
                }
                try {
                    this.zzjzl.setWorkSource(this.zzjzm);
                } catch (IllegalArgumentException e) {
                    Log.wtf(TAG, e.toString());
                }
            }
        }
    }

    private final String zzku(String str) {
        return this.zzjzp ? !TextUtils.isEmpty(null) ? null : this.zzjzo : this.zzjzo;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void acquire(long r13) {
        /*
        r12 = this;
        r10 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        r0 = 0;
        r1 = 1;
        r2 = 0;
        r4 = r12.zzku(r0);
        monitor-enter(r12);
        r0 = r12.zzjzq;	 Catch:{ all -> 0x008a }
        r0 = r0.isEmpty();	 Catch:{ all -> 0x008a }
        if (r0 == 0) goto L_0x0016;
    L_0x0012:
        r0 = r12.zzjzr;	 Catch:{ all -> 0x008a }
        if (r0 <= 0) goto L_0x0026;
    L_0x0016:
        r0 = r12.zzjzl;	 Catch:{ all -> 0x008a }
        r0 = r0.isHeld();	 Catch:{ all -> 0x008a }
        if (r0 != 0) goto L_0x0026;
    L_0x001e:
        r0 = r12.zzjzq;	 Catch:{ all -> 0x008a }
        r0.clear();	 Catch:{ all -> 0x008a }
        r0 = 0;
        r12.zzjzr = r0;	 Catch:{ all -> 0x008a }
    L_0x0026:
        r0 = r12.zzjzp;	 Catch:{ all -> 0x008a }
        if (r0 == 0) goto L_0x0047;
    L_0x002a:
        r0 = r12.zzjzq;	 Catch:{ all -> 0x008a }
        r0 = r0.get(r4);	 Catch:{ all -> 0x008a }
        r0 = (java.lang.Integer[]) r0;	 Catch:{ all -> 0x008a }
        if (r0 != 0) goto L_0x0078;
    L_0x0034:
        r0 = r12.zzjzq;	 Catch:{ all -> 0x008a }
        r2 = 1;
        r2 = new java.lang.Integer[r2];	 Catch:{ all -> 0x008a }
        r3 = 0;
        r5 = 1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x008a }
        r2[r3] = r5;	 Catch:{ all -> 0x008a }
        r0.put(r4, r2);	 Catch:{ all -> 0x008a }
        r0 = r1;
    L_0x0045:
        if (r0 != 0) goto L_0x004f;
    L_0x0047:
        r0 = r12.zzjzp;	 Catch:{ all -> 0x008a }
        if (r0 != 0) goto L_0x0071;
    L_0x004b:
        r0 = r12.zzjzr;	 Catch:{ all -> 0x008a }
        if (r0 != 0) goto L_0x0071;
    L_0x004f:
        com.google.android.gms.common.stats.zze.zzalt();	 Catch:{ all -> 0x008a }
        r0 = r12.mContext;	 Catch:{ all -> 0x008a }
        r1 = r12.zzjzl;	 Catch:{ all -> 0x008a }
        r1 = com.google.android.gms.common.stats.zzc.zza(r1, r4);	 Catch:{ all -> 0x008a }
        r2 = 7;
        r3 = r12.zzgat;	 Catch:{ all -> 0x008a }
        r5 = 0;
        r6 = r12.zzjzn;	 Catch:{ all -> 0x008a }
        r7 = r12.zzjzm;	 Catch:{ all -> 0x008a }
        r7 = com.google.android.gms.common.util.zzy.zzb(r7);	 Catch:{ all -> 0x008a }
        r8 = 1000; // 0x3e8 float:1.401E-42 double:4.94E-321;
        com.google.android.gms.common.stats.zze.zza(r0, r1, r2, r3, r4, r5, r6, r7, r8);	 Catch:{ all -> 0x008a }
        r0 = r12.zzjzr;	 Catch:{ all -> 0x008a }
        r0 = r0 + 1;
        r12.zzjzr = r0;	 Catch:{ all -> 0x008a }
    L_0x0071:
        monitor-exit(r12);	 Catch:{ all -> 0x008a }
        r0 = r12.zzjzl;
        r0.acquire(r10);
        return;
    L_0x0078:
        r1 = 0;
        r3 = 0;
        r3 = r0[r3];	 Catch:{ all -> 0x008a }
        r3 = r3.intValue();	 Catch:{ all -> 0x008a }
        r3 = r3 + 1;
        r3 = java.lang.Integer.valueOf(r3);	 Catch:{ all -> 0x008a }
        r0[r1] = r3;	 Catch:{ all -> 0x008a }
        r0 = r2;
        goto L_0x0045;
    L_0x008a:
        r0 = move-exception;
        monitor-exit(r12);	 Catch:{ all -> 0x008a }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcwr.acquire(long):void");
    }

    public final boolean isHeld() {
        return this.zzjzl.isHeld();
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void release() {
        /*
        r8 = this;
        r0 = 0;
        r2 = 1;
        r1 = 0;
        r4 = r8.zzku(r0);
        monitor-enter(r8);
        r0 = r8.zzjzp;	 Catch:{ all -> 0x006b }
        if (r0 == 0) goto L_0x0019;
    L_0x000c:
        r0 = r8.zzjzq;	 Catch:{ all -> 0x006b }
        r0 = r0.get(r4);	 Catch:{ all -> 0x006b }
        r0 = (java.lang.Integer[]) r0;	 Catch:{ all -> 0x006b }
        if (r0 != 0) goto L_0x0049;
    L_0x0016:
        r0 = r1;
    L_0x0017:
        if (r0 != 0) goto L_0x0021;
    L_0x0019:
        r0 = r8.zzjzp;	 Catch:{ all -> 0x006b }
        if (r0 != 0) goto L_0x0042;
    L_0x001d:
        r0 = r8.zzjzr;	 Catch:{ all -> 0x006b }
        if (r0 != r2) goto L_0x0042;
    L_0x0021:
        com.google.android.gms.common.stats.zze.zzalt();	 Catch:{ all -> 0x006b }
        r0 = r8.mContext;	 Catch:{ all -> 0x006b }
        r1 = r8.zzjzl;	 Catch:{ all -> 0x006b }
        r1 = com.google.android.gms.common.stats.zzc.zza(r1, r4);	 Catch:{ all -> 0x006b }
        r2 = 8;
        r3 = r8.zzgat;	 Catch:{ all -> 0x006b }
        r5 = 0;
        r6 = r8.zzjzn;	 Catch:{ all -> 0x006b }
        r7 = r8.zzjzm;	 Catch:{ all -> 0x006b }
        r7 = com.google.android.gms.common.util.zzy.zzb(r7);	 Catch:{ all -> 0x006b }
        com.google.android.gms.common.stats.zze.zza(r0, r1, r2, r3, r4, r5, r6, r7);	 Catch:{ all -> 0x006b }
        r0 = r8.zzjzr;	 Catch:{ all -> 0x006b }
        r0 = r0 + -1;
        r8.zzjzr = r0;	 Catch:{ all -> 0x006b }
    L_0x0042:
        monitor-exit(r8);	 Catch:{ all -> 0x006b }
        r0 = r8.zzjzl;
        r0.release();
        return;
    L_0x0049:
        r3 = 0;
        r3 = r0[r3];	 Catch:{ all -> 0x006b }
        r3 = r3.intValue();	 Catch:{ all -> 0x006b }
        if (r3 != r2) goto L_0x0059;
    L_0x0052:
        r0 = r8.zzjzq;	 Catch:{ all -> 0x006b }
        r0.remove(r4);	 Catch:{ all -> 0x006b }
        r0 = r2;
        goto L_0x0017;
    L_0x0059:
        r3 = 0;
        r5 = 0;
        r5 = r0[r5];	 Catch:{ all -> 0x006b }
        r5 = r5.intValue();	 Catch:{ all -> 0x006b }
        r5 = r5 + -1;
        r5 = java.lang.Integer.valueOf(r5);	 Catch:{ all -> 0x006b }
        r0[r3] = r5;	 Catch:{ all -> 0x006b }
        r0 = r1;
        goto L_0x0017;
    L_0x006b:
        r0 = move-exception;
        monitor-exit(r8);	 Catch:{ all -> 0x006b }
        throw r0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.zzcwr.release():void");
    }

    public final void setReferenceCounted(boolean z) {
        this.zzjzl.setReferenceCounted(false);
        this.zzjzp = false;
    }
}
