package com.google.firebase.iid;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Parcelable;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.util.Log;
import java.io.IOException;

public final class zzad implements Runnable {
    private final long zznvd;
    private final WakeLock zznve = ((PowerManager) getContext().getSystemService("power")).newWakeLock(1, "fiid-sync");
    private FirebaseInstanceId zznvf;

    zzad(FirebaseInstanceId firebaseInstanceId, long j) {
        this.zznvf = firebaseInstanceId;
        this.zznvd = j;
        this.zznve.setReferenceCounted(false);
    }

    private final boolean zzchn() {
        Exception e;
        String str;
        String valueOf;
        zzac zzcgw = this.zznvf.zzcgw();
        if (zzcgw != null && !zzcgw.zzqy(zzi.zzicq)) {
            return true;
        }
        String zzcgx;
        try {
            zzcgx = this.zznvf.zzcgx();
            if (zzcgx == null) {
                Log.e("FirebaseInstanceId", "Token retrieval failed: null");
                return false;
            }
            if (Log.isLoggable("FirebaseInstanceId", 3)) {
                Log.d("FirebaseInstanceId", "Token successfully retrieved");
            }
            if (zzcgw != null && (zzcgw == null || zzcgx.equals(zzcgw.zzlan))) {
                return true;
            }
            Context context = getContext();
            Parcelable intent = new Intent("com.google.firebase.iid.TOKEN_REFRESH");
            Intent intent2 = new Intent("com.google.firebase.INSTANCE_ID_EVENT");
            intent2.setClass(context, FirebaseInstanceIdReceiver.class);
            intent2.putExtra("wrapped_intent", intent);
            context.sendBroadcast(intent2);
            return true;
        } catch (IOException e2) {
            e = e2;
            str = "FirebaseInstanceId";
            zzcgx = "Token retrieval failed: ";
            valueOf = String.valueOf(e.getMessage());
            Log.e(str, valueOf.length() == 0 ? zzcgx.concat(valueOf) : new String(zzcgx));
            return false;
        } catch (SecurityException e3) {
            e = e3;
            str = "FirebaseInstanceId";
            zzcgx = "Token retrieval failed: ";
            valueOf = String.valueOf(e.getMessage());
            if (valueOf.length() == 0) {
            }
            Log.e(str, valueOf.length() == 0 ? zzcgx.concat(valueOf) : new String(zzcgx));
            return false;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zzcho() {
        /*
        r3 = this;
        r0 = com.google.firebase.iid.FirebaseInstanceId.zzcgy();
    L_0x0004:
        r1 = r3.zznvf;
        monitor-enter(r1);
        r2 = r0.zzche();	 Catch:{ all -> 0x0020 }
        if (r2 != 0) goto L_0x0017;
    L_0x000d:
        r0 = "FirebaseInstanceId";
        r2 = "topic sync succeeded";
        android.util.Log.d(r0, r2);	 Catch:{ all -> 0x0020 }
        r0 = 1;
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
    L_0x0016:
        return r0;
    L_0x0017:
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        r1 = r3.zzqz(r2);
        if (r1 != 0) goto L_0x0023;
    L_0x001e:
        r0 = 0;
        goto L_0x0016;
    L_0x0020:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0020 }
        throw r0;
    L_0x0023:
        r0.zzqs(r2);
        goto L_0x0004;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.firebase.iid.zzad.zzcho():boolean");
    }

    private final boolean zzqz(String str) {
        String str2;
        String valueOf;
        String[] split = str.split("!");
        if (split.length != 2) {
            return true;
        }
        String str3 = split[0];
        String str4 = split[1];
        int i = -1;
        try {
            switch (str3.hashCode()) {
                case 83:
                    if (str3.equals("S")) {
                        i = 0;
                        break;
                    }
                    break;
                case 85:
                    if (str3.equals("U")) {
                        boolean z = true;
                        break;
                    }
                    break;
            }
            switch (i) {
                case 0:
                    this.zznvf.zzqq(str4);
                    if (!FirebaseInstanceId.zzcgz()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "subscribe operation succeeded");
                    return true;
                case 1:
                    this.zznvf.zzqr(str4);
                    if (!FirebaseInstanceId.zzcgz()) {
                        return true;
                    }
                    Log.d("FirebaseInstanceId", "unsubscribe operation succeeded");
                    return true;
                default:
                    return true;
            }
        } catch (IOException e) {
            str2 = "FirebaseInstanceId";
            str3 = "Topic sync failed: ";
            valueOf = String.valueOf(e.getMessage());
            Log.e(str2, valueOf.length() == 0 ? new String(str3) : str3.concat(valueOf));
            return false;
        }
        str2 = "FirebaseInstanceId";
        str3 = "Topic sync failed: ";
        valueOf = String.valueOf(e.getMessage());
        if (valueOf.length() == 0) {
        }
        Log.e(str2, valueOf.length() == 0 ? new String(str3) : str3.concat(valueOf));
        return false;
    }

    final Context getContext() {
        return this.zznvf.getApp().getApplicationContext();
    }

    public final void run() {
        this.zznve.acquire();
        try {
            this.zznvf.zzco(true);
            if (!zzw.zzeu(getContext())) {
                this.zznvf.zzco(false);
            } else if (zzchp()) {
                if (zzchn() && zzcho()) {
                    this.zznvf.zzco(false);
                } else {
                    this.zznvf.zzcb(this.zznvd);
                }
                this.zznve.release();
            } else {
                new zzae(this).zzchq();
                this.zznve.release();
            }
        } finally {
            this.zznve.release();
        }
    }

    final boolean zzchp() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService("connectivity");
        return (connectivityManager == null || connectivityManager.getActiveNetworkInfo() == null || !connectivityManager.getActiveNetworkInfo().isConnected()) ? false : true;
    }
}
