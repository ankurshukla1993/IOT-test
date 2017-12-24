package com.google.android.gms.common.api.internal;

import android.app.Application;
import android.app.PendingIntent;
import android.content.Context;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.support.v4.util.ArraySet;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzbp implements Callback {
    private static final Object sLock = new Object();
    public static final Status zzfqe = new Status(4, "Sign-out occurred while this API call was in progress.");
    private static final Status zzfqf = new Status(4, "The user must be signed in to make this API call.");
    private static zzbp zzfqh;
    private final Context mContext;
    private final Handler mHandler;
    private final GoogleApiAvailability zzfke;
    private final Map<zzh<?>, zzbr<?>> zzfne = new ConcurrentHashMap(5, 0.75f, 1);
    private long zzfpd = 120000;
    private long zzfpe = 5000;
    private long zzfqg = 10000;
    private int zzfqi = -1;
    private final AtomicInteger zzfqj = new AtomicInteger(1);
    private final AtomicInteger zzfqk = new AtomicInteger(0);
    private zzak zzfql = null;
    private final Set<zzh<?>> zzfqm = new ArraySet();
    private final Set<zzh<?>> zzfqn = new ArraySet();

    private zzbp(Context context, Looper looper, GoogleApiAvailability googleApiAvailability) {
        this.mContext = context;
        this.mHandler = new Handler(looper, this);
        this.zzfke = googleApiAvailability;
        this.mHandler.sendMessage(this.mHandler.obtainMessage(6));
    }

    public static zzbp zzaie() {
        zzbp com_google_android_gms_common_api_internal_zzbp;
        synchronized (sLock) {
            zzbq.checkNotNull(zzfqh, "Must guarantee manager is non-null before using getInstance");
            com_google_android_gms_common_api_internal_zzbp = zzfqh;
        }
        return com_google_android_gms_common_api_internal_zzbp;
    }

    public static void zzaif() {
        synchronized (sLock) {
            if (zzfqh != null) {
                zzbp com_google_android_gms_common_api_internal_zzbp = zzfqh;
                com_google_android_gms_common_api_internal_zzbp.zzfqk.incrementAndGet();
                com_google_android_gms_common_api_internal_zzbp.mHandler.sendMessageAtFrontOfQueue(com_google_android_gms_common_api_internal_zzbp.mHandler.obtainMessage(10));
            }
        }
    }

    @WorkerThread
    private final void zzaih() {
        for (zzh remove : this.zzfqn) {
            ((zzbr) this.zzfne.remove(remove)).signOut();
        }
        this.zzfqn.clear();
    }

    @WorkerThread
    private final void zzb(GoogleApi<?> googleApi) {
        zzh zzaga = googleApi.zzaga();
        zzbr com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfne.get(zzaga);
        if (com_google_android_gms_common_api_internal_zzbr == null) {
            com_google_android_gms_common_api_internal_zzbr = new zzbr(this, googleApi);
            this.zzfne.put(zzaga, com_google_android_gms_common_api_internal_zzbr);
        }
        if (com_google_android_gms_common_api_internal_zzbr.zzaam()) {
            this.zzfqn.add(zzaga);
        }
        com_google_android_gms_common_api_internal_zzbr.connect();
    }

    public static zzbp zzch(Context context) {
        zzbp com_google_android_gms_common_api_internal_zzbp;
        synchronized (sLock) {
            if (zzfqh == null) {
                HandlerThread handlerThread = new HandlerThread("GoogleApiHandler", 9);
                handlerThread.start();
                zzfqh = new zzbp(context.getApplicationContext(), handlerThread.getLooper(), GoogleApiAvailability.getInstance());
            }
            com_google_android_gms_common_api_internal_zzbp = zzfqh;
        }
        return com_google_android_gms_common_api_internal_zzbp;
    }

    @WorkerThread
    public final boolean handleMessage(Message message) {
        zzbr com_google_android_gms_common_api_internal_zzbr;
        switch (message.what) {
            case 1:
                this.zzfqg = ((Boolean) message.obj).booleanValue() ? 10000 : 300000;
                this.mHandler.removeMessages(12);
                for (zzh obtainMessage : this.zzfne.keySet()) {
                    this.mHandler.sendMessageDelayed(this.mHandler.obtainMessage(12, obtainMessage), this.zzfqg);
                }
                break;
            case 2:
                zzj com_google_android_gms_common_api_internal_zzj = (zzj) message.obj;
                for (zzh com_google_android_gms_common_api_internal_zzh : com_google_android_gms_common_api_internal_zzj.zzagn()) {
                    zzbr com_google_android_gms_common_api_internal_zzbr2 = (zzbr) this.zzfne.get(com_google_android_gms_common_api_internal_zzh);
                    if (com_google_android_gms_common_api_internal_zzbr2 == null) {
                        com_google_android_gms_common_api_internal_zzj.zza(com_google_android_gms_common_api_internal_zzh, new ConnectionResult(13));
                        break;
                    } else if (com_google_android_gms_common_api_internal_zzbr2.isConnected()) {
                        com_google_android_gms_common_api_internal_zzj.zza(com_google_android_gms_common_api_internal_zzh, ConnectionResult.zzfhy);
                    } else if (com_google_android_gms_common_api_internal_zzbr2.zzaio() != null) {
                        com_google_android_gms_common_api_internal_zzj.zza(com_google_android_gms_common_api_internal_zzh, com_google_android_gms_common_api_internal_zzbr2.zzaio());
                    } else {
                        com_google_android_gms_common_api_internal_zzbr2.zza(com_google_android_gms_common_api_internal_zzj);
                    }
                }
                break;
            case 3:
                for (zzbr com_google_android_gms_common_api_internal_zzbr3 : this.zzfne.values()) {
                    com_google_android_gms_common_api_internal_zzbr3.zzain();
                    com_google_android_gms_common_api_internal_zzbr3.connect();
                }
                break;
            case 4:
            case 8:
            case 13:
                zzcs com_google_android_gms_common_api_internal_zzcs = (zzcs) message.obj;
                com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfne.get(com_google_android_gms_common_api_internal_zzcs.zzfrx.zzaga());
                if (com_google_android_gms_common_api_internal_zzbr == null) {
                    zzb(com_google_android_gms_common_api_internal_zzcs.zzfrx);
                    com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfne.get(com_google_android_gms_common_api_internal_zzcs.zzfrx.zzaga());
                }
                if (com_google_android_gms_common_api_internal_zzbr.zzaam() && this.zzfqk.get() != com_google_android_gms_common_api_internal_zzcs.zzfrw) {
                    com_google_android_gms_common_api_internal_zzcs.zzfrv.zzs(zzfqe);
                    com_google_android_gms_common_api_internal_zzbr.signOut();
                    break;
                }
                com_google_android_gms_common_api_internal_zzbr.zza(com_google_android_gms_common_api_internal_zzcs.zzfrv);
                break;
                break;
            case 5:
                String errorString;
                String errorMessage;
                int i = message.arg1;
                ConnectionResult connectionResult = (ConnectionResult) message.obj;
                for (zzbr com_google_android_gms_common_api_internal_zzbr4 : this.zzfne.values()) {
                    if (com_google_android_gms_common_api_internal_zzbr4.getInstanceId() == i) {
                        if (com_google_android_gms_common_api_internal_zzbr4 != null) {
                            Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                            break;
                        }
                        errorString = this.zzfke.getErrorString(connectionResult.getErrorCode());
                        errorMessage = connectionResult.getErrorMessage();
                        com_google_android_gms_common_api_internal_zzbr4.zzw(new Status(17, new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                        break;
                    }
                }
                com_google_android_gms_common_api_internal_zzbr4 = null;
                if (com_google_android_gms_common_api_internal_zzbr4 != null) {
                    Log.wtf("GoogleApiManager", "Could not find API instance " + i + " while trying to fail enqueued calls.", new Exception());
                } else {
                    errorString = this.zzfke.getErrorString(connectionResult.getErrorCode());
                    errorMessage = connectionResult.getErrorMessage();
                    com_google_android_gms_common_api_internal_zzbr4.zzw(new Status(17, new StringBuilder((String.valueOf(errorString).length() + 69) + String.valueOf(errorMessage).length()).append("Error resolution was canceled by the user, original error message: ").append(errorString).append(": ").append(errorMessage).toString()));
                }
            case 6:
                if (this.mContext.getApplicationContext() instanceof Application) {
                    zzk.zza((Application) this.mContext.getApplicationContext());
                    zzk.zzagp().zza(new zzbq(this));
                    if (!zzk.zzagp().zzbd(true)) {
                        this.zzfqg = 300000;
                        break;
                    }
                }
                break;
            case 7:
                zzb((GoogleApi) message.obj);
                break;
            case 9:
                if (this.zzfne.containsKey(message.obj)) {
                    ((zzbr) this.zzfne.get(message.obj)).resume();
                    break;
                }
                break;
            case 10:
                zzaih();
                break;
            case 11:
                if (this.zzfne.containsKey(message.obj)) {
                    ((zzbr) this.zzfne.get(message.obj)).zzahx();
                    break;
                }
                break;
            case 12:
                if (this.zzfne.containsKey(message.obj)) {
                    ((zzbr) this.zzfne.get(message.obj)).zzair();
                    break;
                }
                break;
            default:
                Log.w("GoogleApiManager", "Unknown message id: " + message.what);
                return false;
        }
        return true;
    }

    final PendingIntent zza(zzh<?> com_google_android_gms_common_api_internal_zzh_, int i) {
        zzbr com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfne.get(com_google_android_gms_common_api_internal_zzh_);
        if (com_google_android_gms_common_api_internal_zzbr == null) {
            return null;
        }
        zzcwb zzais = com_google_android_gms_common_api_internal_zzbr.zzais();
        return zzais == null ? null : PendingIntent.getActivity(this.mContext, i, zzais.getSignInIntent(), 134217728);
    }

    public final <O extends ApiOptions> Task<Boolean> zza(@NonNull GoogleApi<O> googleApi, @NonNull zzcn<?> com_google_android_gms_common_api_internal_zzcn_) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(13, new zzcs(new zzf(com_google_android_gms_common_api_internal_zzcn_, taskCompletionSource), this.zzfqk.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final <O extends ApiOptions> Task<Void> zza(@NonNull GoogleApi<O> googleApi, @NonNull zzct<zzb, ?> com_google_android_gms_common_api_internal_zzct_com_google_android_gms_common_api_Api_zzb__, @NonNull zzdp<zzb, ?> com_google_android_gms_common_api_internal_zzdp_com_google_android_gms_common_api_Api_zzb__) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(8, new zzcs(new zzd(new zzcu(com_google_android_gms_common_api_internal_zzct_com_google_android_gms_common_api_Api_zzb__, com_google_android_gms_common_api_internal_zzdp_com_google_android_gms_common_api_Api_zzb__), taskCompletionSource), this.zzfqk.get(), googleApi)));
        return taskCompletionSource.getTask();
    }

    public final Task<Void> zza(Iterable<? extends GoogleApi<?>> iterable) {
        zzj com_google_android_gms_common_api_internal_zzj = new zzj(iterable);
        for (GoogleApi zzaga : iterable) {
            zzbr com_google_android_gms_common_api_internal_zzbr = (zzbr) this.zzfne.get(zzaga.zzaga());
            if (com_google_android_gms_common_api_internal_zzbr != null) {
                if (!com_google_android_gms_common_api_internal_zzbr.isConnected()) {
                }
            }
            this.mHandler.sendMessage(this.mHandler.obtainMessage(2, com_google_android_gms_common_api_internal_zzj));
            return com_google_android_gms_common_api_internal_zzj.getTask();
        }
        com_google_android_gms_common_api_internal_zzj.zzago();
        return com_google_android_gms_common_api_internal_zzj.getTask();
    }

    public final void zza(ConnectionResult connectionResult, int i) {
        if (!zzc(connectionResult, i)) {
            this.mHandler.sendMessage(this.mHandler.obtainMessage(5, i, 0, connectionResult));
        }
    }

    public final void zza(GoogleApi<?> googleApi) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(7, googleApi));
    }

    public final <O extends ApiOptions, TResult> void zza(GoogleApi<O> googleApi, int i, zzdf<zzb, TResult> com_google_android_gms_common_api_internal_zzdf_com_google_android_gms_common_api_Api_zzb__TResult, TaskCompletionSource<TResult> taskCompletionSource, zzdb com_google_android_gms_common_api_internal_zzdb) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzcs(new zze(i, com_google_android_gms_common_api_internal_zzdf_com_google_android_gms_common_api_Api_zzb__TResult, taskCompletionSource, com_google_android_gms_common_api_internal_zzdb), this.zzfqk.get(), googleApi)));
    }

    public final <O extends ApiOptions> void zza(GoogleApi<O> googleApi, int i, zzm<? extends Result, zzb> com_google_android_gms_common_api_internal_zzm__extends_com_google_android_gms_common_api_Result__com_google_android_gms_common_api_Api_zzb) {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(4, new zzcs(new zzc(i, com_google_android_gms_common_api_internal_zzm__extends_com_google_android_gms_common_api_Result__com_google_android_gms_common_api_Api_zzb), this.zzfqk.get(), googleApi)));
    }

    public final void zza(@NonNull zzak com_google_android_gms_common_api_internal_zzak) {
        synchronized (sLock) {
            if (this.zzfql != com_google_android_gms_common_api_internal_zzak) {
                this.zzfql = com_google_android_gms_common_api_internal_zzak;
                this.zzfqm.clear();
                this.zzfqm.addAll(com_google_android_gms_common_api_internal_zzak.zzahl());
            }
        }
    }

    final void zzagf() {
        this.zzfqk.incrementAndGet();
        this.mHandler.sendMessage(this.mHandler.obtainMessage(10));
    }

    public final void zzagm() {
        this.mHandler.sendMessage(this.mHandler.obtainMessage(3));
    }

    public final int zzaig() {
        return this.zzfqj.getAndIncrement();
    }

    final void zzb(@NonNull zzak com_google_android_gms_common_api_internal_zzak) {
        synchronized (sLock) {
            if (this.zzfql == com_google_android_gms_common_api_internal_zzak) {
                this.zzfql = null;
                this.zzfqm.clear();
            }
        }
    }

    final boolean zzc(ConnectionResult connectionResult, int i) {
        return this.zzfke.zza(this.mContext, connectionResult, i);
    }
}
