package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;

public final class zzbdq extends zzed implements zzbdp {
    zzbdq(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.clearcut.internal.IClearcutLoggerService");
    }

    public final void zza(zzbdn com_google_android_gms_internal_zzbdn, zzbde com_google_android_gms_internal_zzbde) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzbdn);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzbde);
        zzc(1, zzaz);
    }
}
