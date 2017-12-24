package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.AvailabilityException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import java.util.Collections;

final class zzaf implements OnCompleteListener<Void> {
    private /* synthetic */ zzad zzfns;

    private zzaf(zzad com_google_android_gms_common_api_internal_zzad) {
        this.zzfns = com_google_android_gms_common_api_internal_zzad;
    }

    public final void onComplete(@NonNull Task<Void> task) {
        this.zzfns.zzfmy.lock();
        try {
            if (this.zzfns.zzfnn) {
                if (task.isSuccessful()) {
                    this.zzfns.zzfno = new ArrayMap(this.zzfns.zzfne.size());
                    for (zzac zzaga : this.zzfns.zzfne.values()) {
                        this.zzfns.zzfno.put(zzaga.zzaga(), ConnectionResult.zzfhy);
                    }
                } else if (task.getException() instanceof AvailabilityException) {
                    AvailabilityException availabilityException = (AvailabilityException) task.getException();
                    if (this.zzfns.zzfnl) {
                        this.zzfns.zzfno = new ArrayMap(this.zzfns.zzfne.size());
                        for (zzac com_google_android_gms_common_api_internal_zzac : this.zzfns.zzfne.values()) {
                            zzh zzaga2 = com_google_android_gms_common_api_internal_zzac.zzaga();
                            ConnectionResult connectionResult = availabilityException.getConnectionResult(com_google_android_gms_common_api_internal_zzac);
                            if (this.zzfns.zza(com_google_android_gms_common_api_internal_zzac, connectionResult)) {
                                this.zzfns.zzfno.put(zzaga2, new ConnectionResult(16));
                            } else {
                                this.zzfns.zzfno.put(zzaga2, connectionResult);
                            }
                        }
                    } else {
                        this.zzfns.zzfno = availabilityException.zzafw();
                    }
                    this.zzfns.zzfnr = this.zzfns.zzahh();
                } else {
                    Log.e("ConnectionlessGAC", "Unexpected availability exception", task.getException());
                    this.zzfns.zzfno = Collections.emptyMap();
                    this.zzfns.zzfnr = new ConnectionResult(8);
                }
                if (this.zzfns.zzfnp != null) {
                    this.zzfns.zzfno.putAll(this.zzfns.zzfnp);
                    this.zzfns.zzfnr = this.zzfns.zzahh();
                }
                if (this.zzfns.zzfnr == null) {
                    this.zzfns.zzahf();
                    this.zzfns.zzahg();
                } else {
                    this.zzfns.zzfnn = false;
                    this.zzfns.zzfnh.zzc(this.zzfns.zzfnr);
                }
                this.zzfns.zzfnj.signalAll();
                this.zzfns.zzfmy.unlock();
            }
        } finally {
            this.zzfns.zzfmy.unlock();
        }
    }
}
