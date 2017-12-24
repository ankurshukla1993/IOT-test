package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;

public final class zzdxu extends zzed implements zzdxt {
    zzdxu(IBinder iBinder) {
        super(iBinder, "com.google.firebase.crash.internal.IFirebaseCrashApi");
    }

    public final void log(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzb(2, zzaz);
    }

    public final void zza(IObjectWrapper iObjectWrapper, zzdxr com_google_android_gms_internal_zzdxr) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) iObjectWrapper);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzdxr);
        zzb(1, zzaz);
    }

    public final void zza(String str, long j, Bundle bundle) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeLong(j);
        zzef.zza(zzaz, (Parcelable) bundle);
        zzb(7, zzaz);
    }

    public final void zzae(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) iObjectWrapper);
        zzb(4, zzaz);
    }

    public final void zzaf(IObjectWrapper iObjectWrapper) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) iObjectWrapper);
        zzb(5, zzaz);
    }

    public final boolean zzbqp() throws RemoteException {
        Parcel zza = zza(9, zzaz());
        boolean zza2 = zzef.zza(zza);
        zza.recycle();
        return zza2;
    }

    public final void zzcg(boolean z) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, z);
        zzb(8, zzaz);
    }

    public final void zzop(String str) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzb(6, zzaz);
    }
}
