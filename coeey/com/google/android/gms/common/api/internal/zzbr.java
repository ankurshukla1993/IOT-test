package com.google.android.gms.common.api.internal;

import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbz;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public final class zzbr<O extends ApiOptions> implements ConnectionCallbacks, OnConnectionFailedListener, zzx {
    private final zzh<O> zzfjl;
    private final zze zzfnb;
    private boolean zzfpc;
    final /* synthetic */ zzbp zzfqo;
    private final Queue<zza> zzfqp = new LinkedList();
    private final zzb zzfqq;
    private final zzah zzfqr;
    private final Set<zzj> zzfqs = new HashSet();
    private final Map<zzcn<?>, zzcu> zzfqt = new HashMap();
    private final int zzfqu;
    private final zzcy zzfqv;
    private ConnectionResult zzfqw = null;

    @WorkerThread
    public zzbr(zzbp com_google_android_gms_common_api_internal_zzbp, GoogleApi<O> googleApi) {
        this.zzfqo = com_google_android_gms_common_api_internal_zzbp;
        this.zzfnb = googleApi.zza(com_google_android_gms_common_api_internal_zzbp.mHandler.getLooper(), this);
        if (this.zzfnb instanceof zzbz) {
            this.zzfqq = zzbz.zzalg();
        } else {
            this.zzfqq = this.zzfnb;
        }
        this.zzfjl = googleApi.zzaga();
        this.zzfqr = new zzah();
        this.zzfqu = googleApi.getInstanceId();
        if (this.zzfnb.zzaam()) {
            this.zzfqv = googleApi.zza(com_google_android_gms_common_api_internal_zzbp.mContext, com_google_android_gms_common_api_internal_zzbp.mHandler);
        } else {
            this.zzfqv = null;
        }
    }

    @WorkerThread
    private final void zzaik() {
        zzain();
        zzi(ConnectionResult.zzfhy);
        zzaip();
        for (zzcu com_google_android_gms_common_api_internal_zzcu : this.zzfqt.values()) {
            try {
                com_google_android_gms_common_api_internal_zzcu.zzfkw.zzb(this.zzfqq, new TaskCompletionSource());
            } catch (DeadObjectException e) {
                onConnectionSuspended(1);
                this.zzfnb.disconnect();
            } catch (RemoteException e2) {
            }
        }
        while (this.zzfnb.isConnected() && !this.zzfqp.isEmpty()) {
            zzb((zza) this.zzfqp.remove());
        }
        zzaiq();
    }

    @WorkerThread
    private final void zzail() {
        zzain();
        this.zzfpc = true;
        this.zzfqr.zzahk();
        this.zzfqo.mHandler.sendMessageDelayed(Message.obtain(this.zzfqo.mHandler, 9, this.zzfjl), this.zzfqo.zzfpe);
        this.zzfqo.mHandler.sendMessageDelayed(Message.obtain(this.zzfqo.mHandler, 11, this.zzfjl), this.zzfqo.zzfpd);
        this.zzfqo.zzfqi = -1;
    }

    @WorkerThread
    private final void zzaip() {
        if (this.zzfpc) {
            this.zzfqo.mHandler.removeMessages(11, this.zzfjl);
            this.zzfqo.mHandler.removeMessages(9, this.zzfjl);
            this.zzfpc = false;
        }
    }

    private final void zzaiq() {
        this.zzfqo.mHandler.removeMessages(12, this.zzfjl);
        this.zzfqo.mHandler.sendMessageDelayed(this.zzfqo.mHandler.obtainMessage(12, this.zzfjl), this.zzfqo.zzfqg);
    }

    @WorkerThread
    private final void zzb(zza com_google_android_gms_common_api_internal_zza) {
        com_google_android_gms_common_api_internal_zza.zza(this.zzfqr, zzaam());
        try {
            com_google_android_gms_common_api_internal_zza.zza(this);
        } catch (DeadObjectException e) {
            onConnectionSuspended(1);
            this.zzfnb.disconnect();
        }
    }

    @WorkerThread
    private final void zzi(ConnectionResult connectionResult) {
        for (zzj zza : this.zzfqs) {
            zza.zza(this.zzfjl, connectionResult);
        }
        this.zzfqs.clear();
    }

    @WorkerThread
    public final void connect() {
        zzbq.zza(this.zzfqo.mHandler);
        if (!this.zzfnb.isConnected() && !this.zzfnb.isConnecting()) {
            if (this.zzfnb.zzafu() && this.zzfqo.zzfqi != 0) {
                this.zzfqo.zzfqi = this.zzfqo.zzfke.isGooglePlayServicesAvailable(this.zzfqo.mContext);
                if (this.zzfqo.zzfqi != 0) {
                    onConnectionFailed(new ConnectionResult(this.zzfqo.zzfqi, null));
                    return;
                }
            }
            zzj com_google_android_gms_common_api_internal_zzbx = new zzbx(this.zzfqo, this.zzfnb, this.zzfjl);
            if (this.zzfnb.zzaam()) {
                this.zzfqv.zza(com_google_android_gms_common_api_internal_zzbx);
            }
            this.zzfnb.zza(com_google_android_gms_common_api_internal_zzbx);
        }
    }

    public final int getInstanceId() {
        return this.zzfqu;
    }

    final boolean isConnected() {
        return this.zzfnb.isConnected();
    }

    public final void onConnected(@Nullable Bundle bundle) {
        if (Looper.myLooper() == this.zzfqo.mHandler.getLooper()) {
            zzaik();
        } else {
            this.zzfqo.mHandler.post(new zzbs(this));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    @android.support.annotation.WorkerThread
    public final void onConnectionFailed(@android.support.annotation.NonNull com.google.android.gms.common.ConnectionResult r6) {
        /*
        r5 = this;
        r0 = r5.zzfqo;
        r0 = r0.mHandler;
        com.google.android.gms.common.internal.zzbq.zza(r0);
        r0 = r5.zzfqv;
        if (r0 == 0) goto L_0x0012;
    L_0x000d:
        r0 = r5.zzfqv;
        r0.zzaje();
    L_0x0012:
        r5.zzain();
        r0 = r5.zzfqo;
        r1 = -1;
        r0.zzfqi = r1;
        r5.zzi(r6);
        r0 = r6.getErrorCode();
        r1 = 4;
        if (r0 != r1) goto L_0x002d;
    L_0x0025:
        r0 = com.google.android.gms.common.api.internal.zzbp.zzfqf;
        r5.zzw(r0);
    L_0x002c:
        return;
    L_0x002d:
        r0 = r5.zzfqp;
        r0 = r0.isEmpty();
        if (r0 == 0) goto L_0x0038;
    L_0x0035:
        r5.zzfqw = r6;
        goto L_0x002c;
    L_0x0038:
        r1 = com.google.android.gms.common.api.internal.zzbp.sLock;
        monitor-enter(r1);
        r0 = r5.zzfqo;	 Catch:{ all -> 0x0060 }
        r0 = r0.zzfql;	 Catch:{ all -> 0x0060 }
        if (r0 == 0) goto L_0x0063;
    L_0x0045:
        r0 = r5.zzfqo;	 Catch:{ all -> 0x0060 }
        r0 = r0.zzfqm;	 Catch:{ all -> 0x0060 }
        r2 = r5.zzfjl;	 Catch:{ all -> 0x0060 }
        r0 = r0.contains(r2);	 Catch:{ all -> 0x0060 }
        if (r0 == 0) goto L_0x0063;
    L_0x0053:
        r0 = r5.zzfqo;	 Catch:{ all -> 0x0060 }
        r0 = r0.zzfql;	 Catch:{ all -> 0x0060 }
        r2 = r5.zzfqu;	 Catch:{ all -> 0x0060 }
        r0.zzb(r6, r2);	 Catch:{ all -> 0x0060 }
        monitor-exit(r1);	 Catch:{ all -> 0x0060 }
        goto L_0x002c;
    L_0x0060:
        r0 = move-exception;
        monitor-exit(r1);	 Catch:{ all -> 0x0060 }
        throw r0;
    L_0x0063:
        monitor-exit(r1);	 Catch:{ all -> 0x0060 }
        r0 = r5.zzfqo;
        r1 = r5.zzfqu;
        r0 = r0.zzc(r6, r1);
        if (r0 != 0) goto L_0x002c;
    L_0x006e:
        r0 = r6.getErrorCode();
        r1 = 18;
        if (r0 != r1) goto L_0x0079;
    L_0x0076:
        r0 = 1;
        r5.zzfpc = r0;
    L_0x0079:
        r0 = r5.zzfpc;
        if (r0 == 0) goto L_0x009b;
    L_0x007d:
        r0 = r5.zzfqo;
        r0 = r0.mHandler;
        r1 = r5.zzfqo;
        r1 = r1.mHandler;
        r2 = 9;
        r3 = r5.zzfjl;
        r1 = android.os.Message.obtain(r1, r2, r3);
        r2 = r5.zzfqo;
        r2 = r2.zzfpe;
        r0.sendMessageDelayed(r1, r2);
        goto L_0x002c;
    L_0x009b:
        r0 = new com.google.android.gms.common.api.Status;
        r1 = 17;
        r2 = r5.zzfjl;
        r2 = r2.zzagl();
        r3 = java.lang.String.valueOf(r2);
        r3 = r3.length();
        r3 = r3 + 38;
        r4 = new java.lang.StringBuilder;
        r4.<init>(r3);
        r3 = "API: ";
        r3 = r4.append(r3);
        r2 = r3.append(r2);
        r3 = " is not available on this device.";
        r2 = r2.append(r3);
        r2 = r2.toString();
        r0.<init>(r1, r2);
        r5.zzw(r0);
        goto L_0x002c;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.common.api.internal.zzbr.onConnectionFailed(com.google.android.gms.common.ConnectionResult):void");
    }

    public final void onConnectionSuspended(int i) {
        if (Looper.myLooper() == this.zzfqo.mHandler.getLooper()) {
            zzail();
        } else {
            this.zzfqo.mHandler.post(new zzbt(this));
        }
    }

    @WorkerThread
    public final void resume() {
        zzbq.zza(this.zzfqo.mHandler);
        if (this.zzfpc) {
            connect();
        }
    }

    @WorkerThread
    public final void signOut() {
        zzbq.zza(this.zzfqo.mHandler);
        zzw(zzbp.zzfqe);
        this.zzfqr.zzahj();
        for (zzcn com_google_android_gms_common_api_internal_zzf : (zzcn[]) this.zzfqt.keySet().toArray(new zzcn[this.zzfqt.size()])) {
            zza(new zzf(com_google_android_gms_common_api_internal_zzf, new TaskCompletionSource()));
        }
        zzi(new ConnectionResult(4));
        this.zzfnb.zza(new zzbv(this));
    }

    public final void zza(ConnectionResult connectionResult, Api<?> api, boolean z) {
        if (Looper.myLooper() == this.zzfqo.mHandler.getLooper()) {
            onConnectionFailed(connectionResult);
        } else {
            this.zzfqo.mHandler.post(new zzbu(this, connectionResult));
        }
    }

    @WorkerThread
    public final void zza(zza com_google_android_gms_common_api_internal_zza) {
        zzbq.zza(this.zzfqo.mHandler);
        if (this.zzfnb.isConnected()) {
            zzb(com_google_android_gms_common_api_internal_zza);
            zzaiq();
            return;
        }
        this.zzfqp.add(com_google_android_gms_common_api_internal_zza);
        if (this.zzfqw == null || !this.zzfqw.hasResolution()) {
            connect();
        } else {
            onConnectionFailed(this.zzfqw);
        }
    }

    @WorkerThread
    public final void zza(zzj com_google_android_gms_common_api_internal_zzj) {
        zzbq.zza(this.zzfqo.mHandler);
        this.zzfqs.add(com_google_android_gms_common_api_internal_zzj);
    }

    public final boolean zzaam() {
        return this.zzfnb.zzaam();
    }

    public final zze zzahd() {
        return this.zzfnb;
    }

    @WorkerThread
    public final void zzahx() {
        zzbq.zza(this.zzfqo.mHandler);
        if (this.zzfpc) {
            zzaip();
            zzw(this.zzfqo.zzfke.isGooglePlayServicesAvailable(this.zzfqo.mContext) == 18 ? new Status(8, "Connection timed out while waiting for Google Play services update to complete.") : new Status(8, "API failed to connect while resuming due to an unknown error."));
            this.zzfnb.disconnect();
        }
    }

    public final Map<zzcn<?>, zzcu> zzaim() {
        return this.zzfqt;
    }

    @WorkerThread
    public final void zzain() {
        zzbq.zza(this.zzfqo.mHandler);
        this.zzfqw = null;
    }

    @WorkerThread
    public final ConnectionResult zzaio() {
        zzbq.zza(this.zzfqo.mHandler);
        return this.zzfqw;
    }

    @WorkerThread
    public final void zzair() {
        zzbq.zza(this.zzfqo.mHandler);
        if (!this.zzfnb.isConnected() || this.zzfqt.size() != 0) {
            return;
        }
        if (this.zzfqr.zzahi()) {
            zzaiq();
        } else {
            this.zzfnb.disconnect();
        }
    }

    final zzcwb zzais() {
        return this.zzfqv == null ? null : this.zzfqv.zzais();
    }

    @WorkerThread
    public final void zzh(@NonNull ConnectionResult connectionResult) {
        zzbq.zza(this.zzfqo.mHandler);
        this.zzfnb.disconnect();
        onConnectionFailed(connectionResult);
    }

    @WorkerThread
    public final void zzw(Status status) {
        zzbq.zza(this.zzfqo.mHandler);
        for (zza zzs : this.zzfqp) {
            zzs.zzs(status);
        }
        this.zzfqp.clear();
    }
}
