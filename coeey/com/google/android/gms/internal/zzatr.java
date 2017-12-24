package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzm;
import com.google.android.gms.auth.api.accounttransfer.zzu;
import com.google.android.gms.common.api.Status;

public abstract class zzatr extends zzee implements zzatq {
    public zzatr() {
        attachInterface(this, "com.google.android.gms.auth.api.accounttransfer.internal.IAccountTransferCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zze((Status) zzef.zza(parcel, Status.CREATOR));
                break;
            case 2:
                zza((Status) zzef.zza(parcel, Status.CREATOR), (zzu) zzef.zza(parcel, zzu.CREATOR));
                break;
            case 3:
                zza((Status) zzef.zza(parcel, Status.CREATOR), (zzm) zzef.zza(parcel, zzm.CREATOR));
                break;
            case 4:
                zzaai();
                break;
            case 5:
                onFailure((Status) zzef.zza(parcel, Status.CREATOR));
                break;
            case 6:
                zzg(parcel.createByteArray());
                break;
            case 7:
                zza((DeviceMetaData) zzef.zza(parcel, DeviceMetaData.CREATOR));
                break;
            default:
                return false;
        }
        return true;
    }
}
