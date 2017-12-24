package com.google.android.gms.ads.identifier;

import com.facebook.appevents.AppEventsConstants;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import java.util.HashMap;
import java.util.Map;

final /* synthetic */ class zzc implements Runnable {
    private final Info zzamk;
    private final boolean zzaml;
    private final long zzamm;

    zzc(Info info, boolean z, long j) {
        this.zzamk = info;
        this.zzaml = z;
        this.zzamm = j;
    }

    public final void run() {
        Info info = this.zzamk;
        boolean z = this.zzaml;
        long j = this.zzamm;
        Map hashMap = new HashMap();
        hashMap.put("ad_id_size", Integer.toString(info == null ? -1 : info.getId().length()));
        hashMap.put("has_gmscore", z ? AppEventsConstants.EVENT_PARAM_VALUE_YES : AppEventsConstants.EVENT_PARAM_VALUE_NO);
        hashMap.put(JobStorage.COLUMN_TAG, "AdvertisingIdLightClient");
        hashMap.put("time_spent", Long.toString(j));
        new zze().zzc(hashMap);
    }
}
