package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzn extends zzed implements zzm {
    zzn(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
    }

    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i, IObjectWrapper iObjectWrapper2) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) iObjectWrapper);
        zzaz.writeString(str);
        zzaz.writeInt(i);
        zzef.zza(zzaz, (IInterface) iObjectWrapper2);
        zzaz = zza(2, zzaz);
        IObjectWrapper zzap = zza.zzap(zzaz.readStrongBinder());
        zzaz.recycle();
        return zzap;
    }
}
