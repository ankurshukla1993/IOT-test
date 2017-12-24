package com.google.android.gms.auth.api.signin.internal;

import android.content.Context;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.internal.zzcx;
import java.util.Set;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public final class zzb extends AsyncTaskLoader<Void> implements zzcx {
    private Semaphore zzeew = new Semaphore(0);
    private Set<GoogleApiClient> zzeex;

    public zzb(Context context, Set<GoogleApiClient> set) {
        super(context);
        this.zzeex = set;
    }

    private final Void zzaau() {
        int i = 0;
        for (GoogleApiClient zza : this.zzeex) {
            i = zza.zza((zzcx) this) ? i + 1 : i;
        }
        try {
            this.zzeew.tryAcquire(i, 5, TimeUnit.SECONDS);
        } catch (Throwable e) {
            Log.i("GACSignInLoader", "Unexpected InterruptedException", e);
            Thread.currentThread().interrupt();
        }
        return null;
    }

    public final /* synthetic */ Object loadInBackground() {
        return zzaau();
    }

    protected final void onStartLoading() {
        this.zzeew.drainPermits();
        forceLoad();
    }

    public final void zzaav() {
        this.zzeew.release();
    }
}
