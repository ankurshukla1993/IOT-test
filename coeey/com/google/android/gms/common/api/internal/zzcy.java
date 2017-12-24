package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbt;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcvy;
import com.google.android.gms.internal.zzcwb;
import com.google.android.gms.internal.zzcwc;
import com.google.android.gms.internal.zzcwg;
import com.google.android.gms.internal.zzcwo;
import java.util.Set;

public final class zzcy extends zzcwg implements ConnectionCallbacks, OnConnectionFailedListener {
    private static zza<? extends zzcwb, zzcwc> zzfrz = zzcvy.zzdyi;
    private final Context mContext;
    private final Handler mHandler;
    private Set<Scope> zzees;
    private final zza<? extends zzcwb, zzcwc> zzfiy;
    private zzr zzfnd;
    private zzcwb zzfoj;
    private zzda zzfsa;

    @WorkerThread
    public zzcy(Context context, Handler handler, @NonNull zzr com_google_android_gms_common_internal_zzr) {
        this(context, handler, com_google_android_gms_common_internal_zzr, zzfrz);
    }

    @WorkerThread
    public zzcy(Context context, Handler handler, @NonNull zzr com_google_android_gms_common_internal_zzr, zza<? extends zzcwb, zzcwc> com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc) {
        this.mContext = context;
        this.mHandler = handler;
        this.zzfnd = (zzr) zzbq.checkNotNull(com_google_android_gms_common_internal_zzr, "ClientSettings must not be null");
        this.zzees = com_google_android_gms_common_internal_zzr.zzakj();
        this.zzfiy = com_google_android_gms_common_api_Api_zza__extends_com_google_android_gms_internal_zzcwb__com_google_android_gms_internal_zzcwc;
    }

    @WorkerThread
    private final void zzc(zzcwo com_google_android_gms_internal_zzcwo) {
        ConnectionResult zzagt = com_google_android_gms_internal_zzcwo.zzagt();
        if (zzagt.isSuccess()) {
            zzbt zzbcw = com_google_android_gms_internal_zzcwo.zzbcw();
            ConnectionResult zzagt2 = zzbcw.zzagt();
            if (zzagt2.isSuccess()) {
                this.zzfsa.zzb(zzbcw.zzald(), this.zzees);
            } else {
                String valueOf = String.valueOf(zzagt2);
                Log.wtf("SignInCoordinator", new StringBuilder(String.valueOf(valueOf).length() + 48).append("Sign-in succeeded with resolve account failure: ").append(valueOf).toString(), new Exception());
                this.zzfsa.zzh(zzagt2);
                this.zzfoj.disconnect();
                return;
            }
        }
        this.zzfsa.zzh(zzagt);
        this.zzfoj.disconnect();
    }

    @WorkerThread
    public final void onConnected(@Nullable Bundle bundle) {
        this.zzfoj.zza(this);
    }

    @WorkerThread
    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        this.zzfsa.zzh(connectionResult);
    }

    @WorkerThread
    public final void onConnectionSuspended(int i) {
        this.zzfoj.disconnect();
    }

    @WorkerThread
    public final void zza(zzda com_google_android_gms_common_api_internal_zzda) {
        if (this.zzfoj != null) {
            this.zzfoj.disconnect();
        }
        this.zzfnd.zzc(Integer.valueOf(System.identityHashCode(this)));
        this.zzfoj = (zzcwb) this.zzfiy.zza(this.mContext, this.mHandler.getLooper(), this.zzfnd, this.zzfnd.zzakp(), this, this);
        this.zzfsa = com_google_android_gms_common_api_internal_zzda;
        this.zzfoj.connect();
    }

    public final zzcwb zzais() {
        return this.zzfoj;
    }

    public final void zzaje() {
        if (this.zzfoj != null) {
            this.zzfoj.disconnect();
        }
    }

    @BinderThread
    public final void zzb(zzcwo com_google_android_gms_internal_zzcwo) {
        this.mHandler.post(new zzcz(this, com_google_android_gms_internal_zzcwo));
    }
}
