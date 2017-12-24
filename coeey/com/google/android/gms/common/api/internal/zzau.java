package com.google.android.gms.common.api.internal;

import android.support.annotation.WorkerThread;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.internal.zzj;
import java.util.Map;

final class zzau extends zzbb {
    final /* synthetic */ zzar zzfor;
    private final Map<zze, zzat> zzfot;

    public zzau(zzar com_google_android_gms_common_api_internal_zzar, Map<zze, zzat> map) {
        this.zzfor = com_google_android_gms_common_api_internal_zzar;
        super(com_google_android_gms_common_api_internal_zzar);
        this.zzfot = map;
    }

    @WorkerThread
    public final void zzahp() {
        int i;
        int i2 = 1;
        int i3 = 0;
        int i4 = 1;
        int i5 = 0;
        for (zze com_google_android_gms_common_api_Api_zze : this.zzfot.keySet()) {
            if (!com_google_android_gms_common_api_Api_zze.zzafu()) {
                i = 0;
                i4 = i5;
            } else if (!((zzat) this.zzfot.get(com_google_android_gms_common_api_Api_zze)).zzfmm) {
                i = 1;
                break;
            } else {
                i = i4;
                i4 = 1;
            }
            i5 = i4;
            i4 = i;
        }
        i2 = i5;
        i = 0;
        if (i2 != 0) {
            i3 = this.zzfor.zzfni.isGooglePlayServicesAvailable(this.zzfor.mContext);
        }
        if (i3 == 0 || (r0 == 0 && i4 == 0)) {
            if (this.zzfor.zzfol) {
                this.zzfor.zzfoj.connect();
            }
            for (zze com_google_android_gms_common_api_Api_zze2 : this.zzfot.keySet()) {
                zzj com_google_android_gms_common_internal_zzj = (zzj) this.zzfot.get(com_google_android_gms_common_api_Api_zze2);
                if (!com_google_android_gms_common_api_Api_zze2.zzafu() || i3 == 0) {
                    com_google_android_gms_common_api_Api_zze2.zza(com_google_android_gms_common_internal_zzj);
                } else {
                    this.zzfor.zzfob.zza(new zzaw(this, this.zzfor, com_google_android_gms_common_internal_zzj));
                }
            }
            return;
        }
        this.zzfor.zzfob.zza(new zzav(this, this.zzfor, new ConnectionResult(i3, null)));
    }
}
