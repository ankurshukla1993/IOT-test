package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.zzbq;

public class zzak extends zzo {
    private zzbp zzfjo;
    private final ArraySet<zzh<?>> zzfny = new ArraySet();

    private zzak(zzci com_google_android_gms_common_api_internal_zzci) {
        super(com_google_android_gms_common_api_internal_zzci);
        this.zzfrj.zza("ConnectionlessLifecycleHelper", (LifecycleCallback) this);
    }

    public static void zza(Activity activity, zzbp com_google_android_gms_common_api_internal_zzbp, zzh<?> com_google_android_gms_common_api_internal_zzh_) {
        LifecycleCallback.zzn(activity);
        zzci zzn = LifecycleCallback.zzn(activity);
        zzak com_google_android_gms_common_api_internal_zzak = (zzak) zzn.zza("ConnectionlessLifecycleHelper", zzak.class);
        if (com_google_android_gms_common_api_internal_zzak == null) {
            com_google_android_gms_common_api_internal_zzak = new zzak(zzn);
        }
        com_google_android_gms_common_api_internal_zzak.zzfjo = com_google_android_gms_common_api_internal_zzbp;
        zzbq.checkNotNull(com_google_android_gms_common_api_internal_zzh_, "ApiKey cannot be null");
        com_google_android_gms_common_api_internal_zzak.zzfny.add(com_google_android_gms_common_api_internal_zzh_);
        com_google_android_gms_common_api_internal_zzbp.zza(com_google_android_gms_common_api_internal_zzak);
    }

    private final void zzahm() {
        if (!this.zzfny.isEmpty()) {
            this.zzfjo.zza(this);
        }
    }

    public final void onResume() {
        super.onResume();
        zzahm();
    }

    public final void onStart() {
        super.onStart();
        zzahm();
    }

    public final void onStop() {
        super.onStop();
        this.zzfjo.zzb(this);
    }

    protected final void zza(ConnectionResult connectionResult, int i) {
        this.zzfjo.zza(connectionResult, i);
    }

    protected final void zzagm() {
        this.zzfjo.zzagm();
    }

    final ArraySet<zzh<?>> zzahl() {
        return this.zzfny;
    }
}
