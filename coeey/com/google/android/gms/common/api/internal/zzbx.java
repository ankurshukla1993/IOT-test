package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzj;
import java.util.Set;

final class zzbx implements zzda, zzj {
    private Set<Scope> zzees = null;
    private final zzh<?> zzfjl;
    private final zze zzfnb;
    private zzan zzfon = null;
    final /* synthetic */ zzbp zzfqo;
    private boolean zzfra = false;

    public zzbx(zzbp com_google_android_gms_common_api_internal_zzbp, zze com_google_android_gms_common_api_Api_zze, zzh<?> com_google_android_gms_common_api_internal_zzh_) {
        this.zzfqo = com_google_android_gms_common_api_internal_zzbp;
        this.zzfnb = com_google_android_gms_common_api_Api_zze;
        this.zzfjl = com_google_android_gms_common_api_internal_zzh_;
    }

    @WorkerThread
    private final void zzaiu() {
        if (this.zzfra && this.zzfon != null) {
            this.zzfnb.zza(this.zzfon, this.zzees);
        }
    }

    @WorkerThread
    public final void zzb(zzan com_google_android_gms_common_internal_zzan, Set<Scope> set) {
        if (com_google_android_gms_common_internal_zzan == null || set == null) {
            Log.wtf("GoogleApiManager", "Received null response from onSignInSuccess", new Exception());
            zzh(new ConnectionResult(4));
            return;
        }
        this.zzfon = com_google_android_gms_common_internal_zzan;
        this.zzees = set;
        zzaiu();
    }

    public final void zzf(@NonNull ConnectionResult connectionResult) {
        this.zzfqo.mHandler.post(new zzby(this, connectionResult));
    }

    @WorkerThread
    public final void zzh(ConnectionResult connectionResult) {
        ((zzbr) this.zzfqo.zzfne.get(this.zzfjl)).zzh(connectionResult);
    }
}
