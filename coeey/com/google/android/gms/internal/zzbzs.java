package com.google.android.gms.internal;

import android.os.RemoteException;

public final class zzbzs extends zzbzn<String> {
    public zzbzs(int i, String str, String str2) {
        super(0, str, str2);
    }

    private final String zze(zzbzv com_google_android_gms_internal_zzbzv) {
        try {
            return com_google_android_gms_internal_zzbzv.getStringFlagValue(getKey(), (String) zzip(), getSource());
        } catch (RemoteException e) {
            return (String) zzip();
        }
    }

    public final /* synthetic */ Object zza(zzbzv com_google_android_gms_internal_zzbzv) {
        return zze(com_google_android_gms_internal_zzbzv);
    }
}
