package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;

final class zzag implements OnCompleteListener<Void> {
    private /* synthetic */ zzad zzfns;
    private zzcx zzfnt;

    zzag(zzad com_google_android_gms_common_api_internal_zzad, zzcx com_google_android_gms_common_api_internal_zzcx) {
        this.zzfns = com_google_android_gms_common_api_internal_zzad;
        this.zzfnt = com_google_android_gms_common_api_internal_zzcx;
    }

    final void cancel() {
        this.zzfnt.zzaav();
    }

    public final void onComplete(@NonNull Task<Void> task) {
        this.zzfns.zzfmy.lock();
        try {
            if (this.zzfns.zzfnn) {
                if (task.isSuccessful()) {
                    this.zzfns.zzfnp = new ArrayMap(this.zzfns.zzfnf.size());
                    for (zzac zzaga : this.zzfns.zzfnf.values()) {
                        this.zzfns.zzfnp.put(zzaga.zzaga(), ConnectionResult.zzfhy);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    if (this.zzfns.zzfnl) {
                        this.zzfns.zzfnp = new ArrayMap(this.zzfns.zzfnf.size());
                        for (zzac com_google_android_gms_common_api_internal_zzac : this.zzfns.zzfnf.values()) {
                            zzh zzaga2 = com_google_android_gms_common_api_internal_zzac.zzaga();
                            ConnectionResult connectionResult = availabilityException.getConnectionResult(com_google_android_gms_common_api_internal_zzac);
                            if (this.zzfns.zza(com_google_android_gms_common_api_internal_zzac, connectionResult)) {
                                this.zzfns.zzfnp.put(zzaga2, new ConnectionResult(16));
                            } else {
                                this.zzfns.zzfnp.put(zzaga2, connectionResult);
                            }
                        }
                    } else {
                        this.zzfns.zzfnp = availabilityException.zzafw();
                    }
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    this.zzfns.zzfnp = Collections.emptyMap();
                }
                if (this.zzfns.isConnected()) {
                    this.zzfns.zzfno.putAll(this.zzfns.zzfnp);
                    if (this.zzfns.zzahh() == null) {
                        this.zzfns.zzahf();
                        this.zzfns.zzahg();
                        this.zzfns.zzfnj.signalAll();
                    }
                }
                this.zzfnt.zzaav();
                this.zzfns.zzfmy.unlock();
                return;
            }
            this.zzfnt.zzaav();
        } finally {
            this.zzfns.zzfmy.unlock();
        }
    }
}
