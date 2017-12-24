package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthCredential;

public abstract class zzdvi extends zzee implements zzdvh {
    public zzdvi() {
        attachInterface(this, "com.google.firebase.auth.api.internal.IFirebaseAuthCallbacks");
    }

    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (zza(i, parcel, parcel2, i2)) {
            return true;
        }
        switch (i) {
            case 1:
                zzb((zzdwg) zzef.zza(parcel, zzdwg.CREATOR));
                break;
            case 2:
                zza((zzdwg) zzef.zza(parcel, zzdwg.CREATOR), (zzdwe) zzef.zza(parcel, zzdwe.CREATOR));
                break;
            case 3:
                zza((zzdwc) zzef.zza(parcel, zzdwc.CREATOR));
                break;
            case 4:
                zza((zzdwm) zzef.zza(parcel, zzdwm.CREATOR));
                break;
            case 5:
                onFailure((Status) zzef.zza(parcel, Status.CREATOR));
                break;
            case 6:
                zzbpv();
                break;
            case 7:
                zzbpw();
                break;
            case 8:
                zzog(parcel.readString());
                break;
            case 9:
                zzoh(parcel.readString());
                break;
            case 10:
                onVerificationCompleted((PhoneAuthCredential) zzef.zza(parcel, PhoneAuthCredential.CREATOR));
                break;
            case 11:
                zzoi(parcel.readString());
                break;
            case 12:
                zza((Status) zzef.zza(parcel, Status.CREATOR), (PhoneAuthCredential) zzef.zza(parcel, PhoneAuthCredential.CREATOR));
                break;
            case 13:
                zzbpx();
                break;
            default:
                return false;
        }
        return true;
    }
}
