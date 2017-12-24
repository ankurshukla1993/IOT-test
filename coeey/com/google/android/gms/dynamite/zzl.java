package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzed;
import com.google.android.gms.internal.zzef;

public final class zzl extends zzed implements zzk {
    zzl(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.dynamite.IDynamiteLoader");
    }

    public final int zza(IObjectWrapper iObjectWrapper, String str, boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) iObjectWrapper);
        zzaz.writeString(str);
        zzef.zza(zzaz, z);
        zzaz = zza(3, zzaz);
        int readInt = zzaz.readInt();
        zzaz.recycle();
        return readInt;
    }

    public final IObjectWrapper zza(IObjectWrapper iObjectWrapper, String str, int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) iObjectWrapper);
        zzaz.writeString(str);
        zzaz.writeInt(i);
        zzaz = zza(2, zzaz);
        IObjectWrapper zzap = zza.zzap(zzaz.readStrongBinder());
        zzaz.recycle();
        return zzap;
    }
}
