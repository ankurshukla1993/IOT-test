package com.google.android.gms.common.api.internal;

import android.os.IBinder;
import android.os.RemoteException;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.Api.zze;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzdl {
    public static final Status zzfsm = new Status(8, "The connection to Google Play services was lost");
    private static final zzs<?>[] zzfsn = new zzs[0];
    private final Map<zzc<?>, zze> zzfph;
    final Set<zzs<?>> zzfso = Collections.synchronizedSet(Collections.newSetFromMap(new WeakHashMap()));
    private final zzdo zzfsp = new zzdm(this);

    public zzdl(Map<zzc<?>, zze> map) {
        this.zzfph = map;
    }

    public final void release() {
        com.google.android.gms.common.api.zze com_google_android_gms_common_api_zze = null;
        for (PendingResult pendingResult : (zzs[]) this.zzfso.toArray(zzfsn)) {
            pendingResult.zza(com_google_android_gms_common_api_zze);
            if (pendingResult.zzagi() != null) {
                pendingResult.setResultCallback(com_google_android_gms_common_api_zze);
                IBinder zzafv = ((zze) this.zzfph.get(((zzm) pendingResult).zzaft())).zzafv();
                if (pendingResult.isReady()) {
                    pendingResult.zza(new zzdn(pendingResult, com_google_android_gms_common_api_zze, zzafv, com_google_android_gms_common_api_zze));
                } else if (zzafv == null || !zzafv.isBinderAlive()) {
                    pendingResult.zza(com_google_android_gms_common_api_zze);
                    pendingResult.cancel();
                    com_google_android_gms_common_api_zze.remove(pendingResult.zzagi().intValue());
                } else {
                    Object com_google_android_gms_common_api_internal_zzdn = new zzdn(pendingResult, com_google_android_gms_common_api_zze, zzafv, com_google_android_gms_common_api_zze);
                    pendingResult.zza(com_google_android_gms_common_api_internal_zzdn);
                    try {
                        zzafv.linkToDeath(com_google_android_gms_common_api_internal_zzdn, 0);
                    } catch (RemoteException e) {
                        pendingResult.cancel();
                        com_google_android_gms_common_api_zze.remove(pendingResult.zzagi().intValue());
                    }
                }
                this.zzfso.remove(pendingResult);
            } else if (pendingResult.zzagv()) {
                this.zzfso.remove(pendingResult);
            }
        }
    }

    public final void zzaji() {
        for (zzs zzv : (zzs[]) this.zzfso.toArray(zzfsn)) {
            zzv.zzv(zzfsm);
        }
    }

    final void zzb(zzs<? extends Result> com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result) {
        this.zzfso.add(com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result);
        com_google_android_gms_common_api_internal_zzs__extends_com_google_android_gms_common_api_Result.zza(this.zzfsp);
    }
}
