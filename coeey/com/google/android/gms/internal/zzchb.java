package com.google.android.gms.internal;

import android.content.BroadcastReceiver.PendingResult;
import android.content.Context;
import android.os.Bundle;
import com.facebook.react.views.scroll.ReactScrollViewHelper;
import com.google.android.gms.measurement.AppMeasurement;

final class zzchb implements Runnable {
    private /* synthetic */ Context val$context;
    private /* synthetic */ PendingResult zzdlr;
    private /* synthetic */ zzchj zzjay;
    private /* synthetic */ long zzjaz;
    private /* synthetic */ Bundle zzjba;
    private /* synthetic */ zzcgj zzjbb;

    zzchb(zzcha com_google_android_gms_internal_zzcha, zzchj com_google_android_gms_internal_zzchj, long j, Bundle bundle, Context context, zzcgj com_google_android_gms_internal_zzcgj, PendingResult pendingResult) {
        this.zzjay = com_google_android_gms_internal_zzchj;
        this.zzjaz = j;
        this.zzjba = bundle;
        this.val$context = context;
        this.zzjbb = com_google_android_gms_internal_zzcgj;
        this.zzdlr = pendingResult;
    }

    public final void run() {
        zzckm zzag = this.zzjay.zzawg().zzag(this.zzjay.zzawb().getAppId(), "_fot");
        long longValue = (zzag == null || !(zzag.mValue instanceof Long)) ? 0 : ((Long) zzag.mValue).longValue();
        long j = this.zzjaz;
        longValue = (longValue <= 0 || (j < longValue && j > 0)) ? j : longValue - 1;
        if (longValue > 0) {
            this.zzjba.putLong("click_timestamp", longValue);
        }
        AppMeasurement.getInstance(this.val$context).logEventInternal(ReactScrollViewHelper.AUTO, "_cmp", this.zzjba);
        this.zzjbb.zzayx().log("Install campaign recorded");
        if (this.zzdlr != null) {
            this.zzdlr.finish();
        }
    }
}
