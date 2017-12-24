package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.Api.ApiOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasAccountOptions;
import com.google.android.gms.common.api.Api.ApiOptions.HasGoogleSignInAccountOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.internal.zzak;
import com.google.android.gms.common.api.internal.zzbp;
import com.google.android.gms.common.api.internal.zzbr;
import com.google.android.gms.common.api.internal.zzbz;
import com.google.android.gms.common.api.internal.zzcl;
import com.google.android.gms.common.api.internal.zzcn;
import com.google.android.gms.common.api.internal.zzcp;
import com.google.android.gms.common.api.internal.zzct;
import com.google.android.gms.common.api.internal.zzcy;
import com.google.android.gms.common.api.internal.zzdb;
import com.google.android.gms.common.api.internal.zzdf;
import com.google.android.gms.common.api.internal.zzdp;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collection;
import java.util.Collections;

public class GoogleApi<O extends ApiOptions> {
    private final Context mContext;
    private final int mId;
    private final Looper zzakm;
    private final Api<O> zzffv;
    private final O zzfjk;
    private final zzh<O> zzfjl;
    private final GoogleApiClient zzfjm;
    private final zzdb zzfjn;
    protected final zzbp zzfjo;

    @MainThread
    public GoogleApi(@NonNull Activity activity, Api<O> api, O o, zza com_google_android_gms_common_api_GoogleApi_zza) {
        zzbq.checkNotNull(activity, "Null activity is not permitted.");
        zzbq.checkNotNull(api, "Api must not be null.");
        zzbq.checkNotNull(com_google_android_gms_common_api_GoogleApi_zza, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = activity.getApplicationContext();
        this.zzffv = api;
        this.zzfjk = o;
        this.zzakm = com_google_android_gms_common_api_GoogleApi_zza.zzfjr;
        this.zzfjl = zzh.zza(this.zzffv, this.zzfjk);
        this.zzfjm = new zzbz(this);
        this.zzfjo = zzbp.zzch(this.mContext);
        this.mId = this.zzfjo.zzaig();
        this.zzfjn = com_google_android_gms_common_api_GoogleApi_zza.zzfjq;
        zzak.zza(activity, this.zzfjo, this.zzfjl);
        this.zzfjo.zza(this);
    }

    @Deprecated
    public GoogleApi(@NonNull Activity activity, Api<O> api, O o, zzdb com_google_android_gms_common_api_internal_zzdb) {
        this(activity, (Api) api, (ApiOptions) o, new zzd().zza(com_google_android_gms_common_api_internal_zzdb).zza(activity.getMainLooper()).zzagd());
    }

    protected GoogleApi(@NonNull Context context, Api<O> api, Looper looper) {
        zzbq.checkNotNull(context, "Null context is not permitted.");
        zzbq.checkNotNull(api, "Api must not be null.");
        zzbq.checkNotNull(looper, "Looper must not be null.");
        this.mContext = context.getApplicationContext();
        this.zzffv = api;
        this.zzfjk = null;
        this.zzakm = looper;
        this.zzfjl = zzh.zzb(api);
        this.zzfjm = new zzbz(this);
        this.zzfjo = zzbp.zzch(this.mContext);
        this.mId = this.zzfjo.zzaig();
        this.zzfjn = new zzg();
    }

    @Deprecated
    public GoogleApi(@NonNull Context context, Api<O> api, O o, Looper looper, zzdb com_google_android_gms_common_api_internal_zzdb) {
        this(context, (Api) api, null, new zzd().zza(looper).zza(com_google_android_gms_common_api_internal_zzdb).zzagd());
    }

    public GoogleApi(@NonNull Context context, Api<O> api, O o, zza com_google_android_gms_common_api_GoogleApi_zza) {
        zzbq.checkNotNull(context, "Null context is not permitted.");
        zzbq.checkNotNull(api, "Api must not be null.");
        zzbq.checkNotNull(com_google_android_gms_common_api_GoogleApi_zza, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
        this.mContext = context.getApplicationContext();
        this.zzffv = api;
        this.zzfjk = o;
        this.zzakm = com_google_android_gms_common_api_GoogleApi_zza.zzfjr;
        this.zzfjl = zzh.zza(this.zzffv, this.zzfjk);
        this.zzfjm = new zzbz(this);
        this.zzfjo = zzbp.zzch(this.mContext);
        this.mId = this.zzfjo.zzaig();
        this.zzfjn = com_google_android_gms_common_api_GoogleApi_zza.zzfjq;
        this.zzfjo.zza(this);
    }

    @Deprecated
    public GoogleApi(@NonNull Context context, Api<O> api, O o, zzdb com_google_android_gms_common_api_internal_zzdb) {
        this(context, (Api) api, (ApiOptions) o, new zzd().zza(com_google_android_gms_common_api_internal_zzdb).zzagd());
    }

    private final <A extends zzb, T extends zzm<? extends Result, A>> T zza(int i, @NonNull T t) {
        t.zzagw();
        this.zzfjo.zza(this, i, t);
        return t;
    }

    private final <TResult, A extends zzb> Task<TResult> zza(int i, @NonNull zzdf<A, TResult> com_google_android_gms_common_api_internal_zzdf_A__TResult) {
        TaskCompletionSource taskCompletionSource = new TaskCompletionSource();
        this.zzfjo.zza(this, i, com_google_android_gms_common_api_internal_zzdf_A__TResult, taskCompletionSource, this.zzfjn);
        return taskCompletionSource.getTask();
    }

    private final zzs zzagc() {
        Collection grantedScopes;
        zzs com_google_android_gms_common_internal_zzs = new zzs();
        Account account = this.zzfjk instanceof HasGoogleSignInAccountOptions ? ((HasGoogleSignInAccountOptions) this.zzfjk).getGoogleSignInAccount().getAccount() : this.zzfjk instanceof HasAccountOptions ? ((HasAccountOptions) this.zzfjk).getAccount() : null;
        com_google_android_gms_common_internal_zzs = com_google_android_gms_common_internal_zzs.zze(account);
        if (this.zzfjk instanceof HasGoogleSignInAccountOptions) {
            GoogleSignInAccount googleSignInAccount = ((HasGoogleSignInAccountOptions) this.zzfjk).getGoogleSignInAccount();
            if (googleSignInAccount != null) {
                grantedScopes = googleSignInAccount.getGrantedScopes();
                return com_google_android_gms_common_internal_zzs.zze(grantedScopes);
            }
        }
        grantedScopes = Collections.emptySet();
        return com_google_android_gms_common_internal_zzs.zze(grantedScopes);
    }

    public final Context getApplicationContext() {
        return this.mContext;
    }

    public final int getInstanceId() {
        return this.mId;
    }

    public final Looper getLooper() {
        return this.zzakm;
    }

    @WorkerThread
    public zze zza(Looper looper, zzbr<O> com_google_android_gms_common_api_internal_zzbr_O) {
        return this.zzffv.zzafs().zza(this.mContext, looper, zzagc().zzga(this.mContext.getPackageName()).zzgb(this.mContext.getClass().getName()).zzakr(), this.zzfjk, com_google_android_gms_common_api_internal_zzbr_O, com_google_android_gms_common_api_internal_zzbr_O);
    }

    public final <L> zzcl<L> zza(@NonNull L l, String str) {
        return zzcp.zzb(l, this.zzakm, str);
    }

    public zzcy zza(Context context, Handler handler) {
        return new zzcy(context, handler, zzagc().zzakr());
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zza(@NonNull T t) {
        return zza(0, (zzm) t);
    }

    public final Task<Boolean> zza(@NonNull zzcn<?> com_google_android_gms_common_api_internal_zzcn_) {
        zzbq.checkNotNull(com_google_android_gms_common_api_internal_zzcn_, "Listener key cannot be null.");
        return this.zzfjo.zza(this, com_google_android_gms_common_api_internal_zzcn_);
    }

    public final <A extends zzb, T extends zzct<A, ?>, U extends zzdp<A, ?>> Task<Void> zza(@NonNull T t, U u) {
        zzbq.checkNotNull(t);
        zzbq.checkNotNull(u);
        zzbq.checkNotNull(t.zzajc(), "Listener has already been released.");
        zzbq.checkNotNull(u.zzajc(), "Listener has already been released.");
        zzbq.checkArgument(t.zzajc().equals(u.zzajc()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
        return this.zzfjo.zza(this, t, u);
    }

    public final <TResult, A extends zzb> Task<TResult> zza(zzdf<A, TResult> com_google_android_gms_common_api_internal_zzdf_A__TResult) {
        return zza(0, (zzdf) com_google_android_gms_common_api_internal_zzdf_A__TResult);
    }

    public final Api<O> zzafy() {
        return this.zzffv;
    }

    public final O zzafz() {
        return this.zzfjk;
    }

    public final zzh<O> zzaga() {
        return this.zzfjl;
    }

    public final GoogleApiClient zzagb() {
        return this.zzfjm;
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T t) {
        return zza(1, (zzm) t);
    }

    public final <TResult, A extends zzb> Task<TResult> zzb(zzdf<A, TResult> com_google_android_gms_common_api_internal_zzdf_A__TResult) {
        return zza(1, (zzdf) com_google_android_gms_common_api_internal_zzdf_A__TResult);
    }

    public final <A extends zzb, T extends zzm<? extends Result, A>> T zzc(@NonNull T t) {
        return zza(2, (zzm) t);
    }
}
