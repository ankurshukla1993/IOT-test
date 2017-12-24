package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public final class zzbfc extends zzed implements zzbfb {
    zzbfc(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.service.ICommonService");
    }

    public final void zza(zzbez com_google_android_gms_internal_zzbez) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzbez);
        zzc(1, zzaz);
    }
}
