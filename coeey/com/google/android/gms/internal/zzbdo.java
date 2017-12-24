package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzbdo extends zzee implements zzbdn {
    public zzbdo() {
        attachInterface(this, "com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zzo((Status) zzef.zza(parcel, Status.CREATOR));
                break;
            case 2:
                zzp((Status) zzef.zza(parcel, Status.CREATOR));
                break;
            case 3:
                zza((Status) zzef.zza(parcel, Status.CREATOR), parcel.readLong());
                break;
            case 4:
                zzq((Status) zzef.zza(parcel, Status.CREATOR));
                break;
            case 5:
                zzb((Status) zzef.zza(parcel, Status.CREATOR), parcel.readLong());
                break;
            case 6:
                zza((Status) zzef.zza(parcel, Status.CREATOR), (zzbde[]) parcel.createTypedArray(zzbde.CREATOR));
                break;
            case 7:
                zza((DataHolder) zzef.zza(parcel, DataHolder.CREATOR));
                break;
            case 8:
                zza((Status) zzef.zza(parcel, Status.CREATOR), (zzbdc) zzef.zza(parcel, zzbdc.CREATOR));
                break;
            case 9:
                zzb((Status) zzef.zza(parcel, Status.CREATOR), (zzbdc) zzef.zza(parcel, zzbdc.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
