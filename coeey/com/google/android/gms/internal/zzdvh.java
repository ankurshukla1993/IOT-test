package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.PhoneAuthCredential;

public interface zzdvh extends IInterface {
    void onFailure(Status status) throws RemoteException;

    void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zza(Status status, PhoneAuthCredential phoneAuthCredential) throws RemoteException;

    void zza(zzdwc com_google_android_gms_internal_zzdwc) throws RemoteException;

    void zza(zzdwg com_google_android_gms_internal_zzdwg, zzdwe com_google_android_gms_internal_zzdwe) throws RemoteException;

    void zza(zzdwm com_google_android_gms_internal_zzdwm) throws RemoteException;

    void zzb(zzdwg com_google_android_gms_internal_zzdwg) throws RemoteException;

    void zzbpv() throws RemoteException;

    void zzbpw() throws RemoteException;

    void zzbpx() throws RemoteException;

    void zzog(String str) throws RemoteException;

    void zzoh(String str) throws RemoteException;

    void zzoi(String str) throws RemoteException;
}
