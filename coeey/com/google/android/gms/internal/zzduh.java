package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.firebase.auth.PhoneAuthCredential;

final class zzduh<ResultT, CallbackT> extends zzdtu<zzdve, ResultT> implements zzdvq<ResultT> {
    private TaskCompletionSource<ResultT> zzeay;
    private final String zzmai;
    private zzdvr<ResultT, CallbackT> zzmaj;

    public zzduh(zzdvr<ResultT, CallbackT> com_google_android_gms_internal_zzdvr_ResultT__CallbackT, String str) {
        this.zzmaj = com_google_android_gms_internal_zzdvr_ResultT__CallbackT;
        this.zzmai = str;
    }

    protected final /* synthetic */ void zza(zzb com_google_android_gms_common_api_Api_zzb, TaskCompletionSource taskCompletionSource) throws RemoteException {
        zzdve com_google_android_gms_internal_zzdve = (zzdve) com_google_android_gms_common_api_Api_zzb;
        this.zzeay = taskCompletionSource;
        zzdvr com_google_android_gms_internal_zzdvr = this.zzmaj;
        com_google_android_gms_internal_zzdvr.zzmaw = com_google_android_gms_internal_zzdve.zzbpu();
        com_google_android_gms_internal_zzdvr.dispatch();
    }

    public final void zza(ResultT resultT, Status status) {
        zzbq.checkNotNull(this.zzeay, "doExecute must be called before onComplete");
        if (status == null) {
            this.zzeay.setResult(resultT);
        } else if (this.zzmaj.zzmbj != null) {
            this.zzeay.setException(zzdvg.zzb(status, (PhoneAuthCredential) this.zzmaj.zzmbj.clone()));
            this.zzmaj.zzmbj = null;
        } else {
            this.zzeay.setException(zzdvg.zzao(status));
        }
    }

    final String zzbps() {
        return this.zzmai;
    }
}
