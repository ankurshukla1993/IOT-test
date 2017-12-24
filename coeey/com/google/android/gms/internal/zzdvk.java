package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.RemoteException;
import com.google.firebase.auth.ActionCodeSettings;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.UserProfileChangeRequest;

public final class zzdvk extends zzed implements zzdvj {
    zzdvk(IBinder iBinder) {
        super(iBinder, "com.google.firebase.auth.api.internal.IFirebaseAuthService");
    }

    public final void zza(zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(16, zzaz);
    }

    public final void zza(zzdwo com_google_android_gms_internal_zzdwo, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzdwo);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(22, zzaz);
    }

    public final void zza(zzdws com_google_android_gms_internal_zzdws, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzdws);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(3, zzaz);
    }

    public final void zza(PhoneAuthCredential phoneAuthCredential, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzef.zza(zzaz, (Parcelable) phoneAuthCredential);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(23, zzaz);
    }

    public final void zza(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(1, zzaz);
    }

    public final void zza(String str, zzdws com_google_android_gms_internal_zzdws, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (Parcelable) com_google_android_gms_internal_zzdws);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(12, zzaz);
    }

    public final void zza(String str, ActionCodeSettings actionCodeSettings, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (Parcelable) actionCodeSettings);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(25, zzaz);
    }

    public final void zza(String str, PhoneAuthCredential phoneAuthCredential, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (Parcelable) phoneAuthCredential);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(24, zzaz);
    }

    public final void zza(String str, UserProfileChangeRequest userProfileChangeRequest, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (Parcelable) userProfileChangeRequest);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(4, zzaz);
    }

    public final void zza(String str, String str2, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(5, zzaz);
    }

    public final void zza(String str, String str2, String str3, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzaz.writeString(str3);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(11, zzaz);
    }

    public final void zzb(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(2, zzaz);
    }

    public final void zzb(String str, ActionCodeSettings actionCodeSettings, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (Parcelable) actionCodeSettings);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(26, zzaz);
    }

    public final void zzb(String str, String str2, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(6, zzaz);
    }

    public final void zzc(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(9, zzaz);
    }

    public final void zzc(String str, String str2, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(7, zzaz);
    }

    public final void zzd(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(13, zzaz);
    }

    public final void zzd(String str, String str2, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(8, zzaz);
    }

    public final void zze(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(15, zzaz);
    }

    public final void zze(String str, String str2, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(14, zzaz);
    }

    public final void zzf(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(17, zzaz);
    }

    public final void zzf(String str, String str2, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzaz.writeString(str2);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(21, zzaz);
    }

    public final void zzg(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(19, zzaz);
    }

    public final void zzh(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(20, zzaz);
    }

    public final void zzi(String str, zzdvh com_google_android_gms_internal_zzdvh) throws RemoteException {
        Parcel zzaz = zzaz();
        zzaz.writeString(str);
        zzef.zza(zzaz, (IInterface) com_google_android_gms_internal_zzdvh);
        zzb(27, zzaz);
    }
}
