package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzbzx extends zzed implements zzbzv {
    zzbzx(IBinder iBinder) {
        super(iBinder, "com.google.android.gms.flags.IFlagProvider");
    }

    public final boolean getBooleanFlagValue(String str, boolean z, int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, z);
        zzaz.writeInt(i);
        zzaz = zza(2, zzaz);
        boolean zza = zzef.zza(zzaz);
        zzaz.recycle();
        return zza;
    }

    public final int getIntFlagValue(String str, int i, int i2) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeInt(i);
        zzaz.writeInt(i2);
        zzaz = zza(3, zzaz);
        int readInt = zzaz.readInt();
        zzaz.recycle();
        return readInt;
    }

    public final long getLongFlagValue(String str, long j, int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeLong(j);
        zzaz.writeInt(i);
        zzaz = zza(4, zzaz);
        long readLong = zzaz.readLong();
        zzaz.recycle();
        return readLong;
    }

    public final String getStringFlagValue(String str, String str2, int i) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzaz.writeInt(i);
        zzaz = zza(5, zzaz);
        String readString = zzaz.readString();
        zzaz.recycle();
        return readString;
    }

    public final void init(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) iObjectWrapper);
        zzb(1, zzaz);
    }
}
