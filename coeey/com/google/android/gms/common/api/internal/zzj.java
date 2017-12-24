package com.google.android.gms.common.api.internal;

import android.support.v4.util.ArrayMap;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Set;

public final class zzj {
    private final ArrayMap<zzh<?>, ConnectionResult> zzfjc = new ArrayMap();
    private final TaskCompletionSource<Void> zzfli = new TaskCompletionSource();
    private int zzflj;
    private boolean zzflk = false;

    public zzj(Iterable<? extends GoogleApi<?>> iterable) {
        for (GoogleApi zzaga : iterable) {
            this.zzfjc.put(zzaga.zzaga(), null);
        }
        this.zzflj = this.zzfjc.keySet().size();
    }

    public final Task<Void> getTask() {
        return this.zzfli.getTask();
    }

    public final void zza(zzh<?> com_google_android_gms_common_api_internal_zzh_, ConnectionResult connectionResult) {
        this.zzfjc.put(com_google_android_gms_common_api_internal_zzh_, connectionResult);
        this.zzflj--;
        if (!connectionResult.isSuccess()) {
            this.zzflk = true;
        }
        if (this.zzflj != 0) {
            return;
        }
        if (this.zzflk) {
            this.zzfli.setException(new AvailabilityException(this.zzfjc));
            return;
        }
        this.zzfli.setResult(null);
    }

    public final Set<zzh<?>> zzagn() {
        return this.zzfjc.keySet();
    }

    public final void zzago() {
        this.zzfli.setResult(null);
    }
}
