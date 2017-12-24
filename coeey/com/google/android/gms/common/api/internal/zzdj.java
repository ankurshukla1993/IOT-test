package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

final class zzdj implements Runnable {
    private /* synthetic */ Result zzfsk;
    private /* synthetic */ zzdi zzfsl;

    zzdj(zzdi com_google_android_gms_common_api_internal_zzdi, Result result) {
        this.zzfsl = com_google_android_gms_common_api_internal_zzdi;
        this.zzfsk = result;
    }

    @WorkerThread
    public final void run() {
        GoogleApiClient googleApiClient;
        try {
            zzs.zzfly.set(Boolean.valueOf(true));
            this.zzfsl.zzfsi.sendMessage(this.zzfsl.zzfsi.obtainMessage(0, this.zzfsl.zzfsd.onSuccess(this.zzfsk)));
            zzs.zzfly.set(Boolean.valueOf(false));
            zzdi.zzd(this.zzfsk);
            googleApiClient = (GoogleApiClient) this.zzfsl.zzfmb.get();
            if (googleApiClient != null) {
                googleApiClient.zzb(this.zzfsl);
            }
        } catch (RuntimeException e) {
            this.zzfsl.zzfsi.sendMessage(this.zzfsl.zzfsi.obtainMessage(1, e));
            zzs.zzfly.set(Boolean.valueOf(false));
            zzdi.zzd(this.zzfsk);
            googleApiClient = (GoogleApiClient) this.zzfsl.zzfmb.get();
            if (googleApiClient != null) {
                googleApiClient.zzb(this.zzfsl);
            }
        } catch (Throwable th) {
            Throwable th2 = th;
            zzs.zzfly.set(Boolean.valueOf(false));
            zzdi.zzd(this.zzfsk);
            googleApiClient = (GoogleApiClient) this.zzfsl.zzfmb.get();
            if (googleApiClient != null) {
                googleApiClient.zzb(this.zzfsl);
            }
        }
    }
}
