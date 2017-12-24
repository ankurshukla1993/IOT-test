package com.google.android.gms.internal;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzm;

final class zzbdj extends zzm<Status, zzbdl> {
    private final zzbde zzfhm;

    zzbdj(zzbde com_google_android_gms_internal_zzbde, GoogleApiClient googleApiClient) {
        super(zzbcv.API, googleApiClient);
        this.zzfhm = com_google_android_gms_internal_zzbde;
    }

    public final /* bridge */ /* synthetic */ void setResult(Object obj) {
        super.setResult((Status) obj);
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb) throws RemoteException {
        zzbdl com_google_android_gms_internal_zzbdl = (zzbdl) com_google_android_gms_common_api_Api_zzb;
        zzbdn com_google_android_gms_internal_zzbdk = new zzbdk(this);
        try {
            zzbde com_google_android_gms_internal_zzbde = this.zzfhm;
            if (com_google_android_gms_internal_zzbde.zzfgt != null && com_google_android_gms_internal_zzbde.zzfha.zzpkn.length == 0) {
                com_google_android_gms_internal_zzbde.zzfha.zzpkn = com_google_android_gms_internal_zzbde.zzfgt.zzafk();
            }
            if (com_google_android_gms_internal_zzbde.zzfhl != null && com_google_android_gms_internal_zzbde.zzfha.zzpku.length == 0) {
                com_google_android_gms_internal_zzbde.zzfha.zzpku = com_google_android_gms_internal_zzbde.zzfhl.zzafk();
            }
            com_google_android_gms_internal_zzbde.zzfhg = zzfhk.zzc(com_google_android_gms_internal_zzbde.zzfha);
            ((zzbdp) com_google_android_gms_internal_zzbdl.zzakb()).zza(com_google_android_gms_internal_zzbdk, this.zzfhm);
        } catch (Throwable e) {
            Log.e("ClearcutLoggerApiImpl", "derived ClearcutLogger.MessageProducer ", e);
            zzu(new Status(10, "MessageProducer"));
        }
    }

    protected final /* synthetic */ Result zzb(Status status) {
        return status;
    }
}
