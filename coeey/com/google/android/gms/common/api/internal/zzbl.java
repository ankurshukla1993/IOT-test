package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.internal.zzcwc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzbl implements zzcf, zzx {
    private final Context mContext;
    private zza<? extends zzcwb, zzcwc> zzfkf;
    final zzbd zzfmo;
    private final Lock zzfmy;
    private zzr zzfnd;
    private Map<Api<?>, Boolean> zzfng;
    private final zze zzfni;
    final Map<zzc<?>, Api.zze> zzfph;
    private final Condition zzfpu;
    private final zzbn zzfpv;
    final Map<zzc<?>, ConnectionResult> zzfpw = new HashMap();
    private volatile zzbk zzfpx;
    private ConnectionResult zzfpy = null;
    int zzfpz;
    final zzcg zzfqa;

    public zzbl(Context context, zzbd com_google_android_gms_common_api_internal_zzbd, Lock lock, Looper looper, zze com_google_android_gms_common_zze, Map<zzc<?>, Api.zze> map, zzr com_google_android_gms_common_internal_zzr, Map<Api<?>, Boolean> map2, zza<? extends zzcwb, zzcwc> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc, ArrayList<zzw> arrayList, zzcg com_google_android_gms_common_api_internal_zzcg) {
        this.mContext = context;
        this.zzfmy = lock;
        this.zzfni = com_google_android_gms_common_zze;
        this.zzfph = map;
        this.zzfnd = com_google_android_gms_common_internal_zzr;
        this.zzfng = map2;
        this.zzfkf = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc;
        this.zzfmo = com_google_android_gms_common_api_internal_zzbd;
        this.zzfqa = com_google_android_gms_common_api_internal_zzcg;
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            ((zzw) obj).zza(this);
        }
        this.zzfpv = new zzbn(this, looper);
        this.zzfpu = lock.newCondition();
        this.zzfpx = new zzbc(this);
    }

    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzfpu.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzfhy : this.zzfpy != null ? this.zzfpy : new ConnectionResult(13, null);
    }

    public final ConnectionResult blockingConnect(long j, TimeUnit timeUnit) {
        connect();
        long toNanos = timeUnit.toNanos(j);
        while (isConnecting()) {
            if (toNanos <= 0) {
                try {
                    disconnect();
                    return new ConnectionResult(14, null);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return new ConnectionResult(15, null);
                }
            }
            toNanos = this.zzfpu.awaitNanos(toNanos);
        }
        return isConnected() ? ConnectionResult.zzfhy : this.zzfpy != null ? this.zzfpy : new ConnectionResult(13, null);
    }

    public final void connect() {
        this.zzfpx.connect();
    }

    public final void disconnect() {
        if (this.zzfpx.disconnect()) {
            this.zzfpw.clear();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        String concat = String.valueOf(str).concat("  ");
        printWriter.append(str).append("mState=").println(this.zzfpx);
        for (Api api : this.zzfng.keySet()) {
            printWriter.append(str).append(api.getName()).println(":");
            ((Api.zze) this.zzfph.get(api.zzaft())).dump(concat, fileDescriptor, printWriter, strArr);
        }
    }

    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        zzc zzaft = api.zzaft();
        if (this.zzfph.containsKey(zzaft)) {
            if (((Api.zze) this.zzfph.get(zzaft)).isConnected()) {
                return ConnectionResult.zzfhy;
            }
            if (this.zzfpw.containsKey(zzaft)) {
                return (ConnectionResult) this.zzfpw.get(zzaft);
            }
        }
        return null;
    }

    public final boolean isConnected() {
        return this.zzfpx instanceof zzao;
    }

    public final boolean isConnecting() {
        return this.zzfpx instanceof zzar;
    }

    public final void onConnected(@Nullable Bundle bundle) {
        this.zzfmy.lock();
        try {
            this.zzfpx.onConnected(bundle);
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void onConnectionSuspended(int i) {
        this.zzfmy.lock();
        try {
            this.zzfpx.onConnectionSuspended(i);
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void zza(@NonNull ConnectionResult connectionResult, @NonNull Api<?> api, boolean z) {
        this.zzfmy.lock();
        try {
            this.zzfpx.zza(connectionResult, api, z);
        } finally {
            this.zzfmy.unlock();
        }
    }

    final void zza(zzbm com_google_android_gms_common_api_internal_zzbm) {
        this.zzfpv.sendMessage(this.zzfpv.obtainMessage(1, com_google_android_gms_common_api_internal_zzbm));
    }

    final void zza(RuntimeException runtimeException) {
        this.zzfpv.sendMessage(this.zzfpv.obtainMessage(2, runtimeException));
    }

    public final boolean zza(zzcx com_google_android_gms_common_api_internal_zzcx) {
        return false;
    }

    public final void zzagf() {
    }

    public final void zzagy() {
        if (isConnected()) {
            ((zzao) this.zzfpx).zzaho();
        }
    }

    final void zzaib() {
        this.zzfmy.lock();
        try {
            this.zzfpx = new zzar(this, this.zzfnd, this.zzfng, this.zzfni, this.zzfkf, this.zzfmy, this.mContext);
            this.zzfpx.begin();
            this.zzfpu.signalAll();
        } finally {
            this.zzfmy.unlock();
        }
    }

    final void zzaic() {
        this.zzfmy.lock();
        try {
            this.zzfmo.zzahy();
            this.zzfpx = new zzao(this);
            this.zzfpx.begin();
            this.zzfpu.signalAll();
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        t.zzagw();
        return this.zzfpx.zzd(t);
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        t.zzagw();
        return this.zzfpx.zze(t);
    }

    final void zzg(ConnectionResult connectionResult) {
        this.zzfmy.lock();
        try {
            this.zzfpy = connectionResult;
            this.zzfpx = new zzbc(this);
            this.zzfpx.begin();
            this.zzfpu.signalAll();
        } finally {
            this.zzfmy.unlock();
        }
    }
}
