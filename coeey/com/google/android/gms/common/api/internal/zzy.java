package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.internal.zzcwc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

final class zzy implements zzcf {
    private final Context mContext;
    private final Looper zzakm;
    private final zzbd zzfmo;
    private final zzbl zzfmp;
    private final zzbl zzfmq;
    private final Map<zzc<?>, zzbl> zzfmr;
    private final Set<zzcx> zzfms = Collections.newSetFromMap(new WeakHashMap());
    private final zze zzfmt;
    private Bundle zzfmu;
    private ConnectionResult zzfmv = null;
    private ConnectionResult zzfmw = null;
    private boolean zzfmx = false;
    private final Lock zzfmy;
    private int zzfmz = 0;

    private zzy(Context context, zzbd com_google_android_gms_common_api_internal_zzbd, Lock lock, Looper looper, com.google.android.gms.common.zze com_google_android_gms_common_zze, Map<zzc<?>, zze> map, Map<zzc<?>, zze> map2, zzr com_google_android_gms_common_internal_zzr, zza<? extends zzcwb, zzcwc> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc, zze com_google_android_gms_common_api_Api_zze, ArrayList<zzw> arrayList, ArrayList<zzw> arrayList2, Map<Api<?>, Boolean> map3, Map<Api<?>, Boolean> map4) {
        this.mContext = context;
        this.zzfmo = com_google_android_gms_common_api_internal_zzbd;
        this.zzfmy = lock;
        this.zzakm = looper;
        this.zzfmt = com_google_android_gms_common_api_Api_zze;
        this.zzfmp = new zzbl(context, this.zzfmo, lock, looper, com_google_android_gms_common_zze, map2, null, map4, null, arrayList2, new zzaa());
        this.zzfmq = new zzbl(context, this.zzfmo, lock, looper, com_google_android_gms_common_zze, map, com_google_android_gms_common_internal_zzr, map3, com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc, arrayList, new zzab());
        Map arrayMap = new ArrayMap();
        for (zzc put : map2.keySet()) {
            arrayMap.put(put, this.zzfmp);
        }
        for (zzc put2 : map.keySet()) {
            arrayMap.put(put2, this.zzfmq);
        }
        this.zzfmr = Collections.unmodifiableMap(arrayMap);
    }

    public static zzy zza(Context context, zzbd com_google_android_gms_common_api_internal_zzbd, Lock lock, Looper looper, com.google.android.gms.common.zze com_google_android_gms_common_zze, Map<zzc<?>, zze> map, zzr com_google_android_gms_common_internal_zzr, Map<Api<?>, Boolean> map2, zza<? extends zzcwb, zzcwc> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc, ArrayList<zzw> arrayList) {
        zze com_google_android_gms_common_api_Api_zze = null;
        Map arrayMap = new ArrayMap();
        Map arrayMap2 = new ArrayMap();
        for (Entry entry : map.entrySet()) {
            zze com_google_android_gms_common_api_Api_zze2 = (zze) entry.getValue();
            if (com_google_android_gms_common_api_Api_zze2.zzaaw()) {
                com_google_android_gms_common_api_Api_zze = com_google_android_gms_common_api_Api_zze2;
            }
            if (com_google_android_gms_common_api_Api_zze2.zzaam()) {
                arrayMap.put((zzc) entry.getKey(), com_google_android_gms_common_api_Api_zze2);
            } else {
                arrayMap2.put((zzc) entry.getKey(), com_google_android_gms_common_api_Api_zze2);
            }
        }
        zzbq.zza(!arrayMap.isEmpty(), (Object) "CompositeGoogleApiClient should not be used without any APIs that require sign-in.");
        Map arrayMap3 = new ArrayMap();
        Map arrayMap4 = new ArrayMap();
        for (Api api : map2.keySet()) {
            zzc zzaft = api.zzaft();
            if (arrayMap.containsKey(zzaft)) {
                arrayMap3.put(api, (Boolean) map2.get(api));
            } else if (arrayMap2.containsKey(zzaft)) {
                arrayMap4.put(api, (Boolean) map2.get(api));
            } else {
                throw new IllegalStateException("Each API in the isOptionalMap must have a corresponding client in the clients map.");
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        ArrayList arrayList4 = arrayList;
        int size = arrayList4.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList4.get(i);
            i++;
            zzw com_google_android_gms_common_api_internal_zzw = (zzw) obj;
            if (arrayMap3.containsKey(com_google_android_gms_common_api_internal_zzw.zzffv)) {
                arrayList2.add(com_google_android_gms_common_api_internal_zzw);
            } else if (arrayMap4.containsKey(com_google_android_gms_common_api_internal_zzw.zzffv)) {
                arrayList3.add(com_google_android_gms_common_api_internal_zzw);
            } else {
                throw new IllegalStateException("Each ClientCallbacks must have a corresponding API in the isOptionalMap");
            }
        }
        return new zzy(context, com_google_android_gms_common_api_internal_zzbd, lock, looper, com_google_android_gms_common_zze, arrayMap, arrayMap2, com_google_android_gms_common_internal_zzr, com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc, com_google_android_gms_common_api_Api_zze, arrayList2, arrayList3, arrayMap3, arrayMap4);
    }

    private final void zza(ConnectionResult connectionResult) {
        switch (this.zzfmz) {
            case 1:
                break;
            case 2:
                this.zzfmo.zzc(connectionResult);
                break;
            default:
                Log.wtf("CompositeGAC", "Attempted to call failure callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new Exception());
                break;
        }
        zzaha();
        this.zzfmz = 0;
    }

    private final void zzagz() {
        if (zzb(this.zzfmv)) {
            if (zzb(this.zzfmw) || zzahb()) {
                switch (this.zzfmz) {
                    case 1:
                        break;
                    case 2:
                        this.zzfmo.zzj(this.zzfmu);
                        break;
                    default:
                        Log.wtf("CompositeGAC", "Attempted to call success callbacks in CONNECTION_MODE_NONE. Callbacks should be disabled via GmsClientSupervisor", new AssertionError());
                        break;
                }
                zzaha();
                this.zzfmz = 0;
            } else if (this.zzfmw == null) {
            } else {
                if (this.zzfmz == 1) {
                    zzaha();
                    return;
                }
                zza(this.zzfmw);
                this.zzfmp.disconnect();
            }
        } else if (this.zzfmv != null && zzb(this.zzfmw)) {
            this.zzfmq.disconnect();
            zza(this.zzfmv);
        } else if (this.zzfmv != null && this.zzfmw != null) {
            ConnectionResult connectionResult = this.zzfmv;
            if (this.zzfmq.zzfpz < this.zzfmp.zzfpz) {
                connectionResult = this.zzfmw;
            }
            zza(connectionResult);
        }
    }

    private final void zzaha() {
        for (zzcx zzaav : this.zzfms) {
            zzaav.zzaav();
        }
        this.zzfms.clear();
    }

    private final boolean zzahb() {
        return this.zzfmw != null && this.zzfmw.getErrorCode() == 4;
    }

    @Nullable
    private final PendingIntent zzahc() {
        return this.zzfmt == null ? null : PendingIntent.getActivity(this.mContext, System.identityHashCode(this.zzfmo), this.zzfmt.getSignInIntent(), 134217728);
    }

    private static boolean zzb(ConnectionResult connectionResult) {
        return connectionResult != null && connectionResult.isSuccess();
    }

    private final void zze(int i, boolean z) {
        this.zzfmo.zzf(i, z);
        this.zzfmw = null;
        this.zzfmv = null;
    }

    private final boolean zzf(zzm<? extends Result, ? extends zzb> com_google_android_gms_common_api_internal_zzm__extends_com_google_android_gms_common_api_Result___extends_com_google_android_gms_common_api_Api_zzb) {
        zzc zzaft = com_google_android_gms_common_api_internal_zzm__extends_com_google_android_gms_common_api_Result___extends_com_google_android_gms_common_api_Api_zzb.zzaft();
        zzbq.checkArgument(this.zzfmr.containsKey(zzaft), "GoogleApiClient is not configured to use the API required for this call.");
        return ((zzbl) this.zzfmr.get(zzaft)).equals(this.zzfmq);
    }

    private final void zzi(Bundle bundle) {
        if (this.zzfmu == null) {
            this.zzfmu = bundle;
        } else if (bundle != null) {
            this.zzfmu.putAll(bundle);
        }
    }

    public final ConnectionResult blockingConnect() {
        throw new UnsupportedOperationException();
    }

    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        throw new UnsupportedOperationException();
    }

    public final void connect() {
        this.zzfmz = 2;
        this.zzfmx = false;
        this.zzfmw = null;
        this.zzfmv = null;
        this.zzfmp.connect();
        this.zzfmq.connect();
    }

    public final void disconnect() {
        this.zzfmw = null;
        this.zzfmv = null;
        this.zzfmz = 0;
        this.zzfmp.disconnect();
        this.zzfmq.disconnect();
        zzaha();
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("authClient").println(":");
        this.zzfmq.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
        printWriter.append(str).append("anonClient").println(":");
        this.zzfmp.dump(String.valueOf(str).concat("  "), fileDescriptor, printWriter, strArr);
    }

    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return ((zzbl) this.zzfmr.get(api.zzaft())).equals(this.zzfmq) ? zzahb() ? new ConnectionResult(4, zzahc()) : this.zzfmq.getConnectionResult(api) : this.zzfmp.getConnectionResult(api);
    }

    public final boolean isConnected() {
        boolean z = true;
        this.zzfmy.lock();
        try {
            if (!(this.zzfmp.isConnected() && (this.zzfmq.isConnected() || zzahb() || this.zzfmz == 1))) {
                z = false;
            }
            this.zzfmy.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zzfmy.lock();
        try {
            boolean z = this.zzfmz == 2;
            this.zzfmy.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    public final boolean zza(zzcx com_google_android_gms_common_api_internal_zzcx) {
        this.zzfmy.lock();
        try {
            if ((isConnecting() || isConnected()) && !this.zzfmq.isConnected()) {
                this.zzfms.add(com_google_android_gms_common_api_internal_zzcx);
                if (this.zzfmz == 0) {
                    this.zzfmz = 1;
                }
                this.zzfmw = null;
                this.zzfmq.connect();
                return true;
            }
            this.zzfmy.unlock();
            return false;
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void zzagf() {
        this.zzfmy.lock();
        try {
            boolean isConnecting = isConnecting();
            this.zzfmq.disconnect();
            this.zzfmw = new ConnectionResult(4);
            if (isConnecting) {
                new Handler(this.zzakm).post(new zzz(this));
            } else {
                zzaha();
            }
            this.zzfmy.unlock();
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    public final void zzagy() {
        this.zzfmp.zzagy();
        this.zzfmq.zzagy();
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        if (!zzf((zzm) t)) {
            return this.zzfmp.zzd(t);
        }
        if (!zzahb()) {
            return this.zzfmq.zzd(t);
        }
        t.zzu(new Status(4, null, zzahc()));
        return t;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        if (!zzf((zzm) t)) {
            return this.zzfmp.zze(t);
        }
        if (!zzahb()) {
            return this.zzfmq.zze(t);
        }
        t.zzu(new Status(4, null, zzahc()));
        return t;
    }
}
