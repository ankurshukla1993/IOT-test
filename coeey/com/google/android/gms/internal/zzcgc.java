package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import java.util.List;

public abstract class zzcgc extends zzee implements zzcgb {
    public zzcgc() {
        attachInterface(this, "com.google.android.gms.measurement.internal.IMeasurementService");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        List zza;
        switch (i) {
            case 1:
                zza((zzcfx) zzef.zza(parcel, zzcfx.CREATOR), (zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                break;
            case 2:
                zza((zzckk) zzef.zza(parcel, zzckk.CREATOR), (zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                break;
            case 4:
                zza((zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                break;
            case 5:
                zza((zzcfx) zzef.zza(parcel, zzcfx.CREATOR), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 6:
                zzb((zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                break;
            case 7:
                zza = zza((zzcff) zzef.zza(parcel, zzcff.CREATOR), zzef.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 9:
                byte[] zza2 = zza((zzcfx) zzef.zza(parcel, zzcfx.CREATOR), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeByteArray(zza2);
                break;
            case 10:
                zza(parcel.readLong(), parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                break;
            case 11:
                String zzc = zzc((zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                parcel2.writeString(zzc);
                break;
            case 12:
                zza((zzcfi) zzef.zza(parcel, zzcfi.CREATOR), (zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                break;
            case 13:
                zzb((zzcfi) zzef.zza(parcel, zzcfi.CREATOR));
                parcel2.writeNoException();
                break;
            case 14:
                zza = zza(parcel.readString(), parcel.readString(), zzef.zza(parcel), (zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 15:
                zza = zza(parcel.readString(), parcel.readString(), parcel.readString(), zzef.zza(parcel));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 16:
                zza = zza(parcel.readString(), parcel.readString(), (zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 17:
                zza = zzj(parcel.readString(), parcel.readString(), parcel.readString());
                parcel2.writeNoException();
                parcel2.writeTypedList(zza);
                break;
            case 18:
                zzd((zzcff) zzef.zza(parcel, zzcff.CREATOR));
                parcel2.writeNoException();
                break;
            default:
                return false;
        }
        return true;
    }
}
