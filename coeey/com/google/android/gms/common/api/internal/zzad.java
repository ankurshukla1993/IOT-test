package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzbfx;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.internal.zzcwc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public final class zzad implements zzcf {
    private final Looper zzakm;
    private final zzbp zzfjo;
    private final Lock zzfmy;
    private final zzr zzfnd;
    private final Map<zzc<?>, zzac<?>> zzfne = new HashMap();
    private final Map<zzc<?>, zzac<?>> zzfnf = new HashMap();
    private final Map<Api<?>, Boolean> zzfng;
    private final zzbd zzfnh;
    private final zze zzfni;
    private final Condition zzfnj;
    private final boolean zzfnk;
    private final boolean zzfnl;
    private final Queue<zzm<?, ?>> zzfnm = new LinkedList();
    private boolean zzfnn;
    private Map<zzh<?>, ConnectionResult> zzfno;
    private Map<zzh<?>, ConnectionResult> zzfnp;
    private zzag zzfnq;
    private ConnectionResult zzfnr;

    public zzad(Context context, Lock lock, Looper looper, zze com_google_android_gms_common_zze, Map<zzc<?>, Api.zze> map, zzr com_google_android_gms_common_internal_zzr, Map<Api<?>, Boolean> map2, zza<? extends zzcwb, zzcwc> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc, ArrayList<zzw> arrayList, zzbd com_google_android_gms_common_api_internal_zzbd, boolean z) {
        this.zzfmy = lock;
        this.zzakm = looper;
        this.zzfnj = lock.newCondition();
        this.zzfni = com_google_android_gms_common_zze;
        this.zzfnh = com_google_android_gms_common_api_internal_zzbd;
        this.zzfng = map2;
        this.zzfnd = com_google_android_gms_common_internal_zzr;
        this.zzfnk = z;
        Map hashMap = new HashMap();
        for (Api api : map2.keySet()) {
            hashMap.put(api.zzaft(), api);
        }
        Map hashMap2 = new HashMap();
        ArrayList arrayList2 = arrayList;
        int size = arrayList2.size();
        int i = 0;
        while (i < size) {
            Object obj = arrayList2.get(i);
            i++;
            zzw com_google_android_gms_common_api_internal_zzw = (zzw) obj;
            hashMap2.put(com_google_android_gms_common_api_internal_zzw.zzffv, com_google_android_gms_common_api_internal_zzw);
        }
        Object obj2 = 1;
        Object obj3 = null;
        Object obj4 = null;
        for (Entry entry : map.entrySet()) {
            Object obj5;
            Object obj6;
            Object obj7;
            Api api2 = (Api) hashMap.get(entry.getKey());
            Api.zze com_google_android_gms_common_api_Api_zze = (Api.zze) entry.getValue();
            if (com_google_android_gms_common_api_Api_zze.zzafu()) {
                obj5 = 1;
                if (((Boolean) this.zzfng.get(api2)).booleanValue()) {
                    obj6 = obj2;
                    obj7 = obj3;
                } else {
                    obj6 = obj2;
                    obj7 = 1;
                }
            } else {
                obj5 = obj4;
                obj6 = null;
                obj7 = obj3;
            }
            zzac com_google_android_gms_common_api_internal_zzac = new zzac(context, api2, looper, com_google_android_gms_common_api_Api_zze, (zzw) hashMap2.get(api2), com_google_android_gms_common_internal_zzr, com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc);
            this.zzfne.put((zzc) entry.getKey(), com_google_android_gms_common_api_internal_zzac);
            if (com_google_android_gms_common_api_Api_zze.zzaam()) {
                this.zzfnf.put((zzc) entry.getKey(), com_google_android_gms_common_api_internal_zzac);
            }
            obj4 = obj5;
            obj2 = obj6;
            obj3 = obj7;
        }
        boolean z2 = obj4 != null && obj2 == null && obj3 == null;
        this.zzfnl = z2;
        this.zzfjo = zzbp.zzaie();
    }

    private final boolean zza(zzac<?> com_google_android_gms_common_api_internal_zzac_, ConnectionResult connectionResult) {
        return !connectionResult.isSuccess() && !connectionResult.hasResolution() && ((Boolean) this.zzfng.get(com_google_android_gms_common_api_internal_zzac_.zzafy())).booleanValue() && com_google_android_gms_common_api_internal_zzac_.zzahd().zzafu() && this.zzfni.isUserResolvableError(connectionResult.getErrorCode());
    }

    private final boolean zzahe() {
        this.zzfmy.lock();
        try {
            if (this.zzfnn && this.zzfnk) {
                for (zzc zzb : this.zzfnf.keySet()) {
                    ConnectionResult zzb2 = zzb(zzb);
                    if (zzb2 != null) {
                        if (!zzb2.isSuccess()) {
                        }
                    }
                    this.zzfmy.unlock();
                    return false;
                }
                this.zzfmy.unlock();
                return true;
            }
            this.zzfmy.unlock();
            return false;
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    private final void zzahf() {
        if (this.zzfnd == null) {
            this.zzfnh.zzfpi = Collections.emptySet();
            return;
        }
        Set hashSet = new HashSet(this.zzfnd.zzakj());
        Map zzakl = this.zzfnd.zzakl();
        for (Api api : zzakl.keySet()) {
            ConnectionResult connectionResult = getConnectionResult(api);
            if (connectionResult != null && connectionResult.isSuccess()) {
                hashSet.addAll(((zzt) zzakl.get(api)).zzees);
            }
        }
        this.zzfnh.zzfpi = hashSet;
    }

    private final void zzahg() {
        while (!this.zzfnm.isEmpty()) {
            zze((zzm) this.zzfnm.remove());
        }
        this.zzfnh.zzj(null);
    }

    @Nullable
    private final ConnectionResult zzahh() {
        int i = 0;
        ConnectionResult connectionResult = null;
        int i2 = 0;
        ConnectionResult connectionResult2 = null;
        for (zzac com_google_android_gms_common_api_internal_zzac : this.zzfne.values()) {
            Api zzafy = com_google_android_gms_common_api_internal_zzac.zzafy();
            ConnectionResult connectionResult3 = (ConnectionResult) this.zzfno.get(com_google_android_gms_common_api_internal_zzac.zzaga());
            if (!connectionResult3.isSuccess() && (!((Boolean) this.zzfng.get(zzafy)).booleanValue() || connectionResult3.hasResolution() || this.zzfni.isUserResolvableError(connectionResult3.getErrorCode()))) {
                int priority;
                if (connectionResult3.getErrorCode() == 4 && this.zzfnk) {
                    priority = zzafy.zzafr().getPriority();
                    if (connectionResult == null || i > priority) {
                        i = priority;
                        connectionResult = connectionResult3;
                    }
                } else {
                    ConnectionResult connectionResult4;
                    int i3;
                    priority = zzafy.zzafr().getPriority();
                    if (connectionResult2 == null || i2 > priority) {
                        int i4 = priority;
                        connectionResult4 = connectionResult3;
                        i3 = i4;
                    } else {
                        i3 = i2;
                        connectionResult4 = connectionResult2;
                    }
                    i2 = i3;
                    connectionResult2 = connectionResult4;
                }
            }
        }
        return (connectionResult2 == null || connectionResult == null || i2 <= i) ? connectionResult2 : connectionResult;
    }

    @Nullable
    private final ConnectionResult zzb(@NonNull zzc<?> com_google_android_gms_common_api_Api_zzc_) {
        this.zzfmy.lock();
        try {
            zzac com_google_android_gms_common_api_internal_zzac = (zzac) this.zzfne.get(com_google_android_gms_common_api_Api_zzc_);
            if (this.zzfno == null || com_google_android_gms_common_api_internal_zzac == null) {
                this.zzfmy.unlock();
                return null;
            }
            ConnectionResult connectionResult = (ConnectionResult) this.zzfno.get(com_google_android_gms_common_api_internal_zzac.zzaga());
            return connectionResult;
        } finally {
            this.zzfmy.unlock();
        }
    }

    private final <T extends zzm<? extends Result, ? extends zzb>> boolean zzg(@NonNull T t) {
        zzc zzaft = t.zzaft();
        ConnectionResult zzb = zzb(zzaft);
        if (zzb == null || zzb.getErrorCode() != 4) {
            return false;
        }
        t.zzu(new Status(4, null, this.zzfjo.zza(((zzac) this.zzfne.get(zzaft)).zzaga(), System.identityHashCode(this.zzfnh))));
        return true;
    }

    public final ConnectionResult blockingConnect() {
        connect();
        while (isConnecting()) {
            try {
                this.zzfnj.await();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return new ConnectionResult(15, null);
            }
        }
        return isConnected() ? ConnectionResult.zzfhy : this.zzfnr != null ? this.zzfnr : new ConnectionResult(13, null);
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
            toNanos = this.zzfnj.awaitNanos(toNanos);
        }
        return isConnected() ? ConnectionResult.zzfhy : this.zzfnr != null ? this.zzfnr : new ConnectionResult(13, null);
    }

    public final void connect() {
        this.zzfmy.lock();
        try {
            if (!this.zzfnn) {
                this.zzfnn = true;
                this.zzfno = null;
                this.zzfnp = null;
                this.zzfnq = null;
                this.zzfnr = null;
                this.zzfjo.zzagm();
                this.zzfjo.zza(this.zzfne.values()).addOnCompleteListener(new zzbfx(this.zzakm), new zzaf());
                this.zzfmy.unlock();
            }
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void disconnect() {
        this.zzfmy.lock();
        try {
            this.zzfnn = false;
            this.zzfno = null;
            this.zzfnp = null;
            if (this.zzfnq != null) {
                this.zzfnq.cancel();
                this.zzfnq = null;
            }
            this.zzfnr = null;
            while (!this.zzfnm.isEmpty()) {
                zzm com_google_android_gms_common_api_internal_zzm = (zzm) this.zzfnm.remove();
                com_google_android_gms_common_api_internal_zzm.zza(null);
                com_google_android_gms_common_api_internal_zzm.cancel();
            }
            this.zzfnj.signalAll();
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
    }

    @Nullable
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        return zzb(api.zzaft());
    }

    public final boolean isConnected() {
        this.zzfmy.lock();
        try {
            boolean z = this.zzfno != null && this.zzfnr == null;
            this.zzfmy.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    public final boolean isConnecting() {
        this.zzfmy.lock();
        try {
            boolean z = this.zzfno == null && this.zzfnn;
            this.zzfmy.unlock();
            return z;
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    public final boolean zza(zzcx com_google_android_gms_common_api_internal_zzcx) {
        this.zzfmy.lock();
        try {
            if (!this.zzfnn || zzahe()) {
                this.zzfmy.unlock();
                return false;
            }
            this.zzfjo.zzagm();
            this.zzfnq = new zzag(this, com_google_android_gms_common_api_internal_zzcx);
            this.zzfjo.zza(this.zzfnf.values()).addOnCompleteListener(new zzbfx(this.zzakm), this.zzfnq);
            return true;
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void zzagf() {
        this.zzfmy.lock();
        try {
            this.zzfjo.zzagf();
            if (this.zzfnq != null) {
                this.zzfnq.cancel();
                this.zzfnq = null;
            }
            if (this.zzfnp == null) {
                this.zzfnp = new ArrayMap(this.zzfnf.size());
            }
            ConnectionResult connectionResult = new ConnectionResult(4);
            for (zzac zzaga : this.zzfnf.values()) {
                this.zzfnp.put(zzaga.zzaga(), connectionResult);
            }
            if (this.zzfno != null) {
                this.zzfno.putAll(this.zzfnp);
            }
            this.zzfmy.unlock();
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    public final void zzagy() {
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        if (this.zzfnk && zzg((zzm) t)) {
            return t;
        }
        if (isConnected()) {
            this.zzfnh.zzfpn.zzb(t);
            return ((zzac) this.zzfne.get(t.zzaft())).zza(t);
        }
        this.zzfnm.add(t);
        return t;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        zzc zzaft = t.zzaft();
        if (this.zzfnk && zzg((zzm) t)) {
            return t;
        }
        this.zzfnh.zzfpn.zzb(t);
        return ((zzac) this.zzfne.get(zzaft)).zzb(t);
    }
}
