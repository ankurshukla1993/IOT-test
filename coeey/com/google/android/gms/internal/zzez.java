package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import io.fabric.sdk.android.services.common.AdvertisingInfoServiceStrategy.AdvertisingInterface;

public final class zzez extends zzed implements zzex {
    zzez(IBinder iBinder) {
        super(iBinder, AdvertisingInterface.ADVERTISING_ID_SERVICE_INTERFACE_TOKEN);
    }

    public final String getId() throws RemoteException {
        Parcel zza = zza(1, zzaz());
        String readString = zza.readString();
        zza.recycle();
        return readString;
    }

    public final boolean zzb(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, true);
        zzaz = zza(2, zzaz);
        boolean zza = zzef.zza(zzaz);
        zzaz.recycle();
        return zza;
    }
}
