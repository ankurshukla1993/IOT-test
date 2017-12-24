package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import com.google.android.gms.auth.api.signin.internal.zzz;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

final class zzbh implements ResultCallback<Status> {
    private /* synthetic */ GoogleApiClient zzepc;
    private /* synthetic */ zzbd zzfpp;
    private /* synthetic */ zzdc zzfpr;
    private /* synthetic */ boolean zzfps;

    zzbh(zzbd com_google_android_gms_common_api_internal_zzbd, zzdc com_google_android_gms_common_api_internal_zzdc, boolean z, GoogleApiClient googleApiClient) {
        this.zzfpp = com_google_android_gms_common_api_internal_zzbd;
        this.zzfpr = com_google_android_gms_common_api_internal_zzdc;
        this.zzfps = z;
        this.zzepc = googleApiClient;
    }

    public final /* synthetic */ void onResult(@NonNull Result result) {
        Status status = (Status) result;
        zzz.zzbr(this.zzfpp.mContext).zzabi();
        if (status.isSuccess() && this.zzfpp.isConnected()) {
            this.zzfpp.reconnect();
        }
        this.zzfpr.setResult(status);
        if (this.zzfps) {
            this.zzepc.disconnect();
        }
    }
}
