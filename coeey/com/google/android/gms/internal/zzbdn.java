package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public interface zzbdn extends IInterface {
    void zza(Status status, long j) throws RemoteException;

    void zza(Status status, zzbdc com_google_android_gms_internal_zzbdc) throws RemoteException;

    void zza(Status status, zzbde[] com_google_android_gms_internal_zzbdeArr) throws RemoteException;

    void zza(DataHolder dataHolder) throws RemoteException;

    void zzb(Status status, long j) throws RemoteException;

    void zzb(Status status, zzbdc com_google_android_gms_internal_zzbdc) throws RemoteException;

    void zzo(Status status) throws RemoteException;

    void zzp(Status status) throws RemoteException;

    void zzq(Status status) throws RemoteException;
}
