package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzae;
import com.google.android.gms.common.internal.zzaf;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzbeq;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.internal.zzcwc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzbd extends GoogleApiClient implements zzcg {
    private final Context mContext;
    private final Looper zzakm;
    private final int zzfkc;
    private final GoogleApiAvailability zzfke;
    private zza<? extends zzcwb, zzcwc> zzfkf;
    private boolean zzfki;
    private final Lock zzfmy;
    private zzr zzfnd;
    private Map<Api<?>, Boolean> zzfng;
    final Queue<zzm<?, ?>> zzfnm = new LinkedList();
    private final zzae zzfpa;
    private zzcf zzfpb = null;
    private volatile boolean zzfpc;
    private long zzfpd = 120000;
    private long zzfpe = 5000;
    private final zzbi zzfpf;
    private zzca zzfpg;
    final Map<zzc<?>, zze> zzfph;
    Set<Scope> zzfpi = new HashSet();
    private final zzcp zzfpj = new zzcp();
    private final ArrayList<zzw> zzfpk;
    private Integer zzfpl = null;
    Set<zzdi> zzfpm = null;
    final zzdl zzfpn;
    private final zzaf zzfpo = new zzbe(this);

    public zzbd(Context context, Lock lock, Looper looper, zzr com_google_android_gms_common_internal_zzr, GoogleApiAvailability googleApiAvailability, zza<? extends zzcwb, zzcwc> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc, Map<Api<?>, Boolean> map, List<ConnectionCallbacks> list, List<OnConnectionFailedListener> list2, Map<zzc<?>, zze> map2, int i, int i2, ArrayList<zzw> arrayList, boolean z) {
        this.mContext = context;
        this.zzfmy = lock;
        this.zzfki = false;
        this.zzfpa = new zzae(looper, this.zzfpo);
        this.zzakm = looper;
        this.zzfpf = new zzbi(this, looper);
        this.zzfke = googleApiAvailability;
        this.zzfkc = i;
        if (this.zzfkc >= 0) {
            this.zzfpl = Integer.valueOf(i2);
        }
        this.zzfng = map;
        this.zzfph = map2;
        this.zzfpk = arrayList;
        this.zzfpn = new zzdl(this.zzfph);
        for (ConnectionCallbacks registerConnectionCallbacks : list) {
            this.zzfpa.registerConnectionCallbacks(registerConnectionCallbacks);
        }
        for (OnConnectionFailedListener registerConnectionFailedListener : list2) {
            this.zzfpa.registerConnectionFailedListener(registerConnectionFailedListener);
        }
        this.zzfnd = com_google_android_gms_common_internal_zzr;
        this.zzfkf = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc;
    }

    private final void resume() {
        this.zzfmy.lock();
        try {
            if (this.zzfpc) {
                zzahw();
            }
            this.zzfmy.unlock();
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    public static int zza(Iterable<zze> iterable, boolean z) {
        int i = 0;
        int i2 = 0;
        for (zze com_google_android_gms_common_api_Api_zze : iterable) {
            if (com_google_android_gms_common_api_Api_zze.zzaam()) {
                i2 = 1;
            }
            i = com_google_android_gms_common_api_Api_zze.zzaaw() ? 1 : i;
        }
        return i2 != 0 ? (i == 0 || !z) ? 1 : 2 : 3;
    }

    private final void zza(GoogleApiClient googleApiClient, zzdc com_google_android_gms_common_api_internal_zzdc, boolean z) {
        zzbeq.zzfzb.zzd(googleApiClient).setResultCallback(new zzbh(this, com_google_android_gms_common_api_internal_zzdc, z, googleApiClient));
    }

    private final void zzahw() {
        this.zzfpa.zzakx();
        this.zzfpb.connect();
    }

    private final void zzahx() {
        this.zzfmy.lock();
        try {
            if (zzahy()) {
                zzahw();
            }
            this.zzfmy.unlock();
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    private final void zzbv(int i) {
        if (this.zzfpl == null) {
            this.zzfpl = Integer.valueOf(i);
        } else if (this.zzfpl.intValue() != i) {
            String zzbw = zzbw(i);
            String zzbw2 = zzbw(this.zzfpl.intValue());
            throw new IllegalStateException(new StringBuilder((String.valueOf(zzbw).length() + 51) + String.valueOf(zzbw2).length()).append("Cannot use sign-in mode: ").append(zzbw).append(". Mode was already set to ").append(zzbw2).toString());
        }
        if (this.zzfpb == null) {
            boolean z = false;
            boolean z2 = false;
            for (zze com_google_android_gms_common_api_Api_zze : this.zzfph.values()) {
                if (com_google_android_gms_common_api_Api_zze.zzaam()) {
                    z2 = true;
                }
                z = com_google_android_gms_common_api_Api_zze.zzaaw() ? true : z;
            }
            switch (this.zzfpl.intValue()) {
                case 1:
                    if (!z2) {
                        throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
                    } else if (z) {
                        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
                    }
                    break;
                case 2:
                    if (z2) {
                        if (this.zzfki) {
                            this.zzfpb = new zzad(this.mContext, this.zzfmy, this.zzakm, this.zzfke, this.zzfph, this.zzfnd, this.zzfng, this.zzfkf, this.zzfpk, this, true);
                            return;
                        } else {
                            this.zzfpb = zzy.zza(this.mContext, this, this.zzfmy, this.zzakm, this.zzfke, this.zzfph, this.zzfnd, this.zzfng, this.zzfkf, this.zzfpk);
                            return;
                        }
                    }
                    break;
            }
            if (!this.zzfki || z) {
                this.zzfpb = new zzbl(this.mContext, this, this.zzfmy, this.zzakm, this.zzfke, this.zzfph, this.zzfnd, this.zzfng, this.zzfkf, this.zzfpk, this);
            } else {
                this.zzfpb = new zzad(this.mContext, this.zzfmy, this.zzakm, this.zzfke, this.zzfph, this.zzfnd, this.zzfng, this.zzfkf, this.zzfpk, this, false);
            }
        }
    }

    private static String zzbw(int i) {
        switch (i) {
            case 1:
                return "SIGN_IN_MODE_REQUIRED";
            case 2:
                return "SIGN_IN_MODE_OPTIONAL";
            case 3:
                return "SIGN_IN_MODE_NONE";
            default:
                return "UNKNOWN";
        }
    }

    public final ConnectionResult blockingConnect() {
        boolean z = true;
        zzbq.zza(Looper.myLooper() != Looper.getMainLooper(), (Object) "blockingConnect must not be called on the UI thread");
        this.zzfmy.lock();
        try {
            if (this.zzfkc >= 0) {
                if (this.zzfpl == null) {
                    z = false;
                }
                zzbq.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzfpl == null) {
                this.zzfpl = Integer.valueOf(zza(this.zzfph.values(), false));
            } else if (this.zzfpl.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzbv(this.zzfpl.intValue());
            this.zzfpa.zzakx();
            ConnectionResult blockingConnect = this.zzfpb.blockingConnect();
            return blockingConnect;
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final ConnectionResult blockingConnect(long j, @NonNull TimeUnit timeUnit) {
        boolean z = false;
        if (Looper.myLooper() != Looper.getMainLooper()) {
            z = true;
        }
        zzbq.zza(z, (Object) "blockingConnect must not be called on the UI thread");
        zzbq.checkNotNull(timeUnit, "TimeUnit must not be null");
        this.zzfmy.lock();
        try {
            if (this.zzfpl == null) {
                this.zzfpl = Integer.valueOf(zza(this.zzfph.values(), false));
            } else if (this.zzfpl.intValue() == 2) {
                throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            zzbv(this.zzfpl.intValue());
            this.zzfpa.zzakx();
            ConnectionResult blockingConnect = this.zzfpb.blockingConnect(j, timeUnit);
            return blockingConnect;
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final PendingResult<Status> clearDefaultAccountAndReconnect() {
        zzbq.zza(isConnected(), (Object) "GoogleApiClient is not connected yet.");
        zzbq.zza(this.zzfpl.intValue() != 2, (Object) "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
        PendingResult com_google_android_gms_common_api_internal_zzdc = new zzdc(this);
        if (this.zzfph.containsKey(zzbeq.zzdyh)) {
            zza(this, com_google_android_gms_common_api_internal_zzdc, false);
        } else {
            AtomicReference atomicReference = new AtomicReference();
            GoogleApiClient build = new Builder(this.mContext).addApi(zzbeq.API).addConnectionCallbacks(new zzbf(this, atomicReference, com_google_android_gms_common_api_internal_zzdc)).addOnConnectionFailedListener(new zzbg(this, com_google_android_gms_common_api_internal_zzdc)).setHandler(this.zzfpf).build();
            atomicReference.set(build);
            build.connect();
        }
        return com_google_android_gms_common_api_internal_zzdc;
    }

    public final void connect() {
        boolean z = false;
        this.zzfmy.lock();
        try {
            if (this.zzfkc >= 0) {
                if (this.zzfpl != null) {
                    z = true;
                }
                zzbq.zza(z, (Object) "Sign-in mode should have been set explicitly by auto-manage.");
            } else if (this.zzfpl == null) {
                this.zzfpl = Integer.valueOf(zza(this.zzfph.values(), false));
            } else if (this.zzfpl.intValue() == 2) {
                throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
            }
            connect(this.zzfpl.intValue());
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void connect(int i) {
        boolean z = true;
        this.zzfmy.lock();
        if (!(i == 3 || i == 1 || i == 2)) {
            z = false;
        }
        try {
            zzbq.checkArgument(z, "Illegal sign-in mode: " + i);
            zzbv(i);
            zzahw();
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void disconnect() {
        this.zzfmy.lock();
        try {
            this.zzfpn.release();
            if (this.zzfpb != null) {
                this.zzfpb.disconnect();
            }
            this.zzfpj.release();
            for (zzm com_google_android_gms_common_api_internal_zzm : this.zzfnm) {
                com_google_android_gms_common_api_internal_zzm.zza(null);
                com_google_android_gms_common_api_internal_zzm.cancel();
            }
            this.zzfnm.clear();
            if (this.zzfpb != null) {
                zzahy();
                this.zzfpa.zzakw();
                this.zzfmy.unlock();
            }
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void dump(String str, FileDescriptor fileDescriptor, PrintWriter printWriter, String[] strArr) {
        printWriter.append(str).append("mContext=").println(this.mContext);
        printWriter.append(str).append("mResuming=").print(this.zzfpc);
        printWriter.append(" mWorkQueue.size()=").print(this.zzfnm.size());
        printWriter.append(" mUnconsumedApiCalls.size()=").println(this.zzfpn.zzfso.size());
        if (this.zzfpb != null) {
            this.zzfpb.dump(str, fileDescriptor, printWriter, strArr);
        }
    }

    @NonNull
    public final ConnectionResult getConnectionResult(@NonNull Api<?> api) {
        this.zzfmy.lock();
        try {
            if (!isConnected() && !this.zzfpc) {
                throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
            } else if (this.zzfph.containsKey(api.zzaft())) {
                ConnectionResult connectionResult = this.zzfpb.getConnectionResult(api);
                if (connectionResult != null) {
                    this.zzfmy.unlock();
                } else if (this.zzfpc) {
                    connectionResult = ConnectionResult.zzfhy;
                } else {
                    Log.w("GoogleApiClientImpl", zzaia());
                    Log.wtf("GoogleApiClientImpl", String.valueOf(api.getName()).concat(" requested in getConnectionResult is not connected but is not present in the failed  connections map"), new Exception());
                    connectionResult = new ConnectionResult(8, null);
                    this.zzfmy.unlock();
                }
                return connectionResult;
            } else {
                throw new IllegalArgumentException(String.valueOf(api.getName()).concat(" was never registered with GoogleApiClient"));
            }
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final Context getContext() {
        return this.mContext;
    }

    public final Looper getLooper() {
        return this.zzakm;
    }

    public final boolean hasConnectedApi(@NonNull Api<?> api) {
        if (!isConnected()) {
            return false;
        }
        zze com_google_android_gms_common_api_Api_zze = (zze) this.zzfph.get(api.zzaft());
        return com_google_android_gms_common_api_Api_zze != null && com_google_android_gms_common_api_Api_zze.isConnected();
    }

    public final boolean isConnected() {
        return this.zzfpb != null && this.zzfpb.isConnected();
    }

    public final boolean isConnecting() {
        return this.zzfpb != null && this.zzfpb.isConnecting();
    }

    public final boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks connectionCallbacks) {
        return this.zzfpa.isConnectionCallbacksRegistered(connectionCallbacks);
    }

    public final boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        return this.zzfpa.isConnectionFailedListenerRegistered(onConnectionFailedListener);
    }

    public final void reconnect() {
        disconnect();
        connect();
    }

    public final void registerConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        this.zzfpa.registerConnectionCallbacks(connectionCallbacks);
    }

    public final void registerConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfpa.registerConnectionFailedListener(onConnectionFailedListener);
    }

    public final void stopAutoManage(@NonNull FragmentActivity fragmentActivity) {
        zzch com_google_android_gms_common_api_internal_zzch = new zzch(fragmentActivity);
        if (this.zzfkc >= 0) {
            zzi.zza(com_google_android_gms_common_api_internal_zzch).zzbr(this.zzfkc);
            return;
        }
        throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
    }

    public final void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks connectionCallbacks) {
        this.zzfpa.unregisterConnectionCallbacks(connectionCallbacks);
    }

    public final void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener onConnectionFailedListener) {
        this.zzfpa.unregisterConnectionFailedListener(onConnectionFailedListener);
    }

    @NonNull
    public final <C extends zze> C zza(@NonNull zzc<C> com_google_android_gms_common_api_Api_zzc_C) {
        zze com_google_android_gms_common_api_Api_zze = (zze) this.zzfph.get(com_google_android_gms_common_api_Api_zzc_C);
        zzbq.checkNotNull(com_google_android_gms_common_api_Api_zze, "Appropriate Api was not requested.");
        return com_google_android_gms_common_api_Api_zze;
    }

    public final void zza(zzdi com_google_android_gms_common_api_internal_zzdi) {
        this.zzfmy.lock();
        try {
            if (this.zzfpm == null) {
                this.zzfpm = new HashSet();
            }
            this.zzfpm.add(com_google_android_gms_common_api_internal_zzdi);
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final boolean zza(@NonNull Api<?> api) {
        return this.zzfph.containsKey(api.zzaft());
    }

    public final boolean zza(zzcx com_google_android_gms_common_api_internal_zzcx) {
        return this.zzfpb != null && this.zzfpb.zza(com_google_android_gms_common_api_internal_zzcx);
    }

    public final void zzagf() {
        if (this.zzfpb != null) {
            this.zzfpb.zzagf();
        }
    }

    final boolean zzahy() {
        if (!this.zzfpc) {
            return false;
        }
        this.zzfpc = false;
        this.zzfpf.removeMessages(2);
        this.zzfpf.removeMessages(1);
        if (this.zzfpg != null) {
            this.zzfpg.unregister();
            this.zzfpg = null;
        }
        return true;
    }

    final boolean zzahz() {
        boolean z = false;
        this.zzfmy.lock();
        try {
            if (this.zzfpm != null) {
                if (!this.zzfpm.isEmpty()) {
                    z = true;
                }
                this.zzfmy.unlock();
            }
            return z;
        } finally {
            this.zzfmy.unlock();
        }
    }

    final String zzaia() {
        Writer stringWriter = new StringWriter();
        dump("", null, new PrintWriter(stringWriter), null);
        return stringWriter.toString();
    }

    public final void zzb(zzdi com_google_android_gms_common_api_internal_zzdi) {
        this.zzfmy.lock();
        try {
            if (this.zzfpm == null) {
                Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
            } else if (!this.zzfpm.remove(com_google_android_gms_common_api_internal_zzdi)) {
                Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
            } else if (!zzahz()) {
                this.zzfpb.zzagy();
            }
            this.zzfmy.unlock();
        } catch (Throwable th) {
            this.zzfmy.unlock();
        }
    }

    public final void zzc(ConnectionResult connectionResult) {
        if (!com.google.android.gms.common.zze.zze(this.mContext, connectionResult.getErrorCode())) {
            zzahy();
        }
        if (!this.zzfpc) {
            this.zzfpa.zzk(connectionResult);
            this.zzfpa.zzakw();
        }
    }

    public final <A extends zzb, R extends Result, T extends zzm<R, A>> T zzd(@NonNull T t) {
        zzbq.checkArgument(t.zzaft() != null, "This task can not be enqueued (it's probably a Batch or malformed)");
        boolean containsKey = this.zzfph.containsKey(t.zzaft());
        String name = t.zzafy() != null ? t.zzafy().getName() : "the API";
        zzbq.checkArgument(containsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zzfmy.lock();
        try {
            if (this.zzfpb == null) {
                this.zzfnm.add(t);
            } else {
                t = this.zzfpb.zzd(t);
                this.zzfmy.unlock();
            }
            return t;
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zze(@NonNull T t) {
        zzbq.checkArgument(t.zzaft() != null, "This task can not be executed (it's probably a Batch or malformed)");
        boolean containsKey = this.zzfph.containsKey(t.zzaft());
        String name = t.zzafy() != null ? t.zzafy().getName() : "the API";
        zzbq.checkArgument(containsKey, new StringBuilder(String.valueOf(name).length() + 65).append("GoogleApiClient is not configured to use ").append(name).append(" required for this call.").toString());
        this.zzfmy.lock();
        try {
            if (this.zzfpb == null) {
                throw new IllegalStateException("GoogleApiClient is not connected yet.");
            }
            if (this.zzfpc) {
                this.zzfnm.add(t);
                while (!this.zzfnm.isEmpty()) {
                    zzm com_google_android_gms_common_api_internal_zzm = (zzm) this.zzfnm.remove();
                    this.zzfpn.zzb(com_google_android_gms_common_api_internal_zzm);
                    com_google_android_gms_common_api_internal_zzm.zzu(Status.zzfkq);
                }
            } else {
                t = this.zzfpb.zze(t);
                this.zzfmy.unlock();
            }
            return t;
        } finally {
            this.zzfmy.unlock();
        }
    }

    public final void zzf(int i, boolean z) {
        if (!(i != 1 || z || this.zzfpc)) {
            this.zzfpc = true;
            if (this.zzfpg == null) {
                this.zzfpg = GoogleApiAvailability.zza(this.mContext.getApplicationContext(), new zzbj(this));
            }
            this.zzfpf.sendMessageDelayed(this.zzfpf.obtainMessage(1), this.zzfpd);
            this.zzfpf.sendMessageDelayed(this.zzfpf.obtainMessage(2), this.zzfpe);
        }
        this.zzfpn.zzaji();
        this.zzfpa.zzcg(i);
        this.zzfpa.zzakw();
        if (i == 2) {
            zzahw();
        }
    }

    public final void zzj(Bundle bundle) {
        while (!this.zzfnm.isEmpty()) {
            zze((zzm) this.zzfnm.remove());
        }
        this.zzfpa.zzk(bundle);
    }

    public final <L> zzcl<L> zzs(@NonNull L l) {
        this.zzfmy.lock();
        try {
            zzcl<L> zza = this.zzfpj.zza(l, this.zzakm, "NO_TYPE");
            return zza;
        } finally {
            this.zzfmy.unlock();
        }
    }
}
