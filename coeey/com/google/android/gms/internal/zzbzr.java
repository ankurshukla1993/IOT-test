package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbzr extends zzbzn<Long> {
    public zzbzr(int i, String str, Long l) {
        super(0, str, l);
    }

    private final Long zzd(zzbzv com_google_android_gms_internal_zzbzv) {
        try {
            return Long.valueOf(com_google_android_gms_internal_zzbzv.getLongFlagValue(getKey(), ((Long) zzip()).longValue(), getSource()));
        } catch (RemoteException e) {
            return (Long) zzip();
        }
    }

    public final /* synthetic */ Object zza(zzbzv com_google_android_gms_internal_zzbzv) {
        return zzd(com_google_android_gms_internal_zzbzv);
    }
}
