package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbzq extends zzbzn<Integer> {
    public zzbzq(int i, String str, Integer num) {
        super(0, str, num);
    }

    private final Integer zzc(zzbzv com_google_android_gms_internal_zzbzv) {
        try {
            return Integer.valueOf(com_google_android_gms_internal_zzbzv.getIntFlagValue(getKey(), ((Integer) zzip()).intValue(), getSource()));
        } catch (RemoteException e) {
            return (Integer) zzip();
        }
    }

    public final /* synthetic */ Object zza(zzbzv com_google_android_gms_internal_zzbzv) {
        return zzc(com_google_android_gms_internal_zzbzv);
    }
}
