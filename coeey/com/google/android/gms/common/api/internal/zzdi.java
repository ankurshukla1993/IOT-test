package com.google.android.gms.common.api.internal;

import android.os.Looper;
import android.support.annotation.NonNull;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Releasable;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.ResultCallbacks;
import com.google.android.gms.common.api.ResultTransform;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.TransformedResult;
import com.google.android.gms.common.internal.zzbq;
import java.lang.ref.WeakReference;

public final class zzdi<R extends Result> extends TransformedResult<R> implements ResultCallback<R> {
    private final Object zzflz = new Object();
    private final WeakReference<GoogleApiClient> zzfmb;
    private ResultTransform<? super R, ? extends Result> zzfsd = null;
    private zzdi<? extends Result> zzfse = null;
    private volatile ResultCallbacks<? super R> zzfsf = null;
    private PendingResult<R> zzfsg = null;
    private Status zzfsh = null;
    private final zzdk zzfsi;
    private boolean zzfsj = false;

    public zzdi(WeakReference<GoogleApiClient> weakReference) {
        zzbq.checkNotNull(weakReference, "GoogleApiClient reference must not be null");
        this.zzfmb = weakReference;
        GoogleApiClient googleApiClient = (GoogleApiClient) this.zzfmb.get();
        this.zzfsi = new zzdk(this, googleApiClient != null ? googleApiClient.getLooper() : Looper.getMainLooper());
    }

    private final void zzajf() {
        if (this.zzfsd != null || this.zzfsf != null) {
            GoogleApiClient googleApiClient = (GoogleApiClient) this.zzfmb.get();
            if (!(this.zzfsj || this.zzfsd == null || googleApiClient == null)) {
                googleApiClient.zza(this);
                this.zzfsj = true;
            }
            if (this.zzfsh != null) {
                zzx(this.zzfsh);
            } else if (this.zzfsg != null) {
                this.zzfsg.setResultCallback(this);
            }
        }
    }

    private final boolean zzajh() {
        return (this.zzfsf == null || ((GoogleApiClient) this.zzfmb.get()) == null) ? false : true;
    }

    private static void zzd(Result result) {
        if (result instanceof Releasable) {
            try {
                ((Releasable) result).release();
            } catch (Throwable e) {
                String valueOf = String.valueOf(result);
                Log.w("TransformedResultImpl", new StringBuilder(String.valueOf(valueOf).length() + 18).append("Unable to release ").append(valueOf).toString(), e);
            }
        }
    }

    private final void zzd(Status status) {
        synchronized (this.zzflz) {
            this.zzfsh = status;
            zzx(this.zzfsh);
        }
    }

    private final void zzx(Status status) {
        synchronized (this.zzflz) {
            if (this.zzfsd != null) {
                Status onFailure = this.zzfsd.onFailure(status);
                zzbq.checkNotNull(onFailure, "onFailure must not return null");
                this.zzfse.zzd(onFailure);
            } else if (zzajh()) {
                this.zzfsf.onFailure(status);
            }
        }
    }

    public final void andFinally(@NonNull ResultCallbacks<? super R> resultCallbacks) {
        boolean z = true;
        synchronized (this.zzflz) {
            zzbq.zza(this.zzfsf == null, (Object) "Cannot call andFinally() twice.");
            if (this.zzfsd != null) {
                z = false;
            }
            zzbq.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzfsf = resultCallbacks;
            zzajf();
        }
    }

    public final void onResult(R r) {
        synchronized (this.zzflz) {
            if (!r.getStatus().isSuccess()) {
                zzd(r.getStatus());
                zzd((Result) r);
            } else if (this.zzfsd != null) {
                zzcv.zzaid().submit(new zzdj(this, r));
            } else if (zzajh()) {
                this.zzfsf.onSuccess(r);
            }
        }
    }

    @NonNull
    public final <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> resultTransform) {
        TransformedResult com_google_android_gms_common_api_internal_zzdi;
        boolean z = true;
        synchronized (this.zzflz) {
            zzbq.zza(this.zzfsd == null, (Object) "Cannot call then() twice.");
            if (this.zzfsf != null) {
                z = false;
            }
            zzbq.zza(z, (Object) "Cannot call then() and andFinally() on the same TransformedResult.");
            this.zzfsd = resultTransform;
            com_google_android_gms_common_api_internal_zzdi = new zzdi(this.zzfmb);
            this.zzfse = com_google_android_gms_common_api_internal_zzdi;
            zzajf();
        }
        return com_google_android_gms_common_api_internal_zzdi;
    }

    public final void zza(PendingResult<?> pendingResult) {
        synchronized (this.zzflz) {
            this.zzfsg = pendingResult;
            zzajf();
        }
    }

    final void zzajg() {
        this.zzfsf = null;
    }
}
