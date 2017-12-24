package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbzp extends zzbzn<Boolean> {
    public zzbzp(int i, String str, Boolean bool) {
        super(0, str, bool);
    }

    private final Boolean zzb(zzbzv com_google_android_gms_internal_zzbzv) {
        try {
            return Boolean.valueOf(com_google_android_gms_internal_zzbzv.getBooleanFlagValue(getKey(), ((Boolean) zzip()).booleanValue(), getSource()));
        } catch (RemoteException e) {
            return (Boolean) zzip();
        }
    }

    public final /* synthetic */ Object zza(zzbzv com_google_android_gms_internal_zzbzv) {
        return zzb(com_google_android_gms_internal_zzbzv);
    }
}
