package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzed;

public final class zzav extends zzed implements zzat {
    zzav(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.common.internal.ICertData");
    }

    public final IObjectWrapper zzafo() throws RemoteException {
        Parcel zza = zza(1, zzaz());
        IObjectWrapper zzap = zza.zzap(zza.readStrongBinder());
        zza.recycle();
        return zzap;
    }

    public final int zzafp() throws RemoteException {
        Parcel zza = zza(2, zzaz());
        int readInt = zza.readInt();
        zza.recycle();
        return readInt;
    }
}
