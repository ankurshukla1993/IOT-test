package com.google.android.gms.common.api.internal;

import android.app.Activity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzb;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.concurrent.CancellationException;

public class zzcq extends zzo {
    private TaskCompletionSource<Void> zzeay = new TaskCompletionSource();

    private zzcq(zzci com_google_android_gms_common_api_internal_zzci) {
        super(com_google_android_gms_common_api_internal_zzci);
        this.zzfrj.zza("GmsAvailabilityHelper", (LifecycleCallback) this);
    }

    public static zzcq zzp(Activity activity) {
        zzci zzn = LifecycleCallback.zzn(activity);
        zzcq com_google_android_gms_common_api_internal_zzcq = (zzcq) zzn.zza("GmsAvailabilityHelper", zzcq.class);
        if (com_google_android_gms_common_api_internal_zzcq == null) {
            return new zzcq(zzn);
        }
        if (!com_google_android_gms_common_api_internal_zzcq.zzeay.getTask().isComplete()) {
            return com_google_android_gms_common_api_internal_zzcq;
        }
        com_google_android_gms_common_api_internal_zzcq.zzeay = new TaskCompletionSource();
        return com_google_android_gms_common_api_internal_zzcq;
    }

    public final Task<Void> getTask() {
        return this.zzeay.getTask();
    }

    public final void onDestroy() {
        super.onDestroy();
        this.zzeay.trySetException(new CancellationException("Host activity was destroyed before Google Play services could be made available."));
    }

    protected final void zza(ConnectionResult connectionResult, int i) {
        this.zzeay.setException(zzb.zzy(new Status(connectionResult.getErrorCode(), connectionResult.getErrorMessage(), connectionResult.getResolution())));
    }

    protected final void zzagm() {
        int isGooglePlayServicesAvailable = this.zzfke.isGooglePlayServicesAvailable(this.zzfrj.zzajb());
        if (isGooglePlayServicesAvailable == 0) {
            this.zzeay.setResult(null);
        } else if (!this.zzeay.getTask().isComplete()) {
            zzb(new ConnectionResult(isGooglePlayServicesAvailable, null), 0);
        }
    }
}
