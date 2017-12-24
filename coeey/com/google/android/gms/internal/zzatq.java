package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.accounttransfer.DeviceMetaData;
import com.google.android.gms.auth.api.accounttransfer.zzm;
import com.google.android.gms.auth.api.accounttransfer.zzu;
import com.google.android.gms.common.api.Status;

public interface zzatq extends IInterface {
    void onFailure(Status status) throws RemoteException;

    void zza(DeviceMetaData deviceMetaData) throws RemoteException;

    void zza(Status status, zzm com_google_android_gms_auth_api_accounttransfer_zzm) throws RemoteException;

    void zza(Status status, zzu com_google_android_gms_auth_api_accounttransfer_zzu) throws RemoteException;

    void zzaai() throws RemoteException;

    void zze(Status status) throws RemoteException;

    void zzg(byte[] bArr) throws RemoteException;
}
