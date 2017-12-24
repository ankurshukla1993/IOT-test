package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zzn;

final class zzbev extends zzbep {
    private final zzn<Status> zzfzc;

    public zzbev(zzn<Status> com_google_android_gms_common_api_internal_zzn_com_google_android_gms_common_api_Status) {
        this.zzfzc = com_google_android_gms_common_api_internal_zzn_com_google_android_gms_common_api_Status;
    }

    public final void zzci(int i) throws RemoteException {
        this.zzfzc.setResult(new Status(i));
    }
}
